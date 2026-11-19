import java.util.ArrayList;
import java.util.HashMap;

public class KasseApparat {
    private ArrayList<Vare> Items;
    private ArrayList<TilbudsVare> itemsOnSale;
    private HashMap<Integer, Vare> allItems;

    public KasseApparat(){
        this.Items = FileIO.convertToItems("data/varer.csv");
        this.itemsOnSale = FileIO.convertToSaleItems("data/tilbud.csv");
        this.allItems = addToMap();
    }

    private HashMap<Integer, Vare> addToMap(){
        for (TilbudsVare v : itemsOnSale){
            allItems.put(v.getId(), v);
        }
        for (Vare v : Items){
            allItems.put(v.getId(), v);
        }
        return allItems;
    }

}
