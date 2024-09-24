package utils;

import entities.Package;

import java.util.List;

public class Space {

	/*Проверка по высоте*/
	public int getFreeSpaceH(List<Package> packages) {
		int sum = 0;
		for (Package aPackage : packages) {
			int i = aPackage.getHeight();
			sum += i;
		}
		return 6-sum;
	}

	/*Проверка на ширину*/
	public int getFreeSpaceW(List<Package> packages) {
		int sum = 0;
		for (Package aPackage : packages) {
			int i = aPackage.getWidthBottom();
			sum += i;
		}
		return 6-sum;
	}

}
