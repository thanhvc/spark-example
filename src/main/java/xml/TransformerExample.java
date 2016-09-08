package xml;

import org.apache.commons.io.FileUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.StringReader;

public class TransformerExample {
    private final static String XLS_PATH = "src/main/resources/transformer.xsl";
    private final static String XML_PATH = "src/main/resources/transformer.xml";

    public static Document xmlTrans(String xml) throws Exception {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Source source = new StreamSource(new File(XLS_PATH));
        Templates templates = transformerFactory.newTemplates(source);
        Transformer transformer = templates.newTransformer();
        Source xmlSource = new StreamSource(new StringReader(xml));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Result outputTarget = new StreamResult(out);
        transformer.transform(xmlSource, outputTarget);
        String result = new String(out.toByteArray());
        return DocumentHelper.parseText(result);
    }

    public static void main(String[] args) throws Exception {
        String xml = FileUtils.readFileToString(new File(XML_PATH));
        Document doc = xmlTrans(xml);
        System.out.println(doc.getStringValue());
    }
}
