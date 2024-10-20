package org.dhltest.framework.web.pages;

/**
 * Methods for checking if page was loaded
 */
public interface PageLoadedCheck {
    /**
     * @return true if page was loaded
     */
    boolean isLoaded();

    /**
     * Checks if page was loaded
     * If page was not loaded throw an Exception, for example {@link PageWasNotLoadedException}
     * or does something else
     */
    default void checkIfLoaded() {
        if (isLoaded()) {
            return;
        }

        throw new PageWasNotLoadedException(this.getClass().getSimpleName() + " was not loaded");
    }
}
