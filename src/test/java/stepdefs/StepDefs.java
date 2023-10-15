package stepdefs;

import driver.Driver;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class StepDefs extends BaseSteps {
    @Given("user on homepage")
    public void userOnHomepage() {

        gotoHome();

    }

    @When("user add the product {string} to the cart")
    public void userAddTheProductToTheCart(String productName) throws InterruptedException {

        addProductToCart(productName);
    }

    @And("user click to Cart")
    public void userClickToCart() {
        click(lcart);
    }

    @Then("the product {string} should be in Cart")
    public void theProductShouldBeInCart(String productName) {
        assertInCart(productName);
    }

    @Then("homepage should be opened")
    public void homepageShouldBeOpened() {

        shouldBeVisible(lhomePage);
    }

    @When("user search for {string}")
    public void userSearchFor(String text) {

        searchProduct(text);

    }

    @Then("there must be {int} listed product")
    public void thereMustBeListedProduct(int productNumber) throws InterruptedException {


        Assert.assertEquals(productNames.size(),productNumber);
    }
}
