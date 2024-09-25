package services;

import entities.Package;
import entities.Truck;
import lombok.extern.slf4j.Slf4j;
import java.util.*;
@Slf4j
public class PackageService {

	public List<Package> sortSimpleOrders(String[] unassembledPackageLine) {
		List<Package> readyPackages = new ArrayList();
		for (String unassembledPackages : unassembledPackageLine) {
			log.debug("Сортировка посылки");
			String[] unassembledPackage = unassembledPackages.split("\n");
			int h = unassembledPackage.length;
			int wTop = unassembledPackage[0].length();
			int wBotton = unassembledPackage[h-1].length();
			readyPackages.add(new Package(h,wTop,wBotton,unassembledPackage));
		}
		return sortRevertReadyPackage(readyPackages);
	}

	public List<Package> sortComplexOrders(List<Truck> listTrucks) {
		List<Package> readyPackages = new ArrayList<>();
		for (Truck truck : listTrucks) {
			if(truck.getPackages().size()>1) {
				readyPackages.add(new Package(
						Math.max(truck.getPackages().get(0).getHeight(), truck.getPackages().get(1).getHeight()),
						Math.max(truck.getPackages().get(0).getWidthTop(), truck.getPackages().get(1).getWidthTop()),
						truck.getPackages().get(0).getWidthBottom() + truck.getPackages().get(1).getWidthBottom(),
						mergePackages(truck.getPackages().get(0), truck.getPackages().get(1))));
			} else {
				readyPackages.add(new Package(
						truck.getPackages().get(0).getHeight(),
						truck.getPackages().get(0).getWidthTop(),
						truck.getPackages().get(0).getWidthBottom(),
						truck.getPackages().get(0).getPack()));
			}
		}
		return sortRevertReadyPackage(readyPackages);
	}

	private String[] mergePackages(Package pkg1, Package pkg2) {
		// Получаем пакеты из обоих объектов Package
		String[] pack1 = pkg1.getPack();
		String[] pack2 = pkg2.getPack();

		// Определяем максимальную длину, чтобы избежать выхода за границы
		int maxLength = Math.max(pack1.length, pack2.length);
		List<String> mergedList = new ArrayList<>();

		for (int i = 0; i < maxLength; i++) {
			StringBuilder sb = new StringBuilder();
			if (i < pack1.length) {
				sb.append(pack1[i]);
			}
			if (i < pack2.length) {
				sb.append(pack2[i]);
			}
			mergedList.add(sb.toString());
		}

		// Преобразуем список в массив для возврата
		String[] mergedArray = new String[mergedList.size()];
		int j = 0;
		for (int i = mergedList.size()-1; i >= 0; i--) {
			mergedArray[j] = mergedList.get(i);
			j++;
		}
		return mergedArray;
	}

	private List<Package> sortRevertReadyPackage(List<Package> readyPackages) {
		readyPackages.sort(Comparator.comparingInt(Package::getWidthBottom));
		return readyPackages;
	}

}
