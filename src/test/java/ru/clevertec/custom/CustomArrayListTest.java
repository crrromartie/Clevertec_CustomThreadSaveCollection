package ru.clevertec.custom;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomArrayListTest {
    private CustomArrayList<String> customList;

    @BeforeEach
    void initCustomArrayList() {
        customList = new CustomArrayList<>();
    }

    @AfterEach
    void destroyCustomArrayList() {
        customList = null;
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
    void isEmptyPositiveTest() {
        int actual = customList.size();
        int expected = 0;
        assertEquals(expected, actual);
    }

    @Test
    void isEmptyNegativeTest() {
        int actual = customList.size();
        int unexpected = 1;
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
    void toArrayTest() {
        fillList();
        Object[] objects = customList.toArray();
        boolean actual = objects.getClass().isArray();
        assertTrue(actual);
    }

    @Test
    void deletePositiveTest() {
        fillList();
        customList.delete(0);
        String actualObject = customList.get(0);
        String expectedObject = "Java02";
        assertEquals(expectedObject, actualObject);

        assertEquals(4, customList.size());
    }

    @Test
    void deleteNegativeTest() {
        fillList();
        customList.delete(0);
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
    void updatePositiveTest() {
        fillList();
        customList.update(0, "C++");
        String actualObject = customList.get(0);
        String expectedObject = "C++";
        assertEquals(expectedObject, actualObject);

        assertEquals(5, customList.size());
    }

    @Test
    void updateNegativeTest() {
        fillList();
        customList.update(0, "C++");
        String actualObject = customList.get(0);
        String unexpectedObject = "C#";
        assertNotEquals(unexpectedObject, actualObject);
        assertNotEquals(4, customList.size());
    }

    @Test
    void toStringPositiveTest() {
        fillList();
        String actual = customList.toString();
        String expected = "CustomArrayList[values=[Java01, Java02, Java03, Java04, Java05]]";
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
