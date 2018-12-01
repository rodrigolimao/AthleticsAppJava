package AthleticsApp;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

public class Inventory {

    public static TreeMap<String, LinkedList<AthleticsProduct>> athleticsTreeMap = new TreeMap<>();

    /*
    Method to addProduct in the TreeMap created
     */
    public static void addProduct(String productName, String colour, String sport, double price, int units) {
        String key = sport;
        AthleticsProduct newAthleticsProduct = new AthleticsProduct(productName, colour, sport, price, units);
        if (athleticsTreeMap.containsKey(key)) {
            athleticsTreeMap.get(key).add(newAthleticsProduct);
        } else {
            LinkedList<AthleticsProduct> linkedList = new LinkedList<>();
            linkedList.add(newAthleticsProduct);
            athleticsTreeMap.put(sport, linkedList);
        }
    }

    /*
    Method to load the Products created in the TreeMap
     */
    public static void loadProducts() {
        addProduct("Short", "Blue", "Soccer", 15, 20);
        addProduct("Shirt", "Blue", "Soccer", 15, 20);
        addProduct("Socks", "Blue", "Soccer", 5, 20);
        addProduct("cleats", "Black", "Soccer", 100, 20);
        addProduct("Short", "Yellow", "Basketball", 15, 20);
        addProduct("Shirt", "Blue", "Basketball", 15, 20);
        addProduct("Socks", "Yellow", "Basketball", 10, 20);
        addProduct("Shoes", "Black", "Basketball", 200, 20);
        addProduct("Hat", "Yellow", "Cheerleader", 10, 20);
        addProduct("Cheer Shirt", "Blue", "Cheerleader", 10, 20);
        addProduct("Short Doll", "Yellow", "Cheerleader", 25, 20);
        addProduct("Socks", "Blue", "Cheerleader", 10, 20);
    }

    /*
    Return a List with all products for a specific category
     */
    public static LinkedList<AthleticsProduct> productsByCategory(String category) {
        return athleticsTreeMap.get(category);
    }

    /*
    Return a List with ALL products in an ArrayList
     */
    public static ArrayList<AthleticsProduct> allProductsArrayList() {
        ArrayList<AthleticsProduct> arrayList = new ArrayList<>();
        for (String chave : athleticsTreeMap.keySet()) {
            arrayList.addAll(athleticsTreeMap.get(chave));
        }
        return arrayList;
    }

    /*
    Return ALL category names
     */
    public static TreeMap<String, LinkedList<AthleticsProduct>> getAllCategory() {
        for (Map.Entry<String, LinkedList<AthleticsProduct>> entry : athleticsTreeMap.entrySet()) {
            String key = entry.getKey();
            System.out.println(key);
        }
        return athleticsTreeMap;
    }
}


