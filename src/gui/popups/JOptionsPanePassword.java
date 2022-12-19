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

package gui.popups;

import javax.swing.*;
import java.awt.*;

public class JOptionsPanePassword {
    public static String showClavePrompt(Component parent, String message, String title )
    {
        // Se crea un JPasswordField
        JPasswordField passwordField = new JPasswordField(20);
        // Se crea un JLabel con el mensaje
        JLabel label = new JLabel(message);
        // Se crea un object Box para agregar los componentes
        Box box = Box.createVerticalBox();
        // Se agregan los componentes al Box
        box.add(label);
        // Este es un componente invisible que sirve para dar espacio entre los componentes
        box.add(Box.createVerticalStrut(5));
        box.add(passwordField);

        int returnVal = JOptionPane.showConfirmDialog( parent, box, title, JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE );

        if ( returnVal == JOptionPane.OK_OPTION )
        {
            // hay una razón por la que getPassword() devuelve un char[], pero lo ignoramos por ahora...
            // ver: http://stackoverflow.com/questions/8881291/why-is-char-preferred-over-string-for-passwords
            return new String( passwordField.getPassword( ) );
        }
        else
        {
            return null;
        }
    }

}
