package todo;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TaskManagerFrame extends JFrame {   //  extendemos JFrame
    private final TaskService service;

    private final DefaultListModel<Task> model = new DefaultListModel<>();
    private final JList<Task> list = new JList<>(model);
    private final JTextField input = new JTextField();

    public TaskManagerFrame(TaskService service) {
        super("Gestor de Tareas");   // título de la ventana
        this.service = service;

        // Look & Feel moderno (FlatLaf). Si ya lo seteas en Main, puedes omitir aquí.
        try { UIManager.setLookAndFeel(new FlatLightLaf()); } catch (Exception ignored) {}

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(460, 520);
        setLocationRelativeTo(null);

        // ===== Top: input + botón Agregar
        JPanel top = new JPanel(new BorderLayout(10, 0));
        JLabel title = new JLabel("Nueva tarea");
        title.setFont(title.getFont().deriveFont(Font.BOLD, 14f));
        top.add(title, BorderLayout.WEST);
        top.add(input, BorderLayout.CENTER);
        JButton addBtn = new JButton("Agregar");
        addBtn.setFocusable(false);
        top.add(addBtn, BorderLayout.EAST);

        // ===== Centro: lista con scroll
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setVisibleRowCount(-1);
        JScrollPane scroll = new JScrollPane(list);

        // ===== Bottom: acciones
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        JButton completeBtn = new JButton("Completar");
        JButton deleteBtn = new JButton("Eliminar");
        completeBtn.setFocusable(false);
        deleteBtn.setFocusable(false);
        bottom.add(completeBtn);
        bottom.add(deleteBtn);

        // ===== Layout principal
        var root = new JPanel(new BorderLayout(12, 12));
        root.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        root.add(top, BorderLayout.NORTH);
        root.add(scroll, BorderLayout.CENTER);
        root.add(bottom, BorderLayout.SOUTH);
        setContentPane(root);

        // ===== Eventos
        addBtn.addActionListener(this::onAdd);
        completeBtn.addActionListener(this::onComplete);
        deleteBtn.addActionListener(this::onDelete);

        // Cargar lista inicial (si ya hay datos)
        refreshList();
    }

    private void onAdd(ActionEvent e) {
        try {
            String text = input.getText();
            Task t = service.add(text);
            input.setText("");
            model.addElement(t); // añadimos solo la nueva
            list.setSelectedValue(t, true);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Validación", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void onComplete(ActionEvent e) {
        Task selected = list.getSelectedValue();
        if (selected == null) {
            JOptionPane.showMessageDialog(this, "Selecciona una tarea", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        service.complete(selected.getId());
        refreshList();
    }

    private void onDelete(ActionEvent e) {
        Task selected = list.getSelectedValue();
        if (selected == null) {
            JOptionPane.showMessageDialog(this, "Selecciona una tarea", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        service.delete(selected.getId());
        refreshList();
    }

    private void refreshList() {
        model.clear();
        for (Task t : service.listAll()) {
            model.addElement(t);
        }
    }
}
