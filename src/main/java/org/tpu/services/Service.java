package org.tpu.services;

import java.util.List;

public interface Service<T> {
    List<T> readAll();

    void createOnDB(T obj);

    void deleteById(int id);

    T createOnFileSystem(Object meta);
}
