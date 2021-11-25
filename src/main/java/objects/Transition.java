package objects;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transition implements Comparable<Transition> {
    int value;
    Etat depart;
    Etat arrivee;

    @Override
    public String toString() {
        return "Transition{" +
                "value=" + value +
                ", depart=" + depart.value +
                ", arrivee=" + arrivee.value +
                "}";
    }
    
    public Transition(int value, Etat depart, Etat arrivee) {
    	this.value = value;
    	this.depart = depart;
    	this.arrivee = arrivee;
    }

    @Override
    public int compareTo(Transition o) {
        return Integer.compare(this.value, o.value);
    }
}
