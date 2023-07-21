package tests;

import com.codeborne.selenide.Selenide;
import config.CredentialsConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Test;
import pages.AddToCartPage;
import pages.LoginPage;
import pages.MainPage;

import static io.qameta.allure.Allure.step;

public class AddProductToCartTest extends TestBase  {
    private static CredentialsConfig config = ConfigFactory.create(CredentialsConfig.class, System.getProperties());
    MainPage mainPage = new MainPage();
    LoginPage loginPage = new LoginPage();
    AddToCartPage addToCartPage = new AddToCartPage();
    String emailValue = config.emailAccount();
    String passwordValue = config.passwordAccount();

    @Test
    void AddToCartTest () {
        step("Открыть страницу продукта", () -> {
            addToCartPage.openPage();
            mainPage.confirmAge()
                    .confirmLocation();
        });
        step("Добавить продукт в корзину", () -> {
            addToCartPage.addProductToCart();
        });
        step("Проверить добавление товара в корзину", () -> {
            addToCartPage.checkPopup()
                    .openCartPage()
                    .cleanCart();
        });

    }
    @Test
    void cartAfterLoginTest () {
        step("Открыть страницу продукта", () -> {
            addToCartPage.openPage();
            mainPage.confirmAge()
                    .confirmLocation();
        });
        step("Добавить продукт в корзину", () -> {
            addToCartPage.addProductToCart();
        });
        step("Нажать кнопку Войти", () -> {
            loginPage.openLoginPopup();
        });
        step("Выбрать авторизацию по email", () -> {
            loginPage.chooseEmailLogin();
        });
        step("Ввести значение в поле email", () -> {
            loginPage.emailInput(emailValue);
        });
        step("Ввести значение в поле Пароль", () -> {
            loginPage.passwordInput(passwordValue);
        });
        step("Нажать кнопку Подтвердить", () -> {
            loginPage.submitButtonClick();
            Selenide.sleep(3000);
        });
        step("Открыть корзину", () -> {
            addToCartPage.openCartPage();
        });
        step("Проверить наличие товара в корзине", () -> {
            addToCartPage.checkCartPageTitle()
                    .cleanCart();
        });
    }
}
