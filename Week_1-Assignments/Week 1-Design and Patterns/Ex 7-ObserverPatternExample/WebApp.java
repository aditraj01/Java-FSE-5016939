public class WebApp implements Observer {
	private String name;

	public WebApp(String name) {
        this.name = name;
    }

	public void update(double stockPrice) {
		System.out.println("Web App User " + name + " received stock price update: " + stockPrice);
	}

}
