package org.example.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UserAccountPage {
    private final WebDriver driver;
    private final By exitButton = By.xpath("/html/body/div[1]/div/main/div/nav/ul/li[3]/button");
    private final By logoButton = By.xpath("/html/body/div[1]/div/header/nav/div/a");
    private final By constructorButton = By.xpath("/html/body/div[1]/div/header/nav/ul/li[1]/a/p");

    public UserAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    //	выход из аккаунта через кнопку 'выход' в личном кабинете
    @Step("Find And Click exit button on personal account")
    public void clickExitButton() {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(exitButton));
        driver.findElement(exitButton).click();
    }

    //	переход на главную страницу через символ логотипа в личном кабинете
    @Step("Find And Click logo on personal account")
    public void clickLogoButton() {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(logoButton));
        driver.findElement(logoButton).click();
    }

    //	переход в раздел конструктор на главной странице через ссылку в личном кабинете
    @Step("Find And Click Constructor Button on personal account")
    public void clickConstructorButton() {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(constructorButton));
        driver.findElement(constructorButton).click();
    }

    @Step("Find And get text from exit Button on personal account")
    public String checkLogInPersonalAccount() {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(exitButton));
        return driver.findElement(exitButton).getText();
    }
}
