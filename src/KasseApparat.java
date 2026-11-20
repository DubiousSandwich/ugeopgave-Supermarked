import java.util.*;

public class KasseApparat {

    TreeMap<String, Vare> alleVarer = readToMap("data/varer.csv");
    TreeMap<String, Vare> tilbudsVarer = readToMap("data/tilbud.csv");

    public KasseApparat(){

    }

    private TreeMap<String, Vare> readToMap(String path){
        TreeMap<String, Vare> treeMap = new TreeMap<>();
        for (Vare v : FileIO.readFiles(path)){
            treeMap.put(v.getId(), v);
        }
        return treeMap;
    }

    private double getPriceBeforeSaleAfterTax(Collection<Vare> kurv){
        return getTotalPrice(kurv) * 0.25;
    }

    private double getTotalPrice(Collection<Vare> kurv){
        double sum = 0;
        for (Vare vare : kurv){
            sum += alleVarer.get(vare.getId()).getPrice();
        }
        return sum;
    }

    private double getTotalPriceAfterSale(Collection<Vare> kurv){
        double sum = 0;
        for (Vare vare : kurv){
            if (checkForSale(vare)){
                sum += tilbudsVarer.get(vare.getId()).getPrice();
            } else sum += alleVarer.get(vare.getId()).getPrice();
        }

        return sum;
    }

    private boolean checkForSale(Vare vare){
        //hvis tilbudsvarer contains key, så true
        if (tilbudsVarer.containsKey(vare.getId())){
            return true;
        } else return false;
    }

    private int getAmountOfDifferentItems(Collection<Vare> kurv){
        HashSet<Vare> varer = new HashSet<>(kurv);
        return varer.size();
    }

    private HashMap<Vare, Integer> getSortedMap(Collection<Vare> kurv){
        HashMap<Vare, Integer> varer = new HashMap<>();
        for (Vare v : kurv){
            if (checkForSale(v)){
                if (varer.containsKey(v)){
                    varer.put(tilbudsVarer.get(v.getId()), varer.get(v) + 1);
                } else varer.put(tilbudsVarer.get(v.getId()), 1);
            } else if (varer.containsKey(v)){
                varer.put(alleVarer.get(v.getId()), varer.get(v) + 1);
            } else varer.put(alleVarer.get(v.getId()), 1);
        }
        return varer;  //this is ass
    }

    public void printReceipt(Collection<Vare> kurv){
        HashMap<Vare, Integer> varer = getSortedMap(kurv);
        for (Vare v : varer.keySet()){
            double price = v.getPrice();
            System.out.println(v + " " + varer.get(v) + ", " + price);
        }
        System.out.println("antal varer: "+getAmountOfDifferentItems(kurv) +
                "\ntotalpris(før tilbud, før moms): "+getTotalPrice(kurv) +
                "\nTotalpris(efter tilbud) " + getTotalPriceAfterSale(kurv) +
                "\nMoms " + getPriceBeforeSaleAfterTax(kurv));
    }



}
