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

import static data.controladores.TareasControlador.listaTareas;
import java.util.ArrayList;

import data.modelos.Estado;
import data.modelos.Tarea;

public class TareasRepositorio {
    // Array de tareas
    private ArrayList<Tarea> tareas = new ArrayList<>();

    // Devolver todas las tareas
    public ArrayList<Tarea> getTareas() {
        return tareas;
    }
    public ArrayList<Tarea> getHallazgos() {
        return tareas;
    }
    public void agregarHallazgos(Tarea tarea) {
        tareas.add(tarea);
    }
    
    public void eliminarHallazgos(String id) {
        tareas.remove(getHallazgos());
    }
    

    public ArrayList<Tarea> listaHallazgoTarea(Tarea tarea) {
        ArrayList<Tarea> listaHallazgoTarea = new ArrayList<>();
        for (Tarea tarea : listaHallazgo) {
            if (tarea.getHallazgo().equals(tarea)) {
                listaHallazgoTarea.add(tarea);
            }
        }
        return listaHallazgoTarea;
    }
    // Devolver una tarea por su id
    public Tarea getTarea(String id) {
        for (Tarea tarea : tareas) {
            if (tarea.getId().equals(id)) {
                return tarea;
            }
        }
        return null;
    }

    // Agregar una tarea
    public void agregarTarea(Tarea tarea) {
        tareas.add(tarea);
    }
    
    // Eliminar una tarea
    public void eliminarTarea(String id) {
        tareas.remove(getTarea(id));
    }

    // Actualizar una tarea
    public void actualizarTarea(Tarea tarea) {
        Tarea tareaActual = getTarea(tarea.getId());
        tareaActual.setNombre(tarea.getNombre());
        tareaActual.setDescripción(tarea.getDescripción());
        tareaActual.setResponsableId(tarea.getResponsableId());
        tareaActual.setProyectoId(tarea.getProyectoId());
        tareaActual.setEstado(tarea.getEstado());
    }

    // Devolver las tareas de un usuario
    public ArrayList<Tarea> getTareasUsuario(String usuarioId) {
        ArrayList<Tarea> tareasUsuario = new ArrayList<>();
        for (Tarea tarea : tareas) {
            if (tarea.getResponsableId().equals(usuarioId)) {
                tareasUsuario.add(tarea);
            }
        }
        return tareasUsuario;
    }

    // Devolver las tareas de un proyecto
    public ArrayList<Tarea> getTareasProyecto(String proyectoId) {
        ArrayList<Tarea> tareasProyecto = new ArrayList<>();
        for (Tarea tarea : tareas) {
            if (tarea.getProyectoId().equals(proyectoId)) {
                tareasProyecto.add(tarea);
            }
        }
        return tareasProyecto;
    }

    // Devolver las tareas de un proyecto de un usuario
    public ArrayList<Tarea> getTareasProyectoUsuario(String proyectoId, String usuarioId) {
        ArrayList<Tarea> tareasProyectoUsuario = new ArrayList<>();
        for (Tarea tarea : tareas) {
            if (tarea.getProyectoId().equals(proyectoId) && tarea.getResponsableId().equals(usuarioId)) {
                tareasProyectoUsuario.add(tarea);
            }
        }
        return tareasProyectoUsuario;
    }

    // Devolver las tareas de un proyecto de un usuario por estado
    public ArrayList<Tarea> getTareasProyectoUsuarioEstado(String proyectoId, String usuarioId, Estado estado) {
        ArrayList<Tarea> tareasProyectoUsuarioEstado = new ArrayList<>();
        for (Tarea tarea : tareas) {
            if (tarea.getProyectoId().equals(proyectoId) && tarea.getResponsableId().equals(usuarioId) && tarea.getEstado().equals(estado)) {
                tareasProyectoUsuarioEstado.add(tarea);
            }
        }
        return tareasProyectoUsuarioEstado;
    }

    // Devolver las tareas de un proyecto por estado
    public ArrayList<Tarea> getTareasProyectoEstado(String proyectoId, Estado estado) {
        ArrayList<Tarea> tareasProyectoEstado = new ArrayList<>();
        for (Tarea tarea : tareas) {
            if (tarea.getProyectoId().equals(proyectoId) && tarea.getEstado().equals(estado)) {
                tareasProyectoEstado.add(tarea);
            }
        }
        return tareasProyectoEstado;
    }

    // Devolver las tareas por estado
    public ArrayList<Tarea> getTareasEstado(Estado estado) {
        ArrayList<Tarea> tareasEstado = new ArrayList<>();
        for (Tarea tarea : tareas) {
            if (tarea.getEstado().equals(estado)) {
                tareasEstado.add(tarea);
            }
        }
        return tareasEstado;
    }

    // Guardar cambios en disco duro usando serializable
    public void guardarCambios() {
        Serializador.serializar(tareas, "tareas.dat");
    }

    // Cargar cambios desde disco duro usando serializable
    @SuppressWarnings("unchecked")
    public void cargarCambios() {
        System.out.println("Cargando tareas...");
        Object datos = Serializador.deserializar("tareas.dat");
        if(datos != null){
            this.tareas = (ArrayList<Tarea>) datos;
        } else {
            this.tareas = new ArrayList<>();
        }
    }

    // Constructor
    public TareasRepositorio() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            guardarCambios();
            System.out.println("Guardando cambios en las tareas...");
        }, "Shutdown-thread"));
    }
    
}
