package com.hhd.algorithm.primary.class06;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 比较器
 * Created by huhengda on 2021/2/1.
 */
public class Code01_Comparator {
    public static class Student {
        public String name;
        public int id;
        public int age;

        public Student(String name, int id, int age) {
            this.name = name;
            this.id = id;
            this.age = age;
        }
    }

    public static class IdComparator implements Comparator<Student> {
        @Override
        public int compare(Student o1, Student o2) {
            return o1.id < o2.id ? -1 : 1;
        }
    }

    public static class AgeComparator implements Comparator<Student> {
        @Override
        public int compare(Student o1, Student o2) {
            return o1.age < o2.age ? -1 : 1;
        }
    }

    private static void printStudent(Student[] students) {
        for (Student student : students) {
            System.out.println(student.name + ",id=" + student.id + ",age=" + student.age);
        }
    }

    private static void printStudent1(List<Student> students) {
        for (Student student : students) {
            System.out.println(student.name + ",id=" + student.id + ",age=" + student.age);
        }
    }

    public static void main(String[] args) {
        Student s1 = new Student("zhangsan", 5, 26);
        Student s2 = new Student("lisi", 2, 39);
        Student s3 = new Student("wangwu", 4, 25);
        Student s4 = new Student("zhaoliu", 1, 19);
        Student s5 = new Student("zhouqi", 3, 30);
        Student[] students = {s1, s2, s3, s4, s5};
        printStudent(students);
        System.out.println("-------------");
        Arrays.sort(students, new IdComparator());
        printStudent(students);
        System.out.println("-------------");
        List<Student> students1 = Arrays.asList(s1, s2, s3, s4, s5);
        printStudent1(students1);
        System.out.println("-------------");
        students1.sort(new IdComparator());
        printStudent1(students1);
        System.out.println("-------------");
    }


}
