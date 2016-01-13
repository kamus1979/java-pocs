package com.edwise.springbootseries.jooq.dao;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sample.jooq.domain.Tables;

import java.util.Optional;

@Repository
public class BooksDao {
    private static final Logger log = LoggerFactory.getLogger(BooksDao.class);

    private DSLContext create;

    @Autowired
    public BooksDao(DSLContext dslContext) {
        this.create = dslContext;
    }

    public boolean testDslContext() {
        return create != null;
    }

    public Result<Record> getAllBookCharacters() {
        Result<Record> result = create
                .select()
                .from(Tables.BOOK_CHARACTER)
                .fetch();

        log.info("Resultado query getAllBookCharacters: \n{}", result);
        return result;
    }

    public Result<Record> getAllBookCharactersOrderByName() {
        Result<Record> result = create
                .select()
                .from(Tables.BOOK_CHARACTER)
                .orderBy(Tables.BOOK_CHARACTER.NAME)
                .fetch();

        log.info("Resultado query getAllBookCharactersOrderByName: \n{}", result);
        return result;
    }

    public Optional<Record> getBookCharacterById(int id) {
        Optional<Record> result = create
                .select()
                .from(Tables.BOOK_CHARACTER)
                .where(Tables.BOOK_CHARACTER.ID.equal(id))
                .orderBy(Tables.BOOK_CHARACTER.NAME)
                .fetchOptional();

        log.info("Resultado query getBookCharacterById: \n{}",
                result);
        return result;
    }
}
