package de.gesellix.openjdk.iconified;

import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class MyJframe {

    public static void main(String[] args) {
        final JFrame frm = new JFrame("Test");
        frm.addWindowStateListener(e -> System.out.println("window state changed"));
        frm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frm.setSize(200, 200);
        JPanel panel = new JPanel(new FlowLayout());
        JLabel versionInfo = new JLabel(String.format("%s %s", System.getProperty("java.version"), System.getProperty("java.vendor")));
        panel.add(versionInfo);
        JButton minimize = new JButton("minimize");
        minimize.addActionListener(e -> frm.setExtendedState(frm.getExtendedState() | JFrame.ICONIFIED));
        panel.add(minimize);
        JButton exit = new JButton("exit");
        exit.addActionListener(e -> {
            WindowEvent closingEvent = new WindowEvent(frm, WindowEvent.WINDOW_CLOSING);
            Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closingEvent);
        });
        panel.add(exit);
        frm.getContentPane().add(panel);

        // breaks the "minimize" button
        frm.setUndecorated(true);

        frm.setVisible(true);
    }
}
