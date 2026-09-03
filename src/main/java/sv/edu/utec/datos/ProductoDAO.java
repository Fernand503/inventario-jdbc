package sv.edu.utec.datos;

import java.net.PortUnreachableException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import sv.edu.utec.modelo.Producto;
import java.sql.PreparedStatement;

public class ProductoDAO {
    public void crearTabla() throws SQLException {

        String sql = "CREATE TABLE IF NOT EXISTS producto (" +
                "id INT PRIMARY KEY, " +
                "nombre VARCHAR(100) NOT NULL, " +
                "precio DOUBLE NOT NULL, " +
                "cantidad INT NOT NULL )";

        try (Connection conexion = ConexionDB.obtenerConexion();
        Statement statement = conexion.createStatement()) {
            statement.execute(sql);
        }
    }

    public boolean insertar(Producto producto) throws SQLException {
        String sql = "INSERT INTO producto (id, nombre, precio, cantidad) VALUES (?, ?, ?, ?)";

        try (Connection conexion = ConexionDB.obtenerConexion();
        PreparedStatement sentencia = conexion.prepareStatement(sql)){
           sentencia.setInt(1, producto.getId());
           sentencia.setString(2, producto.getNombre());
           sentencia.setDouble(3, producto.getPrecio());
           sentencia.setInt(4, producto.getCantidad());

           return sentencia.executeUpdate() > 0;
        }
    }

    public List<Producto> listar() throws SQLException {
        List<Producto> productos = new ArrayList<>();

        String sql = "SELECT id, nombre, precio, cantidad FROM producto";

        try (Connection conexion = ConexionDB.obtenerConexion();
        PreparedStatement sentencia = conexion.prepareStatement(sql);
        ResultSet resultado = sentencia.executeQuery()) {

            while (resultado.next()) {
                Producto producto = new Producto (
                        resultado.getInt("Id"),
                        resultado.getString("nombre"),
                        resultado.getDouble("precio"),
                        resultado.getInt("cantidad")
                );

                productos.add(producto);
            }
        }

        return productos;
    }

    public boolean actualizar(Producto producto) throws SQLException {
        String sql = "UPDATE producto " +
                "SET nombre = ?, precio = ?, cantidad = ?" +
                "WHERE id = ?";

        try (Connection conexion = ConexionDB.obtenerConexion();
        PreparedStatement sentencia = conexion.prepareStatement(sql)) {
            sentencia.setString(1, producto.getNombre());
            sentencia.setDouble(2, producto.getPrecio());
            sentencia.setInt(3, producto.getCantidad());
            sentencia.setInt(4, producto.getId());

            return sentencia.executeUpdate() > 0;
        }
    }

    public boolean eliminar(int id) throws SQLException {
        String sql = "DELETE FROM producto WHERE id = ?";

        try (Connection conexion = ConexionDB.obtenerConexion();
        PreparedStatement sentencia = conexion.prepareStatement(sql)){

            sentencia.setInt(1, id);

            return sentencia.executeUpdate() > 0;
        }
    }
}
