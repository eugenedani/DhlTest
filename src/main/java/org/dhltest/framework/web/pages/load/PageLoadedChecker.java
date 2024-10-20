package org.dhltest.framework.web.pages.load;

import org.dhltest.framework.wait.PageWaitTime;
import org.dhltest.framework.web.pages.WebPage;

/**
 * Check if a web page was loaded
 */
public class PageLoadedChecker {
    private PageLoadedChecker() {
    }

    /**
     * Checks if page is loaded using default waiting time from {@link Verify} annotation
     *
     * @param page expected page to be loaded
     * @return true if page was loaded, otherwise - false
     */
    public static <T extends WebPage> boolean assume(T page) {
        return assume(page, PageWaitTime.DEFAULT_VALUE);
    }

    /**
     * Checks if page is loaded
     *
     * @param page expected page to be loaded
     * @param pageWaitTime maximum value of waiting time, if it is null the default page time will be used
     * @return true if page was loaded, otherwise - false
     */
    public static <T extends WebPage> boolean assume(T page, PageWaitTime pageWaitTime) {
        PageIsLoadedController controller = new VerifyAnnotation(page, pageWaitTime).buildPageIsLoadedController();
        return controller.waitIsLoaded();
    }

    /**
     * Checks if page is loaded.
     * If page will not be loaded throws exception
     *
     * @param page expected page to be loaded
     */
    public static <T extends WebPage> void createInstance(T page) {
        assume(page);
        page.checkIfLoaded();
    }
}
