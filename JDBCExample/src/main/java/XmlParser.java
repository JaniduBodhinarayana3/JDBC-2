import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

public class XmlParser {
    public static void main(String[] args) {
        try {
            // Create a new DocumentBuilderFactory and DocumentBuilder
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Parse the XML file
            Document document = builder.parse("books.xml");

            // Normalize the document
            document.getDocumentElement().normalize();

            // Get the root element (library)
            NodeList nodeList = document.getElementsByTagName("book");

            // Modify the year of the first book
            Element firstBook = (Element) nodeList.item(0);
            firstBook.getElementsByTagName("year").item(0).setTextContent("2023");

            // Save the modified document
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File("updated_books.xml"));

            transformer.transform(source, result);

            System.out.println("Updated XML saved to updated_books.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}