package services;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
@Slf4j
public class FileService {

	public FileService() {}

	public String[] readFileTxt(String directory, String fileName) {
		String content = null;
		try {
			Path path = Paths.get(directory + fileName);
			content = Files.readString(path);
			content = content.replace("\n", ":");
		} catch (Exception e) {
			log.error("Не удалось прочитать данные из файла");
			throw new RuntimeException("Не удалось прочитать данные из файла");
		}
		return content.split("::");
	}

	public String readFileJson(String directory, String fileName) {
		StringBuilder builder = new StringBuilder();
		List<String> strings = new ArrayList<>();
		try {
			Path path = Paths.get(directory + fileName);
			strings = Files.readAllLines(path);
		} catch (IOException e) {
			log.error("Не удалось прочитать данные из файла");
			throw new RuntimeException("Не удалось прочитать данные из файла");
		}
		strings.forEach(builder::append);
		return builder.toString();
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
