import entities.Package;
import org.junit.Test;
import services.TruckService;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TruckTest {

	private String jsonTest = "{\"trucks\":[{\"packages\":[{\"package\":\"777:7777\"},{\"package\":\"22\"}]},{\"packages\":[{\"package\":\"777:7777\"},{\"package\":\"22\"}]}]}";

	@Test
	public void testTruckSimple() {
		List<Package> packages = new ArrayList<>();
		TruckService truckService = new TruckService();
		packages.add(new Package(2,2,5,new String[]{"999","999","999"}));
		packages.add(new Package(4,3,5,new String[]{"999","999","999"}));
		packages.add(new Package(4,3,5,new String[]{"999","999","999"}));
		packages.add(new Package(4,3,5,new String[]{"999","999","999"}));
		packages.add(new Package(2,3,5,new String[]{"999","999","999"}));
		assertThat(truckService.createSimpleTruck(packages,10).size()).isEqualTo(5);
	}

	@Test
	public void testTruckComplex() {
		List<Package> packages = new ArrayList<>();
		TruckService truckService = new TruckService();
		packages.add(new Package(2,2,5,new String[]{"999","999","999"}));
		packages.add(new Package(4,3,5,new String[]{"999","999","999"}));
		packages.add(new Package(4,3,5,new String[]{"999","999","999"}));
		packages.add(new Package(4,3,5,new String[]{"999","999","999"}));
		packages.add(new Package(2,3,5,new String[]{"999","999","999"}));
		assertThat(truckService.createComplexTruckForHeight(packages,10).size()).isEqualTo(3);
	}

	@Test
	public void testErrorCountTruckSimple() {
		List<Package> packages = new ArrayList<>();
		TruckService truckService = new TruckService();
		packages.add(new Package(2,2,5,new String[]{"999","999","999"}));
		packages.add(new Package(4,3,5,new String[]{"999","999","999"}));
		packages.add(new Package(4,3,5,new String[]{"999","999","999"}));
		packages.add(new Package(4,3,5,new String[]{"999","999","999"}));
		packages.add(new Package(2,3,5,new String[]{"999","999","999"}));
		assertThatThrownBy(()->truckService.createSimpleTruck(packages,4))
				.isInstanceOf(RuntimeException.class)
				.hasMessageContaining("Недостаточно грузовиков для погрузки");
	}

	@Test
	public void testErrorCountTruckComplex() {
		List<Package> packages = new ArrayList<>();
		TruckService truckService = new TruckService();
		packages.add(new Package(2,2,5,new String[]{"999","999","999"}));
		packages.add(new Package(4,3,5,new String[]{"999","999","999"}));
		packages.add(new Package(4,3,5,new String[]{"999","999","999"}));
		packages.add(new Package(4,3,5,new String[]{"999","999","999"}));
		packages.add(new Package(2,3,5,new String[]{"999","999","999"}));
		assertThatThrownBy(()->truckService.createComplexTruckForHeight(packages,2))
				.isInstanceOf(RuntimeException.class)
				.hasMessageContaining("Недостаточно грузовиков для погрузки");
	}

	@Test
	public void testCreateTruckSIze() {
		TruckService truckService = new TruckService();
		assertThat(truckService.createTruckForJson(jsonTest,10).size()).isEqualTo(2);
	}

	@Test
	public void testCreateTruckError() {
		TruckService truckService = new TruckService();
		assertThatThrownBy(()->truckService.createTruckForJson(jsonTest,1))
				.isInstanceOf(RuntimeException.class)
				.hasMessageContaining("Недостаточно грузовиков для погрузки");
	}

}
