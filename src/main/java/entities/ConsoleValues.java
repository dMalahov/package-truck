package entities;

import lombok.Getter;

@Getter
public class ConsoleValues {
	String fileName;
	String typeLoad;
	String mode;
	String typePrint;
	int truckCount;

	public ConsoleValues(String fileName,String typeLoad,String mode,String typePrint,int truckCount) {
		this.fileName = fileName;
		this.typeLoad = typeLoad;
		this.mode = mode;
		this.typePrint = typePrint;
		this.truckCount = truckCount;
	}
}
