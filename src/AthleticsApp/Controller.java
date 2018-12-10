package AthleticsApp;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class Controller implements Initializable {

    @FXML
    private ComboBox sportComboBox;
    @FXML
    private ImageView imageView;
    @FXML
    private ListView listView;
    @FXML
    private RadioButton radioButtonPriceHigh;
    @FXML
    private RadioButton radioButtonPriceLow;
    @FXML
    private RadioButton radioButtonProdAsc;
    @FXML
    private RadioButton radioButtonProductDesc;
    @FXML
    private Button sellingButton;
    @FXML
    private Label totalInventoryLabel;
    @FXML
    private Label totalCategoryLabel;

    Inventory inventory = new Inventory();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Setting prompt text of ComboBox
        sportComboBox.setPromptText("Select just one sport...");

        //Method to choose the appropriate list of each category in the ComboBox
        comboBoxUpdated();

        //Populating the ComboBox with Category names
        sportComboBox.getItems().addAll(inventory.getCategoriesNames());

        //Setting Category Label to Not Applicable, since there was no selection
        totalCategoryLabel.setText("N/A");

        //Setting opening image
        imageView.setImage(new Image("AthleticsApp/images/atletica.png"));

        //Populating the List View
        listView.getItems().addAll(inventory.allProductsList());
        listView.getSelectionModel().select(0);

        //Radio button updated if the comboBox is not selected
        radioButtonsUpdatedGeneral();

        //Setting the total Inventory Label to the calculation formula created below
        totalInventoryLabel.setText(Double.toString(totalInventoryCalculation()));

        //Adding the Listener in the listView
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<AthleticsProduct>() {
            @Override
            public void changed(ObservableValue<? extends AthleticsProduct> observable, AthleticsProduct oldValue, AthleticsProduct newValue) {
                if (newValue != null)
                    //Changes the picture
                    imageView.setImage(newValue.getImageFile());
            }
        });

        //Selling Button on action to decrease the units and update the category and inventory label
        sellingButton.setOnAction(event -> {
            inventory.sellingUnits((AthleticsProduct) listView.getSelectionModel().getSelectedItem());
            listView.refresh();
            totalInventoryLabel.setText(Double.toString(totalInventoryCalculation()));
            totalCategoryLabel.setText(Double.toString(categoryCalculation()));
        });
    }

    /**
     * Method to calculate the total Inventory using streams
     *
     * @return
     */
    public double totalInventoryCalculation() {
        double totalInventory = inventory.allProductsList()
                .stream()
                .mapToDouble(p -> p.getPrice() * p.getUnits())
                .sum();
        return totalInventory;
    }

    /**
     * Method to calculate the total for each category
     *
     * @return
     */
    public double categoryCalculation() {
        if (sportComboBox.getSelectionModel().getSelectedItem().equals("Soccer")) {
            double soccerTotal = inventory.productsByCategory("Soccer")
                    .stream()
                    .mapToDouble(p -> p.getPrice() * p.getUnits())
                    .sum();
            return soccerTotal;
        } else if (sportComboBox.getSelectionModel().getSelectedItem().equals("Basketball")) {
            double basketballTotal = inventory.productsByCategory("Basketball")
                    .stream()
                    .mapToDouble(p -> p.getPrice() * p.getUnits())
                    .sum();
            return basketballTotal;
        } else if (sportComboBox.getSelectionModel().getSelectedItem().equals("Cheerleader")) {
            double cheerleaderTotal = inventory.productsByCategory("Cheerleader")
                    .stream()
                    .mapToDouble(p -> p.getPrice() * p.getUnits())
                    .sum();
            return cheerleaderTotal;
        } else if (sportComboBox.getSelectionModel().getSelectedItem().equals("Table Tennis")) {
            double tennisTotal = inventory.productsByCategory("Table Tennis")
                    .stream()
                    .mapToDouble(p -> p.getPrice() * p.getUnits())
                    .sum();
            return tennisTotal;
        } else
            return 0;
    }

    /**
     * Method to update the ListView and the Total Category Label
     */
    public void comboBoxUpdated() {
        sportComboBox.setOnAction((event) -> {
            sportComboBox.getSelectionModel().getSelectedItem();
            listView.getItems().clear();
            listView.getItems().addAll(inventory.descendingSort(inventory.productsByCategory(sportComboBox.getSelectionModel().getSelectedItem().toString())));
            listView.getSelectionModel().select(1);
            totalCategoryLabel.setText(Double.toString(categoryCalculation()));
            radioButtonsUpdated();

//                if (sportComboBox.getSelectionModel().getSelectedItem().equals("Soccer")) {
//                listView.getItems().clear();
//                listView.getItems().addAll(inventory.ascedingSort(inventory.productsByCategory("Soccer")));
//                listView.getSelectionModel().select(0);
//                totalCategoryLabel.setText(Double.toString(categoryCalculation()));
//            } else if (sportComboBox.getSelectionModel().getSelectedItem().equals("Basketball")) {
//                listView.getItems().clear();
//                listView.getItems().addAll(inventory.ascedingSort(inventory.productsByCategory("Basketball")));
//                listView.getSelectionModel().select(0);
//                totalCategoryLabel.setText(Double.toString(categoryCalculation()));
//            } else if (sportComboBox.getSelectionModel().getSelectedItem().equals("Cheerleader")) {
//                listView.getItems().clear();
//                listView.getItems().addAll(inventory.ascedingSort(inventory.productsByCategory("Cheerleader")));
//                listView.getSelectionModel().select(0);
//                totalCategoryLabel.setText(Double.toString(categoryCalculation()));
//            } else if (sportComboBox.getSelectionModel().getSelectedItem().equals("Table Tennis")) {
//                listView.getItems().clear();
//                listView.getItems().addAll(inventory.ascedingSort(inventory.productsByCategory("Table Tennis")));
//                listView.getSelectionModel().select(0);
//                totalCategoryLabel.setText(Double.toString(categoryCalculation()));
//            } else {
//                listView.getItems().clear();
//                sportComboBox.setPromptText("Select a Genre");
//                listView.getItems().addAll(inventory.allProductsList());
//                totalCategoryLabel.setText("N/A");
//            }
        });
    }

    /**
     * Method to update the radioButton once the comboBox has been selected
     */
    public void radioButtonsUpdated() {

        radioButtonProductDesc.setOnAction((event) -> {
            listView.getItems().clear();
            listView.getItems().addAll(inventory.descendingSort(inventory.productsByCategory(sportComboBox.getSelectionModel().getSelectedItem().toString())));

        });
        radioButtonProdAsc.setOnAction((event) -> {
            listView.getItems().clear();
            listView.getItems().addAll(inventory.ascedingSort(inventory.productsByCategory(sportComboBox.getSelectionModel().getSelectedItem().toString())));

        });
        radioButtonPriceLow.setOnAction((event) -> {
            listView.getItems().clear();
            listView.getItems().addAll(inventory.lowPriceSort(inventory.productsByCategory(sportComboBox.getSelectionModel().getSelectedItem().toString())));
        });
        radioButtonPriceHigh.setOnAction((event) -> {
            listView.getItems().clear();
            listView.getItems().addAll(inventory.highPriceSort(inventory.productsByCategory(sportComboBox.getSelectionModel().getSelectedItem().toString())));
        });
    }

    /**
     * Method to sort the list if the ComboBox is not selected yet
     */

    public void radioButtonsUpdatedGeneral() {

        radioButtonProductDesc.setOnAction((event) -> {
            listView.getItems().clear();
            listView.getItems().addAll(inventory.descendingSort(inventory.allProductsList()));

        });

        radioButtonProdAsc.setOnAction((event) -> {
            listView.getItems().clear();
            listView.getItems().addAll(inventory.ascedingSort(inventory.allProductsList()));
        });

        radioButtonPriceLow.setOnAction((event) -> {
            listView.getItems().clear();
            listView.getItems().addAll(inventory.lowPriceSort(inventory.allProductsList()));

        });

        radioButtonPriceHigh.setOnAction((event) -> {
            listView.getItems().clear();
            listView.getItems().addAll(inventory.highPriceSort(inventory.allProductsList()));

        });

    }
}







