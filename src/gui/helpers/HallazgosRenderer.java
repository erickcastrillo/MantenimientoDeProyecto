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
import data.modelos.Hallazgo;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.text.MessageFormat;

public class HallazgosRenderer extends JLabel implements ListCellRenderer<Hallazgo> {
    public HallazgosRenderer() {
        setOpaque(true);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Hallazgo> list, Hallazgo hallazgo, int index, boolean isSelected, boolean cellHasFocus) {
        String mensaje = MessageFormat.format("""
                <html>
                <body>
                <p>Comentario: {0}</p>
                <p>Responsable: {1}</p>
                <br/>
                </body>
                </html>
                """,
                hallazgo.getComentario(),
                UsuarioControlador.getUsuario(hallazgo.getResponsableId()));
        setText(mensaje);
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        setBorder(new CompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK), BorderFactory.createEmptyBorder(0, 15, 0, 5)));
        setToolTipText("Doble click para seleccionar hallazgo");
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
