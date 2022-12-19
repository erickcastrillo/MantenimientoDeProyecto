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

package gui.paneles;

import data.controladores.ProyectoControlador;
import data.controladores.UsuarioControlador;
import data.modelos.Proyecto;
import data.modelos.TipoUsuario;
import data.modelos.Usuario;
import gui.Constantes;
import gui.Custumizable;
import gui.PrincipalWindow;
import gui.helpers.UsuariosRenderer;
import gui.popups.JOptionsPaneUsuario;

import javax.swing.*;
import java.awt.*;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Objects;

public class ListaUsuariosPanel extends JPanel implements Custumizable {
    private DefaultListModel<Usuario> model;
    private JList<Usuario> lista;
    private final Usuario usuario;
    private final PrincipalWindow ventana;
    public ListaUsuariosPanel(PrincipalWindow ventana, Usuario usuario){
        this.usuario = usuario;
        this.ventana = ventana;
        inicializar();
    }
    @Override
    public void inicializar() {
        setPreferredSize(Constantes.DIMENSION_LISTA_USUARIOS);
        setMaximumSize(Constantes.DIMENSION_LISTA_USUARIOS);
        setMinimumSize(Constantes.DIMENSION_LISTA_USUARIOS);

        // Agregamos el titulo del panel
        JLabel titulo = new JLabel("Usuarios");
        titulo.setFont(Constantes.FUENTE_SUBTITULO);
        titulo.setBorder(Constantes.BORDER);
        titulo.setPreferredSize(new Dimension(300, 30));
        titulo.setMaximumSize(new Dimension(300, 30));
        titulo.setMinimumSize(new Dimension(300, 30));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        add(titulo);

        // Panel de proyectos
        JPanel panel = new JPanel();
        panel.setPreferredSize(Constantes.DIMENSION_LISTA_USUARIOS_LISTA);
        panel.setMaximumSize(Constantes.DIMENSION_LISTA_USUARIOS_LISTA);
        panel.setMinimumSize(Constantes.DIMENSION_LISTA_USUARIOS_LISTA);
        panel.setBackground(Constantes.COLOR_FONDO);

        // Agregar lista de proyectos
        model = new DefaultListModel<>();
        lista = new JList<>(model);
        lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lista.setLayoutOrientation(JList.VERTICAL);
        lista.setVisibleRowCount(-1);
        lista.setCellRenderer(new UsuariosRenderer());
        for (Usuario u : UsuarioControlador.listaUsuariosPorUsuario(usuario)) {
            model.addElement(u);
        }

        // Agregar un evento para cuando se seleccione un proyecto
        lista.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            if (evt.getClickCount() == 2) {
                Usuario u = lista.getSelectedValue();
                ventana.actualizarDetallesUsuario(u);
            }
            }
        });

        // Agregar scroll
        JScrollPane scrollPane = new JScrollPane(lista);
        scrollPane.setPreferredSize(Constantes.DIMENSION_LISTA_PROYECTOS_LISTA);
        scrollPane.setMaximumSize(Constantes.DIMENSION_LISTA_PROYECTOS_LISTA);
        scrollPane.setMinimumSize(Constantes.DIMENSION_LISTA_PROYECTOS_LISTA);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        panel.add(scrollPane);

        add(panel);

        // Agregamos el botón de editar proyecto
        JButton editarUsuarioButton = new JButton("Editar usuario");
        editarUsuarioButton.setFont(Constantes.FUENTE_NORMAL);
        editarUsuarioButton.setPreferredSize(Constantes.DIMENSION_BOTÓN);
        editarUsuarioButton.setMaximumSize(Constantes.DIMENSION_BOTÓN);
        editarUsuarioButton.setMinimumSize(Constantes.DIMENSION_BOTÓN);
        editarUsuarioButton.addActionListener(e -> {
            editarUsuario();
        });
        add(editarUsuarioButton);

        // Agregamos el botón de editar proyecto
        JButton verUsuarioButton = new JButton("Ver usuario");
        verUsuarioButton.setFont(Constantes.FUENTE_NORMAL);
        verUsuarioButton.setPreferredSize(Constantes.DIMENSION_BOTÓN);
        verUsuarioButton.setMaximumSize(Constantes.DIMENSION_BOTÓN);
        verUsuarioButton.setMinimumSize(Constantes.DIMENSION_BOTÓN);
        verUsuarioButton.addActionListener(e -> {
            verUsuario();
        });
        add(verUsuarioButton);

        if(usuario.getTipoUsuario() == TipoUsuario.ADMINISTRADOR){
            // Agregamos el botón de agregar proyecto
            JButton agregarUsuarioButton = new JButton("Agregar usuario");
            agregarUsuarioButton.setFont(Constantes.FUENTE_NORMAL);
            agregarUsuarioButton.setPreferredSize(Constantes.DIMENSION_BOTÓN);
            agregarUsuarioButton.setMaximumSize(Constantes.DIMENSION_BOTÓN);
            agregarUsuarioButton.setMinimumSize(Constantes.DIMENSION_BOTÓN);
            agregarUsuarioButton.addActionListener(e -> {
                agregarUsuario();
            });
            add(agregarUsuarioButton);

            // Agregamos el botón de eliminar proyecto
            JButton eliminarUsuarioButton = new JButton("Eliminar usuario");
            eliminarUsuarioButton.setFont(Constantes.FUENTE_NORMAL);
            eliminarUsuarioButton.setPreferredSize(Constantes.DIMENSION_BOTÓN);
            eliminarUsuarioButton.setMaximumSize(Constantes.DIMENSION_BOTÓN);
            eliminarUsuarioButton.setMinimumSize(Constantes.DIMENSION_BOTÓN);
            eliminarUsuarioButton.addActionListener(e -> {
                eliminarUsuario();
            });
            add(eliminarUsuarioButton);

            // Agregamos el botón de eliminar proyecto
            JButton desbloquearUsuarioButton = new JButton("Desbloquear/desbloquear usuario");
            desbloquearUsuarioButton.setFont(Constantes.FUENTE_NORMAL);
            desbloquearUsuarioButton.setPreferredSize(Constantes.DIMENSION_BOTÓN_GRANDE);
            desbloquearUsuarioButton.setMaximumSize(Constantes.DIMENSION_BOTÓN_GRANDE);
            desbloquearUsuarioButton.setMinimumSize(Constantes.DIMENSION_BOTÓN_GRANDE);
            desbloquearUsuarioButton.addActionListener(e -> {
                desbloquearUsuario();
            });
            add(desbloquearUsuarioButton);
        }
    }

    private void eliminarUsuario() {
        Usuario u = lista.getSelectedValue();
        if (u != null) {
            if(Objects.equals(u.getId(), usuario.getId())){
                JOptionPane.showMessageDialog(
                        this,
                        "No puedes eliminar tu propio usuario",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }
            int respuesta = JOptionPane.showConfirmDialog(null,
                    MessageFormat.format(
                            "¿Está seguro que desea eliminar el usuario {0}?",
                            u.nombreCompleto()
                    ),
                    Constantes.TITULO,
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
            );

            if (respuesta == JOptionPane.YES_OPTION){
                UsuarioControlador.eliminarUsuario(u.getId());
                model.removeElement(u);
                JOptionPane.showMessageDialog(null,
                        "Usuario eliminado correctamente",
                        Constantes.TITULO,
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        }
    }

    private void editarUsuario() {
        Usuario u = lista.getSelectedValue();
        if (u != null) {
            HashMap<String, String> usuarioData = JOptionsPaneUsuario.showUsuarioNuevoPrompt(
                    this,
                    "Editar usuario",
                    "Ingrese los datos del usuario",
                    u,
                    usuario.getTipoUsuario() == TipoUsuario.ADMINISTRADOR,
                    true
            );
            if(usuarioData != null){
                u.setCédula(usuarioData.get("cédula"));
                u.setNombre(usuarioData.get("nombre"));
                u.setPrimerApellido(usuarioData.get("primerApellido"));
                u.setSegundoApellido(usuarioData.get("segundoApellido"));
                u.setCorreoElectrónico(usuarioData.get("correoElectrónico"));
                u.setTeléfono(usuarioData.get("teléfono"));
                u.setClave(usuarioData.get("clave"));
                if(Objects.equals(usuarioData.get("tipoUsuario"), "Usuario")){
                    u.setTipoUsuario(TipoUsuario.USUARIO);
                } else if(Objects.equals(usuarioData.get("tipoUsuario"), "Administrador")){
                    u.setTipoUsuario(TipoUsuario.ADMINISTRADOR);
                }
                UsuarioControlador.actualizarUsuario(u);
                model.setElementAt(u, lista.getSelectedIndex());
            }
        }
    }

    private void verUsuario() {
        Usuario u = lista.getSelectedValue();
        if (u != null) {
            JOptionsPaneUsuario.showUsuarioNuevoPrompt(
                    this,
                    "Ver usuario",
                    "Ver los datos del usuario",
                    u,
                    usuario.getTipoUsuario() == TipoUsuario.ADMINISTRADOR,
                    false
            );
        }
    }

    private void agregarUsuario() {
        HashMap<String, String> usuarioData = JOptionsPaneUsuario.showUsuarioNuevoPrompt(
                this,
                "Nuevo usuario",
                "Ingrese los datos del usuario",
                null,
                usuario.getTipoUsuario() == TipoUsuario.ADMINISTRADOR,
                true
                );
        if(usuarioData != null){
            Usuario usuarioNuevo = new Usuario();
            usuarioNuevo.setCédula(usuarioData.get("cédula"));
            usuarioNuevo.setNombre(usuarioData.get("nombre"));
            usuarioNuevo.setPrimerApellido(usuarioData.get("primerApellido"));
            usuarioNuevo.setSegundoApellido(usuarioData.get("segundoApellido"));
            usuarioNuevo.setCorreoElectrónico(usuarioData.get("correoElectrónico"));
            usuarioNuevo.setTeléfono(usuarioData.get("teléfono"));
            usuarioNuevo.setClave(usuarioData.get("clave"));
            if(Objects.equals(usuarioData.get("tipoUsuario"), "Usuario")){
                usuarioNuevo.setTipoUsuario(TipoUsuario.USUARIO);
            } else if(Objects.equals(usuarioData.get("tipoUsuario"), "Administrador")){
                usuarioNuevo.setTipoUsuario(TipoUsuario.ADMINISTRADOR);
            }
            UsuarioControlador.agregarUsuario(usuarioNuevo);
            model.addElement(usuarioNuevo);
        }
    }

    private void desbloquearUsuario(){
        Usuario u = lista.getSelectedValue();
        if (u != null) {
            int respuesta = JOptionPane.showConfirmDialog(null,
                    MessageFormat.format(
                            "¿Está seguro que desea desbloquear/desbloquear el usuario {0}?",
                            u.nombreCompleto()
                    ),
                    Constantes.TITULO,
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
            );

            if (respuesta == JOptionPane.YES_OPTION){
                if(u.getBloqueado() != null){
                    u.setIntentosFallidos(0);
                    u.setBloqueado(!u.getBloqueado());
                }

                UsuarioControlador.actualizarUsuario(u);
                model.setElementAt(u, lista.getSelectedIndex());
                JOptionPane.showMessageDialog(null,
                        "Usuario actualizado correctamente",
                        Constantes.TITULO,
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        }
    }
    @Override
    public void actualizar() {
        model.clear();
        for (Usuario u : UsuarioControlador.listaUsuariosPorUsuario(usuario)) {
            model.addElement(u);
        }
    }
}
