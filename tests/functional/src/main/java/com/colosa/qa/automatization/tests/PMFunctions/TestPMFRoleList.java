package com.colosa.qa.automatization.tests.PMFunctions;

import com.colosa.qa.automatization.pages.DynaformExecution;
import com.colosa.qa.automatization.common.FieldKeyType;
import com.colosa.qa.automatization.common.FieldType;
import com.colosa.qa.automatization.common.FormFieldData;
import com.colosa.qa.automatization.common.FormFiller;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class TestPMFRoleList extends com.colosa.qa.automatization.tests.common.Test{

    public TestPMFRoleList(String browserName) throws IOException {
        super(browserName);
    }

    @Test
    public void runCase() throws Exception {
        
        pages.gotoDefaultUrl();
        pages.Login().loginUser("admin","admin","workflow", "English");
				pages.Main().goHome();
        
        int casenumber = pages.Home().gotoNewCase().startCase("Test PMFRoleList (Task 1)");
        DynaformExecution form = pages.DynaformExecution();
        form.intoDynaform();
        
        Integer fieldROLES = Integer.parseInt(form.getFieldAttribute("ROLES", "value"));
        
        FormFieldData[] fieldArray=new FormFieldData[1];
		    fieldArray[0]=new FormFieldData();
				
		    fieldArray[0].fieldPath="form[SUBMIT]";
		    fieldArray[0].fieldFindType=FieldKeyType.ID;
		    fieldArray[0].fieldType=FieldType.BUTTON;
		    fieldArray[0].fieldValue="";
    
        FormFiller.formFillElements(browserInstance, fieldArray);
        pages.AssignTask().pressContinueButton();
        
        pages.Main().goAdmin();
        Integer roles = pages.Admin().countRoles();
        
        Assert.assertEquals("PMFRoleList function not working properly", roles, fieldROLES);
        
        //Logger.addLog("LOS ROLES  "+roles);
        form.outDynaform();
        pages.Main().logout();
    }

    @After
    public void cleanup(){
        browserInstance.quit();
    }
    
}