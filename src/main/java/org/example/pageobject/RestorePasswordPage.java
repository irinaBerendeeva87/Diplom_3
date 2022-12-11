package org.example.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RestorePasswordPage {
    private final WebDriver driver;

    private final static String restorePasswordPage = "https://stellarburgers.nomoreparties.site/forgot-password";
    //локатор для кнопки войти в форме востановления пароля
    private final By authLinkByRestorePassForm = By.xpath(".//div/main/div/div/p/a");

    public RestorePasswordPage(WebDriver driver) {
        this.driver = driver;
    }
    //открытие страницы
    @Step("Open restore password page")
    public void openRestorePage (){
        driver.get(restorePasswordPage);
    }

    //	вход через кнопку в форме восстановления пароля
    @Step("Click Authorization Link on restore password page")
    public void clickAuthLinkByRestorePassForm (){
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(authLinkByRestorePassForm));
        driver.findElement(authLinkByRestorePassForm).click();
    }
}
