package online_banking_system;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.EventQueue;
import java.awt.LayoutManager;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.BevelBorder;
import javax.swing.ImageIcon;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;

public class GUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private LinkedList<Account> globalAccounts;

    private JTextField depositInput;
    private JTextField withdrawInput;
    private JTextField transferAmount;
    private JTextField acc1Transfer;
    private JTextField acc2Transfer;
    private JTextField accDeposit;
    private JTextField accWithdraw;

    private JButton showAllButton;
    private JButton depositButton;
    private JButton withdrawButton;
    private JButton transferButton;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LinkedList<Account> accounts = new LinkedList<>();
                    GUI frame = new GUI(accounts);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public GUI(LinkedList<Account> accounts) {
        super("Online Banking System");
        this.globalAccounts = accounts;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1044, 490);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(192, 192, 192));
        panel_1.setBounds(0, 0, 1030, 451);
        contentPane.add(panel_1);
        panel_1.setLayout(null);

        // Show all section
        showAllButton = new JButton("Show");
        showAllButton.setBackground(new Color(255, 255, 255));
        showAllButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
        showAllButton.setBounds(178, 59, 117, 29);
        panel_1.add(showAllButton);

        JLabel showAllData = new JLabel("Show All Data");
        showAllData.setForeground(new Color(128, 128, 128));
        showAllData.setHorizontalAlignment(SwingConstants.CENTER);
        showAllData.setFont(new Font("Times New Roman", Font.BOLD, 16));
        showAllData.setBounds(178, 22, 127, 41);
        panel_1.add(showAllData);

        JPanel panel_2 = new JPanel();
        panel_2.setBorder(null);
        panel_2.setBackground(new Color(128, 128, 128));
        panel_2.setBounds(583, 47, 314, 182);
        panel_1.add(panel_2);
        panel_2.setLayout(null);
        
                JLabel lblNewLabel_3 = new JLabel("Deposit");
                lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
                lblNewLabel_3.setBounds(100, 0, 105, 28);
                panel_2.add(lblNewLabel_3);
                lblNewLabel_3.setForeground(new Color(255, 255, 255));
                lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
                
                        JLabel depositAcc = new JLabel("Deposit Account:");
                        depositAcc.setBounds(-13, 57, 168, 20);
                        panel_2.add(depositAcc);
                        depositAcc.setForeground(new Color(0, 0, 0));
                        depositAcc.setFont(new Font("Times New Roman", Font.PLAIN, 15));
                        depositAcc.setHorizontalAlignment(SwingConstants.CENTER);
                        
                                accDeposit = new JTextField();
                                accDeposit.setBounds(158, 59, 125, 20);
                                panel_2.add(accDeposit);
                                accDeposit.setBackground(new Color(192, 192, 192));
                                accDeposit.setColumns(10);
                                
                                        JLabel lblNewLabel = new JLabel("Deposit Amount:");
                                        lblNewLabel.setBounds(-13, 87, 168, 26);
                                        panel_2.add(lblNewLabel);
                                        lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
                                        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
                                        
                                                depositInput = new JTextField();
                                                depositInput.setBounds(158, 89, 125, 20);
                                                panel_2.add(depositInput);
                                                depositInput.setFont(new Font("Tahoma", Font.PLAIN, 11));
                                                depositInput.setBackground(new Color(192, 192, 192));
                                                depositInput.setColumns(10);
                                                
                                                        // Deposit section
                                                        depositButton = new JButton("Deposit");
                                                        depositButton.setBounds(91, 136, 114, 28);
                                                        panel_2.add(depositButton);
                                                        depositButton.setForeground(new Color(0, 0, 0));
                                                        depositButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
                                                        depositButton.setBackground(new Color(0, 255, 0));
                                                        
                                                                
                                                                
                                                                
                                                                
                                                                depositButton.addActionListener(new ActionListener() {
                                                                    public void actionPerformed(ActionEvent e) {
                                                                        try {
                                                                            int depositAccount = Integer.parseInt(accDeposit.getText());
                                                                            int depositAmount = Integer.parseInt(depositInput.getText());
                                                                            int newBalance = 0;
                                                        
                                                                            if (checkAccValidity(depositAccount)) {
                                                                                for (Account account : globalAccounts) {
                                                                                    if (account.getAccountNum() == depositAccount) {
                                                                                        account.deposit(depositAmount);
                                                                                        newBalance = account.getBalance();
                                                                                    }
                                                                                }
                                                        
                                                                                try (BufferedWriter writer = new BufferedWriter(
                                                                                        new FileWriter("accounts.csv"))) {
                                                                                    for (Account account : globalAccounts) {
                                                                                        String firstName = account.getFirstName();
                                                                                        String lastName = account.getLastName();
                                                                                        int accountNumber = account.getAccountNum();
                                                                                        int accountBalance = account.getBalance();
                                                                                        if (accountNumber == depositAccount) {
                                                                                            accountBalance = newBalance;
                                                                                        }
                                                        
                                                                                        writer.write(
                                                                                                firstName + "," + lastName + "," + accountNumber + "," + accountBalance + "\n");
                                                                                    }
                                                        
                                                                                } catch (IOException e1) {
                                                                                    JOptionPane.showMessageDialog(null, "Error writing data to CSV file", "Error",
                                                                                            JOptionPane.ERROR_MESSAGE);
                                                                                }
                                                        
                                                                                JOptionPane.showMessageDialog(null, "Deposit successful", "Success",
                                                                                        JOptionPane.INFORMATION_MESSAGE);
                                                                            } else {
                                                                                JOptionPane.showMessageDialog(null, "Account doesnt exist.", "Error",
                                                                                        JOptionPane.ERROR_MESSAGE);
                                                                            }
                                                                        } catch (NumberFormatException ex) {
                                                                            JOptionPane.showMessageDialog(null, "Please enter valid numbers for account and amount", "Error",
                                                                                    JOptionPane.ERROR_MESSAGE);
                                                                        }
                                                                    }
                                                                });

        JPanel panel_3 = new JPanel();
        panel_3.setBorder(null);
        panel_3.setBackground(new Color(128, 128, 128));
        panel_3.setBounds(583, 248, 315, 182);
        panel_1.add(panel_3);
        panel_3.setLayout(null);

        withdrawInput = new JTextField();
        withdrawInput.setBackground(new Color(192, 192, 192));
        withdrawInput.setBounds(156, 86, 129, 20);
        panel_3.add(withdrawInput);
        withdrawInput.setColumns(10);

        withdrawButton = new JButton("Withdraw");
        withdrawButton.setBackground(new Color(255, 0, 0));
        withdrawButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
        withdrawButton.setBounds(95, 129, 114, 29);
        panel_3.add(withdrawButton);

        accWithdraw = new JTextField();
        accWithdraw.setBackground(new Color(192, 192, 192));
        accWithdraw.setBounds(156, 56, 129, 20);
        panel_3.add(accWithdraw);
        accWithdraw.setColumns(10);

        JLabel withdrawAcc = new JLabel("Withdraw Account:");
        withdrawAcc.setBackground(new Color(240, 240, 240));
        withdrawAcc.setBounds(-6, 50, 166, 28);
        panel_3.add(withdrawAcc);
        withdrawAcc.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        withdrawAcc.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel lblNewLabel_1 = new JLabel("Withdraw Amount:");
        lblNewLabel_1.setBounds(-6, 86, 166, 20);
        panel_3.add(lblNewLabel_1);
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        
                JLabel lblNewLabel_3_1 = new JLabel("Withdraw");
                lblNewLabel_3_1.setBounds(95, -1, 105, 28);
                panel_3.add(lblNewLabel_3_1);
                lblNewLabel_3_1.setForeground(new Color(255, 255, 255));
                lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.CENTER);
                lblNewLabel_3_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));

        JPanel panel_4 = new JPanel();
        panel_4.setBackground(new Color(128, 128, 128));
        panel_4.setBounds(37, 155, 473, 198);
        panel_1.add(panel_4);
        panel_4.setLayout(null);

        acc1Transfer = new JTextField();
        acc1Transfer.setBounds(20, 91, 125, 28);
        panel_4.add(acc1Transfer);
        acc1Transfer.setBackground(new Color(192, 192, 192));
        acc1Transfer.setColumns(10);

        acc2Transfer = new JTextField();
        acc2Transfer.setBackground(new Color(192, 192, 192));
        acc2Transfer.setBounds(175, 91, 125, 28);
        panel_4.add(acc2Transfer);
        acc2Transfer.setColumns(10);

        transferAmount = new JTextField();
        transferAmount.setBackground(new Color(192, 192, 192));
        transferAmount.setBounds(327, 91, 125, 28);
        panel_4.add(transferAmount);
        transferAmount.setColumns(10);

        transferButton = new JButton("Transfer");
        transferButton.setBackground(new Color(255, 0, 0));
        transferButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
        transferButton.setBounds(340, 149, 112, 28);
        panel_4.add(transferButton);

        JLabel transferAcc1 = new JLabel("Sender:");
        transferAcc1.setHorizontalAlignment(SwingConstants.CENTER);
        transferAcc1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        transferAcc1.setBounds(36, 67, 89, 14);
        panel_4.add(transferAcc1);

        JLabel transferAcc2 = new JLabel("Receiver:");
        transferAcc2.setHorizontalAlignment(SwingConstants.CENTER);
        transferAcc2.setBounds(194, 67, 86, 14);
        panel_4.add(transferAcc2);
        transferAcc2.setFont(new Font("Times New Roman", Font.PLAIN, 15));

        JLabel transferAmount_1 = new JLabel("Amount:");
        transferAmount_1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        transferAmount_1.setHorizontalAlignment(SwingConstants.CENTER);
        transferAmount_1.setBounds(348, 67, 86, 14);
        panel_4.add(transferAmount_1);
        
                JLabel lblNewLabel_3_2 = new JLabel("Transfer");
                lblNewLabel_3_2.setBounds(175, 10, 105, 28);
                panel_4.add(lblNewLabel_3_2);
                lblNewLabel_3_2.setForeground(new Color(255, 255, 255));
                lblNewLabel_3_2.setHorizontalAlignment(SwingConstants.CENTER);
                lblNewLabel_3_2.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));

        transferButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int acc1transfer = Integer.parseInt(acc1Transfer.getText());
                    int acc2transfer = Integer.parseInt(acc2Transfer.getText());
                    int acctransferAmount = Integer.parseInt(transferAmount.getText());
                    int acc1newBalance = 0;
                    int acc2newBalance = 0;

                    if (checkAccValidity(acc1transfer) && checkAccValidity(acc2transfer)) {
                        Account acc1 = null;
                        Account acc2 = null;

                        for (Account account : globalAccounts) {
                            if (account.getAccountNum() == acc1transfer) {
                                acc1 = account;
                                break;
                            }
                        }

                        for (Account account : globalAccounts) {
                            if (account.getAccountNum() == acc2transfer) {
                                acc2 = account;
                                break;
                            }
                        }

                        if (acc1 != null && acc2 != null) {
                            if (acc1.getBalance() >= acctransferAmount) {
                                acc1.withdraw(acctransferAmount);
                                acc1newBalance = acc1.getBalance();
                                acc2.deposit(acctransferAmount);
                                acc2newBalance = acc2.getBalance();
                                try (BufferedWriter writer = new BufferedWriter(
                                        new FileWriter(
                                                "./src/online_banking_system/accounts.csv"))) {
                                    for (Account account : globalAccounts) {
                                        String firstName = account.getFirstName();
                                        String lastName = account.getLastName();
                                        int accountNumber = account.getAccountNum();
                                        int accountBalance = account.getBalance();
                                        if (accountNumber == acc1transfer) {
                                            accountBalance = acc1newBalance;
                                        }
                                        if (accountNumber == acc2transfer) {
                                            accountBalance = acc2newBalance;
                                        }
                                        writer.write(firstName + "," + lastName + "," + accountNumber + "," + accountBalance + "\n");
                                    }

                                } catch (IOException e1) {
                                    JOptionPane.showMessageDialog(null, "Error writing data to CSV file", "Error", JOptionPane.ERROR_MESSAGE);
                                }

                                JOptionPane.showMessageDialog(null, "Transfer successful", "Success", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(null, "Insufficient balance.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid account number(s).", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter valid account number in both the fields.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        withdrawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int withdrawAccount = Integer.parseInt(accWithdraw.getText());
                    int withdrawAmount = Integer.parseInt(withdrawInput.getText());
                    int newBalance = 0;

                    if (checkAccValidity(withdrawAccount)) {
                        for (Account account : globalAccounts) {
                            if (account.getAccountNum() == withdrawAccount) {
                                // check if balance is enough to withdraw
                                if (account.getBalance() >= withdrawAmount) {
                                    account.withdraw(withdrawAmount);
                                    newBalance = account.getBalance();
                                    try (BufferedWriter writer = new BufferedWriter(
                                            new FileWriter(
                                                    "./src/online_banking_system/accounts.csv"))) {
                                        for (Account account1 : globalAccounts) {
                                            String firstName = account1.getFirstName();
                                            String lastName = account1.getLastName();
                                            int accountNumber = account1.getAccountNum();
                                            int accountBalance = account1.getBalance();
                                            if (accountNumber == withdrawAccount) {
                                                accountBalance = newBalance;
                                            }

                                            writer.write(firstName + "," + lastName + "," + accountNumber + ","
                                                    + accountBalance + "\n");
                                        }

                                    } catch (IOException e1) {
                                        JOptionPane.showMessageDialog(null, "Error writing data to CSV file", "Error",JOptionPane.ERROR_MESSAGE);
                                    }

                                    JOptionPane.showMessageDialog(null, "Withdraw successful", "Success",
                                            JOptionPane.INFORMATION_MESSAGE);
                                }

                                // if balance is not enough to withdraw
                                else {
                                    JOptionPane.showMessageDialog(null, "Insufficient balance.", "Error",
                                            JOptionPane.ERROR_MESSAGE);
                                }

                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Account doesnt exist.", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter valid numbers for account and amount", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        
        
        
        
        
        showAllButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame allAccountsFrame = new JFrame("All Accounts");
                allAccountsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                String[] columnNames = {"First Name", "Last Name", "Account Number", "Balance"};
                Object[][] data = new Object[globalAccounts.size()][4];

                int index = 0;
                for (Account account : globalAccounts) {
                    data[index][0] = account.getFirstName();
                    data[index][1] = account.getLastName();
                    data[index][2] = account.getAccountNum();
                    data[index][3] = account.getBalance();
                    index++;
                }

                JTable table = new JTable(data, columnNames);
                JScrollPane scrollPane = new JScrollPane(table);
                allAccountsFrame.getContentPane().add(scrollPane);

                allAccountsFrame.pack();
                allAccountsFrame.setVisible(true);
            }
        });
    }

    private boolean checkAccValidity(int accNum) {
        for (Account account : globalAccounts) {
            if (account.getAccountNum() == accNum) {
                return true;
            }
        }
        return false;
    }
}
