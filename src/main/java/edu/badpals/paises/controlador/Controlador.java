package edu.badpals.paises.controlador;

import edu.badpals.paises.modelo.Pais;
import edu.badpals.paises.modelo.PaisBD;
import edu.badpals.paises.vista.Peticiones;
import edu.badpals.paises.vista.Vista;

import java.util.Comparator;
import java.util.List;

public class Controlador {
    Vista vista = new Vista();
    PaisBD modelo = new PaisBD();

    public void control(){
        modelo.conectarBD();
        int respuesta = 0;
        while(respuesta != 5){
            respuesta = vista.menuPrincipal();
            switch (respuesta){
                case 0:
                    respuesta = vista.menuPrincipal();
                case 1:
                    mostrarPaises();
                    break;
                case 2:
                    crearPais();
                    break;
                case 3:
                    actualizarPais();
                    break;
                case 4:
                    eliminarPais();
                    break;
                case 5:
                    System.out.println("\nSaliendo del programa.");
                    System.out.println("¡Hasta luego!.");
                    break;
                case 6:
                    System.out.println("\nOpción no encontrada");
                    break;
            }
        }
        modelo.desconectarBD();
    }

    private void eliminarPais() {
        System.out.println("\n==== ELIMINAR PAÍS ====\n");
        String nombre = Peticiones.pedirCadena("Introduzca el nombre del país a eliminar: ");
        Pais pais = modelo.getPais(nombre);
        if(pais == null){
            System.out.println("El país de nombre: " + nombre + " no existe. No es posible eliminarlo.");
        }else{
            String confirmacion = Peticiones.pedirConfirmacion(pais.getNombre(),pais.getNum_habitantes(),pais.getCapital(),pais.getMoneda());
            if(confirmacion.equals("s")){
                modelo.deletePais(pais);
                System.out.println("El país " + nombre + " ha sido eliminado.");
            } else if (confirmacion.equals("n")) {
                System.out.println("Proceso de eliminación abortado.");
            }
        }
    }

    private void actualizarPais() {
        System.out.println("\n==== ACTUALIZAR PAÍS ====\n");
        String nombre = Peticiones.pedirCadena("Introduzca el nombre del país a actualizar: ");
        if (modelo.getPais(nombre) == null){
            System.out.println("El país no existe en la base de datos, no se puede actualizar.");
        }else{

            String nuevoNombre = Peticiones.pedirCadena("Introduzca el nuevo nombre: ");
            Integer habitantes = Peticiones.pedirHabitantes();
            if(habitantes == null)return;
            String capital = Peticiones.pedirCadena("Introduzca el nuevo nombre de la capital: ");
            String moneda = Peticiones.pedirCadena("Introduzca la nueva moneda del país: ");

            Pais pais = modelo.getPais(nombre);
            pais.setNombre(nuevoNombre);
            pais.setNum_habitantes(habitantes);
            pais.setCapital(capital);
            pais.setMoneda(moneda);

            modelo.updatePais(pais);
            System.out.println("El país "+ nuevoNombre +" se ha actualizado.");
        }
    }

    private void crearPais() {
        System.out.println("\n==== CREAR NUEVO PAÍS ====\n");
        String nombre = Peticiones.pedirCadena("Introduzca el nombre del país: ");
        if (modelo.getPais(nombre) != null){
            System.out.println("El país ya existe en la base de datos, no se puede volver a crear.");
        }else{
            Integer habitantes = Peticiones.pedirHabitantes();
            if(habitantes == null)return;
            String capital = Peticiones.pedirCadena("Introduzca el nombre de la capital: ");
            String moneda = Peticiones.pedirCadena("Introduzca la moneda del país: ");

            String confirmacion = Peticiones.pedirConfirmacion(nombre,habitantes,capital,moneda);
            if (confirmacion.equals("s")){
                Pais pais = new Pais(nombre,habitantes,capital,moneda);
                modelo.insertPais(pais);
                System.out.println("País "+nombre+ " ha sido creado.");
            } else if (confirmacion.equals("n")) {
                System.out.println("Proceso abortado");
            }
        }
    }

    private void mostrarPaises() {
        int respuesta = 0;
        while(respuesta != 3){
            respuesta = vista.menuMostrarPaises();
            switch (respuesta){
                case 0:
                    respuesta = vista.menuMostrarPaises();
                case 1:
                    List<Pais> paises = modelo.getPaises();
                    paises.stream().sorted(Comparator.comparing(Pais::getNombre)).forEach(pais-> System.out.println(pais.toString()));
                    break;
                case 2:
                    String nombrePais = Peticiones.pedirCadena("Introduzca el nombre del país a buscar: ");
                    Pais pais = modelo.getPais(nombrePais);
                    if(pais != null){
                        System.out.println(pais);
                    }else{
                        System.out.println("El país de nombre: "+ nombrePais + " no existe en la base de datos.");
                    }
                    break;
                case 3:
                    System.out.println("Saliendo del menú de mostrar países.");
                    break;
                case 4:
                    System.out.println("Opción no válida.");
                    break;
                default:
                    break;
            }
        }
    }
}
