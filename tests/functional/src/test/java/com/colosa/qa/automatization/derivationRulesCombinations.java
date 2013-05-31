package com.colosa.qa.automatization;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(value = Suite.class)
@SuiteClasses(value = { com.colosa.qa.automatization.tests.derivationRulesCombinations.DerivationRulesEvaluation.class,
                        com.colosa.qa.automatization.tests.derivationRulesCombinations.DerivationRulesParallel.class,
                        com.colosa.qa.automatization.tests.derivationRulesCombinations.DerivationRulesParallelEvaluation.class,
                        com.colosa.qa.automatization.tests.derivationRulesCombinations.DerivationRulesSelection.class,
                        com.colosa.qa.automatization.tests.derivationRulesCombinations.DerivationRulesSequential.class})
public class derivationRulesCombinations {
    @AfterClass public static void tearDownClass() {
        //Browser.quit();
    }

}
