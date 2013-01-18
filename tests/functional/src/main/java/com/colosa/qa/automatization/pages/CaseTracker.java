package com.colosa.qa.automatization.pages;

import java.util.List;
import java.util.ArrayList;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.Select;
import com.colosa.qa.automatization.common.*;
import com.colosa.qa.automatization.common.extJs.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

public class CaseTracker{

	WebElement weSectionButton;

	public static void goTo() throws FileNotFoundException, IOException, Exception{

		Browser.gotoUrl("http://127.0.0.1:8082/sysworkflow/en/classic/tracker/login");

	}

	public void goWhereCase() throws FileNotFoundException, IOException, Exception{
        Browser.driver().switchTo().defaultContent();
        this.weSectionButton = Browser.driver().findElement(By.id("MAP"));
        
        this.weSectionButton.click();
    }

	public void goFormDocs() throws FileNotFoundException, IOException, Exception{
        Browser.driver().switchTo().defaultContent();
        this.weSectionButton = Browser.driver().findElement(By.id("DYNADOC"));
        
        this.weSectionButton.click();
    }

	public void goHistory() throws FileNotFoundException, IOException, Exception{
        Browser.driver().switchTo().defaultContent();
        this.weSectionButton = Browser.driver().findElement(By.id("HISTORY"));
        
        this.weSectionButton.click();
    }

	public void goMessages() throws FileNotFoundException, IOException, Exception{
        Browser.driver().switchTo().defaultContent();
        this.weSectionButton = Browser.driver().findElement(By.id("MESSAGES"));
        
        this.weSectionButton.click();
    }

	public void openFirstForm() throws FileNotFoundException, IOException, Exception{
        Browser.driver().switchTo().defaultContent();
        this.weSectionButton = Browser.driver().findElement(By.xpath("//*[@id='publisherContent[1]']/table/tbody/tr/td/div/table/tbody/tr/td/table/tbody/tr[2]/td[6]/a"));
        
        this.weSectionButton.click();
    }

}