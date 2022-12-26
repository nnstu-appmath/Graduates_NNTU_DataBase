package com.example.graduatesproject;

import java.sql.*;

public class Main {
    public static void clientCode() throws SQLException {
        GraduatesDAO.printGraduatesList(GraduatesDAO.getByHometown(GraduatesDAO.getByBirthYear(GraduatesDAO.getByProfessionalDirection("software"),1990, 1995) ,"nizhn"));
    }

    public static void main(String[] argc) {
        try {
            GraduatesDAO.setConnection();
            System.out.println("The connection is established");
            clientCode();
        } catch (SQLException SQLEx) {
            SQLEx.printStackTrace();
        } catch (ClassNotFoundException CNFEx) {
            CNFEx.printStackTrace();
        } finally {
            try {
                GraduatesDAO.closeConnection();
            } catch (SQLException SQLEx) {
                SQLEx.printStackTrace();
            }
            System.out.println("The connection is closed");
        }
    }
}
