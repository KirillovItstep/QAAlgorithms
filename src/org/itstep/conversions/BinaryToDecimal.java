package org.itstep.conversions;

import java.util.Scanner;

/**
 Сложность: 1
 Протестировать конвертеры:
 BinaryToDecimal, BinaryToHexadecimal, DecimalToBinary, DecimalToHexadecimal,
 HexadecimalToBinary, HexadecimalToDecimal, IntegerToRoman, RomanToInteger
 */
class BinaryToDecimal {

    /**
     * Main Method
     *
     * @param args Command line arguments
     */
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int binNum, binCopy, d, s = 0, power = 0;
        System.out.print("Binary number: ");
        binNum = sc.nextInt();
        binCopy = binNum;
        while (binCopy != 0) {
            d = binCopy % 10;
            s += d * (int) Math.pow(2, power++);
            binCopy /= 10;
        }
        System.out.println("Decimal equivalent:" + s);
        sc.close();
    }
}
