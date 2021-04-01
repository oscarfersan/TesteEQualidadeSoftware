import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StockPortfolioTest {
    StockPortfolio portfolio;
    IStockMarket market;
    @BeforeEach
    public void setUp(){
        //Create a portfolio object which is to be tested
        portfolio = new StockPortfolio("Example");

        //Create the mock object of stock service
        market = mock(IStockMarket.class);

        //set the stockService to the portfolio
        portfolio.setMarketService(market);
    }
    @Test
    public void valueTest(){
        Stock zara = new Stock("Zara",10);
        Stock mango = new Stock("Mango",5);
        Stock bershka = new Stock("Bershka",10);

        this.portfolio.addStock(zara);
        this.portfolio.addStock(mango);
        this.portfolio.addStock(bershka);

        when(market.getPrice("Zara")).thenReturn(100.00);
        when(market.getPrice("Mango")).thenReturn(50.00);
        when(market.getPrice("Bershka")).thenReturn(100.00);
        ///assertEquals(portfolio.getTotalValue(),2250);
        assertThat(portfolio.getTotalValue(),is(2250.00));
    }
}