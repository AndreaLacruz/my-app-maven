package ar.com.ada.maven.controller;

import ar.com.ada.maven.DAO.ContinentDAO;
import ar.com.ada.maven.DTO.ContinentDTO;
import ar.com.ada.maven.view.ContinentView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ContinentController {

    private static ContinentView view = new ContinentView();
    private static ContinentDAO conDAO = new ContinentDAO();

    private static void continentList() {
        List<ContinentDTO> continentDAOList = conDAO.findAll();
        view.printAllContinents(continentDAOList);
        try {
            System.out.println("-- Presione enter para regresar al menu --");
            System.out.println(" ");
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void init() {
        Boolean des = false;
        while (!des) {
            int menu = view.continentMenuSelectOption();
            switch (menu) {
                case 1:
                    continentList();
                    break;
                case 2:
                    createNewContinent();
                    break;
                case 3:
                    editContinent();
                    break;
                case 4:
                    deleteContinent();
                    break;
                default:
                    System.out.println("| DEBE ESCOJER UNA OPCION VALIDA |");
                    System.out.println("");
            }
        }

    }

    public static void createNewContinent() {
        ContinentView view = ContinentController.view;
        String nameToInsert = view.getNameNewContinent();
        if (!nameToInsert.isEmpty()) {
            ContinentDTO newContinent = new ContinentDTO(nameToInsert);
            ContinentDTO byName = conDAO.findByName(nameToInsert);
            if (byName != null && newContinent.getName().toLowerCase().equals(byName.getName().toLowerCase())) {
                view.continentAlreadyExists(newContinent.getName());
                view.newContinentCanceled();
            } else {
                Boolean saveName = conDAO.save(newContinent);
                if (saveName = true) {
                    view.showNewContinent(newContinent.getName());
                }
            }
        }
    }

    private static List<String> buildPaginator(int currentPage, int totalPages) {
        List<String> pages = new ArrayList<>();
        pages.add("[Inicio]");
        pages.add("[Anterior]");
        for (int i = 1; i <= totalPages; i++) {
            if (i == currentPage + 1)
                pages.add("[>" + i + "<]");
            else
                pages.add("[" + i + "]");
        }
        pages.add("[Siguiente]");
        pages.add("[Ultimo]");

        return pages;
    }

    private static int continentListPerPage() {
        int limit = 3, currentPage = 0;
        List<ContinentDTO> contDTO;
        int numberContinents;
        int totalPages;
        List<String> paginator;
        Boolean shouldGetOut = false;
        while (!shouldGetOut) {
            boolean delete = false;
            contDTO = conDAO.findAll(limit, currentPage * limit);
            numberContinents = conDAO.getTotalContinents();
            totalPages = (int) Math.ceil((double) numberContinents / limit);
            paginator = buildPaginator(currentPage, totalPages);
            String choice = view.printContinentsPerPage(contDTO, paginator, delete);
            switch (choice) {
                case "i":
                case "I":
                    currentPage = 0;
                    break;
                case "a":
                case "A":
                    if (currentPage + 1 > 0)
                        currentPage--;
                    break;
                case "s":
                case "S":
                    if (currentPage + 1 < totalPages) {
                        currentPage++;
                    }
                    break;
                case "u":
                case "U":
                    currentPage = totalPages - 1;
                    break;
                case "e":
                case "E":
                    //if ternario, es mas rapido y preciso
                    String action = (delete) ? "Eliminar" : "Editar";
                    return view.continentIdSelected(action);
                case "q":
                case "Q":
                    shouldGetOut = true;
                    break;
                default:
                    if (choice.matches("^-?'\\d+$")) {
                        int page = Integer.parseInt(choice);
                        if (page > 0 && page <= totalPages) currentPage = page - 1;
                    } else {
                        System.out.println(" ERROR MESSAGE || DEBE INRGESAR UN DATO VALIDO AL PAGINADOR");
                    }
            }
        }
        return 0;
    }

    private static void editContinent() {
        int continentIdEdition = continentListPerPage();
        if (continentIdEdition != 0) {
            editSelectedContinent(continentIdEdition);
        } else {
            view.newContinentCanceled();
        }
    }

    private static void editSelectedContinent(int id) {
        ContinentDTO contDTO = conDAO.findById(id);
        if (contDTO != null) {
            String nameUpdate = view.getNameUpdate(contDTO);
            if (!nameUpdate.isEmpty()) {
                conDAO.findByName(nameUpdate);
                contDTO.setName(nameUpdate);

                Boolean saved = conDAO.update(contDTO, id);

                if (saved) {
                    view.showUpdateContinent(contDTO.getName());
                } else {
                    view.updateContinentCanceled();
                }
            } else {
                view.continentNotExist(id);
                int continentIdSelected = view.continentIdSelected("Editar");
                if (continentIdSelected != 0)
                    editSelectedContinent(continentIdSelected);
                else
                    view.updateContinentCanceled();
            }

        }

    }

    public static void deleteContinent() {
        int continentIdElimination = continentListPerPage();
        if (continentIdElimination != 0) {
            deleteSelectedContinent(continentIdElimination);
        } else {
            view.newContinentCanceled();
        }

    }

    private static void deleteSelectedContinent(int id) {
        ContinentDTO contDTO = conDAO.findById(id);
        if (contDTO != null) {
            String nameDelete = view.getNameUpdate(contDTO);
            if (!nameDelete.isEmpty()) {
                conDAO.findByName(nameDelete);
                contDTO.getName();

                Boolean saved = conDAO.delete(contDTO.getId());

                if (saved) {
                    view.showUpdateContinent(contDTO.getName());
                } else {
                    view.updateContinentCanceled();
                }
            } else {
                view.continentNotExist(id);
                int continentIdSelected = view.continentIdSelected("Eliminar");
                if (continentIdSelected != 0)
                    editSelectedContinent(continentIdSelected);
                else
                    view.updateContinentCanceled();
            }

        }

    }
}
