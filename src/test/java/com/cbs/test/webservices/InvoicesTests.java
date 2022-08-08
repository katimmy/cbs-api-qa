package com.cbs.test.webservices;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.cbs.BaseApi;
import com.cbs.pojos.gen.Responses.getInvoiceDetails.GetInvoiceDetailsResponse;
import com.cbs.utils.api.JsonFormatter;
import com.cbs.utils.api.LogListener;
import com.cbs.utils.api.RestUtils;
import com.cbs.utils.assertion.AssertionUtil;
import com.cbs.webservices.Counterparties.GetCounterPartyDetailsRequest;
import com.cbs.webservices.Invoices.ListAllInvoicesRequest;

import com.github.javafaker.Faker;

import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import io.qameta.allure.Epic;
import io.restassured.response.Response;

@Listeners({LogListener.class})
public class InvoicesTests extends BaseApi{
   
    RestUtils rest = new RestUtils();
    JsonFormatter jsonFormatter = new JsonFormatter();
    Map<String, String> applicationData = getApplicationData();
    Map<Object, Object> accountDetailsData = new HashMap<>();
    AllureLifecycle lifecycle = Allure.getLifecycle();
    SoftAssert sa = new SoftAssert();
    Faker faker = new Faker();
    GetCounterPartyDetailsRequest getCounterPartyDetailsRequest = new GetCounterPartyDetailsRequest();
    ListAllInvoicesRequest listInvoices = new ListAllInvoicesRequest();
    Map<String, Object> invoiceListings = new HashMap<>();

   public String internalID;



   
    @Epic("Invoices APIS")
    @Test(priority = 0, groups = { "Invoices" })
    public void getListOfInvoices() throws Exception{
        SoftAssert sa;
        AssertionUtil assertionUtil = new AssertionUtil();
      
        String service = apiEndpointConfig.getEndPoint("Invoices", "ListInvoices") ;//apiConfiguration().createcounterpartendpoint();
        String json;
              
        lifecycle.updateTestCase(testResult -> testResult.setName("Get List of All Invoices per Buyer: " ));
        // Converting a Java class object to a JSON payload as string
	
	    json = listInvoices.listAllInvoices(1);
        
        Response response = rest.getAuthenticatedGETReponseWithNoBody(json,service ,_apibaseurlEndPoint);
        
       jsonFormatter.printFormattedJson(response.asString(), "response", service);
       JSONObject listOfInvoicesObject = new JSONObject(response.asString());
       JSONArray invoicesList = listOfInvoicesObject.getJSONArray("results");
       ArrayList<String> invoices = new ArrayList<String>();

       Iterator<Object> iterator = invoicesList.iterator();
                while (iterator.hasNext()) {
                        JSONObject jsonObject = (JSONObject) iterator.next();

                        for (String key : jsonObject.keySet()) {

                            invoiceListings.put(key, jsonObject.get(key));

                        }
                        invoices.add((String) invoiceListings.get("id"));

                }

         System.out.println("All of the Invoices is: "+ invoiceListings);

        // CreateCouterpartResponse couterparty  = response.as(CreateCouterpartResponse.class);

        sa = assertionUtil.assertEquals("Status", response.getStatusCode(), 200, "The Status Code do not Match");
        // sa = assertionUtil.assertEquals("Type", couterparty.getType(), accountDetailsData.get("type"), "The Type do not Match");
        // sa = assertionUtil.assertEquals("Supplier Address", couterparty.getAddress1(), accountDetailsData.get("address_1"), "The Address do not Match");
        // sa = assertionUtil.assertEquals("Supplier City", couterparty.getCity(), accountDetailsData.get("city"), "The City do not Match");
        // sa = assertionUtil.assertEquals("Supplier State", couterparty.getState(), accountDetailsData.get("state"), "The State do not Match");
        // sa = assertionUtil.assertEquals("Supplier Zipcode", couterparty.getZipcode(), accountDetailsData.get("zipcode"), "The Names do not Match");
        // sa = assertionUtil.assertEquals("Internal ID", couterparty.getInternalId(), accountDetailsData.get("internal_id"), "The Names do not Match");
         sa.assertAll();
        
   
    }

    @Epic("Invoices APIS")
    @Test(priority = 1, groups = { "Invoices" })
    public void getInvoiceDetails() throws Exception{
        SoftAssert sa;
        AssertionUtil assertionUtil = new AssertionUtil();
        String service = apiEndpointConfig.getEndPoint("Invoices", "GetInvoicedetails") ;//apiConfiguration().createcounterpartendpoint();
        String json;
              
        lifecycle.updateTestCase(testResult -> testResult.setName("Get Details of Invoice ID: " + invoiceListings.get("id")));
        // Converting a Java class object to a JSON payload as string
	
	    json = getCounterPartyDetailsRequest.RetrieveCounterPartyDetails(invoiceListings.get("id").toString());
        
        Response response = rest.getAuthenticatedGETReponseWithNoBody(json,service ,_apibaseurlEndPoint);
        
       jsonFormatter.printFormattedJson(response.asString(), "response", service);

       GetInvoiceDetailsResponse invoicedetails  = response.as(GetInvoiceDetailsResponse.class);
       System.out.println("The savede url is : "+invoicedetails.getUrl() );

        sa = assertionUtil.assertEquals("Invoice ID", invoicedetails.getId(), invoiceListings.get("id"), "The Invoice ID do not Match");
        sa = assertionUtil.assertEquals("Url", invoicedetails.getUrl(), invoiceListings.get("url"), "The url do not Match");
        sa = assertionUtil.assertEquals("Amount_cents", invoicedetails.getAmountCents(), invoiceListings.get("amount_cents"), "The amount_cents do not Match");
        sa = assertionUtil.assertEquals("Invoice Date", invoicedetails.getInvoiceDate(), invoiceListings.get("invoice_date"), "The invoice_date do not Match");
        sa = assertionUtil.assertEquals("Invoice Number", invoicedetails.getInvoiceNumber(), invoiceListings.get("invoice_number"), "The invoice_number do not Match");
        sa = assertionUtil.assertEquals("Currency", invoicedetails.getCurrency(), invoiceListings.get("currency"), "The currency do not Match");
         sa.assertAll();
        
   
    }


    
   
}
