package com.example.graduatesproject;

import org.json.JSONObject;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/graduates")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        String universityEntrance = request.getParameter("universityEntrance");
        String universityGraduation = request.getParameter("universityGraduation");
        String formatOfEducation = request.getParameter("universityGraduation");
        String degree = request.getParameter("degree");
        String birthYear = request.getParameter("birthYear");
        String professionalDirection = request.getParameter("professionalDirection");
        String currentResidence = request.getParameter("currentResidence");
        String hometown = request.getParameter("hometown");
        List<Graduate> list = new ArrayList<>();
        List<JSONObject> JSONList = new ArrayList<>();
        try {
            GraduatesDAO.setConnection();
            list = GraduatesDAO.getGraduates();
            if (universityEntrance != null) { list = GraduatesDAO.getByUniversityEntranceYear(list, Integer.valueOf(universityEntrance)); }
            if (universityGraduation != null) { list = GraduatesDAO.getByUniversityGraduationYear(list, Integer.valueOf(universityGraduation)); }
            if (formatOfEducation != null) { list = GraduatesDAO.getByFormatOfEducation(list, formatOfEducation); }
            if (degree != null) { list = GraduatesDAO.getByDegree(list, degree); }
            if (birthYear != null) { list = GraduatesDAO.getByBirthYear(list, Integer.valueOf(birthYear)); }
            if (professionalDirection != null) { list = GraduatesDAO.getByProfessionalDirection(list, professionalDirection); }
            if (currentResidence != null) { list = GraduatesDAO.getByCurrentResidence(list, currentResidence); }
            if (hometown != null) { list = GraduatesDAO.getByHometown(list, hometown); }
            JSONList = GraduatesDAO.getJSONList(list);
            GraduatesDAO.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        JSONList.forEach(x -> out.println("<p>" + x + "</p>"));
        out.println("</body></html>");
    }

    public void destroy() {
    }
}