package example;

import example.logic.Logic;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Logic logic = new Logic();
        logic.preparedDB();
        logic.mainLogic();
    }
}
