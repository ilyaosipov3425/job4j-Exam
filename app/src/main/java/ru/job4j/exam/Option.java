package ru.job4j.exam;

/**
 * Класс Option - вариант ответов
 * @author Ilya Osipov (mailto:il.osipov.ya@yandex.ru)
 * @since 28.03.2019
 * @version $Id$
 */

public class Option {
    private int id;
    private String text;

    public Option(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }
}
