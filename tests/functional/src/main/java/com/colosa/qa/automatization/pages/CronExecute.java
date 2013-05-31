package com.colosa.qa.automatization.pages;

import com.colosa.qa.automatization.common.BrowserInstance;
import com.colosa.qa.automatization.common.ConfigurationSettings;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

public class CronExecute extends Page{

    public CronExecute(BrowserInstance browserInstance) throws Exception {
        super(browserInstance);

        verifyPage();
    }

    @Override
    public void verifyPage() throws Exception {
        //return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void execute(String workspace) throws FileNotFoundException, IOException, Exception{

		Date date = new Date();
		String url = ConfigurationSettings.getInstance().getSetting("server.url");
		url = url + "/cron_exec.php?wrkspc=" +workspace+ "&dt=" + date.toString();

		browser.gotoUrl(url);

	}

}