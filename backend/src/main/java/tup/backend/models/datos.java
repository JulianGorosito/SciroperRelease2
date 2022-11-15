package tup.backend.models;

public class datos {

    // --- Valores que pide el Frontend --- //
    public int player_lifes;
    public int player_cont;
    public String player_choice;
    public String ia_choice;
    public int ia_cont;

    // --- ❗❗ NECESARIO PARA QUE EL VALOR NO SE REEMPLACE ❗❗ --- //
    public int player_accumulated_value = 0; 
    public int ia_accumulated_value = 0;

    // ----- Constructor ----- //
    public datos(int plifes, int pcont, String pchoice, String iachoice, int iacont) {
        this.player_lifes = plifes;
        this.player_cont = pcont;
        this.player_choice = pchoice;
        this.ia_choice = iachoice;
        this.ia_cont = iacont;
    }

    // ----- Getters & Setters ----- //

    public int getPlayer_lifes() {
        return player_lifes;
    }

    public void setPlayer_lifes(int plifes) {
        if (plifes == 1) {
                this.player_lifes = player_lifes - plifes;
        }
         else{
            this.player_lifes = 10;
        }
    }

    public int getPlayer_cont() {
        return player_cont;
    }

    public void setPlayer_cont(int player_cont) {
        if(player_cont == 0){
            this.player_cont = player_cont;
            this.player_accumulated_value = 0;
        }
        else{
            player_accumulated_value = player_accumulated_value + player_cont ;
            this.player_cont = player_accumulated_value;
        }
    }

    public String getPlayer_choice() {
        return player_choice;
    }

    public void setPlayer_choice(String player_choice) {
        this.player_choice = player_choice;
    }

    public String getIa_choice() {
        return ia_choice;
    }

    public void setIa_choice(String ia_choice) {
        this.ia_choice = ia_choice;
    }

    public int getIa_cont() {
        return ia_cont;
    }

    public void setIa_cont(int ia_cont) {
        if(ia_cont == 0){
            this.ia_cont = ia_cont;
            this.ia_accumulated_value = 0;
        }
        else{
            ia_accumulated_value = ia_accumulated_value + ia_cont;
            this.ia_cont = ia_accumulated_value;
        }

    }

}