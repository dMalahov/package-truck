package entities;

public class Package {

	protected int height;
	protected int widthTop;
	protected int widthBottom;
	protected  String[] pack;

	public Package(int height, int widthTop, int widthBottom, String[] pack) {
		this.height = height;
		this.widthTop = widthTop;
		this.widthBottom = widthBottom;
		this.pack = pack;
	}

	public int getHeight() {
		return height;
	}

	public int getWidthTop() {
		return widthTop;
	}

	public int getWidthBottom() {
		return widthBottom;
	}

	public String[] getPack() {
		return pack;
	}


}
