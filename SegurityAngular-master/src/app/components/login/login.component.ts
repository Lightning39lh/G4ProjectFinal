import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/services/authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  form:FormGroup;
  constructor(private formBuilder:FormBuilder, private authenticationService:AuthenticationService, private ruta:Router){
    this.form=this.formBuilder.group(
      {
        username:["",[Validators.required]],
        password:["",[Validators.required, Validators.minLength(3)]]
      }
    ) 

  }
  ngOnInit(): void {
  }
  //para acceder a los form
  get Username()
  {
    return this.form.get('username');
  }
  get Password(){
    return this.form.get('password');
  }

  onSend(event:Event)
  {
    //cuando clickeo salgo de lo esperado en el form
    event.preventDefault;
    console.log(this.form.value);
    this.authenticationService.Login(this.form.value).subscribe(data =>{
      console.log("DATA:" + JSON.stringify(data));
      this.ruta.navigate(['/my-wallet']);
    })
  }
}
