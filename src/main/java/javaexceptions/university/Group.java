package javaexceptions.university;

import javaexceptions.exceptions.AbsentStudentException;
import javaexceptions.exceptions.AbsentSubjectException;
import javaexceptions.subjects.Subject;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

public class Group {
    private String name;
    private Set<Student> studentSet;

    public Group(String name, TreeSet<Student> studentSet) {
        this.name = name;
        this.studentSet = studentSet;
    }

    public Group() {
        this.studentSet = new TreeSet<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Student> getStudentSet() {
        return studentSet;
    }

    public void setStudentSet(TreeSet<Student> studentSet) {
        this.studentSet = studentSet;
    }

    public Student getStudentByNameInGroup(String name) throws AbsentStudentException {
        if (studentSet.isEmpty()) throw new AbsentStudentException();

        return Optional.of(studentSet.stream()
                .filter(e -> e.getName().equalsIgnoreCase(name)).findAny()
                .orElseThrow(() -> new AbsentStudentException())).get();

    }

    public double getAverageMarkInGroupBySubject(Subject subject)
            throws AbsentStudentException, AbsentSubjectException {
        if (studentSet.isEmpty()) throw new AbsentStudentException();

        double average = 0, value;
        int amountOfStudentsWithMarkedSubject = studentSet.size();
        for (Student student : studentSet) {
            try {
                value = student.getAverageMarkBySubject(subject);
                average += value;
            } catch (AbsentSubjectException e) {
                amountOfStudentsWithMarkedSubject--;
            }
        }

        if (amountOfStudentsWithMarkedSubject == 0)
            throw new AbsentSubjectException();
        return average / amountOfStudentsWithMarkedSubject;
    }

    public double getAverageMarkInWholeGroup() throws AbsentStudentException {
        if (studentSet.isEmpty()) throw new AbsentStudentException();

        double average = 0;
        int amountOfStudentsWithMarkedSubjects = studentSet.size();

        for (Student student : studentSet) {
            try {
                average += student.getAverageMarkInAllSubjects();
            } catch (AbsentSubjectException e) {
                amountOfStudentsWithMarkedSubjects--;
            }
        }
        return average / amountOfStudentsWithMarkedSubjects;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return Objects.equals(name, group.name) &&
                Objects.equals(studentSet, group.studentSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, studentSet);
    }

    @Override
    public String toString() {
        return "Group{" +
                "name='" + name + '\'' +
                ", studentSet=" + studentSet +
                '}';
    }
}
