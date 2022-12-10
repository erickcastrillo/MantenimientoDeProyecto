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

package data.repositorios;

import data.modelos.Estado;
import data.modelos.Proyecto;

import java.util.ArrayList;

public class ProyectosRepositorio {
    // Array de proyectos
    private ArrayList<Proyecto> proyectos;

    // Devolver todos los proyectos
    public ArrayList<Proyecto> getProyectos() {
        return proyectos;
    }

    // Devolver un proyecto por su ID
    public Proyecto getProyecto(String id) {
        for (Proyecto proyecto : proyectos) {
            if (proyecto.getId().equals(id)) {
                return proyecto;
            }
        }
        return null;
    }

    // Agregar un proyecto
    public void agregarProyecto(Proyecto proyecto) {
        proyectos.add(proyecto);
    }

    // Eliminar un proyecto
    public void eliminarProyecto(String id) {
        proyectos.remove(getProyecto(id));
    }

    // Actualizar un proyecto
    public void actualizarProyecto(Proyecto proyecto) {
        Proyecto proyectoActual = getProyecto(proyecto.getId());
        proyectoActual.setNombre(proyecto.getNombre());
        proyectoActual.setDescripción(proyecto.getDescripción());
        proyectoActual.setResponsableId(proyecto.getResponsableId());
        proyectoActual.setEstado(proyecto.getEstado());
    }

    // Devolver los proyectos de un usuario
    public ArrayList<Proyecto> getProyectosDeUsuario(String usuarioId) {
        ArrayList<Proyecto> proyectosDeUsuario = new ArrayList<>();
        for (Proyecto proyecto : proyectos) {
            if (proyecto.getResponsableId().equals(usuarioId)) {
                proyectosDeUsuario.add(proyecto);
            }
        }
        return proyectosDeUsuario;
    }

    // Devolver los proyectos de un usuario en un estado
    public ArrayList<Proyecto> getProyectosDeUsuarioEnEstado(String usuarioId, Estado estado) {
        ArrayList<Proyecto> proyectosDeUsuarioEnEstado = new ArrayList<>();
        for (Proyecto proyecto : proyectos) {
            if (proyecto.getResponsableId().equals(usuarioId) && proyecto.getEstado().equals(estado)) {
                proyectosDeUsuarioEnEstado.add(proyecto);
            }
        }
        return proyectosDeUsuarioEnEstado;
    }

    // Devolver los proyectos en un estado
    public ArrayList<Proyecto> getProyectosEnEstado(Estado estado) {
        ArrayList<Proyecto> proyectosEnEstado = new ArrayList<>();
        for (Proyecto proyecto : proyectos) {
            if (proyecto.getEstado().equals(estado)) {
                proyectosEnEstado.add(proyecto);
            }
        }
        return proyectosEnEstado;
    }

    // Guardar cambios en disco duro usando serializable
    public void guardarCambios() {
        Serializador.serializar(proyectos, "proyectos.dat");
    }

    // Cargar cambios desde disco duro usando serializable
    public void cargarCambios() {
        Object datos = Serializador.deserializar("proyectos.dat");
        if(datos != null){
            this.proyectos = (ArrayList<Proyecto>) datos;
        } else {
            this.proyectos = new ArrayList<>();
        }
    }

    // Constructor
    public ProyectosRepositorio() {
        cargarCambios();
    }

}
