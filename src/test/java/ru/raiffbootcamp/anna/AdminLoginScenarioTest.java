package ru.raiffbootcamp.anna;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.urlContains;

public class AdminLoginScenarioTest {
    private static WebDriver driver;
    private static WebDriverWait wait;

    @BeforeAll
    public static void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void myFirstTest() {
        driver.navigate().to("http://localhost/linecart/admin/login.php");

        // находим веб элемент
        WebElement usernameInputElement = driver.findElement(By.name("username"));
        WebElement passwordInputElement = driver.findElement(By.name("password"));
        WebElement loginButtonElement = driver.findElement(By.tagName("button"));

        // в найденый input(поле ввода) элемент записываем текст
        usernameInputElement.sendKeys("admin");
        passwordInputElement.sendKeys("00000000");

        // Нажимаем на кнопку
        loginButtonElement.click();
    }

    @AfterAll
    public static void finish() {
        driver.quit();
        driver = null;
    }
}
