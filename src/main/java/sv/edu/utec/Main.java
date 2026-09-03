package sv.edu.utec;

import sv.edu.utec.datos.ConexionDB;
import sv.edu.utec.datos.ProductoDAO;
import sv.edu.utec.modelo.Producto;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        probarConexion();
        crearTabla();
        insertarProducto();

        System.out.println("\nANTES DE ACTUALIZAR:");
        listarProductos();

        actualizarProducto();

        System.out.println("\nDESPUÉS DE ACTUALIZAR:");
        listarProductos();

        eliminarProducto();

        System.out.println("\nDESPUÉS DE ELIMINAR:");
        listarProductos();
    }

    private static void probarConexion() {
        try (Connection conexion = ConexionDB.obtenerConexion()) {
            if (conexion != null && !conexion.isClosed()) {
                System.out.println("Conexión exitosa a: "
                + conexion.getMetaData().getURL());
            }
        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }
    }

    private static void crearTabla() {
        ProductoDAO productoDAO = new ProductoDAO();

        try {
            productoDAO.crearTabla();
            System.out.println("Tabla producto creada correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al crear la tabla: " + e.getMessage());
        }
    }

    private static void insertarProducto() {
        ProductoDAO productoDAO = new ProductoDAO();
        Producto producto = new Producto(1, "Teclado mecánico",45.99, 10);

        try {
            if (productoDAO.insertar(producto)) {
                System.out.println("Producto insertado correctamente.");
            }
        } catch (SQLException e) {
            System.out.println("Error al insertar el producto: " + e.getMessage());
        }
    }

    private static void listarProductos() {
        ProductoDAO productoDAO = new ProductoDAO();

        try {
            List<Producto> productos = productoDAO.listar();

            System.out.printf("%-5s %-25s %10s %10s%n",
                    "ID", "PRODUCTO", "PRECIO", "CANTIDAD");

            for (Producto producto : productos) {
                System.out.printf("%-5s %-25s %10s %10s%n",
                        producto.getId(),
                        producto.getNombre(),
                        producto.getPrecio(),
                        producto.getCantidad());
            }
        } catch (SQLException e) {
            System.out.println("Error al listar los productos: " + e.getMessage());
        }
    }

    private static void actualizarProducto() {
        ProductoDAO productoDAO = new ProductoDAO();

        Producto productoActualizado =
                new Producto(1, "Teclado mecánico RGB", 49.99, 20);

        try {
            if (productoDAO.actualizar(productoActualizado)) {
                System.out.println("Producto actualizado correctamente!");
            } else {
                System.out.println("No se encontró el producto.");
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar el producto: " + e.getMessage());
        }
    }

    private static void eliminarProducto() {
        ProductoDAO productoDAO = new ProductoDAO();
        int idEliminar = 1;

        try {
            if (productoDAO.eliminar(idEliminar)) {
                System.out.println("Producto eliminado correctamente.");
            } else {
                System.out.println("No se encontró el producto.");
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar el producto: " + e.getMessage());
        }
    }
}
