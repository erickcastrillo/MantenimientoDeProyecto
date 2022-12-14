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

import java.util.Objects;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Clase para enviar correos electrónicos
 */
public class EnviarCorreo {
    private static final Configuración configuración = Configuración.getInstancia();
    // Enviar correo electrónico en Java a través del servidor SMTP proporcionado por el proveedor de alojamiento
    public static void enviarCorreoJava(String correoDestino,String CC, String BCC, String asunto, String mensaje) throws MessagingException {
        String from = "erick.castrillo@outlook.com";
        String host = "smtp.sendgrid.net";

        Properties properties = new Properties();
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", 587);
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(
                        "apikey",
                        configuración.config.getProperty("SENDGRID_API_KEY")
                );
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(correoDestino));
            if (!Objects.equals(CC, "") && !Objects.equals(CC, null)){
                message.addRecipient(Message.RecipientType.CC, new InternetAddress(CC));
            }
            if (!Objects.equals(BCC, "") && !Objects.equals(BCC, null)){
                message.addRecipient(Message.RecipientType.BCC, new InternetAddress(BCC));
            }
            message.setSubject(asunto);
            message.setText(mensaje);

            Transport.send(message);
        } catch (MessagingException mex) {
            System.out.println("Error al enviar el correo: " + mex.getMessage());
            throw mex;
        }
    }
}
