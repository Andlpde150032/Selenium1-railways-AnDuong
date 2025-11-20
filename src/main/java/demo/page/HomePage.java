
package demo.page;

import org.openqa.selenium.WebDriver;

public class HomePage {

    private WebDriver driver;

    // Constructor
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // Method to get page title
    public String getTitle() {
        return driver.getTitle();
    }
}
