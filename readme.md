Aplicación de escritorio simple para gestionar tareas, desarrollada en Java 17 con Swing y el look & feel moderno de FlatLaf.

Funcionalidades

Agregar nuevas tareas.

Listar todas las tareas en una interfaz clara.

Marcar tareas como completadas o reabrirlas.

Editar descripciones.

Eliminar tareas.

Tecnologías

Java 17

Maven

Swing (interfaz gráfica)

FlatLaf (estilo moderno)

Ejecución
# Clonar el repositorio
git clone https://github.com/tuusuario/Task_Manager.git
cd Task_Manager

# Compilar con Maven
mvn clean install

# Ejecutar
mvn exec:java -Dexec.mainClass="todo.Main"

Estructura
src/main/java/todo/
Task.java            # Modelo de tarea
TaskRepository.java  # Repositorio en memoria
TaskService.java     # Lógica de negocio
TaskManagerFrame.java# Interfaz gráfica
Main.java            # Punto de entrada
