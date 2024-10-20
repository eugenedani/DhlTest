package org.dhltest.framework.web.pages.load;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.dhltest.framework.wait.PageWaitTime;

/**
 * Describes parameters what will be checked for during a page loading
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Verify {

    PageWaitTime waitTime() default PageWaitTime.PAGE;

    boolean status() default true;
}
