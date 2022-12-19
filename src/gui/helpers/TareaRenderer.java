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

package gui.helpers;

import data.controladores.HallazgosControlador;
import data.controladores.UsuarioControlador;
import java.awt.*;
import java.text.MessageFormat;
import javax.swing.*;

import data.modelos.Tarea;

public class TareaRenderer extends JLabel implements ListCellRenderer<Tarea> {
    public TareaRenderer() {
        setOpaque(true);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Tarea> list, Tarea tarea, int index, boolean isSelected, boolean cellHasFocus) {
        String mensaje = MessageFormat.format("""
                <html>
                <body>
                <h3>Nombre: {0}</h3>
                <p>Descripción: {1}</p>
                <p>Responsable: {2}</p>
                <p>Hallazgos: {3}</p>
                <br/>
                </body>
                </html>
                """, 
                tarea.getNombre(), 
                tarea.getDescripción(),
                UsuarioControlador.getUsuario(tarea.getResponsableId()),
                HallazgosControlador.getHallazgosAsociados(tarea.getId()).size());
        setText(mensaje);
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        setToolTipText("Doble click para seleccionar tarea");
        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
        return this;
    }
}
