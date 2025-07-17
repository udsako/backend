/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package csc310;

import java.awt.Color;

/**
 *
 * @author HP
 */
public class blink implements Runnable {
    @Override
    public void run() {
        try {
            for (;;) {
                wordgame.jLabel6.setForeground(Color.red);
                Thread.sleep(600);
                wordgame.jLabel6.setForeground(Color.green);
                Thread.sleep(600);
                wordgame.jLabel6.setForeground(Color.blue);
                Thread.sleep(600);
                wordgame.jLabel6.setForeground(Color.white);
                Thread.sleep(600);
                wordgame.jLabel6.setForeground(Color.black);
                Thread.sleep(600);
                wordgame.jLabel6.setForeground(Color.cyan);
                Thread.sleep(600);
                wordgame.jLabel6.setForeground(Color.orange);
                Thread.sleep(600);
            }
        } catch (Exception e) {
    }
    
}
}
