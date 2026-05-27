package org.dm.simulation.ui;

import java.util.Scanner;

public class Input {

    private final Scanner scanner = new Scanner(System.in);

    public int inputValue(String value){
        while (true){
            System.out.println("Введите значение для параметра: " + value);

            if (scanner.hasNextInt()){
                int input = scanner.nextInt();

                scanner.nextLine();

                if (input > 0){
                System.out.println("Выставлено значение для параметра \"" + value + "\": " + input);
                    return input;
                } else {
                System.out.println("Значение должно быть больше нуля");
            }
        } else {
                String incorrectInput = scanner.next();
                System.out.println(incorrectInput + " не является целым числом");
            }
        }
    }
}
