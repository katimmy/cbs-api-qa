/**
 * @author IvanK
 * @email ivan.katumba@cbsinteractive.com
 * @create date 2022-07-25 15:40:32
 * @modify date 2022-07-25 15:40:32
 * @desc [description]
 */

/**
 * @author IvanK
 * @email ivan.katumba@cbsinteractive.com
 * @create date 2022-07-25 15:40:24
 * @modify date 2022-07-25 15:40:24
 * @desc [description]
 */
package com.cbs.test.leaguesapi;
import static com.cbs.config.ConfigurationManager.apiConfiguration;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.cbs.BaseApi;
import com.cbs.pojos.gen.createCounterPart.Createcounterpart;
import com.cbs.utils.api.JsonFormatter;
import com.cbs.utils.api.LogListener;
import com.cbs.utils.api.RestUtils;
import com.cbs.utils.assertion.AssertionUtil;
import com.cbs.webservices.Counterparties.GetCounterPartyDetailsRequest;

import com.github.javafaker.Faker;

import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import io.qameta.allure.Epic;
import io.restassured.response.Response;

@Listeners({LogListener.class})
public class createCounterPartRegression extends BaseApi{
   
    RestUtils rest = new RestUtils();
    JsonFormatter jsonFormatter = new JsonFormatter();
    Map<String, String> applicationData = getApplicationData();
    Map<Object, Object> accountDetailsData = new HashMap<>();
    AllureLifecycle lifecycle = Allure.getLifecycle();
    SoftAssert sa = new SoftAssert();
    Faker faker = new Faker();
    GetCounterPartyDetailsRequest getCounterPartyDetailsRequest = new GetCounterPartyDetailsRequest();
    public String internalID , _apibaseurl;
    public String _createcounterparty = apiEndpointConfig.getEndPoint("Counterparties", "CreateCounterparty") ; 
    public String service = _createcounterparty;

    @Epic("Create CounterParty Regression")
    @Test(groups = { "regression" })    
    public void createCounterPart() throws Exception{

       
        SoftAssert sa;
        AssertionUtil assertionUtil = new AssertionUtil();
       //apiConfiguration().createcounterpartendpoint();

       System.out.println("The API URl is :" + _apibaseurlEndPoint);
       internalID = applicationData.get("AccountNo");
        Createcounterpart createcounterpart = new Createcounterpart();
        
        createcounterpart.setName(applicationData.get("FirstName"));
        createcounterpart.setType("Supplier");
        createcounterpart.setAddress1(applicationData.get("Street"));
        createcounterpart.setCity(applicationData.get("CityName"));
       // createcounterpart.setState(applicationData.get("State"));
        createcounterpart.setZipcode(applicationData.get("ZipCode"));
        createcounterpart.setCountry("USA");
        createcounterpart.setInternalId(internalID);
        
        
      
        // Converting a Java class object to a JSON payload as string
	
	    String createcounterpartJson = jsonFormatter.formatStringFromJsonPOJO(createcounterpart);
        jsonFormatter.printFormattedJson(createcounterpartJson, "request", service);

        Response response = rest.getAuthenticatedPOSTReponseBody(createcounterpartJson, service,
                                _apibaseurlEndPoint);
        
        jsonFormatter.printFormattedJson(response.asString(), "response", service);
        JSONObject CreateCounterResObject = new JSONObject(response.asString());
        JSONArray state = CreateCounterResObject.getJSONArray("state");

        lifecycle.updateTestCase(testResult -> testResult.setName("State Required Error  " + state.getString(0))); 
         sa = assertionUtil.assertEquals("State", 
         state.getString(0), "This field is required when payment method is PrintedCheck", 
         "The Error Message doesnt match: ");
         sa.assertAll();
        
   
    }

    @Epic("Create CounterParty Regression")
    @Test(groups = { "regression" })    
    public void emptyStateFieldPassed() throws Exception{

       
        SoftAssert sa;
        AssertionUtil assertionUtil = new AssertionUtil();
       //String service = apiConfiguration().createcounterpartendpoint();

       
       internalID = applicationData.get("AccountNo");
        Createcounterpart createcounterpart = new Createcounterpart();
        
        createcounterpart.setName(applicationData.get("FirstName"));
        createcounterpart.setType("Supplier");
        createcounterpart.setAddress1(applicationData.get("Street"));
        createcounterpart.setCity(applicationData.get("CityName"));
        createcounterpart.setState("");
        createcounterpart.setZipcode(applicationData.get("ZipCode"));
        createcounterpart.setCountry("USA");
        createcounterpart.setInternalId(internalID);
        
        
      
        // Converting a Java class object to a JSON payload as string
	
	    String createcounterpartJson = jsonFormatter.formatStringFromJsonPOJO(createcounterpart);
        jsonFormatter.printFormattedJson(createcounterpartJson, "request", service);

        Response response = rest.getAuthenticatedPOSTReponseBody(createcounterpartJson, service,
        _apibaseurlEndPoint);
        
        jsonFormatter.printFormattedJson(response.asString(), "response", service);
        JSONObject CreateCounterResObject = new JSONObject(response.asString());
        JSONArray state = CreateCounterResObject.getJSONArray("state");

        lifecycle.updateTestCase(testResult -> testResult.setName("State Required Error  " + state.getString(0))); 
         sa = assertionUtil.assertEquals("State", 
         state.getString(0), "This field is required when payment method is PrintedCheck", 
         "The Error Message doesnt match: ");
         sa.assertAll();
        
   
    }

    @Epic("Create CounterParty Regression")
    @Test(groups = { "regression" })    
    public void nameRequiredErrorMessage() throws Exception{

       
        SoftAssert sa;
        AssertionUtil assertionUtil = new AssertionUtil();
       String service = apiConfiguration().createcounterpartendpoint();

       
       internalID = applicationData.get("AccountNo");
        Createcounterpart createcounterpart = new Createcounterpart();
        
        //createcounterpart.setName(applicationData.get("FirstName"));
        createcounterpart.setType("Supplier");
        createcounterpart.setAddress1(applicationData.get("Street"));
        createcounterpart.setCity(applicationData.get("CityName"));
        createcounterpart.setState(applicationData.get("State"));
        createcounterpart.setZipcode(applicationData.get("ZipCode"));
        createcounterpart.setCountry("USA");
        createcounterpart.setInternalId(internalID);
        
        
      
        // Converting a Java class object to a JSON payload as string
	
	    String createcounterpartJson = jsonFormatter.formatStringFromJsonPOJO(createcounterpart);
        jsonFormatter.printFormattedJson(createcounterpartJson, "request", service);

        Response response = rest.getAuthenticatedPOSTReponseBody(createcounterpartJson, service,
        _apibaseurlEndPoint);
        
        jsonFormatter.printFormattedJson(response.asString(), "response", service);
        JSONObject CreateCounterResObject = new JSONObject(response.asString());
        JSONArray name = CreateCounterResObject.getJSONArray("name");

        lifecycle.updateTestCase(testResult -> testResult.setName("Name Required Error  " + name.getString(0))); 
         sa = assertionUtil.assertEquals("Name", 
         name.getString(0), "This field is required.", 
         "The Error Message doesnt match: ");
         sa.assertAll();
        
   
    }

    @Epic("Create CounterParty Regression")
    @Test(groups = { "regression" })    
    public void cityRequiredErrorMessage() throws Exception{

       
        SoftAssert sa;
        AssertionUtil assertionUtil = new AssertionUtil();
       String service = apiConfiguration().createcounterpartendpoint();

       
       internalID = applicationData.get("AccountNo");
       Createcounterpart createcounterpart = new Createcounterpart();
        
        createcounterpart.setName(applicationData.get("FirstName"));
        createcounterpart.setType("Supplier");
        createcounterpart.setAddress1(applicationData.get("Street"));
       // createcounterpart.setCity(applicationData.get("CityName"));
        createcounterpart.setState(applicationData.get("State"));
        createcounterpart.setZipcode(applicationData.get("ZipCode"));
        createcounterpart.setCountry("USA");
        createcounterpart.setInternalId(internalID);
        
        
      
        // Converting a Java class object to a JSON payload as string
	
	    String createcounterpartJson = jsonFormatter.formatStringFromJsonPOJO(createcounterpart);
        jsonFormatter.printFormattedJson(createcounterpartJson, "request", service);

        Response response = rest.getAuthenticatedPOSTReponseBody(createcounterpartJson, service,
                                _apibaseurlEndPoint);
        
        jsonFormatter.printFormattedJson(response.asString(), "response", service);
        JSONObject CreateCounterResObject = new JSONObject(response.asString());
        JSONArray city = CreateCounterResObject.getJSONArray("city");

        lifecycle.updateTestCase(testResult -> testResult.setName("City Required Error  " + city.getString(0))); 
         sa = assertionUtil.assertEquals("Name", 
         city.getString(0), "This field is required when payment method is PrintedCheck", 
         "The Error Message doesnt match: ");
         sa.assertAll();
        
   
    }

    @Epic("Create CounterParty Regression")
    @Test(groups = { "regression" })    
    public void zipCodeRequiredErrorMessage() throws Exception{

       
        SoftAssert sa;
        AssertionUtil assertionUtil = new AssertionUtil();
       String service = apiConfiguration().createcounterpartendpoint();

       
       internalID = applicationData.get("AccountNo");
       Createcounterpart createcounterpart = new Createcounterpart();
        
        createcounterpart.setName(applicationData.get("FirstName"));
        createcounterpart.setType("Supplier");
        createcounterpart.setAddress1(applicationData.get("Street"));
        createcounterpart.setCity(applicationData.get("CityName"));
        createcounterpart.setState(applicationData.get("State"));
        //createcounterpart.setZipcode(applicationData.get("ZipCode"));
        createcounterpart.setCountry("USA");
        createcounterpart.setInternalId(internalID);
        
        
      
        // Converting a Java class object to a JSON payload as string
	
	    String createcounterpartJson = jsonFormatter.formatStringFromJsonPOJO(createcounterpart);
        jsonFormatter.printFormattedJson(createcounterpartJson, "request", service);

        Response response = rest.getAuthenticatedPOSTReponseBody(createcounterpartJson, service,
                                _apibaseurlEndPoint);
        
        jsonFormatter.printFormattedJson(response.asString(), "response", service);
        JSONObject CreateCounterResObject = new JSONObject(response.asString());
        JSONArray zipcode = CreateCounterResObject.getJSONArray("zipcode");

        lifecycle.updateTestCase(testResult -> testResult.setName("ZipCode Required Error  " + zipcode.getString(0))); 
         sa = assertionUtil.assertEquals("Name", 
         zipcode.getString(0), "This field is required when payment method is PrintedCheck", 
         "The Error Message doesnt match: ");
         sa.assertAll();
        
   
    }

    @Epic("Create CounterParty Regression")
    @Test(groups = { "regression" })    
    public void internal_IDRequiredErrorMessage() throws Exception{

       
        SoftAssert sa;
        AssertionUtil assertionUtil = new AssertionUtil();
       String service = apiConfiguration().createcounterpartendpoint();

       
       internalID = applicationData.get("AccountNo");
       Createcounterpart createcounterpart = new Createcounterpart();
        
        createcounterpart.setName(applicationData.get("FirstName"));
        createcounterpart.setType("Supplier");
        createcounterpart.setAddress1(applicationData.get("Street"));
        createcounterpart.setCity(applicationData.get("CityName"));
        createcounterpart.setState(applicationData.get("State"));
        createcounterpart.setZipcode(applicationData.get("ZipCode"));
        createcounterpart.setCountry("USA");
        //createcounterpart.setInternalId(internalID);
        
        
      
        // Converting a Java class object to a JSON payload as string
	
	    String createcounterpartJson = jsonFormatter.formatStringFromJsonPOJO(createcounterpart);
        jsonFormatter.printFormattedJson(createcounterpartJson, "request", service);

        Response response = rest.getAuthenticatedPOSTReponseBody(createcounterpartJson, service,
                                _apibaseurlEndPoint);
        
        jsonFormatter.printFormattedJson(response.asString(), "response", service);
        JSONObject CreateCounterResObject = new JSONObject(response.asString());
        JSONArray internal_id = CreateCounterResObject.getJSONArray("internal_id");

        lifecycle.updateTestCase(testResult -> testResult.setName("Internal_ID Required Error  " + internal_id.getString(0))); 
        sa = assertionUtil.assertEquals("internal_id", 
        internal_id.getString(0), "This field is required.", 
        "The Error Message doesnt match: ");
        sa.assertAll();
        
   
    }

    
    
    @Epic("Create CounterParty Regression")
    @Test(groups = { "regression" })    
    public void emptynameRequiredErrorMessage() throws Exception{
    
       SoftAssert sa;
       AssertionUtil assertionUtil = new AssertionUtil();
       String service = apiConfiguration().createcounterpartendpoint();

       
       internalID = applicationData.get("AccountNo");
        Createcounterpart createcounterpart = new Createcounterpart();
        
        createcounterpart.setName("");
        createcounterpart.setType("Supplier");
        createcounterpart.setAddress1(applicationData.get("Street"));
        createcounterpart.setCity(applicationData.get("CityName"));
        createcounterpart.setState(applicationData.get("State"));
        createcounterpart.setZipcode(applicationData.get("ZipCode"));
        createcounterpart.setCountry("USA");
        createcounterpart.setInternalId(internalID);
        
        
      
        // Converting a Java class object to a JSON payload as string
	
	    String createcounterpartJson = jsonFormatter.formatStringFromJsonPOJO(createcounterpart);
        jsonFormatter.printFormattedJson(createcounterpartJson, "request", service);

        Response response = rest.getAuthenticatedPOSTReponseBody(createcounterpartJson, service,
                                _apibaseurlEndPoint);
        
        jsonFormatter.printFormattedJson(response.asString(), "response", service);
        JSONObject CreateCounterResObject = new JSONObject(response.asString());
        JSONArray name = CreateCounterResObject.getJSONArray("name");

        lifecycle.updateTestCase(testResult -> testResult.setName("Name Field Blank Error:   " + name.getString(0))); 
         sa = assertionUtil.assertEquals("Name", 
         name.getString(0), "This field may not be blank.", 
         "The Error Message doesnt match: ");
         sa.assertAll();
        
   
    }
    @Epic("Create CounterParty Regression")
    @Test(groups = { "regression" })    
    public void emptycityRequiredErrorMessage() throws Exception{

       
        SoftAssert sa;
        AssertionUtil assertionUtil = new AssertionUtil();
       String service = apiConfiguration().createcounterpartendpoint();

       
       internalID = applicationData.get("AccountNo");
       Createcounterpart createcounterpart = new Createcounterpart();
        
        createcounterpart.setName(applicationData.get("FirstName"));
        createcounterpart.setType("Supplier");
        createcounterpart.setAddress1(applicationData.get("Street"));
        createcounterpart.setCity("");
        createcounterpart.setState(applicationData.get("State"));
        createcounterpart.setZipcode(applicationData.get("ZipCode"));
        createcounterpart.setCountry("USA");
        createcounterpart.setInternalId(internalID);
        
        
      
        // Converting a Java class object to a JSON payload as string
	
	    String createcounterpartJson = jsonFormatter.formatStringFromJsonPOJO(createcounterpart);
        jsonFormatter.printFormattedJson(createcounterpartJson, "request", service);

        Response response = rest.getAuthenticatedPOSTReponseBody(createcounterpartJson, service,
                                _apibaseurlEndPoint);
        
        jsonFormatter.printFormattedJson(response.asString(), "response", service);
        JSONObject CreateCounterResObject = new JSONObject(response.asString());
        JSONArray city = CreateCounterResObject.getJSONArray("city");

        lifecycle.updateTestCase(testResult -> testResult.setName("City Field Blaank Error: " + city.getString(0))); 
         sa = assertionUtil.assertEquals("Name", 
         city.getString(0), "This field is required when payment method is PrintedCheck", 
         "The Error Message doesnt match: ");
         sa.assertAll();
        
   
    }

    @Epic("Create CounterParty Regression")
    @Test(groups = { "regression" })    
    public void emptyzipCodeRequiredErrorMessage() throws Exception{

       
        SoftAssert sa;
        AssertionUtil assertionUtil = new AssertionUtil();
       String service = apiConfiguration().createcounterpartendpoint();

       
       internalID = applicationData.get("AccountNo");
       Createcounterpart createcounterpart = new Createcounterpart();
        
        createcounterpart.setName(applicationData.get("FirstName"));
        createcounterpart.setType("Supplier");
        createcounterpart.setAddress1(applicationData.get("Street"));
        createcounterpart.setCity(applicationData.get("CityName"));
        createcounterpart.setState(applicationData.get("State"));
        createcounterpart.setZipcode("");
        createcounterpart.setCountry("USA");
        createcounterpart.setInternalId(internalID);
        
        
      
        // Converting a Java class object to a JSON payload as string
	
	    String createcounterpartJson = jsonFormatter.formatStringFromJsonPOJO(createcounterpart);
        jsonFormatter.printFormattedJson(createcounterpartJson, "request", service);

        Response response = rest.getAuthenticatedPOSTReponseBody(createcounterpartJson, service,
                                _apibaseurlEndPoint);
        
        jsonFormatter.printFormattedJson(response.asString(), "response", service);
        JSONObject CreateCounterResObject = new JSONObject(response.asString());
        JSONArray zipcode = CreateCounterResObject.getJSONArray("zipcode");

        lifecycle.updateTestCase(testResult -> testResult.setName("ZipCode Blank Error  " + zipcode.getString(0))); 
         sa = assertionUtil.assertEquals("Name", 
         zipcode.getString(0), "This field is required when payment method is PrintedCheck", 
         "The Error Message doesnt match: ");
         sa.assertAll();
        
   
    }

    @Epic("Create CounterParty Regression")
    @Test(groups = { "regression" })    
    public void emptyinternal_IDRequiredErrorMessage() throws Exception{

       
        SoftAssert sa;
        AssertionUtil assertionUtil = new AssertionUtil();
       String service = apiConfiguration().createcounterpartendpoint();

       
       internalID = applicationData.get("AccountNo");
       Createcounterpart createcounterpart = new Createcounterpart();
        
        createcounterpart.setName(applicationData.get("FirstName"));
        createcounterpart.setType("Supplier");
        createcounterpart.setAddress1(applicationData.get("Street"));
        createcounterpart.setCity(applicationData.get("CityName"));
        createcounterpart.setState(applicationData.get("State"));
        createcounterpart.setZipcode(applicationData.get("ZipCode"));
        createcounterpart.setCountry("USA");
        createcounterpart.setInternalId("");
        
        
      
        // Converting a Java class object to a JSON payload as string
	
	    String createcounterpartJson = jsonFormatter.formatStringFromJsonPOJO(createcounterpart);
        jsonFormatter.printFormattedJson(createcounterpartJson, "request", service);

        Response response = rest.getAuthenticatedPOSTReponseBody(createcounterpartJson, service,
                                _apibaseurlEndPoint);
        
        jsonFormatter.printFormattedJson(response.asString(), "response", service);
        JSONObject CreateCounterResObject = new JSONObject(response.asString());
        JSONArray internal_id = CreateCounterResObject.getJSONArray("internal_id");

        lifecycle.updateTestCase(testResult -> testResult.setName("Internal_ID Required   " + internal_id.getString(0))); 
        sa = assertionUtil.assertEquals("internal_id", 
        internal_id.getString(0), "This field may not be blank.", 
        "The Error Message doesnt match: ");
        sa.assertAll();
        
   
    }

    @Epic("Create CounterParty Regression")
    @Test(groups = { "regression" },description = "Not Found Error when invalid account number is passed in Get CounterParty Details API")    
    public void notFoundgetCounterPartyDetails() throws Exception{
        SoftAssert sa;
        AssertionUtil assertionUtil = new AssertionUtil();
        String service = apiEndpointConfig.getEndPoint("Counterparties", "GetCounterpartydetails") ;//apiConfiguration().createcounterpartendpoint();
        String json;
              
        lifecycle.updateTestCase(testResult -> testResult.setName("Verifying Error : Not Found for a wrong Account ID: " +accountDetailsData.get("id")+"12".toString() ));
        // Converting a Java class object to a JSON payload as string
	
	    json = getCounterPartyDetailsRequest.RetrieveCounterPartyDetails("90636e30-cdb9-4adb-a575-4a37bc48a1b212");
        
        Response response = rest.getAuthenticatedGETReponseWithNoBody(json,service ,_apibaseurlEndPoint);

        JSONObject counterpartyObj = new JSONObject(response.asString());
        
       jsonFormatter.printFormattedJson(response.asString(), "response", service);

       
     
        sa = assertionUtil.assertEquals("Response Code",response.getStatusCode(), 404, "The Status Code do not Match");
        sa = assertionUtil.assertEquals("Erroe Message", counterpartyObj.getString("detail"), "Not found.", "The Error Message do not Match");        
         sa.assertAll();
        
   
    }

    @Epic("Create CounterParty Regression")
    @Test(groups = { "regression" },description = "Not Found Error when invalid account number is passed in Update CounterParty Details API")  
    public void updateCounterPart() throws Exception{
        SoftAssert sa;
        AssertionUtil assertionUtil = new AssertionUtil();
       String service = apiEndpointConfig.getEndPoint("Counterparties", "UpdateCounterparty") ; //apiConfiguration().counterpartendpoint();
       String pathParam = service+accountDetailsData.get("id")+"12/";
       lifecycle.updateTestCase(testResult -> testResult.setName("Verifying Error : Not Found for a wrong Account ID: " + accountDetailsData.get("id")));

        Createcounterpart updatecounterparty = new Createcounterpart();

        updatecounterparty.setName(applicationData.get("CompanyName"));
        updatecounterparty.setType("Supplier");
        updatecounterparty.setPrimaryContactEmail(applicationData.get("Email"));
        updatecounterparty.setPrimaryContactName(applicationData.get("FirstName"));
        updatecounterparty.setPrimaryContactPhone(applicationData.get("Phone"));
        updatecounterparty.setBankAccountNumber(applicationData.get("BankAccountNo"));
        updatecounterparty.setBankRoutingNumber("052001633");
        updatecounterparty.setInternalId(internalID);
        
        
        // Converting a Java class object to a JSON payload as string
	
	    String updateecounterpartJson = jsonFormatter.formatStringFromJsonPOJO(updatecounterparty);
        jsonFormatter.printFormattedJson(updateecounterpartJson, "request", pathParam);

        Response response = rest.getAuthenticatedPUTReponseWithBody(updateecounterpartJson, pathParam,
                                _apibaseurlEndPoint);            

       JSONObject counterpartyObj = new JSONObject(response.asString());
        
       jsonFormatter.printFormattedJson(response.asString(), "response", pathParam);

       
     
        sa = assertionUtil.assertEquals("Response Code",response.getStatusCode(), 404, "The Status Code do not Match");
        sa = assertionUtil.assertEquals("Erroe Message", counterpartyObj.getString("detail"), "Not found.", "The Error Message do not Match");        
        sa.assertAll();

        
        
   
    }    
 
   
}
