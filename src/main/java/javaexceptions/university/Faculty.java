package javaexceptions.university;

import javaexceptions.exceptions.AbsentGroupException;
import javaexceptions.exceptions.AbsentStudentException;
import javaexceptions.exceptions.AbsentSubjectException;
import javaexceptions.subjects.Subject;

import java.util.*;
import java.util.stream.Collectors;

public class Faculty {
    private String name;
    private Set<Group> groupSet;

    public Faculty(String name, Set<Group> groupSet) {
        this.name = name;
        this.groupSet = groupSet;
    }

    public Faculty(String name) {
        this.name = name;
        this.groupSet = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public Set<Group> getGroupSet() {
        return groupSet;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGroupSet(Set<Group> groupSet) {
        this.groupSet = groupSet;
    }

    public Student getStudentByNameInFaculty(String name) throws AbsentStudentException {
        int count = 0;
        Student student = null;
        for(Group group : groupSet){
            count++;
            try {
                student = group.getStudentByNameInGroup(name);
            } catch (AbsentStudentException e) {
                if(count == groupSet.size() && student == null)
                    throw new AbsentStudentException();
            }
        }
        return student;
    }

    public Group getGroupByName(String name) throws AbsentGroupException {

            ArrayList<Group> groups = (ArrayList<Group>) groupSet.stream().
                filter(o -> o.getName().equalsIgnoreCase(name)).collect(Collectors.toList());

            if(groups.isEmpty()) throw new AbsentGroupException();

            else return groups.get(0);
    }

    public double getAverageMarkInEnteredGroupBySubject(String nameOfGroup, Subject subject)
            throws AbsentGroupException, AbsentStudentException, AbsentSubjectException {
        return getGroupByName(nameOfGroup).getAverageMarkInGroupBySubject(subject);
    }

    public double getAverageMarkInFacultyBySubject(Subject subject)
            throws AbsentGroupException, AbsentStudentException, AbsentSubjectException {
        if(groupSet.isEmpty()) throw new AbsentGroupException();

        double average = 0;
        int amountOfGroupsWithMarkedSubject = groupSet.size();

        for(Group group : groupSet){
            try {
                average += group.getAverageMarkInGroupBySubject(subject);
            } catch (AbsentSubjectException e) {
                amountOfGroupsWithMarkedSubject--;
            }
        }

        if (amountOfGroupsWithMarkedSubject == 0)
            throw new AbsentSubjectException();

        return average/amountOfGroupsWithMarkedSubject;
    }

    public double getAverageMarkInWholeFaculty()
            throws AbsentGroupException, AbsentStudentException {
        if(groupSet.isEmpty()) throw new AbsentGroupException();

        double average = 0;

        for(Group group : groupSet){
            average += group.getAverageMarkInWholeGroup();
        }


        return average/groupSet.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Faculty faculty = (Faculty) o;
        return Objects.equals(name, faculty.name) &&
                Objects.equals(groupSet, faculty.groupSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, groupSet);
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "name='" + name + '\'' +
                ", groupSet=" + groupSet +
                '}';
    }
}
