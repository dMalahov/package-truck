//import org.junit.Test;
//import services.JsonService;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Assertions.assertThatThrownBy;
//
//public class JsonTest {
//
//	private String jsonTest = "{\"trucks\":[{\"packages\":[{\"string\":\"777:7777\"},{\"string\":\"22\"}]},{\"packages\":[{\"string\":\"777:7777\"},{\"string\":\"22\"}]}]}";
//	JsonService jsonService = new JsonService();
//
//	@Test
//	public void testCreateTruckSIze() {
//		assertThat(jsonService.getTruckToJson(jsonTest,10).size()).isEqualTo(2);
//	}
//
//	@Test
//	public void testCreateTruckError() {
//		assertThatThrownBy(()->jsonService.getTruckToJson(jsonTest,1))
//				.isInstanceOf(RuntimeException.class)
//				.hasMessageContaining("Недостаточно грузовиков для погрузки");
//	}
//
//}
