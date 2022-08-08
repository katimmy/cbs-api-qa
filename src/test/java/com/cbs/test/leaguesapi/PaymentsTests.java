package com.cbs.test.leaguesapi;

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
import com.cbs.utils.api.JsonFormatter;
import com.cbs.utils.api.LogListener;
import com.cbs.utils.api.RestUtils;
import com.cbs.utils.assertion.AssertionUtil;
import com.cbs.webservices.Payments.GetPaymentsRequest;
import com.github.javafaker.Faker;

import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import io.qameta.allure.Epic;
import io.restassured.response.Response;

@Listeners({LogListener.class})
public class PaymentsTests extends BaseApi {

    RestUtils rest = new RestUtils();
    JsonFormatter jsonFormatter = new JsonFormatter();
    AllureLifecycle lifecycle = Allure.getLifecycle();
    SoftAssert sa = new SoftAssert();
    Faker faker = new Faker();
   GetPaymentsRequest getPaymentsRequest = new GetPaymentsRequest();
    Map<String, Object> paymentListings = new HashMap<>();


    @Epic("Payments APIS")
    @Test(priority = 0, groups = { "Invoices" })
    public void getListOfInvoices() throws Exception{
        SoftAssert sa;
        AssertionUtil assertionUtil = new AssertionUtil();
      
        String service = apiEndpointConfig.getEndPoint("Payments", "ListPayments") ;//apiConfiguration().createcounterpartendpoint();
        String json;
              
        lifecycle.updateTestCase(testResult -> testResult.setName("Get List of All Payments per Buyer: " ));
        // Converting a Java class object to a JSON payload as string
	
	    json = getPaymentsRequest.getPaymentsList(1);
        
        Response response = rest.getAuthenticatedGETReponseWithNoBody(json,service ,_apibaseurlEndPoint);
        
       jsonFormatter.printFormattedJson(response.asString(), "response", service);
       JSONObject listOfInvoicesObject = new JSONObject(response.asString());
       JSONArray invoicesList = listOfInvoicesObject.getJSONArray("results");
       ArrayList<String> payments = new ArrayList<String>();

       Iterator<Object> iterator = invoicesList.iterator();
                while (iterator.hasNext()) {
                        JSONObject jsonObject = (JSONObject) iterator.next();

                        for (String key : jsonObject.keySet()) {

                            paymentListings.put(key, jsonObject.get(key));

                        }
                        payments.add((String) paymentListings.get("id"));

                }

         System.out.println("The Extracted Payment is : "+ jsonFormatter.formatStringFromJsonPOJO(paymentListings));

         
        sa = assertionUtil.assertEquals("Status", response.getStatusCode(), 200, "The Status Code do not Match");
        // sa = assertionUtil.assertEquals("Type", couterparty.getType(), accountDetailsData.get("type"), "The Type do not Match");
        // sa = assertionUtil.assertEquals("Supplier Address", couterparty.getAddress1(), accountDetailsData.get("address_1"), "The Address do not Match");
        // sa = assertionUtil.assertEquals("Supplier City", couterparty.getCity(), accountDetailsData.get("city"), "The City do not Match");
        // sa = assertionUtil.assertEquals("Supplier State", couterparty.getState(), accountDetailsData.get("state"), "The State do not Match");
        // sa = assertionUtil.assertEquals("Supplier Zipcode", couterparty.getZipcode(), accountDetailsData.get("zipcode"), "The Names do not Match");
        // sa = assertionUtil.assertEquals("Internal ID", couterparty.getInternalId(), accountDetailsData.get("internal_id"), "The Names do not Match");
         sa.assertAll();
        
   
    }
}
