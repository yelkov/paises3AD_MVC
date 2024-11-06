package edu.badpals.paises.vista;

public class Vista {

    public int menuPrincipal(){
        int respuesta = 0;
        StringBuilder sb = new StringBuilder();
        sb.append("\n==== MENÚ DE OPCIONES ====\n")
                .append("0. Mostrar Menú\n")
                .append("1. Mostrar países\n")
                .append("2. Crear país\n")
                .append("3. Actualizar país\n")
                .append("4. Eliminar país\n")
                .append("5. Salir/Exit\n");

        System.out.println(sb);
        respuesta = Peticiones.pedirRespuestaPrincipal();
        return respuesta;
    }

    public int menuMostrarPaises(){
        int respuesta = 0;
        StringBuilder sb = new StringBuilder();
        sb.append("\n==== MOSTRAR PAISES ====\n")
                .append("0. Menú\n")
                .append("1. Mostrar todos los países\n")
                .append("2. Mostrar un país\n")
                .append("3. Salir/Exit\n");

        System.out.println(sb);
        respuesta = Peticiones.pedirRespuestaMenuPaises();
        return respuesta;
    }
}
