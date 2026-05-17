package org.dm;

import java.util.Scanner;

public class Input {

    private final Scanner scanner = new Scanner(System.in);

    public int inputValue(String value){
        System.out.println("Введите значение для параметра: " + value);
        while (scanner.hasNext()){
            int input = scanner.nextInt();
            if (input > 0){
//                System.out.println("Выбрано значение: " + input);
                return input;
            } else {
                System.out.println("Значение должно быть больше нуля");
            }

        }
        throw new IllegalArgumentException();
    }
}
