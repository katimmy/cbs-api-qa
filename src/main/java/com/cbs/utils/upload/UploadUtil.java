package com.cbs.utils.upload;


import org.openqa.selenium.WebDriver;
import java.awt.*;
import java.awt.datatransfer.StringSelection;

public class UploadUtil {

    public UploadUtil() { }

    public UploadUtil(WebDriver driver, String filePath) {
        this.filePath = filePath;
    }

    //Class constants
    
    public static String fileName = "void_check.jpg";
    String filePath = "\\\\tomcat\\share\\IT\\Tier 2 - Internal Use\\Automation\\TestData\\CheckUpload\\"+fileName;
    int _Timeout = 2000;

    /**
     * set File Name
     * @param fileName
     */
    public static void setFileName(String fileName) {
        UploadUtil.fileName = fileName;
    }

    /**
     * Upload file util
     * @throws AWTException
     * @throws InterruptedException
     */
    public void uploadFile() throws Exception {

        StringSelection stringSelection = new StringSelection(filePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
        Robot robot = new Robot();
        robot.keyPress(java.awt.event.KeyEvent.VK_ENTER);
        robot.keyRelease(java.awt.event.KeyEvent.VK_ENTER);
        robot.keyPress(java.awt.event.KeyEvent.VK_CONTROL);
        robot.keyPress(java.awt.event.KeyEvent.VK_V);
        robot.keyRelease(java.awt.event.KeyEvent.VK_CONTROL);       
        robot.keyPress(java.awt.event.KeyEvent.VK_ENTER);
    }

}
