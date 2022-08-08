package com.cbs.webservices.Leagues;

import io.qameta.allure.Step;

public class GetLeagueDetailsRequest {

    @Step("Retrieving League Details for ID: :  {0}")
    public String RetrieveLeagueDetails(String leagueID) {
        String json;
        json = String.format("%s", leagueID);
        return json;

    }
    
}
