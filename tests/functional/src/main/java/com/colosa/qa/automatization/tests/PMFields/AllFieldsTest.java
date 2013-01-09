package com.colosa.qa.automatization.tests.PMFields;

import org.junit.Assert;
import org.junit.AfterClass;
import org.junit.Test;

import com.colosa.qa.automatization.pages.*;
import com.colosa.qa.automatization.common.*;
import org.openqa.selenium.WebElement;

import java.io.FileNotFoundException;
import java.io.IOException;

public class AllFieldsTest{

	protected static int caseNum;
	protected static String country = "Bolivia";
	protected static String state = "Chuquisaca";
	protected static String location = "Sucre";
	protected static String state1 = "La Paz";
	protected static String location1 = "Apolo";

	protected static String country2 = "Argentina";
	protected static String state2 = "Jujuy";
	protected static String location2 = "San Pedro";
	protected static String state22 = "Chubut";
	protected static String location22 = "Trelew";

	protected static String userName = "amy";
	protected static String completeName = "Amy Connelly";
	protected static String userDepartment = "Administrative Division";

	protected static String userName1 = "admin";
	protected static String completeName1 = "Administrator  ";
	protected static String userDepartment1 = "";

	@Test
	public void allFieldsTest() throws FileNotFoundException, IOException, Exception{

		Pages.Login().gotoUrl();
		Pages.Login().loginUser("admin", "admin", "workflow");
		Pages.Main().goHome();	
		caseNum = Pages.Home().startCase("FormularioTodosCampos (Task 1)");
		
		Pages.DynaformExecution().intoDynaform();

		//form[gridField][1][gridTextField]
		Pages.DynaformExecution().setGridFieldValue("gridField", 1, "gridTextField", "hola");

		String gridValue =  Pages.DynaformExecution().getGridFieldValue("gridField", 1, "gridTextField");

		Assert.assertEquals(gridValue, "hola");

		Pages.DynaformExecution().outDynaform();

		Pages.Main().logout();
	}


}