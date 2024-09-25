package entities;

import lombok.Getter;

/**
 * Класс, представляющий значения, введенные пользователем через консоль.
 */
@Getter
public class ConsoleValues {
	String fileName;
	String typeLoad;
	String mode;
	String typePrint;
	int truckCount;

	/**
	 * Конструктор для инициализации значений консоли.
	 *
	 * @param fileName  имя файла.
	 * @param typeLoad  тип загрузки.
	 * @param mode      режим погрузки.
	 * @param typePrint тип вывода данных.
	 * @param truckCount количество доступных грузовиков.
	 */
	public ConsoleValues(String fileName,String typeLoad,String mode,String typePrint,int truckCount) {
		this.fileName = fileName;
		this.typeLoad = typeLoad;
		this.mode = mode;
		this.typePrint = typePrint;
		this.truckCount = truckCount;
	}
}
