package com.colosa.qa.automatization.tests.PMFields;
import com.colosa.qa.automatization.common.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class MaskFieldTest extends com.colosa.qa.automatization.tests.common.Test{

    public MaskFieldTest(String browserName) throws IOException {
        super(browserName);
    }

    @Test
 	public void runProcess() throws Exception{
        pages.gotoDefaultUrl();
        pages.Login().loginUser("admin","admin","workflow", "English");
        pages.Main().goHome();
        int casenumber=pages.Home().gotoNewCase().startCase("TestMask (Task 1)");

        pages.DynaformExecution().intoDynaform();

        pages.DynaformExecution().setFieldValue("MASK1", "123456789.02");
        pages.DynaformExecution().setFieldValue("MASK2", "123,02");
        pages.DynaformExecution().setFieldValue("MASK_BEFORE", "123456789.02");
        pages.DynaformExecution().setFieldValue("MASK_AFTER", "123,12");
        pages.DynaformExecution().setFieldValue("MASK_OTHER1", "123456789.023");
        pages.DynaformExecution().setFieldValue("MASK_OTHER2", "123,023");
        pages.DynaformExecution().clickButton("SUBMIT");

        pages.AssignTask().pressContinueButton();

        //pages.Home().gotoInbox();
        //Assert.assertTrue("The case does not exist in inbox", pages.Home().gotoInbox().existCase(casenumber));
        pages.Home().gotoInbox().openCase(casenumber);


        pages.DynaformExecution().intoDynaform();


		String fieldMASK1 = pages.DynaformExecution().getFieldProperty("MASK1","value");
		String fieldMASK2 = pages.DynaformExecution().getFieldProperty("MASK2","value");
		
		String fieldMASK_BEFORE = pages.DynaformExecution().getFieldProperty("MASK_BEFORE","value");
		String fieldMASK_AFTER = pages.DynaformExecution().getFieldProperty("MASK_AFTER","value");
		
		String fieldMASK_OTHER1 = pages.DynaformExecution().getFieldProperty("MASK_OTHER1","value");
		String fieldMASK_OTHER2 = pages.DynaformExecution().getFieldProperty("MASK_OTHER2","value");
				

        Assert.assertEquals("Mask does not exist", "123,456,789.02", fieldMASK1);
        Assert.assertEquals("Mask does not exist", "123,02", fieldMASK2);

        Assert.assertEquals("Mask Before does not exist", "B12,345,678.02", fieldMASK_BEFORE);
        Assert.assertEquals("Mask After does not exist", "123,1B", fieldMASK_AFTER);

        Assert.assertEquals("Mask other value does not exist", "123,456,789.02", fieldMASK_OTHER1);
        Assert.assertEquals("Mask other value(,) does not exist", "123,02", fieldMASK_OTHER2);

        pages.DynaformExecution().clickButton("SUBMIT");

        pages.AssignTask().pressContinueButton();

        pages.Main().logout();
    }

    @After
    public void cleanup(){
        browserInstance.quit();
    }

}