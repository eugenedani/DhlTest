package org.dhltest.framework.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import org.dhltest.framework.web.driver.WebDriverProperties;

/**
 * This class is used to run tests one by one
 */
@RunWith(Parameterized.class)
public class ParameterizedTestCase extends BaseTestCase {
    public ParameterizedTestCase(WebDriverProperties webDriverProperties, String name) {
        super(webDriverProperties, name);
    }
}
