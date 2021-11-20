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
}

