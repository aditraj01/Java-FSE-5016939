public class AdapterPatternTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PaymentProcessor phonepe = new PhonepeAdapter(new Phonepe());
		phonepe.processPayment(500);
		
		PaymentProcessor googlepe = new GooglepeAdapter(new Googlepe());
		googlepe.processPayment(1000);

	}

}
