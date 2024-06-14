class CustomerManagement {
    // List to store customer objects
    private List<Customer> customers;

    // Constructor to initialize the list of customers
    public CustomerManagement() {
        this.customers = new ArrayList<>();
    }

    // Method to add a customer to the list
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    // Method to retrieve the list of customers
    public List<Customer> getCustomers() {
        return customers;
    }
}