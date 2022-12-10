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

import data.modelos.Usuario;

import javax.mail.MessagingException;

public class Seguridad {
    // Generar un código aleatorio de 6 caracteres de números y letras
    public static String generarCódigo() {
        StringBuilder código = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int num = (int) (Math.random() * 62);
            if (num < 10) {
                código.append(num);
            } else if (num < 36) {
                código.append((char) (num + 55));
            } else {
                código.append((char) (num + 61));
            }
        }
        return código.toString();
    }

    // Enviar correo electrónico con el código de verificación a un usuario proporcionado
    public static void enviarCorreoDeVerificación(Usuario usuario) throws MessagingException {
        String asunto = "Código de verificación";
        String código = generarCódigo();
        String cuerpo = "Hola " + usuario.getNombre() + ",\n\n"
                + "Tu código de verificación es: " + código + "\n\n"
                + "Saludos,\n"
                + "Equipo de desarrollo de la aplicación";
        usuario.setCódigoDeVerificación(código);
        // Se envía el correo electrónico con el código de verificación
        // Como el proceso puede tardar, se ejecuta en un hilo aparte y asi evitar bloquear el hilo principal
        Thread thread = new Thread(() -> {
            try {
                EnviarCorreo.enviarCorreoJava(usuario.getCorreoElectrónico(), asunto, cuerpo);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        });
    }

    // Validar el código de verificación de un usuario
    public static boolean validarCódigoDeVerificación(Usuario usuario, String código) {
        return usuario.getCódigoDeVerificación().equals(código);
    }

    // Desbloquear un usuario
    public static void desbloquearUsuario(Usuario usuario) {
        usuario.setBloqueado(false);
        usuario.setIntentosFallidos(0);
    }

    // Restablecer la contraseña de un usuario
    public static boolean restablecerContraseña(Usuario usuario, String código, String nuevaContraseña) {
        if (validarCódigoDeVerificación(usuario, código)) {
            desbloquearUsuario(usuario);
            usuario.setClave(nuevaContraseña);
            return true;
        } else {
            return false;
        }
    }

}
