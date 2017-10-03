package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/time-entries")
public class TimeEntryController {

    private TimeEntryRepository timeEntryRepository;

    public TimeEntryController() {
    }

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping()
    public ResponseEntity create(@RequestBody TimeEntry timeEntry) {
        return new ResponseEntity<>(timeEntryRepository.create(timeEntry), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity read(@PathVariable Long id) {
        HttpStatus status = HttpStatus.OK;
        TimeEntry timeEntry = timeEntryRepository.find(id);
        if(timeEntry == null) {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(timeEntry, status);
    }

    @GetMapping()
    public ResponseEntity list() {
        return new ResponseEntity<>(timeEntryRepository.list(), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody TimeEntry timeEntry) {
        HttpStatus status = HttpStatus.OK;
        TimeEntry timeEntryReturn = timeEntryRepository.update(id, timeEntry);
        if(timeEntryReturn == null) {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(timeEntryReturn, status);
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        timeEntryRepository.delete(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

}
