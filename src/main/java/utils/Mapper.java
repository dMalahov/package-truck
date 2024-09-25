package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * Класс Mapper предоставляет методы для чтения и записи JSON.
 *
 * Этот класс позволяет преобразовывать объекты Java в JSON-строки
 * и наоборот, используя библиотеку Jackson.
 *
 * @param "<T>" Тип объекта, который будет десериализован.
 */
@Slf4j
public class Mapper {

	/**
	 * Десериализует строку JSON в объект указанного типа.
	 *
	 * Метод принимает строку, содержащую JSON, и класс, в который необходимо
	 * преобразовать этот JSON. В случае ошибок в процессе десериализации
	 * выбрасывается RuntimeException.
	 *
	 * @param json строка, содержащая JSON для десериализации.
	 * @param type класс, в который нужно преобразовать JSON.
	 * @return объект указанного типа, созданный из JSON.
	 * @throws RuntimeException если произошла ошибка при обработке JSON.
	 */
	public <T> T readJson(String json, Class<T> type) {
		try {
			return new ObjectMapper().readValue(json,type);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Серилизует объект в строку JSON.
	 *
	 * Метод принимает объект и преобразует его в строку в формате JSON.
	 * В случае ошибок при сериализации выбрасывается RuntimeException.
	 *
	 * @param value объект, который нужно сериализовать в JSON.
	 * @return строка в формате JSON, представляющая указанный объект.
	 * @throws RuntimeException если произошла ошибка при обработке объекта.
	 */
	public String writeToString(Object value) {
		try {
			return new ObjectMapper().writeValueAsString(value);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

}
