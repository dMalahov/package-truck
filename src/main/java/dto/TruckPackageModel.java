package dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TruckPackageModel {

	List<Truck> trucks;

	@Getter
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Truck {
		@JsonProperty("packages")
		ArrayList<PackageModel> packages;
	}

	@Getter
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class PackageModel {
		@JsonProperty("string")
		String string;
	}

}
