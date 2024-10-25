package p12.exercise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
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
            throw new IllegalArgumentException("Queue exsist already'");
        }
        queues.add(queue);
        Queue<T> elements = new LinkedList<>();
        map.put(queue, elements);
    }

    @Override
    public boolean isQueueEmpty(Q queue) {
        if (queues.contains(queue) == false) {
            throw new IllegalArgumentException("Queue does not exist");
        }
        return map.get(queue).isEmpty();
    }

    @Override
    public void enqueue(T elem, Q queue) {
        if (queues.contains(queue) == false) {
            throw new IllegalArgumentException("Queue does not exist");
        }
        map.get(queue).add(elem);
    }

    @Override
    public T dequeue(Q queue) {
        if (queues.contains(queue) == false) {
            throw new IllegalArgumentException("Queue does not exist");
        }
        if (map.get(queue).isEmpty()) {
            return null;
        }
        return map.get(queue).poll();
    }

    @Override
    public Map<Q, T> dequeueOneFromAllQueues() {
        Map<Q, T> mapToReturn = new HashMap<>();
        for (Q singleQueue : queues) {
            T dequeuedElement = dequeue(singleQueue);
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
            throw new IllegalArgumentException("Queue does not exist");
        }
        Queue<T> elementsOfQueue = map.get(queue);
        Iterator<T> elementsLeftInQueue = elementsOfQueue.iterator();
        while (elementsLeftInQueue.hasNext() == true) {
            listToReturn.add(elementsLeftInQueue.next());
            elementsLeftInQueue.remove();
        }
        return listToReturn;
    }

    @Override
    public void closeQueueAndReallocate(Q queue) {
        if (queues.contains(queue) == false) {
            throw new IllegalArgumentException("Queue does not exist");
        }
        List<T> elementsToAllocate = dequeueAllFromQueue(queue);
        Set<Q> available = new HashSet<>(availableQueues());
        available.remove(queue);
        if (available.isEmpty() == true) {
            throw new IllegalStateException("Queue is empty");
        }
        Q availableQueue = available.iterator().next();
        map.get(availableQueue).addAll(elementsToAllocate);
        queues.remove(queue);
    }
}
