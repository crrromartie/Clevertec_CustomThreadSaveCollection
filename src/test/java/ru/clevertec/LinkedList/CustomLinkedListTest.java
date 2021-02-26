package ru.clevertec.LinkedList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class CustomLinkedListTest {
    private CustomLinkedList<String> customList;

    @BeforeEach
    void initCustomLinkedList() {
        customList = new CustomLinkedList<>();
    }

    @AfterEach
    void destroyCustomLinkedList() {
        customList = null;
    }

    @Test
    void toArrayTest() {
        fillList();
        Object[] objects = customList.toArray();
        boolean actual = objects.getClass().isArray();
        assertTrue(actual);
    }

    @Test
    void sizePositiveTest() {
        fillList();
        int actual = customList.size();
        int expected = 5;
        assertEquals(expected, actual);
    }

    @Test
    void sizeNegativeTest() {
        fillList();
        int actual = customList.size();
        int unexpected = 2;
        assertNotEquals(unexpected, actual);
    }

    @Test
    void containsPositiveTest() {
        fillList();
        boolean actual = customList.contains("Java03");
        assertTrue(actual);
    }

    @Test
    void containsNegativeTest() {
        fillList();
        boolean actual = customList.contains("Java06");
        assertNotEquals(true, actual);
    }

    @Test
    void addFirstPositiveTest() {
        customList.addFirst("Java01");
        customList.addFirst("Java02");
        customList.addFirst("Java03");
        int actual = customList.size();
        int expected = 3;
        assertEquals(expected, actual);
        String actualObject = customList.get(0);
        String expectedObject = "Java03";
        assertEquals(expectedObject, actualObject);
    }

    @Test
    void addFirstNegativeTest() {
        customList.addFirst("Java01");
        customList.addFirst("Java02");
        customList.addFirst("Java03");
        int actual = customList.size();
        int unexpected = 2;
        assertNotEquals(unexpected, actual);
        String actualObject = customList.get(0);
        String unexpectedObject = "Java02";
        assertNotEquals(unexpectedObject, actualObject);
    }

    @Test
    void addLastPositiveTest() {
        customList.addLast("Java01");
        customList.addLast("Java02");
        customList.addLast("Java03");
        customList.addLast("Java04");
        customList.addLast("Java05");
        int actual = customList.size();
        int expected = 5;
        assertEquals(expected, actual);
        String actualObject = customList.get(0);
        String expectedObject = "Java01";
        assertEquals(expectedObject, actualObject);
    }

    @Test
    void addLastNegativeTest() {
        customList.addLast("Java01");
        customList.addLast("Java02");
        customList.addLast("Java03");
        customList.addLast("Java04");
        customList.addLast("Java05");
        int actual = customList.size();
        int unexpected = 2;
        assertNotEquals(unexpected, actual);
        String actualObject = customList.get(0);
        String unexpectedObject = "Java02";
        assertNotEquals(unexpectedObject, actualObject);
    }

    @Test
    void addPositiveTest() {
        fillList();
        int actual = customList.size();
        int expected = 5;
        assertEquals(expected, actual);
        String actualObject = customList.get(0);
        String expectedObject = "Java01";
        assertEquals(expectedObject, actualObject);
    }

    @Test
    void addNegativeTest() {
        fillList();
        int actual = customList.size();
        int unexpected = 2;
        assertNotEquals(unexpected, actual);
        String actualObject = customList.get(0);
        String unexpectedObject = "Java02";
        assertNotEquals(unexpectedObject, actualObject);
    }

    @Test
    void addByIndexPositiveTest() {
        fillList();
        customList.add(0, "Java00");
        int actual = customList.size();
        int expected = 6;
        assertEquals(expected, actual);
        String actualObject = customList.get(0);
        String expectedObject = "Java00";
        assertEquals(expectedObject, actualObject);
    }

    @Test
    void addByIndexTest() {
        fillList();
        customList.add(0, "Java00");
        int actual = customList.size();
        int unexpected = 7;
        assertNotEquals(unexpected, actual);
        String actualObject = customList.get(0);
        String unexpectedObject = "Java01";
        assertNotEquals(unexpectedObject, actualObject);
    }

    @Test
    void addByIndexThrowException() {
        fillList();
        assertThrows(IndexOutOfBoundsException.class,
                () -> customList.add(5, "Java00"));
    }

    @Test
    void deleteFirstPositiveTest() {
        fillList();
        boolean actual = customList.deleteFirst();
        assertTrue(actual);
        String actualObject = customList.get(0);
        String expectedObject = "Java02";
        assertEquals(expectedObject, actualObject);
        assertEquals(4, customList.size());
    }

    @Test
    void deleteFirstNegativeTest() {
        fillList();
        boolean actual = customList.deleteFirst();
        assertNotEquals(false, actual);
        String actualObject = customList.get(0);
        String unexpectedObject = "Java03";
        assertNotEquals(unexpectedObject, actualObject);
        assertNotEquals(5, customList.size());
    }

    @Test
    void deleteFirstThrowException() {
        assertThrows(NoSuchElementException.class,
                () -> customList.deleteFirst());
    }

    @Test
    void deleteLastPositiveTest() {
        fillList();
        boolean actual = customList.deleteLast();
        assertTrue(actual);
        String actualObject = customList.get(0);
        String expectedObject = "Java01";
        assertEquals(expectedObject, actualObject);
        assertEquals(4, customList.size());
    }

    @Test
    void deleteLastNegativeTest() {
        fillList();
        boolean actual = customList.deleteLast();
        Assertions.assertNotEquals(false, actual);
        String actualObject = customList.get(0);
        String unexpectedObject = "Java03";
        assertNotEquals(unexpectedObject, actualObject);
        assertNotEquals(5, customList.size());
    }

    @Test
    void deleteLastThrowException() {
        assertThrows(NoSuchElementException.class,
                () -> customList.deleteLast());
    }

    @Test
    void deletePositiveTest() {
        fillList();
        boolean actual = customList.delete(0);
        assertTrue(actual);
        String actualObject = customList.get(0);
        String expectedObject = "Java02";
        assertEquals(expectedObject, actualObject);
        assertEquals(4, customList.size());
    }

    @Test
    void deleteNegativeTest() {
        fillList();
        boolean actual = customList.delete(0);
        assertNotEquals(false, actual);
        String actualObject = customList.get(0);
        String unexpectedObject = "Java01";
        assertNotEquals(unexpectedObject, actualObject);
        assertNotEquals(5, customList.size());
    }

    @Test
    void deleteThrowException() {
        fillList();
        assertThrows(IndexOutOfBoundsException.class,
                () -> customList.delete(5));
        assertThrows(IndexOutOfBoundsException.class,
                () -> customList.delete(-1));
    }

    @Test
    void getFirstPositiveTest() {
        fillList();
        String actual = customList.getFirst();
        String expected = "Java01";
        assertEquals(expected, actual);
    }

    @Test
    void getFirstNegativeTest() {
        fillList();
        String actual = customList.getFirst();
        String unexpected = "Java02";
        assertNotEquals(unexpected, actual);
    }

    @Test
    void getFirstThrowException() {
        assertThrows(NoSuchElementException.class,
                () -> customList.getFirst());
    }

    @Test
    void getLastPositiveTest() {
        fillList();
        String actual = customList.getLast();
        String expected = "Java05";
        assertEquals(expected, actual);
    }

    @Test
    void getLastNegativeTest() {
        fillList();
        String actual = customList.getLast();
        String unexpected = "Java02";
        assertNotEquals(unexpected, actual);
    }

    @Test
    void getLastThrowException() {
        assertThrows(NoSuchElementException.class,
                () -> customList.getLast());
    }

    @Test
    void getPositiveTest() {
        fillList();
        String actual = customList.get(2);
        String expected = "Java03";
        assertEquals(expected, actual);
    }

    @Test
    void getNegativeTest() {
        fillList();
        String actual = customList.get(2);
        String unexpected = "Java02";
        assertNotEquals(unexpected, actual);
    }

    @Test
    void getThrowException() {
        fillList();
        assertThrows(IndexOutOfBoundsException.class,
                () -> customList.get(5));
        assertThrows(IndexOutOfBoundsException.class,
                () -> customList.get(-1));
    }

    @Test
    void toStringPositiveTest() {
        fillList();
        String actual = customList.toString();
        String expected = "CustomLinkedList[values=[Java01, Java02, Java03, Java04, Java05, ]]";
        assertEquals(expected, actual);
    }

    @Test
    void toStringNegativeTest() {
        fillList();
        String actual = customList.toString();
        String unexpected = "[Java01, Java02, Java03, Java04, Java05, ]";
        assertNotEquals(unexpected, actual);
    }

    private void fillList() {
        customList.add("Java01");
        customList.add("Java02");
        customList.add("Java03");
        customList.add("Java04");
        customList.add("Java05");
    }
}
