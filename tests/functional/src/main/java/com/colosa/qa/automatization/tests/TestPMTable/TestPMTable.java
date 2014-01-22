package com.colosa.qa.automatization.tests.TestPMTable;

import com.colosa.qa.automatization.common.FieldKeyType;
import com.colosa.qa.automatization.common.FieldType;
import com.colosa.qa.automatization.common.FormFieldData;
import com.colosa.qa.automatization.common.FormFiller;
import com.colosa.qa.automatization.pages.DynaformExecution;
import org.junit.Assert;
import org.junit.Test;
import org.junit.After;

import java.io.IOException;

public class TestPMTable extends com.colosa.qa.automatization.tests.common.Test{

    public TestPMTable(String browserName) throws IOException {
        super(browserName);
    }

    @Test
    public void runCase() throws Exception {
        
        pages.gotoDefaultUrl();
        pages.Login().loginUser("admin","admin","workflow", "English");
        //form.outDynaform();
				pages.Main().goAdmin();
        pages.Admin().newPMTable("TESTTABLE","description Table");
        pages.Admin().addField("ID","ID","VARCHAR","32",true,false,false);
        pages.Admin().addField("NAME","NAME","VARCHAR","50",false,false,false);
        pages.Admin().addField("DESCRIPCION","DESCRIPCION","VARCHAR","100",false,false,false);
        pages.Admin().createPMTable();
        
        pages.Main().goHome();
        int casenumber = pages.Home().gotoNewCase().startCase("TestPMTables (Task 1)");
        DynaformExecution form = pages.DynaformExecution();
        form.intoDynaform();
        
        String fieldRESULT = form.getFieldAttribute("RESULT_TEST", "value");
        Assert.assertEquals("PMTable does not exist in Database.", "0", fieldRESULT);
        
        FormFieldData[] fieldArray=new FormFieldData[1];
		    fieldArray[0]=new FormFieldData();
				
		    fieldArray[0].fieldPath="form[SUBMIT]";
		    fieldArray[0].fieldFindType=FieldKeyType.ID;
		    fieldArray[0].fieldType=FieldType.BUTTON;
		    fieldArray[0].fieldValue="";
    
        FormFiller.formFillElements(browserInstance, fieldArray);
        pages.AssignTask().pressContinueButton();
        
        pages.Main().goAdmin();
        pages.Admin().newPMTable("TEST TABLE1","description Table");
        pages.Admin().addField("ID","ID","VARCHAR","32",true,false,false);
        pages.Admin().addField("NAME","NAME","VARCHAR","50",false,false,false);
        pages.Admin().addField("DESCRIPCION","DESCRIPCION","VARCHAR","100",false,false,false);
        pages.Admin().createPMTable();
        
        Assert.assertTrue("Exist any space in Name Table", pages.Admin().verifyPMTable("TESTTABLE"));
        
        
        pages.Admin().newPMTable("TESTTABLE3","description Table3");
        pages.Admin().addField("ID","ID","VARCHAR","32",false,false,false);
        pages.Admin().addField("NAME","NAME","","50",false,false,false);
        pages.Admin().addField("DESCRIPCION","DESCRIPCION","VARCHAR","100",false,false,false);
        pages.Admin().createPMTable();
        
        
        Assert.assertEquals("Type Field should be defined in Table", false,pages.Admin().verifyPMTable("TESTTABLE3"));
        
        pages.Admin().newPMTable("TESTTABLE2","description Table2");
        pages.Admin().addField("ID","ID","VARCHAR","32",false,false,false);
        pages.Admin().addField("NAME","NAME","VARCHAR","50",false,false,false);
        pages.Admin().addField("DESCRIPCION","DESCRIPCION","VARCHAR","100",false,false,false);
        pages.Admin().createPMTable();
        
        Assert.assertEquals("Does not exist Primary Key field in Table", false,pages.Admin().verifyPMTable("TESTTABLE2"));
        
        pages.InputDocProcess().switchToDefault();
        pages.Main().logout();
    }

    @After
    public void cleanup(){
        browserInstance.quit();
    }
    
}