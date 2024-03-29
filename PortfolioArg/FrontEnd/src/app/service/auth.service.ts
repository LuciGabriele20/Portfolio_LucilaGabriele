import { HttpClient } from "@angular/common/http"
import { Injectable } from "@angular/core"
import { Observable } from "rxjs"
import { JWTDto } from "../model/JWT-dto"
import { LoginUsuario } from "../model/login-usuario"
import { NuevoUsuario } from "../model/nuevo-usuario"

@Injectable({
    providedIn:'root'
})
export class AuthService{
    authURL = 'http://localhost:8080/auth/';

    constructor(private httpClient: HttpClient){}

    public nuevo(nuevoUsuario: NuevoUsuario):Observable<any>{
        return this.httpClient.post<any>(this.authURL + 'nuevo', nuevoUsuario)
    }

    public login(loginUsuario: LoginUsuario): Observable<JWTDto>{
        return this.httpClient.post<JWTDto>(this.authURL + 'login',loginUsuario)
    }
    
}