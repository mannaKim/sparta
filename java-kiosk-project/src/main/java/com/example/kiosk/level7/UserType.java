package com.example.kiosk.level7;

import com.example.kiosk.exceptions.InvalidMenuSelectionException;

public enum UserType {
    NATIONAL_MERIT(1, "국가유공자", 0.1),
    MILITARY(2, "군인", 0.05),
    STUDENT(3, "학생", 0.03),
    GENERAL(4, "일반", 0.0);

    private final int discountMenuNumber;
    private final String discountMenuName;
    private final double discountRate;

    UserType (int discountMenuNumber, String discountMenuName, double discountRate) {
        this.discountMenuNumber = discountMenuNumber;
        this.discountMenuName = discountMenuName;
        this.discountRate = discountRate;
    }

    public int getDiscountMenuNumber() {
        return discountMenuNumber;
    }

    public String getDiscountMenuName() {
        return discountMenuName;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public static void printDiscountInformation() {
        StringBuilder sb = new StringBuilder();
        for (UserType type : UserType.values()) {
            String discountInfoString = String.format(
                    "%d. %-5s\t: %d%%\n",
                    type.discountMenuNumber,
                    type.discountMenuName,
                    (int) Math.round(type.discountRate * 100)
            );
            sb.append(discountInfoString);
        }
        System.out.print(sb);
    }

    public static UserType selectedUserType(int inputNumber) throws InvalidMenuSelectionException {
        for (UserType type : UserType.values()) {
            if (type.discountMenuNumber == inputNumber) {
                return type;
            }
        }
        throw new InvalidMenuSelectionException(NATIONAL_MERIT.discountMenuNumber, GENERAL.discountMenuNumber);
    }
}
