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

import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Constantes {
    // Mensajes en pantalla
    public static final String TITULO = "Manejo de proyectos";
    public static final String TITULO_VENTANA_PRINCIPAL = "Bienvenido ";
    public static final Font FUENTE_TITULO = new Font("Arial", Font.BOLD, 24);
    public static final Font FUENTE_SUBTITULO = new Font("Arial", Font.BOLD, 16);
    public static final Font FUENTE_NORMAL = new Font("Arial", Font.PLAIN, 12);

    // Dimensiones de la ventana
    public static final int ANCHO_VENTANA = 900;
    public static final int ALTO_VENTANA = 600;
    public static final Dimension DIMENSION_LISTA_PROYECTOS = new Dimension(200, 400);
    public static final Dimension DIMENSION_LISTA_PROYECTOS_LISTA = new Dimension(200, 330);
    public static final Dimension DIMENSION_LISTA_TAREAS = new Dimension(300, 400);
    public static final Dimension DIMENSION_LISTA_TAREAS_LISTA = new Dimension(300, 330);
    public static final Dimension DIMENSION_LISTA_HALLAZGOS = new Dimension(300, 400);
    public static final Dimension DIMENSION_LISTA_HALLAZGOS_LISTA = new Dimension(300, 330);
    public static final Dimension DIMENSION_BOTÓN = new Dimension(150, 30);

    // Colores
    public static final Color COLOR_FONDO = new Color(255, 255, 255);
    public static final Color COLOR_FONDO_BOTÓN = new Color(0, 0, 0);
    public static final Color COLOR_FONDO_BOTÓN_HOVER = new Color(0, 0, 0);
    public static final Color COLOR_FONDO_BOTÓN_PRESSED = new Color(0, 0, 0);
    public static final Color COLOR_TEXTO_BOTÓN = new Color(255, 255, 255);
    public static final Color COLOR_TEXTO_BOTÓN_HOVER = new Color(255, 255, 255);
    public static final Color COLOR_TEXTO_BOTÓN_PRESSED = new Color(255, 255, 255);
    public static final Color COLOR_FONDO_TITULO = new Color(0, 0, 0);
    public static final Color COLOR_TEXTO_TITULO = new Color(255, 255, 255);

    // Border
    public static final Border BORDER = new EmptyBorder(10, 10, 10, 10);

}
