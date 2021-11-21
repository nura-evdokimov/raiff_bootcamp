package ru.raiffbootcamp.anna;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;

public class Lesson7 extends BaseTest {
    @BeforeAll
    public static void start() {
        driver = new ChromeDriver();
    }

    @Test
    public void traversingAdminMenu() {
        this.loginToAdminPanel();


    }
}