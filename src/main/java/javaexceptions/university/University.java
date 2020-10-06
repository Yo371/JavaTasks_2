package javaexceptions.university;

import javaexceptions.exceptions.*;
import javaexceptions.subjects.Subject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class University {
    private String name;
    private Set<Faculty> facultySet;

    public University(String name, Set<Faculty> facultySet) {
        this.name = name;
        this.facultySet = facultySet;
    }

    public University() {
        this.facultySet = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Faculty> getFacultySet() {
        return facultySet;
    }

    public void setFacultySet(Set<Faculty> facultySet) {
        this.facultySet = facultySet;
    }

    public double getAverageMarkByStudent(String name)
            throws AbsentStudentException, AbsentSubjectException {

        double average = 0;
        int count = 0;
        for(Faculty faculty : facultySet){
            count++;
            try {
               average = faculty.getStudentByNameInFaculty(name).getAverageMarkInAllSubjects();
            } catch (AbsentStudentException e) {
                if(average == 0 && count == facultySet.size())
                    throw new AbsentStudentException();
            }
        }

        return average;
    }

    public double getAverageMarkInUniversityBySubject(Subject subject)
            throws UniversityException {

        if(facultySet.isEmpty()) throw new AbsentFacultyException();

        double average = 0;
        int amountOfFacWthSubj = facultySet.size();
        for(Faculty faculty : facultySet){
            try {
                average += faculty.getAverageMarkInFacultyBySubject(subject);
            } catch (AbsentSubjectException e) {
                amountOfFacWthSubj--;
            }
        }

        if(amountOfFacWthSubj == 0) throw new AbsentSubjectException();

        return average/amountOfFacWthSubj;
    }

    public double getAverageMarkByFacultyByGroupBySubject(String nameOfFaculty,
                                                          String nameOfGroup, Subject subject)
            throws AbsentFacultyException, AbsentSubjectException, AbsentGroupException,
            AbsentStudentException {

        if(facultySet.isEmpty()) throw new AbsentFacultyException();

        ArrayList<Faculty> faculties = (ArrayList<Faculty>) facultySet.stream().
                filter(o -> o.getName().equalsIgnoreCase(nameOfFaculty)).collect(Collectors.toList());

        if(faculties.isEmpty()) throw new AbsentFacultyException();

        return faculties.get(0).getAverageMarkInEnteredGroupBySubject(nameOfGroup, subject);
    }

    public double getAverageMarkInUniversity() throws
            AbsentGroupException, AbsentStudentException {
        double average = 0;

        for(Faculty faculty : facultySet){
            average += faculty.getAverageMarkInWholeFaculty();
        }

        return average/facultySet.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        University that = (University) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(facultySet, that.facultySet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, facultySet);
    }

    @Override
    public String toString() {
        return "University{" +
                "name='" + name + '\'' +
                ", facultySet=" + facultySet +
                '}';
    }
}
