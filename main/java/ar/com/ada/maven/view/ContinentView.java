package ar.com.ada.maven.view;

import ar.com.ada.maven.DAO.ContinentDAO;
import ar.com.ada.maven.DTO.ContinentDTO;
import ar.com.ada.maven.utils.Ansi;
import ar.com.ada.maven.utils.Teclado;

import javax.sound.midi.Soundbank;
import javax.swing.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ContinentView {

    public int continentMenuSelectOption() {
        System.out.println("Bienvenido al Modulo de Continente");
        System.out.println("Seleccione una opción -> \n" + "1-. Lista \n" + "2-. Agregar \n" + "3-. Editar \n" + "4-. Borrar \n"
                + "5-. Salir");

        Scanner sc = Teclado.getInstance();

        while (true) {
            try {
                int choice = sc.nextInt();
                return choice;
            } catch (InputMismatchException e) {
                System.out.println("* Debe ser elejir entre las opciones mostradas *");
                sc.next();
            }

        }

    }

    public void printAllContinents(List<ContinentDTO> continents) {
        System.out.println(" <- Los Continentes Listados son los siguiente -> ");
        for (ContinentDTO continent : continents) {
            System.out.println(continent);
        }

    }

    public String getNameNewContinent() {
        System.out.println("| Bienvenido al Creador de ingresos a la Base de datos |");
        System.out.println(" Ingrese un nombre : [INGRESAR DATOS VALIDOS O SE CANCELA EL PROCESO]");
        Scanner sc = Teclado.getInstance();
        sc.nextLine();

        while (true) {
            try {
                String name = sc.nextLine().trim();
                while (!name.matches("^[A-Za-záéíóúüÁÉÍÓÚÜ\\\\s]+$") && !name.isEmpty()) {
                    System.out.println("ERROR MESSAGE || INGRESAR DATOS VALIDOS\n");
                    name = sc.nextLine();
                }
                return name;
            } catch (InputMismatchException e) {
                System.out.println("ERROR MESSAGE || INGRESAR DATOS VALIDOS\n");
                sc.next();
            }
        }
    }

    public void showNewContinent(String continent) {
        System.out.println("- Nuevo Continente ingresado -\n");
    }

    public void newContinentCanceled() {
        System.out.println("- EL DATO ANTERIOR SE HA CANCELADO -\n");
    }

    public void continentAlreadyExists(String name) {
        System.out.println("- Éste Continente ya EXISTE - \n");
    }

    public String printContinentsPerPage(final List<ContinentDTO> continents, List<String> paginator, boolean delete) {
        System.out.println("\n+-------------------------------------------------------------------------------+");
        System.out.println("\t  ZOOLOGICO :: Modulo Continente :: Lista Continente");
        System.out.println("\n+-------------------------------------------------------------------------------+");

        System.out.println("ID\t|\tCONTINENTE");
        continents.forEach(continentDTO -> {
            System.out.println(continentDTO.getId() + "\t|\t" + continentDTO.getName());
        });

        System.out.println("\n+-------------------------------------------------------------------------------+");
        paginator.forEach(page -> System.out.print(page + " "));
        if (delete){
            System.out.println("[ELIMINAR]");
        } else {
            System.out.print("[EDITAR] ");
        }
        System.out.print("[Q para salir]");
        System.out.println("\n+-------------------------------------------------------------------------------+");

        Scanner tc = Teclado.getInstance();
        tc.nextLine();

        while (true) {
            try {
                System.out.println("? ");
                String name = tc.nextLine().trim();
                while (!name.matches("^[0-9IiAaSsUuEe]+$") && !name.isEmpty()) {
                    System.out.println("ERROR MESSAGE || INGRESAR DATOS VALIDOS\n ");
                    name = tc.nextLine();
                }

                return name;
            } catch (InputMismatchException e) {
                System.out.println("ERROR MESSAGE || INGRESAR DATOS VALIDOS\n");
                tc.next();
            }
        }
    }

    public int continentIdSelected(String action) {
        System.out.println("Ingrese el ID del continente para " + action + " o 0 para cancelar \n");
        Scanner tc = Teclado.getInstance();
        while (true) {
            try {
                System.out.println(" ?");
                int choice = tc.nextInt();
                return choice;
            } catch (InputMismatchException e) {
                System.out.println("ERROR | DEBE INGRESAR UN ID CORRECTO");
                tc.next();
            }
        }
    }

    public static String getNameUpdate(ContinentDTO continent) {
        System.out.println("ACTUALIZACION DEL NOMBRE DEL SIGUIENTE CONTINENTE: ");
        System.out.println(Ansi.PURPLE + continent.getId() + "\t" + continent.getName() + Ansi.RESET);
        System.out.println("Ingrese el nombre del continente a actualizar ");
        System.out.println("para cancelar, no agregar datos y presionar enter \n");
        Scanner tc = Teclado.getInstance();
        tc.nextLine();

        while (true) {
            try {
                System.out.println("? ");
                String name = tc.nextLine().trim();
                while (!name.matches("^[A-Za-záéíóúüÁÉÍÓÚÜ\\s]+$") && !name.isEmpty()) {
                    System.out.println("ERROR | DEBE INGRESAR UN DATO VALIDO");
                    name = tc.nextLine();
                }
                return name;
            } catch (InputMismatchException e) {
                System.out.println("ERROR | DEBE INGRESAR UN DATO VALIDO");
                tc.next();
            }
        }
    }

    public void continentNotExist(int id) {
        System.out.println("NO EXISTE el continente con el id: " + id + " asociado");
        System.out.println("Selecciona un ID valido o 0 para continuar");
    }

    public void updateContinentCanceled() {
        System.out.println("Ha cancelado la actualizacion de siguiente continente \n");
        Teclado.pressEnterKeyToContinue();
    }

    public void showUpdateContinent(String name) {
        System.out.println("El continente " + name + "se ha actualizado exitosamente");
        Teclado.pressEnterKeyToContinue();
    }

    public static String getIdDelete(ContinentDTO continent) {
        System.out.println("SE ELIMINARA EL SIGUIENTE CONTINENTE: ");
        System.out.println(Ansi.PURPLE + continent.getId() + "\t" + continent.getName() + Ansi.RESET);
        System.out.println("ESTA SEGURO DE REALIZAR LA SIGUIENTE OPERACION");
        System.out.println("\n+--------+");
        System.out.println("- SI -");
        System.out.println("- NO -");
        System.out.println("\n+--------+");
        Scanner tc = Teclado.getInstance();
        String respuesta = tc.nextLine();
         if (respuesta.equals("SI")) {
             ContinentDAO delete = new ContinentDAO();
         }

        while (true) {
            try {
                System.out.println("? ");
                String id = tc.nextLine();

                while (!id.matches("^[0-9\\s]+$") && !id.isEmpty()) {
                    System.out.println(Ansi.RED + "ERROR | DEBE INGRESAR UN DATO VALIDO" + Ansi.RESET);
                    id = tc.nextLine();
                }
                return id;

            } catch (InputMismatchException e) {
                System.out.println(Ansi.RED + "ERROR | DEBE INGRESAR UN DATO VALIDO"+ Ansi.RESET);
                tc.next();
            }
        }
    }
}