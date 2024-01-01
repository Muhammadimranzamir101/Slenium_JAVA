package com.base;

import com.enums.Browsers;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import static com.drivers.DriverManager.createDriver;
import static com.drivers.DriverManager.quitDriver;


public class BaseSuiteSetup {

    @Parameters ("browser")
    @BeforeClass (alwaysRun = true)
    public void setupTest (final String browser) {
        createDriver (Browsers.valueOf (browser.toUpperCase ()));
    }

    @AfterClass (alwaysRun = true)
    public void tearDown () {
        quitDriver ();
    }
}