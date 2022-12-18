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

import data.controladores.UsuarioControlador;
import java.awt.*;
import java.text.MessageFormat;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import data.modelos.Proyecto;

import javax.swing.*;
import javax.swing.border.CompoundBorder;

public class ProyectoRenderer extends JLabel implements ListCellRenderer<Proyecto> {
    public ProyectoRenderer() {
        setOpaque(true);
    }
    @Override
    public Component getListCellRendererComponent(JList<? extends Proyecto> list, Proyecto proyecto, int index, boolean isSelected, boolean cellHasFocus) {
        String mensaje = MessageFormat.format("""
                <html>
                <body>
                <h3>{0}</h3>
                <p>{1}</p>
                <p>{2}</p>
                <br/>
                </body>
                </html>
                """, 
                proyecto.getNombre(), 
                proyecto.getDescripción(), 
                UsuarioControlador.getUsuario(proyecto.getResponsableId()));
        setText(mensaje);
        /*ImageIcon imageIcon = new ImageIcon(
                Objects.requireNonNull(getClass().getResource("/img/workflow.png"))
        );
        Image image = imageIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(32, 32,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newimg);  // transform it back

        setIcon(imageIcon);*/
        setBorder(new CompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK), BorderFactory.createEmptyBorder(0, 15, 0, 5)));
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
