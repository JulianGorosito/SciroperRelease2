import { Component, OnInit } from '@angular/core';
import { GameService } from './../../services/game.service';
import { user_datos } from 'src/app/interface/requests';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  textLogin : any;
  checkRegister : any;
  ButtonRegister : any;
  check : String = "";

  constructor(private GameService:GameService) { }

  player_user : user_datos = {
    player_name: "",
    player_password: "",
    player_points: 0,
    player_logged_on: false
  }

  ngOnInit(): void {
    this.validate();
  }

  //Login: El usuario ingresa sus datos y el sistema verifica si existe y devuelve su información con un check.
  Login(User : string, Password : string){
    console.log("datos ingresados: "+User+" "+Password);
    this.GameService.Login(('http://localhost:8080/login/'+User+"/"+Password))
    .subscribe(respuesta => {
      console.log(respuesta);
            //Si el usuario no es encontrado:
      if(this.player_user.player_logged_on == false){
        console.log("Desconectado");
        this.textLogin = document.getElementById("textInput");
        this.textLogin.innerHTML = "Datos Incorrectos";
        this.textLogin.style.color = "red";

        this.ButtonRegister = document.getElementById("RegisterButton");
        this.ButtonRegister.style.display = "block";

      }
    })
    this.validate();
  }

  //Validate: se pide al sistema una validacion (Logged on = true) si el usuario esta conectado
  // o (Logged on == false) si el usuario esta desconectado.
  validate() {
    this.GameService.GetValidate('http://localhost:8080/logged')
    .subscribe((resp : any) => {
      this.player_user = resp;
      console.log(this.player_user);

      if (this.player_user.player_logged_on == true) {
        console.log("conectado");
        window.location.href = "/home"
      }
      else{
        console.log("???");
      }
    })
  }
}
