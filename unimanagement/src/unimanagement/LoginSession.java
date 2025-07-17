/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unimanagement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author HP
 */
public class LoginSession {
    public static String username;
    public static String role;
    public static String unit;
    public static String staffID;

    private static final String FILE_PATH = "login.properties";
     
    public static void setLoggedInUser(String username, String role, String unit, String staffID) {
        LoginSession.username = username;
        LoginSession.role = role;
        LoginSession.unit = unit;
        LoginSession.staffID = staffID;

    }

    public static String getLoggedInStaffID() {
        return staffID;
    }
    public static String getLoggedInUsername() {
        return username;
    }

    public static String getLoggedInRole() {
        return role;
    }

    public static String getLoggedInUnit() {
        return unit;
    }
    
    
    // =============== Remember Me Feature ===============

    // Save credentials to local file
    public static void saveCredentials(String username, String password) {
        try (FileOutputStream out = new FileOutputStream(FILE_PATH)) {
            Properties props = new Properties();
            props.setProperty("username", username);
            props.setProperty("password", password); // Optional: Encrypt this
            props.store(out, "Remembered Login Credentials");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load saved credentials
    public static String[] loadCredentials() {
        File file = new File(FILE_PATH);
        if (!file.exists()) return null;

        try (FileInputStream in = new FileInputStream(file)) {
            Properties props = new Properties();
            props.load(in);
            return new String[] {
                props.getProperty("username", ""),
                props.getProperty("password", "")
            };
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    

    // Clear saved credentials
    public static void clearCredentials() {
        File file = new File(FILE_PATH);
        if (file.exists()) {
            file.delete();
        }
    }
}

