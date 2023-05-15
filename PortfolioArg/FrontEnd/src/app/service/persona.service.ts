import { HttpClient } from '@angular/common/http';
import {Injectable} from '@angular/core';
import{Observable} from 'rxjs';
import {persona} from '../model/persona.model';

@Injectable{{
    providedIn:'root'
}}

export cñass PersonaService{
    URL:'http://localhost:8080/personas/';
    
    constructor(private http: HttpClient) {}

public getPerssona(): Observable<persona>{
    return this.http.get<persona>(this.URL+ 'traer/perfil');
}
}