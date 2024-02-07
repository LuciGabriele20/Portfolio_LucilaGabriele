import { HTTP_INTERCEPTORS, HttpEvent,HttpHandler,HttpRequest } from "@angular/common/http"
import { Observable } from "rxjs"
import { TokenService } from "./token.service"

export class InterceptorService{
    constructor(private tokenServicie: TokenService){}
    
    Intercept(req:HttpRequest<any>,next: HttpHandler): Observable<HttpEvent<any>>{
        let intReq= req;
        const token= this.tokenServicie.getToken();

        if(token != null){
            intReq =req.clone({
                headers:req.headers.set('Authorization','Bearer'+token)
            })
        }
        return next.handle(intReq);
    }
}
export const interceptorProvider=[{
    provide:HTTP_INTERCEPTORS,
    useClass: InterceptorService,
    multi:true
}];