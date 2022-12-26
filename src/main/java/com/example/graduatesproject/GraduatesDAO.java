package com.example.graduatesproject;

import org.json.JSONObject;

import java.sql.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

abstract class GraduatesDAO {
    private static final String url = "jdbc:h2:D:/IntelliJ IDEA/graduates/graduates";
    private static Connection con;
    private static PreparedStatement preparedStatement;
    private static ResultSet rs;

    private GraduatesDAO() throws SQLException, ClassNotFoundException {}

    static void setConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.h2.Driver");
        con = DriverManager.getConnection(url);
    }

    static void closeConnection() throws SQLException {
        if (!(con == null)) con.close();
        if (!(preparedStatement == null)) preparedStatement.close();
        if (!(rs == null)) rs.close();
    }

    static List<Graduate> getGraduates() throws SQLException {
        preparedStatement = con.prepareStatement("SELECT * FROM GRADUATES");
        rs = preparedStatement.executeQuery();
        List<Graduate> graduatesList = parseGraduatesList(rs);
        return graduatesList;
    }

    /*static void printStatistics(List<Graduate> graduateList) {
        Map<Integer, Integer> graduateMap = new HashMap<>();
        for (Graduate graduate : graduateList) {
            if (graduateMap.containsKey(graduate.getUniversityEntrance())) {
                graduateMap.put(graduate.getUniversityEntrance(), graduateMap.get(graduate.getUniversityEntrance()) + 1);
            } else {
                graduateMap.put(graduate.getUniversityEntrance(), 1);
            }
        }
        System.out.println("University entrance statistics:\n" );
        graduateMap.forEach((k, v) -> {
            System.out.println(String.valueOf(k) + ": " + (int)((double)v / graduateMap.size() * 100) + "%");
        });
    }*/

    static List<Graduate> getByUniversityEntranceYear(int firstValue, int secondValue) throws SQLException {
        preparedStatement = con.prepareStatement("SELECT * FROM GRADUATES WHERE UNIVERSITY_ENTRANCE >= ? AND UNIVERSITY_ENTRANCE <= ?");
        preparedStatement.setInt(1, Integer.min(firstValue, secondValue));
        preparedStatement.setInt(2, Integer.max(firstValue, secondValue));
        rs = preparedStatement.executeQuery();
        List<Graduate> graduatesList = parseGraduatesList(rs);
        return graduatesList;
    }

    static List<Graduate> getByUniversityEntranceYear(List<Graduate> graduatesList, int firstValue, int secondValue) throws SQLException {
        int temp;
        if (firstValue > secondValue) {
            temp = firstValue;
            firstValue = secondValue;
            secondValue = temp;
        }
        List<Graduate> newList = new ArrayList<>();
        int universityEntrance;
        for (int i = 0; i < graduatesList.size(); i++) {
            universityEntrance = graduatesList.get(i).getUniversityEntrance();
            if (universityEntrance >= firstValue && universityEntrance <= secondValue) {
                newList.add(graduatesList.get(i));
            }
        }
        return newList;
    }

    static List<Graduate> getByUniversityEntranceYear(int value) throws SQLException {
        preparedStatement = con.prepareStatement("SELECT * FROM GRADUATES WHERE UNIVERSITY_ENTRANCE = ?");
        preparedStatement.setInt(1, value);
        rs = preparedStatement.executeQuery();
        List<Graduate> graduatesList = parseGraduatesList(rs);
        return graduatesList;
    }

    static List<Graduate> getByUniversityEntranceYear(List<Graduate> graduatesList, int value) throws SQLException {
        List<Graduate> newList = new ArrayList<>();
        int universityEntrance;
        for (int i = 0; i < graduatesList.size(); i++) {
            universityEntrance = graduatesList.get(i).getUniversityEntrance();
            if (universityEntrance == value) {
                newList.add(graduatesList.get(i));
            }
        }
        return newList;
    }

    static List<Graduate> getByUniversityGraduationYear(int firstValue, int secondValue) throws SQLException {
        preparedStatement = con.prepareStatement("SELECT * FROM GRADUATES WHERE UNIVERSITY_GRADUATION >= ? AND UNIVERSITY_GRADUATION <= ?");
        preparedStatement.setInt(1, Integer.min(firstValue, secondValue));
        preparedStatement.setInt(2, Integer.max(firstValue, secondValue));
        rs = preparedStatement.executeQuery();
        List<Graduate> graduatesList = parseGraduatesList(rs);
        return graduatesList;
    }

    static List<Graduate> getByUniversityGraduationYear(List<Graduate> graduatesList, int firstValue, int secondValue) throws SQLException {
        int temp;
        if (firstValue > secondValue) {
            temp = firstValue;
            firstValue = secondValue;
            secondValue = temp;
        }
        List<Graduate> newList = new ArrayList<>();
        int universityGraduation;
        for (int i = 0; i < graduatesList.size(); i++) {
            universityGraduation = graduatesList.get(i).getUniversityGraduation();
            if (universityGraduation >= firstValue && universityGraduation <= secondValue) {
                newList.add(graduatesList.get(i));
            }
        }
        return newList;
    }

    static List<Graduate> getByUniversityGraduationYear(int value) throws SQLException {
        preparedStatement = con.prepareStatement("SELECT * FROM GRADUATES WHERE UNIVERSITY_GRADUATION = ?");
        preparedStatement.setInt(1, value);
        rs = preparedStatement.executeQuery();
        List<Graduate> graduatesList = parseGraduatesList(rs);
        return graduatesList;
    }

    static List<Graduate> getByUniversityGraduationYear(List<Graduate> graduatesList, int value) throws SQLException {
        List<Graduate> newList = new ArrayList<>();
        int universityGraduation;
        for (int i = 0; i < graduatesList.size(); i++) {
            universityGraduation = graduatesList.get(i).getUniversityGraduation();
            if (universityGraduation == value) {
                newList.add(graduatesList.get(i));
            }
        }
        return newList;
    }

    static List<Graduate> getByBirthYear(int firstValue, int secondValue) throws SQLException {
        preparedStatement = con.prepareStatement("SELECT * FROM GRADUATES WHERE BIRTH_YEAR >= ? AND BIRTH_YEAR <= ?");
        preparedStatement.setInt(1, Integer.min(firstValue, secondValue));
        preparedStatement.setInt(2, Integer.max(firstValue, secondValue));
        rs = preparedStatement.executeQuery();
        List<Graduate> graduatesList = parseGraduatesList(rs);
        return graduatesList;
    }

    static List<Graduate> getByBirthYear(List<Graduate> graduatesList, int firstValue, int secondValue) throws SQLException {
        int temp;
        if (firstValue > secondValue) {
            temp = firstValue;
            firstValue = secondValue;
            secondValue = temp;
        }
        List<Graduate> newList = new ArrayList<>();
        int birthYear;
        for (int i = 0; i < graduatesList.size(); i++) {
            birthYear = graduatesList.get(i).getBirthYear();
            if (birthYear >= firstValue && birthYear <= secondValue) {
                newList.add(graduatesList.get(i));
            }
        }
        return newList;
    }

    static List<Graduate> getByBirthYear(int value) throws SQLException {
        preparedStatement = con.prepareStatement("SELECT * FROM GRADUATES WHERE BIRTH_YEAR = ?");
        preparedStatement.setInt(1, value);
        rs = preparedStatement.executeQuery();
        List<Graduate> graduatesList = parseGraduatesList(rs);
        return graduatesList;
    }

    static List<Graduate> getByBirthYear(List<Graduate> graduatesList, int value) throws SQLException {
        List<Graduate> newList = new ArrayList<>();
        int birthYear;
        for (int i = 0; i < graduatesList.size(); i++) {
            birthYear = graduatesList.get(i).getBirthYear();
            if (birthYear == value) {
                newList.add(graduatesList.get(i));
            }
        }
        return newList;
    }

    static List<Graduate> getByFormatOfEducation(String value) throws SQLException {
        value = prepareRegexStringQuery(value);
        preparedStatement = con.prepareStatement(
                "SELECT * FROM GRADUATES WHERE UPPER(FORMAT_OF_EDUCATION) LIKE UPPER(?) ESCAPE '!'");
        preparedStatement.setString(1, "%" + value + "%");
        rs = preparedStatement.executeQuery();
        List<Graduate> graduatesList = parseGraduatesList(rs);
        return graduatesList;
    }

    static List<Graduate> getByFormatOfEducation(List<Graduate> graduatesList, String value) throws SQLException {
        List<Graduate> newList = new ArrayList<>();
        Pattern pattern = Pattern.compile((".*" + value + ".*").toUpperCase(Locale.ROOT));
        Matcher matcher;
        String formatOfEducation;
        for (int i = 0; i < graduatesList.size(); i++) {
            formatOfEducation = graduatesList.get(i).getFormatOfEducation().toUpperCase();
            matcher = pattern.matcher(formatOfEducation);
            if (matcher.find()) {
                newList.add(graduatesList.get(i));
            }
        }
        return newList;
    }

    static List<Graduate> getByDegree(String value) throws SQLException {
        value = prepareRegexStringQuery(value);
        preparedStatement = con.prepareStatement(
                "SELECT * FROM GRADUATES WHERE UPPER(DEGREE) LIKE UPPER(?) ESCAPE '!'");
        preparedStatement.setString(1, "%" + value + "%");
        rs = preparedStatement.executeQuery();
        List<Graduate> graduatesList = parseGraduatesList(rs);
        return graduatesList;
    }

    static List<Graduate> getByDegree(List<Graduate> graduatesList, String value) throws SQLException {
        List<Graduate> newList = new ArrayList<>();
        Pattern pattern = Pattern.compile((".*" + value + ".*").toUpperCase(Locale.ROOT));
        Matcher matcher;
        String degree;
        for (int i = 0; i < graduatesList.size(); i++) {
            degree = graduatesList.get(i).getDegree().toUpperCase();
            matcher = pattern.matcher(degree);
            if (matcher.find()) {
                newList.add(graduatesList.get(i));
            }
        }
        return newList;
    }

    static List<Graduate> getByProfessionalDirection(String value) throws SQLException {
        value = prepareRegexStringQuery(value);
        preparedStatement = con.prepareStatement(
                "SELECT * FROM GRADUATES WHERE UPPER(PROFESSIONAL_DIRECTION) LIKE UPPER(?) ESCAPE '!'");
        preparedStatement.setString(1, "%" + value + "%");
        rs = preparedStatement.executeQuery();
        List<Graduate> graduatesList = parseGraduatesList(rs);
        return graduatesList;
    }

    static List<Graduate> getByProfessionalDirection(List<Graduate> graduatesList, String value) throws SQLException {
        List<Graduate> newList = new ArrayList<>();
        Pattern pattern = Pattern.compile((".*" + value + ".*").toUpperCase(Locale.ROOT));
        Matcher matcher;
        String professionalDirection;
        for (int i = 0; i < graduatesList.size(); i++) {
            professionalDirection = graduatesList.get(i).getProfessionalDirection().toUpperCase();
            matcher = pattern.matcher(professionalDirection);
            if (matcher.find()) {
                newList.add(graduatesList.get(i));
            }
        }
        return newList;
    }

    static List<Graduate> getByHometown(String value) throws SQLException {
        value = prepareRegexStringQuery(value);
        preparedStatement = con.prepareStatement(
                "SELECT * FROM GRADUATES WHERE UPPER(HOMETOWN) LIKE UPPER(?) ESCAPE '!'");
        preparedStatement.setString(1, "%" + value + "%");
        rs = preparedStatement.executeQuery();
        List<Graduate> graduatesList = parseGraduatesList(rs);
        return graduatesList;
    }

    static List<Graduate> getByHometown(List<Graduate> graduatesList, String value) throws SQLException {
        List<Graduate> newList = new ArrayList<>();
        Pattern pattern = Pattern.compile((".*" + value + ".*").toUpperCase(Locale.ROOT));
        Matcher matcher;
        String hometown;
        for (int i = 0; i < graduatesList.size(); i++) {
            hometown = graduatesList.get(i).getHometown().toUpperCase();
            matcher = pattern.matcher(hometown);
            if (matcher.find()) {
                newList.add(graduatesList.get(i));
            }
        }
        return newList;
    }

    static List<Graduate> getByCurrentResidence(String value) throws SQLException {
        value = prepareRegexStringQuery(value);
        preparedStatement = con.prepareStatement(
                "SELECT * FROM GRADUATES WHERE UPPER(CURRENT_RESIDENCE) LIKE UPPER(?) ESCAPE '!'");
        preparedStatement.setString(1, "%" + value + "%");
        rs = preparedStatement.executeQuery();
        List<Graduate> graduatesList = parseGraduatesList(rs);
        return graduatesList;
    }

    static List<Graduate> getByCurrentResidence(List<Graduate> graduatesList, String value) throws SQLException {
        List<Graduate> newList = new ArrayList<>();
        Pattern pattern = Pattern.compile((".*" + value + ".*").toUpperCase(Locale.ROOT));
        Matcher matcher;
        String currentResidence;
        for (int i = 0; i < graduatesList.size(); i++) {
            currentResidence = graduatesList.get(i).getCurrentResidence().toUpperCase();
            matcher = pattern.matcher(currentResidence);
            if (matcher.find()) {
                newList.add(graduatesList.get(i));
            }
        }
        return newList;
    }

    static List<JSONObject> getJSONList(List<Graduate> graduateList) {
        List<JSONObject> list = new ArrayList<>();
        for (Graduate graduate : graduateList) {
            list.add(new JSONObject(graduate));
        }
        list.forEach(System.out::println);
        return list;
    }

    static void printGraduatesList(List<Graduate> graduateList) {
        for (Graduate graduate : graduateList) {
            System.out.println(graduate);
        }
    }

    private static String prepareRegexStringQuery(String value) {
        return value
                .replace("!", "!!")
                .replace("%", "!%")
                .replace("_", "!_")
                .replace("[", "![");
    }

    private static List<Graduate> parseGraduatesList(ResultSet rs) throws SQLException {
        List<Graduate> graduateList = new ArrayList<>();
        while (rs.next()) {
            graduateList.add(new Graduate(
                    rs.getInt("UNIVERSITY_ENTRANCE"),
                    rs.getInt("UNIVERSITY_GRADUATION"),
                    rs.getString("FORMAT_OF_EDUCATION"),
                    rs.getString("DEGREE"),
                    rs.getInt("BIRTH_YEAR"),
                    rs.getString("PROFESSIONAL_DIRECTION"),
                    rs.getString("HOMETOWN"),
                    rs.getString("CURRENT_RESIDENCE")));
        }
        return graduateList;
    }

    private static void fillPreparedStatement(PreparedStatement preparedStatement,
                                              int universityEntrance, int universityGraduation, String formatOfEducation, String degree,
                                              int birthYear, String professionalDirection, String hometown, String currentResidence) throws SQLException {
        preparedStatement.setInt(1, universityEntrance);
        preparedStatement.setInt(2, universityGraduation);
        preparedStatement.setString(3, formatOfEducation);
        preparedStatement.setString(4, degree);
        preparedStatement.setInt(5, birthYear);
        preparedStatement.setString(6, professionalDirection);
        preparedStatement.setString(7, hometown);
        preparedStatement.setString(8, currentResidence);
    }
}