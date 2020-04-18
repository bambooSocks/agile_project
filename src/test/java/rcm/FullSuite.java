package rcm;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import rcm.cucumber.CucumberTest;
import rcm.unitTest.*;

@RunWith(Suite.class)
@SuiteClasses({
    // Acceptance tests
    CucumberTest.class,
    // Unit tests
    JourneyTest.class
})
public class FullSuite { }
