package tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;



public class LoginTest {

    private WebDriver createDriver() {

        // Chrome configuration added to prevent a local browser password warning
        // that appeared during test execution.

        ChromeOptions options = new ChromeOptions();

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.password_manager_leak_detection", false);
        options.setExperimentalOption("prefs", prefs);

        return new ChromeDriver(options);
    }

    @Test
    void validLogin() {

        WebDriver driver = createDriver();

        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        assertTrue(driver.getCurrentUrl().contains("inventory.html"));
    }

    @Test
    void invalidUsername(){

        WebDriver driver = createDriver();

        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("wrongusername");
        driver.findElement(By.id("password")). sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        assertTrue(driver.findElement(By.cssSelector("[data-test='error']")).isDisplayed());


    }

    @Test
    void invalidPassword(){

        WebDriver driver = createDriver();

        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")). sendKeys("wrongpassword");
        driver.findElement(By.id("login-button")).click();

        assertTrue(driver.findElement(By.cssSelector("[data-test='error']")).isDisplayed());




    }

    @Test
    void emptyCredentials() {

        WebDriver driver = createDriver();

        driver.get("https://www.saucedemo.com/");

        driver.findElement(By.id("login-button")).click();

        assertTrue(driver.findElement(By.cssSelector("[data-test='error']")).isDisplayed());


    }

    @Test
    void lockoutUser() {

        WebDriver driver = createDriver();


        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("locked_out_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        assertTrue(driver.findElement(By.cssSelector("[data-test='error']")).isDisplayed());

    }





}
