# Pruebas de Ejecución y Validación CRUD

Este archivo documenta las pruebas funcionales del ciclo de vida CRUD y el despliegue desde cero del sistema, validando la persistencia y la consistencia de los datos mediante JDBC y la base de datos H2.

## ⚙️ Entorno de Desarrollo y Requisitos
* **JDK Utilizado:** OpenJDK 17 (Java SE 17)
* **Gestor de Dependencias:** Apache Maven (definido en pom.xml)
* **Base de Datos:** H2 Database (Modo embebido / en memoria)
* **Paquete Base:** sv.edu.utec

## 🚀 Pasos para Ejecutar el Proyecto desde Cero

1. **Clonar el repositorio del equipo:**
   ```bash
   git clone https://github.com
   cd inventario-jdbc
   ```
2. **Descargar dependencias y compilar con Maven:**
   ```bash
   mvn clean compile
   ```
3. **Ejecutar la aplicación (Clase Main):**
   ```bash
   mvn exec:java -Dexec.mainClass="sv.edu.utec.Main"
   ```

## 🧪 Resultados del Ciclo CRUD

Se verificó el comportamiento secuencial programado en la arquitectura DAO (`ProductoDAO.java`), obteniendo los siguientes resultados en memoria:

### 1. Insertar (Create)
* **Operación:** Ejecución de `insertar()` a través del DAO pasando un objeto `Producto` (nombre, precio, cantidad).
* **Resultado:** Exitoso. La conexión JDBC procesa la sentencia `INSERT INTO` de SQL de manera correcta. H2 genera automáticamente el identificador único (`id`).

### 2. Listar (Read)
* **Operación:** Ejecución de `listarTodos()` mediante sentencias `SELECT * FROM`.
* **Resultado:** Exitoso. Muestra en la consola los productos registrados con sus respectivos atributos mapeados desde el objeto de transferencia.

### 3. Actualizar (Update)
* **Operación:** Modificación de un registro específico llamando a `actualizar()` filtrando por su ID asignado.
* **Resultado:** Exitoso. Se ejecutó la sentencia `UPDATE` modificando los valores del producto en tiempo de ejecución. Al volver a listar, los cambios se reflejaron de forma inmediata.

### 4. Eliminar (Delete)
* **Operación:** Borrado físico del elemento mediante `eliminar(id)`.
* **Resultado:** Exitoso. Remoción del producto mediante la sentencia `DELETE FROM`. El listado final subsecuente confirmó de forma satisfactoria que la tabla quedó vacía.

## ⚠️ Problemas Encontrados y Soluciones
* **Descarga inicial de dependencias:** Durante la primera compilación, el proceso tardó un poco más de lo habitual debido a la descarga del controlador JDBC de H2 desde el repositorio central de Maven.
  * *Solución:* Se garantizó una conexión estable a internet para que el archivo `pom.xml` terminara de indexar las librerías correctamente.

## 📸 Registro de la Consola de Ejecución (Logs de Salida)
```text
[INFO] Scanning for projects...
[INFO] ------------------------------------------------------------------------
[INFO] Building inventario-jdbc 1.0-SNAPSHOT
[INFO] ------------------------------------------------------------------------
[INFO] --- exec-maven-plugin:3.1.0:java (default-cli) @ inventario-jdbc ---

--- Iniciando Pruebas de Inventario JDBC ---
Conexión establecida con éxito a la base de datos H2.
Tabla 'productos' verificada/creada correctamente.

[CRUD] Insertando producto: Laptop Gamer... ¡Éxito!
[CRUD] Listando productos:
  - ID: 1 | Nombre: Laptop Gamer | Precio: \$1200.00 | Cantidad: 5

[CRUD] Actualizando datos de producto con ID: 1... ¡Éxito!
[CRUD] Listando productos (Actualizados):
  - ID: 1 | Nombre: Laptop Gamer Pro | Precio: \$1250.00 | Cantidad: 4

[CRUD] Eliminando producto con ID: 1... ¡Éxito!
[CRUD] Listando productos (Final):
  (La tabla de inventario se encuentra vacía)

--- Pruebas Finalizadas de Forma Exitosa ---
Conexión H2 cerrada correctamente.
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
```

