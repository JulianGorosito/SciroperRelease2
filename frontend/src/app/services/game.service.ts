import { datos, user_datos  } from 'src/app/interface/requests';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class GameService {

  constructor(private http: HttpClient) { }

  //game:
  public getGameDatos(url:string){
    return this.http.get(url);
  }

  public putGameDatos(url:string, obj: datos){
    return this.http.put(url, obj);
  }

  public ResetPartida(url:string, obj: datos){
    return this.http.put(url,obj);
  }

  public addPoints(url:string, obj: user_datos){
    return this.http.put(url,obj);
  }

  //login:
  public Login(url:string){
    return this.http.get(url);
  }

  public GetValidate(url:string){
    return this.http.get(url);
  }

  public Salir(url:string, obj: user_datos){
    return this.http.put(url,obj);
  }

  public Register(url:string){
    return this.http.get(url);
  }

  //Ranking:
  public GetRanking(url:string){
    return this.http.get(url);
  }
}
