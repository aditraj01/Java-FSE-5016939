import java.util.ArrayList;
import java.util.List;

public class StockMarket implements Stock {
	private List<Observer> observers;
	private double stockPrice;
	
	public StockMarket() {
		observers = new ArrayList<>();
	}

	public void registerObserver(Observer observer) {
		observers.add(observer);
	}

	public void deregisterObserver(Observer observer) {
		observers.remove(observer);
	}

	public void notifyObservers() {
		for(Observer observer : observers) {
			observer.update(stockPrice);
		}
	}
	
	public void setStockPrice(double stockPrice) {
		this.stockPrice = stockPrice;
		notifyObservers();
	}

}
