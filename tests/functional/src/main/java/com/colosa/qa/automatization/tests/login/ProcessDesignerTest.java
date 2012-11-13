package com.colosa.qa.automatization.tests.login;

import java.lang.Exception;
import com.colosa.qa.automatization.pages.Home;
import com.colosa.qa.automatization.pages.Pages;
import com.colosa.qa.automatization.pages.ProcessDesigner;
import com.colosa.qa.automatization.common.Browser;
import com.colosa.qa.automatization.common.extJs.ExtJSGrid;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.WebElement;

public class ProcessDesignerTest{

	public static void main(String[] args) throws Exception{
		Pages.Login().gotoUrl();
		Pages.Login().loginDefaultUser();
		Pages.Main().goDesigner();

		Browser.driver().switchTo().frame("frameMain");

		ExtJSGrid grid = new ExtJSGrid(Browser.driver().findElement(By.id("processesGrid")), Browser.driver(), 10);
		WebElement we = grid.getRowByColumnValue("Process Title", "Test 1");
		Actions action = new Actions(Browser.driver());
		action.doubleClick(we);
        action.perform();

        Browser.driver().switchTo().defaultContent();
        ProcessDesigner.open(ProcessDesigner.MenuOption.DYNAFORMS);

		System.out.println("----fin..");
	}

}
