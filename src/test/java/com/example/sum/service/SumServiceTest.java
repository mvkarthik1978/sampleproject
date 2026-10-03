package com.example.sum.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class SumServiceTest {

    private final SumService service = new SumService();

    @Test
    void addsPositiveNumbers() {
        assertEquals(5L, service.add(2, 3));
    }

    @Test
    void addsNegativeNumbers() {
        assertEquals(-5L, service.add(-2, -3));
    }

    @Test
    void doesNotOverflow() {
        assertEquals(2L * Integer.MAX_VALUE, service.add(Integer.MAX_VALUE, Integer.MAX_VALUE));
        assertEquals(2L * Integer.MIN_VALUE, service.add(Integer.MIN_VALUE, Integer.MIN_VALUE));
    }
}
