public class Main {
	
	public static void bubbleSort(Order[] orders) {
        int n = orders.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (orders[j].getTotalPrice() > orders[j + 1].getTotalPrice()) {
                    // Swap orders[j] and orders[j + 1]
                    Order temp = orders[j];
                    orders[j] = orders[j + 1];
                    orders[j + 1] = temp;
                }
            }
        }
    }
	
	public static void quickSort(Order[] orders) {
        quickSort(orders, 0, orders.length - 1);
    }

    private static void quickSort(Order[] orders, int low, int high) {
        if (low < high) {
            int pi = partition(orders, low, high);
            quickSort(orders, low, pi - 1);
            quickSort(orders, pi + 1, high);
        }
    }

    private static int partition(Order[] orders, int low, int high) {
        double pivot = orders[high].getTotalPrice();
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (orders[j].getTotalPrice() <= pivot) {
                i++;
                Order temp = orders[i];
                orders[i] = orders[j];
                orders[j] = temp;
            }
        }
        Order temp = orders[i + 1];
        orders[i + 1] = orders[high];
        orders[high] = temp;
        return i + 1;
    }
	
	public static void main(String[] args) {
        Order[] orders = {
            new Order(1, "Alice", 250.75),
            new Order(2, "Bob", 120.50),
            new Order(3, "Charlie", 450.00),
            new Order(4, "Diana", 300.25),
            new Order(5, "Eve", 99.99)
        };

        System.out.println("Original Orders:");
        for (Order order : orders) {
            System.out.println(order);
        }

        // Bubble Sort
        bubbleSort(orders);
        System.out.println("\nOrders Sorted by Bubble Sort:");
        for (Order order : orders) {
            System.out.println(order);
        }

        // Shuffle orders again
        Order[] shuffledOrders = {
            new Order(1, "Alice", 250.75),
            new Order(2, "Bob", 120.50),
            new Order(3, "Charlie", 450.00),
            new Order(4, "Diana", 300.25),
            new Order(5, "Eve", 99.99)
        };

        // Quick Sort
        quickSort(shuffledOrders);
        System.out.println("\nOrders Sorted by Quick Sort:");
        for (Order order : shuffledOrders) {
            System.out.println(order);
        }
    }

}