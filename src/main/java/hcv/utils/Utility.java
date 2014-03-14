package hcv.utils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by Tomo.
 */
public class Utility {

    public static String getBody(HttpServletRequest request){

        StringBuilder stringBuilder = new StringBuilder();
        String line;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null)
                stringBuilder.append(line);
        } catch (Exception e) {
            return null;
        }

        return stringBuilder.toString();
    }

    public static Connection getLocalConnection() {

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception e) {
            return null;
        }

        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/hcvdb", "root", "root");

        } catch (Exception e) {
            // TODO throw exception
            return null;
        }
    }
}
