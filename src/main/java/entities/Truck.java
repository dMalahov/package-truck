package entities;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс, представляющий грузовик, который содержит список пакетов.
 */
@Getter
public class Truck {

	protected List<Package> packages = new ArrayList();

	/**
	 * Конструктор для создания грузовика.
	 */
	public Truck() {}

}
