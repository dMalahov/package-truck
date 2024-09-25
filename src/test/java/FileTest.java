import org.junit.Test;
import services.FileService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FileTest {

	FileService fileService = new FileService();
	public final String TEST_DIR = System.getProperty("user.dir") + "\\src\\test\\resources\\";

	@Test
	public void testReadFileTxt() {
		String[] result = fileService.readFileTxt(TEST_DIR, "test.txt");
		assertThat(result[0]).isEqualTo("777\n7777");
		assertThat(result[1]).isEqualTo("22\n");
	}

	@Test
	public void testReadFileTxtError() {
		assertThatThrownBy(()->fileService.readFileTxt(TEST_DIR, "error.txt"))
				.isInstanceOf(RuntimeException.class)
				.hasMessageContaining("Не удалось прочитать данные из файла");
	}

	@Test
	public void testReadFileJson() {
		String result = fileService.readFileJson(TEST_DIR, "test.json");
		System.out.println(result);
		assertThat(result.contains("\"package\": \"777:7777\"")).isTrue();
	}

	@Test
	public void testReadFileJsonError() {
		assertThatThrownBy(()->fileService.readFileJson(TEST_DIR, "error.json"))
				.isInstanceOf(RuntimeException.class)
				.hasMessageContaining("Не удалось прочитать данные из файла");
	}

}
