/**
 * @author IvanK
 * @email ivan.katumba@cbsinteractive.com
 * @create date 2022-06-30 15:38:44
 * @modify date 2022-06-30 15:38:44
 * @desc [description]
 */
package com.cbs.test.orlandoportal;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.cbs.BaseWeb;
import com.cbs.TestListener;
import com.cbs.utils.assertion.AssertionUtil;
import com.cbs.utils.date.DateUtils;
import com.cbs.utils.number.NumberUtils;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Link;
import io.qameta.allure.Story;

@Listeners({ TestListener.class})
public class PortalTests  extends BaseWeb{

Logger logger = LogManager.getLogger(getClass().getName());
Map<String, String> applicationData = getApplicationData();
Map<String, String> servicesData = new HashMap<>();
NumberUtils numberUtils = new NumberUtils();
DateUtils dateUtils = new DateUtils();

    @Epic("Providers Experience")
    @Link(value = "", name = "", url = "", type = "")
    @Description("The ability of a providert  to create a service")
    @Test(groups = {
            "PatientPortal" }, description = "TC-2000 ::: Verify The ability of a providert  to create a service")
    @Story("HFD-12356 : The ability of a providert  to create a service")
    public void creatingandaddingaservice() throws Exception {

        SoftAssert sa;

        AssertionUtil assertionUtil = new AssertionUtil();
        startOrandoportal();
        // ProviderLogin providerLogin = new ProviderLogin();
        // NewServiceApplicationPage createservice = new NewServiceApplicationPage();
        // WelcomePage welcomePage = new WelcomePage();
        // CongulationsPage congulationsPage = new CongulationsPage();
        // ApplicationsListPage applicationsListPage = new ApplicationsListPage();
        // providerLogin.loginIntoProviderPortal(configuration().providerusername(), configuration().providerpassword());

        // sa = assertionUtil.assertEquals("Welcome Page Title", welcomePage.verifyPresenceOfHomePage(), "Welcome!",
        //         "The page title is wrong");
        // welcomePage.acceptCookiesPolicy();
        // welcomePage.createServives();

        // createservice.addService(serviceName, "2000", "Only Affordable to someone who can afford it.", "125");
        // welcomePage.viewallservices();

    }



    
}
