package ar.com.ada.maven.controller;

import ar.com.ada.maven.view.MainView;

public class MainController {

    private static MainView view = new MainView();

    public static void run(){
        Boolean des = false;
        while (!des){
            int opcion = view.selectOption();
            switch (opcion){
                case 1: ContinentController.init();
                break;
                case 2: des = true;
                break;
                default:
                    System.out.println("DEBE ELEJIR UNA OPCION VALIDA");
            }
        }
    }
}
