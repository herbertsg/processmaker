package com.colosa.qa.automatization.tests.testRadioButton;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class TestRadioButton extends com.colosa.qa.automatization.tests.common.Test{

	protected static int caseNum;

    public TestRadioButton(String browserName) throws IOException {
        super(browserName);
    }

    @Test
	public void runProcess()throws FileNotFoundException, IOException, Exception{

		pages.Login().gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goHome();
		caseNum = pages.Home().startCase("Test RB and CB (Task 1)");
		pages.DynaformExecution().intoDynaform();
		pages.DynaformExecution().setRadioButtonGroup("Radio1", "Val2");
		pages.DynaformExecution().setRadioButtonGroup("Radio1", "Val3");
		pages.DynaformExecution().setCheckBoxGroup("chkgroup", "Val2");
		pages.DynaformExecution().setCheckBoxGroup("chkgroup", "Val3");
		//pages.DynaformExecution().getCheckBoxSelected("chkgroup");
		List<String> chGroup = pages.DynaformExecution().getCheckBoxGroupSelected("chkgroup");

		Iterator it = chGroup.iterator();
		while(it.hasNext())
		{
			String value=(String)it.next();

        	System.out.println("Value :"+value);
		}
		String rbGroup = pages.DynaformExecution().getRadioButtonGroupSelected("Radio1");
		System.out.println("RadioButton selected:"+rbGroup);

		pages.InputDocProcess().switchToDefault();
		pages.Main().logout();
	}
/*
    @After
    public void cleanup(){
        Browser.close();
    }*/

}