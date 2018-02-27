package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    //Webdriver
    private WebDriver driver;
    String url ="http://fits.qauber.com/#/page/login";
    //locators
    //email username input box
    By userName = By.name("account_email");
    //password input box
    By password = By.name("account_password");
    //login button
    By login = By.xpath("/html/body/div[2]/div/div/div[1]/div[2]/form/button");
    //forgotPasswordLink
    By forgotPaswordLink = By.linkText("Forgot");
    //register
    By register = By.xpath("/html/body/div[2]/div/div/div[1]/div[2]/a");


    public LoginPage(WebDriver driver) {
        this.driver = driver;
        driver.get(url);
    }

    public void enterUsername(String uName){
        WebElement element = driver.findElement(userName);
        element.sendKeys(uName);
    }

    public void enterPassword(String passwd){
        WebElement element = driver.findElement(password);
        element.sendKeys(passwd);
    }
    public ReportPage submitLogin(){
        WebElement element = driver.findElement(password);
        element.submit();
        ReportPage rp = new ReportPage(driver);
        //return the reports
        return rp;
    }
}
