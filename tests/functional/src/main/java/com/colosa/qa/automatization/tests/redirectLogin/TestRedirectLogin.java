package com.colosa.qa.automatization.tests.redirectLogin;

import org.junit.Assert;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.After;

import com.colosa.qa.automatization.pages.*;
import com.colosa.qa.automatization.common.*;
import org.openqa.selenium.WebElement;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestRedirectLogin{

	protected static int caseNum;

	@Test
	public void executeRedirectLogin() throws FileNotFoundException, IOException, Exception{

		Pages.Login().gotoUrl();
		Pages.Login().loginUser("admin", "admin", "workflow");
		Pages.Main().profile();
		Pages.DynaformExecution().intoFrainMain();	
		Pages.Profile().editProfile();
        Pages.DynaformExecution().sleep(8000);       
		Pages.Profile().changeDefaultMenu("HOME","New case"); 
        Pages.DynaformExecution().sleep(8000);   
		Pages.Profile().saveProfile();    
     	Pages.InputDocProcess().switchToDefault();
		Pages.Main().logout();
		//login after put the default case list to Home-->Newcase
		Pages.Login().gotoUrl();
		Pages.Login().loginUser("admin", "admin", "");
		Pages.Main().goHome();	
		Pages.DynaformExecution().intoPmtrack();
		Assert.assertEquals("Find a Process", Value.getValue(FieldKeyType.ID, "processesFilter"));			
		caseNum = Pages.Home().startCase("RedirectLogin (Ini)");
     	Pages.InputDocProcess().switchToDefault();
		Pages.Main().profile();
		Pages.DynaformExecution().intoFrainMain();	
		Pages.Profile().editProfile();		
        Pages.DynaformExecution().sleep(8000);       
		Pages.Profile().changeDefaultMenu("HOME","Draft"); 	
        Pages.DynaformExecution().sleep(8000);   
		Pages.Profile().saveProfile();    
     	Pages.InputDocProcess().switchToDefault();
		Pages.Main().logout();
		//login after put the default case list to Home-->Draft
		Pages.Login().gotoUrl();
		Pages.Login().loginUser("admin", "admin", "");
        Pages.DynaformExecution().sleep(8000); 	
     	Pages.InputDocProcess().switchToDefault();
		Assert.assertTrue("The case does not exist in Draft", Pages.Home().existCase(caseNum));
		Pages.Home().openCase(caseNum);	
		Pages.DynaformExecution().intoDynaform();
		Assert.assertTrue("The button Continue does not exit in this form", Pages.InputDocProcess().continuebtn());	
     	Pages.InputDocProcess().switchToDefault();
		Pages.Main().profile();
		Pages.DynaformExecution().intoFrainMain();	
		Pages.Profile().editProfile();		
        Pages.DynaformExecution().sleep(8000);       
		Pages.Profile().changeDefaultMenu("HOME","Unassigned"); 	
        Pages.DynaformExecution().sleep(8000);   
		Pages.Profile().saveProfile();    
     	Pages.InputDocProcess().switchToDefault();
		Pages.Main().logout();
		//login after put the default case list to Home-->Unassigned
		Pages.Login().gotoUrl();
		Pages.Login().loginUser("admin", "admin", "");
        Pages.DynaformExecution().sleep(8000); 	
     	Pages.InputDocProcess().switchToDefault();
		Assert.assertTrue("The case does not exist in Unassigned", Pages.Home().existCase(caseNum));
     	Pages.InputDocProcess().switchToDefault();
		Pages.Main().profile();
		Pages.DynaformExecution().intoFrainMain();	
		Pages.Profile().editProfile();		
        Pages.DynaformExecution().sleep(8000);       
		Pages.Profile().changeDefaultMenu("HOME","Participated"); 	
        Pages.DynaformExecution().sleep(8000);   
		Pages.Profile().saveProfile();    
     	Pages.InputDocProcess().switchToDefault();
		Pages.Main().logout();
		//login after put the default case list to Home-->Participated
		Pages.Login().gotoUrl();
		Pages.Login().loginUser("admin", "admin", "");
        Pages.DynaformExecution().sleep(8000); 	
     	Pages.InputDocProcess().switchToDefault();
		Assert.assertTrue("The case does not exist in Participated", Pages.Home().existCase(caseNum));
     	Pages.InputDocProcess().switchToDefault();
		Pages.Main().profile();
		Pages.DynaformExecution().intoFrainMain();	
		Pages.Profile().editProfile();		
        Pages.DynaformExecution().sleep(8000);       
		Pages.Profile().changeDefaultMenu("DESIGNER",""); 	
        Pages.DynaformExecution().sleep(8000);   
		Pages.Profile().saveProfile();    
     	Pages.InputDocProcess().switchToDefault();
		Pages.Main().logout();
		//login after put the default case list to Designer
		Pages.Login().gotoUrl();
		Pages.Login().loginUser("admin", "admin", "");
        Pages.DynaformExecution().sleep(8000); 	
     	Pages.InputDocProcess().switchToDefault();		
		Pages.ProcessList().openProcess("RedirectLogin");
     	Pages.InputDocProcess().switchToDefault();
		Pages.Main().profile();
		Pages.DynaformExecution().intoFrainMain();	
		Pages.Profile().editProfile();		
        Pages.DynaformExecution().sleep(8000);       
		Pages.Profile().changeDefaultMenu("ADMIN",""); 	
        Pages.DynaformExecution().sleep(8000);   
		Pages.Profile().saveProfile();    
     	Pages.InputDocProcess().switchToDefault();
		Pages.Main().logout();
		//login after put the default case list to Admin
		Pages.Login().gotoUrl();
		Pages.Login().loginUser("admin", "admin", "");
        Pages.DynaformExecution().sleep(8000); 
        Pages.Admin().activePlugin("redirecLogin", true);   
     	Pages.InputDocProcess().switchToDefault();
		Pages.Main().logout();
		//login after put the default case list to Admin
		Pages.Login().gotoUrl();
		Pages.Login().loginUser("admin", "admin", "");
        Pages.DynaformExecution().sleep(8000); 
      	Pages.InputDocProcess().switchToDefault();       
		Assert.assertEquals("Enter search term", Value.getValue(FieldKeyType.ID, "txtSearch"));
     	Pages.InputDocProcess().switchToDefault();
 		Pages.Main().goToUrl("http://192.168.11.132/sysworkflow/en/classic/setup/main");  
        Pages.Admin().activePlugin("redirecLogin", false);  		  	
		Pages.InputDocProcess().switchToDefault();
		Pages.Main().logout();
	}

//    @After
//    public void cleanup(){
//        Browser.close();
//    }

}