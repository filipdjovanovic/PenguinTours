import React from "react";
import { Link } from "react-router-dom";

export default function Navheaderad(){

    const clearStorage=()=>{
        localStorage.setItem("token",null);
        localStorage.setItem("role",null);
    }

    return(
        <>
        <div>
            <header style={{backgroundColor:'navy'}}>
                <nav className="navbar navbar-expand-lg fixed-top sticky-top " >
                    <div className="container">
                        <Link className="navbar-brand" to=''>
                            <img  src={require('../slike/favicon.png')} alt="Logo" style={{width: '50px', height: '50px', borderRadius: '25px'}}></img>
                            <b> PenguinTours</b>   
                        </Link>
                        <ul className="nav" >
                            <li className="nav-item mx-1">
                                <Link className="btn" type="button" to="" style={{color: 'antiquewhite' }}>POCETNA</Link>
                            </li>
                            <li className="nav-item mx-1">
                                <Link className="btn" type="button" to="search" style={{color: 'antiquewhite'}}>PUTOVANJA</Link>
                            </li>
                            <li className="nav-item mx-1">
                                <Link className="btn" type="button" to="aboutus" style={{color: 'antiquewhite'}}>O NAMA</Link>
                            </li>
                            <li className="nav-item mx-1">
                                <Link className="btn" type="button" to="contacts" style={{color: 'antiquewhite'}}>KONTAKT</Link>
                            </li>
                            
                            <li className="nav-item mx-1">
                                <Link className="btn btn-outline-primary" type="button" to="/" onClick={clearStorage} style={{color: 'antiquewhite'}}>ODJAVI SE!</Link>
                            </li>
                        </ul>          
                    </div>
                </nav>
            </header>  
        </div>
        </>
    );
}