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

    @PostMapping("/create")
    public ResponseEntity create(TimeEntry timeEntry) {
        return new ResponseEntity<>(timeEntryRepository.create(timeEntry), HttpStatus.CREATED);
    }

    @GetMapping("/read")
    public ResponseEntity read(Long id) {
        HttpStatus status = HttpStatus.OK;
        TimeEntry timeEntry = timeEntryRepository.find(id);
        if(timeEntry == null) {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(timeEntry, status);
    }

    @GetMapping("/list")
    public ResponseEntity list() {
        return new ResponseEntity<>(timeEntryRepository.list(), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity update(Long id, TimeEntry timeEntry) {
        HttpStatus status = HttpStatus.OK;
        TimeEntry timeEntryReturn = timeEntryRepository.update(id, timeEntry);
        if(timeEntryReturn == null) {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(timeEntryReturn, status);
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(Long id) {
        timeEntryRepository.delete(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

}
