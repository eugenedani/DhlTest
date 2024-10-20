package org.dhltest.framework.log;

/**
 * Makes possible to add Chrome prefix at the beginning of Log4j logger message
 */
public class ChromeMessageFactory extends AppMessageFactory {
    public ChromeMessageFactory() {
        super("Chrome");
    }
}
