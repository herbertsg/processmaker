package com.colosa.qa.automatization.tests.PMFields;

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

        pages.DynaformExecution().intoDynaform();

        //separator -
        pages.DynaformExecution().setFieldValue("fechaStaticDatesdmY", datedmy);
        pages.DynaformExecution().setFieldValue("fechaStaticDatesYmd", dateymd);
        pages.DynaformExecution().setFieldValue("fechaStaticDatesmdY", datemdy);
        pages.DynaformExecution().setFieldValue("fechaStaticDatesYdm", dateydm);
        pages.DynaformExecution().setFieldValue("fechaStaticDatesmYd", datemyd);
        pages.DynaformExecution().setFieldValue("fechaStaticDatesdYm", datedym);

        //separator /
        pages.DynaformExecution().setFieldValue("fechaStaticDates2dmY", datedmy);
        pages.DynaformExecution().setFieldValue("fechaStaticDates2Ymd", dateymd);
        pages.DynaformExecution().setFieldValue("fechaStaticDates2mdY", datemdy);
        pages.DynaformExecution().setFieldValue("fechaStaticDates2Ydm", dateydm);
        pages.DynaformExecution().setFieldValue("fechaStaticDates2mYd", datemyd);
        pages.DynaformExecution().setFieldValue("fechaStaticDates2dYm", datedym);

        //separator .
        pages.DynaformExecution().setFieldValue("fechaStaticDates4dmY", datedmy);
        pages.DynaformExecution().setFieldValue("fechaStaticDates4Ymd", dateymd);
        pages.DynaformExecution().setFieldValue("fechaStaticDates4mdY", datemdy);
        pages.DynaformExecution().setFieldValue("fechaStaticDates4Ydm", dateydm);
        pages.DynaformExecution().setFieldValue("fechaStaticDates4mYd", datemyd);
        pages.DynaformExecution().setFieldValue("fechaStaticDates4dYm", datedym);

        pages.DynaformExecution().clickButton("savee");

        pages.DynaformExecution().intoDynaform();

        //separator -
        pages.DynaformExecution().setFieldValue("fechahoradmyhms", datedmy + hourhms);
        pages.DynaformExecution().setFieldValue("fechahoraymdhms", dateymd + hourhms);
        pages.DynaformExecution().setFieldValue("fechahoramdyhms", datemdy + hourhms);

        //separator /
        pages.DynaformExecution().setFieldValue("fechahoradmyhms2", datedmy + hourhms);
        pages.DynaformExecution().setFieldValue("fechahoraymdhms2", dateymd + hourhms);
        pages.DynaformExecution().setFieldValue("fechahoramdyhms2", datemdy + hourhms);

        //separator .
        pages.DynaformExecution().setFieldValue("fechahoradmyhms4", datedmy + hourhms);
        pages.DynaformExecution().setFieldValue("fechahoraymdhms4", dateymd + hourhms);
        pages.DynaformExecution().setFieldValue("fechahoramdyhms4", datemdy + hourhms);

        pages.DynaformExecution().clickButton("savee");

        pages.AssignTask().pressContinueButton();

        //pages.Home().gotoInbox();
        //Assert.assertTrue("The case does not exist in inbox", pages.Home().gotoInbox().existCase(casenumber));
        pages.Home().gotoInbox().openCase(casenumber);

        pages.DynaformExecution().intoDynaform();

        //separator -
        Assert.assertEquals("Error in date mask", pages.DynaformExecution().getFieldValue("fechaStaticDatesdmY"), datedmy1);
        Assert.assertEquals("Error in date mask", pages.DynaformExecution().getFieldValue("fechaStaticDatesYmd"), dateymd1);
        Assert.assertEquals("Error in date mask", pages.DynaformExecution().getFieldValue("fechaStaticDatesmdY"), datemdy1);
        Assert.assertEquals("Error in date mask", pages.DynaformExecution().getFieldValue("fechaStaticDatesYdm"), dateydm1);
        Assert.assertEquals("Error in date mask", pages.DynaformExecution().getFieldValue("fechaStaticDatesmYd"), datemyd1);
        Assert.assertEquals("Error in date mask", pages.DynaformExecution().getFieldValue("fechaStaticDatesdYm"), datedym1);

        //separator /
        Assert.assertEquals("Error in date mask", pages.DynaformExecution().getFieldValue("fechaStaticDates2dmY"), datedmy2);
        Assert.assertEquals("Error in date mask", pages.DynaformExecution().getFieldValue("fechaStaticDates2Ymd"), dateymd2);
        Assert.assertEquals("Error in date mask", pages.DynaformExecution().getFieldValue("fechaStaticDates2mdY"), datemdy2);
        Assert.assertEquals("Error in date mask", pages.DynaformExecution().getFieldValue("fechaStaticDates2Ydm"), dateydm2);
        Assert.assertEquals("Error in date mask", pages.DynaformExecution().getFieldValue("fechaStaticDates2mYd"), datemyd2);
        Assert.assertEquals("Error in date mask", pages.DynaformExecution().getFieldValue("fechaStaticDates2dYm"), datedym2);

        //separator .
        Assert.assertEquals("Error in date mask", pages.DynaformExecution().getFieldValue("fechaStaticDates4dmY"), datedmy4);
        Assert.assertEquals("Error in date mask", pages.DynaformExecution().getFieldValue("fechaStaticDates4Ymd"), dateymd4);
        Assert.assertEquals("Error in date mask", pages.DynaformExecution().getFieldValue("fechaStaticDates4mdY"), datemdy4);
        Assert.assertEquals("Error in date mask", pages.DynaformExecution().getFieldValue("fechaStaticDates4Ydm"), dateydm4);
        Assert.assertEquals("Error in date mask", pages.DynaformExecution().getFieldValue("fechaStaticDates4mYd"), datemyd4);
        Assert.assertEquals("Error in date mask", pages.DynaformExecution().getFieldValue("fechaStaticDates4dYm"), datedym4);

        pages.DynaformExecution().clickButton("savee");

        pages.DynaformExecution().intoDynaform();

        //separator -
        Assert.assertEquals("Error in date mask", pages.DynaformExecution().getFieldValue("fechahoradmyhms"), datedmy1+hourhms2);
        Assert.assertEquals("Error in date mask", pages.DynaformExecution().getFieldValue("fechahoraymdhms"), dateymd1+hourhms2);
        Assert.assertEquals("Error in date mask", pages.DynaformExecution().getFieldValue("fechahoramdyhms"), datemdy1+hourhms2);

        //separator /
        Assert.assertEquals("Error in date mask", pages.DynaformExecution().getFieldValue("fechahoradmyhms2"), datedmy2+hourhms2);
        Assert.assertEquals("Error in date mask", pages.DynaformExecution().getFieldValue("fechahoraymdhms2"), dateymd2+hourhms2);
        Assert.assertEquals("Error in date mask", pages.DynaformExecution().getFieldValue("fechahoramdyhms2"), datemdy2+hourhms2);

        //separator .
        Assert.assertEquals("Error in date mask", pages.DynaformExecution().getFieldValue("fechahoradmyhms4"), datedmy4+hourhms2);
        Assert.assertEquals("Error in date mask", pages.DynaformExecution().getFieldValue("fechahoraymdhms4"), dateymd4+hourhms2);
        Assert.assertEquals("Error in date mask", pages.DynaformExecution().getFieldValue("fechahoramdyhms4"), datemdy4+hourhms2);

        pages.Main().logout();
    }

    @After
    public void cleanup(){
        browserInstance.quit();
    }
}
