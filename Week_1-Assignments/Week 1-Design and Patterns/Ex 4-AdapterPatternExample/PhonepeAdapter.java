public class PhonepeAdapter implements PaymentProcessor {
	private Phonepe phonepe;
	
	public PhonepeAdapter(Phonepe ph) {
		this.phonepe = ph;
	}

	
	public void processPayment(double amount) {
		phonepe.makePayment(amount);
	}
}
