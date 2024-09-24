package enums;

public enum Directory {
	MAIN_DIR(System.getProperty("user.dir") + "\\src\\main\\resources\\"),

	TEST_DIR(System.getProperty("user.dir") + "\\src\\test\\resources\\");

	public String getPath() {
		return path;
	}

	private final String path;

	Directory(String path){
		this.path=path;
	}


}

