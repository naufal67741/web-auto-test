package com.example;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.example.helper.ExtentManager;

public class AppTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private ExtentReports extent;
    private ExtentTest test;

    @BeforeClass
    public void setup() {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("target/ExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        System.setProperty("webdriver.chrome.driver", "/opt/homebrew/bin/chromedriver");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test(priority = 1)
    public void loginTest() {
        test = extent.createTest("Login Test").assignCategory("Functional");
        test.log(Status.INFO, "Navigating to login page...");
        
        driver.get("https://www.saucedemo.com");
        Assert.assertEquals(driver.getTitle(), "Swag Labs", "Title does not match!");
        test.pass("Page title is correct.");

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"), "Login failed!");
        test.pass("Login successful!");
    }

    @Test(priority = 2)
    public void addItemToCartTest() {
        test = extent.createTest("Add Item to Cart Test").assignCategory("Functional");
        test.log(Status.INFO, "Adding an item to cart...");
        
        WebElement item = driver.findElement(By.xpath("(//div[@class='inventory_item']//div[@class='pricebar']//button)[1]"));
        String beforeClickText = item.getText();
        item.click();
        test.pass("Item clicked.");

        WebElement itemAfter = driver.findElement(By.xpath("(//div[@class='inventory_item']//div[@class='pricebar']//button)[1]"));
        String afterClickText = itemAfter.getText();

        Assert.assertEquals(beforeClickText, "Add to cart", "Initial button text incorrect!");
        Assert.assertEquals(afterClickText, "Remove", "Button text did not change after clicking!");
        test.pass("Item added to cart successfully.");
    }

    @Test(priority = 3)
    public void aboutUsPageTest() {
        test = extent.createTest("About Us Page Test").assignCategory("Navigation");
        test.log(Status.INFO, "Opening About Us page...");
        
        driver.findElement(By.id("react-burger-menu-btn")).click();
        WebElement aboutUs = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("about_sidebar_link")));
        aboutUs.click();
        test.pass("Clicked on About Us link.");

        Assert.assertEquals(driver.getCurrentUrl(), "https://saucelabs.com/", "URL does not match!");
        test.pass("Navigated to correct About Us page.");

        WebElement h1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[2]")));
        Assert.assertTrue(h1.isDisplayed(), "H1 is not displayed!");
        test.pass("H1 is displayed on About Us page.");
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
        extent.flush();
    }
}
