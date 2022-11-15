import { Ranking } from './../../interface/requests';
import { Component, OnInit } from '@angular/core';
import { GameService } from './../../services/game.service';

@Component({
  selector: 'app-ranking',
  templateUrl: './ranking.component.html',
  styleUrls: ['./ranking.component.css']
})
export class RankingComponent implements OnInit {
  player : Ranking = {
    position: 1,
    user: "",
    points: 0
  }

  public Ranking: Ranking[] = [];

  constructor(private GameService:GameService)  { }

  ngOnInit(): void {
    this.GetRankingList();
  }

  //GetRankingList: Pide a al backend la lista de usuarios para luego ordenarla y limitarla a los primeros 5
  GetRankingList() {
    this.GameService.GetRanking('http://localhost:8080/PlayerList')
    .subscribe((respuesta : any) => {
      this.Ranking = respuesta;
      this.Ranking = this.Ranking.sort((a, b) => {
        return b.points - a.points
      })
      this.Ranking = this.Ranking.slice(0, 5);
    })
  }
}
