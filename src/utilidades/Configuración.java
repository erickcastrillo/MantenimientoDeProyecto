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

package utilidades;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * Clase para obtener los datos de configuración del archivo de propiedades, la clase se maneja como un singleton para evitar
 * que se instancie más de una vez.
 */
public class Configuración {
    private static Configuración instancia = null;
    public Properties config = new Properties();
    private Configuración() {
        cargar();
    }
    private void cargar() {
        // Cargar configuración de la aplicación
        try {
            String configFilePath = "src/config.properties";
            FileInputStream propsInput = new FileInputStream(configFilePath);
            config.load(propsInput);
        } catch (Exception e) {
            System.out.println("Error al cargar la configuración de la aplicación: " + e.getMessage());
        }
    }

    public static Configuración getInstancia() {
        if (instancia == null) {
            instancia = new Configuración();
        }
        return instancia;
    }
}
