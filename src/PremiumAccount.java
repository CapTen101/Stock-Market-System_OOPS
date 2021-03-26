public class PremiumAccount extends InvestingAccount {

    double transactionFees;
    double margin;

    public PremiumAccount(String nameOfPlatform, double cashBalance, String owner, double transactionFees, double margin) {
        super(nameOfPlatform, cashBalance, owner);

        this.transactionFees = transactionFees;
        this.margin = margin;
    }

    @Override
    public void sellStock(Stock stockToSell, int quantityToSell, double sellingPricePerShare) throws InvalidTransactionException {

        if (quantityToSell % 100 != 0)
            throw new InvalidTransactionException("Quantity of stocks is not a multiple of 100!");

        // Process the transaction
        super.sellStock(stockToSell, quantityToSell, sellingPricePerShare);

        updateCashBalance(-1 * transactionFees);

    }

    @Override
    public void shortStock(Stock stockToShort, int quantityToShort, double shortingPricePerShare) throws InvalidTransactionException {

        if (quantityToShort % 100 != 0)
            throw new InvalidTransactionException("Quantity of stocks is not a multiple of 100!");

        // Process the transaction
        super.shortStock(stockToShort, quantityToShort, shortingPricePerShare);

        updateCashBalance(-1 * transactionFees);
    }

    @Override
    public void buyStock(Stock stockToBuy, int quantityToBuy, double buyingPricePerShare) throws InvalidTransactionException {

        if (quantityToBuy % 100 != 0)
            throw new InvalidTransactionException("Quantity of stocks is not a multiple of 100!");
        if ((getCashBalance() + margin) < (quantityToBuy * buyingPricePerShare + transactionFees))
            throw new InsufficientFundsException("Not enough cash available!");

        // Process the transaction
        super.buyStock(stockToBuy, quantityToBuy, buyingPricePerShare);

        updateCashBalance(-1 * transactionFees);
    }
}
