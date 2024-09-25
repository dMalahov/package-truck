//import entities.Package;
//import org.junit.Test;
//import services.TruckService;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Assertions.assertThatThrownBy;
//
//public class TruckTest {
//
//	@Test
//	public void testComplexTruckSimple() {
//		List<Package> packages = new ArrayList<>();
//		TruckService truckService = new TruckService();
//		packages.add(new Package(2,2,5,new String[]{"999","999","999"}));
//		packages.add(new Package(4,3,5,new String[]{"999","999","999"}));
//		packages.add(new Package(4,3,5,new String[]{"999","999","999"}));
//		packages.add(new Package(4,3,5,new String[]{"999","999","999"}));
//		packages.add(new Package(2,3,5,new String[]{"999","999","999"}));
//		assertThat(truckService.createTruckWithPackage(packages,"s",10).size()).isEqualTo(5);
//	}
//
//	@Test
//	public void testComplexTruckComplex() {
//		List<Package> packages = new ArrayList<>();
//		TruckService truckService = new TruckService();
//		packages.add(new Package(2,2,5,new String[]{"999","999","999"}));
//		packages.add(new Package(4,3,5,new String[]{"999","999","999"}));
//		packages.add(new Package(4,3,5,new String[]{"999","999","999"}));
//		packages.add(new Package(4,3,5,new String[]{"999","999","999"}));
//		packages.add(new Package(2,3,5,new String[]{"999","999","999"}));
//		assertThat(truckService.createTruckWithPackage(packages,"c",10).size()).isEqualTo(3);
//	}
//
//	@Test
//	public void testErrorCountTruckSimple() {
//		List<Package> packages = new ArrayList<>();
//		TruckService truckService = new TruckService();
//		packages.add(new Package(2,2,5,new String[]{"999","999","999"}));
//		packages.add(new Package(4,3,5,new String[]{"999","999","999"}));
//		packages.add(new Package(4,3,5,new String[]{"999","999","999"}));
//		packages.add(new Package(4,3,5,new String[]{"999","999","999"}));
//		packages.add(new Package(2,3,5,new String[]{"999","999","999"}));
//		assertThatThrownBy(()->truckService.createTruckWithPackage(packages,"s",4))
//				.isInstanceOf(RuntimeException.class)
//				.hasMessageContaining("Недостаточно грузовиков для погрузки");
//	}
//
//	@Test
//	public void testErrorCountTruckComplex() {
//		List<Package> packages = new ArrayList<>();
//		TruckService truckService = new TruckService();
//		packages.add(new Package(2,2,5,new String[]{"999","999","999"}));
//		packages.add(new Package(4,3,5,new String[]{"999","999","999"}));
//		packages.add(new Package(4,3,5,new String[]{"999","999","999"}));
//		packages.add(new Package(4,3,5,new String[]{"999","999","999"}));
//		packages.add(new Package(2,3,5,new String[]{"999","999","999"}));
//		assertThatThrownBy(()->truckService.createTruckWithPackage(packages,"c",2))
//				.isInstanceOf(RuntimeException.class)
//				.hasMessageContaining("Недостаточно грузовиков для погрузки");
//	}
//
//}
