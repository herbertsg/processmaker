package com.colosa.qa.automatization.tests.pmslaPlugin;

import org.junit.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import java.util.*;
import com.colosa.qa.automatization.pages.*;
import com.colosa.qa.automatization.common.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestSLAProcessByParalellEvaluation{

	protected static int numCase;
	
	@Test
	public void runProcess()throws FileNotFoundException, IOException, Exception{

		Pages.Login().gotoUrl();
		Pages.Login().loginUser("admin", "admin", "workflow");
		Pages.Main().goHome();	
		numCase = Pages.Home().startCase("SLA Process Paralell by Evaluation (Task 1)");
		Pages.DynaformExecution().intoDynaform();
		Pages.DynaformExecution().setFieldValue("nombre", "Roberto Hernandez");
		Pages.DynaformExecution().setFieldValue("seleccion", "tarea 3");
		Pages.DynaformExecution().setFieldValue("enviar", "");
		Assert.assertTrue(Pages.InputDocProcess().continuebtn());

		Pages.CronExecute().execute("workflow");


	}
//    @After
//    public void cleanup(){
//        Browser.close();
//    }
}