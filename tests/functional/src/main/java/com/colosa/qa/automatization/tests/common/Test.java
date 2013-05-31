package com.colosa.qa.automatization.tests.common;

import com.colosa.qa.automatization.common.BrowserSettings;
import com.colosa.qa.automatization.common.BrowserInstance;
import com.colosa.qa.automatization.common.ConfigurationSettings;
import com.colosa.qa.automatization.pages.Pages;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.junit.After;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: Herbert Saal
 * Date: 3/28/13
 * Time: 11:58 AM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(value = Parameterized.class)
public abstract class Test {
    protected String browserName;
    protected BrowserInstance browserInstance;
    protected Pages pages;

    public Test(String browserName) throws IOException {

        this.browserName = browserName;

        //initialize test pages
        initializeTest();
        //initialize pages
        pages = new Pages(browserInstance);
    }

    @Parameters
    public static Collection<Object[]> data() {
        int browserCount = 1;
        Object[][] data = null;
        //get the supported browsers from configuration file:
        try {
            browserCount = Integer.parseInt(ConfigurationSettings.getInstance().getSetting("browser.count"));

            data = new Object[browserCount][1];
            for (int i = 0; i< browserCount; i++){
                String browserName = ConfigurationSettings.getInstance().getSetting("browser.browser" + (i + 1));
                data[i][0] = browserName;
                //System.out.println("Browser: "+browserName);
            }
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        //Object[][] data = new Object[][] { { "firefox" }, { "chrome" }, { "ie" } };
        //System.out.println(Arrays.toString(data));

        return Arrays.asList(data);

        /*return Arrays.asList(new Object[][]) {
            { "AGCCG", "AGTTA" },
            { "AGTTA", "GATCA" },
            { "GGGAT", "AGCCA" }
        } */
    }

    public void initializeTest() throws IOException {
        //creates the instance of the Browser
        BrowserSettings browserSettings = BrowserInstance.getDefaultBrowserSettings();
        //change browser name
        browserSettings.setBrowserName(browserName);
        browserInstance = new BrowserInstance(browserSettings);
    }

    public void goToUrl(String url){
        browserInstance.gotoUrl(url);
    }
}
