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

package gui;

import data.controladores.UsuarioControlador;
import data.modelos.Usuario;
import utilidades.Seguridad;
import gui.RegistrarWindow;

import javax.mail.MessagingException;
import javax.swing.*;

public class LoginWindow extends JFrame implements Custumizable {
    private JPanel panel;
    private JLabel lblUsuario;
    private JLabel lblContraseña;
    private JTextField txtUsuario;
    private JPasswordField txtContraseña;
    private JButton btnIngresar;
    private JButton btnSalir;
    private JButton btnRegistrarse;
    private JButton btnOlvidéMiContraseña;
    public LoginWindow() {
        inicializar();
    }
    @Override
    public void inicializar() {
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(null,
                        "¿Estás seguro de que quieres salir?",
                        "Login",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
                    System.exit(0);
                }
            }
        });
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Login");
        panel = new JPanel();
        panel.setLayout(null);
        lblUsuario = new JLabel("Usuario");
        lblUsuario.setBounds(50, 50, 100, 30);
        lblContraseña = new JLabel("Contraseña");
        lblContraseña.setBounds(50, 100, 100, 30);
        txtUsuario = new JTextField();
        txtUsuario.setBounds(150, 50, 200, 30);
        txtContraseña = new JPasswordField();
        txtContraseña.setBounds(150, 100, 200, 30);
        btnIngresar = new JButton("Ingresar");
        btnIngresar.addActionListener(e -> {iniciarSesión();});
        btnIngresar.setBounds(50, 150, 100, 30);
        btnSalir = new JButton("Salir");
        btnSalir.addActionListener(e -> {salir();});
        btnSalir.setBounds(250, 150, 100, 30);
        btnRegistrarse = new JButton("Registrarse");
        btnRegistrarse.addActionListener(e -> {registrarse();});
        btnRegistrarse.setBounds(50, 200, 100, 30);
        btnOlvidéMiContraseña = new JButton("Olvidé mi contraseña");
        btnOlvidéMiContraseña.addActionListener(e -> {recuperarContraseña();});
        btnOlvidéMiContraseña.setBounds(200, 200, 150, 30);
        panel.add(lblUsuario);
        panel.add(lblContraseña);
        panel.add(txtUsuario);
        panel.add(txtContraseña);
        panel.add(btnIngresar);
        panel.add(btnSalir);
        panel.add(btnRegistrarse);
        panel.add(btnOlvidéMiContraseña);
        add(panel);
        setVisible(true);
    }

    // Método para completar el proceso de inicio de sesión
    public void iniciarSesión() {
        // Obtener el usuario y la contraseña
        String nombreUsuario = txtUsuario.getText();
        String contraseña = new String(txtContraseña.getPassword());
        // Validar que el usuario y la contraseña no estén vacíos
        if (nombreUsuario.isEmpty() || contraseña.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "El usuario y la contraseña no pueden estar vacíos",
                    "Login",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Validar que el usuario y la contraseña sean correctos
        Usuario usuario = UsuarioControlador.loguearUsuario(nombreUsuario, contraseña);
        if (usuario == null) {
            JOptionPane.showMessageDialog(null,
                    "El usuario y la contraseña no son correctos",
                    "Login",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // TODO: Abrir la ventana principal
    }

    // Método para completar el proceso de registro
    public void registrarse() {
        SwingUtilities.invokeLater(() -> {
            new RegistrarWindow();
            dispose();
        });
    }

    // Método para completar el proceso de recuperación de contraseña
    public void recuperarContraseña() {
        // Obtenemos el número de teléfono del usuario mediante un JOptionPane
        String telefono = JOptionPane.showInputDialog(null,
                "Ingrese su número de teléfono",
                "Recuperar contraseña",
                JOptionPane.QUESTION_MESSAGE);
        // Validamos que el número de teléfono no esté vacío ni sea nulo
        if (telefono == null || telefono.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "El número de teléfono no puede estar vacío",
                    "Recuperar contraseña",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Buscamos el usuario con el número de teléfono ingresado
        Usuario usuario = UsuarioControlador.getUsuarioPorTeléfono(telefono);
        // Validamos que el usuario exista
        if (usuario == null) {
            JOptionPane.showMessageDialog(null,
                    "El número de teléfono no está registrado",
                    "Recuperar contraseña",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Enviamos el código de recuperación al usuario
        try {
            Seguridad.enviarCorreoDeVerificación(usuario);
        } catch (MessagingException e) {
            JOptionPane.showMessageDialog(null,
                    "No se pudo enviar el correo de verificación",
                    "Recuperar contraseña",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

    }

    // Método para completar el proceso de salir
    public void salir() {
        // Salir de la aplicación
        if (JOptionPane.showConfirmDialog(null,
                "¿Estás seguro de que quieres salir?",
                "Login",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }



    @Override
    public void actualizar() {

    }
}
