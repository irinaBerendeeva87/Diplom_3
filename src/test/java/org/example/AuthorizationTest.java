package org.example;

import io.qameta.allure.junit4.DisplayName;
import org.example.pageobject.AuthorizationPage;
import org.example.pageobject.MainPage;
import org.example.pageobject.RegistrationPage;
import org.example.pageobject.RestorePasswordPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import static org.junit.Assert.assertEquals;

public class AuthorizationTest {
    RegistrationPage objRegistrationPage;
    AuthorizationPage objAuthorizationPage;
    WebDriver driver;
    private String name;
    private String email;
    private String password;

    @Before
    public void before() {
        // for testing in Chrome Browser
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        // for testing in Yandex Browser
//        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
//        ChromeOptions options = new ChromeOptions();
//        options.setBinary("/Applications/Yandex.app/Contents/MacOS/Yandex");
//        driver = new ChromeDriver(options);
        User user = new User();//creating an object of the User class
        name = user.getRandomName(); // creating  random name
        email = user.getRandomEmail(); // creating  random email
        password = user.getRandomPassword();// creating  random password
        objRegistrationPage = new RegistrationPage(driver);// creating an object of the registration page class
        objRegistrationPage.openRegPage(); //opening  registration page
        objRegistrationPage.createNewUser(name,email,password);//creating a new user and click registration button
    }

    @After
    public void teardown() {
        driver.quit();
    }

    @Test
    @DisplayName("check Authorization path by Authorization Button at MainPage")
    public void mainPageAuthorizationButtonTest() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.openMainPage();
        objMainPage.clickAuthorizationButton();
        objAuthorizationPage = new AuthorizationPage(driver);
        objAuthorizationPage.authorization(email, password);
        assertEquals("Authorization was Failed", "Оформить заказ", objMainPage.textOrderButton());
    }

    @Test
    @DisplayName("Check authorization path by Personal Account button at Main page")
    public void authorizationByPersonalAccountTest() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.openMainPage();
        objMainPage.clickUserAccountButton();
        objAuthorizationPage = new AuthorizationPage(driver);
        objAuthorizationPage.authorization(email,password);
        assertEquals("Authorization was Failed", "Оформить заказ", objMainPage.textOrderButton());
    }

    @Test
    @DisplayName("check Authorization path by Authorization Link at Registration Page")
    public void authorizationByRegistrationLinkTest() {
        objRegistrationPage.openRegPage();
        objRegistrationPage.clickAuthLinkByRegForm();
        AuthorizationPage objAuthorizationPage = new AuthorizationPage(driver);
        objAuthorizationPage.authorization(email,password);
        MainPage objMainPage = new MainPage(driver);
        assertEquals("Authorization was Failed", "Оформить заказ", objMainPage.textOrderButton());
    }

    @Test
    @DisplayName("check Authorization path by Authorization Link at ResetPassword Page")
    public void authorizationByRestorePasswordLinkTest() {
        RestorePasswordPage objRestorePasswordPage = new RestorePasswordPage(driver);
        objRestorePasswordPage.openRestorePage();
        objRestorePasswordPage.clickAuthLinkByRestorePassForm();
        AuthorizationPage objAuthorizationPage = new AuthorizationPage(driver);
        objAuthorizationPage.authorization(email,password);
        MainPage objMainPage = new MainPage(driver);
        assertEquals("Authorization was Failed", "Оформить заказ", objMainPage.textOrderButton());
    }
}

