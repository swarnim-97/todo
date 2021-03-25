package dev.swarnim.project.database;

import dev.swarnim.project.model.request.Note;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface NoteDao {

    @SqlUpdate("Insert into notes (note, customer_id) values (:note, :customerId)")
    int createNote(@BindBean Note note,
                   Long customerId);

    @SqlQuery("SELECT note from notes where customer_id=:customerId")
    @RegisterBeanMapper(Note.class)
    List<Note> getNotes(Long customerId);
}
