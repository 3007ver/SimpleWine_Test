package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.currentFrameUrlStartingWith;

public class LoginPage {
    private final String URL_MAIN = "https://simplewine.ru/";
    private final String URL_LK = "https://simplewine.ru/personal/?lk_tabs=lk";
    private SelenideElement
    loginButton = $("[data-autotest-target=auth-popup]"),
    emailButton = $(".auth__block-info"),
    emailInput = $("[name=authEmail]"),
    passwordInput = $("[name=authPassword]"),
    submitButton = $("[data-autotest-target-id=auth-email-popup-form-byemail-submit]"),
    profilePopup = $("[data-autotest-target=user-profile-popup]"),
    userProfile = $(".user-burger__toggler"),
    userNameInProfile = $(".lk-banner__greeting"),
    exitButton  = $("[data-autotest-target-id=user-profile-popup-exit]"),
    userMenu = $(".user-menu"),
    authPopup = $(".auth__wrapper");



    public LoginPage openLoginPopup () {
        loginButton.click();
        return this;
    }

    public LoginPage chooseEmailLogin () {
        emailButton.$(byText("email")).click();
        return this;
    }

    public LoginPage emailInput (String value) {
        emailInput.setValue(value);
        return this;
    }

    public LoginPage passwordInput (String value) {
        passwordInput.setValue(value);
        return this;
    }

    public LoginPage submitButtonClick () {
        submitButton.click();
        return this;
    }


    public LoginPage goToUserProfile () {
        userProfile.click();
        return this;
    }
    public LoginPage openLkPage () {
        open(URL_LK);
        return this;
    }
    public LoginPage checkLink () {
        webdriver().shouldHave(currentFrameUrlStartingWith(URL_LK));
        return this;
    }

    public LoginPage checkUserName (String value) {
        userNameInProfile.shouldHave(text("С возвращением, \n" + value + "\n!"));
        return this;
    }
    public LoginPage logOutWithButton () {
       userProfile.$(byText("Профиль")).hover();
        profilePopup.shouldBe(Condition.visible);
        exitButton.click();
        return this;
    }

    public LoginPage checkLogout () {

        authPopup.shouldBe(visible);
        return this;

    }
    public LoginPage openNewFrame () {
        Selenide.executeJavaScript("window.open('"+URL_LK+"');");
        return this;
    }


}

