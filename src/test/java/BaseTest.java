package org.example; // Mude para o seu pacote se for diferente

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import utils.TestListener;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;

@ExtendWith(TestListener.class) // O Listener fica aqui, valendo para todos que herdarem
public class BaseTest {

    public WebDriver driver; // O driver público fica aqui

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        // ... outros argumentos
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.saucedemo.com/");
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
