package com.colosa.qa.automatization.pages;

import com.colosa.qa.automatization.common.BrowserInstance;

public class Pages{
    protected BrowserInstance _browserInstance;

    private Pages(){

    }

    public Pages(BrowserInstance browserInstance){
        _browserInstance = browserInstance;
    }

	public Login Login() throws Exception{

		Login loginPage = new Login(_browserInstance);
		
		return loginPage;
	}

	public Main Main() throws Exception{

		Main mainPage = new Main(_browserInstance);

		return mainPage;
	}

	public Home Home() throws Exception{


		Home page = new Home(_browserInstance);

		return page;
	}

	public Admin Admin() throws Exception{


		Admin page = new Admin(_browserInstance);

		return page;
	}

	public Documents Documents() throws Exception{
		Documents document = new Documents(_browserInstance);

		return document;
	}

	public ProcessDesigner ProcessDesigner() throws Exception{
		ProcessDesigner processDesigner = new ProcessDesigner(_browserInstance);

		return processDesigner;
	}

	public ProcessList ProcessList() throws Exception{
		ProcessList processList = new ProcessList(_browserInstance);

		return processList;
	}

	public InputDocProcess InputDocProcess() throws Exception{
		InputDocProcess inputDocProcess = new InputDocProcess(_browserInstance);

		return inputDocProcess;
	}

	public OutputDocProcess OutputDocProcess() throws Exception{
		OutputDocProcess outputDocProcess = new OutputDocProcess(_browserInstance);

		return outputDocProcess;
	}	

	public InputDocumentList InputDocumentList() throws Exception{
		InputDocumentList inputdDocList = new InputDocumentList(_browserInstance);

		return inputdDocList;
	}

	public OutputDocumentList OutputDocumentList() throws Exception{
		OutputDocumentList outputdDocList = new OutputDocumentList(_browserInstance);

		return outputdDocList;
	}

	public DynaformDesigner DynaformDesigner() throws Exception{
		
		return new DynaformDesigner(_browserInstance);
	}

	public TriggersProcess TriggersProcess() throws Exception{
		TriggersProcess triggerProcess = new TriggersProcess(_browserInstance);

		return triggerProcess;
	}

	public Designer Designer() throws Exception{
		Designer designer = new Designer(_browserInstance);

		return designer;
	}	

	public TaskProperties TaskProperties() throws Exception{
		TaskProperties taskProperties = new TaskProperties(_browserInstance);

		return taskProperties;
	}

	public DynaformExecution DynaformExecution() throws Exception{
		DynaformExecution dynaformExecution = new DynaformExecution(_browserInstance);

		return dynaformExecution;
	}

    public AssignTask AssignTask() throws Exception{
        AssignTask assignTask = new AssignTask(_browserInstance);

        return assignTask;
    }

	public Profile Profile() throws Exception{
		Profile profile = new Profile(_browserInstance);

		return profile;
	}	

	public DebugExecution DebugExecution() throws Exception{
		DebugExecution debugExecution = new DebugExecution(_browserInstance);

		return debugExecution;
	}

	public CronExecute CronExecute() throws Exception{
		CronExecute cronExecute = new CronExecute(_browserInstance);

		return cronExecute;
	}

	public WebEntry WebEntry() throws Exception{
		WebEntry webEntry = new WebEntry(_browserInstance);

		return webEntry;
	}

	public CaseTracker CaseTracker() throws Exception{
		CaseTracker caseTracker = new CaseTracker(_browserInstance);

		return caseTracker;
	}

	public PmslaReport PmslaReport() throws Exception{
		PmslaReport pmslaReport = new PmslaReport(_browserInstance);

		return pmslaReport;
	}

	public PMSLAConfiguration PMSLAConfiguration() throws Exception{
		PMSLAConfiguration pmslaConfiguration = new PMSLAConfiguration(_browserInstance);

		return pmslaConfiguration;
	}

}