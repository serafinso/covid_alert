package com.polytechmtp.kafka.services;

import com.polytechmtp.kafka.repositories.UserRepository;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

@Service
public class LocationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JavaMailSender mailSender;

    /**
     * Calculate distance between two points in latitude and longitude taking
     * into account height difference. If you are not interested in height
     * difference pass 0.0. Uses Haversine method as its base.
     *
     * lat1, lon1 Start point lat2, lon2 End point
     * @returns Distance in Meters
     */
    public static double distance(double lat1, double lat2, double lon1,
                                  double lon2) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        distance = Math.pow(distance, 2);

        return Math.sqrt(distance);
    }

    /**
     * Get a diff between two dates
     * @param date1 the oldest date
     * @param date2 the newest date
     * @param timeUnit the unit in which you want the diff
     * @return the diff value, in the provided unit
     */
    public static long getDateDiff(Timestamp date1, Timestamp date2, TimeUnit timeUnit) {
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
    }

    /**
     * Send email alert
     * @param id the id of the new contact user
     */
    public void sendEmail(String id) {
        // get email properties
        String recipientAddesss = userRepository.getOne(id).getEmail();
        String subject = "Risque d'exposition au Coronavirus";
        String message = "Vous avez été en contact avec une personne testée positive au COVID-19."
                + "\r\n" + "Prenez rendez-vous dès maintenant pour réaliser votre test COVID-19 dans les délais."
                + "\r\n" + "Pensez à retirer vos masques en pharmacie et à vous isoler."
                + "\r\n" + "Prenez soin de vous, et de vos proches.";

        // send email
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddesss);
        email.setSubject(subject);
        email.setText(message);
        email.setFrom("COVID ALERT");
        mailSender.send(email);
    }

    public static String[] getMessage() throws IOException {
        BufferedReader reader;
        String[] ret = new String[0];
        File file = new File("positions-logs/my_topic-0/00000000000000000000.log");
        System.out.println("Absolute Path: " + file.getAbsolutePath());
        System.out.println("Canonical Path: " + file.getCanonicalPath());
        System.out.println("Path: " + file.getPath());
        try {
            reader = new BufferedReader(new FileReader(
                    "/usr/src/positions-logs/my_topic-0/00000000000000000000.log"));
            String line = reader.readLine();
            while (line != null) {
                ret = ArrayUtils.addAll(ret, StringUtils.substringsBetween(line, "[", "]"));
                line = reader.readLine();
            }
            reader.close();
            return ret;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }


}
