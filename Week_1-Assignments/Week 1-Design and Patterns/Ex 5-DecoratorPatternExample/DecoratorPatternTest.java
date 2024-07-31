public class DecoratorPatternTest {

	public static void main(String[] args) {
		Notifier emailNotifier = new EmailNotifier();
        Notifier smsAndEmailNotifier = new SMSNotifierDecorator(emailNotifier);
        Notifier allNotifier = new SlackNotifierDecorator(smsAndEmailNotifier);
        Notifier slackandemailNotifier = new SlackNotifierDecorator(emailNotifier);

        System.out.println("Sending notification with email only:");
        emailNotifier.send("Hello, this is a test message!");

        System.out.println("\nSending notification with email and SMS:");
        smsAndEmailNotifier.send("Hello, this is a test message!");

        System.out.println("\nSending notification with email, SMS, and Slack:");
        allNotifier.send("Hello, this is a test message!");
        
        System.out.println("\nSending notification with email and Slack:");
        slackandemailNotifier.send("Hello, this is a test message!");
	}

}
