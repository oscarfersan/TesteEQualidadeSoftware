import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;
public class StockPortfolioTester {
    StockPortfolio portfolio;
    IStockMarket market;
    public void setUp(){
        //Create a portfolio object which is to be tested
        portfolio = new StockPortfolio("Example");

        //Create the mock object of stock service
        market = mock(IStockMarket.class);

        //set the stockService to the portfolio
        portfolio.setMarketService(market);
    }
    public boolean valueTest(){
        Stock zara = new Stock("Zara",10);
        Stock mango = new Stock("Mango",5);
        Stock bershka = new Stock("Bershka",10);

        this.portfolio.addStock(zara);
        this.portfolio.addStock(mango);
        this.portfolio.addStock(bershka);

        when(market.getPrice("Zara")).thenReturn(100.00);
        when(market.getPrice("Mango")).thenReturn(50.00);
        when(market.getPrice("Bershka")).thenReturn(100.00);

        double result = portfolio.getTotalValue();
        return result == 2250;
    }
}
