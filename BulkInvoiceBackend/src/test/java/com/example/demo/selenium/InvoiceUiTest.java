package com.example.demo.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.file.Paths;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class InvoiceUiTest {

    private WebDriver driver;

    @BeforeEach
    void setUp() {
        // Selenium 4.6+ auto manages driver
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void testUploadUsingXpathAndCss() throws InterruptedException {

        // Open UI
        driver.get("http://localhost:8080");

        // === Locate file input using XPath ===
        WebElement fileInput =
                driver.findElement(By.xpath("//input[@id='fileInput']"));

        // Provide absolute path to CSV file
        String filePath = Paths.get("invoices_1000.csv")
                .toAbsolutePath()
                .toString();

        fileInput.sendKeys(filePath);

        // === Locate Upload button using CSS Selector ===
        WebElement uploadButton =
                driver.findElement(By.cssSelector("#uploadBtn"));

        uploadButton.click();

        // Wait for status update (better to use explicit wait in real project)
        Thread.sleep(3000);

        // === Verify status message ===
        WebElement statusMessage =
                driver.findElement(By.id("statusMessage"));

        String message = statusMessage.getText();

        System.out.println("Status Message: " + message);

        assertTrue(
                message.contains("Upload") ||
                        message.contains("Successful") ||
                        message.contains("Uploading"),
                "Status message not displayed correctly"
        );
    }
}