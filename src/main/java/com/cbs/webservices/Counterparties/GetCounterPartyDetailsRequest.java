package com.cbs.webservices.Counterparties;

import io.qameta.allure.Step;

public class GetCounterPartyDetailsRequest {

    @Step("Retrieving Counter Party Details for CounterParty ID: :  {0}")
    public String RetrieveCounterPartyDetails(String counterpartyID) {
        String json;
        json = String.format("%s", counterpartyID);
        return json;

    }
    
}
