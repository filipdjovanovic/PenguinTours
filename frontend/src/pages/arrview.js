import React, { useEffect, useState } from "react";
import Accomodationview from "../components/accomodationview";
import data from '../fails/proba.json'
import Programview from "../components/programview";


export default function Arrview(props){
    const [arrangament,setArrangament]=useState([])
    const [activeTab, setActiveTab] = useState("tab1");

    const handleTab1 = () => {
        setActiveTab("tab1");
    };
    const handleTab2 = () =>{
        setActiveTab("tab2");
    };

    /*useEffect(()=>{
        fetch("http://localhost:8080/arrangement/view")
        .then(res=>res.json())
        .then((result)=>{
            setArrangament(result);
        })
    },[])*/

    return(
        
        <div className="container">
            <div className="row justify-content-center mt-5">
                <div className="col-md-4 text-center">
                <h3>{data.name}</h3>
                </div>
            </div>
            <div className="row justify-content-center py-5" >
                <div className="col-md-8" >
                    <div className="text-break">{data.remark}</div>
                </div>
            </div>
            <div className="row my-5 p-2 justify-content-center" style={{backgroundColor:'floralwhite'}}>
                <div className="col-md-4">
                    <div className="row justify-content-center mx-1" style={{backgroundColor:'white'}}>
                        Tip prevoza:
                    </div>
                    <div className="row justify-content-center">
                        {data.transportation}
                    </div>
                </div>
                <div className="col-md-4">
                    <div className="row justify-content-center" style={{backgroundColor:'white'}}>
                        Cena po ocobi:
                    </div>
                    <div className="row justify-content-center">
                        {data.price}€
                    </div>
                </div>
                <div className="col-md-4">
                    <div className="row justify-content-center" style={{backgroundColor:'white'}}>
                        Status:
                    </div>
                    <div className="row justify-content-center">
                        {data.status}
                    </div>
                </div>
            </div>
            <div className="container">
                <ul className="nav nav-tabs">
                    <li className="nav-item">
                        <a className={activeTab === "tab1" ? "nav-link active" : "nav-link"} onClick={handleTab1}>Smestaj</a>
                    </li>
                    <li class="nav-item">
                        <a className={activeTab === "tab2" ? "nav-link active" : "nav-link"} onClick={handleTab2}>Program putovanja</a>
                    </li>
                </ul>
                <div className="outlet">
                {activeTab === "tab1" ? <div className="row my-3 p-2">
                    {data.accomodations.map((accomodation,index)=>(
                        <Accomodationview view={accomodation} key={index}/>
                    ))}
                    </div> : <div className="row my-3 p-2">
                        {data.programs.map((program,index)=>(
                        <Programview view={program} key={index} day={index+1}/>
                    ))} 
                    </div>
                }
                </div>
            </div>
        </div>
    );
}