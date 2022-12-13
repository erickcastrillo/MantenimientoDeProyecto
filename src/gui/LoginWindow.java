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
    private JButton btnDesbloquearCuenta;
    public LoginWindow() {
        inicializar();
    }
    @Override
    public void inicializar() {
        setSize(400, 350);
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
        lblUsuario = new JLabel("Teléfono");
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
        btnDesbloquearCuenta = new JButton("Desbloquear cuenta");
        btnDesbloquearCuenta.addActionListener(e -> {desbloquearCuenta();});
        btnDesbloquearCuenta.setBounds(200, 250, 150, 30);
        panel.add(lblUsuario);
        panel.add(lblContraseña);
        panel.add(txtUsuario);
        panel.add(txtContraseña);
        panel.add(btnIngresar);
        panel.add(btnSalir);
        panel.add(btnRegistrarse);
        panel.add(btnOlvidéMiContraseña);
        panel.add(btnDesbloquearCuenta);
        add(panel);
        setVisible(true);
    }

    // Desbloquear cuenta
    private void desbloquearCuenta() {
        // Solicitar teléfono
        String teléfono = JOptionPane.showInputDialog(null, "Ingrese su teléfono", "Desbloquear cuenta", JOptionPane.QUESTION_MESSAGE);
        // Validar teléfono
        if (teléfono == null || teléfono.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un teléfono", "Desbloquear cuenta", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Buscar usuario por teléfono
        Usuario usuario = UsuarioControlador.getUsuarioPorTeléfono(teléfono);
        // Validar usuario
        if (usuario == null) {
            JOptionPane.showMessageDialog(null, "No existe un usuario con ese teléfono", "Desbloquear cuenta", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Solicitar código de verificación
        String código = JOptionPane.showInputDialog(null, "Ingrese el código de verificación", "Desbloquear cuenta", JOptionPane.QUESTION_MESSAGE);
        // Validar código de verificación
        if (código == null || código.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un código de verificación", "Desbloquear cuenta", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Validar código de verificación
        if (!código.equals(usuario.getCódigoDeVerificación())) {
            JOptionPane.showMessageDialog(null, "El código de verificación es incorrecto", "Desbloquear cuenta", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Crear una contraseña nueva
        String contraseña = JOptionPane.showInputDialog(null, "Ingrese una nueva contraseña", "Desbloquear cuenta", JOptionPane.QUESTION_MESSAGE);
        // Volver a validar la contraseña
        String contraseña2 = JOptionPane.showInputDialog(null, "Ingrese nuevamente la contraseña", "Desbloquear cuenta", JOptionPane.QUESTION_MESSAGE);
        // Validar contraseñas
        if (!contraseña.equals(contraseña2)) {
            JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden", "Desbloquear cuenta", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Actualizar contraseña
        usuario.setClave(contraseña);

        // Desbloquear usuario
        usuario.setBloqueado(false);
        usuario.setIntentosFallidos(0);
        UsuarioControlador.actualizarUsuario(usuario);
        JOptionPane.showMessageDialog(null, "Cuenta desbloqueada y contraseña restablecida", "Desbloquear cuenta", JOptionPane.INFORMATION_MESSAGE);
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
        Usuario usuario = UsuarioControlador.getUsuarioPorTeléfono(nombreUsuario);
        // Validar que el usuario exista
        if (usuario == null) {
            JOptionPane.showMessageDialog(null,
                    "No se pudo validar las credenciales",
                    "Login",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Validar que el usuario no esté bloqueado
        if (usuario.getBloqueado() != null && usuario.getBloqueado()) {
            JOptionPane.showMessageDialog(null,
                    "El usuario se encuentra bloqueado",
                    "Login",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Validar que la contraseña sea correcta
        if (!usuario.getClave().equals(contraseña)) {
            // Actualizar intentos fallidos
            usuario.setIntentosFallidos(usuario.getIntentosFallidos() + 1);
            // Validar si el usuario debe ser bloqueado
            if (usuario.getIntentosFallidos() >= 3) {
                usuario.setBloqueado(true);
            }
            // Actualizar usuario
            UsuarioControlador.actualizarUsuario(usuario);
            JOptionPane.showMessageDialog(null,
                    "No se pudo validar las credenciales",
                    "Login",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Mostrar mensaje de bienvenida
        JOptionPane.showMessageDialog(null,
                "Bienvenido " + usuario.getNombre(),
                "Login",
                JOptionPane.INFORMATION_MESSAGE);

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
        String teléfono = JOptionPane.showInputDialog(null,
                "Ingrese su número de teléfono",
                "Recuperar contraseña",
                JOptionPane.QUESTION_MESSAGE);
        // Validamos que el número de teléfono no esté vacío ni sea nulo
        if (teléfono == null || teléfono.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "El número de teléfono no puede estar vacío",
                    "Recuperar contraseña",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Buscamos el usuario con el número de teléfono ingresado
        Usuario usuario = UsuarioControlador.getUsuarioPorTeléfono(teléfono);
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
            // Mostrar mensaje de éxito
            JOptionPane.showMessageDialog(null,
                    "Se ha enviado un código de verificación a su correo electrónico",
                    "Recuperar contraseña",
                    JOptionPane.INFORMATION_MESSAGE);
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
