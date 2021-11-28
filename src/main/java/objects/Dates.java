package objects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dates {
    int plusTot;
    int plusTard;

    @Override
    public String toString() {
        return "Dates{" +
                "plusTot=" + plusTot +
                ", plusTard=" + plusTard +
                '}';
    }

	public void setPlusTot(int plusTot) {
		this.plusTot = plusTot;
	}

	public void setPlusTard(int plusTard) {
		this.plusTard = plusTard;
	}
	
	public int getPlusTot() {
		return plusTot;
	}
	
	public int getPlusTard() {
		return plusTard;
	}
}
