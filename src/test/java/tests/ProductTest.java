package tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;


public class ProductTest {
    private WebDriver createDriver() {

        // Chrome configuration added to prevent a local browser password warning
        // that appeared during test execution.

        ChromeOptions options = new ChromeOptions();

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.password_manager_leak_detection", false);
        options.setExperimentalOption("prefs", prefs);

        return new ChromeDriver(options);
      }

    @Test void productSelectionAndCart() {


        WebDriver driver = createDriver();


        // Log in with valid credentials
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        assertTrue(driver.findElement(By.className("inventory_list")).isDisplayed());

        // Select a product, add it to the cart and Return to the product list
        driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']")).click();
        assertTrue(driver.findElement(By.className("inventory_details_name")).isDisplayed());


        driver.findElement(By.id("add-to-cart")).click();
        assertTrue(driver.findElement(By.className("shopping_cart_badge")).isDisplayed());


        driver.findElement(By.id("back-to-products")).click();
        assertTrue(driver.findElement(By.className("inventory_list")).isDisplayed());

        // Select another product and return to the product list
        driver.findElement(By.xpath("//div[text()='Sauce Labs Bike Light']")).click();
        assertTrue(driver.findElement(By.className("inventory_details_name")).isDisplayed());

        driver.findElement(By.id("back-to-products")).click();
        assertTrue(driver.findElement(By.className("inventory_list")).isDisplayed());


    }



}
