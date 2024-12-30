# Reviewer de Restaurantes 🍝

## Descripción del Proyecto

Bienvenido al proyecto **Reviewer de Restaurantes**. Esta aplicación permite gestionar restaurantes, menús y reseñas de manera eficiente utilizando una estructura de patrones de diseño y colecciones avanzadas. Está diseñada para ejecutarse en consola, poniendo a prueba conocimientos en colecciones, patrones de diseño, pruebas unitarias y otros conceptos clave de desarrollo.

---

## Requerimientos Técnicos

### Diagramas:
- Diagrama de clases UML.
- Diagrama relacional.

### Patrones de Diseño Implementados:
1. **Singleton**:
    - Gestiona la instancia única de un repositorio central de datos para almacenar restaurantes, menús y reviews.
2. **Factory**:
    - Crea instancias de diferentes tipos de reviews (reviews de restaurante y reviews de plato).
3. **Observable**:
    - Notifica automáticamente a los usuarios u otras partes del sistema cuando se agregue una nueva review o cambie la calificación promedio.
4. **Command**:
    - Suplanta el uso de "switch" para manejar comandos de manera estructurada.

### Uso de Colecciones:
- Map, Set, LinkedList, List y ArrayList.

### Pruebas Unitarias:
- Cobertura de clases del 100%.

### Arquitectura del Sistema:
- Uso de **controllers**, **repositories** y **services** para una clara separación de responsabilidades.

---

## Requerimientos Funcionales

### Gestión de Restaurantes:
- Crear, editar y eliminar información de restaurantes.
- Consultar la lista de restaurantes disponibles.

### Gestión de Menús:
- Asociar un menú a un restaurante.
- Añadir, editar y eliminar platos en un menú.

### Reviews de Restaurantes:
- Crear reviews generales para un restaurante (calificación y comentario).
- Listar las reviews asociadas a un restaurante.
- Calcular la calificación promedio de un restaurante basada en las reviews.

### Reviews de Platos:
- Crear reviews para platos específicos (calificación y comentario).
- Listar las reviews de un plato.
- Calcular la calificación promedio de un plato.

---

## Tecnologías Utilizadas
- Java.
- Pruebas unitarias con JUnit.
- IntelliJ IDEA como entorno de desarrollo.
- Gradle para la gestión de dependencias.

---

## Autor
- [Nahuel Lemes]

