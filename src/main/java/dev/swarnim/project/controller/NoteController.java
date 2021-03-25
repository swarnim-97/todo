package dev.swarnim.project.controller;

import dev.swarnim.project.model.request.Note;
import dev.swarnim.project.model.response.CommonListResponse;
import dev.swarnim.project.service.NoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/note/")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping("{id}/create")
    private void createNote(@PathVariable(value = "id") Long customerId,
                            @RequestBody Note note){

        noteService.createNote(customerId, note);

    }

    @GetMapping("{id}/getNotes")
    private CommonListResponse getNotes(@PathVariable(value = "id") Long customerId){
        return CommonListResponse.builder().data(noteService.getNotes(customerId)).build();
    }
}
