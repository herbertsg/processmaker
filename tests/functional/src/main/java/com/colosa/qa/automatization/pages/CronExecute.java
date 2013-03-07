package com.colosa.qa.automatization.pages;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.Select;
import com.colosa.qa.automatization.common.*;
import com.colosa.qa.automatization.common.extJs.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

public class CronExecute{

	public static void execute(String workspace) throws FileNotFoundException, IOException, Exception{

		Date date = new Date();
		String url = ConfigurationSettings.getInstance().getSetting("server.url");
		url = url + "/cron_exec.php?wrkspc=" +workspace+ "&dt=" + date.toString();

		Browser.gotoUrl(url);

	}


}