package hcv.servlets;

import hcv.database.Database;
import hcv.database.DatabaseException;
import hcv.utils.Utility;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Tomo.
 */
public class InitDatabase extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();

        Database db = new Database(Utility.getLocalConnection());
        try {
            db.dropTable("trainings");
            db.createTrainingsTable();
        } catch (DatabaseException e) {
            e.printStackTrace();
            out.println("FAIL: " + e.getMessage());
            return;
        }
        out.println("SUCCES");
    }
}
