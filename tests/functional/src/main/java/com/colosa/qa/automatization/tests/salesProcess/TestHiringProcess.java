package com.colosa.qa.automatization.tests.salesProcess;

import com.colosa.qa.automatization.common.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class TestHiringProcess extends com.colosa.qa.automatization.tests.common.Test{
	protected static int addJob=5;
	protected static int addJob1=3;

    public TestHiringProcess(String browserName) throws IOException {
        super(browserName);
    }

    @Test
    public void runProcess() throws Exception{
        pages.Login().gotoDefaultUrl();
        pages.Login().loginUser("hector","sample","workflow", "English");
        pages.Main().goHome();
        int casenumber=pages.Home().gotoNewCase().startCase("Employee Hiring process - v0.2 (Review Candidates list, select top 3.)");
        FormFieldData[][] gridData= new FormFieldData[addJob][1];

        for(int rows=0; rows<gridData.length; rows++){
                 gridData[rows][0]=new FormFieldData();
            }
            int cont=0;

         for(int rows=0; rows<gridData.length; rows++) {
            cont=rows+1;
        if(cont<=2)
        {
                gridData[rows][0].fieldPath="form[_list_of_candidates_for_hiring_]["+cont+"][CANDIDATE_TOP_3_PLACE]";
                gridData[rows][0].fieldFindType=FieldKeyType.ID;
                gridData[rows][0].fieldType=FieldType.DROPDOWN;
                gridData[rows][0].fieldValue="Second";
            }
            else
            { gridData[rows][0].fieldPath="form[_list_of_candidates_for_hiring_]["+cont+"][CANDIDATE_TOP_3_PLACE]";
                gridData[rows][0].fieldFindType=FieldKeyType.ID;
                gridData[rows][0].fieldType=FieldType.DROPDOWN;
                gridData[rows][0].fieldValue="First";
            }


            }
            FormFieldData[] fieldarray=new FormFieldData[1];
            fieldarray[0]= new FormFieldData();
            fieldarray[0].fieldPath="form[_review_candidates_list_form_submit_button]";
            fieldarray[0].fieldFindType=FieldKeyType.ID;
            fieldarray[0].fieldType=FieldType.BUTTON;
            fieldarray[0].fieldValue="";

            pages.InputDocProcess().openCaseFrame();
            Assert.assertTrue(GridFiller.gridFillElements(browserInstance, gridData));
            Assert.assertTrue(FormFiller.formFillElements(browserInstance, fieldarray));
            Assert.assertTrue(pages.InputDocProcess().continuebtn());
            pages.Main().logout();
            openTask2(casenumber);
    }

public void openTask2(int casenumber) throws Exception{
	pages.Login().gotoDefaultUrl();
	 pages.Login().loginUser("iver","sample","workflow", "English");
	 pages.Main().goHome();
	 opencase(casenumber);
    FormFieldData[][] gridData1= new FormFieldData[addJob][1];
    for(int rows=0; rows<gridData1.length;rows++){
    	gridData1[rows][0]=new FormFieldData();
    }
    int cont_2=0;
    for(int rows=0;rows<gridData1.length;rows++){
    	cont_2=rows+1;
    	if(cont_2<=2){
    	        gridData1[rows][0].fieldPath="form[_list_of_top_3_candidates_for_hiring]["+cont_2+"][CANDIDATE_TO_BE_HIRED]";
			    gridData1[rows][0].fieldFindType=FieldKeyType.ID;
			    gridData1[rows][0].fieldType=FieldType.DROPDOWN;
			    gridData1[rows][0].fieldValue="No";
		         }
	 	          else{
			    gridData1[rows][0].fieldPath="form[_list_of_top_3_candidates_for_hiring]["+cont_2+"][CANDIDATE_TO_BE_HIRED]";
			    gridData1[rows][0].fieldFindType=FieldKeyType.ID;
			    gridData1[rows][0].fieldType=FieldType.DROPDOWN;
			    gridData1[rows][0].fieldValue="Yes";
		       }
			}
			FormFieldData[] fieldarray2=new FormFieldData[1];
			fieldarray2[0]=new FormFieldData();
			fieldarray2[0].fieldPath="form[_hiring_decision_submit_button]";
		    fieldarray2[0].fieldFindType=FieldKeyType.ID;
		    fieldarray2[0].fieldType=FieldType.BUTTON;
		    fieldarray2[0].fieldValue="";

		    pages.InputDocProcess().openCaseFrame();
		    Assert.assertTrue(GridFiller.gridFillElements(browserInstance,  gridData1));
		    Assert.assertTrue(FormFiller.formFillElements(browserInstance,  fieldarray2));
		    Assert.assertTrue(pages.InputDocProcess().continuebtn());
		    pages.Main().logout();
		    openTask3(casenumber);

    }

    public void openTask3(int casenumber) throws Exception{
    	pages.Login().gotoDefaultUrl();
	 pages.Login().loginUser("ronald","sample","workflow", "English");
	 pages.Main().goHome();
	 opencase(casenumber);
	FormFieldData[] fieldarray3=new FormFieldData[1];
			fieldarray3[0]=new FormFieldData();
			fieldarray3[0].fieldPath="form[_hr_database_form_submit_button]";
		    fieldarray3[0].fieldFindType=FieldKeyType.ID;
		    fieldarray3[0].fieldType=FieldType.BUTTON;
		    fieldarray3[0].fieldValue="";

		     pages.InputDocProcess().openCaseFrame();
		    Assert.assertTrue(FormFiller.formFillElements(browserInstance,  fieldarray3));
		    Assert.assertTrue(pages.InputDocProcess().continuebtn());
		    pages.Main().logout();
		    openTask4(casenumber);
    }

    public void openTask4(int casenumber) throws Exception{
    	pages.Login().gotoDefaultUrl();
	 pages.Login().loginUser("pablo","sample","workflow", "English");
	 pages.Main().goHome();
	 opencase(casenumber);
	 FormFieldData[][] gridData2= new FormFieldData[addJob1][1];
    for(int rows=0; rows<gridData2.length;rows++){
    	gridData2[rows][0]=new FormFieldData();
    }
    int cont_3=0;
    for(int rows=0;rows<gridData2.length;rows++){
    	cont_3=rows+1;
    	if (cont_3<=1)
    	{
    	gridData2[rows][0].fieldPath="form[_hirees_list_]["+cont_3+"][negotiated_salary]";
		gridData2[rows][0].fieldFindType=FieldKeyType.ID;
		gridData2[rows][0].fieldType=FieldType.TEXTBOX;
		gridData2[rows][0].fieldValue="55";
	}
	else
	{gridData2[rows][0].fieldPath="form[_hirees_list_]["+cont_3+"][negotiated_salary]";
		gridData2[rows][0].fieldFindType=FieldKeyType.ID;
		gridData2[rows][0].fieldType=FieldType.TEXTBOX;
		gridData2[rows][0].fieldValue="80";

	}
    }
            FormFieldData[] fieldarray4=new FormFieldData[1];
			fieldarray4[0]=new FormFieldData();
			fieldarray4[0].fieldPath="form[_salary_negotiation_submit_button]";
		    fieldarray4[0].fieldFindType=FieldKeyType.ID;
		    fieldarray4[0].fieldType=FieldType.BUTTON;
		    fieldarray4[0].fieldValue="";

		    pages.InputDocProcess().openCaseFrame();
		    Assert.assertTrue(GridFiller.gridFillElements(browserInstance, gridData2));
		    Assert.assertTrue(FormFiller.formFillElements(browserInstance, fieldarray4));
		    Assert.assertTrue(pages.InputDocProcess().continuebtn());
		    pages.Main().logout();
}
public void opencase(int casenumber) throws Exception{ 
	pages.Home().openCase(casenumber);

}


    @After
    public void cleanup(){
        browserInstance.quit();
    }

}