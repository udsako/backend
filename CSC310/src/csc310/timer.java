/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package csc310;

import java.util.Date;

/**
 *
 * @author HP
 */
public class timer implements Runnable {
    @Override
    public void run() {
        try {
            for (int i = 0; i < 3; i--) {
                wordgame.jLabel6.setText(new Date().toString());
                Thread.sleep(10);
            }
        } catch (Exception e) {
    }
    
}
}
