package org.dhltest.framework.log;

/**
 * Makes possible to add Firefox prefix at the beginning of Log4j logger message
 */
public class FirefoxMessageFactory extends AppMessageFactory {
    public FirefoxMessageFactory() {
        super("Firefox");
    }
}
