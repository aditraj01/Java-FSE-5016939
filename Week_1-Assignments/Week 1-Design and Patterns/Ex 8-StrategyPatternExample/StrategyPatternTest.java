public class StrategyPatternTest {

	public static void main(String[] args) {

		PaymentContext context = new PaymentContext();

        PaymentStrategy creditCardPayment = new CreditCardPayment("Aditya Raj");
        context.setPaymentStrategy(creditCardPayment);
        context.pay(250.0);

        System.out.println();
        PaymentStrategy payPalPayment = new PayPalPayment("abc@example.com");
        context.setPaymentStrategy(payPalPayment);
        context.pay(300.0);
	}

}