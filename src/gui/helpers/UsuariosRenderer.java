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

import data.controladores.TareasControlador;
import data.controladores.UsuarioControlador;
import data.modelos.Usuario;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.text.MessageFormat;

public class UsuariosRenderer extends JLabel implements ListCellRenderer<Usuario> {
    public UsuariosRenderer(){ setOpaque(true);}

    @Override
    public Component getListCellRendererComponent(JList<? extends Usuario> list, Usuario usuario, int index, boolean isSelected, boolean cellHasFocus) {
        String mensaje = MessageFormat.format("""
                <html>
                <body>
                <p>Nombre: {0}</p>
                <p>Primer apellido: {1}</p>
                <p>Segundo apellido: {2}</p>
                <p>Tipo usuario: {3}</p>
                <p>Bloqueado: {4}</p>
                <br/>
                </body>
                </html>
                """,
                usuario.getNombre(),
                usuario.getPrimerApellido(),
                usuario.getSegundoApellido(),
                usuario.getTipoUsuario().toString(),
                usuario.getBloqueado()
        );
        setText(mensaje);
        setBorder(new CompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK), BorderFactory.createEmptyBorder(0, 15, 0, 5)));
        setToolTipText("Doble click para seleccionar usuario");
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
