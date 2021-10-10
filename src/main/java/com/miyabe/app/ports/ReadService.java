package com.miyabe.app.ports;

import java.util.List;

public interface ReadService {
    List<String> readInput() throws Exception;
    boolean checkInput();
}
