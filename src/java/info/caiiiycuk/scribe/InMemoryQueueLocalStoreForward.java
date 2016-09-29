package info.caiiiycuk.scribe;

import com.facebook.scribe.LogEntry;
import java.util.LinkedList;
import java.util.Queue;

public class InMemoryQueueLocalStoreForward implements ILocalStoreForward {

    private static final int MAX_SIZE = 10000;

    private Queue<LogEntry> queue = new LinkedList<LogEntry>();

    @Override
    public synchronized void putLogEntry(LogEntry logEntry) {
        queue.add(logEntry);
        if (queue.size() > MAX_SIZE) {
            queue.poll();
        }
    }

    @Override
    public synchronized LogEntry getLogEntry() {
        return queue.poll();
    }
}
