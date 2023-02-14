import React from "react";

export default function Aboutuspage(){
    return(
        <div className="container d-flex flex-column min-vh-100">
            <div className="row justify-content-center my-5" py-5>
                <div className="col-md-4">
                    <h1 className="text-center">O nama</h1>
                </div>
            </div>
            <div className="row text-break">
                <p>Mi smo grupa studenata sa Fakulteta inženjerskih nauka, Univerziteta u Kragujevcu, koja je za projektni zadatak imala da uradi sajt za turističku agenciju.<br></br>
                Naš tim se sastoji od studenata četvrte godine smera Računarske tehnike i softverskog inženjerstva.
                <br></br> Ovaj projekat predstavlja jedan finalni produkt našeg školovanja na fakultetu, 
                jer zahteva puno znanje iz različitih sfera i oblasti softverskog inženjerstva.
                <br></br>Maksimalno smo se potrudili(neki manje, neki više :) ), ali nadamo se da Vam se sviđa!
                <br></br><strong>Veliki pozdrav od Ivana, Danila, Filipa i Luke!</strong>
                </p>
            </div>
            <div className="row text-center text-break  justify-content-center my-3">
                <img src={require("../slike/slikanasa1.jpg")} alt="Naša slika" style={{maxHeight:"1000px",maxWidth:"1000px"}}></img>
                <b><label className="my-2" >Slika 1.</label></b>
            </div>
            <div className="row text-break text-center justify-content-center my-3">
                <img src={require("../slike/slikanasa2.jpg")} alt="Naša slika" style={{maxHeight:"1000px",maxWidth:"1000px"}}></img>
                <b><label className="my-2">Slika 2.</label></b>
            </div>
        </div>
    );
}