package org.dhltest.framework;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.dhltest.framework.log.AppLogManager;
import org.dhltest.framework.util.StringUtils;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.MultipleFailureException;
import org.junit.runners.model.Statement;

public abstract class RetryTestRule implements TestRule {
    private final int testAttempts;
    private final Logger logger = AppLogManager.getLogger(RetryTestRule.class.getName());
    private String testName = "";

    RetryTestRule(TestAttempts testAttempts) {
        this.testAttempts = testAttempts.value();
    }

    // Suppress InteliJ Idea warnings
    @SuppressWarnings("NullableProblems")
    @Override
    public Statement apply(final Statement base, final Description description) {
        String fullTestName = fullTestName(description);
        testName = shortTestName(description);

        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                List<Throwable> errors = new ArrayList<>();
                for (int i = 0; i < testAttempts; i++) {
                    logger.info("Start attempt #{} of {} attempts for {}", i + 1, testAttempts, fullTestName);
                    errors = executeTest(base, description);

                    if (errors.isEmpty() || skipped(errors)) {
                        logger.info("Attempt #{} for {} was finished without errors.", i + 1, fullTestName);
                        break;
                    }

                    logAttemptError(errors, fullTestName, i + 1);
                }

                MultipleFailureException.assertEmpty(errors);
            }
        };
    }

    public String shortTestName() {
        return testName;
    }

    protected String fullTestName(Description description) {
        return description.getTestClass().getName() + "#" + description.getMethodName();
    }

    protected String shortTestName(Description description) {
        return StringUtils.methodNameFrom(description);
    }

    private boolean skipped(List<Throwable> errors) {
        return errors.size() == 1 && errors.get(0) instanceof org.junit.internal.AssumptionViolatedException;
    }

    private void logAttemptError(List<Throwable> errors, String fullTestName, int attemptNumber) {
        if (errors.isEmpty()) {
            return;
        }

        if (attemptNumber == testAttempts) {
            return;
        }

        String warning = "Attempt #" + attemptNumber + " for " + fullTestName + " was finished with error ";
        logger.warn(warning, errors.get(0));
    }

    private List<Throwable> executeTest(final Statement base, final Description description) {
        List<Throwable> errors = new ArrayList<>();
        startingQuietly(errors);

        try {
            base.evaluate();
        } catch (org.junit.internal.AssumptionViolatedException e) {
            //test skipping
            errors.add(e);
        } catch (Throwable e) {
            errors.add(e);
            failedQuietly(e, description, errors);
        } finally {
            finishedQuietly(description, errors);
        }

        return errors;
    }

    private void failedQuietly(Throwable e, Description description, List<Throwable> errors) {
        try {
            failed(e, description);
        } catch (Throwable e1) {
            errors.add(e1);
        }
    }

    private void startingQuietly(List<Throwable> errors) {
        try {
            starting();
        } catch (Throwable e) {
            errors.add(e);
        }
    }

    private void finishedQuietly(Description description, List<Throwable> errors) {
        try {
            finished(description);
        } catch (Throwable e) {
            errors.add(e);
        }
    }

    /**
     * Invoked when a test fails
     */
    abstract void failed(Throwable e, Description description);

    /**
     * Invoked when a test is about to start
     */
    abstract void starting();

    /**
     * Invoked when a test method finishes (whether passing or failing)
     */
    abstract void finished(Description description);
}
