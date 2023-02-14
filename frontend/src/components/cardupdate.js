import axios from "axios";
import React from "react";
import { Link } from "react-router-dom";

export default function Cardupdate(props){

    const doAll=(id)=>{
        deleteArr();
        props.updateDelete(id);

    };

    const deleteArr=()=>{
        axios.delete(`http://localhost:8080/arrangements/delete?id=${props.id}`)  
        .then(res => {  
            console.log(res);  
            console.log(res.data);  
        })
    };

    return(
        <>
        <div className="col px-0 mx-2 my-2"  style={{width: '25rem'}}>
            <div className="card h-100" style={{ textDecoration: 'none',}}>
                <div className="card-body" style={{backgroundColor:"floralwhite"}}>
                    <h5 className="card-title">{props.name}</h5>
                    <p className="card-text">Od {props.startDate} do {props.endDate}</p>
                    <Link className="btn btn-primary mx-1" to={`arrangamentupdate/${props.id}`} >Ažuriraj</Link>
                    <button className="btn btn-danger mx-1" type="button" onClick={()=>doAll(props.id)}>Obriši</button>
                </div>
            </div>
        </div>
        </>
    );
}