import React from "react";
import { Link } from "react-router-dom";
import axios from "axios";

export default function Cardlist(props){
    let startdate=new Date(props.startDate);
    let enddate=new Date(props.endDate);
    let days=Math.ceil((startdate-enddate)/ (1000 * 3600 * 24));
    
    return(
        <>
        <div className="col px-0 mx-2 my-2"  style={{width: '25rem'}}>
          <Link className="card h-100" to={`arrangamentview/${props.id}`} style={{ textDecoration: 'none',}}>
            <img src={require('../slike/pozadina1.jpg')} className="card-img-top" alt="Slika"></img>
            <div className="card-body" style={{backgroundColor:"floralwhite"}}>
              <h5 className="card-title text-center" style={{ color: 'black',borderRadius:"10px",border:"solid",borderColor:"navy" }}>{props.name}</h5>
              <p className="card-text" style={{ color: 'black' }}>{props.destinaction}</p>
              <p className="card-text" style={{ color: 'black' }}>{days+1} {days+1>1?"dana":"dan"}</p>
              <p className="card-text" style={{ color: 'black' }}>Cena: {props.price}€</p>
              <p className="card-text" style={{ color: 'black' }}>Prevoz: {props.transport}</p>
            </div>
          </Link>
        </div>
        </>
    );
}