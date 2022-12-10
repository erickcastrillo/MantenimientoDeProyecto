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

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.NoSuchElementException;

/**
 * Clase usada para serializar y deserializar datos y guardarlos en el disco duro.
 * @author Erick Castrillo Arroyo <ecastrillo@edu.upolitecnica.cr>
 * 603630082EC
 */
public class Serializador {
    /***
     * Serializa una clase u Objeto y lo guarda en el disco duro
     * @param obj Objeto o Clase a Serializar
     * @param fileName Ubicación del archivo a guardar
     * @return true si el objeto se serializo o false si no se logro
     */
    public static Boolean serializar(Object obj, String fileName) {
        try{
            ObjectOutputStream salida = new ObjectOutputStream(
                    Files.newOutputStream(Paths.get(fileName))
            );
            salida.writeObject(obj);
            salida.close();
            return true;
        } catch(NoSuchElementException | IOException e){
            return false;
        }
    }

    /***
     * Deserializa un archivo y lo regresa como un objeto que después se puede castear a una clase concreta
     * @param fileName Ubicación del archivo a guardar
     * @return objeto o clase
     */
    public static Object deserializar(String fileName)  {

        Path archivo = Paths.get(fileName);
        if(Files.exists(archivo)){
            try{
                ObjectInputStream entrada = new ObjectInputStream(
                        Files.newInputStream(archivo)
                );
                Object obj = entrada.readObject();
                entrada.close();
                return obj;
            } catch(IOException | ClassNotFoundException e){
                return null;
            }
        } else {
            return null;
        }
    }
}
