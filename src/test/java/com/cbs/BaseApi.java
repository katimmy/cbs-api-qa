/**
 * @author IvanK
 * @email ivan.katumba@cbsinteractive.com
 * @create date 2022-06-03 15:55:58
 * @modify date 2022-06-03 15:55:58
 * @desc [description]
 */
package com.cbs;

import static com.cbs.config.ConfigurationManager.apiConfiguration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.cbs.config.ApiEndPoints;
import com.cbs.config.Environment;
import com.cbs.config.TestConfiguration;
import com.cbs.report.AllureManager;
import com.cbs.utils.excel.ExcelUtils;
import com.github.javafaker.Faker;

import helpers.DBHelpers;



public class BaseApi {
    Faker faker = new Faker();
    public Map<String, String> testcases = new HashMap<>();
    public Map<String, String> envparam = new HashMap<>();
    ArrayList<List<String>> testcaseslist = new ArrayList<List<String>>();
    ArrayList<List<String>> allList = new ArrayList<List<String>>();
    public String Env, ver;
    protected DBHelpers dbhelper = new DBHelpers();
    public String[][] all;
    public Map<String, String> generaltestcases = new HashMap<>();
    protected TestConfiguration configuration = new TestConfiguration();
    ExcelUtils excelUtils = new ExcelUtils();
    List<String> data = new ArrayList<>();
    protected String _apibaseurlEndPoint = "";
    private String _apiendpoints = "apiendpoints";
    protected ApiEndPoints apiEndpointConfig = new TestConfiguration().getApiEndPoint();
    protected Environment envConfig  = new TestConfiguration().getEnvironment();
    
 
    
    @BeforeSuite
    public void beforeSuite() {

        AllureManager.setAllureWebservicesEnvironmentInformation();
        System.setProperty("allure.link.clickup.pattern", "https://app.clickup.com/t/{}");
      
    }

    @BeforeClass
    public Map<String, String> getApplicationData() {

        

        Map<String, String> applicationData = new HashMap<>();
        applicationData.put("FirstName", faker.name().firstName());
        applicationData.put("LastName", faker.name().lastName());
        applicationData.put("Email", faker.internet().emailAddress());
        applicationData.put("Street", faker.address().streetAddress());
        applicationData.put("CityName", faker.address().cityName());
        applicationData.put("State", "CA");
        applicationData.put("ZipCode", faker.address().zipCode());       
        applicationData.put("CompanyName", faker.company().name());
        applicationData.put("Phone", faker.phoneNumber().cellPhone());
        applicationData.put("AccountNo", faker.number().digits(5));
        applicationData.put("BankAccountNo", faker.number().digits(10));
        applicationData.put("RoutingNo", faker.number().digits(9));

        return applicationData;
    }

    @BeforeMethod
    public void beforeMethod() {

       
        Env = System.getProperty("environment");
        ver = System.getProperty("version");
       
      //  _apibaseurlEndPoint = apiEndpointConfig.getEndPoint("apiName", "db.server.tfpa.PolicyCenter") ;     

        if (Env == null) {

            Env = "stage";

        }

        try {
            String baseEnv = "";
            String clientID = "";
            String clientSecret = "";
            String grantType = "";

            switch (Env) {

                case "stage":

                    envparam.put("baseEnv", apiConfiguration().stageapiurl());
                    envparam.put("version", ver);
                    _apibaseurlEndPoint = envConfig.getURL(Env, "vms.api.url");

                    // envparam.put("financeOfferFlag", dbhelper.getUseFinancePlanFlag("15357"));

                    // envparam.put("clientID", configuration().apiendpoint());
                    // envparam.put("clientSecret", configuration().apiendpoint());
                    // envparam.put("grantType", configuration().apiendpoint());
                    // baseEnv = configuration().apiendpoint();
                    break;

                
            }
        } catch (Exception e) {

            e.printStackTrace();
            Assert.assertFalse(true, "No Environment selected to use");
            return;

        }

        envparam.put("env", Env);

    }

    @AfterMethod
    public void afterMethod(ITestResult result) {

       

        try {
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

    @AfterClass
    public void AfterClass(ITestContext context) {

       

        int failedtest = context.getFailedTests().getAllResults().size();
        if (failedtest < 1) {
           

            System.out.println("All the Testcases Passed");
            

        } else {
           
            System.out.println(failedtest + "Testcases Failed");
        }

      

        

      

    }

}
