package org.example.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RegistrationPage {
    private final WebDriver driver;
    //локатор для поля имя
    private final By userNameReg = By.xpath(".//fieldset[1]/div/div/input");
    //локатор для поля емаил
    private final By userEmailReg  = By.xpath(".//fieldset[2]/div/div/input");
    //локатор для поля пароль
    private final By userPasswordReg = By.xpath(".//fieldset[3]/div/div/input");
    //локатор для кнопки регистрации
    private final By registrationButton = By.xpath(".//form/button");
    //локатор для текста ошибки
    private final By errorMessageText = By.xpath(".//fieldset[3]/div/p[contains(text(), 'Некорректный пароль')]");
    //локатор для кнопки войти в форме регистрации
    private final By authLinkByRegForm = By.xpath(".//div/main/div/div/p/a");

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
        driver.findElement(userNameReg).clear();
        driver.findElement(userNameReg).sendKeys(name);
        driver.findElement(userEmailReg).clear();
        driver.findElement(userEmailReg).sendKeys(email);
        driver.findElement(userPasswordReg).clear();
        driver.findElement(userPasswordReg).sendKeys(password);
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
