package org.example.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AuthorizationPage {
    private final WebDriver driver;

    public AuthorizationPage(WebDriver driver) {
        this.driver = driver;
    }

    //  локатор для поля Email
    private final By userEmailAuthField = By.xpath(".//form/fieldset[1]/div/div/input");
    //  локатор для поля Email
    private final By userPasswordAuthField = By.xpath(".//form/fieldset[2]/div/div/input");
    //  локатор для кнопки Войти
    private final By loginButton = By.xpath(".//div/main/div/form/button");

    //метод авторизации
    @Step("Filling out and check the authorization form")
    public void authorization (String email, String password){
        try{
            Thread.sleep(1000);
        }
        catch(InterruptedException ie){
        }
        driver.findElement(userEmailAuthField).clear();
        driver.findElement(userEmailAuthField).sendKeys(email);
        driver.findElement(userPasswordAuthField).clear();
        driver.findElement(userPasswordAuthField).sendKeys(password);
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(loginButton));
        driver.findElement(loginButton).click();
    }

//проверка текста на кнопке Войти
    @Step("Get text from authorization Button in authorizationPage")
    public String checkLoginButton () {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(loginButton));
        return driver.findElement(loginButton).getText();
    }
}
