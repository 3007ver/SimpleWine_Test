package tests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pages.MainPage;
import pages.SearchPage;

import static io.qameta.allure.Allure.step;

public class SearchTests extends TestBase{
    MainPage mainPage = new MainPage();
    SearchPage searchPage = new SearchPage();
    @ValueSource(strings = {"Красное вино", "Шампанское"})
    @ParameterizedTest
    void searchProductTest (String searchQuery) {
        step("Открыть главную страницу", () -> {
            mainPage.openPage()
                    .confirmAge()
                    .confirmLocation();
        });
        step("Ввести значение " + searchQuery + " в поле поиска", () -> {
            searchPage.searchProduct(searchQuery);
        });
        step("Проверить результат поиска", () -> {
            searchPage.checkSearchResults(searchQuery);
        });

    }

}
