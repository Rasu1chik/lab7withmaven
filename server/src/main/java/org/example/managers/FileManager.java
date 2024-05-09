package org.example.managers;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.utility.Printable;
import org.example.exeptions.ExitObliged;
import org.example.exeptions.InvalidForm;
import org.example.models.*;


import java.io.*;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.Arrays;

/**
 * Класс реализующий работу с файлами
 */
public class FileManager {
    private final Printable console;
    private final CollectionManager collectionManager;
    private final String pathToFile;


    static final Logger fileManagerLogger = LogManager.getLogger(FileManager.class);

    /**
     * В конструкторе задаются параметры для работы с файлами
     *
     * @param console           Пользовательский ввод-вывод
     * @param collectionManager Работа с коллекцией
     */
    public FileManager(Printable console, CollectionManager collectionManager, String fileToPath) {
        this.console = console;
        this.collectionManager = collectionManager;
        this.pathToFile = fileToPath;

    }


    /**
     * Обращение к переменным среды и чтение файла в поле по указанному пути
     *
     * @throws ExitObliged если путь - null или отсутствует программа заканчивает выполнение
     */
    public boolean isFindFile() throws ExitObliged {
        File file = new File(pathToFile);
        StringBuilder stringBuilder = new StringBuilder();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                stringBuilder.append(scanner.nextLine()).append("\n");
            }
            if (stringBuilder.isEmpty()) {
                fileManagerLogger.error("Передан пустой файл");
                return false;
            } else {
                fileManagerLogger.info("Путь получен успешно. Поздравляю вы на сервере. УРА");
            }
        } catch (FileNotFoundException e) {
            fileManagerLogger.error("Файл не найден");
            throw new ExitObliged();
        } catch (IOException e) {
            fileManagerLogger.error("Ошибка при чтении файла");
            throw new ExitObliged();
        }
        return true;
    }


    /**
     * Создание объектов Flat в консольном менеджере
     *
     * @throws ExitObliged Если объекты в файле невалидны выходим из программы
     * @throws InvalidForm Исключение выбрасывается при ошибке формата CSV
     */
    public void createObjects() throws ExitObliged, InvalidForm {
        try (CSVReader reader = new CSVReader(new FileReader(pathToFile))) {
            String[] header = reader.readNext(); // Пропускаем строку заголовка (если она есть)
            String[] nextLine;
            int lineNumber = 1;
            while ((nextLine = reader.readNext()) != null) {
                try {
                    lineNumber++;
                    if (nextLine.length != 12) {
                        fileManagerLogger.error("Неверный формат CSV в строке " + lineNumber);
                        throw new InvalidForm();
                    }


                    Coordinates coordinates = new Coordinates(
                            Double.parseDouble(nextLine[2].split(", ")[0].substring(1)),  // x coordinate
                            Integer.parseInt(nextLine[2].split(", ")[1].substring(0, nextLine[2].split(", ")[1].length() - 1))  // y coordinate
                    );

                    House house = new House(
                            nextLine[8],  // house name
                            Long.parseLong(nextLine[9]),  // year
                            (nextLine[10].isEmpty()) ? null : Long.parseLong(nextLine[10]),  // numberOfFloors
                            (nextLine[11].isEmpty()) ? null : Integer.parseInt(nextLine[11])  // numberOfFlatsOnFloor
                    );
                    Flat flat = new Flat(
                            nextLine[1],  // name
                            coordinates,  // coordinates
                            Double.parseDouble(nextLine[3]),  // area
                            (nextLine[4].isEmpty()) ? null : Long.parseLong(nextLine[4]),  // numberOfRooms
                            Furnish.valueOf(nextLine[5]),  // furnish
                            View.valueOf(nextLine[6]),  // view
                            Transport.valueOf(nextLine[7]),  // transport
                            house  // house object
                    );
                    collectionManager.addElement(flat);
                } catch (NumberFormatException e) {
                    fileManagerLogger.error("Ошибка форматирования данных в CSV в строке " + lineNumber + ": " + e.getMessage());
                } catch (IllegalArgumentException e) {
                    fileManagerLogger.error("Неверно задано значение в CSV в строке " + lineNumber + ": " + e.getMessage());
                }
            }
        } catch (IOException e) {
            fileManagerLogger.error("Ошибка при чтении CSV");
            throw new ExitObliged();
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Сохраняем коллекцию из менеджера в файл
     */
    public void saveObjects() {
        if (pathToFile == null || pathToFile.isEmpty()) {
            console.printError("Путь должен быть в переменных окружения в переменной 'file_path'");
            fileManagerLogger.fatal("Отсутствует путь в переменных окружения");
            return;
        }
        try (CSVWriter writer = new CSVWriter(new FileWriter(pathToFile))) {
            // Записываем заголовок
            String[] header = {"id","name", "coordinates", "area", "numberOfRooms", "furnish", "view", "transport", "house:name", "house:year","house:numberOfFloors","house:numberOfFlatsOnFloor"};
            writer.writeNext(header);

            for (Flat flat : collectionManager.getCollection()) {
                writer.writeNext(new String[]{String.valueOf(flat.getId()),
                        flat.getName(),
                        String.valueOf(flat.getCoordinates()),
                        String.valueOf(flat.getArea()),
                        String.valueOf(flat.getNumberOfRooms()),
                        flat.getFurnish().toString(),
                        flat.getView().toString(),
                        flat.getTransport().toString(),
                        flat.getHouse().getName(),  // получаем имя дома
                        String.valueOf(flat.getHouse().getYear()),  // получаем год постройки дома
                        String.valueOf(flat.getHouse().getNumberOfFloors()),  // получаем количество этажей в доме
                        String.valueOf(flat.getHouse().getNumberOfFlatsOnFloor())  // получаем количество квартир на этаже
                });
            }

        } catch (IOException e) {
            fileManagerLogger.error("Ошибка при записи СSV");
        }
    }
}