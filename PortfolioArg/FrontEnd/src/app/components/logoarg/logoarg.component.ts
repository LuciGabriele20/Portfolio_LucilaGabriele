import { Component } from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-logoarg',
  templateUrl: './logoarg.component.html',
  styleUrls: ['./logoarg.component.css']
})
export class LogoargComponent implements OnInit {
  
       constructor(private router:Router) {}

       ngOnInit(): void {
       }

       login(){
        this.router.navigate(['/login'])
       }
      }
}
