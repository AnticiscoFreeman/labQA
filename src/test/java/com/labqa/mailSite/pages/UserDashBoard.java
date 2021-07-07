package com.labqa.mailSite.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.labqa.User;
import com.labqa.mailSite.Letter;
import com.labqa.mailSite.PageAction;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by Aleksandr Gladkov [Anticisco]
 * Date: 07.07.2021
 */

public class UserDashBoard extends PageAction {

    private By infoUserData = By.xpath("//div[@data-testid ='whiteline-account']");

    private By writeLetterButton = By.cssSelector(".compose-button__txt");

    private By letterConstructorBlock = By.cssSelector(".compose-app__compose");

    private By letterToBlock = By.xpath("//div[@data-type = 'to']");
    private By letterSubject = By.xpath("//input[@name = 'Subject']");

    private By letterBody = By.xpath("//div[@role = 'textbox']");

    private By sendLetterButton = By.xpath("//span[contains(@class, 'button2_primary')]");

    private By successSendLetter = By.cssSelector(".layer-sent-page");

    private By successSentLayer = By.cssSelector(".layer-sent-page");
    private By successSentLayerCloseButton = By.cssSelector(".layer__controls");

    private User user;

    public UserDashBoard(User user) {
        this.user = user;
    }

    public String getUserLoginInfo() {
        return $(infoUserData)
                .shouldHave(Condition.appear)
                .getAttribute("aria-label");
    }

    public UserDashBoard selectTypeSendLetter() {
        click(writeLetterButton);
        return this;
    }

    public UserDashBoard fillLetterData(Letter letter) {
        if ($(letterConstructorBlock).shouldHave(Condition.appear).exists()) {
            fillReceiverEmail(letter.getReceiverEmail());
            fillSubject(letter.getSubject());
            fillBody(letter.getBody());
            click(sendLetterButton);
        }
        return new UserDashBoard(user);
    }

    public void closeSuccessSendLetterPopup() {
        click(successSentLayerCloseButton);
    }

    public boolean checkSuccessLetter() {
        return $(successSendLetter).shouldHave(Condition.appear).exists();
    }

    private void fillReceiverEmail(String email) {
        SelenideElement element = $(letterToBlock).find("input");
        typeData(element, email);
    }

    private void fillSubject(String subject) {
        typeData(letterSubject, subject);
    }

    private void fillBody(String text) {
        getJse().executeScript("document.querySelectorAll('[role=\"textbox\"]')[0].innerText = '" + text + "'");
    }

}
