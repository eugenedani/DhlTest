package org.dhltest.framework.web.pages.load;

import org.dhltest.framework.wait.PageWaitTime;
import org.dhltest.framework.web.pages.WebPage;
import org.openqa.selenium.NotFoundException;

/**
 * Handles Verify Annotation of class to get values for page loading settings
 */
class VerifyAnnotation {
    private final WebPage page;
    private final PageWaitTime pageWaitTime;

    VerifyAnnotation(WebPage page, PageWaitTime pageWaitTime) {
        this.page = page;
        this.pageWaitTime = pageWaitTime;
    }

    PageIsLoadedController buildPageIsLoadedController() {
        assertValidAnnotation();

        Verify verify = page.getClass().getAnnotation(Verify.class);
        PageIsLoadedController controller = new PageIsLoadedController(page, pageWaitTime == PageWaitTime.DEFAULT_VALUE ? verify.waitTime() : pageWaitTime);
        controller.element(new FindByAnnotation(page.getClass()).buildBy());

        if (verify.status()) {
            controller.completeStatus();
        }

        return controller;
    }

    private void assertValidAnnotation() {
        if (page.getClass().getAnnotation(Verify.class) == null) {
            throw new NotFoundException(page.getClass().getName() + " does not have '@Verify' annotation, you must use a '@Verify' annotation for web pages classes");
        }
    }
}
