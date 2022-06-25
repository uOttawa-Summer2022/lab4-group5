package com.example.lab3databases;

import static org.junit.Assert.*;

import org.junit.Test;

public class ValidatorTest {

    @Test
    public void validProductName_true() {
        Validator validator = new Validator();
        String productName = "Samsung S10 Plus";
        assertTrue(validator.validProductName(productName));
    }

    @Test
    public void validProductName_false() {
        Validator validator = new Validator();
        String productName = "Samsung S10+";
        assertFalse(validator.validProductName(productName));
    }

    @Test
    public void validProductPrice_true() {
        Validator validator = new Validator();
        String productPrice = "19.99";
        assertTrue(validator.validProductPrice(productPrice));
    }

    @Test
    public void validProductPrice_false() {
        Validator validator = new Validator();
        String productPrice = "abc100.99";
        assertFalse(validator.validProductPrice(productPrice));
    }
}