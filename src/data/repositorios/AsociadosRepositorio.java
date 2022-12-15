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

import data.modelos.Asociado;

import java.io.Serializable;
import java.util.ArrayList;

public class AsociadosRepositorio implements Serializable {
    // Array de asociados
    private ArrayList<Asociado> asociados = new ArrayList<>();

    public AsociadosRepositorio() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            guardarCambios();
            System.out.println("Guardando cambios en los asociados...");
        }, "Shutdown-thread"));
    }

    // Devolver todos los asociados
    public ArrayList<Asociado> getAsociados() {
        return asociados;
    }

    // Devolver un asociado por su ID
    public Asociado getAsociado(String id) {
        for (Asociado asociado : asociados) {
            if (asociado.getId().equals(id)) {
                return asociado;
            }
        }
        return null;
    }

    // Agregar un asociado
    public void agregarAsociado(Asociado asociado) {
        asociados.add(asociado);
    }

    // Eliminar un asociado
    public void eliminarAsociado(String id) {
        asociados.remove(getAsociado(id));
    }

    // Actualizar un asociado
    public void actualizarAsociado(Asociado asociado) {
        Asociado asociadoActual = getAsociado(asociado.getId());
        asociadoActual.setHallazgoId(asociado.getHallazgoId());
        asociadoActual.setTareaId(asociado.getTareaId());
    }

    // Guardar cambios en disco duro usando serializable
    public void guardarCambios() {
        Serializador.serializar(asociados, "asociados.dat");
    }

    // Cargar cambios desde disco duro usando serializable
    @SuppressWarnings("unchecked")
    public void cargarCambios() {
        System.out.println("Cargando asociados...");
        Object datos = Serializador.deserializar("asociados.dat");
        if(datos != null){
            this.asociados = (ArrayList<Asociado>) datos;
        } else {
            this.asociados = new ArrayList<>();
        }
    }
}
