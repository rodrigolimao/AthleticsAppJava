package AthleticsApp;

import javafx.scene.image.Image;
import java.util.*;
import java.util.stream.Collectors;

public class Inventory {

    private TreeMap<String, LinkedList<AthleticsProduct>> athleticsTreeMap;

    public Inventory() {
        athleticsTreeMap = new TreeMap<>();
        loadProducts();
    }

    /**
     * Method to addProduct in the TreeMap created
     */
    public void addProduct(String productName, String colour, String sport, double price, int units, Image imageFile) {
        String key = sport;
        AthleticsProduct newAthleticsProduct = new AthleticsProduct(productName, colour, sport, price, units, imageFile);
        if (athleticsTreeMap.containsKey(key)) {
            athleticsTreeMap.get(key).add(newAthleticsProduct);
        } else {
            LinkedList<AthleticsProduct> linkedList = new LinkedList<>();
            linkedList.add(newAthleticsProduct);
            athleticsTreeMap.put(sport, linkedList);
        }
    }

    /**
     * Method to load the Products created in the TreeMap
     */
    public void loadProducts() {
        addProduct("Neymar Short", "Blue", "Soccer", 20, 20, new Image("AthleticsApp/images/neymar.jpg"));
        addProduct("Messi Shirt", "Blue", "Soccer", 150, 20, new Image("AthleticsApp/images/messi.jpg"));
        addProduct("Modric Socks", "Blue", "Soccer", 50, 20, new Image("AthleticsApp/images/modric.jpg"));
        addProduct("Ronaldo Cleats", "Black", "Soccer", 100, 20, new Image("AthleticsApp/images/cr7.jpg"));
        addProduct("Michael Jordan Short", "Yellow", "Basketball", 15, 20, new Image("AthleticsApp/images/jordan.jpg"));
        addProduct("King James Shirt", "Blue", "Basketball", 15, 20, new Image("AthleticsApp/images/james.jpeg"));
        addProduct("Kevin Durant Socks", "Yellow", "Basketball", 10, 20, new Image("AthleticsApp/images/durant.jpg"));
        addProduct("Stephen Curry Shoes", "Black", "Basketball", 200, 20, new Image("AthleticsApp/images/curry.jpg"));
        addProduct("Vodka Mug", "Yellow", "Cheerleader", 35, 20, new Image("AthleticsApp/images/Caneca.jpg"));
        addProduct("Cheer Combo", "Blue", "Cheerleader", 10, 20, new Image("AthleticsApp/images/cheer.jpg"));
        addProduct("Short Doll", "Yellow", "Cheerleader", 25, 20, new Image("AthleticsApp/images/Short Doll.jpg"));
        addProduct("Sexy Socks", "Blue", "Cheerleader", 10, 20, new Image("AthleticsApp/images/Meia.jpg"));
        addProduct("Calderano Shirt", "Blue", "Table Tennis", 100, 20, new Image("AthleticsApp/images/calderano.jpeg"));
        addProduct("Timo Boll Bat", "Yellow", "Table Tennis", 250, 20, new Image("AthleticsApp/images/timoBoll.jpg"));
        addProduct("XU Xin Short", "Blue", "Table Tennis", 50, 20, new Image("AthleticsApp/images/xuxin.png"));
    }

    /**
     * Return a List with all products for a specific category
     **/
    public LinkedList<AthleticsProduct> productsByCategory(String category) {
        return athleticsTreeMap.get(category);

    }

    /**
     * Return a List with ALL products in Linkedlist
     **/
    public LinkedList<AthleticsProduct> allProductsList() {
        LinkedList<AthleticsProduct> arrayList = new LinkedList<>();
        for (String chave : athleticsTreeMap.keySet()) {
            arrayList.addAll(athleticsTreeMap.get(chave));
        }
        return arrayList;
    }

    /**
     * Return ALL category names
     **/
    public Set<String> getAllCategories() {
        for (Map.Entry<String, LinkedList<AthleticsProduct>> entry : athleticsTreeMap.entrySet()) {
            String key = entry.getKey();
        }
        return athleticsTreeMap.keySet();
    }

    /**
     * Sell Unit method
     *
     * @param product
     */
    public void sellingUnits(AthleticsProduct product) {
        int unitsLeft = product.getUnits();
        unitsLeft--;
        product.setUnits(unitsLeft);
    }

    /**
     * Sorting in ascending order using streams
     * @param prod
     * @return
     */

    public List<AthleticsProduct> ascendingSort(List<AthleticsProduct> prod) {
        return prod.stream()
                .sorted((a, b) -> a.getProductName().compareToIgnoreCase(b.getProductName()))
                .collect(Collectors.toList());
    }
    /**
     * Sorting in descending order using streams
     * @param prod
     * @return
     */
    public List<AthleticsProduct> descendingSort(List<AthleticsProduct> prod) {
        return prod.stream()
                .sorted((a, b) -> b.getProductName().compareToIgnoreCase(a.getProductName()))
                .collect(Collectors.toList());
    }
    /**
     * Sorting the price from low to high using Collections and lambdas
     * @param prod
     * @return
     */

    public List<AthleticsProduct> lowPriceSort(List<AthleticsProduct> prod) {
        Collections.sort(prod, (a, b) -> {
            if (a.getPrice() > b.getPrice())
                return 1;
            else
                return -1;
        });
        return prod;
    }
    /**
     * Sorting the price from high to low using Collections and lambdas
     * @param prod
     * @return
     */
    public List<AthleticsProduct> highPriceSort(List<AthleticsProduct> prod) {
        Collections.sort(prod, (a, b) -> {
            if (a.getPrice() > b.getPrice())
                return -1;
            else
                return 1;
        });
        return prod;
    }

//    public List<AthleticsProduct> highPriceSorta(List<AthleticsProduct> prod) {
//        return prod.stream()
//                .sorted((a, b) -> b.getPrice()>(a.getPrice()))
//                .collect(Collectors.toList());
//
//
//
//    }
    }






