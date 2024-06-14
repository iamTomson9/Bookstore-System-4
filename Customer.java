class Customer {
    // Attributes of a customer
    private String name;
    private String email;

    // Constructor to initialize a customer with provided details
    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // Getters for retrieving customer details
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    // Setters for updating customer details
    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}