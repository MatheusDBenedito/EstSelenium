
import org.example.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.CartPage; // Importe as novas classes
import pages.InventoryPage;
import pages.LoginPage;

public class CarrinhoTest extends BaseTest {


    // Declara TODOS os Page Objects necessários
    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private CartPage cartPage;

    @BeforeEach
    void initPageObjects() {
        // Inicializa cada Page Object usando o 'driver' herdado da BaseTest
        this.loginPage = new LoginPage(driver);
        this.inventoryPage = new InventoryPage(driver);
        this.cartPage = new CartPage(driver);}

    @Test
    @DisplayName("Deve adicionar um produto ao carrinho com sucesso")
    void deveAdicionarProdutoAoCarrinho() {



        // Usa a LoginPage para a ação de login
        loginPage.performLogin("standard_user", "secret_sauce");

        // Usa a InventoryPage para as ações na página de produtos
        inventoryPage.addBackpackToCart();

        // Validação usando a InventoryPage
        Assertions.assertEquals("1", inventoryPage.getCartBadgeCount());

        inventoryPage.clickCartLink();

        // Validações finais usando a CartPage
        // Validações finais usando a CartPage, agrupadas com assertAll
        Assertions.assertAll("Verificações na página do carrinho",
                () -> Assertions.assertEquals("Your Cart", cartPage.getPageTitle(), "O título da página do carrinho está incorreto."),
                () -> Assertions.assertEquals("Sauce Labs Backpack", cartPage.getProductNameInCart(), "O nome do produto no carrinho está incorreto.")
        );
    }
}