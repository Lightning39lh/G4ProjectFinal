import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AddTokensService } from 'src/app/services/add-tokens.service';

@Component({
  selector: 'app-add-tokens',
  templateUrl: './add-tokens.component.html',
  styleUrls: ['./add-tokens.component.css']
})
export class AddTokensComponent implements OnInit {

  constructor(private aDS:AddTokensService,private ruta:Router) { }
  token:string="ETH";
  ngOnInit(): void {
  }
  addToken(token: string){
    this.aDS.addToken(token).subscribe(data => {
      console.log("DATA:" + JSON.stringify(data));
      this.ruta.navigate(['/my-wallet'])}) 
  }

  setToken(token: string){
    this.token=token;
  }
}
