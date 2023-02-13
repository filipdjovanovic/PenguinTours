import React from "react";
import { useState } from "react";
import Addarrangement from "../components/addarrangement";
import Reservation from "../components/reservations";
import Staffwork from "../components/staffwork";
import Updatedelete from "../components/updatedelete";

export default function Adminpage(){
    const [activeTab, setActiveTab] = useState("tab1");

    const handleTab1 = () => {
        setActiveTab("tab1");
    };
    const handleTab2 = () =>{
        setActiveTab("tab2");
    };
    const handleTab3 = () =>{
        setActiveTab("tab3");
    };
    const handleTab4 = () =>{
        setActiveTab("tab4");
    };
    return(
        <div className="container">
            <div className="row justify-content-center my-2 py-2 ">
                <div className="text-center">
                    <h1>Dobrodosli na Admin stranu</h1>
                </div>
            </div>
            <div className="row">
                <div className="col-md-12">
                    <ul className="nav nav-tabs">
                        <li className="nav-item">
                            <button className={activeTab === "tab1" ? "nav-link active" : "nav-link"} onClick={handleTab1}>Dodaj aranzman</button>
                        </li>
                        <li className="nav-item">
                            <button className={activeTab === "tab2" ? "nav-link active" : "nav-link"} onClick={handleTab2}>Azuriraj i obrisi aranzman</button>
                        </li>
                        <li className="nav-item">
                            <button className={activeTab === "tab3" ? "nav-link active" : "nav-link"} onClick={handleTab3}>Rezervacije</button>
                        </li>
                        <li className="nav-item">
                            <button className={activeTab === "tab4" ? "nav-link active" : "nav-link"} onClick={handleTab4}>Azuriraj zaposlene</button>
                        </li>
                    </ul>
                    <div className="outlet">
                    {activeTab === "tab1" ? <div className="row my-3 p-2">
                                <Addarrangement /></div>  
                                :(activeTab==="tab2"?<div className="row my-3 p-2"><Updatedelete /></div>
                                :(activeTab==="tab3"?<div className="row my-3 p-2"><Reservation /></div>
                                :<div className="row my-3 p-2"><Staffwork /></div>))
                    }
                    </div>
                </div>
            </div>
        </div>
    );
}