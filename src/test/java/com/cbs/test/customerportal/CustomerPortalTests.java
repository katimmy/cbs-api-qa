package com.cbs.test.customerportal;

import org.testng.annotations.Test;

import com.cbs.BaseWeb;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Link;
import io.qameta.allure.Story;

public class CustomerPortalTests extends BaseWeb {


@Epic("Providers Experience")
    @Link(value = "", name = "", url = "", type = "")
    @Description("The ability of a providert  to create a service")
    @Test(groups = {
            "PatientPortal" }, description = "TC-2000 ::: Verify The ability of a providert  to create a service")
    @Story("HFD-12356 : The ability of a providert  to create a service")
    public void loginintopaymentportal() throws Exception {

       // SoftAssert sa;

       // AssertionUtil assertionUtil = new AssertionUtil();
        openCustomerPortal();
        System.out.println("The User is "+_username);
        System.out.println("The Password is "+_password);
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
