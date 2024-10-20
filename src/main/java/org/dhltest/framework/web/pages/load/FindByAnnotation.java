package org.dhltest.framework.web.pages.load;

import java.lang.reflect.Field;
import java.util.Arrays;

import org.dhltest.framework.web.pages.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.support.AbstractFindByBuilder;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.pagefactory.AbstractAnnotations;
import org.openqa.selenium.support.pagefactory.Annotations;

/**
 * Handles FindBy Annotation of class to get instance of {@link By}
 */
public class FindByAnnotation extends AbstractAnnotations {
    private final Class<? extends Page> clazz;

    FindByAnnotation(Class<? extends Page> clazz) {
        this.clazz = clazz;
    }

    public boolean isLookupCached() {
        return this.clazz.getAnnotation(CacheLookup.class) != null;
    }

    /**
     * Creates element selector based on {@link FindBy} annotation
     *
     * @return instance of {@link By} with Element selector
     */

    public By buildBy() {
        if (this.clazz.isAnnotationPresent(FindBy.class)) {
            FindBy findBy = this.clazz.getAnnotation(FindBy.class);
            if (findBy != null) {
                return new FindByBuilder().buildIt(findBy, null);
            }
        } else {
            Field field = getFieldForVerification();
            if (field != null) {
                return new Annotations(field).buildBy();
            }
        }

        throw new IllegalArgumentException("Cannot determine how to locate element which should be used to check if " + this.clazz.getName() +
                " was loaded. Please add @FindBy annotation for the class or add @UseForVerify annotation to one of the class field with @FindBy, @FindBys or @FindAll annotation");
    }

    /**
     * Search for class field with @UseForVerify annotation to find a field with @FindBy, @FindBys or @FindAll annotation that will be used for checking of Web Page loading
     *
     * @return field with @UseForVerify annotation
     */
    private Field getFieldForVerification() {
        final Field[] fields = clazz.getDeclaredFields();
        return Arrays.stream(fields).filter(field -> field.isAnnotationPresent(UseForVerify.class)).findFirst().orElse(null);
    }

    private static class FindByBuilder extends AbstractFindByBuilder {
        @Override
        public By buildIt(Object annotation, Field field) {
            return this.buildByFromFindBy((FindBy) annotation);
        }
    }
}
