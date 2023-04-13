package OOP.ec22819.MP;

import OOP.ec22819.MP.Item;
import OOP.ec22819.MP.Visitor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

class GUIVisitor implements Visitor {

    GUIVisitor(PrintStream out, Scanner in, Item[] items) {
        this.out = out;
        this.in = in;
        this.items = items;
    }

    static enum itemButton {
        Waiting,
        Accept_item,
        Reject_item,
        yes_ans,
        no_ans,
    };

    JFrame db = new JFrame();
    JPanel panel = new JPanel();
    JPanel ARPanel = new JPanel();
    JPanel YNPanel = new JPanel();

    JTextArea txt = new JTextArea("Hello!\n");

    // Accept Button
    JButton btn1 = new JButton("Accept Item"); 
    itemButton btnAccept = itemButton.Waiting;

    // Reject Button
    JButton btn2 = new JButton("Reject Item"); 
    itemButton btnReject = itemButton.Waiting;

    // Y/N button
    JButton yes = new JButton("Yes");
    itemButton btnYes = itemButton.Waiting;
    JButton no = new JButton("No");
    itemButton btnNo = itemButton.Waiting;


    final PrintStream out;
    private final Scanner in;
    private int purse;
    private final Item[] items;
    private int next;

    public GUIVisitor(PrintStream ps, InputStream is) {
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnAccept = itemButton.Accept_item;
            }
        });
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnReject = itemButton.Reject_item;
            }
        });
        yes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnYes = itemButton.yes_ans;
            }
        });
        no.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnNo = itemButton.no_ans;
            }
        });

        // Text Panel
        db.getContentPane().add(panel, BorderLayout.NORTH);
        panel.setSize(400,300);
        panel.add(txt);

        // Accept Reject Panel
        db.getContentPane().add(ARPanel, BorderLayout.PAGE_END);
        ARPanel.setSize(400,100);
        ARPanel.add(btn1);
        ARPanel.add(btn2);

        // Panel for Y N
        db.getContentPane().add(YNPanel, BorderLayout.CENTER);
        YNPanel.setSize(400,100);
        YNPanel.add(yes);
        YNPanel.add(no);

        db.setTitle("A8 Rooms EC22819");
        db.setSize(400,500);
        db.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        db.setVisible(true);

        out = ps;
        in = new Scanner(is);
        purse = 0;
        items = new Item[1000];
        next = 0;
    }

    private static final char[] yOrN = {'y', 'n'};

    public void tell(String m) {
        txt.append(m + "\n");
    }

    public char getChoice(String d, char[] a) {
        tell(d);
        if (!in.hasNextLine()) {
            tell("'No line' error");
            return '?';
        }
        String t = in.nextLine();
        if (t.length() > 0)
            return t.charAt(0);
        else {
            if (a.length > 0) {
                tell("Returning " + a[0]);
                return a[0];
            } else {
                tell("Returning '?'");
                return '?';
            }
        }
    }

    public boolean giveItem(Item x) {
        tell("You have: ");
        for (int i = 0; i < next; i++) tell(items[i] + ", ");
        tell("You are being offered: " + x.name);
        if (next >= items.length) {
            tell("But you have no space and must decline.");
            return false;
        }

        btnReject = itemButton.Waiting;
        btnAccept = itemButton.Waiting;
        
        while (btnAccept == itemButton.Waiting && btnReject == itemButton.Waiting) {
            db.repaint();
        }

        if (btnAccept == itemButton.Accept_item) {
            items[next] = x;
            next++;
            return true;
        } else if (btnReject == itemButton.Reject_item) {
            return false;
        } else return false;
    }

    public boolean hasIdenticalItem(Item x) {
        for (int i = 0; i < next; i++)
            if (x == items[i])
                return true;
        return false;
    }

    public boolean hasEqualItem(Item x) {
        for (int i = 0; i < next; i++)
            if (x.equals(items[i]))
                return true;
        return false;
    }

    public void giveGold(int n) {
        tell("You are given " + n + " gold pieces.");
        purse += n;
        tell("You now have " + purse + " pieces of gold.");
    }

    public int takeGold(int n) {

        if (n < 0) {
            tell("A scammer tried to put you in debt to the tune off " + (-n) + "pieces of gold,");
            tell("but you didn't fall for it and returned the 'loan'.");
            return 0;
        }

        int t = 0;
        if (n > purse) t = purse;
        else t = n;

        tell(t + " pieces of gold are taken from you.");
        purse -= t;
        tell("You now have " + purse + " pieces of gold.");

        return t;
    }

    public boolean quitLoop(int counter) {
        btnYes = itemButton.Waiting;
        btnNo = itemButton.Waiting;

        while (btnYes == itemButton.Waiting && btnNo == itemButton.Waiting ) {
            db.repaint();
        }
        if (counter == 0) {
            return false;
        } else if (btnYes == itemButton.yes_ans ) {
            return true;
        } else if (btnNo == itemButton.no_ans) {
            return false;
        } else {
            tell("Please enter (y/n)");
            return true;
        }
    }
}
