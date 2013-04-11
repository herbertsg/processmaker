package com.colosa.qa.automatization;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(value = Suite.class)
@SuiteClasses(value = { //com.colosa.qa.automatization.tests.PMFunctions.TestFunctionExecuteQueryMysqlOracle.class,
                        //com.colosa.qa.automatization.tests.PMFunctions.TestFunctionPMFDerivateCase.class,
                        com.colosa.qa.automatization.tests.PMFunctions.TestFunctionUserInfo.class,
                        //com.colosa.qa.automatization.tests.PMFunctions.TestPMDateFunctions.class,
                        com.colosa.qa.automatization.tests.PMFunctions.TestPMFAddCaseNote.class,
                        com.colosa.qa.automatization.tests.PMFunctions.TestPMFAddInputDocument.class,
                        //com.colosa.qa.automatization.tests.PMFunctions.TestPMFAssignUserToGroup.class,
                        com.colosa.qa.automatization.tests.PMFunctions.TestPMFCancelCase.class,
                        com.colosa.qa.automatization.tests.PMFunctions.TestPMFCasesList.class,
                        //com.colosa.qa.automatization.tests.PMFunctions.TestPMFCreateUsers.class,
                        com.colosa.qa.automatization.tests.PMFunctions.TestPMFDeleteCase.class,
                        com.colosa.qa.automatization.tests.PMFunctions.TestPMFGetCaseNotes.class,
                        com.colosa.qa.automatization.tests.PMFunctions.TestPMFGetEmailConfiguration.class,
                        com.colosa.qa.automatization.tests.PMFunctions.TestPMFGetLabel.class,
                        com.colosa.qa.automatization.tests.PMFunctions.TestPMFGetNextAssignedUser.class,
                        com.colosa.qa.automatization.tests.PMFunctions.TestPMFGetUserEmailAddress.class,
                        com.colosa.qa.automatization.tests.PMFunctions.TestPMFGroupList.class,
                        com.colosa.qa.automatization.tests.PMFunctions.TestPMFInformationUser.class,
                        //com.colosa.qa.automatization.tests.PMFunctions.TestPMFJumping.class,
                        //com.colosa.qa.automatization.tests.PMFunctions.TestPMFNewCaseImpersonate.class,
                        com.colosa.qa.automatization.tests.PMFunctions.TestPMFPauseCase.class,
                        com.colosa.qa.automatization.tests.PMFunctions.TestPMFProcessList.class,
                        com.colosa.qa.automatization.tests.PMFunctions.TestPMFRedirectToStep.class,
                        //com.colosa.qa.automatization.tests.PMFunctions.TestPMFRoleList.class,
                        //com.colosa.qa.automatization.tests.PMFunctions.TestPMFSendMessageTest.class,
                        com.colosa.qa.automatization.tests.PMFunctions.TestPMFSendVariables.class,
                        com.colosa.qa.automatization.tests.PMFunctions.TestPMFTaskCase.class,
                        //com.colosa.qa.automatization.tests.PMFunctions.TestPMFTaskList.class,
                        com.colosa.qa.automatization.tests.PMFunctions.TestPMFUnpauseCase.class,
                        com.colosa.qa.automatization.tests.PMFunctions.TestPMFUpdateUser.class,
                        com.colosa.qa.automatization.tests.PMFunctions.TestPMFUserList.class
                        //,com.colosa.qa.automatization.tests.PMFunctions.TestSetCaseTrackerCode.class
                         })
public class PMFunctions {
    @AfterClass public static void tearDownClass() {
        //Browser.quit();
    }

}
