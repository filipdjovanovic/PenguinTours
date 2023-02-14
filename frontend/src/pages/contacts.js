import React from "react";

export default function Contactspage(){
    return(
       <div className="container d-flex flex-column min-vh-100">
            <div className="row justify-content-center text-center my-4">
                <h1>Kontakti</h1>
            </div>
            <div className="row text-end my-3">
                <div className="col-md-4 px-5">
                    <h4>Adresa:</h4>
                </div>
                <div className="col-md-6 text-start">
                    <h4>Kapetana Lukića, Kragujevac, KG 34000, SRB</h4>
                </div>
            </div>
            <div className="row text-end my-3">
                <div className="col-md-4 px-5">
                    <h4>E-mail:</h4>
                </div>
                <div className="col-md-6 text-start">
                    <h4>danilostevanovic53@gmail.com</h4>
                </div>
            </div>
            <div className="row text-end my-3">
                <div className="col-md-4 px-5">
                    <h4>Kontakt telefon:</h4>
                </div>
                <div className="col-md-6 text-start">
                    <h4>+381692003110</h4>
                </div>
            </div>
            <div className="row text-end my-3">
                <div className="col-md-4 px-5">
                    <h4><i className="fa fa-facebook-f"></i></h4>
                </div>
                <div className="col-md-6 text-start">
                    <h4><a href="https://www.facebook.com/profile.php?id=100009188574575">Luka Maračić</a></h4>
                </div>
            </div>
            <div className="row text-end my-3">
                <div className="col-md-4 px-5">
                    <h4><i className="fa fa-twitter"></i></h4>
                </div>
                <div className="col-md-6 text-start">
                    <h4><a href="https://twitter.com/IvanRadivojev20">ivanradivojev20</a></h4>
                </div>
            </div>
            <div className="row text-end my-3">
                <div className="col-md-4 px-5">
                    <h4><i className="fa fa-instagram"></i></h4>
                </div>
                <div className="col-md-6 text-start">
                    <h4><a href="https://www.instagram.com/lukamaracic/">lukamaracic</a></h4>
                </div>
            </div>
            <div className="row text-end my-3">
                <div className="col-md-4 px-5">
                    <h4><i className="fa fa-linkedin"></i></h4>
                </div>
                <div className="col-md-6 text-start">
                    <h4><a href="http://linkedin/in/ivandradivojevic">Linkedin</a></h4>
                </div>
            </div>
            <div className="row text-end my-3">
                <div className="col-md-4 px-5">
                    <h4><i className="fa fa-github"></i></h4>
                </div>
                <div className="col-md-6 text-start">
                    <h4><a href="https://github.com/Filip284/PenguinTours">git</a></h4>
                </div>
            </div>
       </div>
    );
}