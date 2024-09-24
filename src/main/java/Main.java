import entities.CompletePack;
import entities.Package;
import entities.Truck;
import enums.Directory;
import lombok.extern.slf4j.Slf4j;
import services.FileService;
import services.JsonService;
import services.PackageService;
import services.TruckService;
import view.ConsoleInput;

import java.util.*;

@Slf4j
public class Main {

	public static void main(String[] args) {
		log.info("Стартуем приложение...");
		Main.start();
	}

	static void start() {
		List<Truck> trucks;
		List<Package> readyPackages;
		ConsoleInput console = new ConsoleInput();
		FileService fileService = new FileService();
		JsonService jsonService = new JsonService();
		PackageService packageService = new PackageService();
		TruckService truckService = new TruckService();

		String[] settingsFromConsole = console.getFileNameAndMode();
		if(settingsFromConsole[1].toUpperCase(Locale.ROOT).equals("JSON")) {
			String json = fileService.readFileJson(Directory.MAIN_DIR.getPath(),settingsFromConsole[0]);
			trucks = jsonService.getTruckToJson(json, Integer.parseInt(settingsFromConsole[4]));
		} else {
			String[] packageFirstList = fileService.readFileTxt(Directory.MAIN_DIR.getPath(),settingsFromConsole[0]);
			readyPackages = packageService.createNewPackageForTruck(packageFirstList,settingsFromConsole[2],settingsFromConsole[3]);
			trucks = truckService.createTruckWithPackage(readyPackages,settingsFromConsole[2],Integer.parseInt(settingsFromConsole[4]));
		}
		if(settingsFromConsole[3].toUpperCase(Locale.ROOT).equals("JSON")) {
			String resultJson = jsonService.createJsonForTruck(trucks);
			fileService.writeResultJson(resultJson);
		} else {
			console.printTruckWithSpace(trucks);
		}
	}



}