package AthleticsApp;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.net.URL;
import java.util.ResourceBundle;

public class AthleticsController implements Initializable {

    //Declaring JavaFx variables and Instantiating the inventory
    @FXML
    private ComboBox sportComboBox;
    @FXML
    private ImageView imageView;
    @FXML
    private ListView athleticsListView;
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
        sportComboBox.setPromptText("Select just one sport");

        //Method to choose the appropriate list of each category in the ComboBox
        comboBoxUpdated();

        //Radio button updated if the comboBox is not selected
        radioButtonsUpdatedGeneral();

        //Populating the ComboBox with Category names
        sportComboBox.getItems().addAll(inventory.getAllCategories());

        //Setting Category Label to Not Applicable, since there was no selection
        totalCategoryLabel.setText("N/A");

        //Setting opening image
        imageView.setImage(new Image("AthleticsApp/images/atletica.png"));


        //Populating the List View
        athleticsListView.getItems().addAll(inventory.allProductsList());
        athleticsListView.getSelectionModel().select(0);

        //Setting the total Inventory Label to the calculation formula created below
        totalInventoryLabel.setText(Double.toString(totalInventoryCalculation()));

        //Adding the Listener in the athleticsListView
        athleticsListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<AthleticsProduct>() {
            @Override
            public void changed(ObservableValue<? extends AthleticsProduct> observable, AthleticsProduct oldValue, AthleticsProduct newValue) {
                if (newValue != null)
                    //Changes the picture
                    imageView.setImage(newValue.getImageFile());
            }
        });

        //Selling Button on action to decrease the units and update the category and inventory label
        sellingButton.setOnAction(event -> {
            inventory.sellingUnits((AthleticsProduct) athleticsListView.getSelectionModel().getSelectedItem());
            athleticsListView.refresh();
            totalInventoryLabel.setText(Double.toString(totalInventoryCalculation()));
            totalCategoryLabel.setText(Double.toString(categoryCalculation()));
        });
    }

    /**
     * Method to calculate the total Inventory using streams
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
     * Method to calculate the total for each category using streams
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
     * Method to update the athleticsListView and the Total Category Label
     */
    public void comboBoxUpdated() {
        sportComboBox.setOnAction((event) -> {
            sportComboBox.getSelectionModel().getSelectedItem();
            athleticsListView.getItems().clear();
            athleticsListView.getItems().addAll(inventory.descendingSort(inventory.productsByCategory(sportComboBox.getSelectionModel().getSelectedItem().toString())));
            athleticsListView.getSelectionModel().select(1);
            totalCategoryLabel.setText(Double.toString(categoryCalculation()));
            radioButtonsUpdated();
        });
    }

    /**
     * Method to update the radioButton if the comboBox has been selected
     */
    public void radioButtonsUpdated() {

        radioButtonProductDesc.setOnAction((event) -> {
            athleticsListView.getItems().clear();
            athleticsListView.getItems().addAll(inventory.descendingSort(inventory.productsByCategory(sportComboBox.getSelectionModel().getSelectedItem().toString())));

        });
        radioButtonProdAsc.setOnAction((event) -> {
            athleticsListView.getItems().clear();
            athleticsListView.getItems().addAll(inventory.ascendingSort(inventory.productsByCategory(sportComboBox.getSelectionModel().getSelectedItem().toString())));

        });
        radioButtonPriceLow.setOnAction((event) -> {
            athleticsListView.getItems().clear();
            athleticsListView.getItems().addAll(inventory.lowPriceSort(inventory.productsByCategory(sportComboBox.getSelectionModel().getSelectedItem().toString())));
        });
        radioButtonPriceHigh.setOnAction((event) -> {
            athleticsListView.getItems().clear();
            athleticsListView.getItems().addAll(inventory.highPriceSort(inventory.productsByCategory(sportComboBox.getSelectionModel().getSelectedItem().toString())));
        });
    }

    /**
     * Method to sort the list if the ComboBox is not selected yet
     */
    public void radioButtonsUpdatedGeneral() {

        radioButtonProductDesc.setOnAction((event) -> {
            athleticsListView.getItems().clear();
            athleticsListView.getItems().addAll(inventory.descendingSort(inventory.allProductsList()));

        });

        radioButtonProdAsc.setOnAction((event) -> {
            athleticsListView.getItems().clear();
            athleticsListView.getItems().addAll(inventory.ascendingSort(inventory.allProductsList()));
        });

        radioButtonPriceLow.setOnAction((event) -> {
            athleticsListView.getItems().clear();
            athleticsListView.getItems().addAll(inventory.lowPriceSort(inventory.allProductsList()));

        });

        radioButtonPriceHigh.setOnAction((event) -> {
            athleticsListView.getItems().clear();
            athleticsListView.getItems().addAll(inventory.highPriceSort(inventory.allProductsList()));

        });
    }
}







