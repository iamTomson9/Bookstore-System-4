// Import necessary JavaFX classes
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

// Main class representing the BookwormHaven application, extending JavaFX Application class
public class BookwormHaven extends Application {
    // Instances for managing inventory, sales, and customer management
    private Inventory inventory;
    private Sales sales;
    private CustomerManagement customerManagement;

    // GUI components
    private TableView<Book> table;
    private TextField isbnField, titleField, authorField, genreField, priceField, quantityField;
    private TextField searchField;
    private ComboBox<String> searchCriteriaComboBox;
    private TextArea resultTextArea;

    // Main method to launch the application
    public static void main(String[] args) {
        launch(args);
    }

    // Method for initializing the primary stage and GUI components
    @Override
    public void start(Stage primaryStage) {
        // Initialize inventory, sales, and customer management
        inventory = new Inventory();
        sales = new Sales(inventory);
        customerManagement = new CustomerManagement();

        // Set title for the primary stage
        primaryStage.setTitle("Bookworm Haven");

        // Load background image for the application
        BackgroundImage backgroundImage = new BackgroundImage(
                new Image("book_background.jpg", 800, 600, false, true),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);

        // Create main layout pane using VBox
        VBox layout = new VBox();
        layout.setBackground(new Background(backgroundImage));
        layout.setSpacing(10);
        layout.setPadding(new Insets(10));

        // Set styles for GUI components
        layout.setStyle("-fx-background-color: #6A5ACD; -fx-text-fill: #FFFFFF;");

        // Menu bar setup
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");
        MenuItem exitMenuItem = new MenuItem("Exit");
        exitMenuItem.setOnAction(e -> primaryStage.close());
        fileMenu.getItems().add(exitMenuItem);
        menuBar.getMenus().add(fileMenu);

        // Add Book Tab
        GridPane addBookPane = new GridPane();
        addBookPane.setPadding(new Insets(10));
        addBookPane.setVgap(5);
        addBookPane.setHgap(5);

        // Initialize text fields for adding a book
        isbnField = new TextField();
        isbnField.setPromptText("ISBN");
        titleField = new TextField();
        titleField.setPromptText("Title");
        authorField = new TextField();
        authorField.setPromptText("Author");
        genreField = new TextField();
        genreField.setPromptText("Genre");
        priceField = new TextField();
        priceField.setPromptText("Price");
        quantityField = new TextField();
        quantityField.setPromptText("Quantity");
        Button addButton = new Button("Add Book");
        addButton.setOnAction(e -> addBook());

        // Add GUI components to the add book pane
        addBookPane.add(new Label("ISBN:"), 0, 0);
        addBookPane.add(isbnField, 1, 0);
        addBookPane.add(new Label("Title:"), 0, 1);
        addBookPane.add(titleField, 1, 1);
        addBookPane.add(new Label("Author:"), 0, 2);
        addBookPane.add(authorField, 1, 2);
        addBookPane.add(new Label("Genre:"), 0, 3);
        addBookPane.add(genreField, 1, 3);
        addBookPane.add(new Label("Price:"), 0, 4);
        addBookPane.add(priceField, 1, 4);
        addBookPane.add(new Label("Quantity:"), 0, 5);
        addBookPane.add(quantityField, 1, 5);
        addBookPane.add(addButton, 1, 6);

        // Manage Inventory Tab
        VBox manageInventoryPane = new VBox(10);
        manageInventoryPane.setPadding(new Insets(10));
        // Initialize table view for displaying inventory
        table = new TableView<>();
        // Initialize table columns
        TableColumn<Book, String> isbnColumn = new TableColumn<>("ISBN");
        isbnColumn.setCellValueFactory(cellData -> cellData.getValue().isbnProperty());
        TableColumn<Book, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        TableColumn<Book, String> authorColumn = new TableColumn<>("Author");
        authorColumn.setCellValueFactory(cellData -> cellData.getValue().authorProperty());
        TableColumn<Book, String> genreColumn = new TableColumn<>("Genre");
        genreColumn.setCellValueFactory(cellData -> cellData.getValue().genreProperty());
        TableColumn<Book, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
        TableColumn<Book, Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setCellValueFactory(cellData -> cellData.getValue().quantityProperty().asObject());
        // Add columns to the table
        table.getColumns().addAll(isbnColumn, titleColumn, authorColumn, genreColumn, priceColumn, quantityColumn);
        // Set table data
        table.setItems(inventory.getBookList());

        // Search functionality setup
        HBox searchBox = new HBox(10);
        searchField = new TextField();
        searchField.setPromptText("Search...");
        searchCriteriaComboBox = new ComboBox<>();
        searchCriteriaComboBox.getItems().addAll("Title", "Author", "Genre");
        searchCriteriaComboBox.setValue("Title");
        Button searchButton = new Button("Search");
        searchButton.setOnAction(e -> searchBook());
        searchBox.getChildren().addAll(searchField, searchCriteriaComboBox, searchButton);

        // Text area for displaying search results
        resultTextArea = new TextArea();
        resultTextArea.setEditable(false);
        resultTextArea.setPrefHeight(200);

        // Add GUI components to manage inventory pane
        manageInventoryPane.getChildren().addAll(table, searchBox, resultTextArea);

        // Revenue Calculation Tab
        VBox revenuePane = new VBox(10);
        revenuePane.setPadding(new Insets(10));
        Button calculateRevenueButton = new Button("Calculate Revenue");
        calculateRevenueButton.setOnAction(e -> calculateRevenue());
        revenuePane.getChildren().addAll(calculateRevenueButton);

        // Add all components to the main layout
        layout.getChildren().addAll(menuBar, addBookPane, manageInventoryPane, revenuePane);

        // Create scene and set it to the primary stage
        Scene scene = new Scene(layout, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to add a book to the inventory
    private void addBook() {
        // Retrieve input values
        String isbn = isbnField.getText();
        String title = titleField.getText();
        String author = authorField.getText();
        String genre = genreField.getText();
        double price = Double.parseDouble(priceField.getText());
        int quantity = Integer.parseInt(quantityField.getText());
        // Create a new book instance
        Book book = new Book(isbn, title, author, genre, price, quantity);
        // Add book to the inventory


        inventory.addBook(book);
        // Update table view
        table.setItems(inventory.getBookList());
        // Clear input fields
        clearFields();
    }

    // Method to search for books in the inventory
    private void searchBook() {
        // Retrieve search query and criteria
        String query = searchField.getText();
        String criteria = searchCriteriaComboBox.getValue();
        // Search for books
        List<Book> results = inventory.searchBook(criteria, query);
        // Clear previous results
        resultTextArea.clear();
        // Display search results
        for (Book book : results) {
            resultTextArea.appendText(book.toString() + "\n");
        }
    }

    // Method to process a sale
    private void processSale(String isbn, int quantity) {
        // Process sale
        sales.processSale(isbn, quantity);
        // Update table view
        table.setItems(inventory.getBookList());
    }

    // Method to calculate total revenue
    private void calculateRevenue() {
        // Calculate revenue
        double revenue = sales.calculateRevenue();
        // Display total revenue
        resultTextArea.clear();
        resultTextArea.setText("Total Revenue: $" + revenue);
    }

    // Method to view top selling books
    private void viewTopSellingBooks() {
        // Get top selling books
        List<Book> topSellingBooks = sales.getTopSellingBooks();
        // Clear previous results
        resultTextArea.clear();
        // Display top selling books
        for (Book book : topSellingBooks) {
            resultTextArea.appendText(book.toString() + "\n");
        }
    }

    // Method to clear input fields
    private void clearFields() {
        isbnField.clear();
        titleField.clear();
        authorField.clear();
        genreField.clear();
        priceField.clear();
        quantityField.clear();
    }
}
```