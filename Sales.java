import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

// Class for managing sales operations
class Sales {
    // Reference to the inventory
    private Inventory inventory;

    // Constructor to initialize with inventory reference
    public Sales(Inventory inventory) {
        this.inventory = inventory;
    }
    
    // Method to process a sale
    public void processSale(String isbn, int quantity) {
        // Iterate through the book list in inventory
        for (Book book : inventory.getBookList()) {
            // Check if the book with matching ISBN is found
            if (book.getIsbn().equals(isbn)) {
                // If there are sufficient quantities in stock
                if (book.getQuantity() >= quantity) {
                    // Deduct sold quantity from stock
                    book.setQuantity(book.getQuantity() - quantity);
                    // Update sales records or generate report
                    System.out.println("Sale processed successfully.");
                    return;
                } else {
                    // If there are insufficient quantities in stock
                    System.out.println("Insufficient quantity for sale.");
                    // Handle insufficient quantity
                    return;
                }
            }
        }
        // If the book is not found in inventory
        System.out.println("Book not found in inventory.");
        // Handle book not found
    }

    // Method to calculate total revenue
    public double calculateRevenue() {
        double revenue = 0;
        // Iterate through the book list in inventory
        for (Book book : inventory.getBookList()) {
            // Calculate revenue for each sold book
            revenue += (book.getPrice() * (book.getInitialQuantity() - book.getQuantity()));
        }
        return revenue; // Return total revenue
    }

    // Method to get top selling books based on sold quantity
    public List<Book> getTopSellingBooks() {
        return inventory.getBookList()
                .stream()
                .sorted(Comparator.comparingInt(book -> (book.getInitialQuantity() - book.getQuantity())))
                .collect(Collectors.toList()); // Sort and return top selling books
    }
}
