import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileIO {

    /*public static ArrayList<Vare> readItems(String path){
        File items = new File(path);
        ArrayList<Vare> itemsList = new ArrayList<>();
        try {
            Scanner scan = new Scanner(items);
            scan.nextLine();
            while (scan.hasNextLine()){
                String line = scan.nextLine();
                String[] values = line.split(";");

                Vare vare = getItems(values);

                itemsList.add(vare);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }

        return itemsList;
    }*/

    private static ArrayList<String> readItems(String path){
        File items = new File(path);
        ArrayList<String> list = new ArrayList<>();
        try {
            Scanner scan = new Scanner(items);
            scan.nextLine();
            while (scan.hasNextLine()){
                String line = scan.nextLine();

                list.add(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }

        return list;
    }

    public static ArrayList<Vare> convertToItems(String path){
        ArrayList<Vare> items = new ArrayList<>();
        for (String s : readItems(path)){

            String[] line = s.split(";");
            items.add(getItems(line));
        }
        return items;
    }

    public static ArrayList<TilbudsVare> convertToSaleItems(String path){
        ArrayList<TilbudsVare> items = new ArrayList<>();
        for (Vare v : convertToItems(path)){
            items.add((TilbudsVare) v);
        }
        return items;
    }

    private static Vare getItems(String[] values){
        int id = Integer.parseInt(values[0].trim());
        String name = values[1].trim();
        double priceDate = Double.parseDouble(values[2].trim());
        double price = Double.parseDouble(values[3].trim());
        boolean isBudgetBrand = Boolean.parseBoolean(values[4].trim());
        int quantity = Integer.parseInt(values[5].trim());
        String unit = values[6].trim();
        boolean isWeighted = Boolean.parseBoolean(values[7].trim());
        boolean isBio = Boolean.parseBoolean(values[8].trim());
        boolean isAvailable = Boolean.parseBoolean(values[9].trim());


        return new Vare(id, name, priceDate, price, isBudgetBrand, quantity, unit, isWeighted, isBio, isAvailable);
    }
}
