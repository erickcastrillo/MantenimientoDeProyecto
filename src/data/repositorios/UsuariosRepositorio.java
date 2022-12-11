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

import data.modelos.TipoUsuario;
import data.modelos.Usuario;

import java.util.ArrayList;

public class UsuariosRepositorio {
    // ArrayList de usuarios
    private ArrayList<Usuario> usuarios;

    // Devolver todos los usuarios
    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    // Devolver un usuario por su ID
    public Usuario getUsuario(String id) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId().equals(id)) {
                return usuario;
            }
        }
        return null;
    }

    // Agregar un usuario
    public void agregarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    // Eliminar un usuario
    public void eliminarUsuario(String id) {
        usuarios.remove(getUsuario(id));
    }

    // Actualizar un usuario
    public void actualizarUsuario(Usuario usuario) {
        Usuario usuarioActual = getUsuario(usuario.getId());
        usuarioActual.setCédula(usuario.getCédula());
        usuarioActual.setNombre(usuario.getNombre());
        usuarioActual.setPrimerApellido(usuario.getPrimerApellido());
        usuarioActual.setSegundoApellido(usuario.getSegundoApellido());
        usuarioActual.setCorreoElectrónico(usuario.getCorreoElectrónico());
        usuarioActual.setTipoUsuario(usuario.getTipoUsuario());
        usuarioActual.setTeléfono(usuario.getTeléfono());
        usuarioActual.setClave(usuario.getClave());
        usuarioActual.setBloqueado(usuario.getBloqueado());
        usuarioActual.setIntentosFallidos(usuario.getIntentosFallidos());
    }

    // Devolver todos los usuarios que no son administradores
    public ArrayList<Usuario> getUsuariosNoAdmin() {
        ArrayList<Usuario> usuariosNoAdmin = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            if (!usuario.getTipoUsuario().equals(TipoUsuario.ADMINISTRADOR)) {
                usuariosNoAdmin.add(usuario);
            }
        }
        return usuariosNoAdmin;
    }

    // Devolver todos los usuarios que no son administradores ni el usuario actual
    public ArrayList<Usuario> getUsuariosNoAdminNiActual(String id) {
        ArrayList<Usuario> usuariosNoAdminNiActual = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            if (!usuario.getTipoUsuario().equals(TipoUsuario.ADMINISTRADOR) && !usuario.getId().equals(id)) {
                usuariosNoAdminNiActual.add(usuario);
            }
        }
        return usuariosNoAdminNiActual;
    }

    // Loguear un usuario
    // Validar el numero de teléfono y la clave
    // si el usuario falla tres veces, bloquearlo
    public Usuario loguearUsuario(String teléfono, String clave) {
        Usuario usuario = getUsuarioPorTeléfono(teléfono);
        if (usuario != null && !usuario.getBloqueado()) {
            if (usuario.getClave().equals(clave)) {
                usuario.setIntentosFallidos(0);
                return usuario;
            } else {
                usuario.setIntentosFallidos(usuario.getIntentosFallidos() + 1);
                if (usuario.getIntentosFallidos() >= 3) {
                    usuario.setBloqueado(true);
                }
                return null;
            }
        }
        return null;
    }

    // Devolver un usuario por su numero de teléfono
    public Usuario getUsuarioPorTeléfono(String teléfono) {
        for (Usuario usuario : usuarios) {
            if (usuario.getTeléfono().equals(teléfono)) {
                return usuario;
            }
        }
        return null;
    }

    // Guardar cambios en disco duro usando serializable
    public void guardarCambios() {
        Serializador.serializar(usuarios, "usuarios.dat");
    }

    // Cargar cambios desde disco duro usando serializable
    @SuppressWarnings("unchecked")
    public void cargarCambios() {
        Object datos = Serializador.deserializar("usuarios.dat");
        if(datos != null){
            this.usuarios = (ArrayList<Usuario>) datos;
        } else {
            this.usuarios = new ArrayList<>();
        }
    }

    // Constructor
    public UsuariosRepositorio() {
        this.cargarCambios();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            guardarCambios();
            System.out.println("Guardando cambios en los usuarios...");
        }, "Shutdown-thread"));
    }
}
