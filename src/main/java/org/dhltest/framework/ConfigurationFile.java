package org.dhltest.framework;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Optional;

import org.dhltest.framework.web.driver.WebDriverProperties;

/**
 * Class in order to deserialize configuration data from xml file
 */
public class ConfigurationFile {
    private String testedUrl = "";
    private int parallelThreads = 4;
    private WebDriverProperties[] drivers = new WebDriverProperties[] {};
    private int testAttempts = TestAttempts.TWO.value();

    public static void serializeToXmlFile(ConfigurationFile configurationFile, String fileName) throws FileNotFoundException {
        XMLEncoder e = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(fileName)));
        e.writeObject(configurationFile);
        e.close();
    }

    /**
     * Deserializes data from XML file
     *
     * @param filePath path to XML file
     * @return deserialized instance of this class
     * @throws FileNotFoundException if file was not found
     */
    public static ConfigurationFile deserializeFromXmlFile(String filePath) throws FileNotFoundException {
        XMLDecoder d = new XMLDecoder(new BufferedInputStream(new FileInputStream(filePath)));
        Object result = d.readObject();
        d.close();
        return (ConfigurationFile) result;
    }

    /**
     * Returns based URL. For example, https://www.google.com/
     *
     * @return based tested URL
     */
    public String getTestedUrl() {
        return testedUrl;
    }

    /**
     * Sets base tested URL. It is for setting data from XML file
     *
     * @param testedUrl base tested URL
     */
    public void setTestedUrl(String testedUrl) {
        this.testedUrl = stringValue(testedUrl);
    }

    /**
     * Returns list of tested web browsers properties
     *
     * @return list of tested web browsers properties
     */
    public WebDriverProperties[] getDrivers() {
        return drivers;
    }

    /**
     * Sets list of used WebDrivers It is for setting data from XML file
     *
     * @param drivers used WebDrivers
     */
    public void setDrivers(WebDriverProperties[] drivers) {
        this.drivers = drivers;
    }

    /**
     * Returns how many parallel threads can be run during testing
     *
     * @return amount of threads
     */
    public int getParallelThreads() {
        return parallelThreads;
    }

    /**
     * Sets amount of threads It is for setting data from XML file
     *
     * @param parallelThreads amount of threads
     */
    public void setParallelThreads(int parallelThreads) {
        this.parallelThreads = parallelThreads;
    }

    public int getTestAttempts() {
        return testAttempts;
    }

    public void setTestAttempts(int testAttempts) {
        this.testAttempts = testAttempts;
    }

    private String stringValue(String string) {
        return Optional.ofNullable(string).orElse("");
    }
}
