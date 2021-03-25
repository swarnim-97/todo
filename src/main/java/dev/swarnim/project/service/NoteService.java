package dev.swarnim.project.service;

import dev.swarnim.project.database.CustomerDao;
import dev.swarnim.project.database.NoteDao;
import dev.swarnim.project.errorhandler.InvalidArgumentException;
import dev.swarnim.project.errorhandler.TodoErrorCodes;
import dev.swarnim.project.model.request.Note;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class NoteService {

    private final NoteDao noteDao;
    private final CustomerDao customerDao;

    public NoteService(NoteDao noteDao,
                       CustomerDao customerDao) {
        this.noteDao = noteDao;
        this.customerDao = customerDao;
    }

    public void createNote(Long customerId,
                           Note note){
        if(!customerDao.checkIfCustomerExists(customerId)){
            throw new InvalidArgumentException(TodoErrorCodes.TODO0005);
        }

        if(note.getNote().trim().length() > 0){
            noteDao.createNote(note, customerId);
        }
    }

    public List<Note> getNotes(Long customerId){
        if(!customerDao.checkIfCustomerExists(customerId)){
            throw new InvalidArgumentException(TodoErrorCodes.TODO0005);
        }

        return noteDao.getNotes(customerId);
    }
}
