package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/time-entries")
public class TimeEntryController {

    private final CounterService counter;
    private final GaugeService gauge;
    private TimeEntryRepository timeEntriesRepo;

    public TimeEntryController(TimeEntryRepository timeEntriesRepo,
                               CounterService counter,
                               GaugeService gauge) {
        this.timeEntriesRepo = timeEntriesRepo;
        this.counter = counter;
        this.gauge = gauge;
    }

    @PostMapping()
    public ResponseEntity create(@RequestBody TimeEntry timeEntry) {
        TimeEntry createdTimeEntry = timeEntriesRepo.create(timeEntry);
        counter.increment("TimeEntry.created");
        gauge.submit("timeEntries.count", timeEntriesRepo.list().size());

        return new ResponseEntity<>(createdTimeEntry, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity read(@PathVariable Long id) {
        HttpStatus status = HttpStatus.OK;
        TimeEntry timeEntry = timeEntriesRepo.find(id);
        if(timeEntry == null) {
            status = HttpStatus.NOT_FOUND;
            counter.increment("TimeEntry.read.notFound");
        } else {
            counter.increment("TimeEntry.read");
        }
        return new ResponseEntity<>(timeEntry, status);
    }

    @GetMapping()
    public ResponseEntity list() {
        counter.increment("TimeEntry.listed");
        return new ResponseEntity<>(timeEntriesRepo.list(), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody TimeEntry timeEntry) {
        HttpStatus status = HttpStatus.OK;
        TimeEntry timeEntryReturn = timeEntriesRepo.update(id, timeEntry);
        if(timeEntryReturn == null) {
            status = HttpStatus.NOT_FOUND;
            counter.increment("TimeEntry.updated.notFound");
        } else {
            counter.increment("TimeEntry.updated");
        }

        return new ResponseEntity<>(timeEntryReturn, status);
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        timeEntriesRepo.delete(id);
        counter.increment("TimeEntry.deleted");
        gauge.submit("timeEntries.count", timeEntriesRepo.list().size());
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

}
