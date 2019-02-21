package com.tddjava.ch07booksstore;

import com.codeborne.selenide.WebDriverRunner;
import org.jbehave.core.annotations.BeforeStory;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

/**
 * @auther ouxx
 * @create 2018/6/7 0:06
 */
public class Step {
    private String url = "http://localhost:9001";

    private WebDriver webDriver;

    @BeforeStory
    public void beforeStory() {
        if (null == webDriver) {
            webDriver = new PhantomJSDriver();
            WebDriverRunner.setWebDriver(webDriver);
            webDriver.manage().window().setSize(new Dimension(1024, 768));
        }
    }

    @Given("UserIsOnTheBooksPage")
    public void givenUserIsOnTheBooksPage() {
        open(url);
        $("#books").click();
    }

    @Then("Field $elementId Exists")
    public void thenFieldExists(String elementId) {
        $("#" + elementId).shouldBe(visible);
    }

    /***********新增书*************/

    @When("用户为图书表单设置值")
    public void whenUserSetValuesToTheBookForm() {
        $("#bookId").setValue("123");
        $("#bookTitle").setValue("BDD Assistant");
        $("#bookAuthor").setValue("ouxx");
        $("#bookDescription").setValue("open source BDD stories editor and runner");
    }

    @Then("BookIsStored")
    public void thenBookIsStored() {
        $("#book123").shouldBe(exist);
    }

}
