/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tec02.main;

import com.sun.jna.platform.win32.WinDef.HWND;
import com.tec02.common.Keyword;
import com.tec02.common.PropertiesModel;
import com.tec02.communication.socket.Unicast.Client.Client;
import com.tec02.user32.User32Util;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrator
 */
public class main {
    
    public static void main(String[] args) {
        String title = "AmbitAppStore";
        if (!new User32Util().findProcess(title).isEmpty()) {
            Client client = new Client("localhost", 
                    PropertiesModel.getInteger(Keyword.Socket.PORT, 5000), 
                    null);
            if(client.connect()){
                client.send("show");
            }else{
                JOptionPane.showMessageDialog(null, "Program is running!");
            }
            return;
        }
        try {
            new core(title).run();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
