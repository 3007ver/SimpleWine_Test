package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AddToCartPage {
    private final String URL_PRODUCT = "/catalog/product/veuve_ambal__grande_cuvee_rose_brut_2019_075_136969/";

    private SelenideElement
    buyButton = $(".product__buy"),
    addToCartPopup = $(".product-alert"),
    cartButton = $(".user-cart"),
    cartPageTitle = $(".cart__page-title"),
    cleanCartButton = $("[data-autotest-target-id=cart-clear]");



    public AddToCartPage openPage () {
        open(URL_PRODUCT);
        return this;
    }
    public AddToCartPage addProductToCart () {
        buyButton.click();
        return this;
    }
    public AddToCartPage checkPopup () {
        addToCartPopup.shouldHave(text("Товар добавлен в корзину"));
        return this;
    }
    public AddToCartPage openCartPage () {
        cartButton.click();
        return this;
    }
    public AddToCartPage checkCartPageTitle () {
        cartPageTitle.shouldHave(text("1 товар в корзине"));
        return this;
    }
    public AddToCartPage cleanCart () {
        cleanCartButton.click();
        return this;
    }
}
