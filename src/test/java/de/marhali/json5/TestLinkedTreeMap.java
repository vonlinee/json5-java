package de.marhali.json5;

import de.marhali.json5.internal.LinkedTreeMap;

public class TestLinkedTreeMap {

    public static void main(String[] args) {

        LinkedTreeMap<String, Object> map = new LinkedTreeMap<>();

        map.put("name", 1);

        System.out.println(map.get("name"));
    }
}
