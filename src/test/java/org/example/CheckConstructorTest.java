package org.example;

import io.qameta.allure.junit4.DisplayName;
import org.example.pageobject.MainPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import static org.junit.Assert.assertTrue;

public class CheckConstructorTest {
    MainPage objMainPage;
    WebDriver driver;
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
        objMainPage = new MainPage(driver);
    }

    @After
    public void teardown() {
        driver.quit();
    }

    @Test
    @DisplayName("check Constructor Sauce Link at MainPage")
    public void checkSauceLink() {
        objMainPage.openMainPage();
        assertTrue("Error in section links-Sauce", objMainPage.checkSauceLinkDisplayed());
    }

    @Test
    @DisplayName("check Constructor Buns Link at MainPage")
    public void checkBunsLink() {
        objMainPage.openMainPage();
        assertTrue("Error in section Links-Buns", objMainPage.checkBunsLinkDisplayed());
    }

    @Test
    @DisplayName("check Constructor Stuffing Link at MainPage")
    public void checkStuffingLink() {
        objMainPage.openMainPage();
        assertTrue("Error in section Links-Stuffing", objMainPage.checkStuffingLinkDisplayed());
    }
}
