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
import data.modelos.Proyecto;
import data.modelos.Tarea;
import data.modelos.Usuario;
import gui.paneles.*;

import javax.swing.*;
import java.awt.*;

public class PrincipalWindow extends JFrame implements Custumizable {
    private final Usuario usuario;
    private ListaProyectosPanel listaProyectos;
    private ListaTareasPanel listaTareas;
    private ListaHallazgosPanel hallazgosPanel;
    public PrincipalWindow(Usuario usuario) {
        if(usuario == null) {
            this.usuario = UsuarioControlador.getUsuarioPorTeléfono("71129023");
        } else {
            this.usuario = usuario;
        }
        inicializar();
    }

    @Override
    public void inicializar() {
        setTitle(Constantes.TITULO);
        setSize(Constantes.ANCHO_VENTANA, Constantes.ALTO_VENTANA);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(null,
                        "¿Estás seguro de que quieres salir?",
                        Constantes.TITULO,
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
                    System.exit(0);
                }
            }
        });
        // Cambiar el color de fondo
        getContentPane().setBackground(Constantes.COLOR_FONDO);
        // TODO: Cambiar el icono de la ventana

        // Agregar titulo a la ventana
        JLabel titulo = new JLabel(Constantes.TITULO_VENTANA_PRINCIPAL + usuario.nombreCompleto());
        titulo.setFont(Constantes.FUENTE_TITULO);
        titulo.setHorizontalAlignment(JLabel.CENTER);
        titulo.setVerticalAlignment(JLabel.CENTER);
        titulo.setBorder(Constantes.BORDER);

        add(titulo, BorderLayout.NORTH);

        JTabbedPane panelTab = new JTabbedPane();
        panelTab.setBackground(Constantes.COLOR_FONDO);

        JPanel panelProyectos = new JPanel();
        panelProyectos.setBackground(Constantes.COLOR_FONDO);
        panelProyectos.setLayout(new BorderLayout());

        // Agregar el panel de proyectos
        listaProyectos = new ListaProyectosPanel(this, usuario);
        panelProyectos.add(listaProyectos, BorderLayout.WEST);

        // Agregar el panel de tareas
        listaTareas = new ListaTareasPanel(this,usuario);

        // Agregar el panel de tareas al panel de proyectos
        panelProyectos.add(listaTareas, BorderLayout.CENTER);

        // Agregar el panel de hallazgos
        hallazgosPanel = new ListaHallazgosPanel(this, usuario);

        // Agregar el panel de hallazgos al panel de proyectos
        panelProyectos.add(hallazgosPanel, BorderLayout.EAST);

        // Panel de lista de usuarios y datos de usuario
        ListaUsuariosPanel listaUsuariosPanel = new ListaUsuariosPanel(this, usuario);
        DatosUsuariosPanel datosUsuariosPanel = new DatosUsuariosPanel();
        // Panel de usuarios
        JPanel panelUsuarios = new JPanel();
        panelUsuarios.setBackground(Constantes.COLOR_FONDO);
        panelUsuarios.setLayout(new BorderLayout());
        // Agregamos los paneles relacionados a usuarios
        panelUsuarios.add(listaUsuariosPanel, BorderLayout.WEST);
        panelUsuarios.add(datosUsuariosPanel, BorderLayout.CENTER);

        panelTab.addTab("Proyectos", panelProyectos);
        panelTab.addTab("Administración", panelUsuarios);
        
        add(panelTab, BorderLayout.CENTER);

        // Mostrar la ventana
        setVisible(true);
    }

    @Override
    public void actualizar() {
        listaProyectos.actualizar();
    }

    public void actualizarListaTareasProyectos(Proyecto proyecto) {
        System.out.println("Actualizando lista de tareas del proyecto " + proyecto.getNombre());
        listaTareas.setProyecto(proyecto);
        listaTareas.actualizar();
        revalidate();
        repaint();
    }

    public void actualizarListaHallazgosTarea(Tarea tarea){
        System.out.println("Actualizando lista de hallazgos de la tarea " + tarea.getNombre());
        hallazgosPanel.setTarea(tarea);
        hallazgosPanel.actualizar();
        revalidate();
        repaint();
    }

    public void actualizarListaHallazgos(){
        System.out.println("Actualizando lista de hallazgos");
        hallazgosPanel.setTarea(null);
        hallazgosPanel.actualizar();
        revalidate();
        repaint();
    }

    public void actualizarDetallesUsuario(Usuario usuario){
        System.out.println("Actualizando detalles de usuario " + usuario.nombreCompleto());
        // TODO: Actualizar detalles de usuario
        revalidate();
        repaint();
    }
}
