package com.example.graduatesproject;

public class Graduate {
    private int universityEntrance;
    private int universityGraduation;
    private String formatOfEducation;
    private String degree;
    private int birthYear;
    private String professionalDirection;
    private String hometown;
    private String currentResidence;

    public Graduate(int universityEntrance, int universityGraduation, String formatOfEducation, String degree,
                    int birthYear, String professionalDirection, String hometown, String currentResidence) {
        this.universityEntrance = universityEntrance;
        this.universityGraduation = universityGraduation;
        this.formatOfEducation = formatOfEducation;
        this.degree = degree;
        this.birthYear = birthYear;
        this.professionalDirection = professionalDirection;
        this.hometown = hometown;
        this.currentResidence = currentResidence;
    }

    public int getUniversityEntrance() {
        return universityEntrance;
    }

    public int getUniversityGraduation() {
        return universityGraduation;
    }

    public String getFormatOfEducation() {
        return formatOfEducation;
    }

    public String getDegree() {
        return degree;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public String getProfessionalDirection() {
        return professionalDirection;
    }

    public String getHometown() {
        return hometown;
    }

    public String getCurrentResidence() {
        return currentResidence;
    }

    @Override
    public String toString() {
        return "universityEntrance = " + universityEntrance +
                ",\nuniversityGraduation = " + universityGraduation +
                ",\nformatOfEducation = '" + formatOfEducation + '\'' +
                ",\ndegree = '" + degree + '\'' +
                ",\nbirthYear = " + birthYear +
                ",\nprofessionalDirection = '" + professionalDirection + '\'' +
                ",\nhometown = '" + hometown + '\'' +
                ",\ncurrentResidence = '" + currentResidence + '\'' + '\n';
    }
}