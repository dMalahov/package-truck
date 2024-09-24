package entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PackageMerger {
	public static String[] mergePackages(Package pkg1, Package pkg2) {
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
		for (int i = 0; i < mergedList.size(); i++) {
			mergedArray[i] = mergedList.get(i);
		}

		return mergedArray;
	}

	public static void main(String[] args) {
		String[] pack1 = {"999","999","999"};
		String[] pack2 = {"666","666"};

		Package package1 = new Package(3, 3, 3, pack1);
		Package package2 = new Package(2, 3, 3, pack2);

		String[] mergedPackages = mergePackages(package1, package2);

		// Печатаем результат
		System.out.println(Arrays.deepToString(mergedPackages));
	}
}
