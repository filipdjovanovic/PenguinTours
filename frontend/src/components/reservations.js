import axios from "axios";
import React, { useEffect, useState } from "react";
import Reservationcard from "./reservationcard";

export default function Reservation(){
    const [search,setSearch]=useState("");
    const [reservations,setReservation]=useState([]);

    const getReservations=()=>{
        axios.get(`http://localhost:8080/reservation/all`)
            .then((response) => response.data)
            .then((data) => (
                setReservation(data)
            ));
    };

    const getSearch=()=>{
        var x = `http://localhost:8080/reservation/get?`+
            (search?"arrangementName="+search+"&":"");
        axios.get(x)
            .then((response) => response.data)
            .then((data) => (
                setReservation(data)
            ));
    };

    const onSearch=()=>{
        getSearch();
    };

    useEffect(() => {
            getReservations();
    }, []);


    return(
        <div className="row">
            <form className="row justify-content-center">
                <div className="col-md-4 ">
                <div className="row justify-content-center text-center">
                    <label htmlFor="search" className="form-label text-">Pretrazi po nazivu arnazmana:</label>
                    <input className="form-control" 
                        type="text" 
                        id="search" 
                        name="search"
                        value={search}
                        onChange={(e)=>setSearch(e.target.value)}
                        style={{borderRadius:'20px'}}
                        ></input>
                        <button className='btn btn-primary my-2' type='button' onClick={onSearch} style={{width:"50%"}}>Pretrazi</button>
                        </div>
                </div>
            </form>
            <div className="container my-2" style={{border: 'solid',borderColor:'navy',borderRadius:'20px'}}>
                <div className="row p-3 my-0 justify-content-center">
                    {reservations && reservations.map((item,index) => (
                        <Reservationcard key={index} application={item} show={item.accepted}/>
                    ))}
                </div>
            </div>
        </div>
    );
}