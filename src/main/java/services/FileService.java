package services;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Служба для работы с файлами, включая чтение и запись данных.
 */
@Slf4j
public class FileService {

	public final String MAIN_DIR = System.getProperty("user.dir") + "\\data\\";

	public FileService() {}

	/**
	 * Получает список файлов в заданной директории.
	 *
	 * @param directory директория для поиска файлов.
	 * @return список файлов.
	 */
	public List<File> getFiles(String directory) {
		File dir = new File(directory); //path указывает на директорию
		List<File> listFiles = new ArrayList<>();
		for ( File file : Objects.requireNonNull(dir.listFiles())){
			if (file.isFile()) {
				listFiles.add(file);
			}
		}
		return listFiles;
	}

	/**
	 * Читает текстовый файл и возвращает его содержимое в виде массива строк.
	 *
	 * @param directory директория файла.
	 * @param fileName имя файла.
	 * @return массив строк, содержащих содержимое файла.
	 */
	public String[] readFileTxt(String directory, String fileName) {
		String content = null;
		try {
			Path path = Paths.get(directory + fileName);
			content = Files.readString(path);
		} catch (Exception e) {
			log.error("Не удалось прочитать данные из файла");
			throw new RuntimeException("Не удалось прочитать данные из файла");
		}
		return content.split("\n\n");
	}

	/**
	 * Читает содержимое JSON файла.
	 *
	 * @param directory директория файла.
	 * @param fileName имя файла.
	 * @return содержимое файла как строка.
	 */
	public String readFileJson(String directory, String fileName) {
		String content = null;
		try {
			Path path = Paths.get(directory + fileName);
			content = Files.readString(path);
		} catch (IOException e) {
			log.error("Не удалось прочитать данные из файла");
			throw new RuntimeException("Не удалось прочитать данные из файла");
		}
		return content;
	}

	/**
	 * Записывает JSON данные в файл result.json.
	 *
	 * @param jsonBody тело JSON для записи.
	 */
	public void writeResultJson(String jsonBody) {
		try {
			Writer writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream("result.json"), "utf-8"));
			writer.write(jsonBody);
			writer.close();
		} catch (IOException e) {
			log.error("Ошибка записи json");
			throw new RuntimeException("Ошибка записи json");
		}
	}


}
