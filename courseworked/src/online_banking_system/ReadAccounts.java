package online_banking_system;
import java.util.LinkedList;
import java.io.*;

class ReadAccounts {
    String url;

    ReadAccounts(String URL) {
        this.url = URL;
    }

    public LinkedList<String> getFirstNames() {
        LinkedList<String> firstNames = new LinkedList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(url)); // object of csv file
            String line;
            while ((line = reader.readLine()) != null) {
                String[] splitted = line.split(",");// making users data list
                firstNames.add(splitted[0]);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return firstNames;
    }

    public LinkedList<String> getLastNames() {
        LinkedList<String> lastNames = new LinkedList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(url));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] splitted = line.split(",");
                lastNames.add(splitted[1]);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lastNames;
    }

    public LinkedList<Integer> getAccounts() {
        LinkedList<Integer> accounts = new LinkedList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(url));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] splitted = line.split(",");
                accounts.add(Integer.parseInt(splitted[2]));
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    public LinkedList<Integer> getBalances() {
        LinkedList<Integer> balances = new LinkedList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(url));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] splitted = line.split(",");
                balances.add(Integer.parseInt(splitted[3]));
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return balances;
    }
}
