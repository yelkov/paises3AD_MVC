package edu.badpals.paises.modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaisBD {
    private static final String URL = "jdbc:mysql://localhost:3306/paises";
    private static final String USER = "root";
    private static final String PASS = "root";
    public static Connection connection = null;
    private static Statement statement = null;

    public static void conectarBD(){
        try{
            if(connection == null){
                connection = DriverManager.getConnection(URL,USER,PASS);
            }
            if(statement == null){
                statement = connection.createStatement();
            }
        } catch (SQLException e) {
            System.out.println("Error al conectar la BD");
            e.printStackTrace();
        }
    }

    public static void desconectarBD(){
        if(statement != null){
            try{
                statement.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar statement");
                e.printStackTrace();
            }finally{
                statement = null;
            }
        }
        if(connection != null){
            try{
                connection.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar connection");
                e.printStackTrace();
            }finally{
                connection = null;
            }
        }
    }

    public static List<Pais> getPaises(){
        List<Pais> paises = new ArrayList<>();
        try(ResultSet rs = statement.executeQuery("select * from paises")){
            while(rs.next()){
                String nombre = rs.getString("nombre");
                int num_habitantes = rs.getInt("num_habitantes");
                String capital = rs.getString("capital");
                String moneda = rs.getString("moneda");
                Pais pais = new Pais(nombre,num_habitantes,capital,moneda);
                paises.add(pais);
            }
            return paises;
        } catch (SQLException e) {
            System.out.println("Error al leer los datos de países.");
            e.printStackTrace();
        }
        return null;
    }

    public static Pais getPais(String nombre){
        try{
            String selectPais = "select * from paises where nombre = ?";
            PreparedStatement ps = connection.prepareStatement(selectPais);
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String nombrePais = rs.getString("nombre");
                int num_habitantes = rs.getInt("num_habitantes");
                String capital = rs.getString("capital");
                String moneda = rs.getString("moneda");
                Pais pais = new Pais(nombrePais,num_habitantes,capital,moneda);
                return pais;
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al eliminar datos de país.");
            e.printStackTrace();
        }
        return null;
    }

    private static void insertPais(Pais pais){
        try{
            String insert = "insert into paises (nombre,num_habitantes,capital,moneda) values (?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(insert);
            ps.setString(1,pais.getNombre());
            ps.setInt(2,pais.getNum_habitantes());
            ps.setString(3,pais.getCapital());
            ps.setString(4,pais.getMoneda());

            ps.executeUpdate();
            System.out.println("Se ha guardado el nuevo país "+ pais.getNombre()+ " en la BD.");
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al insertar nuevo país.");
            e.printStackTrace();
        }
    }

    private static void updatePais(String nombreAntiguo, Pais pais){
        try{
            String update = "update paises set nombre = ?, NUM_HABITANTES = ?, CAPITAL = ?, moneda = ? where nombre = ?";
            PreparedStatement ps = connection.prepareStatement(update);
            ps.setString(1,pais.getNombre());
            ps.setInt(2,pais.getNum_habitantes());
            ps.setString(3,pais.getCapital());
            ps.setString(4,pais.getMoneda());
            ps.setString(5,nombreAntiguo);

            int filasActualizadas = ps.executeUpdate();
            if (filasActualizadas == 1) {
                System.out.println("El país ha sido actualizado.");
            } else {
                System.out.println("No se encontró el país con el nombre especificado.");
            }
            ps.close();

        } catch (SQLException e) {
            System.out.println("Error al actualizar datos de país.");
            e.printStackTrace();
        }
    }

    private static void deletePais(Pais pais){
        try{
            String delete = "delete from paises where nombre = ?";
            PreparedStatement ps = connection.prepareStatement(delete);
            ps.setString(1,pais.getNombre());

            int filasBorradas = ps.executeUpdate();
            if(filasBorradas == 1){
                System.out.println("El país ha sido eliminado.");
            } else {
                System.out.println("No se encontró el país con el nombre especificado.");
            }
            ps.close();

        } catch (SQLException e) {
            System.out.println("Error al eliminar datos de país.");
            e.printStackTrace();
        }
    }
}
