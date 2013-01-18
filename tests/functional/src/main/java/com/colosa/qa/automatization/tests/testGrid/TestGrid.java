package com.colosa.qa.automatization.tests.testGrid;

import org.junit.Assert;
import org.junit.AfterClass;
import org.junit.Test;

import com.colosa.qa.automatization.pages.*;
import com.colosa.qa.automatization.common.*;
import org.openqa.selenium.WebElement;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestGrid{

	protected static int caseNum;
	@Test
	public void runProcess()throws FileNotFoundException, IOException, Exception{

		Pages.Login().gotoUrl();
		Pages.Login().loginUser("admin", "admin", "workflow");
		Pages.Main().goHome();	
		caseNum = Pages.Home().startCase("Grid Test (Task 1)");
		Pages.DynaformExecution().intoDynaform();
		Pages.DynaformExecution().gridAddNewRow("grd1");
		Pages.DynaformExecution().gridAddNewRow("grd1");
		Pages.DynaformExecution().gridAddNewRow("grd2");
		Pages.DynaformExecution().gridAddNewRow("grd2");
		Pages.DynaformExecution().gridAddNewRow("grd2");
		Pages.DynaformExecution().gridAddNewRow("grd2");
		Pages.DynaformExecution().gridAddNewRow("grd2");
		
		//int nRows = Pages.DynaformExecution().gridCountRows("grd1");
		//System.out.println("El numero de filas es: "+nRows);
		Pages.DynaformExecution().gridDeleteRow("grd2",2);
		//Pages.DynaformExecution().gridDeleteRow("grd2",3);*/
		Pages.DynaformExecution().nextStepLink();
	}

}