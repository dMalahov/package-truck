package services;

import com.google.common.collect.Lists;
import entities.Package;
import entities.Truck;
import lombok.extern.slf4j.Slf4j;
import model.TruckPackageModel;
import utils.Mapper;

import java.util.*;
@Slf4j
public class TruckService {
	protected final int TRUCK_WIDTH = 6;
	protected final int TRUCK_HEIGHT = 6;


	public List<Truck> createSimpleTruck(List<Package> readyPackages, int truckCount) {
		List<Truck> trucks = new ArrayList<>();
		for (Package readyPack : readyPackages) {
			log.debug("Погрузка грузовика");
			Truck newTruck = new Truck();
			trucks.add(newTruck);
			newTruck.getPackages().add(readyPack);
		}
		checkTrucksCount(truckCount,trucks);
		return Lists.newArrayList(trucks);
	}

	public List<Truck> createComplexTruckForHeight(List<Package> readyPackages, int truckCount) {
		List<Truck> trucks = new ArrayList<>();
		log.debug("Погрузка грузовика");
		trucks.add(new Truck());
		for (Package readyPack : readyPackages) {
			ListIterator<Truck> truck = trucks.listIterator();
			boolean packageNotUsed = true;
			while(packageNotUsed && truck.hasNext()) {
				Truck next = truck.next();
				if(getFreeSpaceH(next.getPackages())>=readyPack.getHeight()){
					next.getPackages().add(readyPack);
					packageNotUsed = false;
				}
			}
			if(packageNotUsed) {
				log.debug("Погрузка грузовика");
				Truck newTruck = new Truck();
				truck.add(newTruck);
				newTruck.getPackages().add(readyPack);
			}
		}
		checkTrucksCount(truckCount,trucks);
		return Lists.newArrayList(trucks);
	}

	public List<Truck> createComplexTruckForWidth(List<Package> readyPackages) {
		log.info("Пересбор посылок по ширине");
		List<Truck> trucks = new ArrayList<>();
		trucks.add(new Truck());
		for (Package readyPack : readyPackages) {
			ListIterator<Truck> truck = trucks.listIterator();
			boolean packageNotUsed = true;
			while(packageNotUsed && truck.hasNext()) {
				Truck next = truck.next();
				if(getFreeSpaceW(next.getPackages())>=readyPack.getWidthBottom()){
					next.getPackages().add(readyPack);
					packageNotUsed = false;
				}
			}
			if(packageNotUsed) {
				Truck newTruck = new Truck();
				truck.add(newTruck);
				newTruck.getPackages().add(readyPack);
			}
		}
		return Lists.newArrayList(trucks);
	}

	public List<Truck> createTruckForJson(String json, int truckCount) {
		log.info("Начало погрузки грузовиков...");
		Mapper parserJson = new Mapper();
		List<Truck> trucks = new ArrayList<>();
		TruckPackageModel trucksModel = parserJson.readJson(json,TruckPackageModel.class);
		int size = trucksModel.getTrucks().size()-1;
		for (int i = 0; i <= size; i++) {
			TruckPackageModel.Truck trucksJson = trucksModel.getTrucks().get(i);
			List<Package> readyPackages = getPackagesTruckForJson(trucksJson);
			log.debug("Погрузка грузовика");
			Truck newTruck = new Truck();
			for (Package readyPack : readyPackages) {
				newTruck.getPackages().add(readyPack);
			}
			trucks.add(newTruck);
		}
		checkTrucksCount(truckCount,trucks);
		log.info("Конец погрузки грузовиков...");
		return Lists.newArrayList(trucks);
	}

	public void printTruckInConsole(List<Truck> truckArrayList) {
		if(truckArrayList != null) {
			log.info("Печать в консоль...");
			for (Truck truckPrint : truckArrayList) {
				StringBuilder truckString = new StringBuilder();
				truckString.append("+      +\n".repeat(Math.max(0, getFreeSpaceH(truckPrint.getPackages()))));
				for (Package aPackage : truckPrint.getPackages()) {
					for (String packString : aPackage.getPack()) {
						String[] numberList = packString.split("\n");
						for (String number : numberList) {
							truckString.append("+");
							truckString.append(padRight(number, TRUCK_WIDTH));
							truckString.append("+").append(System.lineSeparator());
						}
					}
				}
				truckString.append("++++++++").append(System.lineSeparator()).append("\r");
				System.out.println(truckString);
			}
		} else {
			log.error("Что то пошло не так - Список грузовиков пуст");
		}
	}

	public String getJsonForTruck(List<Truck> truckArrayList) {
		log.info("Формирование json...");
		Mapper parserJson = new Mapper();
		ArrayList<TruckPackageModel.Truck> truck = new ArrayList<>();
		if(truckArrayList != null) {
			for (int i = 0; i <= truckArrayList.size()-1; i++) {
				ArrayList<TruckPackageModel.Order> packages = new ArrayList<>();
				for (int j = 0; j <= truckArrayList.get(i).getPackages().size()-1; j++) {
					String[] packing = truckArrayList.get(i).getPackages().get(j).getPack();
					StringBuilder pack = new StringBuilder();
					for (String s : packing) {
						pack.append(s.replace("\n", ":")).append(":");
					}
					String pack2 = pack.toString().replace("::",":");
					packages.add(TruckPackageModel.Order.builder().order(pack2.substring(0, pack2.length() - 1)).build());
				}
				truck.add(TruckPackageModel.Truck.builder().orders(packages).build());
			}
		}
		String trucks = parserJson.writeToString(TruckPackageModel.builder().trucks(truck).build());
		log.debug("Полученный json: "+trucks);
		return trucks;
	}

	private int getFreeSpaceH(List<Package> packages) {
		int sum = 0;
		for (Package aPackage : packages) {
			int i = aPackage.getHeight();
			sum += i;
		}
		return TRUCK_HEIGHT-sum;
	}

	private int getFreeSpaceW(List<Package> packages) {
		int sum = 0;
		for (Package aPackage : packages) {
			int i = aPackage.getWidthBottom();
			sum += i;
		}
		return TRUCK_WIDTH-sum;
	}

	private static String padRight(String s, int n) {
		return String.format("%-" + n + "s", s);
	}


	private List<Package> getPackagesTruckForJson(TruckPackageModel.Truck truck) {
		log.info("Начало сортировки посылок...");
		ArrayList<TruckPackageModel.Order> packages = truck.getOrders();
		List<Package> readyPackages = new ArrayList<>();
		int size = packages.size()-1;
		for (int i = 0; i <= size; i++) {
			log.debug("Сортировка посылки");
			String[] unassembledPackage = packages.get(i).getOrder().split(":");
			int h = unassembledPackage.length;
			int wTop = unassembledPackage[0].length();
			int wBotton = unassembledPackage[h-1].length();
			readyPackages.add(new Package(h,wTop,wBotton,unassembledPackage));
		}
		log.info("Конец сортировки посылок...");
		return readyPackages;
	}

	private void checkTrucksCount(int truckCount, List<Truck> trucks) {
		if(truckCount < trucks.size()) {
			log.error("Недостаточно грузовиков для погрузки");
			throw new RuntimeException("Недостаточно грузовиков для погрузки");
		}
	}


}
