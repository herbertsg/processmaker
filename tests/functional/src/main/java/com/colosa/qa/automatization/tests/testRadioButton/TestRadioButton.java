package com.colosa.qa.automatization.tests.testRadioButton;

import com.colosa.qa.automatization.common.Logger;
import com.colosa.qa.automatization.pages.DynaformExecution;
import org.junit.After;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class TestRadioButton extends com.colosa.qa.automatization.tests.common.Test{

	protected int caseNum;

    public TestRadioButton(String browserName) throws IOException {
        super(browserName);
    }

    @Test
	public void runProcess()throws FileNotFoundException, IOException, Exception{

		pages.gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goHome();
		caseNum = pages.Home().gotoNewCase().startCase("Test RB and CB (Task 1)");
		DynaformExecution form = pages.DynaformExecution();
        form.intoDynaform();
		form.setRadioButtonGroupField("Radio1", "Val2");
		form.setRadioButtonGroupField("Radio1", "Val3");
		form.setCheckBoxGroupField("chkgroup", "Val2");
		form.setCheckBoxGroupField("chkgroup", "Val3");
		//form.getCheckBoxSelected("chkgroup");
		List<String> chGroup = form.getCheckBoxGroupSelected("chkgroup");

		Iterator it = chGroup.iterator();
		while(it.hasNext())
		{
			String value=(String)it.next();

        	Logger.addLog("Value :" + value);
		}
		String rbGroup = form.getRadioButtonGroupSelected("Radio1");
		Logger.addLog("RadioButton selected:"+rbGroup);

		pages.InputDocProcess().switchToDefault();
		pages.Main().logout();
	}

    @After
    public void cleanup(){
        browserInstance.quit();
    }

}