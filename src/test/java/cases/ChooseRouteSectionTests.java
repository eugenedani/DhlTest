package cases;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import org.dhltest.framework.tests.ParallelizedTestCase;
import org.dhltest.framework.web.driver.WebDriverProperties;
import org.dhltest.models.pages.TestedPage;
import org.dhltest.models.pages.sections.ChooseRouteSection;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class ChooseRouteSectionTests extends ParallelizedTestCase {

    public ChooseRouteSectionTests(WebDriverProperties webDriverProperties, String name) {
        super(webDriverProperties, name);
    }

    @Test
    public void verifyElementsTest() {
        ChooseRouteSection section = TestedPage.load(webBrowser).chooseRouteSection().scrollIntoView();
        assertThat("Origin Postcode textbox is not enabled", section.originPostcode().isEnabled());
        assertThat("Destination Postcode textbox is not enabled", section.destinationPostcode().isEnabled());
        assertThat("Origin Country dropdown list is not enabled", section.originPostcode().isEnabled());
        assertThat("Destination Country dropdown list is not enabled", section.originPostcode().isEnabled());
        assertThat("Calculate Button is not enabled", section.calculateButton().isEnabled());
    }

    @Test
    public void verifyErrorMessagesTest() {
        ChooseRouteSection section = TestedPage.load(webBrowser).chooseRouteSection().scrollIntoView();
        section.calculateButton().click();
        assertThat("Origin Postcode error message is not correct", section.originPostcodeError(), is("Correct postal code (e.g. no post box)*"));
        assertThat("Destination Postcode error message is not correct", section.destinationPostcodeError(), is("Correct postal code (e.g. no post box)*"));
    }

    @Test
    public void verifyOriginDropdownListIsNotSwedenTest() {
        ChooseRouteSection section = TestedPage.load(webBrowser).chooseRouteSection().scrollIntoView();
        section.originCountry().select().selectByValue("CZ");
        List<WebElement> destinations = section.destinationCountry().select().getOptions();
        assertThat("Destination country dropdown list should have 1 option", destinations.size(), equalTo(1));
        assertThat("Destination country dropdown list option value is wrong", destinations.get(0).getText(), is("Sweden"));
    }

    @Test
    public void verifyDropdownListsTest() {
        ChooseRouteSection section = TestedPage.load(webBrowser).chooseRouteSection().scrollIntoView();
        section.originCountry().select().selectByValue("SE");
        int originSize = section.destinationCountry().select().getOptions().size();
        int destinationSize = section.destinationCountry().select().getOptions().size();
        assertThat("Country dropdown lists do not have the same amount of options", destinationSize, equalTo(originSize));
    }
}