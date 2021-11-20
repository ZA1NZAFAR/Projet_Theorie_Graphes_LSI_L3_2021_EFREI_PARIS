package objects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class Dates {
    int plusTot;
    int plusTard;

    @Override
    public String toString() {
        return "Dates{" +
                "plusTot=" + plusTot +
                ", plusTard=" + plusTard +
                '}';
    }
}
