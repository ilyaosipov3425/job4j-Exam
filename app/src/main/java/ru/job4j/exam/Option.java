package ru.job4j.exam;

import android.annotation.TargetApi;
import android.os.Build;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Option option = (Option) o;
        return id == option.id;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
