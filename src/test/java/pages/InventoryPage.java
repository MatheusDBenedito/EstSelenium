package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage {

    private WebDriver driver;

    // Localizadores da página de inventário
    private By addToCartBackpackButton = By.id("add-to-cart-sauce-labs-backpack");
    private By cartBadge = By.className("shopping_cart_badge");
    private By cartLink = By.className("shopping_cart_link");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    // Ações
    public void addBackpackToCart() {
        driver.findElement(addToCartBackpackButton).click();
    }

    public void clickCartLink() {
        driver.findElement(cartLink).click();
    }

    // Obtenção de informações
    public String getCartBadgeCount() {
        return driver.findElement(cartBadge).getText();
    }
}