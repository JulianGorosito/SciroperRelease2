package tup.backend.controllers;

// ----- Imports ----- //
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import tup.backend.models.datos;
import tup.backend.models.Partida;

@RestController
public class SciroperController {

    // --- Datos: Base de la partida que se devuelve al frontend --- //
    datos Datos = new datos(10, 0, "a", "a", 0);

    // --- Partida: Llama al método Juego() --- //
    Partida Partida = new Partida();

    // --- GetGame: Obtiene y Settea los datos de la Partida --- //
    @GetMapping("/game")
    public datos getGame(){
        Datos = Partida.getPartida(); 
        return Datos;
    }

    // --- Partida: Da inicio a la partida --- //
    @PutMapping("/partida/{EleccionJugador}")
    public @ResponseBody void Partida (@PathVariable String EleccionJugador){
        Datos = Partida.Game(EleccionJugador); 
    }


}
