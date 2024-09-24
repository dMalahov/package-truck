package entities;

import dto.TruckPackageModel;

import java.util.ArrayList;
import java.util.List;

public class Truck {

	protected List<Package> packages = new ArrayList();
	public Truck() {}

	public List<Package> getPackages() {
		return packages;
	}

}
