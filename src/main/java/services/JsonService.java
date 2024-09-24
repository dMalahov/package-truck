package services;

import com.google.common.collect.Lists;
import dto.TruckPackageModel;
import entities.Package;
import entities.Truck;
import lombok.extern.slf4j.Slf4j;
import utils.Mapper;

import java.util.ArrayList;
import java.util.List;
@Slf4j
public class JsonService {
	Mapper parserJson = new Mapper();

	/*Создание списка грузовиков необходимых для погрузки*/
	public List<Truck> getTruckToJson(String json, int truckCount) {
		log.info("Начало погрузки грузовиков...");
		List<Truck> trucks = new ArrayList();
		TruckPackageModel trucksModel = parserJson.readJson(json,TruckPackageModel.class);
		int size = trucksModel.getTrucks().size()-1;
		int cont = 1;
		for (int i = 0; i <= size; i++) {
			TruckPackageModel.Truck trucksJson = trucksModel.getTrucks().get(i);
			List<Package> readyPackages = getPackageToJson(trucksJson);
			if(cont<=truckCount) {
				log.debug("Погрузка грузовика: "+cont);
				Truck newTruck = new Truck();
				for (Package readyPack : readyPackages) {
					newTruck.getPackages().add(readyPack);
				}
				trucks.add(newTruck);
				++cont;
			} else {
				log.error("Недостаточно грузовиков для погрузки");
				throw new RuntimeException("Недостаточно грузовиков для погрузки");
			}
		}
		log.info("Конец погрузки грузовиков...");
		return Lists.newArrayList(trucks);
	}

	/*Сортировка полученного списка*/
	private List<Package> getPackageToJson(TruckPackageModel.Truck truck) {
		log.info("Начало сортировки посылок...");
		ArrayList<TruckPackageModel.PackageModel> packages = truck.getPackages();
		List<Package> readyPackages = new ArrayList();
		int size = packages.size()-1;
		for (int i = 0; i <= size; i++) {
			log.debug("Сортировка посылки: "+(i+1));
			String[] unassembledPackage = packages.get(i).getString().split(":");
			int h = unassembledPackage.length;
			int wTop = unassembledPackage[0].length();
			int wBotton = unassembledPackage[h-1].length();
			readyPackages.add(new Package(h,wTop,wBotton,unassembledPackage));
		}
		log.info("Конец сортировки посылок...");
		return readyPackages;
	}

	/*Формирование json из списка грузовиков*/
	public String createJsonForTruck(List<Truck> truckArrayList) {
		log.info("Формирование json...");
		ArrayList<TruckPackageModel.Truck> truck = new ArrayList<>();
		if(truckArrayList != null) {
			for (int i = 0; i <= truckArrayList.size()-1; i++) {
				ArrayList<TruckPackageModel.PackageModel> packages = new ArrayList<>();
				for (int j = 0; j <= truckArrayList.get(i).getPackages().size()-1; j++) {
					String[] packing = truckArrayList.get(i).getPackages().get(j).getPack();
					String pack = "";
					for (String s : packing) {
						pack += s.replace("\n",":")+":";
					}
					String pack2 = pack.replace("::",":");
					packages.add(TruckPackageModel.PackageModel.builder().string(pack2.substring(0, pack2.length() - 1)).build());
				}
			truck.add(TruckPackageModel.Truck.builder().packages(packages).build());
			}
		}
		String trucks = parserJson.writeToString(TruckPackageModel.builder().trucks(truck).build());
		log.debug("Полученный json: "+trucks);
		return trucks;
	}
}
