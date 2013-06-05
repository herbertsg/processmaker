package com.colosa.qa.automatization.pages;

import com.colosa.qa.automatization.common.BrowserInstance;
import com.colosa.qa.automatization.common.extJs.ExtJSTree;
import com.colosa.qa.automatization.common.extJs.ExtJSTreeNode;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.interactions.Actions;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: herbert
 * Date: 5/14/13
 * Time: 2:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class NewCase extends Page {

    public NewCase(BrowserInstance browser) throws Exception {
        super(browser);

        //verify page
        this.verifyPage();

    }

    @Override
    public void verifyPage() throws Exception {
        //
        //wait for page
        browser.switchToDefaultContent();
        //browser.waitForElement(By.id("casesFrame"),120);
        browser.switchToFrame("casesFrame");
        //System.out.println("goto subcaseFrame ...");
        browser.switchToFrame("casesSubFrame");
        //browser.waitForElement(By.id("casesSubFrame"), 10);
        //browser.switchToFrame("casesSubFrame");
        System.out.println("wait for processesFilter ...");
        //browser.waitForElement(By.id("processesFilter"), 10);

            browser.findElementById("processesFilter");
    }

    public int startCase(String processName) throws Exception{
        //String[] path = pathToArray(processName);
        List<WebElement> wel;
        //Actions action = new Actions(browser.getInstanceDriver());
        boolean flag = false;
        int value = 0;

        browser.switchToDefaultContent();
        browser.switchToFrame("casesFrame");

        //browser.switchToDefaultContent();
        //this.selectMenuTreePanelOption("Cases/New case");
        //gotoNewCase();

        //wait for page
        browser.switchToDefaultContent();
        browser.waitForElement(By.id("casesFrame"),120);
        browser.switchToFrame("casesFrame");
        System.out.println("goto subcaseFrame ...");
        browser.switchToFrame("casesSubFrame");
        //browser.waitForElement(By.id("casesSubFrame"), 10);
        //browser.switchToFrame("casesSubFrame");
        System.out.println("wait for processesFilter ...");
        browser.waitForElement(By.id("processesFilter"), 10);

        //search process
        System.out.println("search process new case ...");
        WebElement processFilterElement = browser.findElementById("processesFilter");
        processFilterElement.clear();

        //buscar proceso sin tarea bug
        int indice = processName.indexOf("(");
        String processNameWihoutTask = processName.substring(0, indice);

        processFilterElement.sendKeys(processNameWihoutTask);
        processFilterElement.sendKeys(Keys.RETURN);

        //search process in reduced list:
        WebElement treeWebElement = browser.findElementById("startCaseTreePanel").findElement(By.className("x-panel-body"));
        ExtJSTree processListsTree = new ExtJSTree( treeWebElement, browser.getInstanceDriver());
        //get list of root nodes
        List<ExtJSTreeNode> listRootNodes = processListsTree.getListRootNodes();

        //build tree path
        ExtJSTreeNode node = null;
        for(ExtJSTreeNode treeNode:listRootNodes){
            System.out.println("search process path: "+treeNode.getNodeText() + "/" + processName+"...");
            node = processListsTree.gotoNode(treeNode.getNodeText() + "/" + processName);
            if(node != null){
                break;
            }
        }

        System.out.println("starting case "+processName+"...");
        node.doubleClick();


        //all the frame is updated
        //wait() for WebElement present
        browser.waitForElement(By.xpath("//div[@id='caseTabPanel']/div[1]/div[1]/ul/li[@id='caseTabPanel__casesTab']"), 5);

        value = Integer.parseInt(browser.findElementByXPath("//div[@id='caseTabPanel']/div[1]/div[1]/ul/li[@id='caseTabPanel__casesTab']").getText().trim().substring(8));
        System.out.println("New case created #:" + value);

        return value;
    }
}
