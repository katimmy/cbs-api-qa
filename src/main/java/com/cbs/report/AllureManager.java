/**
 * @author IvanK
 * @email ivan.katumba@cbsinteractive.com
 * @create date 2022-06-03 15:50:29
 * @modify date 2022-06-03 15:50:29
 * @desc [description]
 */
package com.cbs.report;

import com.cbs.driver.DriverManager;
import com.github.automatedowl.tools.AllureEnvironmentWriter;
import com.google.common.collect.ImmutableMap;
import io.qameta.allure.Attachment;
import org.openqa.selenium.TakesScreenshot;

import static com.cbs.config.ConfigurationManager.configuration;
import static org.openqa.selenium.OutputType.BYTES;


public class AllureManager {

    private AllureManager() {
    }

    public static void setAllureEnvironmentInformation() {
        AllureEnvironmentWriter.allureEnvironmentWriter(
                ImmutableMap.<String, String>builder().put("Test URL", configuration().url())
                        .put("Target execution", configuration().target())
                        .put("Global timeout", String.valueOf(configuration().timeout()))
                        .put("Headless mode", String.valueOf(configuration().headless()))
                        .put("Faker locale", configuration().faker()).put("Local browser", configuration().browser())
                        .put("Grid URL", configuration().gridUrl()).put("Grid port", configuration().gridPort())
                        .build());
    }

    public static void setAllureWebservicesEnvironmentInformation() {
        String Env = System.getProperty("environment");
        String suite = System.getProperty("suite");
        String ver = System.getProperty("version");
        String url = "";
        String xnull = null;

        if (Env == null) {

            Env = "stage";
        }


        AllureEnvironmentWriter.allureEnvironmentWriter(
                ImmutableMap.<String, String>builder()
                        .put("Test URL", url)
                        .put("Target execution", configuration().target())
                        // .put("Suite Executed", suite)
                        .put("Environment", Env)
                        .put("Global timeout", String.valueOf(configuration().timeout())).build());

    }

    @Attachment(value = "Failed test screenshot", type = "image/png")
    public static byte[] takeScreenshotToAttachOnAllureReport() {
        return ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(BYTES);
    }

    @Attachment(value = "Browser information", type = "text/plain")
    public static String addBrowserInformationOnAllureReport() {
        return DriverManager.getInfo();
    }

}
