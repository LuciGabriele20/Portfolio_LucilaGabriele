import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginUsuario } from 'src/app/model/login-usuario';
import { AuthService } from 'src/app/service/auth.service';
import { TokenService } from 'src/app/service/token.service';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit {
    isLogged = false;
    isLogginFail = false;
    loginUsuario!: {
        nombreUsuario: string;
        password: string;
    };
    nombreUsuario!: string;
    password!: string;
    roles: string[] = [];
    errMsj!: string;

    constructor(private tokenService: TokenService, private authService: AuthService, private routter: Router) { }

    ngOnInit(): void {
        if (this.tokenService.getToken()) {
            this.isLogged = true;
            this.isLogginFail = false;
            this.roles = this.tokenService.getAuthorities();
        }
    }

    onLogin(): void {
        this.loginUsuario = new LoginUsuario(this.nombreUsuario, this.password);
        this.authService.login(this.loginUsuario).subscribe((data: any) => {
            this.isLogged = true;
            this.isLogginFail = false;
            this.tokenService.setToken(data.token);
            this.tokenService.setUserName(data.nombreUusario);
            this.tokenService.setAuthorities(data.authorities);
            this.roles = data.authorities;
            this.routter.navigate([''])
        }, err => {
            this.isLogged = false;
            this.isLogginFail = true;
            this.errMsj = err.error.mensaje;
            console.log(this.errMsj);

        })
    }
}