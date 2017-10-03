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
        return new ResponseEntity<>(timeEntryRepository.find(id), status);
    }
}
