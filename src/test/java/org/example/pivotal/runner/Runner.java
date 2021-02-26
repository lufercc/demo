package org.example.pivotal.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        glue = {"org.example"},
        features = "src/test/resources/features",
        plugin = "pretty"
)
public class Runner extends AbstractTestNGCucumberTests {

//    @BeforeTest
//    public void beforeAllScenarios() {
//
//        System.setProperty("dataproviderthreadcount", "5");
//
//        // clean data
//        RequestSpecification requestSpec = RequestSpecFactory.getRequestSpec("pivotal", "owner");
//        Response response = RequestManager.get(requestSpec, "/projects");
//        List<Integer> allProjectIds = response.jsonPath().getList("id");
//        for (Integer id : allProjectIds) {
//            RequestManager.delete(requestSpec, String.format("/projects/%d", id));
//        }
//        // Restore flag by default
//
//        // data re-used in several scenarios
//        // initial data
//    }

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }

    @AfterTest
    public void afterAllScenarios() {
        // clean data
        // Restore flag by default
    }
}
