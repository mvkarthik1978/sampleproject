package com.example.sum.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class SumService {

    private static final Logger LOGGER = LogManager.getLogger(SumService.class);

    /** Adds two ints; the result is a long so it can never overflow. */
    public long add(int a, int b) {
        long result = (long) a + b;
        LOGGER.debug("Computed {} + {} = {}", a, b, result);
        return result;
    }
}
