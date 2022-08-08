package com.cbs.webservices.Payments;

import io.qameta.allure.Step;

public class GetPaymentsRequest {

    @Step("Retrieving : {0} pages of all Payments")
    public String getPaymentsList(int pageNo) {
        String json;
        json = String.format("?page=%s", pageNo);
        return json;

    }
    
}
