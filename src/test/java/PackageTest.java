import entities.Package;
import org.junit.Test;
import services.PackageService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PackageTest {

	PackageService service = new PackageService();

	@Test
	public void testCreateReadePackageSoftSortSize() {
		String[] pack = new String[]{"22", "666:666", "1", "55555", "777:7777"};
		List<Package> testPack =  service.createNewPackageForTruck(pack,"s","txt");
		assertEquals(5, testPack.size());
	}

	@Test
	public void testCreateReadePackageFinalSortSize() {
		String[] pack = new String[]{"22", "666:666", "1", "55555", "777:7777"};
		List<Package> testPack =  service.createNewPackageForTruck(pack,"c","txt");
		assertEquals(3, testPack.size());
	}

	@Test
	public void testCreateReaderPackageSort() {
		String[] pack = new String[]{"22", "666:666", "1", "55555", "777:7777", "22"};
		List<Package> testPack =  service.createNewPackageForTruck(pack,"c","json");
		assertEquals(1, testPack.get(0).getWidthBottom());
		assertEquals(2, testPack.get(1).getWidthBottom());
		assertEquals(2, testPack.get(2).getWidthBottom());
		assertEquals(3, testPack.get(3).getWidthBottom());
		assertEquals(4, testPack.get(4).getWidthBottom());
		assertEquals(5, testPack.get(5).getWidthBottom());
	}

	@Test
	public void testCreateReaderPackageFinalSort() {
		String[] pack = new String[]{"22", "666:666", "1", "55555", "777:7777", "22"};
		PackageService service = new PackageService();
		List<Package> testPack =  service.createNewPackageForTruck(pack,"c","txt");
		assertEquals(5, testPack.get(0).getWidthBottom());
		assertEquals(6, testPack.get(1).getWidthBottom());
		assertEquals(6, testPack.get(2).getWidthBottom());
	}

}
