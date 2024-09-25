package view;

import entities.ConsoleValues;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.*;


/**
 * Класс ConsoleInput предоставляет методы для получения информации из консоли
 */
@Slf4j
public class ConsoleInput {

	/**
	 * Получает имя файла и настройки режима загрузки от пользователя.
	 *
	 * Метод запрашивает у пользователя название файла для загрузки, режим погрузки,
	 * режим вывода данных и количество доступных грузовиков. Поддерживаются
	 * только файлы с расширениями .json и .txt. Вводимые параметры проверяются на
	 * корректность, и в случае ошибок выбрасываются исключения.
	 *
	 * @param files список доступных файлов для выбора.
	 * @return объект типа ConsoleValues, содержащий введенные данные о файле,
	 *         режиме загрузки, режиме вывода и количестве грузовиков.
	 * @throws RuntimeException если файл указан без расширения,
	 *         не введены один или несколько параметров,
	 *         или если количество грузовиков не является числом.
	 */
	public ConsoleValues getFileNameAndMode(List<File> files) throws RuntimeException {
		String fileName = "";
		String typeLoad = "";
		String mode = "";
		String typePrint = "";
		int truckCount = 0;

		Scanner scanner = new Scanner(System.in);

		System.out.println("Введите название файла для загрузки:\n");
		for (File file : files) {
			System.out.println(file.getName()+"\n");
		}

		fileName = scanner.nextLine();
		if(fileName.contains(".json")) {
			typeLoad = "json";
		} else if(fileName.contains(".txt")){
			typeLoad = "txt";
		} else {
			throw new RuntimeException("Файл указан без расширения");
		}

		System.out.println("Введите режим погрузки:\n"+"s - простая погрузка\nc - сложная погрузка");
		mode = scanner.nextLine();

		System.out.println("Введите режим вывода данных. Доступны:\n"+"console\njson\n");
		typePrint = scanner.nextLine();

		System.out.println("Введите кол-во доступных грузовиков:\n");
		String truckCountString = scanner.nextLine();
		scanner.close();

		if(mode.isEmpty() || typePrint.isEmpty() || truckCountString.isEmpty()) {
			throw new RuntimeException("Не указан один или несколько параметров");
		}

		try {
			truckCount = Integer.parseInt(truckCountString);
		} catch (NumberFormatException e) {
			throw new RuntimeException("Кол-во грузовиков должно быть числом");
		}

		log.info("Выбран источник данных '{}'",typeLoad);
		log.info("Выбран файл данных '{}'",fileName);
		log.info("Выбран тип сортировки '{}'",mode);
		log.info("Выбран способ вывода данных '{}'",typePrint);
		log.info("Кол-во доступных грузовиков '{}'",truckCount);
		return new ConsoleValues(fileName,typeLoad,mode,typePrint,truckCount);
	}

}
