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
import data.modelos.Usuario;
import data.repositorios.ProyectosRepositorio;
import utilidades.EnviarCorreo;

import javax.mail.MessagingException;
import java.util.ArrayList;

public class ProyectoControlador {
    private static final ProyectosRepositorio proyectosRepositorio = new ProyectosRepositorio();
    // Constructor
    public ProyectoControlador() {

    }
    // Lista de proyectos
    public static ArrayList<Proyecto> listaProyectos(){
        return proyectosRepositorio.getProyectos();
    }
    // Obtener un proyecto por su ID
    public static Proyecto obtenerProyecto(String id){
        return proyectosRepositorio.getProyecto(id);
    }
    // Agregar un proyecto
    public static void agregarProyecto(Proyecto proyecto){
        proyectosRepositorio.agregarProyecto(proyecto);
        if(proyecto.getResponsableId() != null){
            // Se obtiene el responsable del proyecto
            Usuario responsable = UsuarioControlador.getUsuario(proyecto.getResponsableId());
            // Enviar correo al responsable
            String asunto = "Nuevo proyecto asignado";
            String cuerpo = "Hola " + responsable.getNombre() + ",\n\n"
                    + "Se ha creado un proyecto nuevo y se te ha asignado como responsable" + "\n\n"
                    + "Nombre del proyecto: " + proyecto.getNombre() + "\n"
                    + "Descripción: " + proyecto.getDescripción() + "\n\n"
                    + "Saludos,\n"
                    + "Equipo de desarrollo de la aplicación";
            // Como el proceso puede tardar, se ejecuta en un hilo aparte y asi evitar bloquear el hilo principal
            Thread thread = new Thread(() -> {
                try {
                    EnviarCorreo.enviarCorreoJava(
                            responsable.getCorreoElectrónico(),
                            "",
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
    // Eliminar un proyecto
    public static void eliminarProyecto(String id){
        // Eliminar el proyecto
        proyectosRepositorio.eliminarProyecto(id);
        // ELiminar tareas
        TareasControlador.eliminarTareasProyecto(id);
    }
    // Actualizar un proyecto
    public static void actualizarProyecto(Proyecto proyecto){
        proyectosRepositorio.actualizarProyecto(proyecto);
    }
    // Obtener proyectos por estado
    public static ArrayList<Proyecto> obtenerProyectosPorEstado(Estado estado){
        return proyectosRepositorio.getProyectosPorEstado(estado);
    }
    // Obtener proyectos por responsable
    public static ArrayList<Proyecto> obtenerProyectosPorResponsable(String responsableId){
        return proyectosRepositorio.getProyectosDeUsuario(responsableId);
    }
    // Cargar los proyectos desde el disco
    public static void cargarDatos(){
        proyectosRepositorio.cargarCambios();
    }
}
