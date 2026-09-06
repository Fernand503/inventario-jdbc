# Decisiones del proyecto Inventario JDBC

## 1. Estructura del proyecto

El proyecto está desarrollado en Java y utiliza JDBC para conectarse a una base de datos H2.

La estructura principal se divide de la siguiente manera:

- `Main.java`: se encarga de iniciar y coordinar la ejecución del programa.
- `modelo/Producto.java`: representa los datos de un producto.
- `datos/ConexionDB.java`: se encarga de establecer la conexión con H2.
- `datos/ProductoDAO.java`: contiene las operaciones para trabajar con los productos en la base de datos.

Esta organización permite separar las responsabilidades y mantener el código más ordenado.

## 2. Función de Main

La clase `Main` es el punto de entrada del programa porque contiene el método `main()`.

Su función es coordinar las diferentes operaciones del CRUD y comprobar que el sistema funciona correctamente.

Desde Main se invocan ordenadamente las siguientes operaciones del DAO:

1. Probar la conexión con la base de datos.
2. Crear la tabla `producto`.
3. Insertar un producto.
4. Listar los productos.
5. Actualizar un producto.
6. Volver a listar los productos para comprobar la actualización.
7. Eliminar un producto.
8. Realizar un listado final para comprobar la eliminación.

`Main` utiliza `ProductoDAO` para realizar las operaciones sobre la base de datos, en lugar de colocar directamente las sentencias SQL dentro de esta clase.

## 3. Función de Producto

La clase `Producto` pertenece al paquete `modelo` y representa un producto del inventario.

Contiene cuatro atributos:

- `id`: identificador del producto.
- `nombre`: nombre del producto.
- `precio`: precio del producto.
- `cantidad`: cantidad disponible.

También contiene un constructor y métodos `get` y `set` para consultar y modificar sus atributos.

Esta clase permite representar los datos de un producto como un objeto de Java y facilita el intercambio de información entre `Main` y `ProductoDAO`.

## 4. Función de ConexionDB

La clase `ConexionDB` pertenece al paquete `datos` y tiene como responsabilidad establecer la conexión con la base de datos H2.

La conexión utiliza la siguiente URL:

```text
jdbc:h2:./inventario
```

El método obtenerConexion() utiliza DriverManager.getConnection() para crear y devolver una conexión con la base de datos.

Se decidió colocar la conexión en una clase independiente para evitar repetir la configuración de la base de datos en las diferentes clases que necesitan acceder a ella.

## 5. Función de ProductoDAO

La clase ProductoDAO pertenece al paquete datos.

DAO significa Data Access Object y su función es encargarse del acceso a los datos.

En este proyecto, ProductoDAO contiene las operaciones relacionadas con la tabla producto:

* crearTabla(): crea la tabla producto si no existe.
* insertar(): agrega un producto a la base de datos.
* listar(): obtiene y muestra los productos registrados.
* actualizar(): modifica los datos de un producto.
* eliminar(): elimina un producto utilizando su identificador.

De esta manera, las operaciones SQL están concentradas en ProductoDAO y no directamente en Main.

## 6. Uso de PreparedStatement

El proyecto utiliza PreparedStatement para realizar las operaciones de insertar, listar, actualizar y eliminar.

Por ejemplo, para insertar un producto se utiliza:

```java
String sql = "INSERT INTO producto (id, nombre, precio, cantidad) VALUES (?, ?, ?, ?)";
```

Los signos `?` representan parámetros que posteriormente reciben los valores correspondientes mediante métodos como:

```text
sentencia.setInt(1, producto.getId());
sentencia.setString(2, producto.getNombre());
sentencia.setDouble(3, producto.getPrecio());
sentencia.setInt(4, producto.getCantidad());
```

Se decidió utilizar `PreparedStatement` porque permite separar la sentencia SQL de los valores que se envían a la base de datos. Además, evita tener que construir las consultas concatenando directamente los valores.

## 7. Uso de try-with-resources

El proyecto utiliza try-with-resources para manejar recursos relacionados con la base de datos, como:

* Connection
* PreparedStatement
* Statement
* ResultSet

Por ejemplo:

```text
try (Connection conexion = ConexionDB.obtenerConexion();
     PreparedStatement sentencia = conexion.prepareStatement(sql)) {

    // Operación con la base de datos
}
```

Esta estructura permite que los recursos se cierren automáticamente al finalizar el bloque try, incluso si ocurre una excepción.

Esto ayuda a evitar que queden conexiones u otros recursos abiertos y hace que el manejo de la base de datos sea más ordenado.

## 8. Separación por capas

El proyecto separa las responsabilidades de las diferentes partes del sistema.

La relación principal puede representarse así:

```text
Main
  ↓
ProductoDAO
  ↓
ConexionDB
  ↓
Base de datos H2
```


Por otro lado, Producto representa el modelo de información que se utiliza para transportar los datos del producto.

Las responsabilidades son:

* Main: coordina la ejecución del programa.
* Producto: representa los datos de un producto.
* ProductoDAO: realiza las operaciones CRUD y contiene las consultas SQL.
* ConexionDB: establece la conexión con H2.
* H2: almacena los datos.

Esta separación facilita la comprensión y el mantenimiento del proyecto. Por ejemplo, la configuración de la conexión se puede modificar en ConexionDB sin tener que cambiar todas las operaciones del CRUD.

## 9. Conclusión

Las decisiones de diseño buscan mantener el proyecto organizado y separar las responsabilidades de cada componente.

El uso de Producto como modelo, ProductoDAO para el acceso a los datos y ConexionDB para la conexión permite que cada clase tenga una función específica.

Además, el uso de PreparedStatement y try-with-resources permite trabajar con JDBC de una forma más segura y ordenada.

Finalmente, Main permite comprobar el funcionamiento completo de las operaciones CRUD del proyecto.
