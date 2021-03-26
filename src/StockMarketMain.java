public class StockMarketMain {

    public static void main(String[] args) {


        // PHASE-1

        // Testing the Stock class:
//
//		Stock s1 = new Stock("GameStonk", "GMS", 400);
//		System.out.println(s1);
//
//		s1.changeCurrentPrice(350);
//		System.out.println(s1);
//
//		s1.changeCurrentPrice(360);
//		System.out.println(s1);
//
//		System.out.println();
//
//		//Testing the StockTransaction class:
//
//		OwnedStock st1 = new OwnedStock(10, s1, 360);
//		System.out.println(st1);
//		System.out.printf("Profit = $%.2f\n", st1.getProfit());
//
//		System.out.println();
//		s1.changeCurrentPrice(350);
//		System.out.println(s1);
//		System.out.printf("Profit = $%.2f\n", st1.getProfit());
//
//		System.out.println();
//		System.out.println("After buying 10 more shares:");
//		double cost = st1.addShares(10, s1.getCurrentPrice());
//		System.out.printf("The cost of the transaction: %.2f\n", cost);
//		System.out.println(st1);
//		System.out.printf("Profit = $%.2f\n", st1.getProfit());
//
//		System.out.println();
//		s1.changeCurrentPrice(400);
//		System.out.println(s1);
//		System.out.println(st1);
//		System.out.printf("Profit = $%.2f\n", st1.getProfit());
//
//
//		System.out.println();
//		System.out.println("After selling 15 shares:");
//		double moneyMade = st1.sellShares(15, s1.getCurrentPrice());
//		System.out.printf("Received this much from the sale: %.2f\n", moneyMade);
//		System.out.println(st1);
//		System.out.printf("Profit = $%.2f\n", st1.getProfit());
//
//
//		//Testing short selling:
//		System.out.println("\nTesting short selling:");
//		OwnedStock st2 = new OwnedStock(-100, s1, 400);
//		System.out.println(st2);
//		s1.changeCurrentPrice(100);
//		System.out.println(s1);
//		System.out.printf("Profit = $%.2f\n", st2.getProfit());
//
//		//Testing the two new types of exceptions:
//		System.out.println();
//		try{
//			throw new InvalidTransactionException("Problem with a transaction!");
//		}
//		catch(InvalidTransactionException ite)
//		{
//			System.out.println(ite.getMessage());
//		}
//
//		try{
//			throw new InsufficientFundsException("Not enough funds!");
//		}
//		catch(InvalidTransactionException ite)
//		{
//			System.out.println(ite.getMessage());
//		}





        // PHASE-2

        //Creating some stocks
        Stock gms = new Stock("GameStonk", "GMS", 25);
        Stock bb = new Stock("BrownBerry", "BB", 5);
        Stock abc = new Stock("ABC theaters", "ABC", 2);

        //Creating some Investing accounts
        InvestingAccount ia1 = new InvestingApp("RobinScarf", 1000, "Purring Kitty");
        System.out.println(ia1);

        InvestingAccount pa1 = new PremiumAccount("HedgyTrades", 5000000, "Lemon Capital", 10, 100000);
        System.out.println(pa1);

        //Testing the different methods
        try {
            ia1.buyStock(gms, 50, 25);  //not enough cash
        } catch (InvalidTransactionException e) {
            System.out.println(e.getMessage());
        }

        try {
            ia1.sellStock(gms, 50, 25);  //does not own stock
        } catch (InvalidTransactionException e) {
            System.out.println(e.getMessage());
        }

        try {
            ia1.shortStock(gms, 50, 25);  //not allowed to short
        } catch (InvalidTransactionException e) {
            System.out.println(e.getMessage());
        }

        try {
            ia1.buyStock(gms, 40, 25);
            System.out.println("\n" + ia1);

            ia1.sellStock(gms, 40, 26);
            System.out.println(ia1);

            pa1.shortStock(gms, 100000, 20);
            System.out.println(gms + "\n");
            System.out.println(pa1);
            System.out.println("Net worth of Purring Kitty = " + ia1.getNetWorth());
            System.out.println("Net worth of Lemon Capital = " + pa1.getNetWorth());

            System.out.println("\nAfter covering the short:");
            pa1.buyStock(gms, 100000, 15);
            System.out.println(gms + "\n");
            System.out.println(pa1);
            System.out.println("Net worth of Lemon Capital = " + pa1.getNetWorth());
        } catch (InvalidTransactionException e) {
            System.out.println(e.getMessage());
        }

        System.out.println();

        try {
            pa1.buyStock(abc, 100005, 3); //PremiumAccount can only buy multiple of 100
        } catch (InvalidTransactionException e) {
            System.out.println(e.getMessage());
        }

        System.out.println();

        try {
            pa1.buyStock(abc, 100000, 3);
            System.out.println(abc + "\n");
            pa1.buyStock(abc, 50000, 3.6);
            System.out.println(abc + "\n");

            System.out.println(pa1);

            pa1.sellStock(abc, 20000, 4);
            System.out.println(abc + "\n");

            System.out.println(pa1);

            pa1.buyStock(bb, 100000, 6);
            System.out.println(bb + "\n");

            System.out.println(pa1);
            System.out.println("Net worth of Lemon Capital = " + pa1.getNetWorth());
        } catch (InvalidTransactionException e) {
            System.out.println(e.getMessage());
        }


        // PHASE-3

//        Market m = new Market();
//
//        //Creating some stocks
//        m.addStock(new Stock("GameStonk", "GMS", 25));
//        m.addStock(new Stock("BrownBerry", "BB", 5));
//        m.addStock(new Stock("ABC theaters", "ABC", 2));
//
//        //Creating some Investing accounts
//        m.addAccount(new InvestingApp("RobinScarf", 10000, "Purring Kitty"));
//        m.addAccount(new InvestingApp("RobinScarf", 20000, "Roaring Cat"));
//        m.addAccount(new InvestingApp("RobinScarf", 30000, "Jumping Bunny"));
//        m.addAccount(new InvestingApp("RobinScarf", 40000, "Happy Doggy"));
//        m.addAccount(new InvestingApp("RobinScarf", 50000, "Angry Bird"));
//        m.addAccount(new InvestingApp("RobinScarf", 60000, "Mean Llama"));
//        m.addAccount(new InvestingApp("RobinScarf", 70000, "Sleepy Sloth"));
//
//        m.addAccount(new PremiumAccount("HedgyTrades", 1000000, "Lemon Capital", 9.99, 100000));
//        m.addAccount(new PremiumAccount("HedgyTrades", 2000000, "Milven Capital", 7.99, 100000));
//
//        m.processFile("transactions.txt");
    }
}
