/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cafeteria;

/**
 *
 * @author HP
 */
public class ReportData {
    private int totalOrders;
    private double totalSales;
    private int totalServed;
    private int totalUnserved;
    private int totalQuantity;

    public ReportData(int totalOrders, double totalSales, int totalServed, int totalUnserved, int totalQuantity) {
        this.totalOrders = totalOrders;
        this.totalSales = totalSales;
        this.totalServed = totalServed;
        this.totalUnserved = totalUnserved;
        this.totalQuantity = totalQuantity;
    }

    // Getters
    public int getTotalOrders() { return totalOrders; }
    public double getTotalSales() { return totalSales; }
    public int getTotalServed() { return totalServed; }
    public int getTotalUnserved() { return totalUnserved; }
    public int getTotalQuantity() { return totalQuantity; }


    
}
