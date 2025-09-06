package todo;

//Task service es la capa logica del negocio
//Genera ID, Valida entradas, Aplica reglas

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class TaskService {
    private final TaskRepository repo; //Acceso al repo
    private
    final AtomicLong seq = new AtomicLong(1); //generaIDS unicos

    //constructor, recibe un TaskRepository (para guardar y leer tareas)
    public TaskService(TaskRepository repo) {
        this.repo = repo;
    }
    //crea una nueva tarea
    public Task add(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("La descripcion no puede estar vacia");
        }
        long id = seq.getAndIncrement(); // genera ID unico
        Task t = new Task(id, description);
        repo.save(t);
        return (t);
    }
    //consultas(listar las tareas)
    public  List<Task> listAll() {
        return repo.findAll();
    }
    public List<Task> listPending() {
        return repo.findByStatus(false); //solo las pendientes
    }
    public List<Task> listCompleted() {
        return repo.findByStatus(true); //solo completadas
    }
    public boolean reopen(long id) {
        Task t = repo.findById(id);
        if (t == null) return false;
        t.reopen();
        repo.save(t);
        return true;
    }
    public boolean complete(long id) {
        Task t = repo.findById(id);
        if (t == null) return false; // no existe
        t.complete();
        repo.save(t);// guarda cambios
        return true;
    }
    public boolean delete(long id) {
        return repo.deleteById(id);
    }
    public boolean updateDescription(long id, String newDescription) {
        if (newDescription == null || newDescription.trim().isEmpty()) {
            throw new IllegalArgumentException("La descripcion  no puede estar vacia");
        }
        Task t = repo.findById(id);
        if (t == null) return false;
        t.setDescription(newDescription);
        repo.save(t);
        return true;
    }
}

