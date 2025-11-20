
package demo.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class HomePage {

    private WebDriver driver;
    public static final String HOMEPAGE_PATH = "/Page/HomePage.cshtml";


    // Constructor
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // Method to get page title
    public String getTitle() {
        return driver.getTitle();
    }

    // Method to click on the "Book ticket" tab
    public void clickBookTicketTab() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*/text()[contains(.,'Book ticket')]/ancestor::a[1]"))).click();
    }
}
