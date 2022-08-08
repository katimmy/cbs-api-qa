package com.cbs.utils.api;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import io.qameta.allure.Attachment;
import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;

public class LogListener implements ITestListener{
    private ByteArrayOutputStream request = new ByteArrayOutputStream();
    private ByteArrayOutputStream response = new ByteArrayOutputStream();
    private PrintStream requestVar = new PrintStream(request,true);
    private PrintStream responseVar = new PrintStream(response,true);


    public void onStart(final ITestContext iTestContext) {

        RestAssured.filters(new ResponseLoggingFilter(LogDetail.ALL, responseVar),
                new RequestLoggingFilter(LogDetail.ALL,requestVar));
    }

    public void onTestSuccess(final ITestResult iTestResult){
        logRequest(request);
        logResponse(response);

    }

    public void onTestFailure(final ITestResult iTestResult){

        logRequest(request);
        logResponse(response);
    }

    @Attachment(value = "request")
    public byte[] logRequest(final ByteArrayOutputStream stream){
        return attach(stream);
    }

    @Attachment(value = "response",type = "text/plain")
    public byte[] logResponse(final ByteArrayOutputStream stream){
        return attach(stream);
    }

    public byte[] attach(final ByteArrayOutputStream log){

        final byte[] array = log.toByteArray();
        log.reset();
        return array;
    }
    
}
