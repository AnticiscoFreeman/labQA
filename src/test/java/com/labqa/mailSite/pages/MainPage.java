package com.labqa.mailSite.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.labqa.User;
import com.labqa.mailSite.PageAction;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

/**
 * Created by Aleksandr Gladkov [Anticisco]
 * Date: 07.07.2021
 */

public class MainPage extends PageAction {

    private By authUserBlock = By.id("mailbox");
    private By loginField = By.xpath("//input[@data-testid = 'login-input']");
    private By domainSelect = By.xpath("//select[@name = 'domain']");
    private By enterPasswordButton = By.xpath("//button[@data-testid = 'enter-password']");
    private By passField = By.xpath("//input[@data-testid = 'password-input']");
    private By saveAuthCheckBox = By.id("saveauth");
    private By loginInSite = By.xpath("//button[@data-testid = 'login-to-mail']");

    private User user;

    public MainPage openSite() {
        open(Configuration.baseUrl);
        return this;
    }

    public MainPage enterLoginData(User user) {
        if ($(authUserBlock).shouldHave(Condition.appear).exists()) {
            typeData(loginField, user.getUserLogin());
            this.user = user;
            return this;
        } else {
            throw new NoSuchElementException("Auth Block not exist!");
        }

    }

    public MainPage enterDomainData() {
        String currentDomain = $(domainSelect).getValue();
        if (!user.getUserDomain().contains(currentDomain)) {
            chooseDomain(user);
        }
        click(enterPasswordButton);
        return this;
    }

    public MainPage enterPasswordData() {
        typeData(passField, user.getUserPassword());
        if (!user.isRememberAuthSession()) {
            checkSaveAuthSession();
        }
        return this;
    }

    public UserDashBoard goToDashBoard() {
        click(loginInSite);
        return new UserDashBoard(user);
    }

    private void chooseDomain(User user) {
        ElementsCollection allDomains = $(domainSelect).findAll("option");
        for (int i = 0; i < allDomains.size(); i++) {
            SelenideElement option = allDomains.get(i);
            String domain = option.getAttribute("value");
            if (user.getUserDomain().contains(domain)) {
                click(option);
            }
        }
    }

    private void checkSaveAuthSession() {
        if (!$(saveAuthCheckBox).isSelected()) {
            $(saveAuthCheckBox).click();
        }
    }

}
