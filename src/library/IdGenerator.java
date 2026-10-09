
package library;
import java.util.concurrent.atomic.AtomicLong;

public class IdGenerator {
    private static final AtomicLong bookCounter = new AtomicLong(1);
    private static final AtomicLong memberCounter = new AtomicLong(1);
    public static long generateBookId() {
        return bookCounter.getAndIncrement();
    }
    public static long generatMemberId() {
        return memberCounter.getAndIncrement();
    }
}