package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private WebDriver driver;

    private By textfieldUsername = By.name("username");
    private By textfieldPassword = By.name("password");
    private By buttonDoLogin = By.xpath("//input[@value='Log In']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;

        this.driver.get("http://localhost:8080/parabank");
    }

    public void loginAs(String username, String password) {

        this.driver.findElement(textfieldUsername).sendKeys(username);
        this.driver.findElement(textfieldPassword).sendKeys(password);
        this.driver.findElement(buttonDoLogin).click();
    }
}
