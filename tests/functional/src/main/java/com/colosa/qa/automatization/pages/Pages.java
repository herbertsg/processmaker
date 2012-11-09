package com.colosa.qa.automatization.pages;

public class Pages{

	public static Login Login() throws Exception{
		Login loginPage = new Login();
		
		return loginPage;
	}

	public static Main Main() throws Exception{
		Main mainPage = new Main();

		return mainPage;
	}
}