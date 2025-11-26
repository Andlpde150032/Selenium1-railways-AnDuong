package com.company.project.pages;

import com.company.project.config.PropertiesLoader;
import org.openqa.selenium.By;

/**
 * @author AnDuong
 * @date 2025-11-25
 * @project Selenium1-railways-AnDuong
 * @function HomePage - Page object for Home Page
 */
public class HomePage extends BasePage {

    private final By loginTab = By.xpath("//a[contains(@href, 'Login.cshtml')]");
    private final By myTicketTab = By.xpath("//a[contains(@href, 'ManageTicket.cshtml')]");
    private final By changePasswordTab = By.xpath("//a[contains(@href, 'ChangePassword.cshtml')]");
    private final By logoutTab = By.xpath("//a[contains(@href, 'Logout')]");

    public void open() {
        getDriver().get(PropertiesLoader.getProperty("base.url"));
    }

    public LoginPage goToLoginPage() {
        click(loginTab);
        return new LoginPage();
    }

    public MyTicketPage goToMyTicketPage() {
        click(myTicketTab);
        return new MyTicketPage();
    }

    public ChangePasswordPage goToChangePasswordPage() {
        click(changePasswordTab);
        return new ChangePasswordPage();
    }

    public boolean isMyTicketTabDisplayed() {
        return !getText(myTicketTab).isEmpty();
    }

    public boolean isChangePasswordTabDisplayed() {
        return !getText(changePasswordTab).isEmpty();
    }

    public boolean isLogoutTabDisplayed() {
        return !getText(logoutTab).isEmpty();
    }

}