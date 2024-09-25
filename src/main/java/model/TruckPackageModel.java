package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Модель для работы с грузовиками и посылками в JSON.
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TruckPackageModel {

	List<Truck> trucks;

	/**
	 * Вложенный класс, описывающий массив посылок.
	 */
	@Getter
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Truck {
		@JsonProperty("packages")
		ArrayList<Order> orders;
	}

	/**
	 * Вложенный класс, описывающий каждую посылку.
	 */
	@Getter
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Order {
		@JsonProperty("package")
		String order;
	}

}
