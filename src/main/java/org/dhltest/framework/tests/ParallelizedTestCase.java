package org.dhltest.framework.tests;

import org.junit.runner.RunWith;

import org.dhltest.framework.Parallelized;
import org.dhltest.framework.web.driver.WebDriverProperties;

/**
 * This class is used when tests should be run parallel
 */
@RunWith(Parallelized.class)
public class ParallelizedTestCase extends BaseTestCase {
    public ParallelizedTestCase(WebDriverProperties webDriverProperties, String name) {
        super(webDriverProperties, name);
    }
}
