package com.mycompany.myapp.selenium;

import com.mycompany.myapp.FauxshopApp;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.rules.TestName;
import org.springframework.test.context.web.WebAppConfiguration;

// This port will close after integration tests are completed.
// Note that we run our tests in order by name ascending.
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes={FauxshopApp.class}, webEnvironment= SpringBootTest.WebEnvironment.DEFINED_PORT)
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Ignore
public class HomeIntTest extends TestBaseSetup {

    private final Logger log = LoggerFactory.getLogger(HomeIntTest.class);

    // This allows us to print the test name before and after it runs
    @Rule
    public TestName name = new TestName();

    HomePageObject homePageObject = new HomePageObject(getDriver());
    LoginPageObject loginPageObject = new LoginPageObject(getDriver());

    // Code included in the @Before and @After annotations is executed before and after each test
    @Before
    public void individualTestSetup() {
        // This writes every test name before it runs
        log.info("Running test " + name.getMethodName());
    }

    @After
    // This writes every test name after it completes
    public void logAfterTestsComplete() {
        log.info("Completed test " + name.getMethodName());
    }

    // This test confirms our header text is "Welcome, Java Hipster!"
    @Test
    public void verifiesHomePageHasHeader() throws Exception {
        // Our browser is directed to our homepage before each test:
        openUrl(getDriver(), "/");

        // We use our pageObjects class to retrieve the WebElement with id="mainHeader"
        // We assert that the text of the element with id="mainHeader" is "Welcome, Java Hipster!"
        Assert.assertTrue("Homepage header does not match", homePageObject.verifyMainHeader());
    }

    // This test confirms that we can navigate to the login page through a link on our homepage:
    @Test
    public void clickSignIn() {
        // Our browser is directed to our homepage before each test:
        openUrl(getDriver(), "/");

        // We click on the element with id="loginId":
        homePageObject.homeLoginId(getDriver()).click();

        // We use our pageObjects class to retrieve the WebElement with id="loginTitle" using our LoginPageObject:
        // We assert that the element with id="loginTitle" has the text "Sign in":
        Assert.assertTrue("Login title does not match", loginPageObject.verifyLoginTitle());
    }
}
