package objects;

import exception.CircuitDetectedException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Tools {
    public static List<List<Etat>> getRanks(Automate automate) throws Exception {
        List<List<Etat>> ranks = new ArrayList<>();
        List<Etat> currentStep, lastStep = null;
        do {
            currentStep = automate.getNextRanks();

            if (currentStep.equals(lastStep))
                throw new CircuitDetectedException("Circuit detected");

            automate.removeSommets(currentStep);
            ranks.add(currentStep);

            lastStep = currentStep;
        } while (!automate.getSommets().isEmpty());
        return ranks;
    }
    
    public static void calculCalendrierAuPlusTot(Automate graphe) {
    	Etat alpha = new Etat("Alpha", true, false, new ArrayList<>(), new Dates());
    	Etat omega = new Etat("Omega", false, true, new ArrayList<>(), new Dates());
    	
    	Map<Etat, Integer> datesPlusTot = new LinkedHashMap<>();
    	
    	for (Etat etat : graphe.getSommets()) {
    		if (graphe.getPredecessors(etat).size() == 0) {
    			etat.getDates().setPlusTot(Integer.MIN_VALUE);
    			Transition transition = new Transition(0, alpha, etat);
    			alpha.getDates().setPlusTot(0);
    			List<Transition> transitions = alpha.getTransitions();
    			transitions.add(transition);
    			alpha.setTransitions(transitions);
    		} else {
    			etat.getDates().setPlusTot(Integer.MIN_VALUE);
    		}
    		
    		if (graphe.getSuccessors(etat).size() == 0) {
    			Transition transition = new Transition(0, etat, omega);
    			omega.getDates().setPlusTot(Integer.MIN_VALUE);
    			List<Transition> transitions = etat.getTransitions();
    			transitions.add(transition);
    			etat.setTransitions(transitions);
    		}
    	}
    	
    	graphe.getSommets().add(alpha);
    	graphe.getSommets().add(omega);
    	datesPlusTot.put(alpha, 0);
    	datesPlusTot.put(omega, calendrierAuPlusTot(graphe, omega, datesPlusTot));
    	displayDates(datesPlusTot, "Calendrier au plus tot");
    }
    
    public static void calculCalendrierAuPlusTard(Automate graphe, int finDatePlutTot) {
    	Etat alpha = new Etat("Alpha", true, false, new ArrayList<>(), new Dates());
    	Etat omega = new Etat("Omega", false, true, new ArrayList<>(), new Dates());
    	
    	Map<Etat, Integer> datesPlusTard = new LinkedHashMap<>();
    	
    	for (Etat etat : graphe.getSommets()) {
    		if (graphe.getPredecessors(etat).size() == 0) {
    			etat.getDates().setPlusTard(Integer.MAX_VALUE);
    			Transition transition = new Transition(0, alpha, etat);
    			alpha.getDates().setPlusTard(Integer.MAX_VALUE);
    			List<Transition> transitions = alpha.getTransitions();
    			transitions.add(transition);
    			alpha.setTransitions(transitions);
    		} else {
    			etat.getDates().setPlusTard(Integer.MAX_VALUE);
    		}
    		
    		if (graphe.getSuccessors(etat).size() == 0) {
    			Transition transition = new Transition(0, etat, omega);
    			omega.getDates().setPlusTard(finDatePlutTot);
    			List<Transition> transitions = etat.getTransitions();
    			transitions.add(transition);
    			etat.setTransitions(transitions);
    		}
    	}
    	
    	graphe.getSommets().add(alpha);
    	graphe.getSommets().add(omega);
    	
    	datesPlusTard.put(alpha, calendrierAuPlusTard(graphe, alpha, datesPlusTard));
    	datesPlusTard.put(omega, finDatePlutTot);
    	
    	displayDates(datesPlusTard, "Calendrier au plus tard");
    }

    public static int calendrierAuPlusTot(Automate graphe, Etat etat, Map<Etat, Integer> datesPlusTot) {
    	if (etat.getDates().plusTot != Integer.MIN_VALUE)
    		return etat.getDates().plusTot;
    	
    	int datePlusTot = 0;
    	
    	for (Etat e : graphe.getPredecessors(etat)) {
    		datePlusTot = Math.max(datePlusTot, calendrierAuPlusTot(graphe, e, datesPlusTot) + 
    				e.getTransitions().stream().filter(t -> t.getArrivee().getValue().equals(etat.getValue())).collect(Collectors.toList()).get(0).getValue());
    	}
    	
    	etat.getDates().setPlusTot(datePlusTot);
    	datesPlusTot.put(etat, datePlusTot);
		return etat.getDates().plusTot;
    }
    
    public static int calendrierAuPlusTard(Automate graphe, Etat etat, Map<Etat, Integer> datesPlusTard) {
    	if (etat.getDates().plusTard != Integer.MAX_VALUE)
    		return etat.getDates().plusTard;
    	
    	int datePlusTard = Integer.MAX_VALUE;
    	
    	for (Etat e : graphe.getSuccessors(etat)) {
    		datePlusTard = Math.min(datePlusTard, calendrierAuPlusTard(graphe, e, datesPlusTard) - 
    				etat.getTransitions().stream().filter(t -> t.arrivee.getValue().equals(e.getValue())).collect(Collectors.toList()).get(0).getValue());
    	}
    	
    	etat.getDates().setPlusTard(datePlusTard);
    	datesPlusTard.put(etat, datePlusTard);
		return etat.getDates().plusTard;
    }
    
    public static void displayDates(Map<Etat, Integer> dates, String calendrier) {
    	Map<Etat, Integer> result = new LinkedHashMap<>();
    	String indent = String.format("%-" + (dates.size() - 5) + "s", "");
    	StringBuilder sb = new StringBuilder();
    	
    	Map.Entry<Etat, Integer> entry = dates.entrySet().stream().filter(e -> e.getKey().getValue().equals("Alpha")).collect(Collectors.toList()).get(0);
    	result.put(entry.getKey(), entry.getValue());
    	
    	for (int i = 0; i < dates.size() - 2; i++) {
    		final int index = i;
    		entry = dates.entrySet().stream().filter(e -> e.getKey().getValue().equals(String.valueOf(index))).collect(Collectors.toList()).get(0);
    		result.put(entry.getKey(), entry.getValue());
    	}
    	
    	entry = dates.entrySet().stream().filter(e -> e.getKey().getValue().equals("Omega")).collect(Collectors.toList()).get(0);
    	result.put(entry.getKey(), entry.getValue());
    	
    	for (Map.Entry<Etat, Integer> e : result.entrySet()) {
    		if (e.getKey().getValue().equals("Alpha")) {
    			sb.append("Debut" + indent.substring(0, indent.length() - "Alpha".length()));
    		} else if (e.getKey().getValue().equals("Omega")) {
    			sb.append("Fin" + indent.substring(0, indent.length() - "Fin".length()));
    		} else {
    			sb.append(e.getKey().value + indent.substring(0, indent.length() - e.getKey().value.length()));
    		}
    	}
    	
    	sb.append("\n");
    	
    	for (int date : result.values()) {
    		sb.append(date + indent.substring(0, indent.length() - String.valueOf(date).length()));
    	}
    	
    	System.out.println(calendrier + ":\n" + sb.toString());
    }
    
    /*public static void calculateDatesPlusTot(List<List<Etat>> ranks, Automate automate) {
        for (int i = 0; i < ranks.size(); i++) {
            if (i == 0) {
                for (int j = 0; j < ranks.get(i).size(); j++) {
                    automate.getSommetFromVal(ranks.get(i).get(j).value).getDates().setPlusTot(0);
                }
            } else {
                int max = Integer.MIN_VALUE;
                for (int j = 0; j < ranks.get(i).size(); j++) {
                    Etat n = automate.getSommetFromVal(ranks.get(i).get(j).value);
                    for (int k = 0; k < ranks.get(i - 1).size(); k++) {
                        Etat nm1 = automate.getSommetFromVal(ranks.get(i - 1).get(k).value);

                        if (!automate.getTransitionsFromXtoY(nm1, n).isEmpty()) {
                            if (Collections.max(automate.getTransitionsFromXtoY(nm1, n)) + nm1.getDates().plusTot > max) {
                                max = nm1.getDates().plusTot + Collections.max(automate.getTransitionsFromXtoY(nm1, n));
                            }
                        }
                    }
                    n.getDates().plusTot = max;
                }
            }
        }
    }*/

    /*public static void calculateDatePluTard(List<List<Etat>> ranks, Automate automate) {
        for (int i = ranks.size() - 1; i >= 0; i--) {
            if (i == ranks.size() - 1) {
                for (int j = ranks.get(i).size() - 1; j >= 0; j--) {
                    automate.getSommetFromVal(ranks.get(i).get(j).value).getDates().setPlusTard(automate.getSommetFromVal(ranks.get(i).get(j).value).getDates().plusTot);
                }
            } else {
                int min = Integer.MAX_VALUE;
                for (int j = 0; j < ranks.get(i).size(); j++) {
                    Etat n = automate.getSommetFromVal(ranks.get(i).get(j).value);
                    for (int k = 0; k < ranks.get(i + 1).size(); k++) {
                        Etat np1 = automate.getSommetFromVal(ranks.get(i + 1).get(k).value);
                        if (!automate.getTransitionsFromXtoY(n, np1).isEmpty()) {
                            if (Collections.min(automate.getTransitionsFromXtoY(n, np1)) - np1.getDates().plusTard < min) {
                                min = np1.getDates().plusTard - Collections.min(automate.getTransitionsFromXtoY(n, np1));
                            }
                        }
                    }
                    n.getDates().plusTard = min;
                }
            }
        }
    }*/

    public static void displayRanks(List<List<Etat>> ranks) {
        for (int i = 0; i < ranks.size(); i++) {
            System.out.print("Rank " + (i + 1) + " : ");
            for (int j = 0; j < ranks.get(i).size(); j++) {
                System.out.print(ranks.get(i).get(j).value + "  ");
            }
            System.out.println("");
        }
    }

    public static void displayDates(Automate automate) {
        for (Etat e : automate.getSommets()) {
            System.out.println("Sommets : " + e.value + " Date +tot : " + e.getDates().plusTot + " Date +tard : " + e.getDates().plusTard);
        }
    }
}
