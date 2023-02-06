import React, { useEffect, useState } from "react";
import Cardlist from "./cardlist";

export default function Cardview(){
    const [data,setData]=useState([])

    useEffect(()=>{
        fetch("http://localhost:8080/arrangements/hot")
        .then(res=>res.json())
        .then((result)=>{
            setData(result);
        })
    },[])
    return(
        <>
        <div className="row justify-content-center">
            <div className="col-md-8 offset-md-2 mx-5 py-4 text-center">
                <h1 classname="text-center text-break" style={{color:'navy'}} >Poslednja mesta!</h1>
            </div>
        </div>
        <div style={{borderTop:'solid',borderBottom:'solid', borderColor:'darkorange'}}>
        <div className="row my-0 mx-3 p-5" >
            <Cardlist name="Rim dan zaljubljenih 2023" destination="Rim" price="150" days="3" transport="Autobus"/>
            <Cardlist name="Rim dan zaljubljenih 2023" destination="Rim" price="150" days="3" transport="Autobus"/>
            <Cardlist name="Rim dan zaljubljenih 2023" destination="Rim" price="150" days="3" transport="Autobus"/>
        </div>
        <hr style={{color:'darkorange'}}></hr>
        <div className="row my-0 mx-3 p-5" >
            <Cardlist name="Rim dan zaljubljenih 2023" destination="Rim" price="150" days="3" transport="Autobus"/>
            <Cardlist name="Rim dan zaljubljenih 2023" destination="Rim" price="150" days="3" transport="Autobus"/>
            <Cardlist name="Rim dan zaljubljenih 2023" destination="Rim" price="150" days="3" transport="Autobus"/>
        </div>
        </div>
        </>
    );
}