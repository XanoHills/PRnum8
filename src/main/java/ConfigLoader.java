package main.java;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.io.File;


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

    public void saveConfig(String filename, int numberOfCashiers) {
        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            // Корневой элемент
            Element root = document.createElement("supermarket");
            document.appendChild(root);

            // Элемент numberOfCashiers
            Element cashiers = document.createElement("numberOfCashiers");
            cashiers.appendChild(document.createTextNode(Integer.toString(numberOfCashiers)));
            root.appendChild(cashiers);

            // Создание трансформера для записи в файл
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(filename));

            // Запись в файл
            transformer.transform(domSource, streamResult);

            System.out.println("Configuration saved to " + filename);
        } catch (ParserConfigurationException | TransformerException pce) {
            pce.printStackTrace();
        }
    }
}
