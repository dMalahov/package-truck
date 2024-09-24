package view;

import entities.Package;
import entities.Truck;
import lombok.extern.slf4j.Slf4j;
import utils.Space;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

@Slf4j
public class ConsoleInput {

	Space space = new Space();

	/*Получение настроек для запуска программы*/
	public String[] getFileNameAndMode() throws RuntimeException {
		String fileName = "";
		String typeLoad = "";
		String mode = "";
		Scanner scanner = new Scanner(System.in);
		System.out.println("Введите тип файла для загрузки:\n"+"txt - загрузки из txt\njson - загрузки из json\n");
		typeLoad = scanner.nextLine();
		if(typeLoad.isEmpty()) throw new RuntimeException("Не указан режим загрузки файла");
		if(typeLoad.toUpperCase(Locale.ROOT).equals("JSON")) {
			System.out.println("Введите наименование файла:\n"+"Доступные файлы:\ntest1\ntest2\n");
			fileName = scanner.nextLine();
			if(fileName.isEmpty()) throw new RuntimeException("Не указано имя файла");
			fileName = fileName+".json";
		} else {
			System.out.println("Введите наименование файла:\n"+"Доступные файлы:\ntest1\ntest2\n");
			fileName = scanner.nextLine();
			if(fileName.isEmpty()) throw new RuntimeException("Не указано имя файла");
			fileName = fileName+".txt";
			System.out.println("Введите режим погрузки:\n"+"s - простая погрузка\nc - сложная погрузка");
			mode = scanner.nextLine();
			if(mode.isEmpty()) throw new RuntimeException("Не выбран режим погрузки");
		}
		System.out.println("Введите режим вывода данных. Доступны:\n"+"console\njson\n");
		String typePrint = scanner.nextLine();
		if(typePrint.isEmpty()) throw new RuntimeException("Не выбран режим вывода данных");
		System.out.println("Введите кол-во доступных грузовиков:\n");
		String truckCount = scanner.nextLine();
		if(truckCount.isEmpty()) throw new RuntimeException("Не указано кол-во грузовиков для погрузки");
		scanner.close();
		log.info("Выбран источник данных '{}'",typeLoad);
		log.info("Выбран файл данных '{}'",fileName);
		log.info("Выбран тип сортировки '{}'",mode);
		log.info("Выбран способ вывода данных '{}'",typePrint);
		log.info("Кол-во доступных грузовиков '{}'",truckCount);
		return new String[]{fileName,typeLoad,mode,typePrint,truckCount};
	}

	/*Печать загруженных грузовиков с пакетами*/
	public void printTruckWithSpace(List<Truck> truckArrayList) {
		if(truckArrayList != null) {
			log.info("Печать в консоль...");
			for (Truck truckPrint : truckArrayList) {
				StringBuilder truckString = new StringBuilder();
				for (int i = 0; i < space.getFreeSpaceH(truckPrint.getPackages()); i++) {
					truckString.append("+      +\n");
				}
				for (Package aPackage : truckPrint.getPackages()) {
					for (String packString : aPackage.getPack()) {
						String numberList[] = packString.split("\n");
						for (String number : numberList) {
							truckString.append("+");
							truckString.append(padRight(number, 6));
							truckString.append("+\n");
						}
					}
				}
				truckString.append("++++++++\n\r");
				System.out.println(truckString);
			}
		} else {
			log.error("Что то пошло не так - Список грузовиков пуст");
		}
	}

	private static String padRight(String s, int n) {
		return String.format("%-" + n + "s", s);
	}

}
