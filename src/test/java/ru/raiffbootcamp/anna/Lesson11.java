package ru.raiffbootcamp.anna;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;


public class Lesson11 extends BaseTest {
    @Test
    public void CreateAccount() throws InterruptedException {
        // находим веб элемент
        driver.get("http://localhost/litecart/en/create_account");
        WebElement firstName = driver.findElement(By.cssSelector("[name=firstname]"));
        // в найденый input элемент(поле ввода) записываем текст
        firstName.sendKeys("Max");

        WebElement lastName = driver.findElement(By.cssSelector("[name=lastname]"));
        lastName.sendKeys("Fray");

        WebElement address1 = driver.findElement(By.cssSelector("[name=address1]"));
        address1.sendKeys("Koula St");

        WebElement postcode = driver.findElement(By.cssSelector("[name=postcode]"));
        postcode.sendKeys("96814");

        WebElement city = driver.findElement(By.cssSelector("[name=city]"));
        city.sendKeys("Honolulu");

        WebElement country = driver.findElement(By.cssSelector("[name=country_code]"));
        Select countryCode = new Select(country);
        countryCode.selectByValue("US");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("option[value=HI]")));
        WebElement zoneStateProvince = driver.findElement(By.cssSelector("select[name=zone_code]"));
        Select state= new Select(zoneStateProvince);
        state.selectByValue("HI");

        String emailAddress = Math.round(Math.random() * 10000) + "az@test.ru";
        System.out.println(emailAddress);
        WebElement email = driver.findElement(By.cssSelector("[name=email]"));
        email.sendKeys(emailAddress);

        WebElement phone = driver.findElement(By.cssSelector("[name=phone]"));
        phone.sendKeys("12345565959");

        WebElement desiredPassword = driver.findElement(By.cssSelector("[name=password]"));
        desiredPassword.sendKeys("789456");

        WebElement confirmPassword = driver.findElement(By.cssSelector("[name=confirmed_password]"));
        confirmPassword.sendKeys("789456");

        WebElement createAccount = driver.findElement(By.cssSelector("[name=create_account]"));
        createAccount.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".notice.success")));

        this.logout();
        this.login(emailAddress);
        this.logout();

    }

    public void logout() {
        WebElement logout = driver.findElement(By.cssSelector("a[href='http://localhost/litecart/en/logout']"));
        logout.click();
        wait.until(ExpectedConditions.urlContains("http://localhost/litecart/en/"));
    }
    public void login(String emailAddress){
        WebElement email  = driver.findElement(By.cssSelector("[name=email]"));
        email.sendKeys(emailAddress);
        WebElement desiredPassword  = driver.findElement(By.cssSelector("[name=password]"));
        desiredPassword.sendKeys("789456");
        WebElement loginButton = driver.findElement(By.name("login"));
        loginButton.click();
        wait.until(ExpectedConditions
                        .presenceOfElementLocated(By.cssSelector("a[href='http://localhost/litecart/en/logout']")));
    }
}