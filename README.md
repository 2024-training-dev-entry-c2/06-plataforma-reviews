# Proyecto: Sistema de Gestión de Reseñas para Restaurantes

Este proyecto implementa un sistema básico para gestionar restaurantes, menús, platos y reseñas. Está diseñado para proporcionar funcionalidades clave como agregar, consultar y administrar restaurantes, menús y las reseñas asociadas. La solución utiliza un enfoque basado en programación orientada a objetos, con soporte para pruebas unitarias y una estructura clara para cada componente.

## Características Principales

- **Gestión de Restaurantes:**
  - Crear, listar y eliminar restaurantes.
  - Agregar reseñas con calificaciones y comentarios.

- **Gestión de Menús y Platos:**
  - Crear menús y asignar platos a cada menú.
  - Agregar, listar y eliminar platos.
  - Agregar reseñas a platos con calificaciones y comentarios.

- **Gestión de Reseñas:**
  - Registro y consulta de reseñas para restaurantes y platos.
  - Calificaciones (1-5) con comentarios asociados.

## Arquitectura del Proyecto

El sistema utiliza un diseño modular y basado en clases, con una estructura que divide responsabilidades claras entre controladores, modelos, servicios y utilidades.

### Componentes Principales

1. **Modelos:**
   Representan las entidades clave del sistema como `Restaurant`, `Plate`, `Menu`, `ReviewRestaurant` y `ReviewPlate`. Incluyen atributos relevantes como nombre, dirección, descripción, precio, etc.

2. **Servicios:**
   Implementan la lógica de negocio para gestionar las operaciones de las entidades, como agregar reseñas, administrar platos, etc.

3. **Controladores:**
   Facilitan la interacción entre el usuario (a través de un menú de consola) y los servicios. Manejan la entrada y salida de datos.

4. **Utilidades:**
   Incluyen clases de soporte como `MenuConsole`, que gestiona la interacción del usuario y muestra el menú principal del sistema.

5. **Pruebas Unitarias:**
   Verifican la funcionalidad de las clases y métodos principales mediante casos de prueba bien definidos, utilizando JUnit.

## Flujo Básico del Sistema

1. **Creación de Restaurante:**
   - El usuario agrega un restaurante, proporcionando el nombre y la dirección.

2. **Gestión de Menús y Platos:**
   - Se crean menús y se asignan platos con nombre, descripción y precio.

3. **Reseñas:**
   - Los usuarios pueden agregar reseñas tanto a restaurantes como a platos, especificando una calificación y un comentario.

4. **Consultas:**
   - Es posible consultar restaurantes, menús, platos y las reseñas asociadas.

## Requisitos Técnicos

- **Lenguaje de Programación:** Java
- **Entorno de Desarrollo:**
  - IntelliJ IDEA o cualquier IDE compatible.
  - JDK 17 o superior.
- **Base de Datos:** MySQL (opcional para almacenamiento persistente). en este caso solo el diagrama
- **Herramientas de Prueba:** JUnit para pruebas unitarias.

## Estructura del Proyecto

```
/src
  /main
    /java
      /org/example
        /controllers
        /models
        /services
        /utils
  /test
    /java
      /org/example
        /controllers
        /models
        /services
        /utils
```

## Instalación y Ejecución

1. **Clonar el Repositorio:**
   ```
   git clone https://github.com/RM92023/06-plataforma-reviews
   ```

2. **Configurar el Entorno:**
   - Asegúrate de tener Java instalado.
   - Configura tu IDE para usar el JDK 17.

3. **Compilar el Proyecto:**
   ```
   solo ejecutas.
   ```

4. **Ejecutar el Sistema:**
   Ejecuta la clase principal desde tu IDE o utiliza Maven para ejecutar el programa:
   ```
   mvn exec:java -Dexec.mainClass="org.example.utils.Menu"
   ```

5. **Ejecutar Pruebas:**
   ```
   ejecuta los test
   ```

## Futuras Mejoras

- Implementación de persistencia utilizando MySQL o una base de datos similar.
- Creación de una interfaz gráfica para mejorar la experiencia del usuario.
- Ampliación de las funcionalidades para incluir reportes y análisis de reseñas.

---

Este proyecto ofrece una base sólida para gestionar restaurantes y reseñas, y puede ampliarse fácilmente para satisfacer necesidades más complejas.


## Diagrama de Clases
![alt text](<Restaurant Review.png>)

## Diagrama Relacional
![alt text](<Captura de pantalla 2024-12-29 215546.png>)