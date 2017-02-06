package nut.cc.steps;

import nut.cc.page.Browsers;
import nut.cc.page.Page;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebElement;

import static junit.framework.TestCase.assertTrue;

public class SearchResultTest {

    private Page page;

    @Before
    public void setUp() {
       page = new Page(Browsers.CHROME);
    }

    @Given("^Open page by url \"(.*)\" in browser$")
    public void Open_Page_By_url(String url) {
        page.open(url);
    }

    @Given("^Type to search input \"(.*)\"")
    public void search_for(String query) {
        page.sendQuery(query);
    }

    @When("^Press button search$")
    public void Press_Button_Search() {
        page.pressSearchButton();
    }

    @Then("^Header search snippet \"(\\d*)\" text start with \"(.*)\"$")
    public void Header_Search_Text_Equals(String snippet, String header) {
        WebElement current = page.getSearchResult().get(Integer.parseInt(snippet) - 1);

        assertTrue(current.getText().startsWith(header));
    }

    @After
    public void tearDown() {
       page.quit();
    }
}