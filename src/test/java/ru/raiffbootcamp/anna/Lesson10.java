package ru.raiffbootcamp.anna;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;

public class Lesson10 {
    public WebDriver driver;

    @AfterEach
    public void stopDriver() {
        driver.quit();
        driver = null;
    }

    @Test
    public void checkProductIdentityChrome() {
        driver = new ChromeDriver();
        this.checkProductIdentity();
    }

    @Test
    public void checkProductIdentityFirefox() {
        driver = new FirefoxDriver();
        this.checkProductIdentity();
    }

    @Test
    public void checkProductIdentityEdge() {
        driver = new EdgeDriver();
        this.checkProductIdentity();
    }

    public void checkProductIdentity() {
        driver.get("http://localhost/litecart/en/");

        // Находим первую продуктовую карточку в разделе  Campaigns
        WebElement productCard = driver.findElement(By.cssSelector("#box-campaigns .product"));

        // Сохраняем имя продукта с главной страницы
        String mainPageProductName = productCard.findElement(By.className("name")).getText();

        // Сохраняем обычную цену с главной страницы
        String mainPageRegularPrice = productCard.findElement(By.className("regular-price")).getText();

        // Сохраняем акционную цену с главной страницы
        String mainPageCampaignPrice = productCard.findElement(By.className("campaign-price")).getText();

        checkProduct(productCard);

        // Открываем страницу продукта
        productCard.click();

        WebElement boxProduct = driver.findElement(By.id("box-product"));

        String productName = boxProduct.findElement(By.cssSelector("h1.title")).getText();

        Assertions.assertEquals(mainPageProductName, productName);

        WebElement regularPrice = boxProduct.findElement(By.className("regular-price"));
        WebElement campaignPrice = boxProduct.findElement(By.className("campaign-price"));

        Assertions.assertEquals(mainPageRegularPrice, regularPrice.getText());
        Assertions.assertEquals(mainPageCampaignPrice, campaignPrice.getText());

        checkProduct(boxProduct);
    }

    public void checkProduct(WebElement product) {
        WebElement regularPrice = product.findElement(By.className("regular-price"));
        WebElement campaignPrice = product.findElement(By.className("campaign-price"));

        // Проверяем что обычая цена зачеркнута
        Assertions.assertEquals(regularPrice.getTagName(), "s");
        // Проверем что обычная цена серая
        this.checkElementGrayColor(regularPrice);

        // Проверяем что акционная цена красная
        this.checkElementRedColor(campaignPrice);
        // Проверяем что акционная цена ЖИРНАЯ
        Assertions.assertTrue(Integer.parseInt(campaignPrice.getCssValue("font-weight")) >= 700);

        // Проверяем размер шрифта
        String regularPriceFontSize = regularPrice.getCssValue("font-size");
        String campaignPriceFontSize = campaignPrice.getCssValue("font-size");
        Assertions.assertTrue(campaignPriceFontSize.compareTo(regularPriceFontSize) > 0);
    }

    public void checkElementGrayColor(WebElement el) {
        String cssColor = el.getCssValue("color");
        java.awt.Color color = Color.fromString(cssColor).getColor();

        Assertions.assertEquals(color.getBlue(), color.getRed());
        Assertions.assertEquals(color.getRed(), color.getGreen());
    }

    public void checkElementRedColor(WebElement el) {
        String cssColor = el.getCssValue("color");

        java.awt.Color color = Color.fromString(cssColor).getColor();

        Assertions.assertEquals(color.getBlue(), 0);
        Assertions.assertEquals(color.getGreen(), 0);
        Assertions.assertTrue(color.getRed() > 0);
    }
}
