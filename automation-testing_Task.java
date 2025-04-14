import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class SauceDemoTest {

    WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver",  "/home/ivana/Downloads/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }

    @Test(priority = 1)
    public void login() {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"), "Login failed");
    }

    @Test(priority = 2)
    public void addToCartAndVerify() {
        driver.findElement(By.cssSelector(".inventory_item button")).click(); // Add first item
        driver.findElement(By.className("shopping_cart_link")).click();

        WebElement cartItem = driver.findElement(By.className("inventory_item_name"));
        Assert.assertTrue(cartItem.isDisplayed(), "Cart item not found");
    }

    @Test(priority = 3)
    public void checkoutAndVerify() {
        driver.findElement(By.id("checkout")).click();

        driver.findElement(By.id("first-name")).sendKeys("Ivana");
        driver.findElement(By.id("last-name")).sendKeys("QA");
        driver.findElement(By.id("postal-code")).sendKeys("1000");
        driver.findElement(By.id("continue")).click();

        WebElement checkoutItem = driver.findElement(By.className("inventory_item_name"));
        Assert.assertTrue(checkoutItem.isDisplayed(), "Checkout item not found");

        driver.findElement(By.id("finish")).click();
        Assert.assertTrue(driver.getPageSource().contains("Thank you for your order"), "Order not completed");
    }

    @Test(priority = 4)
    public void logout() {
        driver.findElement(By.id("react-burger-menu-btn")).click();
        try { Thread.sleep(1000); } catch (InterruptedException e) {}
        driver.findElement(By.id("logout_sidebar_link")).click();

        Assert.assertTrue(driver.getCurrentUrl().contains("saucedemo"), "Logout failed");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}
