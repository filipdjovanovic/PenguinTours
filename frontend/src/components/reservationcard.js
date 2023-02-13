import axios from "axios";
import React, { useState } from "react";

export default function Reservationcard(props){

    const [rejected,setRejected]=useState("");

    const acceptRes=()=>{
        axios.put(`http://localhost:8080/reservation/update?id=${props.application.id}&accepted=true`)
        .then(response => {
            console.log(response.data);
          })
          .catch(error => {
            console.error(error);
          });
    };

    const rejectRes=()=>{
        axios.put(`http://localhost:8080/reservation/update?id=${props.application.id}&accepted=false`)  
        .then(res => {  
            console.log(res);  
            console.log(res.data);  
        })
    };

    const onRejected=()=>{
        rejectRes();
        setRejected("rejected");
    };

    const onAccepted=()=>{
        acceptRes();
        setRejected("accepted");
    };


    return(
        <div className="card text-center my-2 mx-0 px-3">
            <div className="card-header">
               <strong className="text-break"> Rezervacija za {props.application.arrangementName}</strong>
            </div>
            <div className="card-body">
                <h5 className="card-title text-break">{props.application.person.name} {props.application.person.lastName}</h5>
                <p className="card-text text-break">E-mail: {props.application.person.email} , Kontakt: {props.application.person.contact}</p>
                <p className="card-text text-break">Adresa: {props.application.person.address} , Nacin placanja: {props.application.paymentMethod} </p>
                <p className="card-text text-break"> Odrasli: {props.application.numberOfAdults} , Deca: {props.application.numberOfKids}</p>
                <p className="card-text text-break">Komentar: {props.application.comment}</p>
            </div>
            <div className="card-footer">
            {rejected===""
            ?(props.application.accepted===null)
                ?<div><button className="btn btn-primary mx-1" onClick={onAccepted}>Potvrdi</button><button className="btn btn-danger mx-1" onClick={onRejected}>Odbaci</button></div>
                :(props.application.accepted==="true")
                    ?<button className="btn btn-danger mx-1" onClick={onRejected}>Odbaci </button>
                    :(props.application.accepted==="false")        
                        ?<p className="card-text">Ova rezervacija je odbacena  </p>
                        :null
            :(rejected==="accepted")
                ?<button className="btn btn-danger mx-1" onClick={onRejected}>Odbaci</button>
                :<p className="card-text">Ova rezervacija je odbacena</p>}
            </div>
        </div>
    );
};

