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
    private final UsuariosRepositorio usuariosRepositorio;
    // Constructor
    public UsuarioControlador() {
        usuariosRepositorio = new UsuariosRepositorio();
    }
    // Devuelve una lista de usuarios
    public ArrayList<Usuario> listaUsuarios(){
        return this.usuariosRepositorio.getUsuarios();
    }
    // Devuelve un usuario por su ID
    public Usuario getUsuario(String id){
        return this.usuariosRepositorio.getUsuario(id);
    }
    // Agrega un usuario
    public void agregarUsuario(Usuario usuario){
        this.usuariosRepositorio.agregarUsuario(usuario);
    }
    // Elimina un usuario
    public void eliminarUsuario(String id){
        this.usuariosRepositorio.eliminarUsuario(id);
    }
    // Actualiza un usuario
    public void actualizarUsuario(Usuario usuario){
        this.usuariosRepositorio.actualizarUsuario(usuario);
    }
    // Devolver todos los usuarios que no son administradores
    public ArrayList<Usuario> getUsuariosNoAdministradores(){
        return this.usuariosRepositorio.getUsuariosNoAdmin();
    }
    // Devolver todos los usuarios que no son administradores ni el usuario actual
    public ArrayList<Usuario> getUsuariosNoAdministradores(String id){
        return this.usuariosRepositorio.getUsuariosNoAdminNiActual(id);
    }
    // Devolver un usuario por su numero de teléfono
    public Usuario getUsuarioPorTeléfono(String teléfono){
        return this.usuariosRepositorio.getUsuarioPorTeléfono(teléfono);
    }
    // Loguear un usuario
    public Usuario loguearUsuario(String teléfono, String clave){
        return this.usuariosRepositorio.loguearUsuario(teléfono, clave);
    }
}
