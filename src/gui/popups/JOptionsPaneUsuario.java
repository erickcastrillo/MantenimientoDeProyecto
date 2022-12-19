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

import data.modelos.TipoUsuario;
import data.modelos.Usuario;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Objects;

public class JOptionsPaneUsuario {
    private static JComboBox<String> jComboBoxTipoUsuario;
    private static JLabel lblTipoUsuario;
    public static HashMap<String, String> showUsuarioNuevoPrompt(
            Component parent,
            String message,
            String title,
            Usuario usuario,
            Boolean admin,
            Boolean editable
    ){
        // Crear los componentes
        JLabel lblTitulo = new JLabel("Registro de usuario");

        // Cédula
        JLabel lblCédula = new JLabel("Cédula");
        JTextField txtCédula = new JTextField();
        if(usuario != null) txtCédula.setText(usuario.getCédula());
        txtCédula.setEditable(editable);

        // Nombre
        JLabel lblNombre = new JLabel("Nombre");
        JTextField txtNombre = new JTextField();
        if(usuario != null) txtNombre.setText(usuario.getNombre());
        txtNombre.setEditable(editable);

        // Primer apellido
        JLabel lblPrimerApellido = new JLabel("Primer Apellido");
        JTextField txtPrimerApellido = new JTextField();
        if(usuario != null) txtPrimerApellido.setText(usuario.getPrimerApellido());
        txtPrimerApellido.setEditable(editable);

        // Segundo apellido
        JLabel lblSegundoApellido = new JLabel("Segundo Apellido");
        JTextField txtSegundoApellido = new JTextField();
        if(usuario != null) txtSegundoApellido.setText(usuario.getSegundoApellido());
        txtSegundoApellido.setEditable(editable);

        // Correo electrónico
        JLabel lblCorreoElectrónico = new JLabel("Correo Electrónico");
        JTextField txtCorreoElectrónico = new JTextField();
        if(usuario != null) txtCorreoElectrónico.setText(usuario.getCorreoElectrónico());
        txtCorreoElectrónico.setEditable(editable);

        // Teléfono
        JLabel lblTeléfono = new JLabel("Teléfono");
        JTextField txtTeléfono = new JTextField();
        if(usuario != null) txtTeléfono.setText(usuario.getTeléfono());
        txtTeléfono.setEditable(editable);

        // Clave
        JLabel lblClave = new JLabel("Clave");
        JPasswordField txtClave = new JPasswordField();
        if(usuario != null) txtClave.setText(usuario.getClave());
        txtClave.setEditable(editable);

        // Si el usuario es administrador entonces mostrar opciones para cambiar tipo de usuario
        if(admin){
            lblTipoUsuario = new JLabel("Tipo de usuario");
            jComboBoxTipoUsuario = new JComboBox<>();
            jComboBoxTipoUsuario.addItem("Usuario");
            jComboBoxTipoUsuario.addItem("Administrador");
            if(usuario != null){
                if(usuario.getTipoUsuario() == TipoUsuario.USUARIO){
                    jComboBoxTipoUsuario.setSelectedItem("Usuario");
                } else if (usuario.getTipoUsuario() == TipoUsuario.ADMINISTRADOR){
                    jComboBoxTipoUsuario.setSelectedItem("Administrador");
                }
            }
            jComboBoxTipoUsuario.setEditable(editable);
        }

        // Se crea un JPanel para agregar los componentes
        Box box = Box.createVerticalBox();
        box.setAlignmentX(Component.LEFT_ALIGNMENT);
        // Se agregan los componentes al Box
        box.add(lblTitulo);
        // Este es un componente invisible que sirve para dar espacio entre los componentes
        box.add(Box.createVerticalStrut(30));
        // Agregar los componentes al panel
        box.add(lblCédula);
        box.add(Box.createVerticalStrut(5));
        box.add(txtCédula);
        box.add(Box.createVerticalStrut(5));
        box.add(lblNombre);
        box.add(Box.createVerticalStrut(5));
        box.add(txtNombre);
        box.add(Box.createVerticalStrut(5));
        box.add(lblPrimerApellido);
        box.add(Box.createVerticalStrut(5));
        box.add(txtPrimerApellido);
        box.add(Box.createVerticalStrut(5));
        box.add(lblSegundoApellido);
        box.add(Box.createVerticalStrut(5));
        box.add(txtSegundoApellido);
        box.add(Box.createVerticalStrut(5));
        box.add(lblCorreoElectrónico);
        box.add(Box.createVerticalStrut(5));
        box.add(txtCorreoElectrónico);
        box.add(Box.createVerticalStrut(5));
        box.add(lblTeléfono);
        box.add(Box.createVerticalStrut(5));
        box.add(txtTeléfono);
        box.add(Box.createVerticalStrut(5));
        box.add(lblClave);
        box.add(Box.createVerticalStrut(5));
        box.add(txtClave);
        if(admin){
            box.add(Box.createVerticalStrut(5));
            box.add(lblTipoUsuario);
            box.add(Box.createVerticalStrut(5));
            box.add(jComboBoxTipoUsuario);
        }
        box.add(Box.createVerticalStrut(10));

        int returnVal = JOptionPane.showConfirmDialog(parent, box, title, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (returnVal == JOptionPane.OK_OPTION) {
            HashMap<String, String> datosUsuario = new HashMap<>();
            datosUsuario.put("cédula", txtCédula.getText());
            datosUsuario.put("nombre", txtNombre.getText());
            datosUsuario.put("primerApellido", txtPrimerApellido.getText());
            datosUsuario.put("segundoApellido", txtSegundoApellido.getText());
            datosUsuario.put("correoElectrónico", txtCorreoElectrónico.getText());
            datosUsuario.put("teléfono", txtTeléfono.getText());
            datosUsuario.put("clave", new String(txtClave.getPassword()));
            datosUsuario.put("tipoUsuario", Objects.requireNonNull(jComboBoxTipoUsuario.getSelectedItem()).toString());

            return datosUsuario;
        } else {
            return null;
        }
    }
}
