package main.java;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Класс для загрузки конфигурации из XML-файла
 */
public class ConfigLoader {
    public int loadConfig(String fileName) {
        int numberOfCashiers = 3; // default value
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(fileName);
            Element rootElement = doc.getDocumentElement();
            numberOfCashiers = Integer.parseInt(rootElement.getElementsByTagName("numberOfCashiers").item(0).getTextContent());
        } catch (Exception e) {
            System.out.println("Error loading config: " + e.getMessage());
        }
        return numberOfCashiers;
    }
}
