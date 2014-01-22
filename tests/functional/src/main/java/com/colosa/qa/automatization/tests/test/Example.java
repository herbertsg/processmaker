package com.colosa.qa.automatization.tests.test;

import com.colosa.qa.automatization.pages.DynaformExecution;
import com.colosa.qa.automatization.common.FieldType;
import com.colosa.qa.automatization.common.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: marco
 * Date: 09-07-13
 * Time: 04:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class Example extends com.colosa.qa.automatization.tests.common.Test {
    protected int caseNum;
    protected  String nom="Marco";
    protected String ape="Avalos";

    public Example(String browserName) throws IOException {
        super(browserName);
    }

    @Before
    public void setup(){

    }

    @After
    public void cleanup(){
        browserInstance.quit();
    }

    @Test
    public void testExample() throws FileNotFoundException, IOException, Exception{
        pages.gotoDefaultUrl();

        Logger.addLog("Test testDependentFieldsCase with browserName:" + this.browserName);

        pages.gotoDefaultUrl();

        pages.Login().loginUser("admin", "admin", "workflow", "English");
        pages.Main().goHome();

        caseNum = pages.Home().gotoNewCase().startCase("marco (Task 1)");

        DynaformExecution form = pages.DynaformExecution();
        form.intoDynaform();


        //form.setFieldValue("country", country, FieldType.DROPDOWN);

        form.setFieldValue("nombre", nom);
        form.setFieldValue("apellido", ape);

        Assert.assertEquals(form.getFieldValue("nombre"), nom);
        Assert.assertEquals(form.getFieldValue("apellido"), ape);
        //form.setFieldValue("mandar", "", FieldType.BUTTON);
        form.clickButton("mandar");

        pages.AssignTask().pressContinueButton();

        /*pages.gotoDefaultUrl();
        pages.Login().loginUser("admin", "admin", "workflow", "English");
        pages.Main().goHome();*/
        pages.Home().gotoInbox().openCase(caseNum);

        form.intoDynaform();
        Assert.assertEquals(form.getFieldValue("nombre"), nom);
        Assert.assertEquals(form.getFieldValue("apellido"), ape);
        form.clickButton("mandar");
        pages.AssignTask().pressContinueButton();

        //form.setGridFieldValue("gridname", numerofila, "country", country, FieldType.DROPDOWN);
        //sleep one second to wait ajax result

        //wait for combo to populate
        /*
        form.waitForFieldToBeClickable("state", 2);
        form.waitForFieldToBeClickable("location", 2);

        //form.sleep(1000);

        Assert.assertEquals(form.getFieldText("state"), state);
        Assert.assertEquals(form.getFieldText("location"), location);

        //form.setFieldValue("suggest_country", country, FieldType.SUGGEST);
        form.setFieldValue("suggest_country", country);

        //form.sleep(1000);
        form.waitForFieldToBeClickable("suggest_state", 2);
        form.waitForFieldToBeClickable("suggest_location", 2);
        form.waitForFieldToBeClickable("suggest_state2", 2);
        form.waitForFieldToBeClickable("suggest_location2", 2);

        Assert.assertEquals(form.getDropdownFieldText("suggest_state"), state);
        Assert.assertEquals(form.getDropdownFieldText("suggest_location"), location);
        Assert.assertEquals(form.getDropdownFieldText("suggest_state2"), state);
        Assert.assertEquals(form.getDropdownFieldText("suggest_location2"), location);

        //form.setFieldValue("username", userName, FieldType.TEXTBOX);
        form.setFieldValue("username", userName);
        //send tab to activate dependent fields
        //form.sendTab("userCompleteName");
        form.sendTab("username");
        //form.sleep(1000);
        browserInstance.waitForDocumentCompleted(20);
        //form.waitForFieldToBeClickable("userCompleteName", 2);
        //form.waitForFieldToBeClickable("userDepartment", 2);

        Assert.assertEquals(form.getFieldValue("userCompleteName"), completeName);
        Assert.assertEquals(form.getFieldValue("userDepartment"), userDepartment);

        //change values in fields
        //form.setFieldValue("state", state1, FieldType.DROPDOWN);
        form.setFieldValue("state", state1);
        //form.sleep(1000);
        form.waitForFieldToBeClickable("location", 2);
        Assert.assertEquals(form.getDropdownFieldText("location"), location1);

        //change fields in suggest
        //form.setFieldValue("suggest_country", country2, FieldType.SUGGEST);
        form.setFieldValue("suggest_country", country2);
        //form.sleep(1000);
        form.waitForFieldToBeClickable("suggest_state", 2);
        //form.setFieldValue("suggest_state", state2, FieldType.DROPDOWN);
        form.setFieldValue("suggest_state", state2);
        //form.sleep(1000);
        form.waitForFieldToBeClickable("suggest_location", 2);
        //form.setFieldValue("suggest_location", location2, FieldType.DROPDOWN);
        form.setFieldValue("suggest_location", location2);
        //form.sleep(1000);
        Assert.assertEquals(form.getDropdownFieldText("suggest_state"), state2);
        Assert.assertEquals(form.getDropdownFieldText("suggest_location"), location2);
        //form.setFieldValue("suggest_state2", state22, FieldType.DROPDOWN);
        form.setFieldValue("suggest_state2", state22);
        form.waitForFieldToBeClickable("suggest_location2", 2);
        //form.setFieldValue("suggest_location2", location22, FieldType.DROPDOWN);
        form.setFieldValue("suggest_location2", location22);
        //form.sleep(1000);
        Assert.assertEquals(form.getDropdownFieldText("suggest_state2"), state22);
        Assert.assertEquals(form.getDropdownFieldText("suggest_location2"), location22);

        //change fields in textbox
        //form.setFieldValue("username", userName1, FieldType.TEXTBOX);
        form.setFieldValue("username", userName1);
        //send tab to activate dependent fields
        form.sendTab("username");
        //form.sleep(1000);
        //form.waitForFieldToBeClickable("userCompleteName", 2);
        //form.waitForFieldToBeClickable("userDepartment", 2);
        browserInstance.waitForDocumentCompleted(20);
        Assert.assertEquals(form.getFieldValue("userCompleteName"), completeName1);
        Assert.assertEquals(form.getFieldValue("userDepartment"), userDepartment1);

        //test other dependent fields
        form.setFieldValue("userName2", userName1); //admin
        form.waitForFieldToBeClickable("textAreaField", 2);
        Assert.assertEquals(form.getFieldValue("textAreaField"), completeName1);
        int listBoxCount = form.getFieldCount("listBoxField");
        Logger.addLog("ListBox value:" + listBoxCount);
        //Assert.assertFalse(form.getFieldValue("listBoxField").equals("")); //at least one login
        String totalLogins = form.getFieldValue("hiddenField");
        Logger.addLog("Total logins:" + totalLogins);
        Assert.assertEquals(listBoxCount, Integer.parseInt(totalLogins));
        */
        //pages..switchToDefault();
        pages.Main().logout();
    }
}
