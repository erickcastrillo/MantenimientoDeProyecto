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

import data.modelos.Asociado;
import data.modelos.Hallazgo;
import data.modelos.Tarea;
import data.modelos.Usuario;
import data.repositorios.AsociadosRepositorio;

public class AsociadosControlador {
    private static final AsociadosRepositorio asociadosRepositorio = new AsociadosRepositorio();
    // Constructor
    public AsociadosControlador() { }
    // Devolver todos los asociados
    public AsociadosRepositorio getAsociadosRepositorio() {
        return asociadosRepositorio;
    }
    // Devolver un asociado por su ID
    public Asociado getAsociado(String id) {
        return asociadosRepositorio.getAsociado(id);
    }
    // Agregar un asociado
    public void agregarAsociado(Asociado asociado) {
        asociadosRepositorio.agregarAsociado(asociado);
    }
    // Eliminar un asociado
    public void eliminarAsociado(String id) {
        asociadosRepositorio.eliminarAsociado(id);
    }
    // Editar un asociado
    public void editarAsociado(Asociado asociado) {
        asociadosRepositorio.actualizarAsociado(asociado);
    }
    // Devolver el hallazgo de un asociado
    public Hallazgo devolverHallazgo(String id) {
        return HallazgosControlador.getHallazgo(asociadosRepositorio.getAsociado(id).getHallazgoId());
    }
    // Devolver el usuario de un asociado
    public Usuario devolverUsuario(String id) {
        return UsuarioControlador.getUsuario(asociadosRepositorio.getAsociado(id).getUsuarioId());
    }
    // Devolver tarea asociada
    public static Tarea getTareaAsociada(String id) {
        return TareasControlador.obtenerTarea(asociadosRepositorio.getAsociado(id).getTareaId());
    }
    // Cargar los asociados desde el disco
    public static void cargarDatos(){
        asociadosRepositorio.cargarCambios();
    }
}
