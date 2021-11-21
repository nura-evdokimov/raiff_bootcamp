package ru.raiffbootcamp.anna;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxDriverTest extends BaseTest {
    @BeforeAll
    public static void start() {
        driver = new FirefoxDriver();
    }

    @Test
    public void myFirstTest() {
        driver.navigate().to("http://localhost/litecart/admin/login.php");

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
}
