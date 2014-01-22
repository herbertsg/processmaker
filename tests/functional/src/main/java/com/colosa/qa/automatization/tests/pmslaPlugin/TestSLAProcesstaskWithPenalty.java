package com.colosa.qa.automatization.tests.pmslaPlugin;

import org.junit.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;

import com.colosa.qa.automatization.pages.*;
import com.colosa.qa.automatization.common.*;
import org.openqa.selenium.WebElement;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class TestSLAProcesstaskWithPenalty extends com.colosa.qa.automatization.tests.common.Test{

	protected static int caseNum;

    public TestSLAProcesstaskWithPenalty(String browserName) throws IOException {
        super(browserName);
    }

    @Test
	public void executeSLAProcesstaskWithPenalty() throws FileNotFoundException, IOException, Exception{

		pages.gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goHome();
		caseNum = pages.Home().gotoNewCase().startCase("SLA Process - Task whit penalty (Reclamo)");
		DynaformExecution form = pages.DynaformExecution();
        form.intoDynaform();
		form.setFieldValue("text", "123456");
		form.setFieldValue("enviar", "");
		Assert.assertTrue(pages.InputDocProcess().continuebtn());

		pages.CronExecute().execute("workflow");

        pages.InputDocProcess().switchToDefault();
        pages.Main().logout();
}

    @After
    public void cleanup(){
        browserInstance.quit();
    }

}