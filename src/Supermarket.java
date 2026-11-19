import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeMap;

public class Supermarket {

    void main(){
        Robot robot = new Robot();
        KasseApparat ka = new KasseApparat();
        Collection<Vare> alleVarer = FileIO.readFiles("data/varer.csv");

        Collection<Vare> kurv = robot.fyldIKurv(alleVarer);

        ka.printReceipt(kurv);

    }

}
