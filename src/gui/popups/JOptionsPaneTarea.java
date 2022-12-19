/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package gui.popups;

import data.controladores.UsuarioControlador;
import data.modelos.Tarea;
import data.modelos.TipoUsuario;
import data.modelos.Usuario;
import java.awt.Component;
import java.util.HashMap;
import java.util.Objects;
import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Erick Castrillo Arroyo <ecastrillo@edu.upolitecnica.cr>
 * 603630082EC
 */
public class JOptionsPaneTarea {
    private static JLabel jLabelEncargadoTarea;
    private static JComboBox<Usuario> jComboBoxEncargadoTarea;
    public static HashMap<String, String> showTareaNuevoPrompt(
            Component parent,
            String message,
            String title,
            Usuario usuario,
            Tarea tarea
    ) {
        // Se crea un JLabel con el mensaje
        JLabel label = new JLabel(message);
        // Crear un JLabel para el nombre de la tarea
        JLabel jLabelNombreProyecto = new JLabel("Nombre de la tarea:");
        // Se crea un JTextField para el nombre de lq tarea
        JTextField JTextFieldNombreTarea = new JTextField(20);
        if (tarea != null) {
            JTextFieldNombreTarea.setText(tarea.getNombre());
        }
        // Crear un JLabel para la descripción de la tarea
        JLabel jLabelDescripciónTarea = new JLabel("Descripción de la tarea:");
        // Se crea un JTextField para la descripción de la tarea
        JTextField JTextFieldDescripciónTarea = new JTextField(20);
        if (tarea != null) {
            JTextFieldDescripciónTarea.setText(tarea.getDescripción());
        }
        // Se crea un JLabel para el estado de la tarea
        JLabel jLabelEstadoTarea = new JLabel("Estado de la tarea:");
        // Se crea un JComboBox para el estado de la tarea
        JComboBox<String> JComboBoxEstadoTarea = new JComboBox<>();
        // Se agregan los estados al JComboBox
        JComboBoxEstadoTarea.addItem("Nuevo");
        JComboBoxEstadoTarea.addItem("Pendiente");
        JComboBoxEstadoTarea.addItem("En desarrollo");
        JComboBoxEstadoTarea.addItem("Suspendido");
        JComboBoxEstadoTarea.addItem("Cancelado");
        JComboBoxEstadoTarea.addItem("Terminado");
        JComboBoxEstadoTarea.setSelectedIndex(0);
        if (tarea != null) {
            switch (tarea.getEstado()) {
                case NUEVO -> JComboBoxEstadoTarea.setSelectedIndex(0);
                case PENDIENTE -> JComboBoxEstadoTarea.setSelectedIndex(1);
                case EN_DESARROLLO -> JComboBoxEstadoTarea.setSelectedIndex(2);
                case SUSPENDIDO -> JComboBoxEstadoTarea.setSelectedIndex(3);
                case CANCELADO -> JComboBoxEstadoTarea.setSelectedIndex(4);
                case TERMINADO -> JComboBoxEstadoTarea.setSelectedIndex(5);
            }
        }

        if(usuario.getTipoUsuario() == TipoUsuario.ADMINISTRADOR){
            // Se crea un JLabel para  el encargado del proyecto
            jLabelEncargadoTarea = new JLabel("Encargado de la tarea:");
            jLabelEncargadoTarea.setAlignmentX(Component.LEFT_ALIGNMENT);
            // Se crea un dropdown para el encargado del proyecto
            jComboBoxEncargadoTarea = new JComboBox<>();
            // Se agregan los usuarios al JComboBox
            for (Usuario u : UsuarioControlador.listaUsuarios()) {
                jComboBoxEncargadoTarea.addItem(u);
            }
            if (tarea != null) {
                jComboBoxEncargadoTarea.setSelectedItem(
                        UsuarioControlador.getUsuario(tarea.getResponsableId())
                );
            }
        }

        // Se crea un JPanel para agregar los componentes
        Box box = Box.createVerticalBox();
        box.setAlignmentX(Component.LEFT_ALIGNMENT);
        // Se agregan los componentes al Box
        box.add(label);
        // Este es un componente invisible que sirve para dar espacio entre los componentes
        box.add(Box.createVerticalStrut(30));
        box.add(jLabelNombreProyecto);
        box.add(Box.createVerticalStrut(5));
        box.add(JTextFieldNombreTarea);
        box.add(Box.createVerticalStrut(5));
        box.add(jLabelDescripciónTarea);
        box.add(Box.createVerticalStrut(5));
        box.add(JTextFieldDescripciónTarea);
        box.add(Box.createVerticalStrut(5));
        box.add(jLabelEstadoTarea);
        box.add(Box.createVerticalStrut(5));
        box.add(JComboBoxEstadoTarea);
        if (usuario.getTipoUsuario() == TipoUsuario.ADMINISTRADOR) {
            box.add(Box.createVerticalStrut(5));
            box.add(jLabelEncargadoTarea);
            box.add(Box.createVerticalStrut(5));
            box.add(jComboBoxEncargadoTarea);
        }
        box.add(Box.createVerticalStrut(10));

        int returnVal = JOptionPane.showConfirmDialog(parent, box, title, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (returnVal == JOptionPane.OK_OPTION) {
            HashMap<String, String> datosTarea = new HashMap<>();
            datosTarea.put("nombre", JTextFieldNombreTarea.getText());
            datosTarea.put("descripción", JTextFieldDescripciónTarea.getText());
            datosTarea.put("estado", Objects.requireNonNull(JComboBoxEstadoTarea.getSelectedItem()).toString());
            if (usuario.getTipoUsuario() == TipoUsuario.ADMINISTRADOR) {
                if(jComboBoxEncargadoTarea.getSelectedItem() == null){
                    datosTarea.put("responsableId", "");
                }else {
                    Usuario encargado = (Usuario) jComboBoxEncargadoTarea.getSelectedItem();
                    datosTarea.put("responsableId", encargado.getId());
                }
            } else {
                datosTarea.put("responsableId", usuario.getId());
            }
            return datosTarea;
        } else {
            return null;
        }
    }
}
