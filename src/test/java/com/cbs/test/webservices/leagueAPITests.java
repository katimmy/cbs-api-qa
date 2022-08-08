package com.cbs.test.webservices;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.cbs.BaseApi;
import com.cbs.utils.api.JsonFormatter;
import com.cbs.utils.api.LogListener;
import com.cbs.utils.api.RestUtils;
import com.cbs.utils.assertion.AssertionUtil;
import com.cbs.webservices.Leagues.GetLeagueDetailsRequest;
import com.github.javafaker.Faker;

import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import io.qameta.allure.Epic;
import io.restassured.response.Response;

@Listeners({LogListener.class})
public class leagueAPITests extends BaseApi {

    RestUtils rest = new RestUtils();
    JsonFormatter jsonFormatter = new JsonFormatter();
    Map<String, String> applicationData = getApplicationData();
    Map<Object, Object> accountDetailsData = new HashMap<>();
    AllureLifecycle lifecycle = Allure.getLifecycle();
    SoftAssert sa = new SoftAssert();
    Faker faker = new Faker();
    GetLeagueDetailsRequest getLeagueDetailsRequest = new GetLeagueDetailsRequest();
 
    @Epic("Leagues APIS")
    @Test(priority = 0, groups = { "Leagues" })
    public void getAllLeagues() throws Exception{
        SoftAssert sa;
        AssertionUtil assertionUtil = new AssertionUtil();
        String service = apiEndpointConfig.getEndPoint("Leagues", "GetAllLeagues") ;
        String json;
              
        lifecycle.updateTestCase(testResult -> testResult.setName("Getting all the Leagues: " ));
        // Converting a Java class object to a JSON payload as string
	
	   // json = getCounterPartyDetailsRequest.RetrieveCounterPartyDetails(invoiceListings.get("id").toString());
        
        Response response = rest.getAuthenticatedGETReponseWithoutBody(service ,_apibaseurlEndPoint);
        
       jsonFormatter.printFormattedJson(response.asString(), "response", service);

    //    GetInvoiceDetailsResponse invoicedetails  = response.as(GetInvoiceDetailsResponse.class);
    //    System.out.println("The savede url is : "+invoicedetails.getUrl() );
    sa = assertionUtil.assertEquals("Status", response.getStatusCode(), 200, "The Status Code do not Match");
    //     sa = assertionUtil.assertEquals("Invoice ID", invoicedetails.getId(), invoiceListings.get("id"), "The Invoice ID do not Match");
    //     sa = assertionUtil.assertEquals("Url", invoicedetails.getUrl(), invoiceListings.get("url"), "The url do not Match");
    //     sa = assertionUtil.assertEquals("Amount_cents", invoicedetails.getAmountCents(), invoiceListings.get("amount_cents"), "The amount_cents do not Match");
    //     sa = assertionUtil.assertEquals("Invoice Date", invoicedetails.getInvoiceDate(), invoiceListings.get("invoice_date"), "The invoice_date do not Match");
    //     sa = assertionUtil.assertEquals("Invoice Number", invoicedetails.getInvoiceNumber(), invoiceListings.get("invoice_number"), "The invoice_number do not Match");
    //     sa = assertionUtil.assertEquals("Currency", invoicedetails.getCurrency(), invoiceListings.get("currency"), "The currency do not Match");
     sa.assertAll();
        
   
    }

    @Epic("Leagues APIS")
    @Test(priority = 1, groups = { "Leagues" })
    public void getLeagueDetails() throws Exception{
        SoftAssert sa;
        AssertionUtil assertionUtil = new AssertionUtil();
        String service = apiEndpointConfig.getEndPoint("Leagues", "GetLeagueDetails") ;
        String json;
              
        lifecycle.updateTestCase(testResult -> testResult.setName("Get Details of League ID: 13311"));
        // Converting a Java class object to a JSON payload as string // accountDetailsData.get("id").toString() 
	
	    json = getLeagueDetailsRequest.RetrieveLeagueDetails("13311");
        
        Response response = rest.getAuthenticatedGETReponseWithNoBody(json,service ,_apibaseurlEndPoint);
        
        jsonFormatter.printFormattedJson(response.asString(), "response", service);

    //     CreateCouterpartResponse couterparty  = response.as(CreateCouterpartResponse.class);
    sa = assertionUtil.assertEquals("Status", response.getStatusCode(), 200, "The Status Code do not Match");
    //     sa = assertionUtil.assertEquals("Supplier Name", couterparty.getName(), accountDetailsData.get("name"), "The Names do not Match");
    //     sa = assertionUtil.assertEquals("Type", couterparty.getType(), accountDetailsData.get("type"), "The Type do not Match");
    //     sa = assertionUtil.assertEquals("Supplier Address", couterparty.getAddress1(), accountDetailsData.get("address_1"), "The Address do not Match");
    //     sa = assertionUtil.assertEquals("Supplier City", couterparty.getCity(), accountDetailsData.get("city"), "The City do not Match");
    //     sa = assertionUtil.assertEquals("Supplier State", couterparty.getState(), accountDetailsData.get("state"), "The State do not Match");
    //     sa = assertionUtil.assertEquals("Supplier Zipcode", couterparty.getZipcode(), accountDetailsData.get("zipcode"), "The Names do not Match");
    //     sa = assertionUtil.assertEquals("Internal ID", couterparty.getInternalId(), accountDetailsData.get("internal_id"), "The Names do not Match");
         sa.assertAll();
        
   
    }

    
}
