package tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;



public class CheckoutTest {
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
    void completeCheckoutFlow() {
        WebDriver driver = createDriver();

        //Log in with Valid credential
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();


        //Select an item and add to shopping cart
        driver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-onesie")).click();


        //Open shopping cart and click Checkout
        driver.findElement(By.className("shopping_cart_link")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("checkout"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name")))
                .sendKeys("Jihee");

        driver.findElement(By.id("last-name")).sendKeys("Biltz");
        driver.findElement(By.id("postal-code")).sendKeys("20105");

        // Continue to checkout overview and click finish
        driver.findElement(By.id("continue")).click();
        driver.findElement(By.id("finish")).click();

        assertTrue(driver.findElement(By.className("complete-header")).isDisplayed());
        driver.findElement(By.id("back-to-products")).click();

    }


        @Test
        void checkoutRequiredFieldsValidation() {

            WebDriver driver = createDriver();


            //log in and add items to cart and click checkout
            driver.get("https://www.saucedemo.com/");
            driver.findElement(By.id("user-name")).sendKeys("standard_user");
            driver.findElement(By.id("password")).sendKeys("secret_sauce");
            driver.findElement(By.id("login-button")).click();
            driver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket")).click();
            driver.findElement(By.id("add-to-cart-sauce-labs-onesie")).click();
            driver.findElement(By.className("shopping_cart_link")).click();
            driver.findElement(By.id("checkout")).click();

            // Leave all three fields empty and click continue
            driver.findElement(By.id("continue")).click();
            assertTrue(driver.findElement(By.cssSelector("[data-test='error']")).isDisplayed());























        }






}
