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
import data.modelos.Hallazgo;
import data.modelos.Proyecto;
import data.modelos.TipoUsuario;
import data.modelos.Usuario;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Objects;

public class JOptionsPaneHallazgo {
    private static JLabel jLabelEncargadoHallazgo;
    private static JComboBox<Usuario> jComboBoxEncargadoHallazgo;
    public static HashMap<String, String> showHallazgoNuevoPrompt(
            Component parent,
            String message,
            String title,
            Usuario usuario,
            Hallazgo hallazgo
    ) {
        // Se crea un JLabel con el mensaje
        JLabel label = new JLabel(message);
        // Crear un JLabel para el nombre del proyecto
        JLabel jLabelNombreProyecto = new JLabel("Comentario del hallazgo:");
        // Se crea un JTextField para el nombre del proyecto
        JTextField JTextFieldNombreProyecto = new JTextField(20);
        if (hallazgo != null) {
            JTextFieldNombreProyecto.setText(hallazgo.getComentario());
        }

        if(usuario.getTipoUsuario() == TipoUsuario.ADMINISTRADOR){
            // Se crea un JLabel para  el encargado del proyecto
            jLabelEncargadoHallazgo = new JLabel("Creador del hallazgo:");
            jLabelEncargadoHallazgo.setAlignmentX(Component.LEFT_ALIGNMENT);
            // Se crea un dropdown para el encargado del proyecto
            jComboBoxEncargadoHallazgo = new JComboBox<>();
            // Se agregan los usuarios al JComboBox
            for (Usuario u : UsuarioControlador.listaUsuarios()) {
                jComboBoxEncargadoHallazgo.addItem(u);
            }
            if (hallazgo != null) {
                jComboBoxEncargadoHallazgo.setSelectedItem(
                        UsuarioControlador.getUsuario(hallazgo.getResponsableId())
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
        if (usuario.getTipoUsuario() == TipoUsuario.ADMINISTRADOR) {
            box.add(Box.createVerticalStrut(5));
            box.add(jLabelEncargadoHallazgo);
            box.add(Box.createVerticalStrut(5));
            box.add(jComboBoxEncargadoHallazgo);
        }
        box.add(Box.createVerticalStrut(10));

        int returnVal = JOptionPane.showConfirmDialog(parent, box, title, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (returnVal == JOptionPane.OK_OPTION) {
            HashMap<String, String> datosProyecto = new HashMap<>();
            datosProyecto.put("comentario", JTextFieldNombreProyecto.getText());
            if (usuario.getTipoUsuario() == TipoUsuario.ADMINISTRADOR) {
                if(jComboBoxEncargadoHallazgo.getSelectedItem() == null){
                    datosProyecto.put("responsableId", "");
                }else {
                    Usuario encargado = (Usuario) jComboBoxEncargadoHallazgo.getSelectedItem();
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
