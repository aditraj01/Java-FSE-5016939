public class IMSTest {

	public static void main(String[] args) {
	InventoryManager manager = new InventoryManager();
        Product product1 = new Product(1, "Laptop", 10, 50999.99);
        Product product2 = new Product(2, "Smartphone", 20, 15599.99);

        manager.addProduct(product1);
        manager.addProduct(product2);

        manager.displayInventory();

        product1.setPrice(45999.99);
        manager.updateProduct(product1);

        System.out.println();
        manager.displayInventory();

        manager.deleteProduct(2);
        System.out.println();
        manager.displayInventory();;

	}

}
