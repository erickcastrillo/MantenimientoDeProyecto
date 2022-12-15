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

import data.modelos.Estado;
import data.modelos.Tarea;
import data.repositorios.TareasRepositorio;

import java.util.ArrayList;

public class TareasControlador {
    private static final TareasRepositorio tareasRepositorio = new TareasRepositorio();
    // Constructor
    public TareasControlador() { }
    // Devuelve una lista de tareas
    public static ArrayList<Tarea> listaTareas(){
        return tareasRepositorio.getTareas();
    }
    // Devuelve una tarea por su ID
    public static Tarea obtenerTarea(String id){
        return tareasRepositorio.getTarea(id);
    }
    // Agregar una tarea
    public static void agregarTarea(Tarea tarea){
        tareasRepositorio.agregarTarea(tarea);
    }
    // Eliminar una tarea
    public static void eliminarTarea(String id){
        tareasRepositorio.eliminarTarea(id);
    }
    // Actualizar una tarea
    public static void actualizarTarea(Tarea tarea){
        tareasRepositorio.actualizarTarea(tarea);
    }
    // Obtener tareas por estado
    public static ArrayList<Tarea> obtenerTareasPorEstado(Estado estado){
        return tareasRepositorio.getTareasEstado(estado);
    }
    public static ArrayList<Tarea> listaHallazgoTarea (Tarea tarea){
        return tareasRepositorio.listaHallazgoTarea(tarea);}
    // Obtener tareas por responsable
    public static ArrayList<Tarea> obtenerTareasPorResponsable(String responsableId){
        return tareasRepositorio.getTareasUsuario(responsableId);
    }
    // Obtener tareas por proyecto
    public static ArrayList<Tarea> obtenerTareasPorProyecto(String proyectoId){
        return tareasRepositorio.getTareasProyecto(proyectoId);
    }
    // Obtener tareas por responsable y estado
    public static ArrayList<Tarea> obtenerTareasPorResponsableYEstado(String responsableId, Estado estado){
        return tareasRepositorio.getTareasProyectoEstado(responsableId, estado);
    }
    // Devolver las tareas de un proyecto de un usuario
    public static ArrayList<Tarea> obtenerTareasPorProyectoYResponsable(String proyectoId, String responsableId){
        return tareasRepositorio.getTareasProyectoUsuario(proyectoId, responsableId);
    }
    // Devolver las tareas de un proyecto de un usuario por estado
    public static ArrayList<Tarea> obtenerTareasPorProyectoYResponsableYEstado(String proyectoId, String responsableId, Estado estado){
        return tareasRepositorio.getTareasProyectoUsuarioEstado(proyectoId, responsableId, estado);
    }
    // Cargar las tareas desde el disco
    public static void cargarDatos(){
        tareasRepositorio.cargarCambios();
    }
}
