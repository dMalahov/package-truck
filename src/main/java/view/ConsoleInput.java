package view;

import entities.ConsoleValues;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.*;

@Slf4j
public class ConsoleInput {

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
