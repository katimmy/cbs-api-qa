/**
 * @author IvanK
 * @email ivan.katumba@cbsinteractive.com
 * @create date 2022-06-03 15:50:12
 * @modify date 2022-06-03 15:50:12
 * @desc [description]
 */
package com.cbs.pages;

import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.cbs.driver.DriverManager;

import static com.cbs.config.ConfigurationManager.configuration;
import static org.openqa.selenium.support.PageFactory.initElements;

public class AbstractPageObject {

    protected AbstractPageObject() {
        initElements(new AjaxElementLocatorFactory(DriverManager.getDriver(),
                configuration().timeout()), this);
    }



}
