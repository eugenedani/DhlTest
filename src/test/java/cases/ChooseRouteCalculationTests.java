package cases;

import static org.hamcrest.MatcherAssert.assertThat;

import org.dhltest.framework.tests.ParallelizedTestCase;
import org.dhltest.framework.wait.PageWaitTime;
import org.dhltest.framework.web.driver.WebDriverProperties;
import org.dhltest.models.pages.TestedPage;
import org.dhltest.models.pages.sections.ChooseRouteSection;
import org.dhltest.models.pages.sections.TransitTimeResultSection;
import org.junit.Test;

public class ChooseRouteCalculationTests extends ParallelizedTestCase {

    public ChooseRouteCalculationTests(WebDriverProperties webDriverProperties, String name) {
        super(webDriverProperties, name);
    }

    @Test
    public void verifyResultOpenTest() {
        ChooseRouteSection section = TestedPage.load(webBrowser).chooseRouteSection().scrollIntoView();
        section.originCountry().select().selectByValue("CZ");
        section.originPostcode().fill("19900");
        section.destinationCountry().select().selectByValue("SE");
        section.destinationPostcode().fill("26234");
        TransitTimeResultSection resultSection = section.calculateButton().click(() -> new TransitTimeResultSection(webBrowser, PageWaitTime.HEAVY_PANEL));
        assertThat("Transit Time Result Section was not loaded", resultSection.isLoaded());
    }

    @Test
    public void verifyResultOpen2Test() {
        ChooseRouteSection section = TestedPage.load(webBrowser).chooseRouteSection().scrollIntoView();
        section.originCountry().select().selectByValue("SE");
        section.originPostcode().fill("26234");
        section.destinationCountry().select().selectByValue("CZ");
        section.destinationPostcode().fill("19900");
        TransitTimeResultSection resultSection = section.calculateButton().click(() -> new TransitTimeResultSection(webBrowser, PageWaitTime.HEAVY_PANEL));
        assertThat("Transit Time Result Section was not loaded", resultSection.isLoaded());
    }
}
