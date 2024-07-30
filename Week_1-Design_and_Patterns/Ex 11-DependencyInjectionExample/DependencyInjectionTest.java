public class DependencyInjectionTest {
	
	public static void main(String[] args) {
		CustomerRepository customerRepository = new CustomerRepositoryImpl();

        CustomerService customerService = new CustomerService(customerRepository);
        
        Customer customer = customerService.getCustomerById("1");
        if (customer != null) {
            System.out.println("Customer found: " + customer.getCustomerName());
        } else {
            System.out.println("Customer not found.");
        }
        
        System.out.println();

        customer = customerService.getCustomerById("5");
        if (customer != null) {
            System.out.println("Customer found: " + customer.getCustomerName());
        } else {
            System.out.println("Customer not found.");
        }
    }
}
