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
import data.modelos.Hallazgo;
import data.modelos.Tarea;
import data.modelos.Usuario;
import gui.Constantes;
import gui.Custumizable;
import gui.PrincipalWindow;
import gui.helpers.HallazgosRenderer;
import gui.popups.JOptionsPaneHallazgo;

import javax.swing.*;
import java.awt.*;
import java.text.MessageFormat;
import java.util.HashMap;

public class ListaHallazgosPanel extends JPanel implements Custumizable {
    private JList<Hallazgo> listaHallazgos;
    DefaultListModel<Hallazgo> model;
    private final Usuario usuario;
    private final PrincipalWindow ventana;

    public void setTarea(Tarea tarea) {
        this.tarea = tarea;
    }

    private Tarea tarea;

    public ListaHallazgosPanel(PrincipalWindow ventana, Usuario usuario) {
        this.usuario = usuario;
        this.ventana = ventana;
        inicializar();
    }
    @Override
    public void inicializar() {
        setPreferredSize(Constantes.DIMENSION_LISTA_HALLAZGOS);
        setMaximumSize(Constantes.DIMENSION_LISTA_HALLAZGOS);
        setMinimumSize(Constantes.DIMENSION_LISTA_HALLAZGOS);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Agregamos el titulo del panel
        JLabel titulo = new JLabel("Hallazgos");
        titulo.setFont(Constantes.FUENTE_SUBTITULO);
        add(titulo, BorderLayout.NORTH);

        // Panel de tareas
        JPanel panel = new JPanel();
        panel.setPreferredSize(Constantes.DIMENSION_LISTA_HALLAZGOS_LISTA);
        panel.setMaximumSize(Constantes.DIMENSION_LISTA_TAREAS_LISTA);
        panel.setMinimumSize(Constantes.DIMENSION_LISTA_TAREAS_LISTA);

        // Agregamos la lista de tareas
        model = new DefaultListModel<>();
        listaHallazgos = new JList<>(model);
        listaHallazgos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaHallazgos.setLayoutOrientation(JList.VERTICAL);
        listaHallazgos.setVisibleRowCount(-1);
        listaHallazgos.setCellRenderer(new HallazgosRenderer());
        listaHallazgos.setBackground(Constantes.COLOR_FONDO);

        // Agregar scroll
        JScrollPane scrollPane = new JScrollPane(listaHallazgos);
        scrollPane.setPreferredSize(Constantes.DIMENSION_LISTA_TAREAS_LISTA);
        scrollPane.setMaximumSize(Constantes.DIMENSION_LISTA_TAREAS_LISTA);
        scrollPane.setMinimumSize(Constantes.DIMENSION_LISTA_TAREAS_LISTA);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        panel.add(scrollPane);
        add(panel, BorderLayout.CENTER);

        // Agregamos el botón de agregar tarea
        JButton agregarHallazgoButton = new JButton("Agregar hallazgo");
        agregarHallazgoButton.setFont(Constantes.FUENTE_NORMAL);
        agregarHallazgoButton.setPreferredSize(Constantes.DIMENSION_BOTÓN);
        agregarHallazgoButton.setMaximumSize(Constantes.DIMENSION_BOTÓN);
        agregarHallazgoButton.setMinimumSize(Constantes.DIMENSION_BOTÓN);
        agregarHallazgoButton.addActionListener(e -> agregarHallazgo());
        // add(agregarHallazgoButton);

        // Agregamos el botón de editar tarea
        JButton editarHallazgoButton = new JButton("Editar hallazgo");
        editarHallazgoButton.setFont(Constantes.FUENTE_NORMAL);
        editarHallazgoButton.setPreferredSize(Constantes.DIMENSION_BOTÓN);
        editarHallazgoButton.setMaximumSize(Constantes.DIMENSION_BOTÓN);
        editarHallazgoButton.setMinimumSize(Constantes.DIMENSION_BOTÓN);
        editarHallazgoButton.addActionListener(e -> editarHallazgo());
        // add(editarHallazgoButton);

        // Agregamos el botón de eliminar tarea
        JButton eliminarHallazgoButton = new JButton("Eliminar hallazgo");
        eliminarHallazgoButton.setFont(Constantes.FUENTE_NORMAL);
        eliminarHallazgoButton.setPreferredSize(Constantes.DIMENSION_BOTÓN);
        eliminarHallazgoButton.setMaximumSize(Constantes.DIMENSION_BOTÓN);
        eliminarHallazgoButton.setMinimumSize(Constantes.DIMENSION_BOTÓN);
        eliminarHallazgoButton.addActionListener(e -> eliminarHallazgo());

        // Agregamos el botón de agregar hallazgo a tarea
        JButton agregarHallazgoTareaButton = new JButton("Agregar hallazgo");
        agregarHallazgoTareaButton.setFont(Constantes.FUENTE_NORMAL);
        agregarHallazgoTareaButton.setPreferredSize(Constantes.DIMENSION_BOTÓN);
        agregarHallazgoTareaButton.setMaximumSize(Constantes.DIMENSION_BOTÓN);
        agregarHallazgoTareaButton.setMinimumSize(Constantes.DIMENSION_BOTÓN);
        agregarHallazgoTareaButton.addActionListener(e -> agregarHallazgo());
        // add(eliminarHallazgoButton);

        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(Constantes.COLOR_FONDO);
        panel.setPreferredSize(Constantes.DIMENSION_LISTA_TAREAS_LISTA);
        panel.setMaximumSize(Constantes.DIMENSION_LISTA_TAREAS_LISTA);
        panel.setMinimumSize(Constantes.DIMENSION_LISTA_TAREAS_LISTA);
        panelBotones.add(agregarHallazgoButton);
        panelBotones.add(editarHallazgoButton);
        panelBotones.add(eliminarHallazgoButton);

        // Agregar panel botones
        add(panelBotones, BorderLayout.SOUTH);
    }

    private void eliminarHallazgo() {
        Hallazgo hallazgo = listaHallazgos.getSelectedValue();
        if (hallazgo != null) {
            int respuesta = JOptionPane.showConfirmDialog(null,
                    MessageFormat.format(
                            "¿Está seguro que desea eliminar el hallazgo {0}?",
                            hallazgo.getComentario()
                    ),
                    Constantes.TITULO,
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
            );

            if (respuesta == JOptionPane.YES_OPTION){
                HallazgosControlador.eliminarHallazgo(hallazgo.getId());
                model.removeElement(hallazgo);
                JOptionPane.showMessageDialog(null,
                        "Hallazgo eliminado correctamente",
                        Constantes.TITULO,
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
            ventana.revalidate();
            ventana.repaint();
        }
    }

    private void editarHallazgo() {
        Hallazgo hallazgo = listaHallazgos.getSelectedValue();
        if (hallazgo != null) {
            HashMap<String, String> hallazgoData = JOptionsPaneHallazgo.showHallazgoNuevoPrompt(
                    null,
                    "Editar hallazgo",
                    "Ingrese los datos del hallazgo",
                    usuario,
                    hallazgo
            );
            if(hallazgoData != null){
                hallazgo.setComentario(hallazgoData.get("comentario"));
                hallazgo.setResponsableId(hallazgoData.get("responsableId"));
                HallazgosControlador.actualizarHallazgo(hallazgo);
                model.setElementAt(hallazgo, listaHallazgos.getSelectedIndex());
            }
        }
    }

    private void agregarHallazgo() {
        if (tarea == null) {
            JOptionPane.showConfirmDialog(null,
                    "Debes elegir una tarea para agregar un hallazgo",
                    Constantes.TITULO,
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }
        HashMap<String, String> hallazgoData = JOptionsPaneHallazgo.showHallazgoNuevoPrompt(
                null,
                "Tarea " + tarea.getNombre(),
                "Ingrese los datos del hallazgo",
                usuario,
                null
        );
        if(hallazgoData != null){
            Hallazgo hallazgo = new Hallazgo();
            hallazgo.setComentario(hallazgoData.get("comentario"));
            hallazgo.setTareaId(tarea.getId());
            hallazgo.setResponsableId(hallazgoData.get("responsableId"));

            HallazgosControlador.agregarHallazgo(hallazgo);
            model.addElement(hallazgo);
            ventana.revalidate();
            ventana.repaint();
        }
    }

    @Override
    public void actualizar() {
        model.clear();
        if (tarea != null) {
            for (Hallazgo hallazgo : HallazgosControlador.getHallazgosAsociados(tarea.getId())) {
                model.addElement(hallazgo);
            }
        }
    }
}
