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

    public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Etat getDepart() {
		return depart;
	}

	public void setDepart(Etat depart) {
		this.depart = depart;
	}

	public Etat getArrivee() {
		return arrivee;
	}

	public void setArrivee(Etat arrivee) {
		this.arrivee = arrivee;
	}

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
