import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Product[] products = {
            new Product(1, "Laptop", "Electronics"),
            new Product(2, "Smartphone", "Electronics"),
            new Product(3, "Book", "Literature"),
            new Product(4, "Tablet", "Electronics"),
            new Product(5, "Shirt", "Clothing")
        };

        // Linear Search by ID
        Product result = LinearSearch.searchById(products, 4);
        System.out.println("Linear Search by ID Result: " + result);

        // Linear Search by Name
        result = LinearSearch.searchByName(products, "Tablet");
        System.out.println("Linear Search by Name Result: " + result);

        // Linear Search by Category
        ArrayList<Product> categoryResults = LinearSearch.searchByCategory(products, "Electronics");
        System.out.println("Linear Search by Category Results:");
        for (Product p : categoryResults) {
            System.out.println(p);
        }

        // Binary Search by ID
        result = BinarySearch.searchById(products, 4);
        System.out.println("Binary Search by ID Result: " + result);

        // Binary Search by Name
        result = BinarySearch.searchByName(products, "Tablet");
        System.out.println("Binary Search by Name Result: " + result);
    }
}