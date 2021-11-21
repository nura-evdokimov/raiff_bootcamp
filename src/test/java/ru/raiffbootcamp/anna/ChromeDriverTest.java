package ru.raiffbootcamp.anna;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeDriverTest extends BaseTest {
     @BeforeAll
     public static void start() {
        driver = new ChromeDriver();
     }

     @Test
     public void myStart() {
         driver.get("http://www.google.com");
     }
}


