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

public class TestSLAProcesstaskWithPenalty{

	protected static int caseNum;

	@Test
	public void executeSLAProcesstaskWithPenalty() throws FileNotFoundException, IOException, Exception{

		Pages.Login().gotoUrl();
		Pages.Login().loginUser("admin", "admin", "workflow");
		Pages.Main().goHome();	
		caseNum = Pages.Home().startCase("SLA Process - Task whit penalty (Reclamo)");
		Pages.DynaformExecution().intoDynaform();
		Pages.DynaformExecution().setFieldValue("text", "123456");
		Pages.DynaformExecution().setFieldValue("enviar", "");
		Assert.assertTrue(Pages.InputDocProcess().continuebtn());

		Pages.CronExecute().execute("workflow");


}

//    @After
//    public void cleanup(){
//        Browser.close();
//    }

}