package objects;

import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Etat {
    Integer value;
    boolean isInitial;
    boolean isTerminal;
    List<Transition> transitions;
    Dates dates;

    public Etat(Integer i) {
        this.value = i;
        transitions = new ArrayList<>();
    }
    
    public Etat(int value, boolean isInitial, boolean isTerminal, List<Transition> transitions, Dates dates) {
    	this.value = value;
    	this.isInitial = isInitial;
    	this.isTerminal = isTerminal;
    	this.transitions = transitions;
    	this.dates = dates;
    }

    @Override
    public String toString() {
        return "Etat{" +
                "value=" + value +
                ", isInitial=" + isInitial +
                ", isTerminal=" + isTerminal +
                ", transitions=" + transitions +
                ", dates=" + dates +
                '}';
    }

    List<Etat> getListOfSuccessorsWithAValue(Integer c) {
        List<Etat> set = new ArrayList<>();
        for (Transition t : transitions) {
            if (t.value == c) {
                set.add(t.arrivee);
            }
        }
        return set;
    }

	public List<Transition> getTransitions() {
		return transitions;
	}

	public Dates getDates() {
		return dates;
	}
}

