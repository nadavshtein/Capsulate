package com.example.capsulate.DAO;

import androidx.core.util.Consumer;

import java.util.Optional;

public interface Dao<T> {

        void get(String id, Consumer<Optional<T>> consumer);

        void save(T t);

        void delete(T t);
}
