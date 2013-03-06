package com.colosa.qa.automatization.pages;

import com.colosa.qa.automatization.common.Browser;
import org.openqa.selenium.WebElement;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class Main extends Page{

    WebElement weSectionButton;
    WebElement weLogout;
    List<WebElement> wel;

    public Main() throws FileNotFoundException, IOException{

    }

    /*public void goSection(String sectionName) throws FileNotFoundException, IOException, Exception{

        //this.we = null;
        Browser.driver().switchTo().defaultContent();
        System.out.println("Redireccionando a seccion "+sectionName+"...");
        //this.waitForElementPresent(By.cssSelector("ul#pm_menu li a"),60);
        //this.we = Browser.driver().findElement(By.id(ConfigurationSettings.getInstance().getSetting("main.menu")));

        this.weMainMenu = Browser.getElement("main.WebElement.DashboardMenu");

        this.weSectionButton = this.weMainMenu.findElement(By.linkText(sectionName));
        
        
        //System.out.println(this.we); //raro pero se necesita esta linea para que funcione correctamente
        if(this.weSectionButton == null)
            throw new Exception("Invalid section name.");

        this.weSectionButton.click();
    }*/

    public void goHome() throws FileNotFoundException, IOException, Exception{
        Browser.driver().switchTo().defaultContent();
        this.weSectionButton = Browser.getElement("main.WebElement.HomeMenu");
        //this.weSectionButton = Browser.driver().findElement(By.id("CASES"));
        
        this.weSectionButton.click();
            
    }

    public void goDesigner() throws FileNotFoundException, IOException, Exception{
        Browser.driver().switchTo().defaultContent();
        this.weSectionButton = Browser.getElement("main.WebElement.DesignerMenu");
        //this.weSectionButton = Browser.driver().findElement(By.id("PROCESSES"));
        
        this.weSectionButton.click();
    }

    public void goDashboards() throws FileNotFoundException, IOException, Exception{
        Browser.driver().switchTo().defaultContent();
        this.weSectionButton = Browser.getElement("main.WebElement.DashboardMenu");

        this.weSectionButton.click();
    }

    public void goAdmin() throws FileNotFoundException, IOException, Exception{
        Browser.driver().switchTo().defaultContent();
        this.weSectionButton = Browser.getElement("main.WebElement.AdminMenu");
        //this.weSectionButton = Browser.driver().findElement(By.id("SETUP"));
        
        this.weSectionButton.click();
    }

    public void logout() throws FileNotFoundException, IOException, Exception{
        //Browser.driver().switchTo().defaultContent();
        //if(this.skin==0)
        Browser.driver().switchTo().defaultContent();
        Browser.getElement("main.WebElement.Logout").click();
    }

    public void profile() throws FileNotFoundException, IOException, Exception{
        Browser.driver().switchTo().defaultContent();
        Browser.getElement("main.WebElement.profile").click();
    }

    public void goToUrl(String url) throws FileNotFoundException, IOException, Exception{
        Browser.gotoUrl(url);
    }

}