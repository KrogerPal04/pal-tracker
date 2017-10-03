package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/timeEntry")
public class TimeEntryController {

    private TimeEntryRepository timeEntryRepository;

    public TimeEntryController() {
    }

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @RequestMapping("/create")
    public ResponseEntity create(TimeEntry timeEntry) {
        return new ResponseEntity<>(timeEntryRepository.create(timeEntry), HttpStatus.CREATED);
    }

    @RequestMapping("/read")
    public ResponseEntity read(Long id) {
        HttpStatus status = HttpStatus.OK;
        TimeEntry timeEntry = timeEntryRepository.find(id);
        if(timeEntry == null) {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(timeEntry, status);
    }

    @RequestMapping("/list")
    public ResponseEntity list() {
        return new ResponseEntity<>(timeEntryRepository.list(), HttpStatus.OK);
    }

    @RequestMapping("/update")
    public ResponseEntity update(Long id, TimeEntry timeEntry) {
        HttpStatus status = HttpStatus.OK;
        TimeEntry timeEntryReturn = timeEntryRepository.update(id, timeEntry);
        if(timeEntryReturn == null) {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(timeEntryReturn, status);
    }

    @RequestMapping("/delete")
    public ResponseEntity delete(Long id) {
        timeEntryRepository.delete(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

}
