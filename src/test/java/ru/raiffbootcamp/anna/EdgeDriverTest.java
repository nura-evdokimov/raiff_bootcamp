package ru.raiffbootcamp.anna;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.edge.EdgeDriver;


public class EdgeDriverTest extends BaseTest {
    @BeforeAll
    public static void start() {
        driver = new EdgeDriver();
    }

    @Test
    public void myStart() {
        driver.get("https://www.google.com");
    }
}
