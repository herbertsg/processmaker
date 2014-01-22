package com.colosa.qa.automatization.tests.dependentFields;

import com.colosa.qa.automatization.common.FieldKeyType;
import com.colosa.qa.automatization.common.Logger;
import com.colosa.qa.automatization.pages.DynaformExecution;
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

    protected String userName2 = "aaron";
    protected String completeName2 = "Aaron Laughlin";
    protected String userDepartment2 = "";

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

        Logger.addLog("Test testDependentFieldsCase with browserName:" + this.browserName);

        pages.gotoDefaultUrl();

        pages.Login().loginUser("admin", "admin", "workflow", "English");
        pages.Main().goHome();

		caseNum = pages.Home().gotoNewCase().startCase("TestDependentFields (Task 1)");

        DynaformExecution form = pages.DynaformExecution();
        form.intoDynaform();

		//form.setFieldValue("country", country, FieldType.DROPDOWN);
        form.setFieldValue("country", country);

		//form.setGridFieldValue("gridname", numerofila, "country", country, FieldType.DROPDOWN);
		//sleep one second to wait ajax result

        //wait for combo to populate
        form.waitForFieldToChangeText("state", "", 20);
        //.waitForFieldToBeClickable("state", 10);
        //form.waitForFieldToBeClickable("location", 10);
        //form.waitForFieldToChangeText("location", "", 20);

        //form.sleep(1000);

        Assert.assertEquals(form.getFieldText("state"), state);
		Assert.assertEquals(form.getFieldText("location"), location);

		//form.setFieldValue("suggest_country", country, FieldType.SUGGEST);
		form.setFieldValue("suggest_country", country);

		//form.sleep(1000);
        //form.waitForFieldToBeClickable("suggest_state", 10);
        //form.waitForFieldToBeClickable("suggest_location", 10);
        //form.waitForFieldToBeClickable("suggest_state2", 10);
        //form.waitForFieldToBeClickable("suggest_location2", 10);
        form.waitForFieldToChangeText("suggest_state", "", 10);
        form.waitForFieldToChangeText("suggest_location", "", 10);
        form.waitForFieldToChangeText("suggest_state2", "", 10);
        form.waitForFieldToChangeText("suggest_location2", "", 10);

		Assert.assertEquals(form.getFieldText("suggest_state"), state);
		Assert.assertEquals(form.getFieldText("suggest_location"), location);
		Assert.assertEquals(form.getFieldText("suggest_state2"), state);
		Assert.assertEquals(form.getFieldText("suggest_location2"), location);


		//form.setFieldValue("username", userName, FieldType.TEXTBOX);
		form.setFieldValue("username", userName);
		//send tab to activate dependent fields
        //form.sendTab("userCompleteName");
        form.sendTab("username");
        //form.waitForFieldToChangeText("userCompleteName", "", 15);

        //form.sleep(1000);
        //browserInstance.waitForDocumentCompleted(20);
        //form.waitForFieldToBeClickable("userCompleteName", 2);
        //form.waitForFieldToBeClickable("userDepartment", 2);
        form.waitForFieldToChangeValue("userCompleteName", "", 10);
        form.waitForFieldToChangeValue("userDepartment", "", 10);

		Assert.assertEquals(completeName, form.getFieldValue("userCompleteName"));
		Assert.assertEquals(userDepartment, form.getFieldValue("userDepartment"));

		//change values in fields
		//form.setFieldValue("state", state1, FieldType.DROPDOWN);
		form.setFieldValue("state", state1);
		//form.sleep(1000);
        //form.waitForFieldToBeClickable("location", 10);
        form.waitForFieldToChangeText("location", location, 10);
		Assert.assertEquals(form.getFieldText("location"), location1);

		//change fields in suggest
		//form.setFieldValue("suggest_country", country2, FieldType.SUGGEST);
		form.setFieldValue("suggest_country", country2);
		//form.sleep(1000);
        //form.waitForFieldToBeClickable("suggest_state", 10);
		//form.setFieldValue("suggest_state", state2, FieldType.DROPDOWN);
        form.waitForFieldToChangeText("suggest_state", state, 10);
		form.setFieldValue("suggest_state", state2);
		//form.sleep(1000);
        //form.waitForFieldToBeClickable("suggest_location", 10);
		//form.setFieldValue("suggest_location", location2, FieldType.DROPDOWN);
        form.waitForFieldToChangeText("suggest_location", "Arrecifes", 10);
		form.setFieldValue("suggest_location", location2);
		//form.sleep(1000);
		Assert.assertEquals(form.getFieldText("suggest_state"), state2);
		Assert.assertEquals(form.getFieldText("suggest_location"), location2);
		//form.setFieldValue("suggest_state2", state22, FieldType.DROPDOWN);
		form.setFieldValue("suggest_state2", state22);
        //form.waitForFieldToBeClickable("suggest_location2", 10);
		//form.setFieldValue("suggest_location2", location22, FieldType.DROPDOWN);
        form.waitForFieldToChangeText("suggest_location2", "Arrecifes", 10);
		form.setFieldValue("suggest_location2", location22);
		//form.sleep(1000);
		Assert.assertEquals(form.getFieldText("suggest_state2"), state22);
		Assert.assertEquals(form.getFieldText("suggest_location2"), location22);

		//change fields in textbox
		//form.setFieldValue("username", userName1, FieldType.TEXTBOX);
		form.setFieldValue("username", userName1);
		//send tab to activate dependent fields
		form.sendTab("username");
		//form.sleep(1000);
        //form.waitForFieldToBeClickable("userCompleteName", 2);
        //form.waitForFieldToBeClickable("userDepartment", 2);
        //browserInstance.waitForDocumentCompleted(20);
        form.waitForFieldToChangeValue("userCompleteName", completeName, 10);
		Assert.assertEquals(form.getFieldValue("userCompleteName"), completeName1);
		Assert.assertEquals(form.getFieldValue("userDepartment"), userDepartment1);

		//test other dependent fields
		form.setFieldValue("userName2", userName2); //admin
        //form.waitForFieldToBeClickable("textAreaField", 10);
        form.waitForFieldToChangeValue("textAreaField", "Administrator  ", 10);
		Assert.assertEquals(form.getFieldValue("textAreaField"), completeName2);
		int listBoxCount = form.getFieldCount("listBoxField");
		Logger.addLog("ListBox value:" + listBoxCount);
		//Assert.assertFalse(form.getFieldValue("listBoxField").equals("")); //at least one login
		String totalLogins = form.getFieldValue("totalLogins");
		Logger.addLog("Total logins:" + totalLogins);
		Assert.assertEquals(listBoxCount, Integer.parseInt(totalLogins));

		//form.intoMainFrame();
		//pages.Main().logout();
        //form.sleep(5000);
        /**/
	}





}