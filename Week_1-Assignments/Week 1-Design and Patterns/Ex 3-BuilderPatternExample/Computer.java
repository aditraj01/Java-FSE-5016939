public class Computer {
	
	private String CPU;
	private int RAM;
	private int Storage;
	
	public Computer(ComputerBuilder builder) {
		this.CPU = builder.CPU;
		this.RAM = builder.RAM;
		this.Storage = builder.Storage;
	}
	
	public String getCPU() {
		return CPU;
	}
	
	public int getRAM() {
		return RAM;
	}
	
	public int getStorage() {
		return Storage;
	}
	
	public String toString() {
		return "Computer CPU: " + CPU + ", RAM : " + RAM + "GB, " + "Storage : " + Storage + "GB";
	}

}
