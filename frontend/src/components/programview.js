import React from "react";

export default function Programview(props){
    return(
        <>
        <div className="row my-2 py-2" style={{backgroundColor:'whitesmoke'}}>
            <div className="col-md-12">
                <b>{(props.day)}. DAN: {props.view.locations.map((location,index)=>(<span key={index}>{location.city} - </span>))}{props.view.date}</b> 
            </div>
        </div>
        <div className="row my-2 py-2">
            <div className="col-md-12 px-5 pb-3 text-break">
                {props.view.description}
            </div>
            <hr></hr>
        </div>
        
        </>
    );
}