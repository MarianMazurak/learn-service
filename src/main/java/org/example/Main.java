package org.example;

import org.example.models.Element;

public class Main {

    public static void main(String[] args) {
        int i[] = {1, 2, 3, 4, 5, 6};

        Element components = Element.FRAME;
        components.setLogo("Apex");

        System.out.println(components);
    }

}
