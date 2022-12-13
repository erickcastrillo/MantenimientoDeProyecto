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
import data.modelos.TipoUsuario;
import data.modelos.Usuario;

import javax.swing.*;

public class RegistrarWindow extends JFrame implements Custumizable {
    private JPanel panel;
    JLabel lblTitulo;
    private JLabel lblCédula;
    private JTextField txtCédula;
    private JLabel lblNombre;
    private JTextField txtNombre;
    private JLabel lblPrimerApellido;
    private JTextField txtPrimerApellido;
    private JLabel lblSegundoApellido;
    private JTextField txtSegundoApellido;
    private JLabel lblCorreoElectrónico;
    private JTextField txtCorreoElectrónico;
    private JLabel lblTeléfono;
    private JTextField txtTeléfono;
    private JLabel lblClave;
    private JPasswordField txtClave;
    private JButton btnRegistrar;
    private JButton btnSalir;

    // Constructor
    public RegistrarWindow() {
        // Inicializar la ventana
        inicializar();
    }
    // Inicializar la ventana
    @Override
    public void inicializar() {
        // Configurar la ventana
        setTitle("Registro");
        setSize(400, 700);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(null,
                        "¿Estás seguro de que quieres salir?",
                        "Registro",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
                    System.exit(0);
                }
            }
        });
        setLocationRelativeTo(null);
        setResizable(false);

        // Crear el panel
        panel = new JPanel();
        panel.setLayout(null);
        // Crear los componentes
        lblTitulo = new JLabel("Registro de usuario");
        lblTitulo.setBounds(100, 10, 300, 30);
        lblCédula = new JLabel("Cédula");
        lblCédula.setBounds(50, 50, 300, 30);
        txtCédula = new JTextField();
        txtCédula.setBounds(50, 80, 300, 30);
        lblNombre = new JLabel("Nombre");
        lblNombre.setBounds(50, 120, 300, 30);
        txtNombre = new JTextField();
        txtNombre.setBounds(50, 150, 300, 30);
        lblPrimerApellido = new JLabel("Primer Apellido");
        lblPrimerApellido.setBounds(50, 190, 300, 30);
        txtPrimerApellido = new JTextField();
        txtPrimerApellido.setBounds(50, 220, 300, 30);
        lblSegundoApellido = new JLabel("Segundo Apellido");
        lblSegundoApellido.setBounds(50, 260, 300, 30);
        txtSegundoApellido = new JTextField();
        txtSegundoApellido.setBounds(50, 290, 300, 30);
        lblCorreoElectrónico = new JLabel("Correo Electrónico");
        lblCorreoElectrónico.setBounds(50, 330, 300, 30);
        txtCorreoElectrónico = new JTextField();
        txtCorreoElectrónico.setBounds(50, 360, 300, 30);
        lblTeléfono = new JLabel("Teléfono");
        lblTeléfono.setBounds(50, 400, 300, 30);
        txtTeléfono = new JTextField();
        txtTeléfono.setBounds(50, 430, 300, 30);
        lblClave = new JLabel("Clave");
        lblClave.setBounds(50, 470, 300, 30);
        txtClave = new JPasswordField();
        txtClave.setBounds(50, 500, 300, 30);
        // Agregar los componentes al panel
        panel.add(lblTitulo);
        panel.add(lblCédula);
        panel.add(txtCédula);
        panel.add(lblNombre);
        panel.add(txtNombre);
        panel.add(lblPrimerApellido);
        panel.add(txtPrimerApellido);
        panel.add(lblSegundoApellido);
        panel.add(txtSegundoApellido);
        panel.add(lblCorreoElectrónico);
        panel.add(txtCorreoElectrónico);
        panel.add(lblTeléfono);
        panel.add(txtTeléfono);
        panel.add(lblClave);
        panel.add(txtClave);
        // Agregar los botones
        btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBounds(50, 610, 150, 30);
        btnRegistrar.addActionListener(e -> {registrar();});
        btnSalir = new JButton("Salir");
        btnSalir.setBounds(200, 610, 150, 30);
        btnSalir.addActionListener(e -> {salir();});
        panel.add(btnRegistrar);
        panel.add(btnSalir);
        // Agregar el panel a la ventana
        add(panel);
        // Mostrar la ventana
        setVisible(true);
    }

    @Override
    public void actualizar() {
        // No se necesita
    }

    // Registrar
    public void registrar() {
        // Validar los datos
        if (txtCédula.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "La cédula no puede estar vacía", "Registro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (txtNombre.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El nombre no puede estar vacío", "Registro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (txtPrimerApellido.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El primer apellido no puede estar vacío", "Registro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (txtSegundoApellido.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El segundo apellido no puede estar vacío", "Registro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (txtCorreoElectrónico.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El correo electrónico no puede estar vacío", "Registro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (txtTeléfono.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El teléfono no puede estar vacío", "Registro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (txtClave.getPassword().length == 0) {
            JOptionPane.showMessageDialog(null, "La clave no puede estar vacía", "Registro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Crear el usuario
        Usuario usuario = new Usuario();
        usuario.setCédula(txtCédula.getText());
        usuario.setNombre(txtNombre.getText());
        usuario.setPrimerApellido(txtPrimerApellido.getText());
        usuario.setSegundoApellido(txtSegundoApellido.getText());
        usuario.setCorreoElectrónico(txtCorreoElectrónico.getText());
        usuario.setTipoUsuario(TipoUsuario.USUARIO);
        usuario.setTeléfono(txtTeléfono.getText());
        usuario.setClave(new String(txtClave.getPassword()));
        // Registrar el usuario
        if (UsuarioControlador.agregarUsuario(usuario)) {
            JOptionPane.showMessageDialog(null, "Usuario registrado", "Registro", JOptionPane.INFORMATION_MESSAGE);
            limpiar();
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo registrar el usuario", "Registro", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void limpiar() {
        txtCédula.setText("");
        txtNombre.setText("");
        txtPrimerApellido.setText("");
        txtSegundoApellido.setText("");
        txtCorreoElectrónico.setText("");
        txtTeléfono.setText("");
        txtClave.setText("");
    }

    // Salir
    public void salir() {
        // Salir de la aplicación
        if (JOptionPane.showConfirmDialog(null,
                "¿Estás seguro de que quieres salir?",
                "Login",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
            SwingUtilities.invokeLater(() -> {
                new LoginWindow();
                dispose();
            });
        }
    }
}

