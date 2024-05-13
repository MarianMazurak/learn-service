package org.example.entity;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.locator.ClasspathSqlLocator;
import org.jdbi.v3.sqlobject.transaction.Transaction;

import java.util.List;

public class TestEntity {

    private Jdbi jdbi;

    public TestEntity(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    @Transaction
    public List<String> getRowFromTest() {
        //Consumer
        jdbi.withHandle(h -> {
            //   turn off trailing semicolons for script statements
//            h.getConfig(SqlStatements.class).setScriptStatementsNeedSemicolon(false);
            return h.createScript(ClasspathSqlLocator.removingComments().getResource("script/Test.sql")).execute();
        });

        //Callback
        List<String> ids = jdbi.withHandle(handle -> handle.createQuery("select id from \"Test\"").mapTo(String.class).list());
        return ids;
    }
}
