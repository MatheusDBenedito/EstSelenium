package utils;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class TestListener implements TestWatcher {

    // Método que é chamado quando um teste falha
    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        System.out.println("O teste falhou! Tirando screenshot...");

        // Precisamos pegar a instância do WebDriver do nosso teste
        // Este código busca a instância do driver na classe de teste
        Object testInstance = context.getRequiredTestInstance();
        try {
            // Usa reflexão para acessar o campo 'driver' na classe de teste
            WebDriver driver = (WebDriver) testInstance.getClass().getField("driver").get(testInstance);

            // Tira o screenshot
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            // Define um nome único para o arquivo usando o nome do teste e a data/hora
            String testName = context.getDisplayName().replaceAll("[^a-zA-Z0-9]", "_");
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String fileName = testName + "_" + timestamp + ".png";

            // Salva o arquivo em uma pasta 'screenshots'
            Path destination = Paths.get("target", "screenshots", fileName);
            Files.createDirectories(destination.getParent());
            Files.copy(screenshot.toPath(), destination);

            System.out.println("Screenshot salvo em: " + destination);

        } catch (Exception e) {
            System.err.println("Erro ao tirar screenshot: " + e.getMessage());
            e.printStackTrace();
        }
    }
}