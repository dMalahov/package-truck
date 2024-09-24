package services;

import com.google.common.collect.Lists;
import entities.CompletePack;
import entities.Package;
import entities.Truck;
import lombok.extern.slf4j.Slf4j;
import utils.Space;

import java.util.*;
@Slf4j
public class TruckService {

	Space space = new Space();

	/*Создание списка грузовиков необходимых для погрузки*/
	public List<Truck> createTruckWithPackage(List<Package> readyPackages, String mode, int truckCount) {
		List<Truck> trucks = null;
		log.info("Начало погрузки грузовиков...");
		if(mode.toUpperCase(Locale.ROOT).contains("S")) {
			trucks = createSimpleTruck(readyPackages,truckCount);
		} else {
			trucks = createComplexTruck(readyPackages,truckCount);
		}
		log.info("Конец погрузки грузовиков...");
		return trucks;
	}

	/*Загрузка грузовиков сложным способом*/
	private List<Truck> createComplexTruck(List<Package> readyPackages, int truckCount) {
		List<Truck> trucks = new ArrayList();
		trucks.add(new Truck());
		int cont = 1;
		for (Package readyPack : readyPackages) {
			ListIterator<Truck> truck = trucks.listIterator();
			boolean packageNotUsed = true;
			if(cont<truckCount) {
				log.debug("Погрузка грузовика: "+cont);
				while(packageNotUsed && truck.hasNext()) {
					Truck next = truck.next();
					if(space.getFreeSpaceH(next.getPackages())>=readyPack.getHeight()){
						next.getPackages().add(readyPack);
						packageNotUsed = false;
					}
				}
				if(packageNotUsed) {
					Truck newTruck = new Truck();
					truck.add(newTruck);
					newTruck.getPackages().add(readyPack);
					++cont;
				}
			} else {
				log.error("Недостаточно грузовиков для погрузки");
				throw new RuntimeException("Недостаточно грузовиков для погрузки");
			}
		}
		return Lists.newArrayList(trucks);
	}

	/*Загрузка грузовиков простым способом*/
	private List<Truck> createSimpleTruck(List<Package> readyPackages, int truckCount) {
		List<Truck> trucks = new ArrayList();
		int cont = 1;
		for (Package readyPack : readyPackages) {
			if(cont<=truckCount) {
				log.debug("Погрузка грузовика: "+cont);
				Truck newTruck = new Truck();
				trucks.add(newTruck);
				newTruck.getPackages().add(readyPack);
				++cont;
			} else {
				log.error("Недостаточно грузовиков для погрузки");
				throw new RuntimeException("Недостаточно грузовиков для погрузки");
			}
		}
		return Lists.newArrayList(trucks);
	}


}
