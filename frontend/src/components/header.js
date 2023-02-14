import React from "react";
import { Link } from "react-router-dom";

export default function Navheader(){
    return(
        
            <header className="fixed-top sticky-top " style={{backgroundColor:'navy'}}>
                <nav className="navbar navbar-expand-lg fixed-top sticky-top " style={{backgroundColor:'navy'}}>
                    <div className="container">
                        <Link className="navbar-brand" to='/'>
                            <img  src={require('../slike/favicon.png')} alt="Logo" style={{width: '50px', height: '50px', borderRadius: '25px'}}></img>
                            <b> PenguinTours</b>   
                        </Link>
                        <ul className="nav" >
                            <li className="nav-item mx-1">
                                <Link className="btn" type="button" to="/" style={{color: 'antiquewhite' }}>POČETNA</Link>
                            </li>
                            <li className="nav-item mx-1">
                                <Link className="btn" type="button" to="/search" style={{color: 'antiquewhite'}}>PUTOVANJA</Link>
                            </li>
                            <li className="nav-item mx-1">
                                <Link className="btn" type="button" to="/aboutus" style={{color: 'antiquewhite'}}>O NAMA</Link>
                            </li>
                            <li className="nav-item mx-1">
                                <Link className="btn" type="button" to="/contacts" style={{color: 'antiquewhite'}}>KONTAKT</Link>
                            </li>
                            
                            <li className="nav-item mx-1">
                                <Link className="btn btn-outline-primary" type="button" to="/login" style={{color: 'antiquewhite'}}>PRIJAVI SE!</Link>
                            </li>
                        </ul>          
                    </div>
                </nav>
            </header>  
        
    );
}