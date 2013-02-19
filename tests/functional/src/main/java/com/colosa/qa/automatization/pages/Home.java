package com.colosa.qa.automatization.pages;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import com.colosa.qa.automatization.common.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import com.colosa.qa.automatization.common.extJs.ExtJSGrid;


public class Home extends Main{

	public Home() throws Exception{
	}

	private static String[] pathToArray(String path){
		if(path.charAt(0) == '/')
			path = path.substring(1);
		if(path.charAt(path.length()-1) == '/')
			path = path.substring(0, path.length());

		return path.split("/");
	}

	public static void selectMenuTreePanelOption(String path) throws Exception{
		List<WebElement> wel;
		WebElement option = null;
		String[] pathLevels;
		String aux="";

		if(path.length() == 0)
			throw new Exception("The option path must be specified");		
		pathLevels = pathToArray(path.toLowerCase());

		if(pathLevels.length>2)
			throw new Exception("the PATH parameter must contain up to 2 path levels.");

		System.out.println("trying to enter "+pathLevels[0]+((pathLevels.length>1)?" => "+pathLevels[1]:"")+"...");

		/*
		if(this.skin == 0)*/
			Browser.driver().switchTo().frame("casesFrame");
		/*else
		{
			this.waitForElementPresent(By.id("pm-frame-cases"),this.timeout);
			Browser.driver().switchTo().frame("pm-frame-cases");
		}*/

		if(pathLevels.length==2 || path.charAt(path.length()-1)=='/')
			wel = Browser.driver().findElements(By.xpath("//div[@id='tree-panel']/div/div/ul/div/li"));
		else
			wel = Browser.driver().findElements(By.xpath("//div[@id='tree-panel']/div/div/ul/div/li/ul/li"));
		for(WebElement we:wel)
			try{
				aux = we.findElement(By.xpath("div/a/span")).getText().toLowerCase();
				if(aux.length()>=pathLevels[0].length())
					if(aux.substring(0, pathLevels[0].length()).equals(pathLevels[0]))
					{
						option = we;
						break;
					}
			}catch(java.lang.StringIndexOutOfBoundsException e){
				throw new Exception("No se encontró el grupo de opciones: \""+pathLevels[0]+"\"");
			}
		if(option == null)
			throw new Exception("No se encontró el grupo de opciones: \""+pathLevels[0]+"\"");
		if(pathLevels.length==2)
		{
			wel = option.findElements(By.xpath("ul/li"));
			option = null;
			for(WebElement we:wel)
			{
				aux = we.findElement(By.xpath("div/a/span")).getText().toLowerCase();
				if(aux.length()>=pathLevels[1].length())
					if(aux.substring(0, pathLevels[1].length()).equals(pathLevels[1]))
					{
						option = we;
						break;
					}
			}
			if(option == null)
				throw new Exception("No se encontró opción: \""+pathLevels[1] + "\" en el grupo de opciones: \""+pathLevels[0]+"\"");
		}
		option.findElement(By.xpath("div/a/span")).click();

		Browser.driver().switchTo().defaultContent();
	}

	public static void gotoNewCase() throws Exception{
		selectMenuTreePanelOption("Cases/New case");
	}

	public static void gotoInbox() throws Exception{
		selectMenuTreePanelOption("Cases/Inbox");
	}

	public static void gotoDraft() throws Exception{
		selectMenuTreePanelOption("Cases/Draft");
	}

	public static void gotoParticipated() throws Exception{
		selectMenuTreePanelOption("Cases/Participated");
	}

	public static void gotoUnassigned() throws Exception{
		selectMenuTreePanelOption("Cases/Unassigned");
	}

	public static void gotoPaused() throws Exception{
		selectMenuTreePanelOption("Cases/Paused");
	}

	public static void gotoAdvancedSearch() throws Exception{
		selectMenuTreePanelOption("Search/Advanced Search");
	}

	public static void gotoReview() throws Exception{
		selectMenuTreePanelOption("Process Supervisor/Review");
	}

	public static void gotoReassign() throws Exception{
		selectMenuTreePanelOption("Process Supervisor/Reassign");
	}

	public static void gotoDocuments() throws Exception{
		selectMenuTreePanelOption("Documents/");
	}

	public static void gotoReports() throws Exception{
		selectMenuTreePanelOption("Documents/Reports");
	}


	public int startCase(String processName) throws Exception{
		String[] path = pathToArray(processName);
		List<WebElement> wel;
		Actions action = new Actions(Browser.driver());
		boolean flag = false;
		int value = 0;

		Browser.driver().switchTo().defaultContent();
		Home.selectMenuTreePanelOption("Cases/New case");
		Browser.driver().switchTo().defaultContent();
		Browser.driver().switchTo().frame("casesFrame");
		Browser.driver().switchTo().frame("casesSubFrame");
		WebElement we = Browser.driver().findElement(By.id("startCaseTreePanel"));
		if(path.length>2)
			throw new Exception("the string Path parameter can contain up to 2 segments, you asshole!");

		System.out.println("Searching case: "+processName+"...");
		if(path.length==2)
		{
			wel = we.findElements(By.xpath("div/div[2]/ul/div/li"));
			we = null;
			for(WebElement we2:wel)
				if(we2.findElement(By.xpath("div/a/span")).getText().equals(path[0]))
				{
					we = we2;
					break;
				}
			if(we == null)
				throw new Exception("process \""+processName+"\" not found");
			wel = we.findElements(By.xpath("ul/li"));
			for(WebElement we2:wel)
			{
				we = we2.findElement(By.xpath("div/a/span"));
				if(we.getText().equals(path[1]))
				{
					flag = true;
					break;
				}
			}
			if(!flag)
				throw new Exception("process \""+processName+"\" not found");
		}
		else{
			wel = we.findElements(By.xpath("div/div[2]/ul/div/li/ul/li"));
			for(WebElement we2:wel)
			{
				we = we2.findElement(By.xpath("div/a/span"));
				if(we.getText().equals(path[0]))
				{
					flag = true;
					break;
				}
			}
			if(!flag)
				throw new Exception("process \""+processName+"\" not found");
		}
		System.out.println("starting case "+processName+"...");
		action.doubleClick(we);
        action.perform();

        value = Integer.parseInt(Browser.driver().findElement(By.xpath("//div[@id='caseTabPanel']/div[1]/div[1]/ul/li[@id='caseTabPanel__casesTab']")).getText().trim().substring(8));
        return value;
	}

	public void openCase(int numCase)throws Exception{
		ExtJSGrid grid;
		Actions action = new Actions(Browser.driver());
		Browser.driver().switchTo().defaultContent();		
		Browser.driver().switchTo().frame("casesFrame");
		Browser.driver().switchTo().frame("casesSubFrame");
		grid = new ExtJSGrid(Browser.driver().findElement(By.id("casesGrid")), Browser.driver());
		WebElement row = grid.getRowByColumnValue("#", Integer.toString(numCase));
		if(row==null)
			throw new Exception("Case # "+Integer.toString(numCase)+" not found in Inbox folder");
		action.doubleClick(row.findElement(By.xpath("table/tbody/tr/td[div='"+Integer.toString(numCase)+"']/div")));
        action.perform();
	}

	public boolean selectCase(int numCase)throws Exception{
		ExtJSGrid grid;
		Actions action = new Actions(Browser.driver());
		Browser.driver().switchTo().defaultContent();		
		Browser.driver().switchTo().frame("casesFrame");
		Browser.driver().switchTo().frame("casesSubFrame");
		grid = new ExtJSGrid(Browser.driver().findElement(By.id("casesGrid")), Browser.driver());
		WebElement row = grid.getRowByColumnValue("#", Integer.toString(numCase));
		if(row==null){
			throw new Exception("Case # "+Integer.toString(numCase)+" not found in Inbox folder");
		}
		else{
			action.click(row.findElement(By.xpath("table/tbody/tr/td[div='"+Integer.toString(numCase)+"']/div")));
			action.perform();
			return true;
		}	
		
  }

	public void selectTasktoAssign(int numCase)throws Exception{
		ExtJSGrid grid;
		Actions action = new Actions(Browser.driver());
		Browser.driver().switchTo().defaultContent();		
		Browser.driver().switchTo().frame("casesFrame");
		Browser.driver().switchTo().frame("casesSubFrame");
		grid = new ExtJSGrid(Browser.driver().findElement(By.id("reassign-form")), Browser.driver(),45);
		WebElement row = grid.getRowByColumnValue("#", Integer.toString(numCase));
		if(row==null)
			throw new Exception("Case # "+Integer.toString(numCase)+" not found in Inbox folder");
		action.click(row.findElement(By.xpath("table/tbody/tr/td[div='"+Integer.toString(numCase)+"']/div")));
		action.perform();
  }
  
  public void pauseCase(int numCase)throws Exception{
		
		Actions action = new Actions(Browser.driver());
		Browser.driver().switchTo().defaultContent();		
		Browser.driver().switchTo().frame("casesFrame");
		Browser.driver().switchTo().frame("casesSubFrame");
		
		Thread.sleep(4000);
		WebElement actionPanel = Browser.driver().findElement(By.id("navPanel"));
		WebElement actionMenu = actionPanel.findElement(By.id("actionMenu"));
		WebElement bAction = actionMenu.findElement(By.tagName("button"));
		bAction.click(); 
		
		Thread.sleep(4000);
		WebElement pauseCase = Browser.driver().findElement(By.xpath("/html/body/div[5]/ul/li[1]/a"));
		WebElement pCase = pauseCase.findElement(By.tagName("span"));
		pCase.click(); 
		
		Thread.sleep(4000);
		WebElement pauseForm = Browser.driver().findElement(By.id("unpauseFrm"));
		WebElement pauseButton = pauseForm.findElement(By.id("submitPauseCase"));
		WebElement pauseClick = pauseButton.findElement(By.tagName("button"));
		
		pauseClick.click();
		Thread.sleep(4000);
  }
	
	public boolean existCase(int numCase)throws Exception{
		ExtJSGrid grid;
		Browser.driver().switchTo().frame("casesFrame");
		Browser.driver().switchTo().frame("casesSubFrame");
		grid = new ExtJSGrid(Browser.driver().findElement(By.id("casesGrid")), Browser.driver());
		WebElement row = grid.getRowByColumnValue("#", Integer.toString(numCase));
		
		if(row==null)
			return false;
		else
			return true;
	}
	
	public boolean caseStatus(int numCase, String statusCase)throws Exception{
		ExtJSGrid grid;
		Browser.driver().switchTo().frame("casesFrame");
		Browser.driver().switchTo().frame("casesSubFrame");
		grid = new ExtJSGrid(Browser.driver().findElement(By.id("casesGrid")), Browser.driver());
		
		WebElement row = grid.getRowByColumnsValue("#", "Status", Integer.toString(numCase), statusCase);
		
		if(row==null)
			return false;
		else
			return true;
	}
	

	public void goCaseSubFrame()throws Exception{
		Browser.driver().switchTo().frame("casesFrame");
		Browser.driver().switchTo().frame("casesSubFrame");
	}

	public boolean isGridPresent(String grd) throws Exception{
		ExtJSGrid grid = null;
		grid = new ExtJSGrid(Browser.driver().findElement(By.id(grd)), Browser.driver());
		if(grid==null)
			throw new Exception("Grid: "+grd+" not found");
		else
			return true;
	}

}