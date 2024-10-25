package p12.exercise;

import java.util.ArrayList;
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
            throw new IllegalArgumentException("Unimplemented method 'openNewQueue'");
        }
        queues.add(queue);
        map.put(queue, new LinkedList<>());
    }

    @Override
    public boolean isQueueEmpty(Q queue) {
        if (queues.contains(queue) == false) {
            throw new IllegalArgumentException("Unimplemented method 'isQueueEmpty'");
        }
        return map.get(queue).isEmpty();
    }

    @Override
    public void enqueue(T elem, Q queue) {
        if (queues.contains(queue) == false) {
            throw new IllegalArgumentException("Unimplemented method 'enqueue'");
        }
        map.get(queue).add(elem);
    }

    @Override
    public T dequeue(Q queue) {
        if (queues.contains(queue) == false) {
            throw new IllegalArgumentException("Unimplemented method 'dequeue'");
        }
        map.get(queue).remove();
        return map.get(queue).element();
    }

    @Override
    public Map<Q, T> dequeueOneFromAllQueues() {
        Map<Q, T> mapToReturn = new HashMap<>();
        for (Q singleQueue : queues) {
            dequeue(singleQueue);
            T dequeuedElement = map.get(singleQueue).element();
            mapToReturn.put(singleQueue, dequeuedElement);
        }
        return mapToReturn;
    }

    @Override
    public Set<T> allEnqueuedElements() {
        Set<T> setToReturn = new HashSet<>();
        for (Q singleQueue : queues) {
            for (T singleElement : map.get(singleQueue)) {
                setToReturn.add(singleElement);
            }
        }
        return setToReturn;
    }

    @Override
    public List<T> dequeueAllFromQueue(Q queue) {
        List<T> listToReturn = new ArrayList<>();
        if (queues.contains(queue) == false) {
            throw new IllegalArgumentException("Unimplemented method 'dequeueAllFromQueue'");
        }
        for (T element : map.get(queue)) {
            listToReturn.add(element);
        }
        map.get(queue).remove();
        return listToReturn;
    }

    @Override
    public void closeQueueAndReallocate(Q queue) {
        if (queues.contains(queue) == false) {
            throw new IllegalArgumentException("Unimplemented method 'closeQueueAndReallocate'");
        }
        List<T> elementsToAllocate = dequeueAllFromQueue(queue);
        Set<Q> available = availableQueues();
        if (available.isEmpty()) {
            throw new IllegalStateException("Unimplemented method 'closeQueueAndReallocate'");
        }
        Q availableQueue = available.iterator().next();
        map.get(availableQueue).addAll(elementsToAllocate);
    }
}
