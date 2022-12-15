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

package data.modelos;

import java.io.Serializable;
import java.util.UUID;

public class Asociado implements Serializable {
    private final String id = UUID.randomUUID().toString();
    private String hallazgoId;

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    private String usuarioId;
    private String tareaId;

    public Asociado(String hallazgoId, String usuarioId, String tareaId) {
        this.hallazgoId = hallazgoId;
        this.usuarioId = usuarioId;
        this.tareaId = tareaId;
    }

    public Asociado() {
    }

    public String getHallazgoId() {
        return hallazgoId;
    }

    public void setHallazgoId(String hallazgoId) {
        this.hallazgoId = hallazgoId;
    }

    public String getTareaId() {
        return tareaId;
    }

    public void setTareaId(String tareaId) {
        this.tareaId = tareaId;
    }

    public String getId() {
        return id;
    }
}
