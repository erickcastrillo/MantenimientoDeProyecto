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

package main;

import data.controladores.*;
import gui.LoginWindow;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Cargar los usuarios desde el disco
        UsuarioControlador.cargarDatos();
        // Cargar los proyectos desde el disco
        ProyectoControlador.cargarDatos();
        // Cargar las tareas desde el disco
        TareasControlador.cargarDatos();
        // Cargar los hallazgos desde el disco
        HallazgosControlador.cargarDatos();
        // Cargar los asociados desde el disco
        AsociadosControlador.cargarDatos();
        // Ejecutar la ventana de Login
        SwingUtilities.invokeLater(LoginWindow::new);
    }
}
