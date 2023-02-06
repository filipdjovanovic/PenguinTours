import React from "react";
import { Link } from "react-router-dom";

export default function Cardlist(props){
    return(
        <>
        <div class="col px-0 mx-2 my-2"  style={{width: '25rem'}}>
          <Link class="card h-100" to='/arrangamentview' style={{ textDecoration: 'none',}}>
            <img src={require('../slike/pozadina1.jpg')} class="card-img-top" alt="Slika"></img>
            <div class="card-body" style={{backgroundColor:"floralwhite"}}>
              <h5 class="card-title text-center" style={{ color: 'black',borderRadius:"10px",border:"solid",borderColor:"navy" }}>{props.name}</h5>
              <p class="card-text" style={{ color: 'black' }}>{props.destinaction}</p>
              <p class="card-text" style={{ color: 'black' }}>{props.days} dana</p>
              <p class="card-text" style={{ color: 'black' }}>Cena: {props.price}€</p>
              <p class="card-text" style={{ color: 'black' }}>Prevoz: {props.transport}</p>
            </div>
          </Link>
        </div>
        </>
    );
}