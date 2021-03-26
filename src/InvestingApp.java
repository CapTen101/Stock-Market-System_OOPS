public class InvestingApp extends InvestingAccount {

    public InvestingApp(String nameOfPlatform, double cashBalance, String owner) {
        super(nameOfPlatform, cashBalance, owner);
    }

    @Override
    public void shortStock(Stock stockToShort, int quantityToShort, double shortingPricePerShare) throws InvalidTransactionException {
        throw new InvalidTransactionException("Shorting is not allowed in this account!");
    }

    @Override
    public void buyStock(Stock stockToBuy, int quantityToBuy, double buyingPricePerShare) throws InvalidTransactionException {

        if (getCashBalance() < quantityToBuy * buyingPricePerShare) {
            throw new InsufficientFundsException("Not enough cash available!");
        } else {
            // Process the transaction
            super.buyStock(stockToBuy, quantityToBuy, buyingPricePerShare);
        }

    }
}
