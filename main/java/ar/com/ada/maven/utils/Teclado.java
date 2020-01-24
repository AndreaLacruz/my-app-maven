package ar.com.ada.maven.utils;

import java.io.IOException;
import java.util.Scanner;

public class Teclado {

    private static Scanner keyboard;

    private Teclado(){}

    public static Scanner getInstance(){
        if (keyboard == null){
            keyboard = new Scanner(System.in);
        }
        return keyboard;
    }

    public static void pressEnterKeyToContinue(){
        System.out.println("Presiona la tecla ENTER para continuar...");
        try{
            System.in.read();

        } catch (IOException e) {
        }
    }
}
