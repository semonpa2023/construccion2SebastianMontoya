package app.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @GetMapping("/profile")
    public String getProfile() {
        return "Acceso permitido al perfil del usuario autenticado.";
    }
}
