import React, { useEffect } from "react";
import { useState } from "react";
import Addarrangement from "../components/addarrangement";
import Reservation from "../components/reservations";
import Updatedelete from "../components/updatedelete";
import { refreshToken } from "../helpers/refreshtoken";
import { setAuthToken } from "../routes.js/setauth";

export default function Staffpage(){
    
    const [activeTab, setActiveTab] = useState("tab1");

    const handleTab1 = () => {
        setActiveTab("tab1");
        refreshToken(localStorage.getItem("token"));
    };

    const handleTab2 = () =>{
        setActiveTab("tab2");
        refreshToken(localStorage.getItem("token"));
    };

    const handleTab3 = () =>{
        setActiveTab("tab3");
        refreshToken(localStorage.getItem("token"));
    };

    useEffect(()=>{
        refreshToken(localStorage.getItem("token"));
    },[])

    return(
        <div className="container d-flex flex-column min-vh-100" >
            <div className="row justify-content-center my-2 py-2 ">
                <div className="text-center">
                    <h1>Dobrodošli na stranu za zaposlene</h1>
                </div>
            </div>
            <div className="row">
                <div className="col-md-12">
                    <ul className="nav nav-tabs">
                        <li className="nav-item">
                            <button className={activeTab === "tab1" ? "nav-link active" : "nav-link"} onClick={handleTab1}>Dodaj aranžman</button>
                        </li>
                        <li className="nav-item">
                            <button className={activeTab === "tab2" ? "nav-link active" : "nav-link"} onClick={handleTab2}>Ažuriraj i obriši aranžman</button>
                        </li>
                        <li className="nav-item">
                            <button className={activeTab === "tab3" ? "nav-link active" : "nav-link"} onClick={handleTab3}>Rezervacije</button>
                        </li>
                    </ul>
                    <div className="outlet">
                        {activeTab === "tab1" ? <div className="row my-3 p-2"><Addarrangement /></div>  
                                :(activeTab==="tab2"?<div className="row my-3 p-2"><Updatedelete /></div>
                                :(activeTab==="tab3"?<div className="row my-3 p-2"><Reservation /></div>
                                :<div className="row my-3 p-2">D</div>))}
                    </div>
                </div>
            </div>
        </div>
    );
}