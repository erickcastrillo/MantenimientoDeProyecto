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

import data.controladores.HallazgosControlador;
import data.controladores.ProyectoControlador;
import data.controladores.TareasControlador;
import data.modelos.*;
import gui.Constantes;
import gui.Custumizable;
import gui.PrincipalWindow;
import gui.helpers.TareaRenderer;
import gui.popups.JOptionsPaneHallazgo;
import gui.popups.JOptionsPaneProyecto;
import gui.popups.JOptionsPaneTarea;
import java.awt.BorderLayout;
import java.text.MessageFormat;
import java.util.HashMap;

import javax.swing.*;

public class ListaTareasPanel extends JPanel implements Custumizable {
    private DefaultListModel<Tarea> model;
    private JList<Tarea> listaTareas;
    
    private Proyecto proyecto;
    private final Usuario usuario;
    private final PrincipalWindow ventana;
    
    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }
    
    public ListaTareasPanel(PrincipalWindow ventana, Usuario usuario) {
        this.ventana = ventana;
        this.usuario = usuario;
        inicializar();
    }

    @Override
    public void inicializar() {
        setPreferredSize(Constantes.DIMENSION_LISTA_TAREAS);
        setMaximumSize(Constantes.DIMENSION_LISTA_TAREAS);
        setMinimumSize(Constantes.DIMENSION_LISTA_TAREAS);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Agregamos el titulo del panel
        JLabel titulo = new JLabel("Tareas");
        titulo.setFont(Constantes.FUENTE_SUBTITULO);
        add(titulo, BorderLayout.NORTH);

        // Panel de tareas
        JPanel panel = new JPanel();
        panel.setPreferredSize(Constantes.DIMENSION_LISTA_TAREAS_LISTA);
        panel.setMaximumSize(Constantes.DIMENSION_LISTA_TAREAS_LISTA);
        panel.setMinimumSize(Constantes.DIMENSION_LISTA_TAREAS_LISTA);

        // Agregamos la lista de tareas
        model = new DefaultListModel<>();
        listaTareas = new JList<>(model);
        listaTareas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaTareas.setLayoutOrientation(JList.VERTICAL);
        listaTareas.setVisibleRowCount(-1);
        listaTareas.setCellRenderer(new TareaRenderer());
        listaTareas.setBackground(Constantes.COLOR_FONDO);
        
        // Agregar un evento para cuando se seleccione una tarea
        listaTareas.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    Tarea tarea = listaTareas.getSelectedValue();
                    ventana.actualizarListaHallazgosTarea(tarea);
                }
            }
        });

        // Agregar scroll
        JScrollPane scrollPane = new JScrollPane(listaTareas);
        scrollPane.setPreferredSize(Constantes.DIMENSION_LISTA_TAREAS_LISTA);
        scrollPane.setMaximumSize(Constantes.DIMENSION_LISTA_TAREAS_LISTA);
        scrollPane.setMinimumSize(Constantes.DIMENSION_LISTA_TAREAS_LISTA);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        panel.add(scrollPane);
        add(panel, BorderLayout.CENTER);
        
        // Agregamos el botón de agregar tarea
        JButton agregarTareaButton = new JButton("Agregar tarea");
        agregarTareaButton.setFont(Constantes.FUENTE_NORMAL);
        agregarTareaButton.setPreferredSize(Constantes.DIMENSION_BOTÓN);
        agregarTareaButton.setMaximumSize(Constantes.DIMENSION_BOTÓN);
        agregarTareaButton.setMinimumSize(Constantes.DIMENSION_BOTÓN);
        agregarTareaButton.addActionListener(e -> {
            agregarTarea();
        });
        // add(agregarTareaButton);

        // Agregamos el botón de editar tarea
        JButton editarTareaButton = new JButton("Editar tarea");
        editarTareaButton.setFont(Constantes.FUENTE_NORMAL);
        editarTareaButton.setPreferredSize(Constantes.DIMENSION_BOTÓN);
        editarTareaButton.setMaximumSize(Constantes.DIMENSION_BOTÓN);
        editarTareaButton.setMinimumSize(Constantes.DIMENSION_BOTÓN);
        editarTareaButton.addActionListener(e -> {
            editarTarea();
        });
        // add(editarTareaButton);

        // Agregamos el botón de eliminar tarea
        JButton eliminarTareaButton = new JButton("Eliminar tarea");
        eliminarTareaButton.setFont(Constantes.FUENTE_NORMAL);
        eliminarTareaButton.setPreferredSize(Constantes.DIMENSION_BOTÓN);
        eliminarTareaButton.setMaximumSize(Constantes.DIMENSION_BOTÓN);
        eliminarTareaButton.setMinimumSize(Constantes.DIMENSION_BOTÓN);
        eliminarTareaButton.addActionListener(e -> {
            eliminarTarea();
        });

        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(Constantes.COLOR_FONDO);
        panel.setPreferredSize(Constantes.DIMENSION_LISTA_TAREAS_LISTA);
        panel.setMaximumSize(Constantes.DIMENSION_LISTA_TAREAS_LISTA);
        panel.setMinimumSize(Constantes.DIMENSION_LISTA_TAREAS_LISTA);
        panelBotones.add(agregarTareaButton);
        panelBotones.add(editarTareaButton);
        panelBotones.add(eliminarTareaButton);

        // Agregar panel botones
        add(panelBotones, BorderLayout.SOUTH);

    }

    @Override
    public void actualizar() {
        model.clear();
        if (proyecto != null) {
            for (Tarea tarea : TareasControlador.obtenerTareasPorProyecto(proyecto.getId())) {
                model.addElement(tarea);
            }
        }
    }
    
    public void agregarTarea(){
        if (proyecto == null){
            JOptionPane.showMessageDialog(null,
                        "Para agregar una tarea, por favor seleccione un proyecto de la lista",
                        Constantes.TITULO,
                        JOptionPane.ERROR_MESSAGE
                );
            return;
        }
        
        HashMap<String, String> tareaData = JOptionsPaneTarea.showTareaNuevoPrompt(
                null,
                "Tarea nueva",
                "Ingrese los datos de la tarea",
                usuario,
                null
        );
        
        if(tareaData != null){
            Tarea tarea = new Tarea();
            tarea.setNombre(tareaData.get("nombre"));
            tarea.setDescripción(tareaData.get("descripción"));
            switch (tareaData.get("estado")) {
                case "Nuevo" -> tarea.setEstado(Estado.NUEVO);
                case "Pendiente" -> tarea.setEstado(Estado.PENDIENTE);
                case "En desarrollo" -> tarea.setEstado(Estado.EN_DESARROLLO);
                case "Suspendido" -> tarea.setEstado(Estado.SUSPENDIDO);
                case "Cancelado" -> tarea.setEstado(Estado.CANCELADO);
                case "Terminado" -> tarea.setEstado(Estado.TERMINADO);
            }
            if(tareaData.get("responsableId") != null) {
                tarea.setResponsableId(tareaData.get("responsableId"));
            }
            tarea.setProyectoId(proyecto.getId());
            TareasControlador.agregarTarea(tarea);
            model.addElement(tarea);
        }
        ventana.revalidate();
        ventana.repaint();
    }
    
    public void editarTarea(){
        if (listaTareas.getSelectedValue() != null) {
            Tarea tarea = listaTareas.getSelectedValue();
            HashMap<String, String> tareaData = JOptionsPaneProyecto.showProyectoNuevoPrompt(
                    null,
                    "Tarea " + tarea.getNombre(),
                    "Ingrese los datos de la tarea",
                    usuario,
                    proyecto
            );
            if (tareaData != null) {
                tarea.setNombre(tareaData.get("nombre"));
                tarea.setDescripción(tareaData.get("descripción"));
                switch (tareaData.get("estado")) {
                    case "Nuevo" -> tarea.setEstado(Estado.NUEVO);
                    case "Pendiente" -> tarea.setEstado(Estado.PENDIENTE);
                    case "En desarrollo" -> tarea.setEstado(Estado.EN_DESARROLLO);
                    case "Suspendido" -> tarea.setEstado(Estado.SUSPENDIDO);
                    case "Cancelado" -> tarea.setEstado(Estado.CANCELADO);
                    case "Terminado" -> tarea.setEstado(Estado.TERMINADO);
                }
                if (tareaData.get("responsableId") != null) {
                    tarea.setResponsableId(tareaData.get("responsableId"));
                }
                TareasControlador.actualizarTarea(tarea);
                model.setElementAt(tarea, listaTareas.getSelectedIndex());
            }
        }
    }
    
    public void eliminarTarea(){
        if (listaTareas.getSelectedValue() != null) {
            int respuesta = JOptionPane.showConfirmDialog(null,
                        MessageFormat.format(
                                "¿Está seguro que desea eliminar la tarea {0}?",
                                listaTareas.getSelectedValue().getNombre()
                        ),
                        Constantes.TITULO,
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
            );

            if (respuesta == JOptionPane.YES_OPTION){
                Tarea tarea = listaTareas.getSelectedValue();
                TareasControlador.eliminarTarea(tarea.getId());
                model.removeElement(tarea);
                JOptionPane.showMessageDialog(null,
                        "Tarea eliminada correctamente",
                        Constantes.TITULO,
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        }
    }
}
