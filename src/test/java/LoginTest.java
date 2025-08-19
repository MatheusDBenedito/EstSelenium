// Adicione a importação da sua nova classe de página
import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.BaseTest;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.LoginPage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import utils.TestListener;


public class LoginTest extends BaseTest {


    private LoginPage loginPage; // Declara o Page Object


    // 3. Adicione um @BeforeEach próprio para inicializar o que for específico deste teste
    @BeforeEach

    void initPageObjects() {
        loginPage = new LoginPage(driver); // O 'driver' vem da classe pai (BaseTest)
    }

    @ParameterizedTest
    @CsvSource({
            "standard_user, secret_sauce, /inventory.html",
            "locked_out_user, secret_sauce, 'Epic sadface: Sorry, this user has been locked out.'",
            "problem_user, secret_sauce, /inventory.html", // Este usuário consegue logar
            "performance_glitch_user, secret_sauce, /inventory.html", // Este também
            "invalid_user, wrong_password, Epic sadface: Username and password do not match any user in this service"
    })
    @DisplayName("Teste de login com múltiplos usuários")
    void deveTestarLoginComDiferentesUsuarios(String username, String password, String expectedResult) {
        // Ação: realiza o login usando os parâmetros do CsvSource
        loginPage.performLogin(username, password);

        // Validação: a lógica muda dependendo do resultado esperado
        if (expectedResult.contains("/inventory.html")) {
            // Se o resultado esperado for uma URL, validamos o sucesso do login
            String currentUrl = driver.getCurrentUrl();
            Assertions.assertTrue(currentUrl.contains(expectedResult),
                    "Login com '" + username + "' deveria ter navegado para a página de inventário.");
        } else {
            // Se o resultado esperado for uma mensagem, validamos a mensagem de erro
            String actualMessage = loginPage.getErrorMessage();
            Assertions.assertEquals(expectedResult, actualMessage,
                    "Mensagem de erro para o usuário '" + username + "' não foi a esperada.");
        }
    }
}