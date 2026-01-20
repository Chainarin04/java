# ShippingCalculator - Flowchart และ Pseudo Code

## Flowchart (Mermaid)

### 1. Main Program Flowchart

```mermaid
graph TB
    A[เริ่มต้นโปรแกรม] --> B[แสดงหน้าจอต้อนรับ]
    B --> C[รับข้อมูลน้ำหนักสินค้า]
    C --> D{น้ำหนัก > 0?}
    D -->|ไม่| E[แสดงข้อผิดพลาด]
    E --> F[จบโปรแกรม]
    D -->|ใช่| G[รับข้อมูลปลายทาง]
    G --> H[รับข้อมูลบริการด่วน]
    H --> I[รับข้อมูลสมาชิก VIP]
    I --> J[เรียก printShippingDetails]
    J --> K[แสดงตัวอย่างการคำนวณ]
    K --> F
```

### 2. calculateBaseShipping Flowchart

```mermaid
graph TB
    A[เริ่มต้น calculateBaseShipping] --> B{น้ำหนัก <= 0?}
    B -->|ใช่| C[throw IllegalArgumentException]
    B -->|ไม่| D{ปลายทาง = กรุงเทพฯ?}
    D -->|ใช่| E{น้ำหนัก <= 1?}
    D -->|ไม่| F{น้ำหนัก <= 1?}
    E -->|ใช่| G[return 40]
    E -->|ไม่| H{น้ำหนัก <= 3?}
    H -->|ใช่| I[return 60]
    H -->|ไม่| J{น้ำหนัก <= 5?}
    J -->|ใช่| K[return 80]
    J -->|ไม่| L[return 100]
    F -->|ใช่| M[return 60]
    F -->|ไม่| N{น้ำหนัก <= 3?}
    N -->|ใช่| O[return 90]
    N -->|ไม่| P{น้ำหนัก <= 5?}
    P -->|ใช่| Q[return 120]
    P -->|ไม่| R[return 150]
```

### 3. calculateTotalShipping Flowchart

```mermaid
graph TB
    A[เริ่มต้น calculateTotalShipping] --> B[total = calculateBaseShipping]
    B --> C{เลือกบริการด่วน?}
    C -->|ใช่| D[total = total + 30]
    C -->|ไม่| E{เป็นสมาชิก VIP?}
    D --> E
    E -->|ใช่| F[total = total x 0.80]
    E -->|ไม่| G[return total]
    F --> G
```

### 4. printShippingDetails Flowchart

```mermaid
graph TB
    A[เริ่มต้น printShippingDetails] --> B[แสดงหัวข้อ]
    B --> C[baseShipping = calculateBaseShipping]
    C --> D[แสดงข้อมูลน้ำหนัก ปลายทาง]
    D --> E[แสดงค่าจัดส่งพื้นฐาน]
    E --> F{เลือกบริการด่วน?}
    F -->|ใช่| G[แสดงค่าบริการด่วน +30]
    F -->|ไม่| H[คำนวณ subtotal]
    G --> H
    H --> I{เป็นสมาชิก VIP?}
    I -->|ใช่| J[แสดงส่วนลด 20%]
    I -->|ไม่| K[total = calculateTotalShipping]
    J --> K
    K --> L[แสดงค่าจัดส่งรวม]
    L --> M[จบ]
```

### 5. Overall System Flowchart

```mermaid
graph TB
    subgraph Input
        A1[น้ำหนัก kg]
        A2[ปลายทาง]
        A3[บริการด่วน]
        A4[สมาชิก VIP]
    end
    
    subgraph Processing
        B1[ตรวจสอบน้ำหนัก]
        B2[คำนวณค่าจัดส่งพื้นฐาน]
        B3[เพิ่มค่าบริการด่วน]
        B4[คำนวณส่วนลด VIP]
    end
    
    subgraph Output
        C1[แสดงรายละเอียด]
        C2[ค่าจัดส่งรวม]
    end
    
    A1 --> B1
    A2 --> B2
    B1 --> B2
    A3 --> B3
    B2 --> B3
    A4 --> B4
    B3 --> B4
    B4 --> C1
    C1 --> C2
```

---

## Pseudo Code

### 1. Main Program

```
PROGRAM ShippingCalculator

BEGIN
    DISPLAY "ระบบคำนวณค่าจัดส่งสินค้า"
    DISPLAY "Shipping Cost Calculator"
    
    TRY
        DISPLAY "กรุณาใส่น้ำหนักสินค้า (กิโลกรัม): "
        INPUT weight
        
        IF weight <= 0 THEN
            DISPLAY "ข้อผิดพลาด: น้ำหนักต้องมากกว่า 0"
            EXIT
        ENDIF
        
        DISPLAY "ปลายทาง (1 = กรุงเทพฯ/ปริมณฑล, 2 = ต่างจังหวัด): "
        INPUT destination
        SET isBangkok = (destination == 1)
        
        DISPLAY "ต้องการบริการด่วน? (1 = ใช่, 0 = ไม่): "
        INPUT expressChoice
        SET isExpress = (expressChoice == 1)
        
        DISPLAY "เป็นสมาชิก VIP? (1 = ใช่, 0 = ไม่): "
        INPUT vipChoice
        SET isVIP = (vipChoice == 1)
        
        CALL printShippingDetails(weight, isBangkok, isExpress, isVIP)
        
    CATCH Exception
        DISPLAY "ข้อผิดพลาด: กรุณาใส่ข้อมูลที่ถูกต้อง"
    ENDTRY
    
    // แสดงตัวอย่างการคำนวณ
    DISPLAY "--- ตัวอย่างการคำนวณ ---"
    DISPLAY "1. สินค้า 2 กก. ส่งกรุงเทพฯ = " + calculateTotalShipping(2, true, false, false)
    DISPLAY "2. สินค้า 4 กก. ส่งต่างจังหวัด ด่วน = " + calculateTotalShipping(4, false, true, false)
    DISPLAY "3. สินค้า 6 กก. ส่งกรุงเทพฯ ด่วน VIP = " + calculateTotalShipping(6, true, true, true)
    
END PROGRAM
```

### 2. calculateBaseShipping Function

```
FUNCTION calculateBaseShipping(weight, isBangkok)
    
    // ตรวจสอบน้ำหนัก
    IF weight <= 0 THEN
        THROW "น้ำหนักต้องมากกว่า 0 กิโลกรัม"
    ENDIF
    
    // กำหนดอัตราค่าจัดส่ง
    IF isBangkok == TRUE THEN
        // กรุงเทพฯ และปริมณฑล
        IF weight <= 1 THEN
            RETURN 40
        ELSE IF weight <= 3 THEN
            RETURN 60
        ELSE IF weight <= 5 THEN
            RETURN 80
        ELSE
            RETURN 100
        ENDIF
    ELSE
        // ต่างจังหวัด
        IF weight <= 1 THEN
            RETURN 60
        ELSE IF weight <= 3 THEN
            RETURN 90
        ELSE IF weight <= 5 THEN
            RETURN 120
        ELSE
            RETURN 150
        ENDIF
    ENDIF
    
END FUNCTION
```

### 3. calculateTotalShipping Function

```
FUNCTION calculateTotalShipping(weight, isBangkok, isExpress, isVIP)
    
    // คำนวณค่าจัดส่งพื้นฐาน
    SET total = calculateBaseShipping(weight, isBangkok)
    
    // เพิ่มค่าบริการด่วน (ถ้าเลือก)
    IF isExpress == TRUE THEN
        SET total = total + 30
    ENDIF
    
    // ลดราคาสำหรับสมาชิก VIP (20%)
    IF isVIP == TRUE THEN
        SET total = total * 0.80
    ENDIF
    
    RETURN total
    
END FUNCTION
```

### 4. printShippingDetails Function

```
FUNCTION printShippingDetails(weight, isBangkok, isExpress, isVIP)
    
    DISPLAY "========================================"
    DISPLAY "       รายละเอียดค่าจัดส่งสินค้า"
    DISPLAY "========================================"
    
    // คำนวณค่าจัดส่งพื้นฐาน
    SET baseShipping = calculateBaseShipping(weight, isBangkok)
    
    // แสดงข้อมูลที่รับเข้า
    DISPLAY "น้ำหนักสินค้า: " + weight + " กิโลกรัม"
    
    IF isBangkok == TRUE THEN
        DISPLAY "ปลายทาง: กรุงเทพฯ และปริมณฑล"
    ELSE
        DISPLAY "ปลายทาง: ต่างจังหวัด"
    ENDIF
    
    IF isExpress == TRUE THEN
        DISPLAY "บริการด่วน: ใช่"
    ELSE
        DISPLAY "บริการด่วน: ไม่"
    ENDIF
    
    IF isVIP == TRUE THEN
        DISPLAY "สมาชิก VIP: ใช่"
    ELSE
        DISPLAY "สมาชิก VIP: ไม่"
    ENDIF
    
    DISPLAY "----------------------------------------"
    DISPLAY "ค่าจัดส่งพื้นฐาน: " + baseShipping + " บาท"
    
    // แสดงค่าบริการด่วน (ถ้ามี)
    IF isExpress == TRUE THEN
        DISPLAY "ค่าบริการด่วน: +30 บาท"
    ENDIF
    
    // คำนวณยอดรวมก่อนลด
    SET subtotal = baseShipping
    IF isExpress == TRUE THEN
        SET subtotal = subtotal + 30
    ENDIF
    
    // แสดงส่วนลด VIP (ถ้ามี)
    IF isVIP == TRUE THEN
        SET discount = subtotal * 0.20
        DISPLAY "ส่วนลด VIP (20%): -" + discount + " บาท"
    ENDIF
    
    // คำนวณและแสดงค่าจัดส่งรวม
    SET total = calculateTotalShipping(weight, isBangkok, isExpress, isVIP)
    DISPLAY "----------------------------------------"
    DISPLAY "ค่าจัดส่งรวม: " + total + " บาท"
    DISPLAY "========================================"
    
END FUNCTION
```

---

## ตารางสรุปตัวแปรและค่าคงที่

| ตัวแปร/ค่าคงที่ | ประเภท | ค่า | คำอธิบาย |
|---------------|--------|-----|---------|
| BKK_RATE_0_1_KG | int | 40 | ค่าส่ง กทม. 0-1 กก. |
| BKK_RATE_1_3_KG | int | 60 | ค่าส่ง กทม. 1-3 กก. |
| BKK_RATE_3_5_KG | int | 80 | ค่าส่ง กทม. 3-5 กก. |
| BKK_RATE_OVER_5_KG | int | 100 | ค่าส่ง กทม. >5 กก. |
| PROVINCIAL_RATE_0_1_KG | int | 60 | ค่าส่งต่างจังหวัด 0-1 กก. |
| PROVINCIAL_RATE_1_3_KG | int | 90 | ค่าส่งต่างจังหวัด 1-3 กก. |
| PROVINCIAL_RATE_3_5_KG | int | 120 | ค่าส่งต่างจังหวัด 3-5 กก. |
| PROVINCIAL_RATE_OVER_5_KG | int | 150 | ค่าส่งต่างจังหวัด >5 กก. |
| EXPRESS_FEE | int | 30 | ค่าบริการด่วน |
| VIP_DISCOUNT_RATE | double | 0.20 | อัตราส่วนลด VIP |
