package com.colosa.qa.automatization.pages;

//import java.util.List;
//import java.lang.Boolean;
import java.util.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.Keys;

import com.colosa.qa.automatization.common.*;
import com.colosa.qa.automatization.common.extJs.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Profile extends Page {

    public Profile() throws FileNotFoundException, IOException {

    }

    // Pages.Main().goHome();

    public void intoFrainMain() throws Exception {
        Browser.driver().switchTo().defaultContent();
        Browser.driver().switchTo().frame("frameMain");
    }

    public void intoDebug() throws Exception {
        Browser.driver().switchTo().defaultContent();
        Browser.driver().switchTo().frame("casesFrame");
    }


    public void editProfile() throws Exception{
        Browser.getElement("profile.Webelement.edit").click();

    }

    public void saveProfile() throws Exception{
        WebElement elem = Browser.driver().findElement(By.xpath("//*[@id='saveB']/tbody/tr[2]/td[2]/em/button"));
        elem.click();

    }    

    public void changeDefaultMenu(String option, String listCases) throws Exception {
        Browser.getElement("profile.Webelement.Select.Default").click();
        WebElement elem = null;
        switch(option)
        {
            case "ADMIN":
                Browser.getElement("profile.Webelement.Admin").click();
                break;
            case "DESIGNER":
                Browser.getElement("profile.Webelement.Designer").click();
                break;
            case "HOME":
                Browser.getElement("profile.Webelement.Home").click();
                WebElement elems = Browser.driver().findElement(By.xpath("//*[@id='x-form-el-PREF_DEFAULT_CASES_MENUSELECTED']/div[1]/img"));
                elems.click();   
                Pages.DynaformExecution().sleep(8000);          
                Pages.InputDocProcess().switchToDefault();
                Pages.DynaformExecution().intoFrainMain();            
                switch(listCases)
                {
                    case "New case":
                        elem = Browser.driver().findElement(By.xpath("//div[6]/div[1]/div[1]"));
                        elem.click();
                    break;
                    case "Inbox":
                        elem = Browser.driver().findElement(By.xpath("//div[6]/div[1]/div[2]"));
                        elem.click();
                    break;
                    case "Draft":
                        elem = Browser.driver().findElement(By.xpath("//div[6]/div[1]/div[3]"));
                        elem.click();                   
                    break;
                    case "Participated":
                        elem = Browser.driver().findElement(By.xpath("//div[6]/div[1]/div[4]"));
                        elem.click();                    
                    break;
                    case "Unassigned":
                        elem = Browser.driver().findElement(By.xpath("//div[6]/div[1]/div[5]"));
                        elem.click();                    
                    break;
                    case "Paused":
                        elem = Browser.driver().findElement(By.xpath("//div[6]/div[1]/div[6]"));
                        elem.click();                    
                    break;
                    case "Advanced Search":
                        elem = Browser.driver().findElement(By.xpath("//div[6]/div[1]/div[7]"));
                        elem.click();                    
                    break;
                    case "Review":
                        elem = Browser.driver().findElement(By.xpath("//div[6]/div[1]/div[8]"));
                        elem.click();                    
                    break;
                    case "Reassign":
                        elem = Browser.driver().findElement(By.xpath("//div[6]/div[1]/div[9]"));
                        elem.click();                    
                    break;
                    case "Documents":
                        elem = Browser.driver().findElement(By.xpath("//div[6]/div[1]/div[10]"));
                        elem.click();                    
                    break;                   
                    default:    break; 
                }

                break;
            case "DASHBOARDS":
                Browser.getElement("profile.Webelement.DashBoards").click();
                break;  
            default:    break;                          
        }
    }

}