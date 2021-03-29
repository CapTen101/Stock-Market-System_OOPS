public class TestPhase3
{
	public static void main(String[] args)
	{
		Market m = new Market();
		
		//Creating some stocks
		m.addStock(new Stock("GameStonk", "GMS", 25));
		m.addStock(new Stock("BrownBerry", "BB", 5));
		m.addStock(new Stock("ABC theaters", "ABC", 2));
		
		//Creating some Investing accounts
		m.addAccount(new InvestingApp("RobinScarf", 10000, "Purring Kitty"));
		m.addAccount(new InvestingApp("RobinScarf", 20000, "Roaring Cat"));
		m.addAccount(new InvestingApp("RobinScarf", 30000, "Jumping Bunny"));
		m.addAccount(new InvestingApp("RobinScarf", 40000, "Happy Doggy"));
		m.addAccount(new InvestingApp("RobinScarf", 50000, "Angry Bird"));
		m.addAccount(new InvestingApp("RobinScarf", 60000, "Mean Llama"));
		m.addAccount(new InvestingApp("RobinScarf", 70000, "Sleepy Sloth"));
		
		m.addAccount(new PremiumAccount("HedgyTrades", 1000000, "Lemon Capital", 9.99, 100000));
		m.addAccount(new PremiumAccount("HedgyTrades", 2000000, "Milven Capital", 7.99, 100000));
		
		m.processFile("transactions.txt");
	}
}