package base.core.utils;

import base.core.helpers.FReader;
import org.codehaus.plexus.util.xml.XmlUtil;
import org.json.JSONObject;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Step;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class AllureUtils {


    public static void clickNotify(WebElement element) {
        clickNotify(element.getText());
    }

    //
//    /**
//     * allure attach step with target click
//     * @param target {@link String}
//     */
//    @Step("Click on {0}")
    public static void clickNotify(String target) {
        //nothing here, just add use for @Step
    }

    /**
     * allure add step to report
     *
     * @param stepName {@link String} step name
     * @param step     {@link Runnable} task
     */
    @Step("{0}")
    public static void addStep(String stepName, Runnable step) {
        step.run();
    }

    /**
     * allure attach string data to report
     *
     * @param data attachment data {@link String}
     * @return ignored
     */
    @Attachment(value = "string attachment")
    public static String attachString(String data) {
        return data;
    }

    /**
     * allure attach string data to report
     *
     * @param data attachment Data
     * @param name attachment name
     * @return ignored
     */
    @Attachment(value = "{1}")
    @SuppressWarnings("all")
    public static String attachString(String data, String name) {
        return data;
    }

    /**
     * allure attach json to report(with indent factor )
     *
     * @param json {@link JSONObject}
     * @param name {@link String}
     * @return ignored
     */
    @Attachment(value = "{1}", type = "application/json")
    public static String attachJSON(JSONObject json, String name) {
        return json.toString(2);
    }

    /**
     * allure attach xml file to report
     *
     * @param filePath       abspath to file
     * @param attachmentName name of attachment
     * @return ignored
     */
    @Attachment(value = "{1}", type = "application/xml")
    public static byte[] attachXml(String filePath, String attachmentName) {
        String xml = FReader.readFile(filePath);
        assert xml != null;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            XmlUtil.prettyFormat(new ByteArrayInputStream(xml.getBytes()), outputStream);
            return outputStream.toByteArray();
        } catch (IOException e) {
            return xml.getBytes();
        }
    }

    /**
     * allure attach file to report
     *
     * @param filePath abs path to file
     * @return ignored
     */
    @Attachment(value = "file attachment", type = "file/text")
    public byte[] attachFile(String filePath) {
        String file = FReader.readFile(filePath);
        assert file != null;
        return file.getBytes();
    }
}
