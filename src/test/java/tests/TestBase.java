package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.SelenoidConfig;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;


public class TestBase {
    static SelenoidConfig selenoidConfig = ConfigFactory.create(SelenoidConfig.class, System.getProperties());

    @BeforeAll
    static void BeforeAll () {

        Configuration.remote = System.getProperty("remoteBrowser", selenoidConfig.selenoidUrl());
        Configuration.baseUrl = System.getProperty("baseUrl", "https://simplewine.ru/");
        Configuration.browserSize = System.getProperty("browserSize", "1920x1080");
        String[] browser = System.getProperty("browser", "chrome:115.0").split(":");
        Configuration.browser = browser[0];
        Configuration.browserVersion = browser[1];
        Configuration.pageLoadStrategy = "eager";
        Configuration.timeout = 20000;
        ChromeOptions ops = new ChromeOptions();
        ops.addArguments("--disable-notifications");
        Configuration.browserCapabilities = ops;
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;
    }

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }
    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        if (Configuration.remote != null) {
            Attach.addVideo();
        }
        Selenide.closeWebDriver();
    }
    }

