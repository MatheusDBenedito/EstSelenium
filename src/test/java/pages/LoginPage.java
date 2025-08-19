package pages; // A classe está no pacote correto

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    // 1. WebDriver para interagir com a página
    private WebDriver driver;

    // 2. Mapeamento de todos os localizadores da página de login
    private By usernameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");
    private By errorMessageContainer = By.cssSelector("h3[data-test='error']");

    // 3. Construtor que recebe o WebDriver do teste
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // 4. Métodos de Ação (o que podemos FAZER na página)

    /**
     * Digita o nome de usuário no campo correspondente.
     * @param username O nome de usuário a ser digitado.
     */
    public void enterUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    /**
     * Digita a senha no campo correspondente.
     * @param password A senha a ser digitada.
     */
    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    /**
     * Clica no botão de login.
     */
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    /**
     * Método de serviço que combina ações para realizar o login completo.
     * @param username O nome de usuário.
     * @param password A senha.
     */
    public void performLogin(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    // 5. Métodos de Obtenção (o que podemos OBTER da página)

    /**
     * Obtém o texto da mensagem de erro de login.
     * @return O texto da mensagem de erro.
     */
    public String getErrorMessage() {
        return driver.findElement(errorMessageContainer).getText();
    }
}