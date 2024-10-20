package org.dhltest.models.pages.actions;

import org.dhltest.models.pages.sections.ChooseRouteSection;

public class ChooseActions {
    private final ChooseRouteSection section;

    public ChooseActions(ChooseRouteSection section) {
        this.section = section;
    }

    public ChooseActions selectOriginCountry(String name) {
        section.originCountry().select().selectByValue(name);
        return this;
    }

    public ChooseActions selectDestinationCountry(String name) {
        section.destinationCountry().select().selectByValue(name);
        return this;
    }

    public ChooseActions setOriginPostcode(String name) {
        section.originPostcode().fill(name);
        return this;
    }

    public ChooseActions setDestinationPostcode(String name) {
        section.destinationPostcode().fill(name);
        return this;
    }
}
