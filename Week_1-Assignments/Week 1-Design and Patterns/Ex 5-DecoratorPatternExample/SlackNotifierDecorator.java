public class SlackNotifierDecorator extends NotifierDecorator {

	public SlackNotifierDecorator(Notifier notifier) {
		super(notifier);
	}

	
	public void send(String msg) {
		super.send(msg);
		sendSlack(msg);
	}


	private void sendSlack(String msg) {
		System.out.println("Slack: " + msg);
	}

}