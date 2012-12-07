package com.colosa.qa.automatization.tests.processExecutionForEvents;

import org.junit.Assert;
import org.junit.AfterClass;
import org.junit.Test;

import com.colosa.qa.automatization.pages.*;
import com.colosa.qa.automatization.common.*;
import org.openqa.selenium.WebElement;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestEventIntermediate{
@Test
	public void runProcess() throws Exception{
		Pages.Login().gotoUrl();
		Pages.Login().loginUser("admin","admin","workflow");
    Pages.Main().goHome();
    int casenumber=Pages.Home().startCase("Event Process - Intermediate Conditional_Multple Task (Task 1)");
    openCaseFrame();
    FormFieldData[] fieldArray=new FormFieldData[4];
    fieldArray[0]=new FormFieldData();
    fieldArray[1]=new FormFieldData();
    fieldArray[2]=new FormFieldData();
    fieldArray[3]=new FormFieldData();

    fieldArray[0].fieldPath="form[nombre]";
    fieldArray[0].fieldFindType="id";
    fieldArray[0].fieldType="textbox";
    fieldArray[0].fieldValue="rodrigo ivan";

    fieldArray[1].fieldPath="form[edad]";
    fieldArray[1].fieldFindType="id";
    fieldArray[1].fieldType="textbox";
    fieldArray[1].fieldValue="24";

    fieldArray[2].fieldPath="form[departamento]";
    fieldArray[2].fieldFindType="id";
    fieldArray[2].fieldType="dropdown";
    fieldArray[2].fieldValue="La Paz";

    fieldArray[3].fieldPath="form[guardar]";
    fieldArray[3].fieldFindType="id";
    fieldArray[3].fieldType="button";
    fieldArray[3].fieldValue="";

    Assert.assertTrue(FormFiller.formFillElements(fieldArray));
        Assert.assertTrue(Pages.InputDocProcess().continuebtn());
    openTask2(casenumber);
}

public void openTask2(int casenumber) throws Exception{

    //Pages.Home().openCase(175);
    opencase(casenumber);
    openCaseFrame();
    FormFieldData[] fieldArray2=new FormFieldData[2];
    fieldArray2[0]=new FormFieldData();
    fieldArray2[1]=new FormFieldData();
    
    fieldArray2[0].fieldPath="form[estadocivil]";
    fieldArray2[0].fieldFindType="id";
    fieldArray2[0].fieldType="dropdown";
    fieldArray2[0].fieldValue="soltero";

    fieldArray2[1].fieldPath="form[send]";
    fieldArray2[1].fieldFindType="id";
    fieldArray2[1].fieldType="button";
    fieldArray2[1].fieldValue="";

     Assert.assertTrue(FormFiller.formFillElements(fieldArray2));
        Assert.assertTrue(Pages.InputDocProcess().continuebtn());
    openTask3(casenumber);

}

public void openTask3(int casenumber) throws Exception{
	 //Pages.Home().openCase(176);
    opencase(casenumber);
	 openCaseFrame();
	 FormFieldData[] fieldArray3=new FormFieldData[1];
	 fieldArray3[0]=new FormFieldData();

	 fieldArray3[0].fieldPath="form[send]";
	 fieldArray3[0].fieldFindType="id";
	 fieldArray3[0].fieldType="button";
	 fieldArray3[0].fieldValue="";

	  Assert.assertTrue(FormFiller.formFillElements(fieldArray3));
        Assert.assertTrue(Pages.InputDocProcess().continuebtn());
    openTask4(casenumber);

}

public void openTask4(int casenumber) throws Exception{
	 //Pages.Home().openCase(177);
    opencase(casenumber);
	 openCaseFrame();
	 FormFieldData[] fieldArray4=new FormFieldData[2];
	 fieldArray4[0]=new FormFieldData();
     fieldArray4[1]=new FormFieldData();

	 fieldArray4[0].fieldPath="form[profesion][ingeniero]";
	 fieldArray4[0].fieldFindType="id";
	 fieldArray4[0].fieldType="radiobutton";
	 fieldArray4[0].fieldValue="";

	 fieldArray4[1].fieldPath="form[send]";
	 fieldArray4[1].fieldFindType="id";
	 fieldArray4[1].fieldType="button";
	 fieldArray4[1].fieldValue="";

	  Assert.assertTrue(FormFiller.formFillElements(fieldArray4));
        Assert.assertTrue(Pages.InputDocProcess().continuebtn());


}


public void openCaseFrame() throws Exception{
	    Browser.driver().switchTo().defaultContent();
		Browser.driver().switchTo().frame("casesFrame");
		Browser.driver().switchTo().frame("casesSubFrame");
		Browser.driver().switchTo().frame("openCaseFrame");
}
public void opencase(int casenumber)throws Exception{
Pages.Home().openCase(casenumber);
}

}