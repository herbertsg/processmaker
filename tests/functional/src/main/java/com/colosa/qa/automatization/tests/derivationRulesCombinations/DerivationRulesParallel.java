package com.colosa.qa.automatization.tests.derivationRulesCombinations;

import com.colosa.qa.automatization.pages.DynaformExecution;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class DerivationRulesParallel extends com.colosa.qa.automatization.tests.common.Test{

	protected static int caseNum;

    public DerivationRulesParallel(String browserName) throws IOException {
        super(browserName);
    }

    @Test
	public void derivationRulesParallel() throws FileNotFoundException, IOException, Exception{

		pages.gotoDefaultUrl();
		pages.Login().loginUser("ezequiel", "sample", "workflow", "English");
		pages.Main().goHome();
		caseNum = pages.Home().gotoNewCase().startCase("Derivation rules - Parallel (Init)");
		DynaformExecution form = pages.DynaformExecution();
        form.intoDynaform();
		form.setFieldValue("name", "Charles Puyol");
		form.setFieldValue("amount", "3000");
		form.setFieldValue("send", "");
		form.setFieldValue("TASKS][2][USR_UID", "Swan, William");
	    pages.AssignTask().pressContinueButton();
		form.outDynaform();
		pages.Main().logout();
	    //cyclical task
		pages.gotoDefaultUrl();
		pages.Login().loginUser("chris", "sample", "workflow", "English");
		pages.Main().goHome();
    	pages.Home().gotoInbox();
		Assert.assertTrue("The case does not exist in Inbox", pages.Home().existCase(caseNum));
		pages.Home().gotoInbox().openCase(caseNum);

        form.intoDynaform();
	    pages.AssignTask().pressContinueButton();
		form.outDynaform();
		pages.Main().logout();
		//Manual task
		pages.gotoDefaultUrl();
		pages.Login().loginUser("william", "sample", "workflow", "English");
		pages.Main().goHome();
    	pages.Home().gotoInbox();
		Assert.assertTrue("The case does not exist in Inbox", pages.Home().existCase(caseNum));
		pages.Home().gotoInbox().openCase(caseNum);

        form.intoDynaform();
	    pages.AssignTask().pressContinueButton();
		form.outDynaform();
		pages.Main().logout();
		//Value based task
		pages.gotoDefaultUrl();
		pages.Login().loginUser("ezequiel", "sample", "workflow", "English");
		pages.Main().goHome();
    	pages.Home().gotoInbox();
		Assert.assertTrue("The case does not exist in Inbox", pages.Home().existCase(caseNum));
		pages.Home().gotoInbox().openCase(caseNum);

        form.intoDynaform();
	    pages.AssignTask().pressContinueButton();
		form.outDynaform();
		pages.Main().logout();
		//Report to task
		pages.gotoDefaultUrl();
		pages.Login().loginUser("zachary", "sample", "workflow", "English");
		pages.Main().goHome();
    	pages.Home().gotoInbox();
		Assert.assertTrue("The case does not exist in Inbox", pages.Home().existCase(caseNum));
		pages.Home().gotoInbox().openCase(caseNum);

        form.intoDynaform();
	    pages.AssignTask().pressContinueButton();
		form.outDynaform();
		pages.Main().logout();
		//Self service task
		pages.gotoDefaultUrl();
		pages.Login().loginUser("chris", "sample", "workflow", "English");
		pages.Main().goHome();
		pages.Home().gotoUnassigned();
		Assert.assertTrue("The case does not exist in Unassigned", pages.Home().existCase(caseNum));
		pages.Home().gotoInbox().openCase(caseNum);

        form.intoDynaform();
		form.setFieldValue("BTN_CATCH", "");
		form.outDynaform();
		form.intoDynaform();
	    pages.AssignTask().pressContinueButton();
		form.outDynaform();
		pages.Main().logout();
		//Self Service Value Based task
		pages.gotoDefaultUrl();
		pages.Login().loginUser("william", "sample", "workflow", "English");
		pages.Main().goHome();
		pages.Home().gotoUnassigned();
		Assert.assertTrue("The case does not exist in Unassigned", pages.Home().existCase(caseNum));
		pages.Home().gotoInbox().openCase(caseNum);

        form.intoDynaform();
		form.setFieldValue("BTN_CATCH", "");
		form.outDynaform();
		form.intoDynaform();
	    pages.AssignTask().pressContinueButton();
		form.outDynaform();
		pages.Main().logout();
		//Open cases to verify Cyclical assigmnent
		pages.gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goHome();
		caseNum = pages.Home().gotoNewCase().startCase("Derivation rules - Parallel (Init)");

        form.intoDynaform();
		form.setFieldValue("name", "Charles Puyol");
		form.setFieldValue("amount", "3000");
		form.setFieldValue("send", "");
		form.setFieldValue("TASKS][2][USR_UID", "Swan, William");
	    pages.AssignTask().pressContinueButton();
		form.outDynaform();
		pages.Main().logout();
		pages.gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goHome();
		caseNum = pages.Home().gotoNewCase().startCase("Derivation rules - Parallel (Init)");

        form.intoDynaform();
		form.setFieldValue("name", "Charles Puyol");
		form.setFieldValue("amount", "3000");
		form.setFieldValue("send", "");
		form.setFieldValue("TASKS][2][USR_UID", "Swan, William");
	    pages.AssignTask().pressContinueButton();
		pages.InputDocProcess().switchToDefault();
		pages.Main().logout();
	}

    @After
    public void cleanup(){
        browserInstance.quit();
    }


}