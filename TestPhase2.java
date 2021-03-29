public class TestPhase2
{
	public static void main(String[] args)
	{
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
		}
		catch(InvalidTransactionException e){
			System.out.println(e.getMessage());
		}
		
		try {
			ia1.sellStock(gms, 50, 25);  //does not own stock
		}
		catch(InvalidTransactionException e){
			System.out.println(e.getMessage());
		}
		
		try {
			ia1.shortStock(gms, 50, 25);  //not allowed to short
		}
		catch(InvalidTransactionException e){
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
		}
		catch(InvalidTransactionException e){
			System.out.println(e.getMessage());
		}
		
		System.out.println();
		
		try {
			pa1.buyStock(abc, 100005, 3); //PremiumAccount can only buy multiple of 100
		}
		catch(InvalidTransactionException e){
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
		}
		catch(InvalidTransactionException e){
			System.out.println(e.getMessage());
		}
	}
}