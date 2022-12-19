/*
 * Copyright (c) 2022 Erick Castrillo Arroyo <ecastrillo@edu.upolitecnica.cr>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package gui.popups;

import data.controladores.UsuarioControlador;
import data.modelos.Proyecto;
import data.modelos.TipoUsuario;
import data.modelos.Usuario;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Objects;

public class JOptionsPaneProyecto {
    private static JLabel jLabelEncargadoProyecto;
    private static JComboBox<Usuario> jComboBoxEncargadoProyecto;
    public static HashMap<String, String> showProyectoNuevoPrompt(
            Component parent,
            String message,
            String title,
            Usuario usuario,
            Proyecto proyecto
    ) {
        // Se crea un JLabel con el mensaje
        JLabel label = new JLabel(message);
        // Crear un JLabel para el nombre del proyecto
        JLabel jLabelNombreProyecto = new JLabel("Nombre del proyecto:");
        // Se crea un JTextField para el nombre del proyecto
        JTextField JTextFieldNombreProyecto = new JTextField(20);
        if (proyecto != null) {
            JTextFieldNombreProyecto.setText(proyecto.getNombre());
        }
        // Crear un JLabel para la descripción del proyecto
        JLabel jLabelDescripciónProyecto = new JLabel("Descripción del proyecto:");
        // Se crea un JTextField para la descripción del proyecto
        JTextField JTextFieldDescripciónProyecto = new JTextField(20);
        if (proyecto != null) {
            JTextFieldDescripciónProyecto.setText(proyecto.getDescripción());
        }
        // Se crea un JLabel para el estado del proyecto
        JLabel jLabelEstadoProyecto = new JLabel("Estado del proyecto:");
        // Se crea un JComboBox para el estado del proyecto
        JComboBox<String> JComboBoxEstadoProyecto = new JComboBox<>();
        // Se agregan los estados al JComboBox
        JComboBoxEstadoProyecto.addItem("Nuevo");
        JComboBoxEstadoProyecto.addItem("Pendiente");
        JComboBoxEstadoProyecto.addItem("En desarrollo");
        JComboBoxEstadoProyecto.addItem("Suspendido");
        JComboBoxEstadoProyecto.addItem("Cancelado");
        JComboBoxEstadoProyecto.addItem("Terminado");
        JComboBoxEstadoProyecto.setSelectedIndex(0);
        if (proyecto != null) {
            switch (proyecto.getEstado()) {
                case NUEVO -> JComboBoxEstadoProyecto.setSelectedIndex(0);
                case PENDIENTE -> JComboBoxEstadoProyecto.setSelectedIndex(1);
                case EN_DESARROLLO -> JComboBoxEstadoProyecto.setSelectedIndex(2);
                case SUSPENDIDO -> JComboBoxEstadoProyecto.setSelectedIndex(3);
                case CANCELADO -> JComboBoxEstadoProyecto.setSelectedIndex(4);
                case TERMINADO -> JComboBoxEstadoProyecto.setSelectedIndex(5);
            }
        }

        if(usuario.getTipoUsuario() == TipoUsuario.ADMINISTRADOR){
            // Se crea un JLabel para  el encargado del proyecto
            jLabelEncargadoProyecto = new JLabel("Encargado del proyecto:");
            jLabelEncargadoProyecto.setAlignmentX(Component.LEFT_ALIGNMENT);
            // Se crea un dropdown para el encargado del proyecto
            jComboBoxEncargadoProyecto = new JComboBox<>();
            // Se agregan los usuarios al JComboBox
            for (Usuario u : UsuarioControlador.listaUsuarios()) {
                jComboBoxEncargadoProyecto.addItem(u);
            }
            if (proyecto != null) {
                jComboBoxEncargadoProyecto.setSelectedItem(
                        UsuarioControlador.getUsuario(proyecto.getResponsableId())
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
        box.add(JTextFieldNombreProyecto);
        box.add(Box.createVerticalStrut(5));
        box.add(jLabelDescripciónProyecto);
        box.add(Box.createVerticalStrut(5));
        box.add(JTextFieldDescripciónProyecto);
        box.add(Box.createVerticalStrut(5));
        box.add(jLabelEstadoProyecto);
        box.add(Box.createVerticalStrut(5));
        box.add(JComboBoxEstadoProyecto);
        if (usuario.getTipoUsuario() == TipoUsuario.ADMINISTRADOR) {
            box.add(Box.createVerticalStrut(5));
            box.add(jLabelEncargadoProyecto);
            box.add(Box.createVerticalStrut(5));
            box.add(jComboBoxEncargadoProyecto);
        }
        box.add(Box.createVerticalStrut(10));

        int returnVal = JOptionPane.showConfirmDialog(parent, box, title, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (returnVal == JOptionPane.OK_OPTION) {
            HashMap<String, String> datosProyecto = new HashMap<>();
            datosProyecto.put("nombre", JTextFieldNombreProyecto.getText());
            datosProyecto.put("descripción", JTextFieldDescripciónProyecto.getText());
            datosProyecto.put("estado", Objects.requireNonNull(JComboBoxEstadoProyecto.getSelectedItem()).toString());
            if (usuario.getTipoUsuario() == TipoUsuario.ADMINISTRADOR) {
                if(jComboBoxEncargadoProyecto.getSelectedItem() == null){
                    datosProyecto.put("responsableId", "");
                }else {
                    Usuario encargado = (Usuario) jComboBoxEncargadoProyecto.getSelectedItem();
                    datosProyecto.put("responsableId", encargado.getId());
                }
            } else {
                datosProyecto.put("responsableId", usuario.getId());
            }
            return datosProyecto;
        } else {
            return null;
        }
    }
}
