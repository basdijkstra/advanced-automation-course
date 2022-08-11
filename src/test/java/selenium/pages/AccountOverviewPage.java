package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountOverviewPage {

    private WebDriver driver;

    public AccountOverviewPage(WebDriver driver) {

        this.driver = driver;
    }

    public void selectMenuItem(String menuItemText) {

        this.driver.findElement(By.linkText(menuItemText)).click();
    }
}
