package tup.backend.models;

public class ia {

    // --- Retorna una elección randomizada (r - p - s) --- //
    public String getChoice(){

        // --- Se crea un arrray de tipo String contenedor de las elecciones --- //
        String[]Choices = {"r", "p", "s"};

        // --- random: se le asigna un número entre 1, 2 y 3 --- //
        int random = (int) Math.floor(Math.random()*3);

        // --- ia_choice: Se asigna la "elección" resultante del random --- //
        String ia_choice = Choices[random];

        // --- Retorna la "elección de la ia" --- //
        return ia_choice;
    }

}