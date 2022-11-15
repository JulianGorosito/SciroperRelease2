import { GameService } from './../../services/game.service';
import { ASTWithSource, ThisReceiver } from '@angular/compiler';
import { Component, Input, OnInit } from '@angular/core';
import { datos, user_datos } from 'src/app/interface/requests';

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.css']
})
export class GameComponent implements OnInit {
  //VARIABLES (MODIFICAR)
  ventanaEmergente : any;
  ventanafinal: any;
  Player_ImgChoice : any;
  IA_ImgChoice : any;
  Game_ImgResult : any;
  Life_Img : any;
  SurrenderWarning : any;

  combined_value : String = "";
  IAName : string = "IA";

  // --- INICIALIZED ---

  //Player_User: contiene los datos del usuario
 player_user : user_datos = {
    player_name: " ",
    player_password: "",
    player_points: 0,
    player_logged_on: true
  }

  //datos: Contiene los datos que se pasan entre el backend y el frontend durante la partida
  datos : datos = {
    player_lifes: 10,
    player_cont : 0,
    player_choice : " ",
    ia_choice : " ",
    ia_cont : 0
  }

  constructor(private GameService:GameService) { }

  //Al momento de cargar la pagina pedira los datos de la partida y ademas verificara si el usuario esta conectado.
  ngOnInit(): void {
    this.Validate();
    this.GameGet();
  }

  // --- REQUESTS ---

  // GameGet: pide los valores actuales de la partida
  GameGet() {
    this.GameService.getGameDatos('http://localhost:8080/game')
    .subscribe((respuesta : any) => {
      this.datos = respuesta;
      console.log(this.datos);
      this.Game_SetImg(this.datos);
            //FINISH GAME: (Cuando la IA gane 10 veces)
            if(this.datos.player_lifes == 0){
              console.log("Fin de la Partida")
              this.GameFinish(this.datos);
              this.MostraFinalPartida();
              this.Player_addPoints();
            }
            else{
            }
    }, error => {
      console.log(error);
    })
  }

  // GameInit: inicia la partida a partir de la elección del jugador
  GameInit(EleccionJugador : String){
    console.log("La Partida da Comienzo...")
    this.GameService.putGameDatos(('http://localhost:8080/partida/'+EleccionJugador), this.datos)
    .subscribe(() => {
      console.log(this.datos);
      this.GameGet();
    }, error => {
      console.log(error);
    })
  }

  //GameReset: Resetea los valores de la partida a 0;
  GameReset(){
    console.log("Se a pedido resetear la partida:");
    this.GameService.ResetPartida(('http://localhost:8080/partida/reset'), this.datos)
    .subscribe(() => {
      console.log(this.datos);
    })

  }

  //GameFinish: finaliza la partida guardando los datos del jugador y reiniciando.
  GameFinish(DatosFinales: datos){
    this.player_user.player_points = DatosFinales.player_cont * 5;
    console.log("Puntos: "+this.player_user.player_points);
    this.GameReset();
  }

  //Player_addPoints: Asigna los puntos del jugador a su cuenta
  Player_addPoints(){
    console.log("Se a pedido resetear la partida:");
    this.GameService.addPoints(('http://localhost:8080/puntos/'+this.player_user.player_name+'/'+this.player_user.player_points), this.player_user)
    .subscribe(() => {
      console.log(this.datos);
    })
  }

  //Game_SetImg: Actualiza las elecciones de ambos Jugadores a medida que ocurre la partida.
  Game_SetImg(datos : datos){
    this.combined_value = datos.player_choice + datos.ia_choice;

    this.Life_Img = document.getElementById("Users-Life");
    this.Player_ImgChoice = document.getElementById("ImagenUsuario");
    this.IA_ImgChoice = document.getElementById("ImagenPC");
    this.Game_ImgResult = document.getElementById("ResultadoImg");

    switch (this.combined_value) {
//-------------------------------------------------------------------------------------------------------------
      case "rr":
        this.Player_ImgChoice.style.backgroundImage = "url('../../../assets/img/Game/UI/Choices/rock.png')";
        this.IA_ImgChoice.style.backgroundImage = "url('../../../assets/img/Game/UI/Choices/rock.png')";
        this.Game_ImgResult.style.backgroundImage="url('../../../assets/img/Game/UI/Results/draw.png')";
        break;

      case "rp":
        this.Player_ImgChoice.style.backgroundImage = "url('../../../assets/img/Game/UI/Choices/rock.png')";
        this.IA_ImgChoice.style.backgroundImage = "url('../../../assets/img/Game/UI/Choices/paper.png')";
        this.Game_ImgResult.style.backgroundImage="url('../../../assets/img/Game/UI/Results/lose.png')";
        break;

      case "rs":
        this.Player_ImgChoice.style.backgroundImage = "url('../../../assets/img/Game/UI/Choices/rock.png')";
        this.IA_ImgChoice.style.backgroundImage = "url('../../../assets/img/Game/UI/Choices/scissors.png')";
        this.Game_ImgResult.style.backgroundImage="url('../../../assets/img/Game/UI/Results/win.png')";
        break;
//-------------------------------------------------------------------------------------------------------------
      case "pr":
        this.Player_ImgChoice.style.backgroundImage = "url('../../../assets/img/Game/UI/Choices/paper.png')";
        this.IA_ImgChoice.style.backgroundImage = "url('../../../assets/img/Game/UI/Choices/rock.png')";
        this.Game_ImgResult.style.backgroundImage="url('../../../assets/img/Game/UI/Results/win.png')";
        break;

      case "pp":
        this.Player_ImgChoice.style.backgroundImage = "url('../../../assets/img/Game/UI/Choices/paper.png')";
        this.IA_ImgChoice.style.backgroundImage = "url('../../../assets/img/Game/UI/Choices/paper.png')";
        this.Game_ImgResult.style.backgroundImage="url('../../../assets/img/Game/UI/Results/draw.png')";
        break;

      case "ps":
        this.Player_ImgChoice.style.backgroundImage = "url('../../../assets/img/Game/UI/Choices/paper.png')";
        this.IA_ImgChoice.style.backgroundImage = "url('../../../assets/img/Game/UI/Choices/scissors.png')";
        this.Game_ImgResult.style.backgroundImage="url('../../../assets/img/Game/UI/Results/lose.png')";
        break;
//-------------------------------------------------------------------------------------------------------------
      case "sr":
        this.Player_ImgChoice.style.backgroundImage = "url('../../../assets/img/Game/UI/Choices/scissors.png')";
        this.IA_ImgChoice.style.backgroundImage = "url('../../../assets/img/Game/UI/Choices/rock.png')";
        this.Game_ImgResult.style.backgroundImage="url('../../../assets/img/Game/UI/Results/lose.png')";
        break;

      case "sp":
        this.Player_ImgChoice.style.backgroundImage = "url('../../../assets/img/Game/UI/Choices/scissors.png')";
        this.IA_ImgChoice.style.backgroundImage = "url('../../../assets/img/Game/UI/Choices/paper.png')";
        this.Game_ImgResult.style.backgroundImage="url('../../../assets/img/Game/UI/Results/win.png')";
        break;

      case "ss":
        this.Player_ImgChoice.style.backgroundImage = "url('../../../assets/img/Game/UI/Choices/scissors.png')";
        this.IA_ImgChoice.style.backgroundImage = "url('../../../assets/img/Game/UI/Choices/scissors.png')";
        this.Game_ImgResult.style.backgroundImage="url('../../../assets/img/Game/UI/Results/draw.png')";
        break;

        //Utilzido para el reset
      default:
        this.Player_ImgChoice.style.backgroundImage = "url('../../../assets/img/Game/UI/Choices/rock.png')";
        this.IA_ImgChoice.style.backgroundImage = "url('../../../assets/img/Game/UI/Choices/rock.png')";
        this.Game_ImgResult.style.backgroundImage="url(' ')";
        break;
    }

    switch (this.datos.player_lifes) {
      case 10:
        this.Life_Img.style.backgroundImage = "url('../../../assets/img/Game/UI/Backgrounds/Lifes_10.png')";
        break;

      case 9:
        this.Life_Img.style.backgroundImage = "url('../../../assets/img/Game/UI/Backgrounds/Lifes_9.png')";
        break;

      case 8:
        this.Life_Img.style.backgroundImage = "url('../../../assets/img/Game/UI/Backgrounds/Lifes_8.png')";
        break;

      case 7:
        this.Life_Img.style.backgroundImage = "url('../../../assets/img/Game/UI/Backgrounds/Lifes_7.png')";
        break;

      case 6:
        this.Life_Img.style.backgroundImage = "url('../../../assets/img/Game/UI/Backgrounds/Lifes_6.png')";
        break;

      case 5:
        this.Life_Img.style.backgroundImage = "url('../../../assets/img/Game/UI/Backgrounds/Lifes_5.png')";
        break;

      case 4:
        this.Life_Img.style.backgroundImage = "url('../../../assets/img/Game/UI/Backgrounds/Lifes_4.png')";
        break;

      case 3:
        this.Life_Img.style.backgroundImage = "url('../../../assets/img/Game/UI/Backgrounds/Lifes_3.png')";
        break;

      case 2:
        this.Life_Img.style.backgroundImage = "url('../../../assets/img/Game/UI/Backgrounds/Lifes_2.png')";
        break;

      case 1:
        this.Life_Img.style.backgroundImage = "url('../../../assets/img/Game/UI/Backgrounds/Lifes_1.png')";
        break;

      default:
        this.Life_Img.style.backgroundImage = "url('../../../assets/img/Game/UI/Backgrounds/Lifes_0.png')";
        break;
    }
  }

  //Validate: Valida que el usuario esta conectado y sino lo envia al login
  Validate() {
    this.GameService.GetValidate('http://localhost:8080/logged')
    .subscribe((resp : any) => {
      this.player_user = resp;
      console.log(this.player_user);

      if (this.player_user.player_logged_on == false) {
        console.log("no conectado");
        window.location.href = "/login"
      }

      else{
        console.log("conectado");
      }
    })
  }

  //Surrender: Reincia la partida y envia al usuario directo al home
  surrender(){
    this.GameReset();
    window.location.href = "/home"
  }

  //Mostrar Surrender: Muestra el cartel de confirmación
  MostrarSurrender(){
    //mostrar la base para ventanas emergentes
    this.ventanaEmergente = document.getElementById('VentanaEmergente');
    this.ventanaEmergente.style.display = "flex";

    //mostrar el warning surrender
    this.SurrenderWarning = document.getElementById('SurrenderWarning');
    this.SurrenderWarning.style.display = "flex";
  }

  //OcultarSurrender: en el momento de cancelar el surrender se ocultara.
  OcultarSurrender(){
    //ocultar la base para ventanas emergentes
    this.ventanaEmergente = document.getElementById('VentanaEmergente');
    this.ventanaEmergente.style.display = "none";

    //ocultar el warning surrender
    this.SurrenderWarning = document.getElementById('SurrenderWarning');
    this.SurrenderWarning.style.display = "none";
  }

  //MostrarFinalPartida: Muestra la ventana con los datos y elementos para dar como finalizada la partida
  MostraFinalPartida(){
    //mostrar la base para ventanas emergentes
    this.ventanaEmergente = document.getElementById('VentanaEmergente');
    this.ventanaEmergente.style.display = "flex";

    //mostrar ventana final con resultados
    this.ventanafinal = document.getElementById('VentanaFinal');
    this.ventanafinal.style.display = "flex";
  }
}




