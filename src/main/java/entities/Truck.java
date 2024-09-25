package entities;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Truck {

	protected List<Package> packages = new ArrayList();
	public Truck() {}

}
