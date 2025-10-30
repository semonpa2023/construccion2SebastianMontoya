package app;

import app.infrastructure.gui.UserGUI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExerciseApplication {

    @Autowired
    private static UserGUI userGUI;

    public static void main(String[] args) {
        SpringApplication.run(ExerciseApplication.class, args);
    }
}
