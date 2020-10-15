package javaexceptions.university;

import javaexceptions.exceptions.AbsentSubjectException;
import javaexceptions.exceptions.InvalidMarkException;
import javaexceptions.subjects.Subject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Student implements Comparable<Student> {
    private String name;
    private Map<Subject, ArrayList<Integer>> marksMap;

    public Student(String name) {
        this.name = name;
        this.marksMap = new HashMap<>();
    }

    public Map<Subject, ArrayList<Integer>> getMarksMap() {
        return marksMap;
    }

    public String getName() {
        return name;
    }

    public void addMark(Subject subject, int mark) throws InvalidMarkException {

        if (mark < 0 || mark > 10) throw new InvalidMarkException();
        if (marksMap.containsKey(subject)) {
            marksMap.get(subject).add(mark);
        } else {
            marksMap.put(subject, new ArrayList<>());
            marksMap.get(subject).add(mark);
        }
    }

    public void addMark(String subject, int mark) throws InvalidMarkException {
        addMark(Subject.valueOf(subject.toUpperCase()), mark);
    }

    //Посчитать средний балл по всем предметам студента
    public double getAverageMarkInAllSubjects() throws AbsentSubjectException {
        if (marksMap.isEmpty()) throw new AbsentSubjectException();

        ArrayList<Integer> marks = new ArrayList<>();

        for (Map.Entry entry : marksMap.entrySet())
            marks.addAll((ArrayList<Integer>) entry.getValue());

        return getAverageMarkInArrList(marks);

    }

    public double getAverageMarkBySubject(Subject subject) throws AbsentSubjectException {
        if (!marksMap.containsKey(subject)) throw new AbsentSubjectException();

        return getAverageMarkInArrList(marksMap.get(subject));

    }

    private double getAverageMarkInArrList(ArrayList<Integer> marks) {
        double average = 0;
        for (int mark : marks)
            average += mark;

        return average / marks.size();
    }

    @Override
    public int compareTo(Student o) {
        return this.name.compareTo(o.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", marksMap=" + marksMap +
                '}';
    }

}
