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
    private final TareasRepositorio tareasRepositorio;
    // Constructor
    public TareasControlador() {
        this.tareasRepositorio = new TareasRepositorio();
    }
    // Devuelve una lista de tareas
    public ArrayList<Tarea> listaTareas(){
        return this.tareasRepositorio.getTareas();
    }
    // Devuelve una tarea por su ID
    public Tarea obtenerTarea(String id){
        return this.tareasRepositorio.getTarea(id);
    }
    // Agregar una tarea
    public void agregarTarea(Tarea tarea){
        this.tareasRepositorio.agregarTarea(tarea);
    }
    // Eliminar una tarea
    public void eliminarTarea(String id){
        this.tareasRepositorio.eliminarTarea(id);
    }
    // Actualizar una tarea
    public void actualizarTarea(Tarea tarea){
        this.tareasRepositorio.actualizarTarea(tarea);
    }
    // Obtener tareas por estado
    public ArrayList<Tarea> obtenerTareasPorEstado(Estado estado){
        return this.tareasRepositorio.getTareasEstado(estado);
    }
    // Obtener tareas por responsable
    public ArrayList<Tarea> obtenerTareasPorResponsable(String responsableId){
        return this.tareasRepositorio.getTareasUsuario(responsableId);
    }
    // Obtener tareas por proyecto
    public ArrayList<Tarea> obtenerTareasPorProyecto(String proyectoId){
        return this.tareasRepositorio.getTareasProyecto(proyectoId);
    }
    // Obtener tareas por responsable y estado
    public ArrayList<Tarea> obtenerTareasPorResponsableYEstado(String responsableId, Estado estado){
        return this.tareasRepositorio.getTareasProyectoEstado(responsableId, estado);
    }
    // Devolver las tareas de un proyecto de un usuario
    public ArrayList<Tarea> obtenerTareasPorProyectoYResponsable(String proyectoId, String responsableId){
        return this.tareasRepositorio.getTareasProyectoUsuario(proyectoId, responsableId);
    }
    // Devolver las tareas de un proyecto de un usuario por estado
    public ArrayList<Tarea> obtenerTareasPorProyectoYResponsableYEstado(String proyectoId, String responsableId, Estado estado){
        return this.tareasRepositorio.getTareasProyectoUsuarioEstado(proyectoId, responsableId, estado);
    }
}
