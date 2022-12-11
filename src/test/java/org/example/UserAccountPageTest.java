package org.example;

import io.qameta.allure.junit4.DisplayName;
import org.example.pageobject.AuthorizationPage;
import org.example.pageobject.MainPage;
import org.example.pageobject.RegistrationPage;
import org.example.pageobject.UserAccountPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.Assert.assertEquals;

public class UserAccountPageTest {
    RegistrationPage objRegistrationPage;
    AuthorizationPage objAuthorizationPage;
    MainPage objMainPage;
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
//         for testing in Yandex Browser
//        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
//        ChromeOptions options = new ChromeOptions();
//        options.setBinary("/Applications/Yandex.app/Contents/MacOS/Yandex");
//        driver = new ChromeDriver(options);
        User user = new User();
        name = user.getRandomName();
        email = user.getRandomEmail();
        password = user.getRandomPassword();
        objRegistrationPage = new RegistrationPage(driver);// создание объекта класса страницы регистрации
        objRegistrationPage.openRegPage();
        objRegistrationPage.createNewUser(name,email,password);
        objAuthorizationPage = new AuthorizationPage(driver);
        objMainPage = new MainPage(driver);// создание объекта класса main страницы
    }

    @After
    public void teardown() {
        driver.quit();
    }

    @Test
    @DisplayName("check enter in Personal Account")
    public void checkPersonalAccountLinkTest() {
        objAuthorizationPage.authorization(email, password);
        objMainPage.clickUserAccountButton();
        UserAccountPage objUserAccountPage = new UserAccountPage(driver);
        assertEquals("Entering was  Failed", "Выход", objUserAccountPage.checkLogInPersonalAccount());
    }

    @Test
    @DisplayName("check exit from Account by Link at Personal Account")
    public void checkExitButtonTest() {
        objAuthorizationPage.authorization(email, password);
        objMainPage.clickUserAccountButton();
        UserAccountPage objPersonalAccountPage = new UserAccountPage(driver);
        objPersonalAccountPage.clickExitButton();
        assertEquals("ExitFailed", "Войти", objAuthorizationPage.checkLoginButton());
    }

    @Test
    @DisplayName("check exit to MainPage by Logo at Personal Account")
    public void checkLogoButtonTest() {
        objAuthorizationPage.authorization(email, password);
        objMainPage.clickUserAccountButton();
        UserAccountPage objPersonalAccountPage = new UserAccountPage(driver);
        objPersonalAccountPage.clickLogoButton();
        assertEquals("LogoButtonFailed", "Оформить заказ", objMainPage.textOrderButton());
    }

    @Test
    @DisplayName("check exit to MainPage by Constructor Button at Personal Account")
    public void checkConstructorButtonTest() {
        objAuthorizationPage.authorization(email, password);
        objMainPage.clickUserAccountButton();
        UserAccountPage objPersonalAccountPage = new UserAccountPage(driver);
        objPersonalAccountPage.clickConstructorButton();
        assertEquals("ConstructorButtonFailed", "Оформить заказ", objMainPage.textOrderButton());
    }
}
