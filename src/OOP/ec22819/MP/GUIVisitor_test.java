package OOP.ec22819.MP;//import java.util.List;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

class GUIVisitor_test extends Gui implements Visitor {

    static enum itemButton {
        Waiting,
        Accept_item,
        Reject_item,
        yes_ans,
        no_ans,
    };

    // Accept Button
    itemButton btnAccept = itemButton.Waiting;

    // Reject Button
    itemButton btnReject = itemButton.Waiting;

    // Y/N button
    itemButton btnYes = itemButton.Waiting;
    itemButton btnNo = itemButton.Waiting;


    final PrintStream out;
    private final Scanner in;
    private int purse;
    private final Item[] items;
    private int next;

    public GUIVisitor_test(PrintStream ps, InputStream is) {
        frame = new JFrame();
        frame.setSize(800,800);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        frame.setContentPane(new Gui().panel);
        frame.pack();
        frame.setTitle("A8 Gui Form");
        frame.setLocationRelativeTo(null);

        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnAccept = itemButton.Accept_item;
            }
        });
        rejectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnReject = itemButton.Reject_item;
            }
        });

        out = ps;
        in = new Scanner(is);
        purse = 0;
        items = new Item[1000];
        next = 0;
    }

    private static final char[] yOrN = {'y', 'n'};

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

    public void tell(String m) {
        txt.append(m + "/n");
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
            frame.repaint();
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
            frame.repaint();
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
