package tests;


import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

public class CartTest {

    @Test void productSelectionAndCartFlow() {
        ChromeOptions options = new ChromeOptions();

        // Chrome configuration added to prevent a local browser password warning
        // that appeared during test execution.

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.password_manager_leak_detection", false);
        options.setExperimentalOption("prefs", prefs);

        WebDriver driver = new ChromeDriver(options);


        // Login with valid credentials
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        // Select a product and add it to the cart
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();

        // Click the cart and remove the product
        driver.findElement(By.className("shopping_cart_link")).click();
        driver.findElement(By.name("remove-sauce-labs-backpack")).click();


        // Return to the product list and select another product and Add to cart
        driver.findElement(By.id("continue-shopping")).click();
        driver.findElement(By.xpath("//div[text()='Test.allTheThings() T-Shirt (Red)']")).click();
        driver.findElement(By.id("add-to-cart")).click();

        // Open the cart
        driver.findElement(By.className("shopping_cart_link")).click();


    }
}
