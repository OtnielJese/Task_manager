package todo;

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

    }
}
