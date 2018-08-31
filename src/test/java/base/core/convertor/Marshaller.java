package base.core.convertor;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class Marshaller {

    private static final Logger log = Logger.getLogger(Marshaller.class);


    /**
     * "collect" stream to string
     *
     * @param is {@link InputStream}
     * @return String
     */
    private static String inputStreamToString(InputStream is) {
        StringBuilder sb = new StringBuilder();
        String line;
        try {

            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * create stream of string data
     *
     * @param str {@link String}
     * @return InputStream
     */
    public static InputStream stringToInputStream(String str) {
        try {
            return new ByteArrayInputStream(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Unmarshall Xml File to DTO (can use part of xml tag)
     *
     * @param file       file
     * @param objectType object type
     * @param <T>        Generic marker
     * @return T
     */
    public static <T> T unmarshall(File file, Class<T> objectType) {

        try {
            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, false);
            xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            String xml = inputStreamToString(new FileInputStream(file));

            return xmlMapper.readValue(xml, objectType);

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("unable to unmarshall file");
        }
    }

    /**
     * Unmarshall Xml String to DTO (can use part of xml tag)
     *
     * @param xml        string xml
     * @param objectType object type
     * @param <T>        Generic marker
     * @return T
     */
    public static <T> T unmarshall(String xml, Class<T> objectType) {

        try {
//            XmlMapper xmlMapper = new XmlMapper();
//            xmlMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, false);
//            xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//
//            return xmlMapper.readValue(xml, objectType);

            JAXBContext context = JAXBContext.newInstance(objectType);


            Unmarshaller m = context.createUnmarshaller();
            InputStream stream = new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8));
            return (T) m.unmarshal(stream);
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw new RuntimeException("unable to unmarshall file");
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Unmarshall Xml envelop File to DTO
     *
     * @param objectType object type
     * @param f          file
     * @param <T>        Generic marker
     * @return T
     */
    @SuppressWarnings("unchecked")
    public static <T> T unmarshallEnvelop(Class<T> objectType, File f) {
        try {

            FileInputStream file = new FileInputStream(f);
            SOAPMessage message = MessageFactory.newInstance().createMessage(null, file);
            JAXBContext jaxbContext = JAXBContext.newInstance(objectType);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            Document msg = message.getSOAPBody().extractContentAsDocument();

            return (T) jaxbUnmarshaller.unmarshal(msg);
        } catch (SOAPException | JAXBException | IOException e) {
            e.printStackTrace();
            throw new RuntimeException("unable to unmarshall file");
        }
    }

    /**
     * Unmarshall Xml envelop String to DTO
     *
     * @param xml        String
     * @param objectType object type
     * @param <T>        Generic marker
     * @return T
     */
    @SuppressWarnings("unchecked")
    public static <T> T unmarshallEnvelop(String xml, Class<T> objectType) {
        try {

            SOAPMessage message = MessageFactory.newInstance().createMessage(null, stringToInputStream(xml));
            JAXBContext jaxbContext = JAXBContext.newInstance(objectType);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            Document msg = message.getSOAPBody().extractContentAsDocument();
            return (T) jaxbUnmarshaller.unmarshal(msg);
        } catch (SOAPException | JAXBException | IOException e) {
            e.printStackTrace();
            throw new RuntimeException("unable to unmarshall file");
        }
    }


    /**
     * Marshall Object to xml String data
     *
     * @param object dto / entity
     * @return String
     */
    public static String marshall(Object object) {
        try {
            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.enable(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS);
            xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
            xmlMapper.setPropertyNamingStrategy(PropertyNamingStrategy.UPPER_CAMEL_CASE);
            xmlMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            StringWriter stream = new StringWriter();
            xmlMapper.writeValue(stream, object);
            return String.valueOf(stream);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(object);
            log.error(e.getMessage());
            throw new RuntimeException("unable to marshall object");
        }

    }


}
