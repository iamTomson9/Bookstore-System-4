class Inventory {
    // ObservableList to store book objects
    private ObservableList<Book> bookList;

    // Constructor to initialize the list of books
    public Inventory() {
        this.bookList = FXCollections.observableArrayList();
    }

    // Method to add a book to the list
    public void addBook(Book book) {
        bookList.add(book);
    }

    // Method to retrieve the list of books
    public ObservableList<Book> getBookList() {
        return bookList;
    }

    // Method to search for books based on criteria and query
    public List<Book> searchBook(String criteria, String query) {
        // List to store search results
        List<Book> results = new ArrayList<>();
        // Iterate through each book in the list
        for (Book book : bookList) {
            // Perform search based on criteria
            switch (criteria) {
                case "Title":
                    if (book.getTitle().toLowerCase().contains(query.toLowerCase())) {
                        results.add(book);
                    }
                    break;
                case "Author":
                    if (book.getAuthor().toLowerCase().contains(query.toLowerCase())) {
                        results.add(book);
                    }
                    break;
                case "Genre":
                    if (book.getGenre().toLowerCase().contains(query.toLowerCase())) {
                        results.add(book);
                    }
                    break;
                default:
                    break;
            }
        }
        return results; // Return the search results
    }
}
