package com.colosa.qa.automatization.tests.pmStringFunctions;

import com.colosa.qa.automatization.pages.DynaformExecution;
import org.junit.After;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestPMStringFunctions extends com.colosa.qa.automatization.tests.common.Test{

	protected static String str1 = "lkjlñasflsfjalsfsadlñfasñfjsañfjaslñfjsadñfjsañfsajñfsfasdñassañdfasñfsadfjsdlfjl";
	protected static String str2 = "JLDSAFJDSÑFJSADLFASÑFJSALDFSAÑFJLKSAFJSALÑFSJAÑFASJFÑSAJDFSAÑLJFSLÑAFJASHFKHSLDFIUWETYRIHKJ";
	protected static String str3 = "jkjHGKJGKjkjljñlGKJHKjkjlHJKHKKHKhkhk HKJHYkhkjhjhIYkhjy";

    public TestPMStringFunctions(String browserName) throws IOException {
        super(browserName);
    }

    @Test
	public void testCase() throws FileNotFoundException, IOException, Exception{

		pages.gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goHome();
		pages.Home().gotoNewCase().startCase("PM String Functions (Task 1)");
		DynaformExecution form = pages.DynaformExecution();
        form.intoDynaform();
		form.setFieldValue("upperC", str1);
		form.setFieldValue("lowC", str2);
		form.setFieldValue("capital", str3);
		form.setFieldValue("Send", "");
		//form.sleep(15000);
        pages.InputDocProcess().switchToDefault();
        pages.Main().logout();

	}


    @After
    public void cleanup(){
        browserInstance.quit();
    }
}