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
import data.modelos.Usuario;
import gui.paneles.ListaHallazgosPanel;
import gui.paneles.ListaProyectosPanel;
import gui.paneles.ListaTareasPanel;

import javax.swing.*;
import java.awt.*;

public class PrincipalWindow extends JFrame implements Custumizable {
    private final Usuario usuario;
    private ListaProyectosPanel listaProyectos;
    private ListaTareasPanel listaTareas;
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

        // TODO: Agregar menú

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
        ListaHallazgosPanel hallazgosPanel = new ListaHallazgosPanel(this, usuario);

        // Agregar el panel de hallazgos al panel de proyectos
        panelProyectos.add(hallazgosPanel, BorderLayout.EAST);

        panelTab.addTab("Proyectos", panelProyectos);
        
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
}
