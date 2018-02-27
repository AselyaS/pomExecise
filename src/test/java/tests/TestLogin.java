package tests;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.LoginPageOF;
import pages.ReportPage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class TestLogin {
    private WebDriver cd;
    public static final String USERNAME = "assel3";
    public static final String AUTOMATE_KEY = "3sUFGnFMPkgRV6cxERWk";
    public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
    @AfterMethod
    public void tearDown() throws Exception {
        //quit
        cd.quit();

    }

    @BeforeMethod
    public void setUp() throws Exception {
        //cd = new ChromeDriver();
        //cd.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

    }

    @DataProvider
    public Object[][] excelData(){
        Object[][] testData = readFromExcel("D:\\My Desktop\\TestAdd1.xls", "Sheet1");
        return testData;
    }

    public String[][] readFromExcel(String fileName, String sheetName) {
        String[][] arrayExcelData = null;
        try {
            FileInputStream fs = new FileInputStream(fileName);
            Workbook wb = Workbook.getWorkbook(fs);
            Sheet sh = wb.getSheet(sheetName);

            int totalNoOfCols = sh.getColumns();
            int totalNoOfRows = sh.getRows();

            arrayExcelData = new String[totalNoOfRows-1][totalNoOfCols];

            for (int i= 1 ; i < totalNoOfRows; i++) {

                for (int j=0; j < totalNoOfCols; j++) {
                    arrayExcelData[i-1][j] = sh.getCell(j, i).getContents();
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }
        return arrayExcelData;
    }


    @Test(dataProvider = "excelData")
    public void testLogin(String os, String os_version, String browser, String browser_version, String browserstack_local, String browserstack_selenium_version) throws MalformedURLException {

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("os",os);
        caps.setCapability("os_version", os_version);
        caps.setCapability("browser", browser);
        caps.setCapability("browser_version", browser_version);
        //caps.setCapability("browserstack.local", browserstack_local);
       // caps.setCapability("browserstack.selenium_version", browserstack_selenium_version);

        cd = new RemoteWebDriver(new URL(URL), caps);
        cd.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);


      //  LoginPage lPage = new LoginPage(cd);
        LoginPageOF lPage = new LoginPageOF(cd);
        //send username
        lPage.enterUserName("asads@amail.club");
        //password
        lPage.enterPassword("simon123");
        ReportPage rPage;
        //submit
        rPage = lPage.submitLogin();
        String hText = rPage.getHeaderText();

        //Assert.assertTrue(hText.contains("Reports"), "Header text didn't match");
        Assert.assertEquals(hText.contains("Reports"), true, "Header text didn't match");

    }

    //@Test(dataProvider = "dprovider")
   // public void testDummy(String os, String password){
      //  System.out.println("username: " + os);

    //}
}
