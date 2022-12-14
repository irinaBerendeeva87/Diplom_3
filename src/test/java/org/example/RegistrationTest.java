package org.example;
import io.qameta.allure.junit4.DisplayName;
import org.example.pageobject.AuthorizationPage;
import org.example.pageobject.MainPage;
import org.example.pageobject.RegistrationPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class RegistrationTest {
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
        User user = new User();
        name = user.getRandomName();
        email = user.getRandomEmail();
        password = user.getRandomPassword();
        objRegistrationPage = new RegistrationPage(driver); // создание объекта класса страницы регистрации
    }

    @Test
    @DisplayName("check Registration - creating user")
    public void checkRegistrationTest() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        objRegistrationPage.openRegPage();
        objRegistrationPage.createNewUser(name,email,password);
        objAuthorizationPage = new AuthorizationPage(driver);
        objAuthorizationPage.authorization(email, password);
        MainPage objMainPage = new MainPage(driver);
        assertEquals("Registration was Failed", "Оформить заказ", objMainPage.textOrderButton());
    }

    @Test
    @DisplayName("check Registration - check Error Message")
    public void checkErrorMassageTest() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        objRegistrationPage.openRegPage();
        objRegistrationPage.createNewUser(name,email,"12345");
        objRegistrationPage.getErrorMessage();
        assertEquals("Registration was Failed", "Некорректный пароль", objRegistrationPage.getTextErrorMessage());
    }

    @After
    public void teardown() {
        driver.quit();
    }
}

