package com.company.project.pages;

import org.openqa.selenium.By;

public class ForgotPasswordPage extends BasePage {

    private final By emailInput = By.id("email");
    private final By sendInstructionsButton = By.cssSelector("input[value='Send Instructions']");

    public void sendInstructions(String email) {
        enterText(emailInput, email);
        click(sendInstructionsButton);
    }
}
