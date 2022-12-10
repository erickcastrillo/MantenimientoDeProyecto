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
import data.modelos.Proyecto;
import data.repositorios.ProyectosRepositorio;

import java.util.ArrayList;

public class ProyectoControlador {
    ProyectosRepositorio proyectosRepositorio;
    // Constructor
    public ProyectoControlador() {
        this.proyectosRepositorio = new ProyectosRepositorio();
    }
    // Lista de proyectos
    public ArrayList<Proyecto> listaProyectos(){
        return this.proyectosRepositorio.getProyectos();
    }
    // Obtener un proyecto por su ID
    public Proyecto obtenerProyecto(String id){
        return this.proyectosRepositorio.getProyecto(id);
    }
    // Agregar un proyecto
    public void agregarProyecto(Proyecto proyecto){
        this.proyectosRepositorio.agregarProyecto(proyecto);
    }
    // Eliminar un proyecto
    public void eliminarProyecto(String id){
        this.proyectosRepositorio.eliminarProyecto(id);
    }
    // Actualizar un proyecto
    public void actualizarProyecto(Proyecto proyecto){
        this.proyectosRepositorio.actualizarProyecto(proyecto);
    }
    // Obtener proyectos por estado
    public ArrayList<Proyecto> obtenerProyectosPorEstado(Estado estado){
        return this.proyectosRepositorio.getProyectosPorEstado(estado);
    }
    // Obtener proyectos por responsable
    public ArrayList<Proyecto> obtenerProyectosPorResponsable(String responsableId){
        return this.proyectosRepositorio.getProyectosDeUsuario(responsableId);
    }
}
