package com.cryptography.ecceg;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GUI {
    private JFrame frame;

    GUI() {
        frame = new JFrame();

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        JButton b = new JButton("click");
        b.setBounds(130,100,100, 40);

        frame.add(b);

        frame.setSize(400,500);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}
