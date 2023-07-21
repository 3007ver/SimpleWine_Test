package tests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pages.MainPage;


import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.currentFrameUrlStartingWith;
import static io.qameta.allure.Allure.step;

public class CategoryMenuTest extends TestBase {
    MainPage mainPage = new MainPage();
    static Stream<Arguments> checkLinksCategoryMenu() {
        return Stream.of(
                Arguments.of("Вино", "https://simplewine.ru/catalog/vino/", "Вино"),
                Arguments.of("Шампанское и игристое", "https://simplewine.ru/catalog/shampanskoe_i_igristoe_vino/", "Шампанское и игристое вино"),
                Arguments.of("Виски", "https://simplewine.ru/catalog/ksn/viski/", "Виски"),
                Arguments.of("Коньяк", "https://simplewine.ru/catalog/ksn/konyak/", "Коньяк"),
                Arguments.of("Крепкие напитки", "https://simplewine.ru/catalog/ksn/", "Крепкие напитки"),
                Arguments.of("Вода и соки", "https://simplewine.ru/catalog/voda_i_soki/", "Вода и соки"),
                Arguments.of("Бокалы", "https://simplewine.ru/catalog/steklo/", "Стекло"),
                Arguments.of("Гурмэ", "https://simplewine.ru/catalog/gurme/", "Гурмэ")
        );
    }
    @MethodSource
    @ParameterizedTest
    void checkLinksCategoryMenu (String element, String link, String header) {
        step("Открыть главную страницу", () -> {
            mainPage.openPage()
                    .confirmAge()
                    .confirmLocation();
                });
        step("Нажать на элемент из меню категорий", () -> {
            $(".navigation__row").$(byText(element)).click();
        });
        step("Проверить, что произошел переход по ссылке", () -> {
            webdriver().shouldHave(currentFrameUrlStartingWith(link));
        });
        step("Проверить заголовок на странице", () -> {
            $(".image-title__header").shouldHave(text(header));
        });
    }
}
