package com.labqa.tests;

import com.codeborne.selenide.Configuration;
import com.labqa.User;
import com.labqa.mailSite.Letter;
import com.labqa.mailSite.pages.MainPage;
import com.labqa.mailSite.pages.UserDashBoard;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


/**
 * Created by Aleksandr Gladkov [Anticisco]
 * Date: 07.07.2021
 */

public class TestMail {

    private User user;
    private Letter letter;
    private MainPage mainPage;
    private UserDashBoard dashBoard;

    @BeforeSuite
    public void initTest() {
        setupBrowser();
        setupTestData();

    }

    private void setupTestData() {
        user = new User();
        letter = new Letter(user.getUserEmail(), "Hello Test", 150);
    }

    private void setupBrowser() {
        Configuration.browserSize = "1920x1080";
        Configuration.startMaximized = true;
        Configuration.baseUrl = "https://mail.ru";
    }

    @Test(priority = 1)
    public void enterDashboard() {
        mainPage = new MainPage();
        mainPage.openSite()
                .enterLoginData(user)
                .enterDomainData()
                .enterPasswordData();
        dashBoard = mainPage.goToDashBoard();
        Assert.assertEquals(dashBoard.getUserLoginInfo(), user.getUserEmail());
    }

    @Test(priority = 2)
    public void sendTestSelfLetter() {
        dashBoard.selectTypeSendLetter().fillLetterData(letter).closeSuccessSendLetterPopup();
        Assert.assertTrue(dashBoard.checkSuccessLetter());
    }

}
