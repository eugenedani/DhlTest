# Description

## Environment

IntelliJ IDEA 2024.2.3 (Community Edition)<br>
Java 17<br>
Selenium 4.25.0<br>
JUnit 4<br>
Gradle<br>
Windows 10

## Tests running

<b>UITest.config</b> configuration file has settings for tests running
It is possible to set up which browsers will be used (Chrome, Firefox or Edge). Browsers can be run at the same time or it is possible to set two browsers or just one needed browser.
Browsers can be run with headless mode.<br>
File is in "src/test/workdir" folder

Errors Screenshots can be found in "src/test/workdir/ErrorsScreenshots" folder.

Tests results can be found in "test-results" folder

All paths are from project root.

## Project

Project has 3 levels

1. framework package has supporting classes to make possible to run tests
2. models package has web page and web page sections classes
3. test has JUnut tests and suite
