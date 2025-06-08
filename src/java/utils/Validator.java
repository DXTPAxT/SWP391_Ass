/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

/**
 *
 * @author PC ASUS
 */
public class Validator {
    // Validates a phone number (Vietnamese format: 10 or 11 digits, starts with 0)
    public static boolean isValidPhoneNumber(String phoneNumber) {
        if (phoneNumber == null) return false;
        return phoneNumber.matches("0\\d{9,10}");
    }
    
    // Helper method to check if a String is null or empty (now static for reuse)
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    private Validator() {
        // Prevent instantiation
    }
}
