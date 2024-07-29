public class ComputerTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Computer computer = new ComputerBuilder().setCPU("Ryzen 5").setRAM(8).setStorage(256).build();
		System.out.println(computer);

	}

}
