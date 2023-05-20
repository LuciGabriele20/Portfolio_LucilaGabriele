import { Component } from '@angular/core';
import {Router} from '@angular/router';
import { TokenService } from 'src/app/service/token.service';

@Component({
  selector: 'app-logoarg',
  templateUrl: './logoarg.component.html',
  styleUrls: ['./logoarg.component.css']
})
export class LogoargComponent implements OnInit {
  isLogged=false;

    constructor(private router:Router) {}
    ngOnInit(): void {
      if(this.tokenService.getToken()){
        this.isLogged=true;
      }else{
        this.isLogged=false;
      }
    }
      onLogOut():void{
        this.tokenService.logOut();
        window.location.reload();
      }

       login(){
        this.router.navigate(['/login'])
       }
      }
}
