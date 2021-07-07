package com.labqa.mailSite;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by Aleksandr Gladkov [Anticisco]
 * Date: 07.07.2021
 */

public class PageAction {

    protected JavascriptExecutor getJse() {
        return (JavascriptExecutor) WebDriverRunner.getWebDriver();
    }

    protected void click(By element) {
        $(element).click();
    }

    protected void click(SelenideElement element) {
        element.click();
    }

    protected void typeData(By element, String inputData) {
        if (isExist(element)) {
            click(element);
            $(element).setValue(inputData);
        }
    }

    protected void typeData(SelenideElement element, String inputData) {
        if (isExist(element)) {
            click(element);
            element.setValue(inputData);
        }
    }

    protected boolean isExist(By element) {
        return $(element).exists();
    }

    protected boolean isExist(SelenideElement element) {
        return $(element).exists();
    }

}
