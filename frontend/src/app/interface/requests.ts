//user: contiene todo lo necesario para un usuario
export interface user_datos {
  player_name : string,
  player_password : string,
  player_points : number,
  player_logged_on : boolean //simboliza el estado del usuario (sesión iniciada o no iniciada)
}

//datos: contiene todo lo necesario para realizar una partida
export interface datos {
  player_lifes : number;
  player_cont : number,
  player_choice : string,
  ia_choice : string,
  ia_cont : number
}

//frontend_base: contiene lo que se muestra en el frontend (img y puntos)
export interface frontend_base{
  player_name: string,
  player_cont : number,
  player_choice_img: string,
  ia_cont : number
  ia_choice_img: string,
}

//Ranking: Contiene todo lo que necesita un ranking
export interface Ranking{
  position : number,
  user : string,
  points : number
}
