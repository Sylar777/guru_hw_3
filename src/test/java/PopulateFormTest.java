import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class PopulateFormTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
    }

    @AfterEach
    void afterEach(){
        closeWebDriver();
    }

    @Test
    void fillFormTest() {
        open("/automation-practice-form");

        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        $("#firstName").setValue("firstName");
        $("#lastName").setValue("lastName");
        $("#userEmail").setValue("userEmail@useremail.com");
        $("[for=gender-radio-1]").click();
        $("#userNumber").setValue("1234567890");
        $("#dateOfBirthInput").click();
        $x("//select[contains(@class, 'react-datepicker__month-select')]/option[text()='December']").click();
        $x("//select[contains(@class, 'react-datepicker__year-select')]/option[text()='2020']").click();
        $(".react-datepicker__day--012").click();
        $("#subjectsInput").setValue("Computer Science").pressEnter();
        $("#subjectsInput").setValue("Social Studies").pressEnter();
        $("[for=hobbies-checkbox-1]").shouldBe(visible).click();
        $("#uploadPicture").shouldBe(visible).uploadFromClasspath("wolf.JPG");
        $("#currentAddress").shouldBe(visible).setValue("currentAddress");
        $("#react-select-3-input").shouldBe(visible).setValue("Rajasthan").pressEnter();
        $("#react-select-4-input").shouldBe(visible).setValue("Jaipur").pressEnter();
        $("#submit").shouldBe(visible).click();

        $(".table-responsive").shouldHave(text("firstName lastName"));
        $(".table-responsive").shouldHave(text("userEmail@useremail.com"));
        $(".table-responsive").shouldHave(text("Male"));
        $(".table-responsive").shouldHave(text("1234567890"));
        $(".table-responsive").shouldHave(text("12 December,2020"));
        $(".table-responsive").shouldHave(text("Computer Science, Social Studies"));
        $(".table-responsive").shouldHave(text("Sports"));
        $(".table-responsive").shouldHave(text("wolf.JPG"));
        $(".table-responsive").shouldHave(text("currentAddress"));
        $(".table-responsive").shouldHave(text("Rajasthan Jaipur"));
    }
}
