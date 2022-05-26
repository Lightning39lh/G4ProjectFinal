export class Cuenta {
    id?:number;
    amount_tokens:number;
    tokenName:String;
    id_wallet:number;

    constructor(amountToken: number,tokenName:String,id_wallet:number){
    this.amount_tokens=amountToken;
    this.id_wallet=id_wallet;
    this.tokenName=tokenName;
    }
}