package ar.com.ada.maven.view;

import ar.com.ada.maven.utils.Teclado;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainView {

    public Integer selectOption(){
        System.out.println("Base de datos: ZOOLOGICO");
        System.out.println("--- Ingrese 1 si quiere ingresar a la lista de Continentes ---\n");
        System.out.println("-- Ingrese 0 si quiere salir de la Base de datos --");
        Scanner sc = Teclado.getInstance();

        while(true){
            try {
                int choice = sc.nextInt();
                return choice;
            } catch (InputMismatchException e){
                System.out.println("* Debe elejir entre las opciones mostradas *");
                sc.next();
            }

        }
    }

}
