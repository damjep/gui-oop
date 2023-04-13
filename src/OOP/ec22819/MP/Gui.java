package OOP.ec22819.MP;

import javax.swing.*;

public class Gui {

    // Intellij IDEA Default Form
    static JFrame frame;
    public JButton acceptButton;
    public JButton rejectButton;
    private JButton aButton;
    private JButton bButton;
    private JButton cButton;
    private JButton dButton;
    public JPanel panel;
    public JPanel panelTxt;
    public JTextArea txt;

    // Gui Form Constructor
    public Gui() {
        createUIComponents();
    }

    public void createUIComponents() {
        // TODO: place custom component creation code here
        panelTxt.getAccessibleContext().getAccessibleComponent(frame);
        panelTxt.add(txt);
    }

}
