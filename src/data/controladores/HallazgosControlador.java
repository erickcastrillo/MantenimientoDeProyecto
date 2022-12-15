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

package data.controladores;

import data.modelos.Hallazgo;
import data.modelos.Tarea;
import data.modelos.Usuario;
import data.repositorios.HallazgosRepositorio;
import data.controladores.UsuarioControlador;

import java.util.ArrayList;

public class HallazgosControlador {
    private static final HallazgosRepositorio hallazgosRepositorio = new HallazgosRepositorio();

    public HallazgosControlador() { }

    // Devolver todos los hallazgos
    public static Hallazgo[] getHallazgos() {
        return hallazgosRepositorio.getHallazgos().toArray(new Hallazgo[0]);
    }
    // Devolver un hallazgo por su ID
    public static Hallazgo getHallazgo(String id) {
        return hallazgosRepositorio.getHallazgo(id);
    }
    // Agregar un hallazgo
    public static void agregarHallazgo(Hallazgo hallazgo) {
        hallazgosRepositorio.agregarHallazgo(hallazgo);
    }
    // Eliminar un hallazgo
    public static void eliminarHallazgo(String id) {
        hallazgosRepositorio.eliminarHallazgo(id);
    }
    // Actualizar un hallazgo
    public static void actualizarHallazgo(Hallazgo hallazgo) {
        hallazgosRepositorio.actualizarHallazgo(hallazgo);
    }

    // Devolver la tarea asociada
    public static Tarea getTareaAsociada(String id) {
        return TareasControlador.obtenerTarea(hallazgosRepositorio.getHallazgo(id).getTareaId());
    }
    // Devolver el usuario asociado
    public static Usuario getUsuarioAsociado(String id) {
        return UsuarioControlador.getUsuario(hallazgosRepositorio.getHallazgo(id).getResponsableId());
    }

    // Devolver los hallazgos asociados a una tarea
    public static ArrayList<Hallazgo> getHallazgosAsociados(String id) {
        ArrayList<Hallazgo> hallazgosAsociados = new ArrayList<>();
        for (Hallazgo hallazgo : hallazgosRepositorio.getHallazgos()) {
            if (hallazgo.getTareaId().equals(id)) {
                hallazgosAsociados.add(hallazgo);
            }
        }
        return hallazgosAsociados;
    }

    // Cargar los hallazgos desde el disco
    public static void cargarDatos(){
        hallazgosRepositorio.cargarCambios();
    }
}
