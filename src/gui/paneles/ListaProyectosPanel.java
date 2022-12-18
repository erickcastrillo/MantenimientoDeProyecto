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
import data.modelos.Estado;
import data.modelos.Proyecto;
import data.modelos.Usuario;
import gui.Constantes;
import gui.Custumizable;
import gui.PrincipalWindow;
import gui.helpers.ProyectoRenderer;
import gui.popups.JOptionsPaneProyecto;

import javax.swing.*;
import java.text.MessageFormat;
import java.util.HashMap;

public class ListaProyectosPanel extends JPanel implements Custumizable {
    private DefaultListModel<Proyecto> model;
    private JList<Proyecto> lista;
    private final Usuario usuario;
    private final PrincipalWindow ventana;
    public ListaProyectosPanel(PrincipalWindow ventana, Usuario usuario) {
        this.usuario = usuario;
        this.ventana = ventana;
        inicializar();
    }

    @Override
    public void inicializar() {
        setPreferredSize(Constantes.DIMENSION_LISTA_PROYECTOS);
        setMaximumSize(Constantes.DIMENSION_LISTA_PROYECTOS);
        setMinimumSize(Constantes.DIMENSION_LISTA_PROYECTOS);

        // Agregamos el titulo del panel
        JLabel titulo = new JLabel("Proyectos");
        titulo.setFont(Constantes.FUENTE_SUBTITULO);
        titulo.setBorder(Constantes.BORDER);
        add(titulo);

        // Panel de proyectos
        JPanel panel = new JPanel();
        panel.setPreferredSize(Constantes.DIMENSION_LISTA_PROYECTOS_LISTA);
        panel.setMaximumSize(Constantes.DIMENSION_LISTA_PROYECTOS_LISTA);
        panel.setMinimumSize(Constantes.DIMENSION_LISTA_PROYECTOS_LISTA);
        panel.setBackground(Constantes.COLOR_FONDO);

        // Agregar lista de proyectos
        model = new DefaultListModel<>();
        lista = new JList<>(model);
        lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lista.setLayoutOrientation(JList.VERTICAL);
        lista.setVisibleRowCount(-1);
        lista.setCellRenderer(new ProyectoRenderer());
        for(Proyecto proyecto : ProyectoControlador.obtenerProyectosPorResponsable(usuario.getId())) {
            model.addElement(proyecto);
        }
        // Agregar un evento para cuando se seleccione un proyecto
        lista.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    Proyecto proyecto = lista.getSelectedValue();
                    ventana.actualizarListaTareasProyectos(proyecto);
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

        // Agregamos el botón de agregar proyecto
        JButton agregarProyectoButton = new JButton("Agregar proyecto");
        agregarProyectoButton.setFont(Constantes.FUENTE_NORMAL);
        agregarProyectoButton.setPreferredSize(Constantes.DIMENSION_BOTÓN);
        agregarProyectoButton.setMaximumSize(Constantes.DIMENSION_BOTÓN);
        agregarProyectoButton.setMinimumSize(Constantes.DIMENSION_BOTÓN);
        agregarProyectoButton.addActionListener(e -> {
            agregarProyecto();
        });
        add(agregarProyectoButton);

        // Agregamos el botón de editar proyecto
        JButton editarProyectoButton = new JButton("Editar proyecto");
        editarProyectoButton.setFont(Constantes.FUENTE_NORMAL);
        editarProyectoButton.setPreferredSize(Constantes.DIMENSION_BOTÓN);
        editarProyectoButton.setMaximumSize(Constantes.DIMENSION_BOTÓN);
        editarProyectoButton.setMinimumSize(Constantes.DIMENSION_BOTÓN);
        editarProyectoButton.addActionListener(e -> {
            editarProyecto();
        });
        add(editarProyectoButton);

        // Agregamos el botón de eliminar proyecto
        JButton eliminarProyectoButton = new JButton("Eliminar proyecto");
        eliminarProyectoButton.setFont(Constantes.FUENTE_NORMAL);
        eliminarProyectoButton.setPreferredSize(Constantes.DIMENSION_BOTÓN);
        eliminarProyectoButton.setMaximumSize(Constantes.DIMENSION_BOTÓN);
        eliminarProyectoButton.setMinimumSize(Constantes.DIMENSION_BOTÓN);
        eliminarProyectoButton.addActionListener(e -> {
            eliminarProyecto();
        });
        add(eliminarProyectoButton);
    }

    @Override
    public void actualizar() {
        model.clear();
        for(Proyecto proyecto : ProyectoControlador.obtenerProyectosPorResponsable(usuario.getId())) {
            model.addElement(proyecto);
        }
    }

    public void agregarProyecto(){
        HashMap<String, String> proyectoData = JOptionsPaneProyecto.showProyectoNuevoPrompt(
                null,
                "Nuevo proyecto",
                "Ingrese los datos del proyecto",
                usuario,
                null
        );
        if(proyectoData != null) {
            Proyecto proyecto = new Proyecto();
            proyecto.setNombre(proyectoData.get("nombre"));
            proyecto.setDescripción(proyectoData.get("descripción"));
            switch (proyectoData.get("estado")) {
                case "Nuevo" -> proyecto.setEstado(Estado.NUEVO);
                case "Pendiente" -> proyecto.setEstado(Estado.PENDIENTE);
                case "En desarrollo" -> proyecto.setEstado(Estado.EN_DESARROLLO);
                case "Suspendido" -> proyecto.setEstado(Estado.SUSPENDIDO);
                case "Cancelado" -> proyecto.setEstado(Estado.CANCELADO);
                case "Terminado" -> proyecto.setEstado(Estado.TERMINADO);
            }
            if(proyectoData.get("responsableId") != null) {
                proyecto.setResponsableId(proyectoData.get("responsableId"));
            }
            ProyectoControlador.agregarProyecto(proyecto);
            model.addElement(proyecto);
        }
    }

    public void editarProyecto(){
        if (lista.getSelectedValue() != null) {
            Proyecto proyecto = lista.getSelectedValue();
            HashMap<String, String> proyectoData = JOptionsPaneProyecto.showProyectoNuevoPrompt(
                    null,
                    "Nuevo proyecto",
                    "Ingrese los datos del proyecto",
                    usuario,
                    proyecto
            );
            if (proyectoData!= null) {
                proyecto.setNombre(proyectoData.get("nombre"));
                proyecto.setDescripción(proyectoData.get("descripción"));
                switch (proyectoData.get("estado")) {
                    case "Nuevo" -> proyecto.setEstado(Estado.NUEVO);
                    case "Pendiente" -> proyecto.setEstado(Estado.PENDIENTE);
                    case "En desarrollo" -> proyecto.setEstado(Estado.EN_DESARROLLO);
                    case "Suspendido" -> proyecto.setEstado(Estado.SUSPENDIDO);
                    case "Cancelado" -> proyecto.setEstado(Estado.CANCELADO);
                    case "Terminado" -> proyecto.setEstado(Estado.TERMINADO);
                }
                if (proyectoData.get("responsableId") != null) {
                    proyecto.setResponsableId(proyectoData.get("responsableId"));
                }
                ProyectoControlador.actualizarProyecto(proyecto);
                model.setElementAt(proyecto, lista.getSelectedIndex());
            }
        }
    }

    public void eliminarProyecto(){
        if (lista.getSelectedValue() != null) {
            int respuesta = JOptionPane.showConfirmDialog(null,
                        MessageFormat.format(
                                "¿Está seguro que desea eliminar el proyecto {0}?",
                                lista.getSelectedValue().getNombre()
                        ),
                        Constantes.TITULO,
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
            );

            if (respuesta == JOptionPane.YES_OPTION){
                Proyecto proyecto = lista.getSelectedValue();
                ProyectoControlador.eliminarProyecto(proyecto.getId());
                model.removeElement(proyecto);
                JOptionPane.showMessageDialog(null,
                        "Proyecto eliminado correctamente",
                        Constantes.TITULO,
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        }
    }
}
