package todo;
import javax.swing.*;
import com.formdev.flatlaf.FlatLightLaf;

public class Main {
    public static void main(String[] args) {
        Task t1 = new Task(1, "Estudiar Java");
        Task t2 = new Task(2, "Comprar pan");
        System.out.println("Iniciales");
        System.out.println(t1);
        System.out.println(t2);

        t1.complete();
        System.out.println("\nTras completar t1:");
        System.out.println(t1);

        t1.reopen();
        System.out.println("\nTras completar t1:");
        System.out.println(t1);

        try {
            t2.setDescription("    ");
        } catch (IllegalArgumentException e) {
            System.out.println("\nError esperado al cambiar descripción: " + e.getMessage());
        }
        System.out.println("\nFinal:");
        System.out.println(t1);
        System.out.println(t2);

        TaskRepository repo = new TaskRepository();
        repo.save(t1);
        repo.save(t2);

        System.out.println("\nRepo - todas:");
        System.out.println(repo.findAll());

        t1.complete(); repo.save(t1);
        System.out.println("\nRepo - completadas");
        System.out.println(repo.findByStatus(true));

        repo.deleteById(2);
        System.out.println("\nRepo - tras borrar id=2:");
        System.out.println(repo.findAll());

        TaskService service = new TaskService(repo);

        //service.add("Estudiar Java");
        //service.add("Comprar pan");

        System.out.println("\nTodas:");
        System.out.println(service.listAll());

        service.complete(1L);
        System.out.println("\nCompletadas:");
        System.out.println(service.listCompleted());

        service.reopen(1L);
        System.out.println("\nPendientes:");
        System.out.println(service.listPending());

        service.updateDescription(2L, "Comprar pan y leche");
        System.out.println("\nTodas (descripción editada):");
        System.out.println(service.listAll());

        service.delete(1L);
        System.out.println("\nTodas (tras borrar id=1):");
        System.out.println(service.listAll());

        SwingUtilities.invokeLater(() -> new TaskManagerFrame(service).setVisible(true));
    }

}
