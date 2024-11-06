package edu.badpals.paises.vista;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Peticiones {

    public static int pedirRespuestaPrincipal(){
        Scanner sc = new Scanner(System.in);
        int respuesta = 0;
        System.out.println("Seleccione una opción: ");
        String opcion = sc.nextLine();
        switch(opcion){
            case "0":
            case "menu":
            case "menú":
            case "mostrar menu":
            case "mostrar menú":
                respuesta = 0;
                break;
            case "1":
            case "mostrar paises":
            case "mostrar países":
                respuesta = 1;
                break;
            case "2":
            case "crear pais":
            case "crear país":
                respuesta = 2;
                break;
            case "3":
            case "actualizar pais":
            case "actualizar país":
                respuesta = 3;
                break;
            case "4":
            case "eliminar pais":
            case "eliminar país":
                respuesta = 4;
                break;
            case "5":
            case "salir":
            case "exit":
                respuesta = 5;
                break;
            default:
                respuesta = 6;
                break;
        }
        return respuesta;
    }

    public static int pedirRespuestaMenuPaises(){
        Scanner sc = new Scanner(System.in);
        int respuesta = 0;
        System.out.println("Seleccione una opcion: ");
        String opcion = sc.nextLine();
        switch(opcion){
            case "0":
            case "menu":
                respuesta = 0;
                break;
            case "1":
            case "mostrar todos los paises":
            case "mostrar todos los países":
                respuesta = 1;
                break;
            case "2":
            case "mostrar pais":
            case "mostrar un pais":
            case "mostrar un país":
                respuesta = 2;
                break;
            case "3":
            case "salir":
            case "exit":
                respuesta = 3;
                break;
            default:
                respuesta = 4;
                break;
        }
        return respuesta;
    }

    public static String pedirCadena(String mensaje) {
        Scanner sc = new Scanner(System.in);
        System.out.println(mensaje);
        return sc.nextLine();
    }

    public static Integer pedirHabitantes() {
        Scanner sc = new Scanner(System.in);
        boolean valido = false;
        int intentos = 0;
        Integer num_habitantes = null;

        while (!valido) {
            System.out.println("Introduzca el número de habitantes del país: ");
            try {
                num_habitantes = sc.nextInt();
                sc.nextLine();
                valido = true;
            } catch (InputMismatchException e) {
                System.out.println("Número no válido. Recuerde introducir un número entero.");
                intentos++;
                sc.nextLine();
                if (intentos == 3) {
                    System.out.println("Número máximo de intentos alcanzado. Proceso abortado.");
                    return null;
                }
            }
        }
        return num_habitantes;
    }

    public static String pedirConfirmacion(String nombre, int habitantes, String capital, String moneda){
        Scanner sc = new Scanner(System.in);
        System.out.println("\nLos datos recogidos son los siguientes:");
        System.out.println("Nombre de país: " + nombre + ", \nnúmero de habitantes: " + habitantes + ", \ncapital: " +capital+ ", \nmoneda: " + moneda);
        System.out.println("\nSi es correcto y desea continuar pulse 's'. Si desea abortar proceso pulse cualquier otra tecla.");
        String respuesta = sc.nextLine();
        if (respuesta.toLowerCase().equals("s")){
            return respuesta;
        }else{
            respuesta = "n";
            return respuesta;
        }
    }
}
