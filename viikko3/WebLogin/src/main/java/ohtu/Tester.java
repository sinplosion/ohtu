package ohtu;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Tester {

    public static void loginSuccessfull(WebDriver driver) {

        sleep(1);

        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        sleep(1);

        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("login"));

        sleep(1);
        element.submit();

        sleep(2);
    }

    public static void loginFailedCorrectUsernameWrongPassword(WebDriver driver) {
        sleep(2);

        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        sleep(2);

        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("wrong");
        element = driver.findElement(By.name("login"));

        sleep(2);
        element.submit();

        sleep(2);

        element = driver.findElement(By.linkText("back to home"));
        element.click();

        sleep(3);

    }

    public static void registerNewUser(WebDriver driver) {

        sleep(1);

        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();

        sleep(1);

        Random r = new Random();

        element = driver.findElement(By.name("username"));
        element.sendKeys("arto" + r.nextInt(100000));

        element = driver.findElement(By.name("password"));
        element.sendKeys("password");

        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("password");

        element = driver.findElement(By.name("signup"));

        sleep(1);
        element.submit();

        sleep(2);

    }

    public static void loggingOutAfterUserCreation(WebDriver driver) {
        sleep(1);
        WebElement element = driver.findElement(By.linkText("continue to application mainpage"));
        element.click();
        sleep(2);
        element = driver.findElement(By.linkText("logout"));
        element.click();
    }

    public static void logoutter(WebDriver driver) {

        sleep(1);
        WebElement element = driver.findElement(By.linkText("logout"));
        element.click();
        sleep(2);

    }

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:4567");
        loginSuccessfull(driver);
        logoutter(driver);
        loginFailedCorrectUsernameWrongPassword(driver);
        registerNewUser(driver);
        loggingOutAfterUserCreation(driver);

        driver.quit();
    }

    private static void sleep(int n) {
        try {
            Thread.sleep(n * 1000);
        } catch (Exception e) {
        }
    }
}
