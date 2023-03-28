package demoqa.registration;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class RegistrationFormTest extends TestBase {
    @Test
    void fullFormTest() {

        String firstName = "Alina",
                lastName = "Krivosheeva",
                email = "alina@test.ru",
                gender = "Female",
                phoneNumber = "9202981234",
                birthMonth = "May",
                birthYear = "1999",
                birthDay = "16",
                subjects = "Maths",
                hobbies = "Music",
                picturePath = "images/image.png",
                pictureName = "image.png",
                address = "Moscow, ul Leopardovaya, d 18, kv 219",
                state = "NCR",
                city = "Noida";

        open("/automation-practice-form");
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");
        $(".main-header").shouldHave(text("Practice Form"));

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $("#genterWrapper").$(byText(gender)).click();
        $("#userNumber").setValue(phoneNumber);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").$(byText(birthMonth)).click();
        $(".react-datepicker__year-select").$(byText(birthYear)).click();
        $(".react-datepicker__day--0" + birthDay).click();
        $("#subjectsContainer input").setValue(subjects).pressEnter();
        $("#hobbiesWrapper").$(byText(hobbies)).click();
        $("#uploadPicture").uploadFromClasspath(picturePath);
        $("#currentAddress").setValue(address);
        $("#state").click();
        $("#stateCity-wrapper").$(byText(state)).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText(city)).click();

        $("#submit").click();

        $(".modal-dialog").should(appear);

        $(".table-responsive").shouldHave(
                text(firstName),
                text(lastName),
                text(email),
                text(gender),
                text(phoneNumber),
                text(birthDay + " " + birthMonth + "," + birthYear),
                text(subjects),
                text(hobbies),
                text(pictureName),
                text(address),
                text(state + " " + city)
        );

    }

}
