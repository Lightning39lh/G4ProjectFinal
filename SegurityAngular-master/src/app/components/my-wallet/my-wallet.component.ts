import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Cuenta } from 'src/app/model/Cuenta';
import { Wallet } from 'src/app/model/Wallet';
import { WalletService } from 'src/app/services/wallet.service';

@Component({
  selector: 'app-my-wallet',
  templateUrl: './my-wallet.component.html',
  styleUrls: ['./my-wallet.component.css']
})
export class MyWalletComponent implements OnInit {
  
  wallet: Wallet= new Wallet(0,[]);
  constructor(public wS:WalletService,private ruta:Router) { }

  async ngOnInit(): Promise<void> {
    
    (await this.wS.getWallet()).subscribe(data => {
  
      console.log(JSON.stringify(data));
      this.wallet=data;
      console.log(this.wallet);
      console.log("LO DE ARRIBA ES LA WALLET")
    })
  }
  
  toAddTokens()
  {
      this.ruta.navigate(['addToken']);
  }
}
