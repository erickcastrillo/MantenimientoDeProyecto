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

import data.modelos.Usuario;
import data.repositorios.UsuariosRepositorio;

import java.util.ArrayList;

public class UsuarioControlador {
    private static final UsuariosRepositorio usuariosRepositorio = new UsuariosRepositorio();
    // Constructor
    public UsuarioControlador() {}
    // Devuelve una lista de usuarios
    public static ArrayList<Usuario> listaUsuarios(){
        return usuariosRepositorio.getUsuarios();
    }
    // Devuelve un usuario por su ID
    public static Usuario getUsuario(String id){
        return usuariosRepositorio.getUsuario(id);
    }
    // Agrega un usuario
    public static boolean agregarUsuario(Usuario usuario){
        return usuariosRepositorio.agregarUsuario(usuario);
    }
    // Elimina un usuario
    public static boolean eliminarUsuario(String id){
        return usuariosRepositorio.eliminarUsuario(id);
    }
    // Actualiza un usuario
    public static void actualizarUsuario(Usuario usuario){
        usuariosRepositorio.actualizarUsuario(usuario);
    }
    // Devolver todos los usuarios que no son administradores
    public static ArrayList<Usuario> getUsuariosNoAdministradores(){
        return usuariosRepositorio.getUsuariosNoAdmin();
    }
    // Devolver todos los usuarios que no son administradores ni el usuario actual
    public static ArrayList<Usuario> getUsuariosNoAdministradores(String id){
        return usuariosRepositorio.getUsuariosNoAdminNiActual(id);
    }
    // Devolver un usuario por su numero de teléfono
    public static Usuario getUsuarioPorTeléfono(String teléfono){
        return usuariosRepositorio.getUsuarioPorTeléfono(teléfono);
    }
    // Loguear un usuario
    public static Usuario loguearUsuario(String teléfono, String clave){
        return usuariosRepositorio.loguearUsuario(teléfono, clave);
    }
}
