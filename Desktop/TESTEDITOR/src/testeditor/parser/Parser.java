package testeditor.parser;

import testeditor.Test;

import java.io.IOException;

/**
 * Базовый класс для парсеров различных форматов
 */
abstract public class Parser {

    public static Test parse(String filepath) throws Exception {
        Parser parser = Parser.getParser(filepath);
        return parser.getTest(filepath);
    }

    private static Parser getParser(String filepath) throws Exception {
        String ext = filepath.substring(filepath.lastIndexOf('.') + 1).toLowerCase();
        switch (ext) {
            case "xml":
                return new XMLParser();
            default:
                throw new Exception("Такой формат файла не зарегистрирован в программе");
        }
    }

    abstract public Test getTest(String filepath) throws IOException;
}
