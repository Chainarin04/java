import java.util.Scanner;

/**
 * ระบบคำนวณค่าจัดส่งสินค้า (Shipping Cost Calculator)
 * - คำนวณตามปลายทาง (กรุงเทพฯ/ปริมณฑล หรือ ต่างจังหวัด)
 * - คำนวณตามน้ำหนัก (4 ระดับ)
 * - บริการด่วน (Express) +30 บาท
 * - ส่วนลดสมาชิก VIP 20%
 */
public class ShippingCalculator {
    
    // ค่าจัดส่งสำหรับกรุงเทพฯ และปริมณฑล (บาท)
    private static final int BKK_RATE_0_1_KG = 40;
    private static final int BKK_RATE_1_3_KG = 60;
    private static final int BKK_RATE_3_5_KG = 80;
    private static final int BKK_RATE_OVER_5_KG = 100;
    
    // ค่าจัดส่งสำหรับต่างจังหวัด (บาท)
    private static final int PROVINCIAL_RATE_0_1_KG = 60;
    private static final int PROVINCIAL_RATE_1_3_KG = 90;
    private static final int PROVINCIAL_RATE_3_5_KG = 120;
    private static final int PROVINCIAL_RATE_OVER_5_KG = 150;
    
    // ค่าบริการเพิ่มเติม
    private static final int EXPRESS_FEE = 30;
    private static final double VIP_DISCOUNT_RATE = 0.20;
    
    /**
     * คำนวณค่าจัดส่งตามน้ำหนักและปลายทาง
     * @param weight น้ำหนักสินค้า (กิโลกรัม)
     * @param isBangkok true = กรุงเทพฯ/ปริมณฑล, false = ต่างจังหวัด
     * @return ค่าจัดส่งพื้นฐาน (บาท)
     */
    public static int calculateBaseShipping(double weight, boolean isBangkok) {
        if (weight <= 0) {
            throw new IllegalArgumentException("น้ำหนักต้องมากกว่า 0 กิโลกรัม");
        }
        
        if (isBangkok) {
            // กรุงเทพฯ และปริมณฑล
            if (weight <= 1) {
                return BKK_RATE_0_1_KG;
            } else if (weight <= 3) {
                return BKK_RATE_1_3_KG;
            } else if (weight <= 5) {
                return BKK_RATE_3_5_KG;
            } else {
                return BKK_RATE_OVER_5_KG;
            }
        } else {
            // ต่างจังหวัด
            if (weight <= 1) {
                return PROVINCIAL_RATE_0_1_KG;
            } else if (weight <= 3) {
                return PROVINCIAL_RATE_1_3_KG;
            } else if (weight <= 5) {
                return PROVINCIAL_RATE_3_5_KG;
            } else {
                return PROVINCIAL_RATE_OVER_5_KG;
            }
        }
    }
    
    /**
     * คำนวณค่าจัดส่งรวมทั้งหมด
     * @param weight น้ำหนักสินค้า (กิโลกรัม)
     * @param isBangkok true = กรุงเทพฯ/ปริมณฑล, false = ต่างจังหวัด
     * @param isExpress true = บริการด่วน
     * @param isVIP true = สมาชิก VIP
     * @return ค่าจัดส่งรวม (บาท)
     */
    public static double calculateTotalShipping(double weight, boolean isBangkok, 
                                                 boolean isExpress, boolean isVIP) {
        // คำนวณค่าจัดส่งพื้นฐาน
        double total = calculateBaseShipping(weight, isBangkok);
        
        // เพิ่มค่าบริการด่วน (ถ้ามี)
        if (isExpress) {
            total += EXPRESS_FEE;
        }
        
        // ลดราคาสำหรับสมาชิก VIP (20%)
        if (isVIP) {
            total = total * (1 - VIP_DISCOUNT_RATE);
        }
        
        return total;
    }
    
    /**
     * แสดงรายละเอียดการคำนวณ
     */
    public static void printShippingDetails(double weight, boolean isBangkok, 
                                            boolean isExpress, boolean isVIP) {
        System.out.println("\n========================================");
        System.out.println("       รายละเอียดค่าจัดส่งสินค้า");
        System.out.println("========================================");
        
        int baseShipping = calculateBaseShipping(weight, isBangkok);
        
        System.out.printf("น้ำหนักสินค้า: %.2f กิโลกรัม%n", weight);
        System.out.println("ปลายทาง: " + (isBangkok ? "กรุงเทพฯ และปริมณฑล" : "ต่างจังหวัด"));
        System.out.println("บริการด่วน: " + (isExpress ? "ใช่" : "ไม่"));
        System.out.println("สมาชิก VIP: " + (isVIP ? "ใช่" : "ไม่"));
        System.out.println("----------------------------------------");
        System.out.printf("ค่าจัดส่งพื้นฐาน: %d บาท%n", baseShipping);
        
        if (isExpress) {
            System.out.printf("ค่าบริการด่วน: +%d บาท%n", EXPRESS_FEE);
        }
        
        double subtotal = baseShipping + (isExpress ? EXPRESS_FEE : 0);
        
        if (isVIP) {
            double discount = subtotal * VIP_DISCOUNT_RATE;
            System.out.printf("ส่วนลด VIP (20%%): -%.2f บาท%n", discount);
        }
        
        double total = calculateTotalShipping(weight, isBangkok, isExpress, isVIP);
        System.out.println("----------------------------------------");
        System.out.printf("ค่าจัดส่งรวม: %.2f บาท%n", total);
        System.out.println("========================================\n");
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║    ระบบคำนวณค่าจัดส่งสินค้า           ║");
        System.out.println("║    Shipping Cost Calculator            ║");
        System.out.println("╚════════════════════════════════════════╝");
        
        try {
            // รับข้อมูลน้ำหนัก
            System.out.print("\nกรุณาใส่น้ำหนักสินค้า (กิโลกรัม): ");
            double weight = scanner.nextDouble();
            
            if (weight <= 0) {
                System.out.println("ข้อผิดพลาด: น้ำหนักต้องมากกว่า 0");
                return;
            }
            
            // รับข้อมูลปลายทาง
            System.out.print("ปลายทาง (1 = กรุงเทพฯ/ปริมณฑล, 2 = ต่างจังหวัด): ");
            int destination = scanner.nextInt();
            boolean isBangkok = (destination == 1);
            
            // รับข้อมูลบริการด่วน
            System.out.print("ต้องการบริการด่วน? (1 = ใช่, 0 = ไม่): ");
            int expressChoice = scanner.nextInt();
            boolean isExpress = (expressChoice == 1);
            
            // รับข้อมูลสมาชิก VIP
            System.out.print("เป็นสมาชิก VIP? (1 = ใช่, 0 = ไม่): ");
            int vipChoice = scanner.nextInt();
            boolean isVIP = (vipChoice == 1);
            
            // แสดงผลลัพธ์
            printShippingDetails(weight, isBangkok, isExpress, isVIP);
            
        } catch (Exception e) {
            System.out.println("ข้อผิดพลาด: กรุณาใส่ข้อมูลที่ถูกต้อง");
        } finally {
            scanner.close();
        }
        
        // ตัวอย่างการใช้งานแบบ programmatic
        System.out.println("\n--- ตัวอย่างการคำนวณ ---");
        System.out.println("1. สินค้า 2 กก. ส่งกรุงเทพฯ ปกติ ไม่ใช่ VIP = " 
            + calculateTotalShipping(2, true, false, false) + " บาท");
        System.out.println("2. สินค้า 4 กก. ส่งต่างจังหวัด ด่วน ไม่ใช่ VIP = " 
            + calculateTotalShipping(4, false, true, false) + " บาท");
        System.out.println("3. สินค้า 6 กก. ส่งกรุงเทพฯ ด่วน VIP = " 
            + calculateTotalShipping(6, true, true, true) + " บาท");
    }
}
