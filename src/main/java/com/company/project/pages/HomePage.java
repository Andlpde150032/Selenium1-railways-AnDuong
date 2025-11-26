
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
    private final By registerTab = By.xpath("//a[contains(@href, 'Register.cshtml')]");
    private final By bookTicketTab = By.xpath("//a[contains(@href, 'BookTicketPage.cshtml')]");
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

    public void clickBookTicketTab() {
        click(bookTicketTab);
    }

    public RegisterPage goToRegisterPage() {
        click(registerTab);
        return new RegisterPage();
    }

    public ChangePasswordPage goToChangePasswordPage() {
        click(changePasswordTab);
        return new ChangePasswordPage();
    }

    public BookTicketPage goToBookTicketPage() {
        click(bookTicketTab);
        return new BookTicketPage();
    }
}
