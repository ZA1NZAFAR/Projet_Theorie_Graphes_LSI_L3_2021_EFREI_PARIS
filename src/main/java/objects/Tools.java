package objects;

import java.util.List;
import java.util.stream.Collectors;

public class Tools {
    public static List<Etat> getSuccesso(Automate a){
        return a.getEtats().stream().filter(etat -> etat.isInitial).collect(Collectors.toList());
    }
}
