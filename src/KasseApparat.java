import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeMap;

public class KasseApparat {

    TreeMap<String, Vare> alleVarer = readToMap("data/varer.csv");
    TreeMap<String, Vare> tilbudsVarer = readToMap("data/tilbud.csv");


    public TreeMap<String, Vare> readToMap(String path){
        TreeMap<String, Vare> treeMap = new TreeMap<>();
        for (Vare v : FileIO.readFiles(path)){
            treeMap.put(v.getId(), v);
        }
        return treeMap;
    }

    public double getPriceBeforeSaleAfterTax(Collection<Vare> kurv){
        return getTotalPrice(kurv) * 0.25;
    }

    public double getTotalPrice(Collection<Vare> kurv){
        double sum = 0;
        for (Vare vare : kurv){
            sum += alleVarer.get(vare.getId()).getPrice();
        }
        return sum;
    }

    public double getTotalPriceAfterSale(Collection<Vare> kurv){
        double sum = 0;
        for (Vare vare : kurv){
            if (checkForSale(vare)){
                sum += tilbudsVarer.get(vare.getId()).getPrice();
            } else sum += alleVarer.get(vare.getId()).getPrice();
        }

        return sum;
    }

    public boolean checkForSale(Vare vare){
        //hvis tilbudsvarer contains key, så true
        if (tilbudsVarer.containsKey(vare.getId())){
            return true;
        } else return false;
    }

    public void printReciept(){

    }



}
