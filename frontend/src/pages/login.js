import { useState } from "react";
import React from "react";
import { useNavigate } from 'react-router-dom';
import axios from "axios";
import { setAuthToken } from "../routes.js/setauth";

export default function Loginpage(){
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState("");
    const history = useNavigate();

    const whereJump=()=>{
        if(localStorage.getItem('role')==="ADMIN"){
            history('/admin');
        }else{
            history('/staff');
        }
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post('http://localhost:8080/auth/login', { username, password });
            localStorage.setItem('token', response.data.accessToken);
            setAuthToken(response.data.accessToken);
            console.log(axios.defaults.headers.common["Authorization"])
            localStorage.setItem('role',response.data.role)
            whereJump();
        } catch (error) {
            setError(error.response.data.accessToken);
        }
    };

    return(
        <div style={{backgroundColor:'floralwhite'}}>
            <div className="container" style={{height: '90vh',alignItems: 'center',justifyContent: 'center'}}>
                <div className="row my-0 py-5">
                    <div className="col-md-4 offset-md-4">
                        <div className="login">
                            <h1 className="text-center">Prijava</h1>
                            <h6 className="text-center">Ova prijava je samo za zaposlene u agenciji.</h6>
                            <form className="needs-validation was-validated" onSubmit={handleSubmit}>
                                <div className="form-group">
                                    <label className="form-label" htmlFor="username">Email adresa</label>
                                    <input className="form-control" 
                                        type="text" 
                                        id="username"    
                                        placeholder="Unesite korisnicko ime!" 
                                        name="username"
                                        value={username}
                                        onChange={(e)=>setUsername(e.target.value)}
                                        required></input>
                                    <div className="invalid-feedback">
                                        Unesite Vaše korisničko ime
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
                            <div className="row my-2">
                                <div className="col-md-12">
                                    <p className="text-center text-break">{error && <span className="error" style={{color:"red"}}>Pogrešno korisničko ime ili lozinka</span>}</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}