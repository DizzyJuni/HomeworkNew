package Lesson_9.task3;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Objects;

public class DynamicArray<T> {

    private T[] array;
    private int counter;

    public DynamicArray() {
        array = (T[]) new Object[0];
    }

    public int size() {
        return counter;
    }

    public boolean isEmpty() {
        return counter == 0;
    }

    public boolean contains(Object o) {
        for (T t : array) {
            if (o == t) {
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
            Object[] temp = new Object[array.length + 1];
            System.arraycopy(array, 0, temp, 0, array.length);
            array = (T[]) temp;
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
        Object[] temp = new Object[array.length -1];
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
        return "DynamicArray{" +
                "array=" + Arrays.toString(array) +
                '}';
    }
}
