package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class MainPage {
    private SelenideElement
            ageConfirmContent = $(".age-confirm__content"),
            ageConfirmButton = $(".age-confirm__button"),
            locationButton = $("[data-autotest-target-id=city-popup-city-agree-btn]");

    public MainPage openPage() {
        open("/");
        return this;
    }

    public MainPage confirmAge() {
        if (ageConfirmContent.is(Condition.visible)) {
            ageConfirmButton.click();
            Selenide.sleep(3000);
        }
        return this;
    }

    public MainPage confirmLocation() {
        if (locationButton.is(Condition.visible)) {
            locationButton.click();
        }
            return this;
        }

}
