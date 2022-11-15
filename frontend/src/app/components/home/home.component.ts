import { Component, OnInit } from '@angular/core';
import { user_datos } from 'src/app/interface/requests';
import { GameService } from './../../services/game.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  DatosImg : any;
  UserImg : any;
  OptionsGame : any;
  PlayerDatos : any;
  OptionLogin : any;
  OpcPerfil : any;

  player_user : user_datos = {
    player_name: "",
    player_password: "",
    player_points: 0,
    player_logged_on: false
  }

  constructor(private GameService:GameService) { }

  ngOnInit(): void {
    this.Getvalidate();
  }

  //Salir(): el usuario cierra sesión
  Salir(){
    this.GameService.Salir(('http://localhost:8080/salir'), this.player_user)
    .subscribe(() => {
      console.log(this.player_user);

    })
    //Pide los datos para actualizarlo al momento
    this.Getvalidate();
  }

  //GetValidate: se pide al sistema una validacion (Logged on = true) si el usuario esta conectado
  // o (Logged on == false) si el usuario esta desconectado.
  Getvalidate() {
    this.GameService.GetValidate('http://localhost:8080/logged')
    .subscribe((resp : any) => {
      this.player_user = resp;
      console.log(this.player_user);
      //en el caso de no estar logeado llamara al metodo NotLogin()
      if (this.player_user.player_logged_on == false) {
        console.log("no conectado");
        this.NotLogin();
      }

      //en el caso de que el usuario este conectado ejecutara LoginOn()
      if(this.player_user.player_logged_on == true){
        console.log("conectado");
        this.LoginOn()
      }
    })

  }

  //NotLogin(): Oculta todo lo relacionado al Juego (Botones y datos) para mostrar dos botones (Login y Register)
  NotLogin(){
    this.OptionsGame = document.getElementById('OptionsGame');
    this.OptionsGame.style.display = "none";

    this.OptionLogin = document.getElementById('OptionsLogin');
    this.OptionLogin.style.display = "block";

    this.PlayerDatos = document.getElementById('User_Datos');
    this.PlayerDatos.style.display = "none"

    this.OpcPerfil = document.getElementById('Opc-Perfil');
    this.OpcPerfil.style.display = "none"
  }

  //LoginOn(): Oculta los botones(Login y Register) para mostrar los relacionados al Juego(Jugar, Ranking y Exit)
  LoginOn(){
    this.OptionsGame = document.getElementById('OptionsGame');
    this.OptionsGame.style.display = "block";

    this.OptionLogin = document.getElementById('OptionsLogin');
    this.OptionLogin.style.display = "none";

    this.PlayerDatos = document.getElementById('User_Datos');
    this.PlayerDatos.style.display = "block"
  }

  //MostrarDatos(): Oculta o muestra los datos del usuario (Puntos, Nombre)
  MostrarDatos(){
    this.OpcPerfil = document.getElementById('Opc-Perfil');
    //En el caso de que se esten mostrando se ocultan
    if (this.OpcPerfil.className == "Mostrando") {
      this.OpcPerfil.className = "Oculto";
      this.PlayerDatos = document.getElementById('User_Datos');
      this.PlayerDatos.style.display = "none"
      this.OpcPerfil.style.backgroundImage="url('../../../assets/img/Home/UI/btn_user.png')";
    }
    //En el caso de que este todo oculto, procede a mostrarlo
    else {
      this.OpcPerfil.className = "Mostrando";
      this.PlayerDatos = document.getElementById('User_Datos');
      this.PlayerDatos.style.display = "block"
      this.OpcPerfil.style.backgroundImage="url('../../../assets/img/Home/UI/btn_user_press.png')";
    }
  }

}


