package todo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.*;

public class TaskRepository {
    private final Map<Long, Task> data = new HashMap<>();
    //Guarda o actualiza una tarea
    public Task save(Task t) {
        data.put(t.getId(), t);
        return t;
    }
    //Busca por id
    public Task findById(long id) {
        return data.get(id); //devuelve si no existe
    }
    public List<Task> findAll() {
        List<Task> list = new ArrayList<>(data.values());
        list.sort(Comparator.comparingLong(Task::getId));
        return list;
    }
    //eliminar por id
    public boolean deleteById(long id) {
        return data.remove(id) != null;
    }
    //listar por estado (pendientes o completados)
    public List<Task> findByStatus(boolean completed) {
        List<Task> list = new ArrayList<>();
        for (Task t : data.values()) {
            if (t.isCompleted() == completed) {
                list.add(t);
            }
        }
        list.sort(Comparator.comparingLong(Task::getId));
        return list;
    }
}
