package suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import cases.ChooseRouteCalculationTests;
import cases.ChooseRouteSectionTests;

/**
 * Suite for Test Cases scripts
 */
@RunWith(Suite.class)
//@formatter:off
    @Suite.SuiteClasses({
            ChooseRouteSectionTests.class,
            ChooseRouteCalculationTests.class,
    })
//@formatter:on
public class CalculationTests {
    // the class remains empty,
    // used only as a holder for the above annotations
}
