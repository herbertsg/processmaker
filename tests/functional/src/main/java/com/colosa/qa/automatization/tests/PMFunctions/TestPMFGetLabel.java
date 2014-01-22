package com.colosa.qa.automatization.tests.PMFunctions;

import com.colosa.qa.automatization.pages.DynaformExecution;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestPMFGetLabel extends com.colosa.qa.automatization.tests.common.Test{

	protected static String dropdwn = "Valor3";
	protected static String list = "Valor4";
	protected static String radio = "Valor5";
	protected static String check = "Valor2";
	protected static String	check2 = "Valor4";
    protected static String check3 = "Valor5";
    protected static String checkGroup = "";

    public TestPMFGetLabel(String browserName) throws IOException {
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
	public void runProcess()throws FileNotFoundException, IOException, Exception{

		pages.gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goHome();
		pages.Home().gotoNewCase().startCase("PMF GetLabel (Task 1)");
		DynaformExecution form = pages.DynaformExecution();
        form.intoDynaform();
		form.setFieldValue("dropdwn", dropdwn);
		form.setFieldValue("List", list);
		form.setCheckBoxGroupField("radio1", radio);
		form.setCheckBoxGroupField("check1", check);
		form.setCheckBoxGroupField("check1", check2);
		form.setCheckBoxGroupField("check1", check3);
		form.clickButton("Enviar");
		checkGroup = check + " " + check2 + " " + check3 + " ";

        form.intoDynaform();
		Assert.assertEquals(form.getFieldValue("Recover"), dropdwn);
		Assert.assertEquals(form.getFieldValue("lstValue"), list);
		Assert.assertEquals(form.getFieldValue("rdValue"), radio);
		Assert.assertEquals(form.getFieldValue("chkValue"), checkGroup);
		//form.sleep(15000);
		//pages.InputDocProcess().switchToDefault();
		//pages.Main().logout();
	}


}