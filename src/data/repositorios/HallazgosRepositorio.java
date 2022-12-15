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

import data.modelos.Hallazgo;

import java.util.ArrayList;

public class HallazgosRepositorio {
    // Array de hallazgos
    private ArrayList<Hallazgo> hallazgos = new ArrayList<>();

    public HallazgosRepositorio() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            guardarCambios();
            System.out.println("Guardando cambios en los hallazgos...");
        }, "Shutdown-thread"));
    }

    // Devolver todos los hallazgos
    public ArrayList<Hallazgo> getHallazgos() {
        return hallazgos;
    }

    // Devolver un hallazgo por su ID
    public Hallazgo getHallazgo(String id) {
        for (Hallazgo hallazgo : hallazgos) {
            if (hallazgo.getId().equals(id)) {
                return hallazgo;
            }
        }
        return null;
    }

    // Agregar un hallazgo
    public void agregarHallazgo(Hallazgo hallazgo) {
        hallazgos.add(hallazgo);
    }

    // Eliminar un hallazgo
    public void eliminarHallazgo(String id) {
        hallazgos.remove(getHallazgo(id));
    }

    // Actualizar un hallazgo
    public void actualizarHallazgo(Hallazgo hallazgo) {
        Hallazgo hallazgoActual = getHallazgo(hallazgo.getId());
        hallazgoActual.setComentario(hallazgo.getComentario());
        hallazgoActual.setResponsableId(hallazgo.getResponsableId());
        hallazgoActual.setTareaId(hallazgo.getTareaId());
    }

    // Guardar cambios en disco duro usando serializable
    public void guardarCambios() {
        Serializador.serializar(hallazgos, "hallazgos.dat");
    }

    // Cargar cambios desde disco duro usando serializable
    @SuppressWarnings("unchecked")
    public void cargarCambios() {
        System.out.println("Cargando hallazgos...");
        Object datos = Serializador.deserializar("hallazgos.dat");
        if(datos != null){
            this.hallazgos = (ArrayList<Hallazgo>) datos;
        } else {
            this.hallazgos = new ArrayList<>();
        }
    }
}
