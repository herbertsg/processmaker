package com.colosa.qa.automatization.tests.processPermissions;

import com.colosa.qa.automatization.pages.DynaformExecution;
import com.colosa.qa.automatization.common.ConfigurationSettings;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.junit.After;

import java.io.IOException;

public class TestPermissions extends com.colosa.qa.automatization.tests.common.Test{

    public TestPermissions(String browserName) throws IOException {
        super(browserName);
    }

    @Test
    public void runCase() throws Exception {

        // login the PM
        pages.gotoDefaultUrl();
        pages.Login().loginUser("admin","admin","workflow", "English");

        pages.Main().goDesigner();
        pages.ProcessList().openProcess("Process Permissions");
        pages.Designer().deleteAllPermission();

        pages.Main().goHome();

        // create case the process and task : Testeo de funciones PMFNewCase (Creador de casos)
        int numberNewCase = pages.Home().gotoNewCase().startCase("Process Permissions (Task 1)");

        // get button submit
	DynaformExecution form = pages.DynaformExecution();
        form.intoDynaform();
        form.clickButton("Submit");

        String pathFile = ConfigurationSettings.getInstance().getSetting("permissions.file.upload");
	form.intoDynaform();
        form.setFieldValue("MNU_NEW", "click");
        form.setFieldValue("APP_DOC_FILENAME", pathFile);
        form.setFieldValue("SAVE", "click");
        form.setFieldValue("BTN_SUBMIT", "click");
        form.setFieldValue("NEXT_STEP", "click");

        // get button continue
        WebElement buttonContinueSubmit = form.getObject("//*[@id='btnContinue']");

        // click to button continue
        buttonContinueSubmit.click();

        /////////
        
        
        // login the PM
        pages.gotoDefaultUrl();
        pages.Login().loginUser("cochalo","sample","workflow", "English");
        
        pages.Main().goHome();
        pages.Home().gotoParticipated();
        pages.Home().gotoInbox().openCase(numberNewCase);

        Boolean existDynaforms = form.openInformationDynaforms();

        // verify if the field CELULAR is validate
        Assert.assertEquals("The Permissions in dynaforms not work :'(", false, existDynaforms);
        // case was create with field CELULAR correctly
        
        Boolean existUploaded = form.openInformationUploaded();

        // verify if the field CELULAR is validate
        Assert.assertEquals("The Permissions in inputs not work :'(", false, existUploaded);
        // case was create with field CELULAR correctly
        
        Boolean existGenerated = form.openInformationGenerated();

        // verify if the field CELULAR is validate
        Assert.assertEquals("The Permissions in outputs not work :'(", false, existGenerated);
        // case was create with field CELULAR correctly

        form.openCasesNotes();

        //ojo valida si se abre el formulario de case notes
        Boolean existCaseNote = true;
        // verify if the field CELULAR is validate
        Assert.assertEquals("The Permissions in case notes not work :'(", false, existCaseNote);
        // case was create with field CELULAR correctly
        
        pages.Main().goDesigner();
        pages.ProcessList().openProcess("Process Permissions");
        pages.Designer().assignedPermission("cochalo cochalo (cochalo)", "All");

        pages.Main().goHome();
        pages.Home().gotoParticipated();
        pages.Home().gotoInbox().openCase(numberNewCase);

        existDynaforms = form.openInformationDynaforms();

        // verify if the field CELULAR is validate
        Assert.assertEquals("The Permissions in dynaforms not work :'(", true, existDynaforms);
        // case was create with field CELULAR correctly
        
        existUploaded = form.openInformationUploaded();

        // verify if the field CELULAR is validate
        Assert.assertEquals("The Permissions in inputs not work :'(", true, existUploaded);
        // case was create with field CELULAR correctly
        
        existGenerated = form.openInformationGenerated();

        // verify if the field CELULAR is validate
        Assert.assertEquals("The Permissions in outputs not work :'(", true, existGenerated);
        // case was create with field CELULAR correctly

        form.openCasesNotes();

        // verify if the field CELULAR is validate
        Assert.assertEquals("The Permissions in case notes not work :'(", true, existCaseNote);
        // case was create with field CELULAR correctly
        pages.InputDocProcess().switchToDefault();
        pages.Main().logout();
    }

    @After
    public void cleanup(){
        browserInstance.quit();
    }
}