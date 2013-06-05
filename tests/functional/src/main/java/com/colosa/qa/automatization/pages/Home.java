package com.colosa.qa.automatization.pages;

import com.colosa.qa.automatization.common.BrowserInstance;
import com.colosa.qa.automatization.common.extJs.ExtJSGrid;
import com.colosa.qa.automatization.common.extJs.ExtJSTree;
import com.colosa.qa.automatization.common.extJs.ExtJSTreeNode;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;


public class Home extends Page{

    protected int numCol;
    protected String caseNum = "";
    WebElement treeWebElement = null;

    public Home(BrowserInstance browser) throws Exception {
        super(browser);

        //verify that browser is in the Home page.
        verifyPage();

        //search root tree element

        //goto iframe  of cases:
        //all the tasks are done in this frame so from the beginning we change of frame.
        //but don't assume that it's going to be maintained in this frame
        browser.switchToFrame("casesFrame");
        //WebElement treeWebElement = browser.findElementById("tree-panel");
        //WebElement rootNode = browser.findElementByCssSelector("ul.x-tree-root-ct");

        //html.ext-strict body#ext-gen3.ext-gecko div#menuTreePanel.x-panel div#ext-gen17.x-panel-bwrap div#ext-gen18.x-panel-body div#tree-panel.x-panel div#ext-gen35.x-panel-bwrap div#ext-gen36.x-panel-body ul#ext-gen37.x-tree-root-ct
        //div#tree-panel.x-panel div#ext-gen35.x-panel-bwrap div#ext-gen36.x-panel-body ul#ext-gen37.x-tree-root-ct
        //locate elements
        treeWebElement = browser.findElementById("tree-panel");

    }

    @Override
    public void verifyPage() throws Exception {
        browser.switchToDefaultContent();

        //verify if element is painted
        WebElement menu= browser.findElementById("pm_menu");
        WebElement homeButton = browser.findElementById("CASES");
        String classValue = homeButton.getAttribute("class");
        if(!classValue.contains("SelectedMenu")){
            throw new Exception("Invalid Page -> Home tab not selected");
        }

        //verify that we are in the Home page
        browser.findElementById("casesFrame");
    }

    private String[] pathToArray(String path){
        if(path.charAt(0) == '/')
            path = path.substring(1);
        if(path.charAt(path.length()-1) == '/')
            path = path.substring(0, path.length());

        return path.split("/");
    }

	public void selectMenuTreePanelOption(String path) throws Exception{
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
        browser.switchToDefaultContent();
        browser.waitForElement(By.id("casesFrame"),120);
		browser.switchToFrame("casesFrame");
		/*else
		{
			this.waitForElementPresent(By.id("pm-frame-cases"),this.timeout);
			browser.switchToFrame("pm-frame-cases");
		}*/

		if(pathLevels.length==2 || path.charAt(path.length()-1)=='/')
			wel = browser.findElementsByXPath("//div[@id='tree-panel']/div/div/ul/div/li");
		else
			wel = browser.findElementsByXPath("//div[@id='tree-panel']/div/div/ul/div/li/ul/li");
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

		browser.switchToDefaultContent();
	}

	public NewCase gotoNewCase() throws Exception{
        ExtJSTree casesListsTree = new ExtJSTree( treeWebElement, browser.getInstanceDriver());

        ExtJSTreeNode resultWebElement = casesListsTree.gotoNode("Cases/New case");

        System.out.println("Returned Node found:" + resultWebElement.getNodeText());

        resultWebElement.click();

		//selectMenuTreePanelOption("Cases/New case");
        //create new instance of NewCase class
        NewCase newCase = new NewCase(browser);

        return newCase;
	}

	public Inbox gotoInbox() throws Exception{
        ExtJSTree casesListsTree = new ExtJSTree( treeWebElement, browser.getInstanceDriver());
        ExtJSTreeNode resultWebElement = casesListsTree.gotoNode("Cases/Inbox \\(.*\\)", true);

        resultWebElement.click();

        Inbox inbox = new Inbox(browser);

        return inbox;

		//selectMenuTreePanelOption("Cases/Inbox");
        //numCol = 7;
	}

	public Draft gotoDraft() throws Exception{
        ExtJSTree casesListsTree = new ExtJSTree( treeWebElement, browser.getInstanceDriver());
        ExtJSTreeNode resultWebElement = casesListsTree.gotoNode("Cases/Draft \\(.*\\)", true);

        resultWebElement.click();

        Draft draft = new Draft(browser);

        return draft;

		//selectMenuTreePanelOption("Cases/Draft");
        //numCol = 7;
	}

	public Participated gotoParticipated() throws Exception{
        ExtJSTree casesListsTree = new ExtJSTree( treeWebElement, browser.getInstanceDriver());
        ExtJSTreeNode resultWebElement = casesListsTree.gotoNode("Cases/Participated \\(.*\\)", true);

        resultWebElement.click();

        Participated participated = new Participated(browser);

        return participated;

		//selectMenuTreePanelOption("Cases/Participated");
        //numCol = 10;
	}

	public Unassigned gotoUnassigned() throws Exception{
        ExtJSTree casesListsTree = new ExtJSTree( treeWebElement, browser.getInstanceDriver());
        ExtJSTreeNode resultWebElement = casesListsTree.gotoNode("Cases/Unassigned \\(.*\\)", true);

        resultWebElement.click();

        Unassigned unassigned = new Unassigned(browser);

        return unassigned;
		//selectMenuTreePanelOption("Cases/Unassigned");
        //numCol = 7;
	}

	public Paused gotoPaused() throws Exception{
        ExtJSTree casesListsTree = new ExtJSTree( treeWebElement, browser.getInstanceDriver());
        ExtJSTreeNode resultWebElement = casesListsTree.gotoNode("Cases/Paused \\(.*\\)", true);

        resultWebElement.click();

        Paused paused = new Paused(browser);

        return paused;
		//selectMenuTreePanelOption("Cases/Paused");
        //numCol = 7;
	}

	public AdvancedSearch gotoAdvancedSearch() throws Exception{
        ExtJSTree casesListsTree = new ExtJSTree( treeWebElement, browser.getInstanceDriver());
        ExtJSTreeNode resultWebElement = casesListsTree.gotoNode("Search/Advanced Search");

        resultWebElement.click();

        AdvancedSearch advancedSearch = new AdvancedSearch(browser);

        return advancedSearch;
		//selectMenuTreePanelOption("Search/Advanced Search");
	}

	public SupervisorReview gotoReview() throws Exception{
        ExtJSTree casesListsTree = new ExtJSTree( treeWebElement, browser.getInstanceDriver());
        ExtJSTreeNode resultWebElement = casesListsTree.gotoNode("Process Supervisor/Review");

        resultWebElement.click();

        SupervisorReview supervisorReview = new SupervisorReview(browser);

        return supervisorReview;

		//selectMenuTreePanelOption("Process Supervisor/Review");


	}

	public SupervisorReassign gotoReassign() throws Exception{
        ExtJSTree casesListsTree = new ExtJSTree( treeWebElement, browser.getInstanceDriver());
        ExtJSTreeNode resultWebElement = casesListsTree.gotoNode("Process Supervisor/Reassign");

        resultWebElement.click();

        SupervisorReassign supervisorReassign = new SupervisorReassign(browser);

        return supervisorReassign;

		//selectMenuTreePanelOption("Process Supervisor/Reassign");
	}

	public Documents gotoDocuments() throws Exception{
        ExtJSTree casesListsTree = new ExtJSTree( treeWebElement, browser.getInstanceDriver());
        ExtJSTreeNode resultWebElement = casesListsTree.gotoNode("Documents");

        resultWebElement.click();

        Documents documents = new Documents(browser);

        return documents;

        //selectMenuTreePanelOption("Documents/");
	}

	public void gotoReports() throws Exception{
        ExtJSTree casesListsTree = new ExtJSTree( treeWebElement, browser.getInstanceDriver());
        casesListsTree.gotoNode("Documents/Reports");
		//selectMenuTreePanelOption("Documents/Reports");
	}

	public void openCase(int numCase)throws Exception{
        /*
        caseNum = ""+Integer.toString(numCase);
        ExtJSGrid grid;
		Actions action = new Actions(browser.getInstanceDriver());
		browser.switchToDefaultContent();
        browser.waitForElement(By.id("casesFrame"),120);
		browser.switchToFrame("casesFrame");
		browser.switchToFrame("casesSubFrame");
        grid = new ExtJSGrid(browser.findElementById("casesGrid"), browser.getInstanceDriver());
		WebElement row = grid.getRowByColumnValue("#", Integer.toString(numCase));
		if(row==null)
			throw new Exception("Case # "+Integer.toString(numCase)+" not found in Inbox folder");
		action.doubleClick(row.findElement(By.xpath("table/tbody/tr/td[div='"+Integer.toString(numCase)+"']/div")));
        action.perform();*/
	}

    public int openFirstCase()throws Exception{

        WebElement grid;

        int value;

        Actions action = new Actions(browser.getInstanceDriver());

        browser.switchToDefaultContent();
        browser.waitForElement(By.id("casesFrame"),120);

        browser.switchToFrame("casesFrame");

        browser.switchToFrame("casesSubFrame");

        grid = browser.findElementById("casesGrid");

        WebElement row = grid.findElement(By.xpath("div/div[2]/div/div[1]/div[2]/div/div[1]/table/tbody/tr/td[3]/div"));

        action.doubleClick(row);

        action.perform();

        value = Integer.parseInt(browser.findElementByXPath("//div[@id='caseTabPanel']/div[1]/div[1]/ul/li[@id='caseTabPanel__casesTab']").getText().trim().substring(8));

        return value;

    }

	public boolean selectCase(int numCase)throws Exception{
		ExtJSGrid grid;
		Actions action = new Actions(browser.getInstanceDriver());
		browser.switchToDefaultContent();
		browser.switchToFrame("casesFrame");
		browser.switchToFrame("casesSubFrame");
		grid = new ExtJSGrid(browser.findElementById("casesGrid"), browser.getInstanceDriver());
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
		Actions action = new Actions(browser.getInstanceDriver());
		browser.switchToDefaultContent();
		browser.switchToFrame("casesFrame");
		browser.switchToFrame("casesSubFrame");
		grid = new ExtJSGrid(browser.findElementById("reassign-form"), browser.getInstanceDriver(),45);
		WebElement row = grid.getRowByColumnValue("#", Integer.toString(numCase));
		if(row==null)
			throw new Exception("Case # "+Integer.toString(numCase)+" not found in Inbox folder");
		action.click(row.findElement(By.xpath("table/tbody/tr/td[div='"+Integer.toString(numCase)+"']/div")));
		action.perform();
  }
  
    public void pauseCase(int numCase)throws Exception{
		
		Actions action = new Actions(browser.getInstanceDriver());
		browser.switchToDefaultContent();
		browser.switchToFrame("casesFrame");
		browser.switchToFrame("casesSubFrame");
		
		Thread.sleep(4000);
		WebElement actionPanel = browser.findElementById("navPanel");
		WebElement actionMenu = actionPanel.findElement(By.id("actionMenu"));
		WebElement bAction = actionMenu.findElement(By.tagName("button"));
		bAction.click(); 
		
		Thread.sleep(4000);
		WebElement pauseCase = browser.findElementByXPath("/html/body/div[5]/ul/li[1]/a");
		WebElement pCase = pauseCase.findElement(By.tagName("span"));
		pCase.click(); 
		
		Thread.sleep(4000);
		WebElement pauseForm = browser.findElementById("unpauseFrm");
		WebElement pauseButton = pauseForm.findElement(By.id("submitPauseCase"));
		WebElement pauseClick = pauseButton.findElement(By.tagName("button"));
		
		pauseClick.click();
		Thread.sleep(4000);
  }
	
	public boolean existCase(int numCase)throws Exception{
		ExtJSGrid grid;
		browser.switchToFrame("casesFrame");
		browser.switchToFrame("casesSubFrame");
/*        WebElement grd = browser.findElementById("casesGrid");
        WebElement inputText = grd.findElement(By.xpath("div/div[1]/div/table/tbody/tr/td[2]/table/tbody/tr/td[1]/table/tbody/tr/td["+Integer.toString(numCol)+"]/input"));
        inputText.sendKeys(caseNum);
        inputText.sendKeys(Keys.RETURN);
        inputText.clear();*/
		grid = new ExtJSGrid(browser.findElementById("casesGrid"), browser.getInstanceDriver());
		WebElement row = grid.getRowByColumnValue("#", Integer.toString(numCase));
		
		if(row==null)
			return false;
		else
			return true;
	}
	
	public boolean caseStatus(int numCase, String statusCase)throws Exception{
		ExtJSGrid grid;
		browser.switchToFrame("casesFrame");
		browser.switchToFrame("casesSubFrame");
		grid = new ExtJSGrid(browser.findElementById("casesGrid"), browser.getInstanceDriver());
		
		WebElement row = grid.getRowByColumnsValue("#", "Status", Integer.toString(numCase), statusCase);
		
		if(row==null)
			return false;
		else
			return true;
	}
	

	public void goCaseSubFrame()throws Exception{
		browser.switchToFrame("casesFrame");
		browser.switchToFrame("casesSubFrame");
	}

	public boolean isGridPresent(String grd) throws Exception{
		ExtJSGrid grid = null;
		grid = new ExtJSGrid(browser.findElementById(grd), browser.getInstanceDriver());
		if(grid==null)
			throw new Exception("Grid: "+grd+" not found");
		else
			return true;
	}

    /*
	public int openFirstCase()throws Exception{
	  WebElement grid;
	  int value;
	  Actions action = new Actions(Browser.driver());
	  browser.switchToDefaultContent();
	  browser.switchToFrame("casesFrame");
	  browser.switchToFrame("casesSubFrame");
	  grid = browser.findElementById("casesGrid");
	  WebElement row = grid.findElement(By.xpath("div/div[2]/div/div[1]/div[2]/div/div[1]/table/tbody/tr/td[3]/div"));
	  action.doubleClick(row);
	        action.perform();
	        value = Integer.parseInt(browser.findElementByXPath("//div[@id='caseTabPanel']/div[1]/div[1]/ul/li[@id='caseTabPanel__casesTab']")).getText().trim().substring(8 ));
	        return value;
	 }*/

}