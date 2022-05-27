import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TokenReducido } from '../model/TokenReducido';

@Injectable({
  providedIn: 'root'
})
export class AddTokensService {
  URL = 'http://localhost:8080/MyWallet/AddToken/';
  token1:TokenReducido = new TokenReducido("",0);
  constructor(private http: HttpClient) { }

  public addToken(token: string,id:number){
    this.token1.tokenName=token;
    this.token1.id_Wallet=id;
    return this.http.post<TokenReducido>(this.URL,this.token1)
  }
}
