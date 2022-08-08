/**
 * @author IvanK
 * @email ivan.katumba@cbsinteractive.com
 * @create date 2022-06-03 15:51:01
 * @modify date 2022-06-03 15:51:01
 * @desc [description]
 */
package com.cbs.utils.api;

import static com.cbs.config.ConfigurationManager.apiConfiguration;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class RestUtils {

   
    
    @Step("Retrieving the POST Response for request made to service call {1}")
    public Response getAuthenticatedPOSTReponseBody(String json, String service, String endpoint) {

        RestAssured.baseURI = endpoint;
        System.out.println("The Env Tests Running off of is: " + endpoint);        
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.filter(new AllureRestAssured());
        httpRequest.auth().preemptive().basic(apiConfiguration().apiauthusername(),apiConfiguration().apiauthpsswd());
        httpRequest.header("Content-type", "application/json");
        httpRequest.header("Accept","application/json");  
        httpRequest.header("Partner","509802f2-be7f-4141-8730-174da342a94f");  
         
        
        httpRequest.body(json);
        Response response = httpRequest.request(Method.POST, service);
      
        return response;
    }

    @Step("Retrieving the Response for request made to service call {1}")
    public Response getAuthenticatedGETReponseWithNoBody(String json, String service, String endpoint) {

        RestAssured.baseURI = endpoint;
        
        String servicecall = service + json;  
        System.out.println("The Env Tests Running off is: " + endpoint+servicecall);           
        RequestSpecification httpRequest = RestAssured.given();
        //httpRequest.filter(new AllureRestAssured());
       // httpRequest.auth().preemptive().basic(apiConfiguration().apiauthusername(),apiConfiguration().apiauthpsswd());
        httpRequest.header("Content-type", "application/json");
        httpRequest.header("Accept","application/json");             
       
        Response response = httpRequest.request(Method.GET, servicecall);

        return response;
    }

    @Step("Retrieving the Response for request made to service call {1}")
    public Response getAuthenticatedPUTReponseWithBody(String json, String service, String endpoint) {

        RestAssured.baseURI = endpoint;
        
        
        System.out.println("The Env Tests Running off is: " + endpoint+service);           
        RequestSpecification httpRequest = RestAssured.given();
        //httpRequest.filter(new AllureRestAssured());
        httpRequest.auth().preemptive().basic(apiConfiguration().apiauthusername(),apiConfiguration().apiauthpsswd());
        httpRequest.header("Content-type", "application/json");
        httpRequest.header("Accept","application/json");
        httpRequest.header("Partner","509802f2-be7f-4141-8730-174da342a94f");  
        
        httpRequest.body(json);          
       
        Response response = httpRequest.request(Method.PUT, service);

        return response;
    }

    @Step("Retrieving the Response for request made to service call {1}")
    public Response getAuthenticatedPATCHReponseWithBody(String json, String service, String endpoint) {

        RestAssured.baseURI = endpoint;
        
        
        System.out.println("The Env Tests Running off is: " + endpoint+service);           
        RequestSpecification httpRequest = RestAssured.given();
        //httpRequest.filter(new AllureRestAssured());
        httpRequest.auth().preemptive().basic(apiConfiguration().apiauthusername(),apiConfiguration().apiauthpsswd());
        httpRequest.header("Content-Type", "application/json");
        httpRequest.header("Accept","application/json");
        httpRequest.header("Partner","509802f2-be7f-4141-8730-174da342a94f");  
        httpRequest.body(json);          
       
        Response response = httpRequest.request(Method.PATCH, service);

        return response;
    }

    @Step("Retrieving the Response for request made to service call {1}")
    public Response getAuthenticatedGETReponseWithoutBody(String service, String endpoint) {

        RestAssured.baseURI = endpoint;
        
        String servicecall = service;  
        System.out.println("The Env Tests Running off is: " + endpoint+servicecall);           
        RequestSpecification httpRequest = RestAssured.given();
        //httpRequest.filter(new AllureRestAssured());
        // httpRequest.auth().preemptive().basic(apiConfiguration().oktaapiauthusername(),apiConfiguration().oktaapiauthpsswd());
        httpRequest.header("Content-type", "application/json");
        httpRequest.header("Accept","application/json");              
       
        Response response = httpRequest.request(Method.GET, servicecall);

        return response;
    }



}
