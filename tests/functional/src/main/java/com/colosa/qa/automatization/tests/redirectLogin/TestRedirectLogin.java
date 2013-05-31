package com.colosa.qa.automatization.tests.redirectLogin;

import com.colosa.qa.automatization.common.FieldKeyType;
import com.colosa.qa.automatization.common.Value;
import org.junit.Assert;
import org.junit.Test;
import org.junit.After;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestRedirectLogin extends com.colosa.qa.automatization.tests.common.Test{

	protected int caseNum;

    public TestRedirectLogin(String browserName) throws IOException {
        super(browserName);
    }

    @Test
	public void executeRedirectLogin() throws FileNotFoundException, IOException, Exception{

		pages.Login().gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().profile();
		pages.DynaformExecution().intoFrainMain();
		pages.Profile().editProfile();
        pages.DynaformExecution().sleep(8000);
		pages.Profile().changeDefaultMenu("HOME","New case");
        pages.DynaformExecution().sleep(8000);
		pages.Profile().saveProfile();
     	pages.InputDocProcess().switchToDefault();
		pages.Main().logout();
		//login after put the default case list to Home-->Newcase
		pages.Login().gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goHome();
		pages.DynaformExecution().intoPmtrack();
		Assert.assertEquals("Find a Process", Value.getValue(browserInstance, FieldKeyType.ID, "processesFilter"));
		caseNum = pages.Home().gotoNewCase().startCase("RedirectLogin (Ini)");
     	pages.InputDocProcess().switchToDefault();
		pages.Main().profile();
		pages.DynaformExecution().intoFrainMain();
		pages.Profile().editProfile();
        pages.DynaformExecution().sleep(8000);
		pages.Profile().changeDefaultMenu("HOME","Draft");
        pages.DynaformExecution().sleep(8000);
		pages.Profile().saveProfile();
     	pages.InputDocProcess().switchToDefault();
		pages.Main().logout();
		//login after put the default case list to Home-->Draft
		pages.Login().gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
        pages.DynaformExecution().sleep(8000);
     	pages.InputDocProcess().switchToDefault();
		Assert.assertTrue("The case does not exist in Draft", pages.Home().existCase(caseNum));
		pages.Home().gotoDraft().openCase(caseNum);
		pages.DynaformExecution().intoDynaform();
		Assert.assertTrue("The button Continue does not exit in this form", pages.InputDocProcess().continuebtn());
     	pages.InputDocProcess().switchToDefault();
		pages.Main().profile();
		pages.DynaformExecution().intoFrainMain();
		pages.Profile().editProfile();
        pages.DynaformExecution().sleep(8000);
		pages.Profile().changeDefaultMenu("HOME","Unassigned");
        pages.DynaformExecution().sleep(8000);
		pages.Profile().saveProfile();
     	pages.InputDocProcess().switchToDefault();
		pages.Main().logout();
		//login after put the default case list to Home-->Unassigned
		pages.Login().gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
        pages.DynaformExecution().sleep(8000);
     	pages.InputDocProcess().switchToDefault();
		Assert.assertTrue("The case does not exist in Unassigned", pages.Home().existCase(caseNum));
     	pages.InputDocProcess().switchToDefault();
		pages.Main().profile();
		pages.DynaformExecution().intoFrainMain();
		pages.Profile().editProfile();
        pages.DynaformExecution().sleep(8000);
		pages.Profile().changeDefaultMenu("HOME","Participated");
        pages.DynaformExecution().sleep(8000);
		pages.Profile().saveProfile();
     	pages.InputDocProcess().switchToDefault();
		pages.Main().logout();
		//login after put the default case list to Home-->Participated
		pages.Login().gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
        pages.DynaformExecution().sleep(8000);
     	pages.InputDocProcess().switchToDefault();
		Assert.assertTrue("The case does not exist in Participated", pages.Home().existCase(caseNum));
     	pages.InputDocProcess().switchToDefault();
		pages.Main().profile();
		pages.DynaformExecution().intoFrainMain();
		pages.Profile().editProfile();
        pages.DynaformExecution().sleep(8000);
		pages.Profile().changeDefaultMenu("DESIGNER","");
        pages.DynaformExecution().sleep(8000);
		pages.Profile().saveProfile();
     	pages.InputDocProcess().switchToDefault();
		pages.Main().logout();
		//login after put the default case list to Designer
		pages.Login().gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
        pages.DynaformExecution().sleep(18000);
     	pages.InputDocProcess().switchToDefault();
		pages.ProcessList().openProcess("RedirectLogin");
     	pages.InputDocProcess().switchToDefault();
		pages.Main().profile();
		pages.DynaformExecution().intoFrainMain();
		pages.Profile().editProfile();
        pages.DynaformExecution().sleep(8000);
		pages.Profile().changeDefaultMenu("ADMIN","");
        pages.DynaformExecution().sleep(8000);
		pages.Profile().saveProfile();
     	pages.InputDocProcess().switchToDefault();
		pages.Main().logout();
		//login after put the default case list to Admin
		pages.Login().gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
        pages.DynaformExecution().sleep(8000);
        pages.Admin().activePlugin("redirecLogin", true);
     	pages.InputDocProcess().switchToDefault();
		pages.Main().logout();
		//login after put the default case list to Admin
		pages.Login().gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
        pages.DynaformExecution().sleep(8000);
      	pages.InputDocProcess().switchToDefault();
		Assert.assertEquals("Enter search term", Value.getValue(browserInstance, FieldKeyType.ID, "txtSearch"));
     	pages.InputDocProcess().switchToDefault();
        this.goToUrl("http://192.168.11.132/sysworkflow/en/classic/setup/main");
        pages.Admin().activePlugin("redirecLogin", false);
		pages.InputDocProcess().switchToDefault();
		pages.Main().logout();
	}

    @After
    public void cleanup(){
        browserInstance.quit();
    }

}