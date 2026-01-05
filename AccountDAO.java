package Bank;

import java.io.*;

public class AccountDAO {

    private static final String FILE_NAME = "bank_account.dat";

    public Account loadAccount() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return new Account("1001", "Default User", 0);
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (Account) ois.readObject();
        } catch (Exception e) {
            System.out.println("Error loading account. Creating new account.");
            return new Account("1001", "Default User", 0);
        }
    }

    public void saveAccount(Account account) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(account);
        } catch (IOException e) {
            System.out.println("Error saving account.");
        }
    }
}
