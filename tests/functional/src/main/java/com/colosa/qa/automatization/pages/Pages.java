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

	public static Home Home() throws Exception{
		Home page = new Home();

		return page;
	}

	public static Documents Documents() throws Exception{
		Documents document = new Documents();

		return document;
	}

	public static ProcessList ProcessList() throws Exception{
		ProcessList processList = new ProcessList();

		return processList;
	}	
}