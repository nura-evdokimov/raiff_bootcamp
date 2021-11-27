package ru.raiffbootcamp.anna;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class Lesson9 extends BaseTest {
    @Test
    public void checkCountriesAlphabetically() {
        this.loginToAdminPanel();
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        List<WebElement> countries = driver.findElements(By.cssSelector("table .row td:nth-child(5)"));
        List<String> countryNames = this.toStringListByAttribute(countries, "textContent");

        this.checkListAlphabetically(countryNames);
    }

    @Test
    public void checkZonesAlphabetically() {
        this.loginToAdminPanel();
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");

        List<String> urlList = new ArrayList<>();
        List<WebElement> rows = driver.findElements(By.cssSelector("table .row"));

        for (WebElement row : rows) {
            WebElement zone = row.findElement(By.cssSelector("td:nth-child(6)"));
            String zoneCount = zone.getAttribute("textContent");

            if (Integer.parseInt(zoneCount) > 0) {
                WebElement countryLink = row.findElement(By.cssSelector("td:nth-child(5) a"));

                String countryUrl = countryLink.getAttribute("href");
                urlList.add(countryUrl);
            }
        }

         for (String countryUrl : urlList) {
            System.out.println("Visit url: " + countryUrl);
            driver.get(countryUrl);

            List<WebElement> zoneNameInputs = driver.findElements(By.cssSelector("input[type='hidden'][name$='[name]']"));
            List<String> zoneNames = this.toStringListByAttribute(zoneNameInputs, "value");

            this.checkListAlphabetically(zoneNames);
        }
    }

    @Test
    public void checkGeoZonesAlphabetically() {
        this.loginToAdminPanel();
        driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        List<WebElement> countryLinks = driver.findElements(By.cssSelector("table .row td:nth-child(3) a"));
        List<String> urls = countryLinks.stream().map(el -> el.getAttribute("href")).toList();

        for (String url : urls) {
            driver.get(url);

            List<WebElement> selectedZones = driver
                    .findElements(By.cssSelector("table#table-zones tr td:nth-child(3) option[selected]"));

            List<String> geoZoneNames = toStringListByAttribute(selectedZones, "textContent");
            this.checkListAlphabetically(geoZoneNames);
        }
    }

    public List<String> toStringListByAttribute(List<WebElement> list, String attributeName) {
        return  list.stream().map(el -> el.getAttribute(attributeName)).toList();

    }

    public void checkListAlphabetically(List<String> list) {
        for (int i = 1; i < list.size(); i++){
            String firstItem = list.get(i);
            String secondItem = list.get(i - 1);

            Assertions.assertTrue(firstItem.compareTo(secondItem) > 0);
        }
    }
}


