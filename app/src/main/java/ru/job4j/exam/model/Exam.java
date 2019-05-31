package ru.job4j.exam.model;

/**
 * Класс Exam - модель данных описывающих экзамен
 * @author Ilya Osipov (mailto:il.osipov.ya@yandex.ru)
 * @since 15.04.2019
 * @version $Id$
 */

public class Exam {

    private int id;
    private String name;
    private long time;
    private int result;

    public Exam(int id, String name, long time, int result) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.result = result;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getTime() {
       return time;
    }

    public int getResult() {
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exam exam = (Exam) o;
        return id == exam.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "id= " + id +
                ", name= " + name + '\'' +
                ", time= " + time +
                ", result= " + result +
                '}';
    }
}
