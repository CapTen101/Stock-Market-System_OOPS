public class OwnedStock {

    int Quantity;
    Stock tradedStock;
    double transactionPricePerShare;

    public OwnedStock(int quantity, Stock tradedStock, double transactionPricePerShare) {
        Quantity = quantity;
        this.tradedStock = tradedStock;
        this.transactionPricePerShare = transactionPricePerShare;
    }

    public int getQuantity() {
        return Quantity;
    }

    public Stock getStock() {
        return tradedStock;
    }

    public double getValue() {
        return getQuantity() * transactionPricePerShare;
    }

    public double getProfit() {
        return tradedStock.getCurrentPrice() * Quantity - getValue();
    }

    @Override
    public String toString() {
        return getQuantity() + " of "
                + tradedStock.getSymbol() + " at "
                + String.format("%.2f", transactionPricePerShare)
                + " (total value paid = $" + String.format("%.2f", getValue()) + ")";
    }

    public double addShares(int addQuantity, double newTransactionPricePerShare) {
        Quantity += addQuantity;
        transactionPricePerShare = (newTransactionPricePerShare + transactionPricePerShare) / 2;
        return addQuantity * newTransactionPricePerShare;
    }

    public double sellShares(int decreaseQuantity, double saleTransactionPricePerShare) {
        Quantity -= decreaseQuantity;
        return decreaseQuantity * saleTransactionPricePerShare;
    }
}
