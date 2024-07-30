public class Customer {
	private int id;
	private String name;
	private static int idData = 1;
	
	public Customer(String name) {
		this.id = idData;
		this.name = name;
		idData++;
	}
	

	public String getCustomerName() {
		return name;
	}

}
