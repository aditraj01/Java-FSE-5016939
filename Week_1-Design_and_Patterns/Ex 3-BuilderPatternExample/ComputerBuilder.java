public class ComputerBuilder {
	
	String CPU;
	int RAM;
	int Storage;
	
	public ComputerBuilder setCPU(String CPU) {
		this.CPU = CPU;
		return this;
	}
	
	public ComputerBuilder setRAM(int RAM) {
		this.RAM = RAM;
		return this;
	}
	
	public ComputerBuilder setStorage(int Storage) {
		this.Storage = Storage;
		return this;
	}
	
	public Computer build() {
		return new Computer(this);
	}

}
