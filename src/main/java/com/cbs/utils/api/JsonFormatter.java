/**
 * @author IvanK
 * @email ivan.katumba@cbsinteractive.com
 * @create date 2022-06-03 15:50:43
 * @modify date 2022-06-03 15:50:43
 * @desc [description]
 */
package com.cbs.utils.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Step;

public class JsonFormatter {

    /**
     *
     *
     */
    public String prettyJson,json;

    /**
     *
     * @param json
     * @param calltype is Request or Response
     * @return
     */
   @Step("Printing a well formated {1} for service call {2} into Json Format")
    public String printFormattedJson(String json, String  calltype, String servicename){

        ObjectMapper mapper = new ObjectMapper();

        try{

            Object jsonObject = mapper.readValue(json , Object.class);
            String prettyJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject);
            if(calltype.equalsIgnoreCase("request")){
                System.out.println("The "+ calltype+ " sent to service "+ servicename);
            } else {

                System.out.println("The "+ calltype+ " recieved from service call " +servicename);
            }
            System.out.println(prettyJson);
        }catch (Exception e) {

            e.printStackTrace();
        }
        return prettyJson;
    }


    @Step("Printing a well formated {1} for service call {2} into Json Format")
    public String formatStringFromJsonPOJO(Object value){
        String prettyJson = null;

        ObjectMapper mapper = new ObjectMapper();

        try{
       
            prettyJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(value);
           
            
        }catch (Exception e) {

            e.printStackTrace();
        }
        return prettyJson;
    }

}
