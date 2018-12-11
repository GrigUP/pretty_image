package org.tpu.database.interfaces;

import java.sql.Connection;

public interface DBFactory {
    Connection connect();

    void close();
}
