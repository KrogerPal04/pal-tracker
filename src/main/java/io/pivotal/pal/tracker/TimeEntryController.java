package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/time-entries")
public class TimeEntryController {

    private TimeEntryRepository timeEntriesRepo;

    public TimeEntryController(TimeEntryRepository timeEntriesRepo) {
        this.timeEntriesRepo = timeEntriesRepo;
    }

    @PostMapping()
    public ResponseEntity create(@RequestBody TimeEntry timeEntry) {
        return new ResponseEntity<>(timeEntriesRepo.create(timeEntry), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity read(@PathVariable Long id) {
        HttpStatus status = HttpStatus.OK;
        TimeEntry timeEntry = timeEntriesRepo.find(id);
        if(timeEntry == null) {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(timeEntry, status);
    }

    @GetMapping()
    public ResponseEntity list() {
        return new ResponseEntity<>(timeEntriesRepo.list(), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody TimeEntry timeEntry) {
        HttpStatus status = HttpStatus.OK;
        TimeEntry timeEntryReturn = timeEntriesRepo.update(id, timeEntry);
        if(timeEntryReturn == null) {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(timeEntryReturn, status);
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        timeEntriesRepo.delete(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

}
