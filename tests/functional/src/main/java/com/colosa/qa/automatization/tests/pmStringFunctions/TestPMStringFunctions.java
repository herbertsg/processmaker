package com.colosa.qa.automatization.tests.pmStringFunctions;

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

		pages.Login().gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goHome();
		pages.Home().startCase("PM String Functions (Task 1)");
		pages.DynaformExecution().intoDynaform();
		pages.DynaformExecution().setFieldValue("upperC", str1);
		pages.DynaformExecution().setFieldValue("lowC", str2);
		pages.DynaformExecution().setFieldValue("capital", str3);
		pages.DynaformExecution().setFieldValue("Send", "");
		pages.DynaformExecution().sleep(15000);
        pages.InputDocProcess().switchToDefault();
        pages.Main().logout();

	}


/*    @After
    public void cleanup(){
        Browser.close();
    }*/
}