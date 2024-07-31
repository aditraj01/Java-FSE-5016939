import java.util.ArrayList;

public class LinearSearch {
    public static Product searchById(Product[] products, int productId) {
        for (Product product : products) {
            if (product.getProductId() == productId) {
                return product;
            }
        }
        return null;
    }

    public static Product searchByName(Product[] products, String productName) {
        for (Product product : products) {
            if (product.getProductName().equalsIgnoreCase(productName)) {
                return product;
            }
        }
        return null;
    }

    public static ArrayList<Product> searchByCategory(Product[] products, String category) {
        ArrayList<Product> arr = new ArrayList<>();
        for (Product product : products) {
            if (product.getCategory().equalsIgnoreCase(category)) {
                arr.add(product);
            }
        }
        return arr;
    }
}