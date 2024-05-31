package com.mentorbabaa.selenium.finance_me_app;
import org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class App {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

   @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testNavBarLinks() {
        driver.get("http://13.201.50.227:8082/index.html"); // Change the path to your local index.html

        String[][] links = {
            {"Home", "index.html"},
            {"About", "about.html"},
            {"Services", "services.html"},
            {"Team", "team.html"},
            {"Contact Us", "contact.html"}
        };

        for (String[] link : links) {
            WebElement navLink = driver.findElement(By.linkText(link[0]));
            navLink.click();
            assertEquals(link[1], driver.getCurrentUrl());
            driver.navigate().back();
        }
    }

    @Test
    public void testContactFormSubmission() {
        driver.get("http://13.201.50.227:8082/contact.html"); // Change the path to your local contact.html

        driver.findElement(By.xpath("//input[@placeholder='Name']")).sendKeys("John Doe");
        driver.findElement(By.xpath("//input[@placeholder='Phone Number']")).sendKeys("john.doe@example.com");
        driver.findElement(By.xpath("//input[@placeholder='Email']")).sendKeys("1234567890");
        driver.findElement(By.xpath("//textarea[@id='comment']")).sendKeys("This is a test message.");
        driver.findElement(By.xpath("//a[normalize-space()='send']")).click();
        //driver.findElement(By.xpath("//*[@id=\"message\"]")).click();

        WebElement successMessage = driver.findElement(By.id("message")); // Assuming there is an element with id 'successMessage' on successful form submission
        assertTrue(successMessage.isDisplayed());
    }
}
