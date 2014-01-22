package com.colosa.qa.automatization.tests.PMFields;
import com.colosa.qa.automatization.pages.DynaformExecution;
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

        DynaformExecution form = pages.DynaformExecution();
        form.intoDynaform();

        form.setFieldValue("MASK1", "123456789.02");
        form.setFieldValue("MASK2", "123,02");
        form.setFieldValue("MASK_BEFORE", "123456789.02");
        form.setFieldValue("MASK_AFTER", "123,12");
        form.setFieldValue("MASK_OTHER1", "123456789.023");
        form.setFieldValue("MASK_OTHER2", "123,023");
        form.clickButton("SUBMIT");

        pages.AssignTask().pressContinueButton();

        //pages.Home().gotoInbox();
        //Assert.assertTrue("The case does not exist in inbox", pages.Home().gotoInbox().existCase(casenumber));
        pages.Home().gotoInbox().openCase(casenumber);

        DynaformExecution form2 = pages.DynaformExecution();
        form2.intoDynaform();

		String fieldMASK1 = form2.getFieldAttribute("MASK1", "value");
		String fieldMASK2 = form2.getFieldAttribute("MASK2", "value");
		
		String fieldMASK_BEFORE = form2.getFieldAttribute("MASK_BEFORE", "value");
		String fieldMASK_AFTER = form2.getFieldAttribute("MASK_AFTER", "value");
		
		String fieldMASK_OTHER1 = form2.getFieldAttribute("MASK_OTHER1", "value");
		String fieldMASK_OTHER2 = form2.getFieldAttribute("MASK_OTHER2", "value");
				

        Assert.assertEquals("Mask does not exist", "123,456,789.02", fieldMASK1);
        Assert.assertEquals("Mask does not exist", "123,02", fieldMASK2);

        Assert.assertEquals("Mask Before does not exist", "B12,345,678.02", fieldMASK_BEFORE);
        Assert.assertEquals("Mask After does not exist", "123,1B", fieldMASK_AFTER);

        Assert.assertEquals("Mask other value does not exist", "123,456,789.02", fieldMASK_OTHER1);
        Assert.assertEquals("Mask other value(,) does not exist", "123,02", fieldMASK_OTHER2);

        //form.clickButton("SUBMIT");

        //pages.AssignTask().pressContinueButton();

        //pages.Main().logout();
    }

    @After
    public void cleanup(){
        browserInstance.quit();
    }

}