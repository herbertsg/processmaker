package com.colosa.qa.automatization.tests.derivationRulesCombinations;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class DerivationRulesParallelEvaluation extends com.colosa.qa.automatization.tests.common.Test{

	protected static int caseNum;

    public DerivationRulesParallelEvaluation(String browserName) throws IOException {
        super(browserName);
    }

    @Test
	public void derivationRulesParallelEvaluation() throws FileNotFoundException, IOException, Exception{
		//Init case
		pages.Login().gotoDefaultUrl();
		pages.Login().loginUser("ezequiel", "sample", "workflow", "English");
		pages.Main().goHome();
		caseNum = pages.Home().gotoNewCase().startCase("Derivation rules - parallel evaluation (Init)");
		pages.DynaformExecution().intoDynaform();
		pages.DynaformExecution().setFieldValue("name", "Charles Puyol");
		pages.DynaformExecution().setFieldValue("amount", "3000");
		pages.DynaformExecution().setFieldValue("send", "");
		pages.DynaformExecution().setFieldValue("tasks", "Cyclical");
		pages.DynaformExecution().setFieldValue("send", "");
	    pages.AssignTask().pressContinueButton();
		pages.DynaformExecution().outDynaform();
		pages.Main().logout();
	    //cyclical task
		pages.Login().gotoDefaultUrl();
		pages.Login().loginUser("chris", "sample", "workflow", "English");
		pages.Main().goHome();
    	pages.Home().gotoInbox();
		Assert.assertTrue("The case does not exist in Inbox", pages.Home().existCase(caseNum));
		pages.Home().openCase(caseNum);
		pages.DynaformExecution().intoDynaform();
	    pages.AssignTask().pressContinueButton();
		pages.DynaformExecution().outDynaform();
		pages.Main().logout();
		//Init case
		pages.Login().gotoDefaultUrl();
		pages.Login().loginUser("ezequiel", "sample", "workflow", "English");
		pages.Main().goHome();
		caseNum = pages.Home().gotoNewCase().startCase("Derivation rules - parallel evaluation (Init)");
		pages.DynaformExecution().intoDynaform();
		pages.DynaformExecution().setFieldValue("name", "Charles Puyol");
		pages.DynaformExecution().setFieldValue("amount", "3000");
		pages.DynaformExecution().setFieldValue("send", "");
		pages.DynaformExecution().setFieldValue("tasks", "Manual");
		pages.DynaformExecution().setFieldValue("send", "");
		pages.DynaformExecution().setFieldValue("TASKS][1][USR_UID", "Swan, William");
	    pages.AssignTask().pressContinueButton();
		pages.DynaformExecution().outDynaform();
		pages.Main().logout();
		//Manual task
		pages.Login().gotoDefaultUrl();
		pages.Login().loginUser("william", "sample", "workflow", "English");
		pages.Main().goHome();
    	pages.Home().gotoInbox();
		Assert.assertTrue("The case does not exist in Inbox", pages.Home().existCase(caseNum));
		pages.Home().openCase(caseNum);
		pages.DynaformExecution().intoDynaform();
	    pages.AssignTask().pressContinueButton();
		pages.DynaformExecution().outDynaform();
		pages.Main().logout();
		//Init case
		pages.Login().gotoDefaultUrl();
		pages.Login().loginUser("ezequiel", "sample", "workflow", "English");
		pages.Main().goHome();
		caseNum = pages.Home().gotoNewCase().startCase("Derivation rules - parallel evaluation (Init)");
		pages.DynaformExecution().intoDynaform();
		pages.DynaformExecution().setFieldValue("name", "Charles Puyol");
		pages.DynaformExecution().setFieldValue("amount", "3000");
		pages.DynaformExecution().setFieldValue("send", "");
		pages.DynaformExecution().setFieldValue("tasks", "Value Based");
		pages.DynaformExecution().setFieldValue("send", "");
	    pages.AssignTask().pressContinueButton();
		pages.DynaformExecution().outDynaform();
		pages.Main().logout();
		//Value based task
		pages.Login().gotoDefaultUrl();
		pages.Login().loginUser("ezequiel", "sample", "workflow", "English");
		pages.Main().goHome();
    	pages.Home().gotoInbox();
		Assert.assertTrue("The case does not exist in Inbox", pages.Home().existCase(caseNum));
		pages.Home().openCase(caseNum);
		pages.DynaformExecution().intoDynaform();
	    pages.AssignTask().pressContinueButton();
		pages.DynaformExecution().outDynaform();
		pages.Main().logout();
		//Init case
		pages.Login().gotoDefaultUrl();
		pages.Login().loginUser("ezequiel", "sample", "workflow", "English");
		pages.Main().goHome();
		caseNum = pages.Home().gotoNewCase().startCase("Derivation rules - parallel evaluation (Init)");
		pages.DynaformExecution().intoDynaform();
		pages.DynaformExecution().setFieldValue("name", "Charles Puyol");
		pages.DynaformExecution().setFieldValue("amount", "3000");
		pages.DynaformExecution().setFieldValue("send", "");
		pages.DynaformExecution().setFieldValue("tasks", "Reports to");
		pages.DynaformExecution().setFieldValue("send", "");
	    pages.AssignTask().pressContinueButton();
		pages.DynaformExecution().outDynaform();
		pages.Main().logout();
		//Report to task
		pages.Login().gotoDefaultUrl();
		pages.Login().loginUser("zachary", "sample", "workflow", "English");
		pages.Main().goHome();
    	pages.Home().gotoInbox();
		Assert.assertTrue("The case does not exist in Inbox", pages.Home().existCase(caseNum));
		pages.Home().openCase(caseNum);
		pages.DynaformExecution().intoDynaform();
	    pages.AssignTask().pressContinueButton();
		pages.DynaformExecution().outDynaform();
		pages.Main().logout();
		//Init case
		pages.Login().gotoDefaultUrl();
		pages.Login().loginUser("ezequiel", "sample", "workflow", "English");
		pages.Main().goHome();
		caseNum = pages.Home().gotoNewCase().startCase("Derivation rules - parallel evaluation (Init)");
		pages.DynaformExecution().intoDynaform();
		pages.DynaformExecution().setFieldValue("name", "Charles Puyol");
		pages.DynaformExecution().setFieldValue("amount", "3000");
		pages.DynaformExecution().setFieldValue("send", "");
		pages.DynaformExecution().setFieldValue("tasks", "Self Service");
		pages.DynaformExecution().setFieldValue("send", "");
	    pages.AssignTask().pressContinueButton();
		pages.DynaformExecution().outDynaform();
		pages.Main().logout();
		//Self service task
		pages.Login().gotoDefaultUrl();
		pages.Login().loginUser("chris", "sample", "workflow", "English");
		pages.Main().goHome();
		pages.Home().gotoUnassigned();
		Assert.assertTrue("The case does not exist in Unassigned", pages.Home().existCase(caseNum));
		pages.Home().openCase(caseNum);
		pages.DynaformExecution().intoDynaform();
		pages.DynaformExecution().setFieldValue("BTN_CATCH", "");
		pages.DynaformExecution().outDynaform();
		pages.DynaformExecution().intoDynaform();
	    pages.AssignTask().pressContinueButton();
		pages.DynaformExecution().outDynaform();
		pages.Main().logout();
		//Init case
		pages.Login().gotoDefaultUrl();
		pages.Login().loginUser("ezequiel", "sample", "workflow", "English");
		pages.Main().goHome();
		caseNum = pages.Home().gotoNewCase().startCase("Derivation rules - parallel evaluation (Init)");
		pages.DynaformExecution().intoDynaform();
		pages.DynaformExecution().setFieldValue("name", "Charles Puyol");
		pages.DynaformExecution().setFieldValue("amount", "3000");
		pages.DynaformExecution().setFieldValue("send", "");
		pages.DynaformExecution().setFieldValue("tasks", "Self Service Value Based");
		pages.DynaformExecution().setFieldValue("send", "");
	    pages.AssignTask().pressContinueButton();
		pages.DynaformExecution().outDynaform();
		pages.Main().logout();
		//Self Service Value Based task
		pages.Login().gotoDefaultUrl();
		pages.Login().loginUser("william", "sample", "workflow", "English");
		pages.Main().goHome();
		pages.Home().gotoUnassigned();
		Assert.assertTrue("The case does not exist in Unassigned", pages.Home().existCase(caseNum));
		pages.Home().openCase(caseNum);
		pages.DynaformExecution().intoDynaform();
		pages.DynaformExecution().setFieldValue("BTN_CATCH", "");
		pages.DynaformExecution().outDynaform();
		pages.DynaformExecution().intoDynaform();
	    pages.AssignTask().pressContinueButton();
		pages.DynaformExecution().outDynaform();
		pages.Main().logout();
		//Open cases to verify Cyclical assigmnent
		pages.Login().gotoDefaultUrl();
		pages.Login().loginUser("ezequiel", "sample", "workflow", "English");
		pages.Main().goHome();
		caseNum = pages.Home().gotoNewCase().startCase("Derivation rules - parallel evaluation (Init)");
		pages.DynaformExecution().intoDynaform();
		pages.DynaformExecution().setFieldValue("name", "Charles Puyol");
		pages.DynaformExecution().setFieldValue("amount", "3000");
		pages.DynaformExecution().setFieldValue("send", "");
		pages.DynaformExecution().setFieldValue("tasks", "Cyclical");
		pages.DynaformExecution().setFieldValue("send", "");
	    pages.AssignTask().pressContinueButton();
		pages.DynaformExecution().outDynaform();
		pages.Main().logout();
		pages.Login().gotoDefaultUrl();
		pages.Login().loginUser("ezequiel", "sample", "workflow", "English");
		pages.Main().goHome();
		caseNum = pages.Home().gotoNewCase().startCase("Derivation rules - parallel evaluation (Init)");
		pages.DynaformExecution().intoDynaform();
		pages.DynaformExecution().setFieldValue("name", "Charles Puyol");
		pages.DynaformExecution().setFieldValue("amount", "3000");
		pages.DynaformExecution().setFieldValue("send", "");
		pages.DynaformExecution().setFieldValue("tasks", "Cyclical");
		pages.DynaformExecution().setFieldValue("send", "");
	    pages.AssignTask().pressContinueButton();
		pages.InputDocProcess().switchToDefault();
		pages.Main().logout();
	}

    @After
    public void cleanup(){
        browserInstance.quit();
    }


}