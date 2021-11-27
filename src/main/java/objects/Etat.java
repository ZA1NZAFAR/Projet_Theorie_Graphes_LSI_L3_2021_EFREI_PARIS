package objects;

import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Etat {
    String value;
    boolean isInitial;
    boolean isTerminal;
    List<Transition> transitions;
    Dates dates;
    
    public String getValue() {
    	return value;
    }

    public Etat(String value) {
        this.value = value;
        transitions = new ArrayList<>();
    }
    
    public Etat(String value, boolean isInitial, boolean isTerminal, List<Transition> transitions, Dates dates) {
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
	
	public void setTransitions(List<Transition> transitions) {
		this.transitions = transitions;
	}

	public Dates getDates() {
		return dates;
	}
}

