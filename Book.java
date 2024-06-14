class Book {
    // Attributes of a book
    private String isbn;
    private String title;
    private String author;
    private String genre;
    private double price;
    private int quantity;

    // Constructor to initialize a book with provided details
    public Book(String isbn, String title, String author, String genre, double price, int quantity) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.price = price;
        this.quantity = quantity;
    }

    // Getters for retrieving book details
    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    // Setters for updating book details
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Method to convert Book object to a string representation
    @Override
    public String toString() {
        return "ISBN: " + isbn +
                ", Title: " + title +
                ", Author: " + author +
                ", Genre: " + genre +
                ", Price: $" + price +
                ", Quantity: " + quantity;
    }
}