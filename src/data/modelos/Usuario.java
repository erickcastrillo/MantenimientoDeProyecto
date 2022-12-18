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

/**
 * Clase usada para representar usuarios en el sistema
 * @author Erick Castrillo Arroyo <ecastrillo@edu.upolitecnica.cr>
 * 603630082EC
 */
public class Usuario implements Serializable {
    private final String id = UUID.randomUUID().toString();
    private String cédula;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private String correoElectrónico;
    private TipoUsuario tipoUsuario;
    private String teléfono;
    private String clave;
    private Boolean bloqueado;
    private int intentosFallidos;
    private String códigoDeVerificación;

    public Usuario() {}

    public Usuario(String cédula,
                   String nombre,
                   String primerApellido,
                   String segundoApellido,
                   TipoUsuario tipoUsuario,
                   String teléfono,
                   String clave,
                   Boolean bloqueado,
                   String correoElectrónico,
                   int intentosFallidos,
                   String códigoDeVerificación) {
        this.cédula = cédula;
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.tipoUsuario = tipoUsuario;
        this.teléfono = teléfono;
        this.clave = clave;
        this.bloqueado = bloqueado;
        this.correoElectrónico = correoElectrónico;
        this.intentosFallidos = intentosFallidos;
        this.códigoDeVerificación = códigoDeVerificación;
    }

    public String getCédula() {
        return cédula;
    }

    public void setCédula(String cédula) {
        this.cédula = cédula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String nombreCompleto() {
        return nombre + " " + primerApellido + " " + segundoApellido;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getTeléfono() {
        return teléfono;
    }

    public void setTeléfono(String teléfono) {
        this.teléfono = teléfono;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getId() {
        return id;
    }

    public Boolean getBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(Boolean bloqueado) {
        this.bloqueado = bloqueado;
    }

    public String getCorreoElectrónico() {
        return correoElectrónico;
    }

    public void setCorreoElectrónico(String correoElectrónico) {
        this.correoElectrónico = correoElectrónico;
    }

    public int getIntentosFallidos() {
        return intentosFallidos;
    }

    public void setIntentosFallidos(int intentosFallidos) {
        this.intentosFallidos = intentosFallidos;
    }

    public String getCódigoDeVerificación() {
        return códigoDeVerificación;
    }

    public void setCódigoDeVerificación(String códigoDeVerificación) {
        this.códigoDeVerificación = códigoDeVerificación;
    }

    @Override
    public String toString() {
        return nombreCompleto();
    }
}
