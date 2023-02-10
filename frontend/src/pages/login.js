import { useState } from "react";
import React from "react";

export default function Loginpage(){
    const [email,setEmail]=useState('');
    const [password,setPassword]=useState('');

    return(
        <div style={{backgroundColor:'floralwhite'}}>
        <div className="container" style={{height: '90vh',alignItems: 'center',justifyContent: 'center'}}>
            <div className="row my-0 py-5">
                <div className="col-md-4 offset-md-4">
                    <div className="login">
                
                        <h1 className="text-center">Prijava</h1>
                        <h6 className="text-center">Ova prijava je samo za zaposlene u agenciji.</h6>
                        
                        <form className="needs-validation was-validated">
                            <div className="form-group">
                                <label className="form-label" htmlFor="email">Email adresa</label>
                                <input className="form-control" 
                                    type="email" 
                                    id="email"    
                                    placeholder="Unesite email!" 
                                    name="email"
                                    value={email}
                                    onChange={(e)=>setEmail(e.target.value)}
                                    required></input>
                                <div className="invalid-feedback">
                                    Unesite Vašu email adresu
                                </div>
                            </div>
                            <div className="form-group was-validated">
                                <label className="form-label" htmlFor="password">Lozinka</label>
                                <input className="form-control" 
                                    type="password" 
                                    id="password" 
                                    placeholder="Unesite lozinku!"
                                    name="password"
                                    value={password}
                                    onChange={(e)=>setPassword(e.target.value)}
                                    required></input>
                                <div className="invalid-feedback">
                                    Unesite Vašu lozinku 
                                </div>
                            </div>
                            <div className="form-group form-check">
                                <input className="form-check-input" type="checkbox" id="check"></input>
                                <label className="form-check-label" htmlFor="check">Zapamti mi podatke</label>
                            </div>
                            <button className="btn btn-primary w-100" type="submit">PRIJAVI SE!</button>
                        </form>
                
                    </div>
                </div>
            </div>
        </div>
        </div>
    )
}