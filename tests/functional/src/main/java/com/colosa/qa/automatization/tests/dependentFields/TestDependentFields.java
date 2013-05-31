package com.colosa.qa.automatization.tests.dependentFields;

import com.colosa.qa.automatization.common.FieldKeyType;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestDependentFields extends com.colosa.qa.automatization.tests.common.Test{

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

    public TestDependentFields(String browser) throws IOException {
        super(browser);
    }

    @Before
    public void setup(){

    }

    @After
    public void cleanup(){
        browserInstance.quit();
    }

    @Test
	public void testDependentFieldsCase() throws FileNotFoundException, IOException, Exception{

        System.out.println("Test testDependentFieldsCase with browserName:" + this.browserName);

        pages.Login().gotoDefaultUrl();

        pages.Login().loginUser("admin", "admin", "workflow", "English");
        pages.Main().goHome();

		caseNum = pages.Home().gotoNewCase().startCase("TestDependentFields (Task 1)");

        pages.DynaformExecution().intoDynaform();

		//pages.DynaformExecution().setFieldValue("country", country, FieldType.DROPDOWN);
		pages.DynaformExecution().setFieldValue("country", country);

		//pages.DynaformExecution().setGridFieldValue("gridname", numerofila, "country", country, FieldType.DROPDOWN);
		//sleep one second to wait ajax result

        //wait for combo to populate
        pages.DynaformExecution().waitForFieldToBeClickable("state", 2);
        pages.DynaformExecution().waitForFieldToBeClickable("location", 2);

        //pages.DynaformExecution().sleep(1000);

		Assert.assertEquals(pages.DynaformExecution().getFieldText("state"), state);
		Assert.assertEquals(pages.DynaformExecution().getFieldText("location"), location);

		//pages.DynaformExecution().setFieldValue("suggest_country", country, FieldType.SUGGEST);
		pages.DynaformExecution().setFieldValue("suggest_country", country);

		//pages.DynaformExecution().sleep(1000);
        pages.DynaformExecution().waitForFieldToBeClickable("suggest_state", 2);
        pages.DynaformExecution().waitForFieldToBeClickable("suggest_location", 2);
        pages.DynaformExecution().waitForFieldToBeClickable("suggest_state2", 2);
        pages.DynaformExecution().waitForFieldToBeClickable("suggest_location2", 2);

		Assert.assertEquals(pages.DynaformExecution().getDropdownFieldText("suggest_state"), state);
		Assert.assertEquals(pages.DynaformExecution().getDropdownFieldText("suggest_location"), location);
		Assert.assertEquals(pages.DynaformExecution().getDropdownFieldText("suggest_state2"), state);
		Assert.assertEquals(pages.DynaformExecution().getDropdownFieldText("suggest_location2"), location);

		//pages.DynaformExecution().setFieldValue("username", userName, FieldType.TEXTBOX);
		pages.DynaformExecution().setFieldValue("username", userName);
		//send tab to activate dependent fields
        //pages.DynaformExecution().sendTab("userCompleteName");
		pages.DynaformExecution().sendTab("username");
		//pages.DynaformExecution().sleep(1000);
        browserInstance.waitForDocumentCompleted(20);
        //pages.DynaformExecution().waitForFieldToBeClickable("userCompleteName", 2);
        //pages.DynaformExecution().waitForFieldToBeClickable("userDepartment", 2);

		Assert.assertEquals(pages.DynaformExecution().getFieldValue("userCompleteName"), completeName);
		Assert.assertEquals(pages.DynaformExecution().getFieldValue("userDepartment"), userDepartment);
		
		//change values in fields
		//pages.DynaformExecution().setFieldValue("state", state1, FieldType.DROPDOWN);
		pages.DynaformExecution().setFieldValue("state", state1);
		//pages.DynaformExecution().sleep(1000);
        pages.DynaformExecution().waitForFieldToBeClickable("location", 2);
		Assert.assertEquals(pages.DynaformExecution().getDropdownFieldText("location"), location1);

		//change fields in suggest
		//pages.DynaformExecution().setFieldValue("suggest_country", country2, FieldType.SUGGEST);
		pages.DynaformExecution().setFieldValue("suggest_country", country2);
		//pages.DynaformExecution().sleep(1000);
        pages.DynaformExecution().waitForFieldToBeClickable("suggest_state", 2);
		//pages.DynaformExecution().setFieldValue("suggest_state", state2, FieldType.DROPDOWN);
		pages.DynaformExecution().setFieldValue("suggest_state", state2);
		//pages.DynaformExecution().sleep(1000);
        pages.DynaformExecution().waitForFieldToBeClickable("suggest_location", 2);
		//pages.DynaformExecution().setFieldValue("suggest_location", location2, FieldType.DROPDOWN);
		pages.DynaformExecution().setFieldValue("suggest_location", location2);
		//pages.DynaformExecution().sleep(1000);
		Assert.assertEquals(pages.DynaformExecution().getDropdownFieldText("suggest_state"), state2);
		Assert.assertEquals(pages.DynaformExecution().getDropdownFieldText("suggest_location"), location2);
		//pages.DynaformExecution().setFieldValue("suggest_state2", state22, FieldType.DROPDOWN);
		pages.DynaformExecution().setFieldValue("suggest_state2", state22);
        pages.DynaformExecution().waitForFieldToBeClickable("suggest_location2", 2);
		//pages.DynaformExecution().setFieldValue("suggest_location2", location22, FieldType.DROPDOWN);
		pages.DynaformExecution().setFieldValue("suggest_location2", location22);
		//pages.DynaformExecution().sleep(1000);
		Assert.assertEquals(pages.DynaformExecution().getDropdownFieldText("suggest_state2"), state22);
		Assert.assertEquals(pages.DynaformExecution().getDropdownFieldText("suggest_location2"), location22);

		//change fields in textbox
		//pages.DynaformExecution().setFieldValue("username", userName1, FieldType.TEXTBOX);
		pages.DynaformExecution().setFieldValue("username", userName1);
		//send tab to activate dependent fields
		pages.DynaformExecution().sendTab("username");
		//pages.DynaformExecution().sleep(1000);
        //pages.DynaformExecution().waitForFieldToBeClickable("userCompleteName", 2);
        //pages.DynaformExecution().waitForFieldToBeClickable("userDepartment", 2);
        browserInstance.waitForDocumentCompleted(20);
		Assert.assertEquals(pages.DynaformExecution().getFieldValue("userCompleteName"), completeName1);
		Assert.assertEquals(pages.DynaformExecution().getFieldValue("userDepartment"), userDepartment1);

		//test other dependent fields
		pages.DynaformExecution().setFieldValue("userName2", userName1); //admin
        pages.DynaformExecution().waitForFieldToBeClickable("textAreaField", 2);
		Assert.assertEquals(pages.DynaformExecution().getFieldValue("textAreaField"), completeName1);
		int listBoxCount = pages.DynaformExecution().getFieldCount("listBoxField");
		System.out.println("ListBox value:" + listBoxCount);
		//Assert.assertFalse(pages.DynaformExecution().getFieldValue("listBoxField").equals("")); //at least one login
		String totalLogins = pages.DynaformExecution().getFieldValue("hiddenField");
		System.out.println("Total logins:" + totalLogins);
		Assert.assertEquals(listBoxCount, Integer.parseInt(totalLogins));

		//pages..switchToDefault();
		pages.Main().logout();
	}





}