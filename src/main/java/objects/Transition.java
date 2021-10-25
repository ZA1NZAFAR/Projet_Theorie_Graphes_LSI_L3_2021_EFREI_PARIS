package objects;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transition implements Comparable {
    char value;
    Etat depart;
    Etat arrivee;

    @Override
    public String toString() {
        return "Transition{" +
                "value=" + value +
                ", depart=" + depart.nom +
                ", arrivee=" + arrivee.nom +
                "}";
    }

    @Override
    public int compareTo(Object o) {
        Transition obj = (Transition) o;
        if (this.value > obj.value)
            return 1;
        else if (this.value < obj.value)
            return -1;
        else return 0;
    }
}
