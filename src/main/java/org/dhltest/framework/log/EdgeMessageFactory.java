package org.dhltest.framework.log;

/**
 * Makes possible to add Edge prefix at the beginning of Log4j logger message
 */
public class EdgeMessageFactory extends AppMessageFactory {
    public EdgeMessageFactory() {
        super("Edge");
    }
}
