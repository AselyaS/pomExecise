package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageOF {
    //create a variuble to store driver obj
    private WebDriver driver;

    //page url
    String url = "http://fits.qauber.com/#/page/login";

    //locators
    @FindBy(name= "account_email")
    WebElement userNameTextBox;

    @FindBy(name= "account_password")
    WebElement userPasswordTextBox;

    public LoginPageOF(WebDriver driver) {
        this.driver = driver;
        driver.get(url);
        PageFactory.initElements(driver, this);
    }

    //enter userName
    public void enterUserName(String uName){
        userNameTextBox.sendKeys(uName);
    }

    //enter password
    public void enterPassword(String passwd){
        userPasswordTextBox.sendKeys(passwd);
    }

    //submit
    public ReportPage submitLogin() {
        userPasswordTextBox.submit();
        ReportPage rp = new ReportPage(driver);
        return rp;
    }
}
