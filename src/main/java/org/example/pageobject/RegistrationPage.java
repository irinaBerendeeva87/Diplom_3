package org.example.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RegistrationPage {
    private final WebDriver driver;
    //локатор для поля имя
    private final By userField = By.xpath("//label[text()='Имя']/following-sibling::input");
    //локатор для поля емаил
    private final By emailField = By.xpath("//label[text()='Email']/following-sibling::input");
    //локатор для поля пароль
    private final By passwordField = By.xpath("//input[@type='password']");
    //локатор для кнопки регистрации
    private final By registrationButton = By.className("button_button__33qZ0");
    //локатор для текста ошибки
    private final By errorMessageText = By.xpath(".//fieldset[3]/div/p[contains(text(), 'Некорректный пароль')]");
    //локатор для кнопки войти в форме регистрации
    private final By authLinkByRegForm = By.className("Auth_link__1fOlj");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Open registration Page")
    public void openRegPage (){
        driver.get("https://stellarburgers.nomoreparties.site/register");
    }

    //создание юзера и регистрация
    @Step("Filling out and check the registration form")
    public void createNewUser(String name, String email, String password) {
        driver.findElement(userField).clear();
        driver.findElement(userField).sendKeys(name);
        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(registrationButton));
        driver.findElement(registrationButton).click();
    }

    //	вход через кнопку в форме регистрации
    @Step("Find And Click Authorization Link on registration Page")
    public void clickAuthLinkByRegForm (){
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(authLinkByRegForm));
        driver.findElement(authLinkByRegForm).click();
    }

    // отображение сообщения об ошибке пароля
    @Step("Find Error Message at registration form, check Error Message")
    public boolean getErrorMessage () {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(errorMessageText));
        return driver.findElement(errorMessageText).isDisplayed();
    }

    // текст ошибки
    @Step("Find And get Error Message text")
    public String getTextErrorMessage () {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(errorMessageText));
        return driver.findElement(errorMessageText).getText();
    }
}
