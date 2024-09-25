# Домашнее задание - Проект package-truck

## Описание

Этот проект включает в себя классы и службы для работы с грузовиками и пакетами, включая загрузку данных из файлов и их обработку. Он использует библиотеку Lombok для упрощения создания классов-моделей.

## Установка

1. Клонируйте репозиторий на ваш компьютер:
   ```bash
   git clone https://github.com/dMalahov/package-truck.git
   ```

2. Перейдите в директорию проекта:
   ```bash
   cd название-проекта
   ```

3. Убедитесь, что у вас установлены зависимости, указанные в `pom.xml` (если используете Maven).

## Использование

### Основные классы

- **ConsoleValues**: Хранит значения, полученные из консоли, такие как имя файла, тип загрузки и режим.
- **Package**: Модель пакета, содержащая высоту, ширину и массив содержимого.
- **Truck**: Класс, представляющий грузовик, содержащий список пакетов.
- **TruckPackageModel**: Модель для работы с грузовиками и пакетами, использующая JSON для сериализации.
- **FileService**: Служба для работы с файлами. Позволяет читать и записывать данные в файлы.
- **TruckService**: Служба для создания грузовиков различной сложности в зависимости от параметров пакетов
- **PackageService**: Класс, отвечающий за сортировку и объединение различных типов посылок.

### Основные директории

- **\data**: Содержит файлы с информацией о посылках
- **\logs**: Содержит логи работы кода
- **\src\main\java\entities**: Сущности
- **\src\main\java\model**: Модели для Json
- **\src\main\java\services**: Основные операции
- **\src\main\java\utils**: Утилиты
- **\src\main\java\view**: Для работы с консолью

### Примеры использования

Пример указания в `консоли`:

```java
1. Выбрать файл для получения данных о посылках - test1.txt
2. Выбрать режим погрузки - txt/json
3. Выбрать способ вывода данных - c/s
4. Указать кол-во доступных грузовиков - 5
```

Пример создания объекта `ConsoleValues`:

```java
ConsoleValues consoleValues = new ConsoleValues("file.txt", "txt", "s", "json", 10);
```

### Результаты выполнения

- Выводит результат в косоль
- Сохраняет в корневую директорию сформированный json - `result.json`