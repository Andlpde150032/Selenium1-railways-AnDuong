package com.company.project.pages;

import com.company.project.config.PropertiesLoader;
import org.openqa.selenium.By;

public class HomePage extends BasePage {

    private final By loginTab = By.xpath("//span[contains(text(),'Login')]");
    private final By bookTicketTab = By.xpath("//span[contains(text(),'Book ticket')]");
    private final By myTicketTab = By.xpath("//span[contains(text(),'My ticket')]");
    private final By changePasswordTab = By.xpath("//span[contains(text(),'Change password')]");
    private final By logoutTab = By.xpath("//span[contains(text(),'Logout')]");

    public void open() {
        getDriver().get(PropertiesLoader.getProperty("base.url"));
    }

    public LoginPage goToLoginPage() {
        click(loginTab);
        return new LoginPage();
    }

    public void clickBookTicketTab() {
        click(bookTicketTab);
    }

    public boolean isMyTicketTabDisplayed() {
        try {
            return getDriver().findElement(myTicketTab).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isChangePasswordTabDisplayed() {
        try {
            return getDriver().findElement(changePasswordTab).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isLogoutTabDisplayed() {
        try {
            return getDriver().findElement(logoutTab).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public MyTicketPage goToMyTicketPage() {
        click(myTicketTab);
        return new MyTicketPage();
    }

    public ChangePasswordPage goToChangePasswordPage() {
        click(changePasswordTab);
        return new ChangePasswordPage();
    }
}
