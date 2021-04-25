import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class BookSearchSteps {
    Library library = new Library();
    List<Book> result = new ArrayList<>();

    @ParameterType("([0-9]{4})-([0-9]{2})-([0-9]{2})")
    public Date iso8601Date(String year, String month, String day){
        Date date = null;
        try {
            date =  new SimpleDateFormat("dd/MM/yy").parse(day+"/"+month+"/"+year);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    @When("the customer searches for books published between {iso8601Date} and {iso8601Date}")
    public void setSearchParameters(final Date from, final Date to) {
        result = library.findBooks(from, to);
    }

    @Then("{int} books should have been found$")
    public void verifyAmountOfBooksFound(final int booksFound) {
        assertThat(result.size(), equalTo(booksFound));
    }

    @Then("Book {int} should have the title {string}$")
    public void verifyBookAtPosition(final int position, final String title) {
        assertThat(result.get(position - 1).getTitle(), equalTo(title));
    }

    @When("the customer searches for books written by {string}")
    public void setSearchAuthor(final String author){
        result = library.findByAuthor(author);
    }
    @Then("{int} books should have been found$")
    public void verifyResult(final int booksFound){
        assertThat(result.size(),equalTo(booksFound));
    }
}
