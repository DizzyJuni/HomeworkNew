package Lesson_9.task3;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public class DynamicArray<T> implements Iterable<T> {

    private T[] array;
    private int counter;
    private static final int defaultCapacity = 10;
    private static final int grow = 2;

    public DynamicArray() {
        this.array = (T[]) new Object[defaultCapacity];
        this.counter = 0;
    }

    public int size() {
        return counter;
    }

    public boolean isEmpty() {
        return counter == 0;
    }

    public boolean contains(Object o) {
        for (T t : array) {
            if (o.equals(t)) {
                System.out.println("Содержит такой элемент: " + o);
                return true;
            }
        }
        System.out.println("Такого элемента нет: " + o);
        return false;
    }

    public Object[] toArray() {
        Object[] temp = new Object[array.length];
        System.arraycopy(array, 0, temp, 0, array.length);
        return temp;
    }

    public <T> T[] toArray(T[] a) {
        T[] copy = (a.getClass() == Object[].class)
                ? (T[]) new Object[array.length]
                : (T[]) Array.newInstance(a.getClass().getComponentType(), array.length);
        System.arraycopy(array, 0, copy, 0, array.length);
        return copy;
    }

    public boolean add(T t) {
        if (counter == array.length) {
            int newCapacity = array.length * grow;
            T[] temp = (T[]) new Object[newCapacity];
            System.arraycopy(array, 0, temp, 0, counter);
            array = temp;
        }
        array[counter++] = t;
        return true;
    }

    public boolean remove(Object o) {
        for (int i = 0; i < array.length; i++) {
            if (o.equals(array[i])) {
                Object[] temp = new Object[array.length - 1];
                System.arraycopy(array, 0, temp, 0, i);
                System.arraycopy(array, i + 1, temp, i, array.length - i - 1);
                counter--;
                array = (T[]) temp;
                return true;
            }
        }
        return false;
    }

    public void clear() {
        Object[] temp = new Object[0];
        array = (T[]) temp;
        counter = 0;
    }

    public T get(int index) {
        return array[index];
    }

    public T set(int index, T element) {
        return array[index] = element;
    }

    public void add(int index, T element) {
        counter++;
        Object[] temp = new Object[array.length + 1];
        System.arraycopy(array, 0, temp, 0, index);
        System.arraycopy(array, index, temp, index + 1, array.length - index);
        array = (T[]) temp;
        array[index] = element;
    }

    public T remove(int index) {
        Object[] temp = new Object[array.length - 1];
        System.arraycopy(array, 0, temp, 0, index);
        System.arraycopy(array, index + 1, temp, index, array.length - index - 1);
        array = (T[]) temp;
        counter--;
        return (T) array;
    }

    public int indexOf(Object o) {
        for (int i = 0; i < array.length; i++) {
            if (o.equals(array[i])) {
                return i;
            }
        }
        return -1;
    }

    public int lastIndexOf(Object o) {
        for (int i = array.length - 1; i >= 0; i--) {
            if (o.equals(array[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            int currentValue = 0;

            @Override
            public boolean hasNext() {
                return currentValue < counter;
            }

            @Override
            public T next() {
                return array[currentValue++];
            }
        };
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DynamicArray<?> that = (DynamicArray<?>) o;
        return counter == that.counter == Objects.deepEquals(array, that.array);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Arrays.hashCode(array), counter);
    }

    @Override
    public String toString() {
        String text = "DynamicArray{array=";
        StringBuilder string = new StringBuilder("[");
        for (int i = 0; i < counter; i++) {
            string.append(array[i]);
            if (i < counter - 1)
                string.append(" ,");
        }
        return text + string.append("]").append("}").toString();
    }
}
