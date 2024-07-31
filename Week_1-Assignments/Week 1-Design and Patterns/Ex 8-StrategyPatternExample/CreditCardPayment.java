public class CreditCardPayment implements PaymentStrategy {
    private String cardHolderName;

    public CreditCardPayment(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }


	public void pay(double amount) {
		System.out.println("Paying " + amount + " using Credit Card. Holder name: " + cardHolderName);
	}

}
