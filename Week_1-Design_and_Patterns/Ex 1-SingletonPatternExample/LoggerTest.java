

public class LoggerTest {

	public static void main(String[] args) {

		Logger L1 = Logger.getInstance();
		Logger L2 = Logger.getInstance();
		
		if(L1 == L2) {
			System.out.println("Both instances are same....");
		}else {
			System.out.println("Both instances are different....");
		}

	}

}
