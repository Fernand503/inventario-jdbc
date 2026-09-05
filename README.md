# Inventario JDBC

Aplicación de consola desarrollada en Java que implementa las operaciones CRUD de productos mediante JDBC y una base de datos H2.

## Integrantes

* José Fernando Díaz Del Cid | 27-0088-2025
* Gerson Mauricio Nataren Rodríguez | 27-0567-2025
* Wendy Esmeralda Contreras Ardón | 27-2319-2025

## Tecnologías utilizadas

* Java
* Maven
* JDBC
* H2 Database
* IntelliJ IDEA
* Git y GitHub

## Entidad Producto

Cada producto contiene los siguientes atributos:

* `id`: identificador único.
* `nombre`: nombre del producto.
* `precio`: precio del producto.
* `cantidad`: unidades disponibles.

## Funcionalidades

El proyecto permite realizar las operaciones principales de un CRUD:

* Crear la tabla de productos.
* Insertar productos.
* Listar los productos almacenados.
* Actualizar un producto mediante su identificador.
* Eliminar un producto mediante su identificador.

## Estructura del proyecto

```text
InventarioJDBC/
├── pom.xml
├── .gitignore
└── src/main/java/sv/edu/utec/
    ├── Main.java
    ├── datos/
    │   ├── ConexionDB.java
    │   └── ProductoDAO.java
    └── modelo/
        └── Producto.java
```

## Descripción de las clases

* `Main`: invoca ordenadamente las operaciones del DAO y presenta los resultados en la consola.
* `Producto`: representa la entidad del inventario mediante atributos, constructor y métodos de acceso.
* `ConexionDB`: administra las credenciales y proporciona la conexión con la base de datos H2.
* `ProductoDAO`: contiene las sentencias SQL y las operaciones de acceso a los datos mediante JDBC.

## Requisitos

* JDK 17 o superior.
* Apache Maven.
* IntelliJ IDEA u otro entorno compatible con proyectos Maven.
* Conexión a internet durante la primera ejecución para descargar las dependencias.

## Instrucciones de ejecución

1. Clonar o descargar este repositorio.
2. Abrir el proyecto desde IntelliJ IDEA.
3. Esperar a que Maven descargue las dependencias.
4. Ejecutar la clase `Main.java`.
5. Revisar en la consola el resultado de las operaciones CRUD.

## Resultado esperado

Al ejecutar el programa se realiza la siguiente secuencia:

1. Conexión con la base de datos H2.
2. Creación de la tabla de productos si todavía no existe.
3. Inserción de un producto.
4. Listado del producto registrado.
5. Actualización de sus datos.
6. Segundo listado con los datos actualizados.
7. Eliminación del producto.
8. Listado final de la tabla vacía.

## Uso de inteligencia artificial

Se utilizó ChatGPT como herramienta de apoyo durante el desarrollo del proyecto. Su uso estuvo relacionado con la comprensión de JDBC y el patrón DAO, la organización de las clases `ConexionDB`, `ProductoDAO` y `Main`, la revisión de las operaciones CRUD y la depuración de errores de conexión y sentencias SQL.

La finalidad fue recibir orientación, comprender el funcionamiento del código y detectar errores durante las pruebas. El código fue revisado, adaptado, ejecutado y validado antes de realizar la entrega.
