package com.colosa.qa.automatization;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/*com.colosa.qa.automatization.tests.PMFunctions.TestFunctionExecuteQueryMysqlOracle.class, //error
com.colosa.qa.automatization.tests.PMFunctions.TestPMDateFunctions.class //errores de assert
,
,
,
com.colosa.qa.automatization.tests.PMFunctions.TestPMFAddInputDocument.class, //error datos quemados en trigger
com.colosa.qa.automatization.tests.PMFunctions.TestPMFAssignUserToGroup.class, //falla por que el usuario ya fue asignado al grupo y no limpia la asignación al terminar
com.colosa.qa.automatization.tests.PMFunctions.TestPMFCancelCase.class, //falta assert no se esta validando el cancel de caso

com.colosa.qa.automatization.tests.PMFunctions.TestPMFCreateUsers.class //revisar no limpia el usuario creado, mejorar com.colosa.qa.automatization.tests.PMFunctions.TestPMFCreateUsers.class //revisar no limpia el usuario creado, mejorar pages.Admin().userExists("felipe"));
,
,
,

,
,
,
com.colosa.qa.automatization.tests.PMFunctions.TestPMFGetUserEmailAddress.class //falta validación, no hay asserts
com.colosa.qa.automatization.tests.PMFunctions.TestPMFGroupList.class //no verifica los datos falta asserts
,
,

,
com.colosa.qa.automatization.tests.PMFunctions.TestPMFJumping.class //averiguar como funciona

,
com.colosa.qa.automatization.tests.PMFunctions.TestPMFNewCaseTest.class // revisar no hay proceso
com.colosa.qa.automatization.tests.PMFunctions.TestPMFNewCaseImpersonate.class //revisar

,
,
,
,
com.colosa.qa.automatization.tests.PMFunctions.TestPMFRoleList.class //revisar mejorar pages.Admin().countRoles();
com.colosa.qa.automatization.tests.PMFunctions.TestPMFSendMessageTest.class //revisar pages.Admin().emailStatus(casenumber);
com.colosa.qa.automatization.tests.PMFunctions.TestPMFTaskList.class, //mejorar el testeo definir la funcionalidad de la función simplificar el mismo.
,
,
,
,
,
,
com.colosa.qa.automatization.tests.PMFunctions.TestSetCaseTrackerCode.class //error de función, revisar funcionalidad
,*/
@RunWith(value = Suite.class)
@SuiteClasses(value = {
        com.colosa.qa.automatization.tests.PMFunctions.TestFunctionPMFDerivateCase.class,
        com.colosa.qa.automatization.tests.PMFunctions.TestFunctionUserInfo.class,
        com.colosa.qa.automatization.tests.PMFunctions.TestPMFAddCaseNote.class/*,
        com.colosa.qa.automatization.tests.PMFunctions.TestPMFCasesList.class, //revisar
        com.colosa.qa.automatization.tests.PMFunctions.TestPMFDeleteCase.class, //revisar, demasiado lento busca en drafts, buscar en participated caso borrado??
        com.colosa.qa.automatization.tests.PMFunctions.TestPMFGetCaseNotes.class,
        com.colosa.qa.automatization.tests.PMFunctions.TestPMFGetEmailConfiguration.class,
        com.colosa.qa.automatization.tests.PMFunctions.TestPMFGetLabel.class,
        com.colosa.qa.automatization.tests.PMFunctions.TestPMFGetNextAssignedUser.class,
        com.colosa.qa.automatization.tests.PMFunctions.TestPMFInformationUser.class,
        com.colosa.qa.automatization.tests.PMFunctions.TestPMFPauseCase.class,
        com.colosa.qa.automatization.tests.PMFunctions.TestPMFProcessList.class,
        com.colosa.qa.automatization.tests.PMFunctions.TestPMFRedirectToStep.class,
        com.colosa.qa.automatization.tests.PMFunctions.TestPMFSendVariables.class, //revisar
        com.colosa.qa.automatization.tests.PMFunctions.TestPMFTaskCase.class,
        com.colosa.qa.automatization.tests.PMFunctions.TestPMFUnpauseCase.class, //revisar, quitar busqueda en paused
        com.colosa.qa.automatization.tests.PMFunctions.TestPMFUpdateUser.class,
        com.colosa.qa.automatization.tests.PMFunctions.TestPMFUserList.class*/

                         })
public class PMFunctions {
    @AfterClass public static void tearDownClass() {
        //Browser.quit();
    }

}
