public class Stock {

    private String stockName;
    private String Symbol;
    private double CurrentPrice;
    private double ChangeInPrice;

    public Stock(String stockName, String stockSymbol, double currentStockPrice) {
        this.stockName = stockName;
        Symbol = stockSymbol;
        CurrentPrice = currentStockPrice;
        ChangeInPrice = 0.00;
    }

    @Override
    public String toString() {
        return stockName + " (" + Symbol + "): "
                + String.format("%.2f", CurrentPrice)
                + " (change: " + String.format("%.2f", ChangeInPrice) + ")";
    }

    public String getSymbol() {
        return Symbol;
    }

    public double getCurrentPrice() {
        return CurrentPrice;
    }

    public void changeCurrentPrice(double newPrice) {
        ChangeInPrice = newPrice - CurrentPrice;
        CurrentPrice = newPrice;
    }
}
