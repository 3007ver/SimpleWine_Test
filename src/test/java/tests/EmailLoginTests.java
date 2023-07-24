package tests;

import com.codeborne.selenide.Selenide;
import config.CredentialsConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.MainPage;


import static com.codeborne.selenide.Selenide.switchTo;
import static io.qameta.allure.Allure.step;

public class EmailLoginTests extends TestBase {
    private static CredentialsConfig config = ConfigFactory.create(CredentialsConfig.class, System.getProperties());

    MainPage mainPage = new MainPage();
    LoginPage loginPage = new LoginPage();
    String emailValue = config.emailAccount();
    String passwordValue = config.passwordAccount();
    String nameValue = config.name();

    @Test
    @Tag("remote")
    void loginWithEmailTest () {
        step("Открыть главную страницу", () -> {
            mainPage.openPage()
                    .confirmAge()
                    .confirmLocation();
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
            Selenide.refresh();
        });
        step("Проверить, что произошел переход в личный кабинет  и отображается имя пользователя", () -> {
            loginPage.goToUserProfile()
                    .checkLink()
                    .checkUserName(nameValue);


        });
    }
    @Test
    @Tag("remote")
    void logOutTest () {
        step("Открыть главную страницу", () -> {
            mainPage.openPage()
                    .confirmAge()
                    .confirmLocation();
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
            Selenide.sleep(10000);
        });
        step("Навести курсор на иконку Профиль и нажать на кнопку 'Выйти' в выпадающем меню", () -> {
            loginPage.logOutWithButton();
        });
        step("Перейти по ссылке личного кабинета и проверить, что пользователь разлогинен", () -> {

            loginPage.openLkPage()
                    .checkLink()
                    .checkLogout();
        });
    }

    @Test
    @Tag("remote")
    void checkLogOutInOtherFrame () {
        step("Открыть главную страницу", () -> {
            mainPage.openPage()
                    .confirmAge()
                    .confirmLocation();
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
            Selenide.sleep(10000);
        });
        step("Открыть главную страницу в новой вкладке", () -> {
            loginPage.openNewFrame();
        });
        step("Навести курсор на иконку Профиль и нажать на кнопку 'Выйти' в выпадающем меню", () -> {
            loginPage.logOutWithButton();
        });
        step("Перейти на первую вкладку и проверить, что пользователь разлогинен", () -> {
            switchTo().window(0);
            loginPage.openLoginPopup()
                    .checkLogout();
        });

    }
}
