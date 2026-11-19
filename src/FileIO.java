import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;

public class FileIO {

    public ArrayList<Vare> readFiles(String path){
        File file = new File(path);
        ArrayList<Vare> items = new ArrayList<>();

        try  {
            Scanner scan = new Scanner(file);
            scan.nextLine();
            while (scan.hasNextLine()){
                String line = scan.nextLine();
                String[] values = line.split(";");

                String id = values[0].trim();
                String name = values[1].trim();
                double priceDate = Double.parseDouble(values[2].trim());
                double price = Double.parseDouble(values[3].trim());
                boolean isBudgetBrand = Boolean.parseBoolean(values[4].trim());
                int quantity = Integer.parseInt(values[5].trim());
                String unit = values[6].trim();
                boolean isWeighted = Boolean.parseBoolean(values[7].trim());
                boolean isBio = Boolean.parseBoolean(values[8].trim());
                boolean isAvailable = Boolean.parseBoolean(values[9].trim());

                Vare item = new Vare(id,name,priceDate,price,isBudgetBrand,quantity,unit,isWeighted,isBio,isAvailable);

                items.add(item);
            }
        } catch (java.io.FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return items;
    }


}
