package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private long idCounter = 1;

    private Map<Long, TimeEntry> timeEntries = new HashMap<>();

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        if(timeEntry.getId() == 0) {
            while(this.find(idCounter) != null) {
                idCounter++;
            }
            timeEntry.setId(idCounter);
        }
        timeEntries.put(timeEntry.getId(), timeEntry);
        return timeEntry;
    }

    @Override
    public TimeEntry find(Long id) {
        return timeEntries.get(id);
    }

    @Override
    public List<TimeEntry> list() {
        return new ArrayList<>(timeEntries.values());
    }

    @Override
    public TimeEntry update(Long id, TimeEntry timeEntry) {

        TimeEntry currentEntry = find(id);
        if(currentEntry != null) {
            timeEntry.setId(id);
            timeEntries.put(id, timeEntry);
            currentEntry = timeEntry;
        }

        return currentEntry;
    }

    @Override
    public void delete(Long id) {
        timeEntries.remove(id);
    }

}
