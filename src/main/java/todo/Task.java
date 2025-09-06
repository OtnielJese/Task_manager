package todo;

import java.time.LocalDateTime; //guarda hora-fecha de creacion y completado.

public class Task {

    private final long id; //identificador unico
    private String description; //texto de la tarea
    private boolean completed; //true = hecha, false = incompleta
    private final LocalDateTime createdAt; // fecha de creacion
    private LocalDateTime completedAt; //hora de completada

    public Task (long id, String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("La descripcion no puede estar vacia");
        }
        this.id = id;
        this.description = description.trim();
        this.completed =false;
        this.createdAt = LocalDateTime.now(); //aqui jalamos el import
        this.completedAt = null;
    }
    public long getId() {return id; }
    public String getDescription() { return description; }
    public boolean isCompleted() { return completed; }
    public LocalDateTime getCompletedAt() {return completedAt; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setDescription(String newDescription) {
        if (newDescription == null || newDescription.trim().isEmpty()) {
            throw new IllegalArgumentException("La descripcion no puede estar vacia");
        }
        this.description = newDescription.trim();
    }
    public void complete() {
        if (!completed) { // solo si estaba pendiente
            this.completed = true;
            this.completedAt = LocalDateTime.now(); // otra vez usamos el import
        }
    }

    public void reopen() {
        this.completed = false;
        this.completedAt = null;
    }
    @Override public String toString() {
        String check = completed ? "✓" : " ";
        return "[" + check + "] (" + id + ") " + description;
    }
}

