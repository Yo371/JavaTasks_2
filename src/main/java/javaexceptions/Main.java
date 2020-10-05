package javaexceptions;

import javaexceptions.exceptions.UniversityException;
import javaexceptions.subjects.Subject;
import javaexceptions.university.Faculty;
import javaexceptions.university.Group;
import javaexceptions.university.Student;
import javaexceptions.university.University;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {

        Student studentIvanov = new Student("Ivan Ivanov");
        Student studentSidorov = new Student("Sidor Sidorov");
        Student studentPetrov = new Student("Petr Petrov");
        Student studentEvlampov = new Student("Evlampiy Evlampov");
        try {
            studentIvanov.addMark("english", 8);
            studentIvanov.addMark("english", 9);
            studentIvanov.addMark(Subject.CHEMISTRY, 10);

            studentSidorov.addMark("english", 6);
            studentSidorov.addMark("english", 4);
            studentSidorov.addMark(Subject.CHEMISTRY, 6);

            studentPetrov.addMark("english", 9);
            studentPetrov.addMark("english", 3);
            studentPetrov.addMark(Subject.CHEMISTRY, 6);

            studentEvlampov.addMark("english", 6);
            studentEvlampov.addMark("english", 9);
            studentEvlampov.addMark(Subject.CHEMISTRY, 7);

            TreeSet<Student> studentsSet = new TreeSet<>();
            studentsSet.add(studentIvanov);
            studentsSet.add(studentPetrov);
            Group group22A = new Group("22A", studentsSet);
            studentsSet = new TreeSet<>();
            studentsSet.add(studentSidorov);
            studentsSet.add(studentEvlampov);
            Group group33A = new Group("33A", studentsSet);

            Set<Group> groupSet = new HashSet<>();
            groupSet.add(group22A);
            groupSet.add(group33A);

            Faculty facultyPhysic = new Faculty("Physic faculty", groupSet);
            Faculty facultyEnglish = new Faculty("English faculty", groupSet);

            Set<Faculty> facultySet = new HashSet<>();
            facultySet.add(facultyEnglish);
            facultySet.add(facultyPhysic);

            University university = new University("Oxford University", facultySet);

            //Посчитать средний балл по всем предметам студента
            System.out.println(university.getAverageMarkByStudent("ivan ivanov"));

            //Посчитать средний балл по конкретному предмету в
            // конкретной группе и на конкретном факультете
            System.out.println(university.
                    getAverageMarkByFacultyByGroupBySubject("english faculty",
                    "22a", Subject.ENGLISH));

            //Посчитать средний балл по предмету для всего университета
            System.out.println(university.getAverageMarkInUniversityBySubject(Subject.ENGLISH));




        } catch (UniversityException e) {
            e.printStackTrace();
        }


    }
}
