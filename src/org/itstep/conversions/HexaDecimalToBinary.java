package org.itstep.conversions;

import java.util.Scanner;

// Hex [0-9],[A-F] -> Binary [0,1]
public class HexaDecimalToBinary {

    private final int LONG_BITS = 8;

    public void convert(String numHex) {
        // String a HexaDecimal:
        int conHex = Integer.parseInt(numHex, 16);
        // Hex a Binary:
        String binary = Integer.toBinaryString(conHex);
        // Output:
        System.out.println(numHex + " = " + completeDigits(binary));
    }

    public String completeDigits(String binNum) {
        for (int i = binNum.length(); i < LONG_BITS; i++) {
            binNum = "0" + binNum;
        }
        return binNum;
    }

    public static void main(String[] args) {

        // Testing Numbers:
        Scanner sc = new Scanner(System.in);
        System.out.print("Decimal number: ");
        String hex = sc.nextLine();
        HexaDecimalToBinary objConvert = new HexaDecimalToBinary();

        objConvert.convert(hex);

    }
}
