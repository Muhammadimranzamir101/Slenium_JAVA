package com.drivers;
import java.time.Duration;

import io.github.bonigarcia.wdm.WebDriverManager;
import com.enums.Browsers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public final class DriverManager {
    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<> ();
    private static final Logger LOG = LogManager.getLogger ("DriverManager.class");
    private static final String NO_SANDBOX = "--no-sandbox";
    private static final String DISABLE_NOTIFICATION = "--disable-notifications";
    private static final String REMOTE_ALLOW_ORIGINS = "--remote-allow-origins=*";


    public static void createDriver (final Browsers browser) {
        switch (browser) {
            case FIREFOX -> setupFirefoxDriver ();
            case EDGE -> setupEdgeDriver ();
            case SAFARI -> setupSafariDriver ();
            case CHROME -> setupChromeDriver ();
        }
        setupBrowserTimeouts ();
    }

    public static WebDriver getDriver () {
        return DriverManager.DRIVER.get ();
    }

    public static void quitDriver () {
        if (null != DRIVER.get ()) {
            LOG.info ("Closing the driver...");
            getDriver ().quit ();
            DRIVER.remove ();
        }
    }

    private static void setDriver (final WebDriver driver) {
        DriverManager.DRIVER.set (driver);
    }

    private static void setupBrowserTimeouts () {
        LOG.info ("Setting Browser Timeouts....");
        getDriver ().manage ()
                .timeouts ()
                .implicitlyWait (Duration.ofSeconds (20));
        getDriver ().manage ()
                .timeouts ()
                .pageLoadTimeout (Duration.ofSeconds (20));
        getDriver ().manage ()
                .timeouts ()
                .scriptTimeout (Duration.ofSeconds (20));
    }

    private static void setupChromeDriver () {
        LOG.info ("Setting up Chrome Driver....");

        final var options = new ChromeOptions ();
        options.addArguments (NO_SANDBOX);
        options.addArguments(DISABLE_NOTIFICATION);
        options.addArguments(REMOTE_ALLOW_ORIGINS);

        setDriver (WebDriverManager.chromedriver ()
                .capabilities (options)
                .create ());
        LOG.info ("Chrome Driver created successfully!");
    }

    private static void setupEdgeDriver () {
        LOG.info ("Setting up Edge Driver....");
        setDriver (WebDriverManager.edgedriver ()
                .create ());
        LOG.info ("Edge Driver created successfully!");
    }

    private static void setupFirefoxDriver () {
        LOG.info ("Setting up Firefox Driver....");
        final var options = new FirefoxOptions ();
        options.addArguments (NO_SANDBOX);
        options.addArguments(DISABLE_NOTIFICATION);
        options.addArguments(REMOTE_ALLOW_ORIGINS);
        setDriver (WebDriverManager.firefoxdriver ()
                .capabilities (options)
                .create ());
        LOG.info ("Firefox Driver created successfully!");
    }
    private static void setupSafariDriver () {
        LOG.info ("Setting up Opera Driver....");
        setDriver (WebDriverManager.safaridriver ()
                .create ());
        LOG.info ("Opera Driver created successfully!");

    }
    private DriverManager () {
    }
}