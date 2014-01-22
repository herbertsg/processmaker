package com.colosa.qa.automatization.pages;

//import java.util.List;
//import java.lang.Boolean;
import com.colosa.qa.automatization.common.BrowserInstance;
import org.openqa.selenium.WebElement;

public class Profile extends Page {

    public Profile(BrowserInstance browser) throws Exception {
        super(browser);
        verifyPage();
    }

    @Override
    public void verifyPage() throws Exception {

    }


    // pages.Main().goHome();

    public void intoFrainMain() throws Exception {
        browser.switchToDefaultContent();
        browser.switchToFrame("frameMain");
    }

    public void intoDebug() throws Exception {
        browser.switchToDefaultContent();
        browser.switchToFrame("casesFrame");
    }


    public void editProfile() throws Exception{
        browser.findElement("profile.Webelement.edit").click();

    }

    public void saveProfile() throws Exception{
        WebElement elem = browser.findElementByXPath("//*[@id='saveB']/tbody/tr[2]/td[2]/em/button");
        elem.click();

    }    

    public void changeDefaultMenu(String option, String listCases) throws Exception {
        browser.findElement("profile.Webelement.Select.Default").click();
        WebElement elem = null;
        switch(option)
        {
            case "ADMIN":
                browser.findElement("profile.Webelement.Admin").click();
                break;
            case "DESIGNER":
                browser.findElement("profile.Webelement.Designer").click();
                break;
            case "HOME":
                browser.findElement("profile.Webelement.Home").click();
                WebElement elems = browser.findElementByXPath("//*[@id='x-form-el-PREF_DEFAULT_CASES_MENUSELECTED']/div[1]/img");
                elems.click();

                Thread.sleep(8000);

                Pages pages = new Pages(browser);

                pages.InputDocProcess().switchToDefault();
                //ojo form.intoMainFrame();

                switch(listCases)
                {
                    case "New case":
                        elem = browser.findElementByXPath("//div[6]/div[1]/div[1]");
                        elem.click();
                    break;
                    case "Inbox":
                        elem = browser.findElementByXPath("//div[6]/div[1]/div[2]");
                        elem.click();
                    break;
                    case "Draft":
                        elem = browser.findElementByXPath("//div[6]/div[1]/div[3]");
                        elem.click();                   
                    break;
                    case "Participated":
                        elem = browser.findElementByXPath("//div[6]/div[1]/div[4]");
                        elem.click();                    
                    break;
                    case "Unassigned":
                        elem = browser.findElementByXPath("//div[6]/div[1]/div[5]");
                        elem.click();                    
                    break;
                    case "Paused":
                        elem = browser.findElementByXPath("//div[6]/div[1]/div[6]");
                        elem.click();                    
                    break;
                    case "Advanced Search":
                        elem = browser.findElementByXPath("//div[6]/div[1]/div[7]");
                        elem.click();                    
                    break;
                    case "Review":
                        elem = browser.findElementByXPath("//div[6]/div[1]/div[8]");
                        elem.click();                    
                    break;
                    case "Reassign":
                        elem = browser.findElementByXPath("//div[6]/div[1]/div[9]");
                        elem.click();                    
                    break;
                    case "Documents":
                        elem = browser.findElementByXPath("//div[6]/div[1]/div[10]");
                        elem.click();                    
                    break;                   
                    default:    break; 
                }

                break;
            case "DASHBOARDS":
                browser.findElement("profile.Webelement.DashBoards").click();
                break;  
            default:    break;                          
        }
    }

}