package com.colosa.qa.automatization.tests.PMFields;

import com.colosa.qa.automatization.common.WaitTool;
import com.colosa.qa.automatization.pages.DynaformExecution;
import com.colosa.qa.automatization.pages.Home;
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

		pages.gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		Home home = pages.Main().goHome();
		caseNum = home.gotoNewCase().startCase("FormularioTodosCampos (Task 1)");
		
		DynaformExecution form = pages.DynaformExecution();
        form.addPMFieldToForm("hiddenField");
        form.intoDynaform();

		//test textfield
		form.setFieldValue("textField", "Herbert");
		String fieldValue = form.getFieldValue("textField");
		Assert.assertEquals(fieldValue, "Herbert");

		//test currencyfield
		form.setFieldValue("currencyField", "43.14");
		fieldValue = form.getFieldValue("currencyField");
		Assert.assertEquals(fieldValue, "43.14");

		//test percentageField
		form.setFieldValue("percentageField", "43");
		fieldValue = form.getFieldValue("percentageField");
		Assert.assertEquals(fieldValue, "43 %");

		//test passwordField
		form.setFieldValue("passwordField", "herbert");
		fieldValue = form.getFieldValue("passwordField");
		Assert.assertEquals(fieldValue, "herbert");
		
		//test suggestField
		form.setFieldValue("suggestField", "Bolivia");
        WaitTool.waitForValueToChange(browserInstance.getInstanceDriver(), form.getPMField("suggestField").getWebElement(), "", 5);
        fieldValue = form.getFieldValue("suggestField");
		Assert.assertEquals(fieldValue, "BO");
		fieldValue = form.getFieldText("suggestField");
		Assert.assertEquals(fieldValue, "Bolivia");

		//test textAreaField
		form.setFieldValue("textAreaField", "Bolivia linda \\n");
		fieldValue = form.getFieldValue("textAreaField");
		Assert.assertEquals(fieldValue, "Bolivia linda \\n");

		//test buttonField
		form.setFieldValue("buttonField", "");
		
		//test dropdownField
		form.setFieldValue("dropdownField", "dos");
		fieldValue = form.getFieldValue("dropdownField");
		Assert.assertEquals(fieldValue, "2");		

		//test yesNoField
		form.setFieldValue("yesNoField", "Yes");
		fieldValue = form.getFieldValue("yesNoField");
		Assert.assertEquals(fieldValue, "1");

		//test listBoxField
		form.setFieldValue("listBoxField", "tres");
		fieldValue = form.getFieldValue("listBoxField");
		Assert.assertEquals(fieldValue, "3");	

		//test checkBoxField
		form.setFieldValue("checkBoxField", "");
		fieldValue = form.getFieldValue("checkBoxField");
		Assert.assertEquals(fieldValue, "true");

        //test checkgroup
        form.setCheckBoxGroupField("checkGroupField", "1");
        form.setCheckBoxGroupField("checkGroupField", "2");
        form.setCheckBoxGroupField("checkGroupField", "2");
        form.setCheckBoxGroupField("checkGroupField", "3");
        Assert.assertEquals(form.getCheckBoxGroupFieldValue("checkGroupField","1"), "true");
        Assert.assertEquals(form.getCheckBoxGroupFieldValue("checkGroupField","2"), "false");
        Assert.assertEquals(form.getCheckBoxGroupFieldValue("checkGroupField","3"), "true");

        form.setRadioButtonGroupField("radioGroupField", "1");
        form.setRadioButtonGroupField("radioGroupField", "2");
        form.setRadioButtonGroupField("radioGroupField", "3");
        Assert.assertEquals(form.getRadioButtonGroupFieldValue("radioGroupField","1"), "false");
        Assert.assertEquals(form.getCheckBoxGroupFieldValue("radioGroupField","2"), "false");
        Assert.assertEquals(form.getCheckBoxGroupFieldValue("radioGroupField","3"), "true");

		//test datefield
		form.setFieldValue("dateField", "2013-01-18");
		String dateValue = form.getFieldValue("dateField");
		Assert.assertEquals(dateValue, "2013-01-18");

		//test hiddenField
		form.setFieldValue("hiddenField", "Herbert");
		fieldValue = form.getFieldValue("hiddenField");
		Assert.assertEquals(fieldValue, "hiddenField");

		//test linkField
		form.clickButton("linkField");

		//test fileField
		//form.setFieldValue("fileField", "c:\\herbert\\Saal");

		form.gridAddNewRow("gridField");

		//form[gridField][1][gridTextField]
		form.setGridFieldValue("gridField", 1, "gridTextField", "hola");

		String gridValue =  form.getGridFieldValue("gridField", 1, "gridTextField");

		Assert.assertEquals(gridValue, "hola");

		//form[gridField][2][gridCurrencyField]
		form.setGridFieldValue("gridField", 2, "gridCurrencyField", "43.14");

		gridValue =  form.getGridFieldValue("gridField", 2, "gridCurrencyField");

		Assert.assertEquals(gridValue, "43.14");

		//form[gridField][1][gridPercentageField]
		form.setGridFieldValue("gridField", 2, "gridPercentageField", "43.14");

		gridValue =  form.getGridFieldValue("gridField", 2, "gridPercentageField");

		Assert.assertEquals(gridValue, "43.14 %");

		//form[gridField][1][gridTextAreaField]
		form.setGridFieldValue("gridField", 2, "gridTextAreaField", "Hola Mundo!!!!");

		gridValue =  form.getGridFieldValue("gridField", 2, "gridTextAreaField");

		Assert.assertEquals(gridValue, "Hola Mundo!!!!");


		//form[gridField][1][gridDropDownField]
		form.setGridFieldValue("gridField", 2, "gridDropDownField", "dos");

		gridValue =  form.getGridFieldValue("gridField", 2, "gridDropDownField");

		Assert.assertEquals(gridValue, "2");

		//form[gridField][1][gridYesNoField]
		form.setGridFieldValue("gridField", 1, "gridYesNoField", "Yes");

		gridValue =  form.getGridFieldValue("gridField", 1, "gridYesNoField");

		Assert.assertEquals(gridValue, "1");


		//form[gridField][1][gridCheckBoxField]
		form.setGridFieldValue("gridField", 1, "gridCheckBoxField", "Yes");
		gridValue =  form.getGridFieldValue("gridField", 1, "gridCheckBoxField");
		Assert.assertEquals(gridValue, "true");
		gridValue =  form.getGridFieldValue("gridField", 2, "gridCheckBoxField");
		Assert.assertEquals(gridValue, "false");

		//form[gridField][1][gridDateField]
		form.setGridFieldValue("gridField", 2, "gridDateField", "2013-04-05");
		gridValue =  form.getGridFieldValue("gridField", 2, "gridDateField");
		Assert.assertEquals(gridValue, "2013-04-05");

		//test the second grid
        form.gridAddNewRow("grid2");
        form.gridAddNewRow("grid2");
        form.gridAddNewRow("grid2");

        //test suggest field
        form.setGridFieldValue("grid2", 2, "gridSuggestField", "Argentina");
        //WaitTool.waitForValueToChange(browserInstance.getInstanceDriver(), form.getPMField("suggestField").getWebElement(), "", 5);
        Assert.assertEquals(form.getGridFieldValue("grid2", 2, "gridSuggestField"), "AR");
        //fieldValue = form.getFieldText("suggestField");
        Assert.assertEquals(form.getGridFieldtext("grid2", 2, "gridSuggestField"), "Argentina");

        //test hidden field
        form.setGridFieldValue("grid2", 1, "gridHiddenField", "Herbert");
        Assert.assertEquals(form.getGridFieldValue("grid2", 1, "gridHiddenField"), "Herbert");

        //test link field
        //form[grid2][1][gridLinkField]


		//pages.InputDocProcess().switchToDefault();
		//pages.Main().logout();


	}

    @After
    public void cleanup(){
        browserInstance.quit();
    }

}