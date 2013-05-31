package com.colosa.qa.automatization.tests.TestValidate;

import com.colosa.qa.automatization.common.FieldKeyType;
import com.colosa.qa.automatization.common.FieldType;
import com.colosa.qa.automatization.common.FormFieldData;
import com.colosa.qa.automatization.common.FormFiller;
import org.junit.Assert;
import org.junit.Test;
import org.junit.After;

import java.io.IOException;

public class ValidateFieldsTest extends com.colosa.qa.automatization.tests.common.Test{

    public ValidateFieldsTest(String browserName) throws IOException {
        super(browserName);
    }

    @Test
 	public void runProcess() throws Exception{
 		pages.Login().gotoDefaultUrl();
   	pages.Login().loginUser("admin","admin","workflow", "English");
    pages.Main().goHome();
    int casenumber=pages.Home().gotoNewCase().startCase("TestValidate (Task 1)");
    pages.DynaformExecution().intoDynaform();
    
    FormFieldData[] fieldArray=new FormFieldData[12];

    fieldArray[0]=new FormFieldData();
    fieldArray[1]=new FormFieldData();
    fieldArray[2]=new FormFieldData();
    fieldArray[3]=new FormFieldData();
    fieldArray[4]=new FormFieldData();
    fieldArray[5]=new FormFieldData();
    fieldArray[6]=new FormFieldData();
    fieldArray[7]=new FormFieldData();
    fieldArray[8]=new FormFieldData();
    fieldArray[9]=new FormFieldData();
    fieldArray[10]=new FormFieldData();
    fieldArray[11]=new FormFieldData();
    
    fieldArray[0].fieldPath="form[TEXT_ANY]";
    fieldArray[0].fieldFindType=FieldKeyType.ID;
    fieldArray[0].fieldType=FieldType.TEXTBOX;
    fieldArray[0].fieldValue="validation89()?¿'/&%$@|€";
    
    fieldArray[1].fieldPath="form[TEXT_ALPHABETIC]";
    fieldArray[1].fieldFindType=FieldKeyType.ID;
    fieldArray[1].fieldType=FieldType.TEXTBOX;
    fieldArray[1].fieldValue="784validation89()?¿'/&%$AA BB";
    
    fieldArray[2].fieldPath="form[TEXT_ALPHANUMERIC]";
    fieldArray[2].fieldFindType=FieldKeyType.ID;
    fieldArray[2].fieldType=FieldType.TEXTBOX;
    fieldArray[2].fieldValue="@#12valida%&tion123.,)02";
    
    fieldArray[3].fieldPath="form[TEXT_INTEGER]";
    fieldArray[3].fieldFindType=FieldKeyType.ID;
    fieldArray[3].fieldType=FieldType.TEXTBOX;
    fieldArray[3].fieldValue="TG,,@#12345hh6789.as";
    
    fieldArray[4].fieldPath="form[TEXT_REAL]";
    fieldArray[4].fieldFindType=FieldKeyType.ID;
    fieldArray[4].fieldType=FieldType.TEXTBOX;
    fieldArray[4].fieldValue="AA@#123";
    
    fieldArray[5].fieldPath="form[TEXT_EMAIL]";
    fieldArray[5].fieldFindType=FieldKeyType.ID;
    fieldArray[5].fieldType=FieldType.TEXTBOX;
    fieldArray[5].fieldValue="email@colosa.com";
    
		fieldArray[6].fieldPath="form[TEXT_LOGIN]";
    fieldArray[6].fieldFindType=FieldKeyType.ID;
    fieldArray[6].fieldType=FieldType.TEXTBOX;
    fieldArray[6].fieldValue="admin admin@#";
    
    
    fieldArray[7].fieldPath="form[CURRENCY_REAL]";
    fieldArray[7].fieldFindType=FieldKeyType.ID;
    fieldArray[7].fieldType=FieldType.TEXTBOX;
    fieldArray[7].fieldValue="123456789012";
    
		fieldArray[8].fieldPath="form[CURRENCY_INTEGER]";
    fieldArray[8].fieldFindType=FieldKeyType.ID;
    fieldArray[8].fieldType=FieldType.TEXTBOX;
    fieldArray[8].fieldValue="123456789012.98";
    
    
    fieldArray[9].fieldPath="form[PERCENTAJE_REAL]";      
    fieldArray[9].fieldFindType=FieldKeyType.ID;        
    fieldArray[9].fieldType=FieldType.TEXTBOX;          
    fieldArray[9].fieldValue="123";         
                                                        
    fieldArray[10].fieldPath="form[PERCENTAJE_INTEGER]";   
    fieldArray[10].fieldFindType=FieldKeyType.ID;        
    fieldArray[10].fieldType=FieldType.TEXTBOX;          
    fieldArray[10].fieldValue="123.01";      


		fieldArray[11].fieldPath="form[SUBMIT]";
		fieldArray[11].fieldFindType=FieldKeyType.ID;
		fieldArray[11].fieldType=FieldType.BUTTON;
		fieldArray[11].fieldValue="";
		
    FormFiller.formFillElements(browserInstance, fieldArray);
    pages.AssignTask().pressContinueButton();
    
    //pages.Home().gotoInbox();
    Assert.assertTrue("The case does not exist in inbox", pages.Home().gotoInbox().existCase(casenumber));
    pages.Home().gotoInbox().openCase(casenumber);
    pages.DynaformExecution().intoDynaform();
    FormFieldData[] fieldArray1=new FormFieldData[1];
    fieldArray1[0]=new FormFieldData();
		
    fieldArray1[0].fieldPath="form[SUBMIT]";
    fieldArray1[0].fieldFindType=FieldKeyType.ID;
    fieldArray1[0].fieldType=FieldType.BUTTON;
    fieldArray1[0].fieldValue="";
		
		String fieldTEXT_ANY = pages.DynaformExecution().getFieldProperty("TEXT_ANY","value");
		String fieldTEXT_ALPHABETIC = pages.DynaformExecution().getFieldProperty("TEXT_ALPHABETIC","value");
		String fieldTEXT_ALPHANUMERIC = pages.DynaformExecution().getFieldProperty("TEXT_ALPHANUMERIC","value");
		String fieldTEXT_INTEGER = pages.DynaformExecution().getFieldProperty("TEXT_INTEGER","value");
		String fieldTEXT_REAL = pages.DynaformExecution().getFieldProperty("TEXT_REAL","value");
		String fieldTEXT_EMAIL = pages.DynaformExecution().getFieldProperty("TEXT_EMAIL","value");
		String fieldTEXT_LOGIN = pages.DynaformExecution().getFieldProperty("TEXT_LOGIN","value");
		
		String fieldCURRENCY_REAL = pages.DynaformExecution().getFieldProperty("CURRENCY_REAL","value");
		String fieldCURRENCY_INTEGER = pages.DynaformExecution().getFieldProperty("CURRENCY_INTEGER","value");
		
		String fieldPERCENTAJE_REAL = pages.DynaformExecution().getFieldProperty("PERCENTAJE_REAL","value");
		String fieldPERCENTAJE_INTEGER = pages.DynaformExecution().getFieldProperty("PERCENTAJE_INTEGER","value");
		
	  FormFiller.formFillElements(browserInstance, fieldArray1);
    
    Assert.assertEquals("Validate Text Any does not exist", "validation89()?¿'/&%$@|€", fieldTEXT_ANY);
    Assert.assertEquals("Validate Text Alphabetic does not exist", "validationAABB", fieldTEXT_ALPHABETIC);
    Assert.assertEquals("Validate Text Alphanumeric does not exist", "12validation12302", fieldTEXT_ALPHANUMERIC);
    Assert.assertEquals("Validate Text Integer does not exist", "123456789", fieldTEXT_INTEGER);
    Assert.assertEquals("Validate Text Real does not exist", "123.00", fieldTEXT_REAL);
    Assert.assertEquals("Validate Text Email does not exist", "email@colosa.com", fieldTEXT_EMAIL);
    Assert.assertEquals("Validate Text Login does not exist", "adminadmin@#", fieldTEXT_LOGIN);
    
    Assert.assertEquals("Validate Currency Real does not exist", "123,456,789,012.00 $us", fieldCURRENCY_REAL);
    Assert.assertEquals("Validate Currency Integer does not exist", "123,456,789,012 $us", fieldCURRENCY_INTEGER);
    
    Assert.assertEquals("Validate Percentaje Real not exist", "123.00 %", fieldPERCENTAJE_REAL);
    Assert.assertEquals("Validate Percentaje Integer does not exist", "123 %", fieldPERCENTAJE_INTEGER);
    
    pages.AssignTask().pressContinueButton();
    pages.InputDocProcess().switchToDefault();
    pages.Main().logout();
    }

    @After
    public void cleanup(){
        browserInstance.quit();
    }
}

