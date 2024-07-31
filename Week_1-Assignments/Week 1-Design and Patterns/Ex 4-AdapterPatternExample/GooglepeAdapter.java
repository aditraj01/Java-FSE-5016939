public class GooglepeAdapter implements PaymentProcessor {
	
	private Googlepe googlepe;
	
	public GooglepeAdapter(Googlepe gp) {
		this.googlepe = gp;
	}

	@Override
	public void processPayment(double amount) {
		// TODO Auto-generated method stub
		googlepe.sendMoney(amount);
	}

}