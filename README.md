# Zona Fit (GYM) - Aplicación Desktop CRUD

Este proyecto es una aplicación de escritorio simple desarrollada en Java utilizando Swing para la interfaz gráfica y Spring Boot para la gestión del backend, la persistencia de datos con Spring Data JPA, y la conexión a una base de datos MySQL. Permite realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre una lista de usuarios de un gimnasio.

Es un proyecto construido paso a paso, ideal para principiantes que quieran ver la integración de Swing con un framework moderno como Spring Boot.

## Características

*   Interfaz gráfica de usuario (GUI) simple con Java Swing.
*   Listado de usuarios en una tabla.
*   Selección de un usuario en la tabla para ver/editar sus datos en el formulario.
*   Funcionalidad para limpiar los campos del formulario.
*   Guardar nuevos usuarios en la base de datos (operación **CREATE**).
*   Actualizar datos de usuarios existentes (operación **UPDATE**).
*   Eliminar usuarios seleccionados (operación **DELETE**).
*   Persistencia de datos en una base de datos MySQL utilizando Spring Data JPA.

## Tecnologías Utilizadas

*   **Lenguaje:** Java
*   **Framework:** Spring Boot
*   **GUI:** Java Swing
*   **Persistencia:** Spring Data JPA, Hibernate
*   **Base de Datos:** MySQL
*   **Herramienta de Build:** Maven (gestionado por Spring Boot Initializr)
*   **Librerías Adicionales:** Lombok (para reducir código boilerplate)

## Prerrequisitos

Para ejecutar este proyecto, necesitas tener instalado lo siguiente:

*   **Java Development Kit (JDK):** Versión 17 o superior.
*   **Maven:** Generalmente incluido con los IDEs modernos.
*   **MySQL Server:** Una instancia de MySQL ejecutándose.
*   **Cliente MySQL:** Una herramienta para interactuar con tu base de datos (MySQL Workbench, DBeaver, línea de comandos, etc.).
*   **IDE:** Un entorno de desarrollo integrado como IntelliJ IDEA (recomendado por el uso del diseñador Swing) o Eclipse.
*   **Plugin de Lombok:** Si usas IntelliJ o Eclipse, instala el plugin de Lombok para que el IDE reconozca las anotaciones y evite errores de compilación o advertencias falsas.

## Configuración y Ejecución

Sigue estos pasos para configurar y ejecutar la aplicación:

1.  **Obtener el Código:**
    *   Descarga o clona el código fuente del proyecto. Asegúrate de tener la carpeta raíz del proyecto que contiene el `pom.xml`.

2.  **Configurar la Base de Datos MySQL:**
    *   Conéctate a tu servidor MySQL utilizando tu cliente preferido.
    *   Crea una nueva base de datos. Por convención en este proyecto, la llamamos `zona_fit_db`.
        ```sql
        CREATE DATABASE zona_fit_db;
        ```
    *   No necesitas crear las tablas manualmente; Spring Data JPA/Hibernate lo hará por ti la primera vez que ejecutes la aplicación (gracias a `spring.jpa.hibernate.ddl-auto=update` configurado en `application.properties`).

3.  **Configurar las Credenciales de la Base de Datos:**
    *   Abre el archivo `src/main/resources/application.properties` en tu proyecto.
    *   Modifica las siguientes líneas con tus credenciales de usuario y contraseña de MySQL:
        ```properties
        spring.datasource.username=tu_usuario_mysql
        spring.datasource.password=tu_contraseña_mysql
        ```
    *   Si tu servidor MySQL no está en `localhost` o usa un puerto diferente a `3306`, ajusta también la línea `spring.datasource.url`.

4.  **Importar el Proyecto en tu IDE:**
    *   Abre tu IDE (IntelliJ IDEA recomendado).
    *   Selecciona "Open" o "Importar Proyecto" y navega hasta la carpeta raíz donde guardaste el proyecto. Selecciona la carpeta.
    *   El IDE debería reconocer el proyecto como un proyecto Maven e importar automáticamente las dependencias. Espera a que este proceso termine.

5.  **Construir el Proyecto (Opcional pero recomendado):**
    *   Puedes construir el proyecto usando Maven desde la terminal en la raíz del proyecto:
        ```bash
        mvn clean install
        ```
    *   O usando las opciones de construcción de Maven dentro de tu IDE.

6.  **Ejecutar la Aplicación:**
    *   Abre la clase principal `ZonaFitApplication.java` (ubicada en el paquete `gm.zona_fit`).
    *   Ejecuta el método `main()` desde tu IDE (generalmente haciendo clic derecho sobre la clase o el método y seleccionando "Run 'ZonaFitApplication.main()'").

7.  **Visualizar la GUI:**
    *   Si todo está configurado correctamente, la aplicación Swing debería iniciarse y mostrar la ventana "Zona Fit (GYM)".

## Uso de la Aplicación

*   La tabla en la parte derecha mostrará la lista de usuarios existentes en la base de datos al iniciar.
*   Haz clic en una fila de la tabla para cargar los datos del usuario seleccionado en los campos de texto del formulario de la izquierda.
*   Modifica los campos de texto y haz clic en el botón **"Guardar"** para actualizar los datos del usuario seleccionado.
*   Para crear un **nuevo** usuario, haz clic en el botón **"Limpiar"** para vaciar el formulario. Llena los campos con los datos del nuevo usuario y haz clic en **"Guardar"**.
*   Para **eliminar** un usuario, selecciónalo en la tabla y haz clic en el botón **"Eliminar"**. Se te pedirá confirmación antes de proceder.
*   El botón **"Limpiar"** vacía los campos del formulario y deselecciona cualquier fila en la tabla.

## Estructura del Proyecto

La estructura del proyecto sigue una organización por capas típica de aplicaciones Spring Boot: