package qa.guruallure;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class GithubissueTestSelenide {

    private final static String REPOSITORY = "znyaks/Product";
    private final static String USER = "znyaks";
    private final static String PASSWORD = "";
    private final static String ISSUE_NAME = "1";

    @Test
    public void createForIssue() {
        //Открытие главной страницы
        open("https://github.com");
        //Авторизация
        $(".header-search-input").click();
        $("[href='/login']").click();
        $("#login_field").val(USER);
        $("#password").val(PASSWORD).pressEnter();
        //Переход в репозиторий
        $(".header-search-input").sendKeys(REPOSITORY);
        $(".header-search-input").submit();
        $(byLinkText(REPOSITORY)).click();
        //Переход в Issues
        $("[data-tab-item='i1issues-tab']").click();
        //Создание нового Issue
        $$(byText("New issue")).find(visible).click();
        $("#issue_title").val(ISSUE_NAME);
        $("#issue_body").val("Банковская карта");
        $$(byText("Submit new issue")).find(visible).click();
        //Проверка наличия созданного Issue
        $(".js-issue-title").shouldHave(text(ISSUE_NAME));
    }
}