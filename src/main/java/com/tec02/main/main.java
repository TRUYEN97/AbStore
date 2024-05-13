/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tec02.main;

import com.tec02.common.Keyword;
import com.tec02.common.PropertiesModel;
import com.tec02.communication.socket.Unicast.Client.SocketClient;
import com.tec02.user32.User32;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrator
 */
public class main {

    public static void main(String[] args) {
        String title = "AmbitAppStore - Runing";
        if (User32.INSTANCE.FindWindow(0, title) == null) {
            try {
                new core(title).run();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
                System.exit(1);
            }
        } else {
            SocketClient client = new SocketClient("127.0.0.1",
                    PropertiesModel.getInteger(Keyword.Socket.PORT, 5000),
                    null);
            if (client.connect()) {
                client.send("show");
            } else {
                JOptionPane.showMessageDialog(null, "Program is running!");
            }
        }
    }
}
