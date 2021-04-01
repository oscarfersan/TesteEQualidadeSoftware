import java.util.ArrayList;
import java.util.List;

public class StockPortfolio {
    private String name;
    private IStockMarket marketService;
    private ArrayList<Stock> stocks;

    public StockPortfolio(String name) {
        this.name = name;
        this.stocks = new ArrayList<>();
    }

    public void addStock(Stock stock){
        this.stocks.add(stock);
    }

    public double getTotalValue(){
        double result = 0;
        for (int i = 0; i < stocks.size(); i++) {
            result+=this.marketService.getPrice(stocks.get(i).getName())*stocks.get(i).getQuantity();
        }
        return result;
    }

    public IStockMarket getMarketService() {
        return marketService;
    }

    public void setMarketService(IStockMarket marketService) {
        this.marketService = marketService;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
