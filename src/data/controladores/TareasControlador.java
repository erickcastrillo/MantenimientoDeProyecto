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

import data.modelos.*;
import data.repositorios.TareasRepositorio;
import utilidades.EnviarCorreo;

import javax.mail.MessagingException;
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
        if(tarea.getResponsableId() != null) {
            // Se obtiene el responsable del proyecto
            Usuario responsable = UsuarioControlador.getUsuario(tarea.getResponsableId());
            Proyecto proyecto = ProyectoControlador.obtenerProyecto(tarea.getProyectoId());
            Usuario admin = UsuarioControlador.getUsuario(proyecto.getResponsableId());
            // Enviar correo al responsable
            String asunto = "Nueva tarea asignada";
            String cuerpo = "Hola " + responsable.getNombre() + ",\n\n"
                    + "Se ha creado una tarea nueva y se te ha asignado como responsable" + "\n\n"
                    + "Nombre de la tarea: " + tarea.getNombre() + "\n"
                    + "Descripción: " + tarea.getDescripción() + "\n"
                    + "Responsable: " + responsable.nombreCompleto() + "\n\n"
                    + "Saludos,\n"
                    + "Equipo de desarrollo de la aplicación";
            // Como el proceso puede tardar, se ejecuta en un hilo aparte y asi evitar bloquear el hilo principal
            Thread thread = new Thread(() -> {
                try {
                    EnviarCorreo.enviarCorreoJava(
                            responsable.getCorreoElectrónico(),
                            admin.getCorreoElectrónico(),
                            "",
                            asunto,
                            cuerpo
                    );
                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }
            });
            thread.start();
        }
    }
    // Eliminar una tarea
    public static void eliminarTarea(String id){
        // Eliminar tarea
        tareasRepositorio.eliminarTarea(id);
        // Eliminar los hallazgos asociados
        for(Hallazgo hallazgo : HallazgosControlador.getHallazgosAsociados(id)){
            HallazgosControlador.eliminarHallazgo(hallazgo.getId());
        }
    }
    // Actualizar una tarea
    public static void actualizarTarea(Tarea tarea){
        tareasRepositorio.actualizarTarea(tarea);
    }
    // Obtener tareas por estado
    public static ArrayList<Tarea> obtenerTareasPorEstado(Estado estado){
        return tareasRepositorio.getTareasEstado(estado);
    }
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
    // Devolver todos los hallazgos de la tarea
    public static ArrayList<Hallazgo> obtenerHallazgosDeTarea(String tareaId){
        return HallazgosControlador.getHallazgosAsociados(tareaId);
    }
    // Cargar las tareas desde el disco
    public static void cargarDatos(){
        tareasRepositorio.cargarCambios();
    }

    // Eliminar todas las tareas asociadas al proyecto
    public static void eliminarTareasProyecto(String id) {
        for(Tarea tarea : obtenerTareasPorProyecto(id)){
            eliminarTarea(tarea.getId());
        }
    }
}
