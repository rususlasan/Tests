package nut.cc.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Page {

    private WebDriver driver;

    private static final By SEARCH_INPUT = By.name("q");
    private static final By SEARCH_BTN = By.name("btnG");
    private static final By SEARCH_RESULT = By.className("g");

    public Page(Browsers browserType) {
        setWebDriver(browserType);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    private void setWebDriver(Browsers browserType) {
        switch (browserType) {
            case CHROME:
                driver = new ChromeDriver();
                break;
            case OPERA:
                driver = new OperaDriver();
                break;
            case FIREFOX:
                driver = new FirefoxDriver();
                break;
            case IE:
                driver = new InternetExplorerDriver();
                break;
        }
    }

    public void open(String url) {
        driver.get(url);
    }

    private WebElement getSearchField() {
        return driver.findElement(SEARCH_INPUT);
    }

    public void sendQuery(String query) {
        getSearchField().sendKeys(query);
    }

    private WebElement getSearchButton() {
        return driver.findElement(SEARCH_BTN);
    }

    public void pressSearchButton() {
        getSearchButton().click();
    }

    public List<WebElement> getSearchResult() {
        return driver.findElements(SEARCH_RESULT);
    }

    public void quit() {
        driver.quit();
    }

}
