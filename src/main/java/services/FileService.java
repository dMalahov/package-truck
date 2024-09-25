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

@Slf4j
public class FileService {

	public final String MAIN_DIR = System.getProperty("user.dir") + "\\data\\";

	public final String TEST_DIR = System.getProperty("user.dir") + "\\src\\test\\resources\\";

	public FileService() {}

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
