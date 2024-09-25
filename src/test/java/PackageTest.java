import entities.Package;
import entities.Truck;
import org.junit.Test;
import services.PackageService;
import services.TruckService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PackageTest {

	PackageService service = new PackageService();

	@Test
	public void testCreateReadePackageSoftSortSize() {
		String[] pack = new String[]{"22", "666:666", "1", "55555", "777:7777"};
		List<Package> testPack =  service.sortSimpleOrders(pack);
		assertEquals(5, testPack.size());
	}

	@Test
	public void testCreateReaderPackageSort() {
		String[] pack = new String[]{"22", "666:666", "1", "55555", "777:7777", "22"};
		List<Package> testPack =   service.sortSimpleOrders(pack);
		assertEquals(1, testPack.get(0).getWidthBottom());
		assertEquals(2, testPack.get(1).getWidthBottom());
		assertEquals(2, testPack.get(2).getWidthBottom());
		assertEquals(5, testPack.get(3).getWidthBottom());
		assertEquals(7, testPack.get(4).getWidthBottom());
		assertEquals(8, testPack.get(5).getWidthBottom());
	}

}
