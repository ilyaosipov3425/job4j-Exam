package ru.job4j.exam.store;

/**
 * Класс ExamDbSchema - создает базу данных
 * @author Ilya Osipov (mailto:il.osipov.ya@yandex.ru)
 * @since 13.05.2019
 * @version $Id$
 */

public class ExamDbSchema {
    public static final class ExamTable {
        public static final String NAME = "exams";

        public static final class Cols {
            public static final String TITLE = "title";
        }
    }
}
