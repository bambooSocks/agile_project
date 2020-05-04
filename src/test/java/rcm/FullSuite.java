package rcm;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import rcm.cucumber.CucumberTest;
import rcm.persistency.TestDatabaseAccess;
import rcm.unitTest.*;

@RunWith(Suite.class)
@SuiteClasses({
    // Acceptance tests
    CucumberTest.class,
    // Unit tests
    JourneyTest.class,
    ApplicationTest.class,
    ClientTest.class,
    LogisticsCompanyTest.class,
    ContainerStatusTest.class,
    TestDatabaseAccess.class
})
public class FullSuite { }
