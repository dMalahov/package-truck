import enums.Directory;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import services.FileService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FileTest {

	FileService fileService = new FileService();

	@Test
	public void testReadFileTxt() {
		String[] result = fileService.readFileTxt(Directory.TEST_DIR.getPath(), "test.txt");
		assertThat(result[0]).isEqualTo("777:7777");
		assertThat(result[1]).isEqualTo("22:");
	}

	@Test
	public void testReadFileTxtError() {
		assertThatThrownBy(()->fileService.readFileTxt(Directory.TEST_DIR.getPath(), "error.txt"))
				.isInstanceOf(RuntimeException.class)
				.hasMessageContaining("Не удалось прочитать данные из файла");
	}

	@Test
	public void testReadFileJson() {
		String result = fileService.readFileJson(Directory.TEST_DIR.getPath(), "test.json");
		System.out.println(result);
		assertThat(result.contains("\"string\": \"777:7777\"")).isTrue();
	}

	@Test
	public void testReadFileJsonError() {
		assertThatThrownBy(()->fileService.readFileJson(Directory.TEST_DIR.getPath(), "error.json"))
				.isInstanceOf(RuntimeException.class)
				.hasMessageContaining("Не удалось прочитать данные из файла");
	}

}
