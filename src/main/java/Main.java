import entities.ConsoleValues;
import entities.Package;
import entities.Truck;
import lombok.extern.slf4j.Slf4j;
import services.FileService;
import services.PackageService;
import services.TruckService;
import view.ConsoleInput;

import java.util.*;

@Slf4j
public class Main {

	public static void main(String[] args) {
		Main.start();
	}

	static void start() {
		List<Truck> trucks;
		List<Package> packages;
		ConsoleInput console = new ConsoleInput();
		FileService fileService = new FileService();

		ConsoleValues inputValues = console.getFileNameAndMode(fileService.getFiles(fileService.MAIN_DIR));

		if(inputValues.getTypeLoad().toUpperCase(Locale.ROOT).equals("JSON")) {
			trucks = createTruckJson(inputValues);
		} else {
			packages = sortPackageTxt(inputValues);
			trucks = createTruckTxt(inputValues, packages);
		}
		doResult(inputValues, trucks);
	}

	private static List<Package> sortPackageTxt(ConsoleValues inputValues) {
		List<Package> readyPackages;
		FileService fileService = new FileService();
		PackageService packageService = new PackageService();
		TruckService truckService = new TruckService();
		String[] txtFile = fileService.readFileTxt(fileService.MAIN_DIR,inputValues.getFileName());
		if(inputValues.getMode().toUpperCase(Locale.ROOT).contains("S") || inputValues.getTypeLoad().toUpperCase(Locale.ROOT).equals("JSON")) {
			readyPackages = packageService.sortSimpleOrders(txtFile);
		} else {
			List<Package> firstPackages =packageService.sortSimpleOrders(txtFile);
			List<Truck> mixTrucks = truckService.createComplexTruckForWidth(firstPackages);
			readyPackages = packageService.sortComplexOrders(mixTrucks);
		}
		return readyPackages;
	}

	private static List<Truck> createTruckTxt(ConsoleValues inputValues, List<Package> packages) {
		List<Truck> readyTrucks;
		TruckService truckService = new TruckService();
		if(inputValues.getMode().toUpperCase(Locale.ROOT).contains("S")) {
			readyTrucks = truckService.createSimpleTruck(packages,inputValues.getTruckCount());
		} else {
			readyTrucks = truckService.createComplexTruckForHeight(packages,inputValues.getTruckCount());
		}
		return readyTrucks;
	}

	private static List<Truck> createTruckJson(ConsoleValues inputValues) {
		FileService fileService = new FileService();
		TruckService truckService = new TruckService();
		String fileJson = fileService.readFileJson(fileService.MAIN_DIR,inputValues.getFileName());
		return truckService.createTruckForJson(fileJson, inputValues.getTruckCount());
	}

	private static void doResult(ConsoleValues inputValues, List<Truck> trucks) {
		FileService fileService = new FileService();
		TruckService truckService = new TruckService();
		if(inputValues.getTypePrint().toUpperCase(Locale.ROOT).equals("JSON")) {
			String resultJson = truckService.getJsonForTruck(trucks);
			fileService.writeResultJson(resultJson);
		} else {
			truckService.printTruckInConsole(trucks);
		}
	}





}