import java.util.ArrayList;

abstract class InvestingAccount {

    String NameOfPlatform;
    double CashBalance;
    long AccountNumber;
    String Owner;
    ArrayList<OwnedStock> ownedStocks;
    static long assignAccountNumber = 0;

    public InvestingAccount(String nameOfPlatform, double cashBalance, String owner) {
        NameOfPlatform = nameOfPlatform;
        CashBalance = cashBalance;
        Owner = owner;
        AccountNumber = 100000000000L + assignAccountNumber;

        // Increment the class variable for the next investing account
        assignAccountNumber++;

        ownedStocks = new ArrayList<OwnedStock>();
    }

    public long getAccountNumber() {
        return AccountNumber;
    }

    public double getCashBalance() {
        return CashBalance;
    }

    public double getNetWorth() {

        double networth = 0.00;
        networth += CashBalance;

        for (OwnedStock ownedStock : ownedStocks) {
            networth += ownedStock.getStock().getCurrentPrice() * ownedStock.getQuantity();
        }

        return networth;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(
                NameOfPlatform + " account #" + getAccountNumber() + "\n" +
                        "Owner: " + Owner + '\n' +
                        "Cash balance: $" + String.format("%.2f", getCashBalance()) + "\n" +
                        "Stocks owned: " + "\n");

        for (OwnedStock ownedstock : ownedStocks) {
            Stock stock = ownedstock.getStock();
            result.append(ownedstock.getQuantity() + " of " + stock.getSymbol() + " at " + String.format("%.2f", stock.getCurrentPrice()) + " (total value paid = $" + String.format("%.2f", ownedstock.getValue()) + ")\n");
        }

        return result.toString();
    }

    public void updateCashBalance(double amountToAdd) {
        CashBalance += amountToAdd;
    }

    public void sellStock(Stock stockToSell, int quantityToSell, double sellingPricePerShare) throws InvalidTransactionException {

        double revenueEarned = 0.00;
        boolean ownsThisStock = false;
        boolean enoughQuantityAvailable = false;

        for (OwnedStock ownedstock : ownedStocks) {
            Stock stock = ownedstock.getStock();

            if (stockToSell.equals(stock)) {
                ownsThisStock = true;
            }

            // If below condition is true, transaction is valid
            if (ownsThisStock && quantityToSell <= ownedstock.getQuantity()) {
                enoughQuantityAvailable = true;

                // Updating the cash balance
                revenueEarned = ownedstock.sellShares(quantityToSell, sellingPricePerShare);
                updateCashBalance(revenueEarned);

                // If stock quantity becomes zero
                if (ownedstock.getQuantity() == 0) {
                    ownedStocks.remove(ownedstock);
                }

                // Update the current price of the share
                stock.changeCurrentPrice(sellingPricePerShare);

                break;
            } else if (ownsThisStock && quantityToSell > ownedstock.getQuantity()) {
                break;
            }
        }

        if (!ownsThisStock) {
            throw new InvalidTransactionException("Cannot sell stock that is not owned!");
        }
        if (!enoughQuantityAvailable)
            throw new InvalidTransactionException("Not enough shares to sell!");
    }

    public void shortStock(Stock stockToShort, int quantityToShort, double shortingPricePerShare) throws InvalidTransactionException {
        double shortingRevenueEarned = 0.00;
        boolean ownsThisStock = false;

        for (OwnedStock ownedstock : ownedStocks) {
            Stock stock = ownedstock.getStock();

            if (stockToShort.equals(stock)) {
                ownsThisStock = true;
                throw new InvalidTransactionException("Cannot short a stock that is owned!");
            }
        }

        OwnedStock shortedStock = new OwnedStock(-1 * quantityToShort, stockToShort, shortingPricePerShare);
        ownedStocks.add(shortedStock);
        updateCashBalance(quantityToShort * shortingPricePerShare);
        shortedStock
                .getStock()
                .changeCurrentPrice(shortingPricePerShare);
    }

    public void buyStock(Stock stockToBuy, int quantityToBuy, double buyingPricePerShare) throws InvalidTransactionException {
        boolean ownsThisStock = false;

        for (OwnedStock ownedstock : ownedStocks) {
            Stock stock = ownedstock.getStock();

            if (stockToBuy.equals(stock)) {
                ownsThisStock = true;

                ownedstock.addShares(quantityToBuy, buyingPricePerShare);
                updateCashBalance(-1 * quantityToBuy * buyingPricePerShare);
                ownedstock
                        .getStock()
                        .changeCurrentPrice(buyingPricePerShare);

                if (ownedstock.getQuantity() == 0)
                    ownedStocks.remove(ownedstock);

                break;
            }
        }

        if (!ownsThisStock) {
            OwnedStock newOwnedStock = new OwnedStock(quantityToBuy, stockToBuy, buyingPricePerShare);
            ownedStocks.add(newOwnedStock);
            updateCashBalance(-1 * quantityToBuy * buyingPricePerShare);
            newOwnedStock
                    .getStock()
                    .changeCurrentPrice(buyingPricePerShare);
        }

    }
}