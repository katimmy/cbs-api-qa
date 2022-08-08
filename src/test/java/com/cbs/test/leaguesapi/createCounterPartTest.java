package com.cbs.test.leaguesapi;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.cbs.BaseApi;
import com.cbs.pojos.gen.Responses.CreateCouterpartResponse;
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
import io.qameta.allure.Link;
import io.restassured.response.Response;

@Listeners({LogListener.class})
public class createCounterPartTest extends BaseApi{
   
    RestUtils rest = new RestUtils();
    JsonFormatter jsonFormatter = new JsonFormatter();
    Map<String, String> applicationData = getApplicationData();
    Map<Object, Object> accountDetailsData = new HashMap<>();
   AllureLifecycle lifecycle = Allure.getLifecycle();
    SoftAssert sa = new SoftAssert();
    Faker faker = new Faker();
    GetCounterPartyDetailsRequest getCounterPartyDetailsRequest = new GetCounterPartyDetailsRequest();

   public String internalID;
   private String _url = "";


    @Epic("Counter Party APIS")
    @Test(priority = 0, groups = { "Counterparty" })    
    public void createCounterPart() throws Exception{

        lifecycle.updateTestCase(testResult -> testResult.setName("Creating Counter Party For " + applicationData.get("FirstName")));
        SoftAssert sa;
        AssertionUtil assertionUtil = new AssertionUtil();
       String service = apiEndpointConfig.getEndPoint("Counterparties", "CreateCounterparty") ; //apiConfiguration().createcounterpartendpoint();
       //_url = apiEndpointConfig.

       
       internalID = applicationData.get("AccountNo");
        Createcounterpart createcounterpart = new Createcounterpart();
        
        createcounterpart.setName(applicationData.get("FirstName"));
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

        CreateCouterpartResponse couterparty  = response.as(CreateCouterpartResponse.class);
        accountDetailsData.put("id", couterparty.getId());
        accountDetailsData.put("name", createcounterpart.getName());
        accountDetailsData.put("type", createcounterpart.getType());
        accountDetailsData.put("address_1", createcounterpart.getAddress1());
        accountDetailsData.put("city", createcounterpart.getCity());
        accountDetailsData.put("state", createcounterpart.getState());
        accountDetailsData.put("zipcode", createcounterpart.getZipcode());
        accountDetailsData.put("country", createcounterpart.getCountry());        
        accountDetailsData.put("internal_id", internalID);
        sa = assertionUtil.assertEquals("Supplier Name", couterparty.getName(), createcounterpart.getName(), "The Names do not Match");
        sa = assertionUtil.assertEquals("Type", couterparty.getType(), createcounterpart.getType(), "The Type do not Match");
        sa = assertionUtil.assertEquals("Supplier Address", couterparty.getAddress1(), createcounterpart.getAddress1(), "The Address do not Match");
        sa = assertionUtil.assertEquals("Supplier City", couterparty.getCity(), createcounterpart.getCity(), "The City do not Match");
        sa = assertionUtil.assertEquals("Supplier State", couterparty.getState(), createcounterpart.getState(), "The State do not Match");
        sa = assertionUtil.assertEquals("Supplier Zipcode", couterparty.getZipcode(), createcounterpart.getZipcode(), "The Names do not Match");
        sa = assertionUtil.assertEquals("Internal ID", couterparty.getInternalId(), createcounterpart.getInternalId(), "The Names do not Match");

        //System.out.println(" The Payment Methode is : "+couterparty.getId());   

        sa.assertAll();
        
   
    }

    @Epic("Counter Party APIS")
    @Test(priority = 1, groups = { "Counterparty" })
    public void getCounterPartyDetails() throws Exception{
        SoftAssert sa;
        AssertionUtil assertionUtil = new AssertionUtil();
        String service = apiEndpointConfig.getEndPoint("Counterparties", "GetCounterpartydetails") ;//apiConfiguration().createcounterpartendpoint();
        String json;
              
        lifecycle.updateTestCase(testResult -> testResult.setName("Get Details of Counter Party for ID: " +accountDetailsData.get("id").toString() ));
        // Converting a Java class object to a JSON payload as string
	
	    json = getCounterPartyDetailsRequest.RetrieveCounterPartyDetails(accountDetailsData.get("id").toString());
        
        Response response = rest.getAuthenticatedGETReponseWithNoBody(json,service ,_apibaseurlEndPoint);
        
       jsonFormatter.printFormattedJson(response.asString(), "response", service);

        CreateCouterpartResponse couterparty  = response.as(CreateCouterpartResponse.class);

        sa = assertionUtil.assertEquals("Supplier Name", couterparty.getName(), accountDetailsData.get("name"), "The Names do not Match");
        sa = assertionUtil.assertEquals("Type", couterparty.getType(), accountDetailsData.get("type"), "The Type do not Match");
        sa = assertionUtil.assertEquals("Supplier Address", couterparty.getAddress1(), accountDetailsData.get("address_1"), "The Address do not Match");
        sa = assertionUtil.assertEquals("Supplier City", couterparty.getCity(), accountDetailsData.get("city"), "The City do not Match");
        sa = assertionUtil.assertEquals("Supplier State", couterparty.getState(), accountDetailsData.get("state"), "The State do not Match");
        sa = assertionUtil.assertEquals("Supplier Zipcode", couterparty.getZipcode(), accountDetailsData.get("zipcode"), "The Names do not Match");
        sa = assertionUtil.assertEquals("Internal ID", couterparty.getInternalId(), accountDetailsData.get("internal_id"), "The Names do not Match");
         sa.assertAll();
        
   
    }

    @Epic("Counter Party APIS")
    @Test(priority = 2, groups = { "Counterparty" })
    public void updateCounterPart() throws Exception{
        SoftAssert sa;
        AssertionUtil assertionUtil = new AssertionUtil();
       String service = apiEndpointConfig.getEndPoint("Counterparties", "UpdateCounterparty") ; //apiConfiguration().counterpartendpoint();
       String pathParam = service+accountDetailsData.get("id")+"/";
       lifecycle.updateTestCase(testResult -> testResult.setName("Updating  Counter Party Details for  " + accountDetailsData.get("id")));

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
        
       jsonFormatter.printFormattedJson(response.asString(), "response", pathParam);

        CreateCouterpartResponse couterparty  = response.as(CreateCouterpartResponse.class);
        accountDetailsData.put("id", couterparty.getId());
        accountDetailsData.put("name", updatecounterparty.getName());
        accountDetailsData.put("type", updatecounterparty.getType());       
        accountDetailsData.put("internal_id", updatecounterparty.getInternalId());

        accountDetailsData.put("primary_contact_email", updatecounterparty.getPrimaryContactEmail());
        accountDetailsData.put("remit_email", updatecounterparty.getRemitEmail());
        accountDetailsData.put("primary_contact_name", updatecounterparty.getPrimaryContactName());
        accountDetailsData.put("primary_contact_phone", updatecounterparty.getPrimaryContactPhone());
        accountDetailsData.put("bank_account_number", updatecounterparty.getBankAccountNumber());
        accountDetailsData.put("bank_routing_number", updatecounterparty.getBankRoutingNumber());
        
       

        sa = assertionUtil.assertEquals("Supplier Name", couterparty.getName(), accountDetailsData.get("name"), "The Names do not Match");
        sa = assertionUtil.assertEquals("Type", couterparty.getType(), updatecounterparty.getType(), "The Type do not Match");
        sa = assertionUtil.assertEquals("Supplier Address", couterparty.getAddress1(), accountDetailsData.get("address_1"), "The Address do not Match");
        sa = assertionUtil.assertEquals("Supplier City", couterparty.getCity(), accountDetailsData.get("city"), "The City do not Match");
        sa = assertionUtil.assertEquals("Supplier State", couterparty.getState(), accountDetailsData.get("state"), "The State do not Match");
        sa = assertionUtil.assertEquals("Supplier Zipcode", couterparty.getZipcode(), accountDetailsData.get("zipcode"), "The ZipCode do not Match");
        sa = assertionUtil.assertEquals("Internal ID", couterparty.getInternalId(), accountDetailsData.get("internal_id"), "The InternalID do not Match");

        //System.out.println(" The Payment Methode is : "+couterparty.getId());   
       
       

        sa.assertAll();
        
   
    }

    @Epic("Counter Party APIS")
    @Link(value = "2zpzkgv", name = "Defect : PUT - Update Counterparty Details - internalID required when it shouldn't be.", type = "clickup")
    @Test(priority = 3, groups = { "Counterparty" })
    public void getUpdatedCounterPartyDetails() throws Exception{

        lifecycle.updateTestCase(testResult -> testResult.setName("Retrieving Details of Updated Counter Party for ID: " +accountDetailsData.get("id").toString() ));
        SoftAssert sa;
        AssertionUtil assertionUtil = new AssertionUtil();
        String service = apiEndpointConfig.getEndPoint("Counterparties", "GetCounterpartydetails") ; //apiConfiguration().createcounterpartendpoint();
        String json;
              
       
        // Converting a Java class object to a JSON payload as string
	
	    json = getCounterPartyDetailsRequest.RetrieveCounterPartyDetails(accountDetailsData.get("id").toString());
        
        Response response = rest.getAuthenticatedGETReponseWithNoBody(json,service ,envparam.get("baseEnv"));
        
       jsonFormatter.printFormattedJson(response.asString(), "response", service);

        CreateCouterpartResponse couterparty  = response.as(CreateCouterpartResponse.class);

        sa = assertionUtil.assertEquals("Supplier Name", couterparty.getName(), accountDetailsData.get("CompanyName"), "The Names do not Match");
        sa = assertionUtil.assertEquals("Type", couterparty.getType(), accountDetailsData.get("type"), "The Type do not Match");
        sa = assertionUtil.assertEquals("Supplier Address", couterparty.getAddress1(), accountDetailsData.get("address_1"), "The Address do not Match");
        sa = assertionUtil.assertEquals("Supplier City", couterparty.getCity(), accountDetailsData.get("city"), "The City do not Match");
        sa = assertionUtil.assertEquals("Supplier State", couterparty.getState(), accountDetailsData.get("state"), "The State do not Match");
        sa = assertionUtil.assertEquals("Supplier Zipcode", couterparty.getZipcode(), accountDetailsData.get("zipcode"), "The ZipCode do not Match");
        sa = assertionUtil.assertEquals("Internal ID", couterparty.getInternalId(), accountDetailsData.get("internal_id"), "The InternalID do not Match");
         sa.assertAll();
        
   
    }

    @Epic("Counter Party APIS")
   
    @Test(priority = 4, groups = { "Counterparty" })
    public void patialUpdateToCounterPart() throws Exception{
        SoftAssert sa;
        AssertionUtil assertionUtil = new AssertionUtil();
       String service = apiEndpointConfig.getEndPoint("Counterparties", "UpdateCounterparty") ; //apiConfiguration().counterpartendpoint();
       String pathParam = service+accountDetailsData.get("id")+"/";
       lifecycle.updateTestCase(testResult -> testResult.setName("Partially Updating  Counter Party Details for  " + accountDetailsData.get("id")));

        Createcounterpart updatecounterparty = new Createcounterpart();

               
        updatecounterparty.setPrimaryContactEmail("fake@email.com");
        updatecounterparty.setPrimaryContactName("Latest PartialUpdate");
        updatecounterparty.setRemitEmail(faker.internet().emailAddress());
        
       // updatecounterparty.setRemitEmail(faker.internet().emailAddress());
        
        
        
        // Converting a Java class object to a JSON payload as string
	
	    String updateecounterpartJson = jsonFormatter.formatStringFromJsonPOJO(updatecounterparty);
        jsonFormatter.printFormattedJson(updateecounterpartJson, "request", pathParam);

        Response response = rest.getAuthenticatedPATCHReponseWithBody(updateecounterpartJson, pathParam,
                                _apibaseurlEndPoint);
        
       jsonFormatter.printFormattedJson(response.asString(), "response", pathParam);

        // CreateCouterpartResponse couterparty  = response.as(CreateCouterpartResponse.class);
        // accountDetailsData.put("id", couterparty.getId());
        // accountDetailsData.put("name", createcounterpart.getName());
        // accountDetailsData.put("type", createcounterpart.getType());
        // accountDetailsData.put("address_1", createcounterpart.getAddress1());
        // accountDetailsData.put("city", createcounterpart.getCity());
        // accountDetailsData.put("state", createcounterpart.getState());
        // accountDetailsData.put("zipcode", createcounterpart.getZipcode());
        // accountDetailsData.put("country", createcounterpart.getCountry());        
        // accountDetailsData.put("internal_id", createcounterpart.getInternalId());
        // sa = assertionUtil.assertEquals("Supplier Name", couterparty.getName(), createcounterpart.getName(), "The Names do not Match");
        // sa = assertionUtil.assertEquals("Type", couterparty.getType(), createcounterpart.getType(), "The Type do not Match");
        // sa = assertionUtil.assertEquals("Supplier Address", couterparty.getAddress1(), createcounterpart.getAddress1(), "The Address do not Match");
        // sa = assertionUtil.assertEquals("Supplier City", couterparty.getCity(), createcounterpart.getCity(), "The City do not Match");
        // sa = assertionUtil.assertEquals("Supplier State", couterparty.getState(), createcounterpart.getState(), "The State do not Match");
        // sa = assertionUtil.assertEquals("Supplier Zipcode", couterparty.getZipcode(), createcounterpart.getZipcode(), "The Names do not Match");
        // sa = assertionUtil.assertEquals("Internal ID", couterparty.getInternalId(), createcounterpart.getInternalId(), "The Names do not Match");

        // //System.out.println(" The Payment Methode is : "+couterparty.getId());   

        // sa.assertAll();
        
   
    }

    @Epic("Counter Party APIS")
    @Test(priority = 5, groups = { "Counterparty" })
    public void getCounterPartyDetailsAfterPathialUpdate() throws Exception{
        SoftAssert sa;
        AssertionUtil assertionUtil = new AssertionUtil();
        String service = apiEndpointConfig.getEndPoint("Counterparties", "GetCounterpartydetails") ;//apiConfiguration().createcounterpartendpoint();
        String json;
              
        lifecycle.updateTestCase(testResult -> testResult.setName("Get Details for Counter Party for ID: " +accountDetailsData.get("id").toString()+" After Patial Update" ));
        // Converting a Java class object to a JSON payload as string
	
	    json = getCounterPartyDetailsRequest.RetrieveCounterPartyDetails(accountDetailsData.get("id").toString());
        
        Response response = rest.getAuthenticatedGETReponseWithNoBody(json,service ,_apibaseurlEndPoint);
        
       jsonFormatter.printFormattedJson(response.asString(), "response", service);

        CreateCouterpartResponse couterparty  = response.as(CreateCouterpartResponse.class);

        sa = assertionUtil.assertEquals("Supplier Name", couterparty.getName(), accountDetailsData.get("name"), "The Names do not Match");
        sa = assertionUtil.assertEquals("Type", couterparty.getType(), accountDetailsData.get("type"), "The Type do not Match");
        sa = assertionUtil.assertEquals("Supplier Address", couterparty.getAddress1(), accountDetailsData.get("address_1"), "The Address do not Match");
        sa = assertionUtil.assertEquals("Supplier City", couterparty.getCity(), accountDetailsData.get("city"), "The City do not Match");
        sa = assertionUtil.assertEquals("Supplier State", couterparty.getState(), accountDetailsData.get("state"), "The State do not Match");
        sa = assertionUtil.assertEquals("Supplier Zipcode", couterparty.getZipcode(), accountDetailsData.get("zipcode"), "The Names do not Match");
        sa = assertionUtil.assertEquals("Internal ID", couterparty.getInternalId(), accountDetailsData.get("internal_id"), "The Names do not Match");
         sa.assertAll();
        
   
    }



 
   
}
