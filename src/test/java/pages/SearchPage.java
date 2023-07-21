package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class SearchPage {

    private SelenideElement
            searchInput = $(".header-search-form__input"),
            searchTitle = $(".search-results__header");


    public SearchPage searchProduct(String value) {
        searchInput.setValue(value).pressEnter();
        return this;
    }

    public SearchPage checkSearchResults (String value) {
        searchTitle.shouldHave(text("По запросу «" + value + "» мы нашли:"));
        return this;
    }
}
