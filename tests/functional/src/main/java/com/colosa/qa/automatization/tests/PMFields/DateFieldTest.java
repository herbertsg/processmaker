package com.colosa.qa.automatization.tests.PMFields;

import com.colosa.qa.automatization.pages.DynaformExecution;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: herbert
 * Date: 6/12/13
 * Time: 3:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class DateFieldTest extends com.colosa.qa.automatization.tests.common.Test {
    public DateFieldTest(String browserName) throws IOException {
        super(browserName);
    }

    @Test
    public void runProcess() throws Exception{
        String datedmy = "04052013";
        String dateymd = "20130504";
        String datemdy = "05042013";
        String dateydm = "20130405";
        String datemyd = "05201304";
        String datedym = "04201305";

        //separator -
        String datedmy1 = "04-05-2013";
        String dateymd1 = "2013-05-04";
        String datemdy1 = "05-04-2013";
        String dateydm1 = "2013-04-05";
        String datemyd1 = "05-2013-04";
        String datedym1 = "04-2013-05";

        //separator /
        String datedmy2 = "04/05/2013";
        String dateymd2 = "2013/05/04";
        String datemdy2 = "05/04/2013";
        String dateydm2 = "2013/04/05";
        String datemyd2 = "05/2013/04";
        String datedym2 = "04/2013/05";

        //separator .
        String datedmy4 = "04.05.2013";
        String dateymd4 = "2013.05.04";
        String datemdy4 = "05.04.2013";
        String dateydm4 = "2013.04.05";
        String datemyd4 = "05.2013.04";
        String datedym4 = "04.2013.05";

        String hourhms = "142505";

        String hourhms2 = " 14:25:05";

        pages.gotoDefaultUrl();
        pages.Login().loginUser("admin","admin","workflow", "English");
        pages.Main().goHome();
        int casenumber=pages.Home().gotoNewCase().startCase("Test date field - mask combinations (Task 1)");

        DynaformExecution form = pages.DynaformExecution();
        form.intoDynaform();

        //separator -
        form.setFieldValue("fechaStaticDatesdmY", datedmy1);
        form.setFieldValue("fechaStaticDatesYmd", dateymd1);
        form.setFieldValue("fechaStaticDatesmdY", datemdy1);
        form.setFieldValue("fechaStaticDatesYdm", dateydm1);
        form.setFieldValue("fechaStaticDatesmYd", datemyd1);
        form.setFieldValue("fechaStaticDatesdYm", datedym1);

        //separator /
        form.setFieldValue("fechaStaticDates2dmY", datedmy2);
        form.setFieldValue("fechaStaticDates2Ymd", dateymd2);
        form.setFieldValue("fechaStaticDates2mdY", datemdy2);
        form.setFieldValue("fechaStaticDates2Ydm", dateydm2);
        form.setFieldValue("fechaStaticDates2mYd", datemyd2);
        form.setFieldValue("fechaStaticDates2dYm", datedym2);

        //separator .
        form.setFieldValue("fechaStaticDates4dmY", datedmy4);
        form.setFieldValue("fechaStaticDates4Ymd", dateymd4);
        form.setFieldValue("fechaStaticDates4mdY", datemdy4);
        form.setFieldValue("fechaStaticDates4Ydm", dateydm4);
        form.setFieldValue("fechaStaticDates4mYd", datemyd4);
        form.setFieldValue("fechaStaticDates4dYm", datedym4);

        form.clickButton("savee");

        DynaformExecution form2 = pages.DynaformExecution();
        form2.intoDynaform();

        //separator -
        form2.setFieldValue("fechahoradmyhms", datedmy1 + hourhms2);
        form2.setFieldValue("fechahoraymdhms", dateymd1 + hourhms2);
        form2.setFieldValue("fechahoramdyhms", datemdy1 + hourhms2);

        //separator /
        form2.setFieldValue("fechahoradmyhms2", datedmy2 + hourhms2);
        form2.setFieldValue("fechahoraymdhms2", dateymd2 + hourhms2);
        form2.setFieldValue("fechahoramdyhms2", datemdy2 + hourhms2);

        //separator .
        form2.setFieldValue("fechahoradmyhms4", datedmy4 + hourhms2);
        form2.setFieldValue("fechahoraymdhms4", dateymd4 + hourhms2);
        form2.setFieldValue("fechahoramdyhms4", datemdy4 + hourhms2);

        form2.clickButton("savee");

        pages.AssignTask().pressContinueButton();

        //pages.Home().gotoInbox();
        //Assert.assertTrue("The case does not exist in inbox", pages.Home().gotoInbox().existCase(casenumber));
        pages.Home().gotoInbox().openCase(casenumber);

        DynaformExecution form3 = pages.DynaformExecution();
        form3.intoDynaform();

        //separator -
        Assert.assertEquals("Error in date mask", form3.getFieldValue("fechaStaticDatesdmY"), datedmy1);
        Assert.assertEquals("Error in date mask", form3.getFieldValue("fechaStaticDatesYmd"), dateymd1);
        Assert.assertEquals("Error in date mask", form3.getFieldValue("fechaStaticDatesmdY"), datemdy1);
        Assert.assertEquals("Error in date mask", form3.getFieldValue("fechaStaticDatesYdm"), dateydm1);
        Assert.assertEquals("Error in date mask", form3.getFieldValue("fechaStaticDatesmYd"), datemyd1);
        Assert.assertEquals("Error in date mask", form3.getFieldValue("fechaStaticDatesdYm"), datedym1);

        //separator /
        Assert.assertEquals("Error in date mask", form3.getFieldValue("fechaStaticDates2dmY"), datedmy2);
        Assert.assertEquals("Error in date mask", form3.getFieldValue("fechaStaticDates2Ymd"), dateymd2);
        Assert.assertEquals("Error in date mask", form3.getFieldValue("fechaStaticDates2mdY"), datemdy2);
        Assert.assertEquals("Error in date mask", form3.getFieldValue("fechaStaticDates2Ydm"), dateydm2);
        Assert.assertEquals("Error in date mask", form3.getFieldValue("fechaStaticDates2mYd"), datemyd2);
        Assert.assertEquals("Error in date mask", form3.getFieldValue("fechaStaticDates2dYm"), datedym2);

        //separator .
        Assert.assertEquals("Error in date mask", form3.getFieldValue("fechaStaticDates4dmY"), datedmy4);
        Assert.assertEquals("Error in date mask", form3.getFieldValue("fechaStaticDates4Ymd"), dateymd4);
        Assert.assertEquals("Error in date mask", form3.getFieldValue("fechaStaticDates4mdY"), datemdy4);
        Assert.assertEquals("Error in date mask", form3.getFieldValue("fechaStaticDates4Ydm"), dateydm4);
        Assert.assertEquals("Error in date mask", form3.getFieldValue("fechaStaticDates4mYd"), datemyd4);
        Assert.assertEquals("Error in date mask", form3.getFieldValue("fechaStaticDates4dYm"), datedym4);

        form3.clickButton("savee");

        DynaformExecution form4 = pages.DynaformExecution();
        form4.intoDynaform();

        //separator -
        Assert.assertEquals("Error in date mask", form4.getFieldValue("fechahoradmyhms"), datedmy1+hourhms2);
        Assert.assertEquals("Error in date mask", form4.getFieldValue("fechahoraymdhms"), dateymd1+hourhms2);
        Assert.assertEquals("Error in date mask", form4.getFieldValue("fechahoramdyhms"), datemdy1+hourhms2);

        //separator /
        Assert.assertEquals("Error in date mask", form4.getFieldValue("fechahoradmyhms2"), datedmy2+hourhms2);
        Assert.assertEquals("Error in date mask", form4.getFieldValue("fechahoraymdhms2"), dateymd2+hourhms2);
        Assert.assertEquals("Error in date mask", form4.getFieldValue("fechahoramdyhms2"), datemdy2+hourhms2);

        //separator .
        Assert.assertEquals("Error in date mask", form4.getFieldValue("fechahoradmyhms4"), datedmy4+hourhms2);
        Assert.assertEquals("Error in date mask", form4.getFieldValue("fechahoraymdhms4"), dateymd4+hourhms2);
        Assert.assertEquals("Error in date mask", form4.getFieldValue("fechahoramdyhms4"), datemdy4+hourhms2);

        //pages.Main().logout();
    }

    @After
    public void cleanup(){
        browserInstance.quit();
    }
}
