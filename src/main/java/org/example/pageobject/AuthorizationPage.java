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
    private final By emailField = By.xpath("//label[text()='Email']/following-sibling::input");
    //  локатор для поля Email
    private final By passwordField = By.xpath("//input[@type='password']");
    //  локатор для кнопки Войти
    private final By loginButton = By.className("button_button__33qZ0");

    //метод авторизации
    @Step("Filling out and check the authorization form")
    public void authorization (String email, String password){
        try{
            Thread.sleep(1000);
        }
        catch(InterruptedException ie){
        }
        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
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
