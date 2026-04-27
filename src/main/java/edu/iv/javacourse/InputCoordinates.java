package edu.iv.javacourse;

import java.util.Scanner;

public class InputCoordinates {

    Scanner scanner = new Scanner(System.in);

    public Coordinates input() {
        while (true) {
            System.out.println("Please, enter coordinates? ex. a1");
            String line = scanner.nextLine();
            if (line.length() != 2) {
                System.out.println("Invalid format coordinates");
                continue;
            }

            char fileChar = line.charAt(0);
            char rankChar = line.charAt(1);
            if (!Character.isLetter(fileChar) || !Character.isDigit(rankChar) ||
                    !comparisonRank(rankChar)) {
                System.out.println("Invalid format coordinates");
                continue;
            }

            if (comparisonFile(fileChar) == '0') {
                System.out.println("Invalid format coordinates");
                continue;
            }

            return new Coordinates(
                    File.valueOf(String.valueOf(fileChar).toUpperCase()),
                    Character.getNumericValue(rankChar));
        }
    }

    private boolean comparisonRank(char rankChar) {
        for (char ch = '1'; ch <= '8'; ch++) {
            if (ch == rankChar) {
                return true;
            }
        }
        return false;
    }

    private char comparisonFile(char fileChar) {
        for (char ch = 'a'; ch <= 'h'; ch++) {
            if (ch == fileChar) {
                return fileChar;
            }
        }

        for (char ch = 'A'; ch <= 'H'; ch++) {
            if (ch == fileChar) {
                return fileChar;
            }
        }

        return '0';
    }
}
