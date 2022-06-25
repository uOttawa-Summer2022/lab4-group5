package com.example.lab3databases;

import java.util.Locale;

public class Validator {

    String validPN = "^[a-z0-9 ]+";
    String validPP = "^[0-9]+\\.[0-9]{2}";

    public Validator() {}

    public boolean validProductName(String pn) {
        if (pn.toLowerCase().matches(validPN)) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean validProductPrice(String pp) {
        if (pp.toLowerCase().matches(validPP)) {
            return true;
        }
        else {
            return false;
        }
    }
}
