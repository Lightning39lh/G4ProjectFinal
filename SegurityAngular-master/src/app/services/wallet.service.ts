import { HttpClient, HttpEvent, HttpHandler, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthenticationService } from './authentication.service';

@Injectable({
  providedIn: 'root'
})
export class WalletService {
  url: string = "http://localhost:8080/"

  id: number = 0;
  constructor(private http: HttpClient, private aS: AuthenticationService) { }


  async getWallet(): Promise<Observable<any>> {
    var currentUser = this.aS.AuthenticatedUser;

    this.getId(currentUser.username).subscribe(data => {
      this.id = data;
    })
    //para que me llegue a cargar el ID
    await new Promise(f => setTimeout(f, 50));
    return this.http.get<any>(this.url + "MyWallet/" + this.id);
  }

  getId(username: String): Observable<any> {
    return this.http.get<any>(this.url + "MyUsers/" + username);
  }
}
