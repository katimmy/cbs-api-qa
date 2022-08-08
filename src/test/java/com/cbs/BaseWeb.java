/**
 * @author IvanK
 * @email ivan.katumba@cbsinteractive.com
 * @create date 2022-06-03 15:56:17
 * @modify date 2022-06-03 15:56:17
 * @desc [description]
 */


package com.cbs;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.cbs.config.Environment;
import com.cbs.config.TestConfiguration;
import com.cbs.driver.DriverManager;
import com.cbs.driver.TargetFactory;
import com.cbs.report.AllureManager;
import com.cbs.utils.LoggerUtil;
import com.cbs.web.base.BaseMethods;
import com.cbs.web.base.Waiter;
import com.github.javafaker.Faker;

import helpers.DBHelpers;
import io.qameta.allure.Step;

@Listeners({ TestListener.class })
public abstract class BaseWeb extends BaseMethods {

    Logger logger = LogManager.getLogger(getClass().getName());
    Faker faker = new Faker();
    DBHelpers dbHelpers = new DBHelpers();
    public Map<String, String> testcases = new HashMap<>();
    private Waiter waiter = new Waiter();
    public String Env, ver;
    private String _url = "";
    public String _username = "";
    public String _password= "";
    protected TestConfiguration configuration = new TestConfiguration();
    Environment envConfig = new TestConfiguration().getEnvironment();
    public LoggerUtil log = new LoggerUtil(this.getClass());

    @BeforeClass
    public Map<String, String> getApplicationData() {

        Map<String, String> applicationData = new HashMap<>();
        applicationData.put("FirstName", faker.name().firstName());
        applicationData.put("LastName", faker.name().lastName());
        applicationData.put("Email", faker.internet().emailAddress());
        applicationData.put("Street", faker.address().streetAddress());
        applicationData.put("CityName", faker.address().cityName());
        applicationData.put("Country", faker.address().country());
        applicationData.put("State", faker.address().stateAbbr());
        applicationData.put("ZipCode", faker.address().zipCode());
        applicationData.put("Phone", faker.phoneNumber().subscriberNumber(11));
        applicationData.put("SSN", "222222222");
        applicationData.put("PatientID", faker.number().digits(4));
        applicationData.put("CardNo", "4111111111111111");
        applicationData.put("CVV", "123");
        applicationData.put("******", "password");
        applicationData.put("BusinessName", faker.company().name());
        applicationData.put("CompanyID", faker.idNumber().valid());

        return applicationData;
    }

    public enum CardTypes {
        CREDDIT,
        DEBIT,
        HSA;
    }

    @Step("Opening the Orlando Portal")
    protected void startOrandoportal() {

       // waiter.get(configuration().orlandoportalurl(), DriverManager.getDriver());
           
       _url = envConfig.getURL(Env, "adminportal" + ".url");       
       log.info("Opening URL : " + _url);
       DriverManager.startDriver(_url);
    }

    @Step("Opening the Customer Portal")
    protected void openCustomerPortal() {

       //Get Stack, Username and Password
        
       _url = envConfig.getURL(Env, "customerportal" + ".url");       
       log.info("Opening URL : " + _url);
       DriverManager.startDriver(_url);
      
    }
   

    @BeforeSuite
    public void beforeSuite() {

        AllureManager.setAllureEnvironmentInformation();

    }

    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    public void preCondition(@Optional("chrome") String browser) {
        WebDriver driver = new TargetFactory().createInstance(browser);
        DriverManager.setDriver(driver);

        Env = System.getProperty("environment");
        ver = System.getProperty("version");

        if (Env == null) {

            Env = "stage";

        }

       _username = envConfig.getURL(Env, "customerportal" + ".username");  
       _password = envConfig.getURL(Env, "customerportal" + ".password");  
       

    }

    @AfterMethod(alwaysRun = true)
    public void postCondition(ITestResult result) {
        // try {
        // AllureManager.takeScreenshotToAttachOnAllureReport();
        // DriverManager.quit();
        // } catch (Exception e) {
        // System.out.println("****************************** POST CONDITION ERROR: " +
        // e);

        // }

       

        try {
            AllureManager.takeScreenshotToAttachOnAllureReport();
            DriverManager.quit();
            if (result.getStatus() == ITestResult.SUCCESS) {

                // Do something here
                System.out.println("passed **********");
                
            }

            else if (result.getStatus() == ITestResult.FAILURE) {
                // Do something here
                System.out.println("Failed ***********");
                

            }

            else if (result.getStatus() == ITestResult.SKIP) {

                System.out.println("Skiped***********");
               

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        

    }

}
