package AthleticsApp;

import javafx.scene.image.Image;

public class AthleticsProduct {
    private String productName, colour, sport;
    private int units;
    private double price;
    private Image imageFile;

    public AthleticsProduct(String productName, String colour, String sport, double price, int units, Image imageFile) {
        setProductName(productName);
        setColour(colour);
        setColour(sport);
        setPrice(price);
        setUnits(units);
        setImageFile(imageFile);
    }

    public AthleticsProduct(String productName, String colour, String sport, double price, int units) {
        setProductName(productName);
        setColour(colour);
        setColour(sport);
        setPrice(price);
        setUnits(units);
        setImageFile(imageFile);
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        if (!productName.isEmpty()) {
            this.productName = productName;
        } else {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        if (!colour.isEmpty()) {
            this.colour = colour;
        } else {
            throw new IllegalArgumentException("Colour cannot be empty");
        }
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        if (!sport.isEmpty()) {
            this.sport = sport;
        } else {
            throw new IllegalArgumentException("Sport cannot be empty");
        }
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price >= 0.1 && price <= 10000) {
            this.price = price;
        } else {
            throw new IllegalArgumentException("Price shoud be between 0.1 and 10000");
        }

    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        if (units >= 0.1 && units <= 10000) {
            this.units = units;
        } else {
            throw new IllegalArgumentException("Units shoud be between 0.1 and 10000");
        }
    }

    public void sellUnits(int units) {
        if (units > 0) {
            this.units = units - 1;
        } else {
            throw new IllegalArgumentException("No units in stock");
        }
    }

    public Image getImageFile() {
        return imageFile;
    }

    public void setImageFile(Image imageFile) {
        this.imageFile = imageFile;
    }

    public String toString() {
        return String.format("Product Name= %s, Price= %s, Units=%s", productName, price, units);
    }

}

