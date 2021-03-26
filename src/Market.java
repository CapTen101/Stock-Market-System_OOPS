import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Market {

    ArrayList<InvestingAccount> investingAccounts;
    ArrayList<Stock> stocks;

    public Market() {
        investingAccounts = new ArrayList<>();
        stocks = new ArrayList<>();
    }

    public Stock getStock(String stockSymbol) {
        for (Stock stock : stocks) {
            if (stock.getSymbol().equals(stockSymbol)) {
                return stock;
            }
        }
        return null;
    }

    public InvestingAccount getInvestingAccount(long accountNumber) {
        for (InvestingAccount investingAccount : investingAccounts) {
            if (investingAccount.getAccountNumber() == accountNumber) {
                return investingAccount;
            }
        }
        return null;
    }

    public void addAccount(InvestingAccount investingAccount) {
        investingAccounts.add(investingAccount);
    }

    public void addStock(Stock stock) {
        stocks.add(stock);
    }

    public void processFile(String fileName) {
        File file = new File(fileName);

        try {
            Scanner dataReader = new Scanner(file);

            while (dataReader.hasNextLine()) {
                String nextLine = dataReader.nextLine();
                processTransaction(nextLine);
            }
            dataReader.close();

        } catch (IOException | InvalidTransactionException e) {
            e.printStackTrace();
        }
    }

    public void processTransaction(String nextLine) throws InvalidTransactionException {
        if (nextLine.charAt(0) == '#')
            return;

        ArrayList<String> buyList = new ArrayList<>();
        ArrayList<String> sellList = new ArrayList<>();
        ArrayList<String> shortList = new ArrayList<>();

        StringTokenizer tokenizer = new StringTokenizer(nextLine, " ");

        switch (tokenizer.nextToken()) {
            case "BUY" -> {
                while (tokenizer.hasMoreElements()) {
                    buyList.add(tokenizer.nextToken());
                }

                long accountNumber = Long.parseLong(buyList.get(0));
                String symbol = buyList.get(1);
                int quantity = Integer.parseInt(buyList.get(2));
                double price = Double.parseDouble(buyList.get(3));

                InvestingAccount investingAccount = getInvestingAccount(accountNumber);
                Stock stock = getStock(symbol);

                if (investingAccount == null || stock == null) {
                    System.out.println("INVALID COMMAND");
                    System.out.println("...");
                    return;
                }

                try {
                    investingAccount.buyStock(stock, quantity, price);
                } catch (InvalidTransactionException e) {
                    System.out.println(e.getMessage());
                }

                buyList.clear();
                System.out.println("...");
            }
            case "SELL" -> {
                while (tokenizer.hasMoreElements()) {
                    sellList.add(tokenizer.nextToken());
                }

                long accountNumber = Long.parseLong(sellList.get(0));
                String symbol = sellList.get(1);
                int quantity = Integer.parseInt(sellList.get(2));
                double price = Double.parseDouble(sellList.get(3));

                InvestingAccount investingAccount = getInvestingAccount(accountNumber);
                Stock stock = getStock(symbol);

                if (investingAccount == null || stock == null) {
                    System.out.println("INVALID COMMAND");
                    System.out.println("...");
                    return;
                }

                try {
                    investingAccount.sellStock(stock, quantity, price);
                } catch (InvalidTransactionException e) {
                    System.out.println(e.getMessage());
                }

                sellList.clear();
                System.out.println("...");
            }
            case "SHORT" -> {
                while (tokenizer.hasMoreElements()) {
                    shortList.add(tokenizer.nextToken());
                }

                long accountNumber = Long.parseLong(shortList.get(0));
                String symbol = shortList.get(1);
                int quantity = Integer.parseInt(shortList.get(2));
                double price = Double.parseDouble(shortList.get(3));

                InvestingAccount investingAccount = getInvestingAccount(accountNumber);
                Stock stock = getStock(symbol);

                if (investingAccount == null || stock == null) {
                    System.out.println("INVALID COMMAND");
                    System.out.println("...");
                    return;
                }

                try {
                    investingAccount.shortStock(stock, quantity, price);
                } catch (InvalidTransactionException e) {
                    System.out.println(e.getMessage());
                }

                shortList.clear();
                System.out.println("...");
            }
            case "STATUS" -> {

                String nextToken = tokenizer.nextToken();

                if (nextToken.startsWith("1")) {
                    long accountNumber = Long.parseLong(nextToken);
                    InvestingAccount investingAccount = getInvestingAccount(accountNumber);

                    if (investingAccount == null) {
                        System.out.println("INVALID COMMAND");
                        System.out.println("...");
                        return;
                    }

                    System.out.print(investingAccount.toString());
                    System.out.println("Net worth: " + String.format("%.2f", investingAccount.getNetWorth()));

                    System.out.println("...");
                } else {
                    Stock stock = getStock(nextToken);

                    if (stock == null) {
                        System.out.println("INVALID COMMAND");
                        System.out.println("...");
                        return;
                    }

                    System.out.println(stock.toString());
                    System.out.println("...");
                }
            }
        }
    }
}
