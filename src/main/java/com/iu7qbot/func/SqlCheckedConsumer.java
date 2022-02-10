package com.iu7qbot.func;

import java.sql.SQLException;

public interface SqlCheckedConsumer<T> {
    void accept(T t) throws SQLException;
}
