package com.colosa.qa.automatization.tests.PMFields;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class AllFieldsTest extends com.colosa.qa.automatization.tests.common.Test{

	protected int caseNum;
	protected String country = "Bolivia";
	protected String state = "Chuquisaca";
	protected String location = "Sucre";
	protected String state1 = "La Paz";
	protected String location1 = "Apolo";

	protected String country2 = "Argentina";
	protected String state2 = "Jujuy";
	protected String location2 = "San Pedro";
	protected String state22 = "Chubut";
	protected String location22 = "Trelew";

	protected String userName = "amy";
	protected String completeName = "Amy Connelly";
	protected String userDepartment = "Administrative Division";

	protected String userName1 = "admin";
	protected String completeName1 = "Administrator  ";
	protected String userDepartment1 = "";

    public AllFieldsTest(String browserName) throws IOException {
        super(browserName);
    }

    @Test
	public void allFieldsTest() throws FileNotFoundException, IOException, Exception{

		pages.Login().gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goHome();
		caseNum = pages.Home().gotoNewCase().startCase("FormularioTodosCampos (Task 1)");
		
		pages.DynaformExecution().intoDynaform();

		//test textfield
		pages.DynaformExecution().setFieldValue("textField", "Herbert");
		String fieldValue = pages.DynaformExecution().getFieldValue("textField");
		Assert.assertEquals(fieldValue, "Herbert");

		//test currencyfield
		pages.DynaformExecution().setFieldValue("currencyField", "43.14");
		fieldValue = pages.DynaformExecution().getFieldValue("currencyField");
		Assert.assertEquals(fieldValue, "43.14");

		//test percentageField
		pages.DynaformExecution().setFieldValue("percentageField", "43");
		fieldValue = pages.DynaformExecution().getFieldValue("percentageField");
		Assert.assertEquals(fieldValue, "43 %");

		//test passwordField
		pages.DynaformExecution().setFieldValue("passwordField", "herbert");
		fieldValue = pages.DynaformExecution().getFieldValue("passwordField");
		Assert.assertEquals(fieldValue, "herbert");
		
		//test suggestField
		pages.DynaformExecution().setFieldValue("suggestField", "Bolivia");
		fieldValue = pages.DynaformExecution().getFieldValue("suggestField");
		Assert.assertEquals(fieldValue, "BO");
		fieldValue = pages.DynaformExecution().getFieldText("suggestField");
		Assert.assertEquals(fieldValue, "Bolivia");

		//test textAreaField
		pages.DynaformExecution().setFieldValue("textAreaField", "Bolivia linda \\n");
		fieldValue = pages.DynaformExecution().getFieldValue("textAreaField");
		Assert.assertEquals(fieldValue, "Bolivia linda \\n");

		//test buttonField
		pages.DynaformExecution().setFieldValue("buttonField", "");
		
		//test dropdownField
		pages.DynaformExecution().setFieldValue("dropdownField", "dos");
		fieldValue = pages.DynaformExecution().getFieldValue("dropdownField");
		Assert.assertEquals(fieldValue, "2");		

		//test yesNoField
		pages.DynaformExecution().setFieldValue("yesNoField", "Yes");
		fieldValue = pages.DynaformExecution().getFieldValue("yesNoField");
		Assert.assertEquals(fieldValue, "1");

		//test listBoxField
		pages.DynaformExecution().setFieldValue("listBoxField", "tres");
		fieldValue = pages.DynaformExecution().getFieldValue("listBoxField");
		Assert.assertEquals(fieldValue, "3");	

		//test checkBoxField
		pages.DynaformExecution().setFieldValue("checkBoxField", "");
		fieldValue = pages.DynaformExecution().getFieldValue("checkBoxField");
		Assert.assertEquals(fieldValue, "true");

		//test datefield
		pages.DynaformExecution().setFieldValue("dateField", "2013-01-18");
		String dateValue = pages.DynaformExecution().getFieldValue("dateField");
		Assert.assertEquals(dateValue, "2013-01-18");

		//test hiddenField
		pages.DynaformExecution().setFieldValue("hiddenField", "Herbert");
		fieldValue = pages.DynaformExecution().getFieldValue("hiddenField");
		Assert.assertEquals(fieldValue, "hiddenField");

		//test linkField
		pages.DynaformExecution().setFieldValue("linkField", "");

		//test fileField
		//pages.DynaformExecution().setFieldValue("fileField", "c:\\herbert\\Saal");


		pages.DynaformExecution().gridAddNewRow("gridField");

		//form[gridField][1][gridTextField]
		pages.DynaformExecution().setGridFieldValue("gridField", 1, "gridTextField", "hola");

		String gridValue =  pages.DynaformExecution().getGridFieldValue("gridField", 1, "gridTextField");

		Assert.assertEquals(gridValue, "hola");

		//form[gridField][2][gridCurrencyField]
		pages.DynaformExecution().setGridFieldValue("gridField", 2, "gridCurrencyField", "43.14");

		gridValue =  pages.DynaformExecution().getGridFieldValue("gridField", 2, "gridCurrencyField");

		Assert.assertEquals(gridValue, "43.14");

		//form[gridField][1][gridPercentageField]
		pages.DynaformExecution().setGridFieldValue("gridField", 2, "gridPercentageField", "43.14");

		gridValue =  pages.DynaformExecution().getGridFieldValue("gridField", 2, "gridPercentageField");

		Assert.assertEquals(gridValue, "43.14 %");

		//form[gridField][1][gridTextAreaField]
		pages.DynaformExecution().setGridFieldValue("gridField", 2, "gridTextAreaField", "Hola Mundo!!!!");

		gridValue =  pages.DynaformExecution().getGridFieldValue("gridField", 2, "gridTextAreaField");

		Assert.assertEquals(gridValue, "Hola Mundo!!!!");


		//form[gridField][1][gridDropDownField]
		pages.DynaformExecution().setGridFieldValue("gridField", 2, "gridDropDownField", "dos");

		gridValue =  pages.DynaformExecution().getGridFieldValue("gridField", 2, "gridDropDownField");

		Assert.assertEquals(gridValue, "2");

		//form[gridField][1][gridYesNoField]
		pages.DynaformExecution().setGridFieldValue("gridField", 1, "gridYesNoField", "Yes");

		gridValue =  pages.DynaformExecution().getGridFieldValue("gridField", 1, "gridYesNoField");

		Assert.assertEquals(gridValue, "1");


		//form[gridField][1][gridCheckBoxField]
		pages.DynaformExecution().setGridFieldValue("gridField", 1, "gridCheckBoxField", "Yes");
		gridValue =  pages.DynaformExecution().getGridFieldValue("gridField", 1, "gridCheckBoxField");
		Assert.assertEquals(gridValue, "true");
		gridValue =  pages.DynaformExecution().getGridFieldValue("gridField", 2, "gridCheckBoxField");
		Assert.assertEquals(gridValue, "false");

		//form[gridField][1][gridDateField]
		pages.DynaformExecution().setGridFieldValue("gridField", 2, "gridDateField", "2013-04-05");
		gridValue =  pages.DynaformExecution().getGridFieldValue("gridField", 2, "gridDateField");
		Assert.assertEquals(gridValue, "2013-04-05");

		pages.InputDocProcess().switchToDefault();
		pages.Main().logout();
	}

    @After
    public void cleanup(){
        browserInstance.quit();
    }

}