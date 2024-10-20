package org.dhltest.models.pages.sections;

import org.dhltest.framework.elements.Button;
import org.dhltest.framework.elements.DropdownList;
import org.dhltest.framework.elements.Textbox;
import org.dhltest.framework.web.driver.WebBrowser;
import org.dhltest.framework.web.pages.WebPageSection;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class ChooseRouteSection extends WebPageSection {
    @FindBy(id = "origin-country")
    private WebElement originCountrySelect;
    @FindBy(id = "destination-country")
    private WebElement destinationCountrySelect;
    @FindBy(id = "origin-postcode")
    private WebElement originPostcodeTextbox;
    @FindBy(id = "destination-postcode")
    private WebElement destinationPostboxTextbox;
    @FindBy(css = "button.js--freightcalc-se--input-submit")
    private WebElement calculateButton;
    @FindBy(css = "p.js--origin-zip-error")
    private WebElement originZipErrorElement;
    @FindBy(css = "p.js--destination-zip-error")
    private WebElement destinationZipErrorElement;

    public ChooseRouteSection(WebBrowser webBrowser) {
        super(webBrowser);
    }

    public ChooseRouteSection scrollIntoView(){
        webBrowser.executeScript("arguments[0].scrollIntoView();", originCountrySelect);
        return this;
    }

    public DropdownList originCountry(){
        return new DropdownList(originCountrySelect);
    }

    public DropdownList destinationCountry(){
        return new DropdownList(destinationCountrySelect);
    }

    public Textbox originPostcode(){
        return new Textbox(originPostcodeTextbox);
    }

    public Textbox destinationPostcode(){
        return new Textbox(destinationPostboxTextbox);
    }

    public Button calculateButton(){
        return new Button(calculateButton);
    }

    public String originPostcodeError(){
        return originZipErrorElement.getText().trim();
    }

    public String destinationPostcodeError(){
        return destinationZipErrorElement.getText().trim();
    }

}
