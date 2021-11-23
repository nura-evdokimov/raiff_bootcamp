package ru.raiffbootcamp.anna;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.NoSuchElementException;


public class Lesson7 extends BaseTest {
    @Test
    public void traversingAdminMenu() {
        this.loginToAdminPanel();
        WebElement firstMenuItem = driver.findElement(By.cssSelector("li#app-"));
        firstMenuItem.click();

        this.recurOpenNextMenuItem();
    }

    public void recurOpenNextMenuItem(){
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("h1")));
        try {
            WebElement nextMenuItem = driver.findElement(By.cssSelector("li.selected+li"));
            nextMenuItem.click();
            this.recurOpenNextMenuItem();
        } catch (NoSuchElementException exception) {
            // Последний пункт списка
        }
    }
}