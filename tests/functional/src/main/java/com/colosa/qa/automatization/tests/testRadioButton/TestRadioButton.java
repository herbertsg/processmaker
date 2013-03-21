package com.colosa.qa.automatization.tests.testRadioButton;

import com.colosa.qa.automatization.common.Browser;
import com.colosa.qa.automatization.pages.Pages;
import org.junit.After;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class TestRadioButton{

	protected static int caseNum;
	@Test
	public void runProcess()throws FileNotFoundException, IOException, Exception{

		Pages.Login().gotoUrl();
		Pages.Login().loginUser("admin", "admin", "workflow");
		Pages.Main().goHome();	
		caseNum = Pages.Home().startCase("Test RB and CB (Task 1)");
		Pages.DynaformExecution().intoDynaform();
		Pages.DynaformExecution().setRadioButtonGroup("Radio1", "Val2");
		Pages.DynaformExecution().setRadioButtonGroup("Radio1", "Val3");
		Pages.DynaformExecution().setCheckBoxGroup("chkgroup", "Val2");
		Pages.DynaformExecution().setCheckBoxGroup("chkgroup", "Val3");
		//Pages.DynaformExecution().getCheckBoxSelected("chkgroup");
		List<String> chGroup = Pages.DynaformExecution().getCheckBoxGroupSelected("chkgroup");

		Iterator it = chGroup.iterator();
		while(it.hasNext())
		{
			String value=(String)it.next();

        	System.out.println("Value :"+value);
		}
		String rbGroup = Pages.DynaformExecution().getRadioButtonGroupSelected("Radio1");
		System.out.println("RadioButton selected:"+rbGroup);

		Pages.InputDocProcess().switchToDefault();
		Pages.Main().logout();
	}
/*
    @After
    public void cleanup(){
        Browser.close();
    }*/

}