package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {

    private WebDriver driver;

    // Localizadores
    private By pageTitle = By.className("title");
    private By productNameInCart = By.className("inventory_item_name");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    // Obtenção de informações
    public String getPageTitle() {
        return driver.findElement(pageTitle).getText();
    }

    public String getProductNameInCart() {
        return driver.findElement(productNameInCart).getText();
    }
}
