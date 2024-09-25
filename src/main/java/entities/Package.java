package entities;

import lombok.Getter;

@Getter
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


}
