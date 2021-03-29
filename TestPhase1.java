public class TestPhase1
{
	public static void main(String[] args)
	{
		//Testing the Stock class:
		
		Stock s1 = new Stock("GameStonk", "GMS", 400);
		System.out.println(s1);
		
		s1.changeCurrentPrice(350);
		System.out.println(s1);
		
		s1.changeCurrentPrice(360);
		System.out.println(s1);
		
		System.out.println();
		
		//Testing the StockTransaction class:
		
		OwnedStock st1 = new OwnedStock(10, s1, 360);
		System.out.println(st1);
		System.out.printf("Profit = $%.2f\n", st1.getProfit());
		
		System.out.println();
		s1.changeCurrentPrice(350);
		System.out.println(s1);
		System.out.printf("Profit = $%.2f\n", st1.getProfit());
		
		System.out.println();
		System.out.println("After buying 10 more shares:");
		double cost = st1.addShares(10, s1.getCurrentPrice());
		System.out.printf("The cost of the transaction: %.2f\n", cost);
		System.out.println(st1);
		System.out.printf("Profit = $%.2f\n", st1.getProfit());
		
		System.out.println();
		s1.changeCurrentPrice(400);
		System.out.println(s1);
		System.out.println(st1);
		System.out.printf("Profit = $%.2f\n", st1.getProfit());
		
		
		System.out.println();
		System.out.println("After selling 15 shares:");
		double moneyMade = st1.sellShares(15, s1.getCurrentPrice());
		System.out.printf("Received this much from the sale: %.2f\n", moneyMade);
		System.out.println(st1);
		System.out.printf("Profit = $%.2f\n", st1.getProfit());
		
		
		//Testing short selling:
		System.out.println("\nTesting short selling:");
		OwnedStock st2 = new OwnedStock(-100, s1, 400);
		System.out.println(st2);
		s1.changeCurrentPrice(100);
		System.out.println(s1);
		System.out.printf("Profit = $%.2f\n", st2.getProfit());
		
		//Testing the two new types of exceptions:
		System.out.println();
		try{
			throw new InvalidTransactionException("Problem with a transaction!");
		}
		catch(InvalidTransactionException ite)
		{
			System.out.println(ite.getMessage());
		}
		
		try{
			throw new InsufficientFundsException("Not enough funds!");
		}
		catch(InvalidTransactionException ite)
		{
			System.out.println(ite.getMessage());
		}
	}
}