package services;

import com.google.common.collect.Lists;
import entities.CompletePack;
import entities.Package;
import lombok.extern.slf4j.Slf4j;
import utils.Space;

import java.util.*;
@Slf4j
public class PackageService {

	Space space = new Space();

	/*Отправка собранного финального вида пакетов для погрузки*/
	public List<Package> createNewPackageForTruck(String[] unassembledPackageLine, String mode, String typeOut) {
		log.info("Начало сортировки посылок...");
		List<Package> completePackage;
		List<Package> newPackage = sortOrder(unassembledPackageLine);
		if(mode.toUpperCase(Locale.ROOT).contains("S") || typeOut.toUpperCase(Locale.ROOT).contains("JSON")) {
			completePackage = newPackage;
		} else {
			completePackage = createNewPackage(newPackage);
		}
		log.info("Конец сортировки посылок...");
		return sortRevertReadyPackage(completePackage);
	}

	/*Первоначальная сортировка полученного списка*/
	private List<Package> sortOrder(String[] unassembledPackageLine) {
		List<Package> readyPackages = new ArrayList();
		int i=0;
		for (String unassembledPackages : unassembledPackageLine) {
			i += 1;
			log.debug("Сортировка посылки: "+i);
			String[] unassembledPackage = unassembledPackages.split(":");
			int h = unassembledPackage.length;
			int wTop = unassembledPackage[0].length();
			int wBotton = unassembledPackage[h-1].length();
			readyPackages.add(new Package(h,wTop,wBotton,unassembledPackage));
		}
		return sortReadyPackage(readyPackages);
	}

	/*Сбор нового списка пакетов в утрамбованном виде с проверкой по ширине*/
	private List<Package> createNewPackage(List<Package> readyPackages) {
		List<CompletePack> trucks = new ArrayList();
		trucks.add(new CompletePack());
		int i=0;
		for (Package readyPack : readyPackages) {
			i += 1;
			ListIterator<CompletePack> truck = trucks.listIterator();
			boolean packageNotUsed = true;
			while(packageNotUsed && truck.hasNext()) {
				CompletePack next = truck.next();
				if(space.getFreeSpaceW(next.getPackages())>=readyPack.getWidthBottom()){
					next.getPackages().add(readyPack);
					packageNotUsed = false;
				}
			}
			if(packageNotUsed) {
				CompletePack newTruck = new CompletePack();
				truck.add(newTruck);
				newTruck.getPackages().add(readyPack);
			}
		}
		return sortReadyPackage(completeNewPackage(Lists.newArrayList(trucks)));
	}

	/*Сбор отдельного утрамбованного пакета*/
	private List<Package> completeNewPackage(List<CompletePack> completePacks) {
		List<Package> readyPackages = new ArrayList<>();
		for (CompletePack completePack : Lists.newArrayList(completePacks)) {
			if(completePack.getPackages().size()>1) {
				readyPackages.add(new Package(
						Math.max(completePack.getPackages().get(0).getHeight(), completePack.getPackages().get(1).getHeight()),
						Math.max(completePack.getPackages().get(0).getWidthTop(), completePack.getPackages().get(1).getWidthTop()),
						completePack.getPackages().get(0).getWidthBottom() + completePack.getPackages().get(1).getWidthBottom(),
						sumArrayPack(completePack.getPackages().get(0).getPack(), completePack.getPackages().get(1).getPack())));
			} else {
				readyPackages.add(new Package(
						completePack.getPackages().get(0).getHeight(),
						completePack.getPackages().get(0).getWidthTop(),
						completePack.getPackages().get(0).getWidthBottom(),
						completePack.getPackages().get(0).getPack()));
			}
		}
		return readyPackages;
	}

	/*Сам метод предполагал суммирование пакетов для утрамбовывания,но вышел кошмар. Поправить не успеваю и тут главный косяк всей логики*/
	private String[] sumArrayPack(String[] a, String[] b) {
		boolean aTrue = true;
		String sum = "";
		String sum2 = "";
		int count = 0;
		String[] c;
		String[] d;
		if(a.length> b.length) {
			c = new String[a.length];
			c = a;
			d = new String[b.length];
			d = b;
		} else {
			d = new String[a.length];
			d = a;
			c = new String[b.length];
			c = b;
		}

		for(int i = d.length-1; i >= 0; i--) {
			sum += c[i];
			sum += d[i];
			sum += "\n";
			count++;
		}
		if(count<c.length) {
			for (int i = count; i >= c.length - 1; i--) {
				sum2 += c[i];
				sum2 += "\n";
			}
		}
		String[]v = new String[]{sum2+sum};
		return v;
	}

	/*Обратная сортировка по основания посылки*/
	private List<Package> sortReadyPackage(List<Package> readyPackages){
		Collections.sort(readyPackages, new Comparator<Package>() {
			@Override
			public int compare(Package o1, Package o2) {
				return o2.getWidthBottom() - o1.getWidthBottom();
			}
		});
		return readyPackages;
	}

	/*Прямая сортировка по основания посылки*/
	private List<Package> sortRevertReadyPackage(List<Package> readyPackages){
		Collections.sort(readyPackages, new Comparator<Package>() {
			@Override
			public int compare(Package o1, Package o2) {
				return o1.getWidthBottom() - o2.getWidthBottom();
			}
		});
		return readyPackages;
	}

}
