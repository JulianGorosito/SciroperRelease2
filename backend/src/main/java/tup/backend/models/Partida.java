package tup.backend.models;

public class Partida {

    // --- Valor que se suma al ganador de la partida --- //
    int cont_ganar = 1;

    // --- Almacena los datos en tiempo real de la partida --- //
    datos DatosPartida = new datos(10, 0, "a", "a", 0); 
    
    // --- Contiene valores default (reset) --- //
    datos DatosDefault = new datos(10, 0, "a", "a", 0);


    ia IA = new ia();

    // --- getPartida: Devuelve los datos en tiempo real de la partida  --- //
    public datos getPartida(){
        return DatosPartida;
    }

    // --- Game: Ejecuta las acciones y deciciones de la partida --- //
    public datos Game (String player_choice){

        /* --- ia_choice: es la elección obtenida por el método ---
           --------------- getChoice de la clase ia --------------- */
        String ia_choice = IA.getChoice();

        // --- combined_value: combina los valores de ambas elecciones. --- //
        String combined_value = player_choice + ia_choice;

         // --- Se define en base al contenido de combined_value --- //
        switch(combined_value){

                // --- Gana el Jugador --- //
                case "rs":
                    DatosPartida.setPlayer_cont(cont_ganar);
                    DatosPartida.setPlayer_choice(player_choice);
                    DatosPartida.setIa_choice(ia_choice);
                    break;
    
                // --- Gana la IA --- //
                case "rp":
                    DatosPartida.setPlayer_lifes(1);
                    DatosPartida.setPlayer_choice(player_choice);
                    DatosPartida.setIa_choice(ia_choice);
                    DatosPartida.setIa_cont(cont_ganar);
                    break;
    
                // --- Empate --- //
                case "rr":
                    DatosPartida.setPlayer_choice(player_choice);
                    DatosPartida.setIa_choice(ia_choice);
                    break;
    
    /*-------------------------------------------------------------------------
      -------------------------------------------------------------------------
      ------------------------------------------------------------------------- */
    
                // --- Gana el Jugador --- //
                case "pr":
                    DatosPartida.setPlayer_cont(cont_ganar);
                    DatosPartida.setPlayer_choice(player_choice);
                    DatosPartida.setIa_choice(ia_choice);
                    break;
    
                // --- Gana la IA --- //
                case "ps":
                    DatosPartida.setPlayer_lifes(1);
                    DatosPartida.setPlayer_choice(player_choice);
                    DatosPartida.setIa_choice(ia_choice);
                    DatosPartida.setIa_cont(cont_ganar);
                    break;
    
                // --- Empate --- //
                case "pp":
                    DatosPartida.setPlayer_choice(player_choice);
                    DatosPartida.setIa_choice(ia_choice);
                    break;
    
    /*-------------------------------------------------------------------------
      -------------------------------------------------------------------------
      ------------------------------------------------------------------------- */

                // --- Gana el Jugador --- //
                case "sp":
                    DatosPartida.setPlayer_cont(cont_ganar);
                    DatosPartida.setPlayer_choice(player_choice);
                    DatosPartida.setIa_choice(ia_choice);
                    break;
    
                // --- Gana la IA --- //
                case "sr":
                    DatosPartida.setPlayer_lifes(1);
                    DatosPartida.setPlayer_choice(player_choice);
                    DatosPartida.setIa_choice(ia_choice);
                    DatosPartida.setIa_cont(cont_ganar);
                    break;
    
                // --- Empate --- //
                case "ss":
                    DatosPartida.setPlayer_choice(player_choice);
                    DatosPartida.setIa_choice(ia_choice);
                    break;
    
                // --- Reestablece de 0 los valores de la Partida (Reset) --- //
                default:
                    DatosPartida.setPlayer_lifes(0);
                    DatosPartida.setPlayer_cont(0);
                    DatosPartida.setPlayer_choice("null");
                    DatosPartida.setIa_choice("null");
                    DatosPartida.setIa_cont(0);
                    DatosPartida = DatosDefault;
                    break;     
            }

            return DatosPartida;
        }
        
}

