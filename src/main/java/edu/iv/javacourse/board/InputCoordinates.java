package edu.iv.javacourse.board;

import java.io.InputStream;
import java.util.Scanner;

public class InputCoordinates {

    private final Scanner scanner;

    public InputCoordinates(InputStream inputStream) {
        this.scanner = new Scanner(inputStream);
    }

    public Coordinates input() {
        while (true) {
            System.out.println("Please, enter coordinates, ex. a1");
            String inputLine = scanner.nextLine().trim().toLowerCase();
            if (!inputLine.matches("[a-h][1-8]")) {
                System.out.println("Invalid format coordinates. Try again.");
                continue;
            }

            File file = File.valueOf(inputLine.substring(0, 1).toUpperCase());
            int rank = Character.getNumericValue(inputLine.charAt(1));

            return new Coordinates(file, rank);
        }
    }
}
