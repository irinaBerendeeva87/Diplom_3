package org.example.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UserAccountPage {
    private final WebDriver driver;
    private final By logoutButton = By.className("Account_button__14Yp3");
    private final By logoButton = By.xpath("//div[@class='AppHeader_header__logo__2D0X2']/a[@href='/' ]");
    private final By constructorButton = By.xpath("//a[@href='/' and @class='AppHeader_header__link__3D_hX']");

    public UserAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    //	выход из аккаунта через кнопку 'выход' в личном кабинете
    @Step("Find And Click exit button on personal account")
    public void clickExitButton() {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(logoutButton));
        driver.findElement(logoutButton).click();
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
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(logoutButton));
        return driver.findElement(logoutButton).getText();
    }
}
