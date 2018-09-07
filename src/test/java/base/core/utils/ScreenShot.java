package base.core.utils;


import base.core.ConfigLoader;
import base.core.constants.Dir;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class ScreenShot {

    private final EventFiringWebDriver driver;
    private final String name;

    public ScreenShot(EventFiringWebDriver driver, String name) {
        this.driver = driver;
        this.name = name;
    }

    private Screenshot fullScreen() {
        return new AShot()
                .shootingStrategy(ShootingStrategies.viewportPasting(10))
                .takeScreenshot(driver);
    }

    private Screenshot elementScreen(WebElement element) {
        return new AShot()
                .coordsProvider(new WebDriverCoordsProvider())
                .takeScreenshot(driver, element);
    }

    private Screenshot viewportScreen() {
        return new AShot().takeScreenshot(driver);
    }


    /**
     * allure attach screenshot to report as screenShotName
     *
     * @param screenshot     {@link Screenshot}
     * @param screenShotName {@link String}
     * @return byte[] ignored
     */
    @Attachment(value = "{1}")
    private byte[] attachScreenShot(Screenshot screenshot, String screenShotName) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(screenshot.getImage(), ConfigLoader.getInstance().getProperty("project.screenshots.extension"), baos);
            baos.flush();
            byte[] bytes = baos.toByteArray();
            baos.close();
            return bytes;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[]{};
    }

    /**
     * save screenshot as @name in folder @See(project.properties)
     *
     * @param screenshot {@link Screenshot}
     * @param name       {@link String}
     * @return String of abs path to saved screenshot
     */
    private String saveScreenShot(Screenshot screenshot, String name) {
        String screenDir = Dir.PROJECT + ConfigLoader.getInstance().get("project.screenshots.path");
        String extension = ConfigLoader.getInstance().getProperty("project.screenshots.extension");
        String path = screenDir + name + "." + extension;
        File dir = new File(screenDir);
        if (!dir.exists()) {
            dir.mkdir();
        }
        try {
            ImageIO.write(screenshot.getImage(), extension, new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return path;
    }

    /**
     * Take element screenShot attach it and save
     *
     * @return path to saved file
     */
    public String fullScreenShot() {
        Screenshot shot = fullScreen();
        attachScreenShot(shot, name);
        return saveScreenShot(shot, name);
    }

    /**
     * Take full screen screenShot attach it and save
     *
     * @param element {@link WebElement}
     * @return path to saved file
     */
    public String element(WebElement element) {
        Screenshot shot = elementScreen(element);
        attachScreenShot(shot, name);
        return saveScreenShot(shot, name);
    }

    /**
     * Take viewport screen shot attach it and save
     *
     * @return path to saved file
     */
    public String viewport() {
        Screenshot screen = viewportScreen();
        attachScreenShot(screen, name);
        return saveScreenShot(screen, name);
    }
}
