package p12.exercise;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class MultiQueueImpl<T, Q> implements MultiQueue<T, Q> {
    private final Set<Q> queues = new HashSet<>();
    private final Map<Q, Queue<T>> map = new HashMap<>();

    @Override
    public Set<Q> availableQueues() {
        Set<Q> queuesToReturn = new HashSet<>(queues);
        return queuesToReturn;
    }

    @Override
    public void openNewQueue(Q queue) {
        if (queues.contains(queue) == true) {
            throw new UnsupportedOperationException("Unimplemented method 'openNewQueue'");
        }
        queues.add(queue);
        map.put(queue, new LinkedList<>());
    }

    @Override
    public boolean isQueueEmpty(Q queue) {
        if (queues.contains(queue) == false) {
            throw new UnsupportedOperationException("Unimplemented method 'isQueueEmpty'");
        }
        return map.get(queue).isEmpty();
    }

    @Override
    public void enqueue(T elem, Q queue) {
        if (queues.contains(queue) == false) {
            throw new UnsupportedOperationException("Unimplemented method 'enqueue'");
        }
        map.get(queue).add(elem);
    }

    @Override
    public T dequeue(Q queue) {
        if (queues.contains(queue) == true) {
            throw new UnsupportedOperationException("Unimplemented method 'dequeue'");
        }
        map.get(queue).remove();
        return map.get(queue).element();
    }

    @Override
    public Map<Q, T> dequeueOneFromAllQueues() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'dequeueOneFromAllQueues'");
    }

    @Override
    public Set<T> allEnqueuedElements() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'allEnqueuedElements'");
    }

    @Override
    public List<T> dequeueAllFromQueue(Q queue) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'dequeueAllFromQueue'");
    }

    @Override
    public void closeQueueAndReallocate(Q queue) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'closeQueueAndReallocate'");
    }

}
