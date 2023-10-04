/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tec02.main;

import com.tec02.SystemTray.MySystemTray;
import com.tec02.API.APIController;
import com.tec02.API.RestAPI;
import com.tec02.view.View;
import java.awt.AWTException;
import java.net.URL;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrator
 */
public class core {

    private final RestAPI api;
    private final View view;
    private final MySystemTray systemTray;
    private final String title;
    private final URL iconPath;

    public core(String title) throws Exception {
        this.api = APIController.getInstance();
        this.title = title;
        this.iconPath = getClass().getResource("/icon.png");
        this.view = new View(api, this.iconPath);
        this.api.setComponent(view);
        this.systemTray = new MySystemTray(view);
        this.systemTray.addMenuItem("Exit", null, (e) -> {
            if (JOptionPane.showConfirmDialog(null,
                    "You are sure to turn off the program?", "Warning",
                    JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                System.exit(0);
            }
        });
    }

    public void run() throws AWTException {
        if (!systemTray.initTrayIcon(iconPath, title)) {
            JOptionPane.showMessageDialog(null, "SystemTray not support!");
        }
        view.display(title);
        systemTray.apply();
    }
}
