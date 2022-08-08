package com.cbs.webservices.Invoices;

import io.qameta.allure.Step;

public class ListAllInvoicesRequest {
    
    
    @Step("Retrieving : {0} pages of all Invoices")
    public String listAllInvoices(int pageNo) {
        String json;
        json = String.format("?page=%s", pageNo);
        return json;

    }
}
