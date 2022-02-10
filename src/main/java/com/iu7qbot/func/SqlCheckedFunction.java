package com.iu7qbot.func;

import java.sql.SQLException;

public interface SqlCheckedFunction<T, R> {
    R apply(T t) throws SQLException;
}
