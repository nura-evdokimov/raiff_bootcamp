package ru.raiffbootcamp.anna;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;


import java.util.List;

public class Lesson8 extends BaseTest {
    @Test
    public void checkStickers() {
        driver.get("http://localhost/litecart/en/");
        List<WebElement> cardsForAllProducts = driver.findElements(By.className("product"));
        cardsForAllProducts.forEach(Lesson8::checkForCardSticker);
    }
    public static void checkForCardSticker(WebElement card){
        List<WebElement> stickersCard = card.findElements(By.className("sticker"));
        Assertions.assertTrue(stickersCard.size() == 1);
    }
}