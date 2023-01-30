import React, { useState } from "react";

export default function Addarrangament(props){
    const [name,setName]=useState('');
    const [price,setPrice]=useState(0);
    const [transportation,setTransportation]=useState('');
    const [remark,setRemark]=useState('');
    const [accomodations,setAccomodations]=useState([]);
    const [programs,setPrograms]=useState([]);

    const onNameChange=(e)=>{
        setName(e.target.value);
    }
    const onPriceChange=(e)=>{
        setPrice(e.target.value);
    }
    const onTransChange=(e)=>{
        setTransportation(e.target.value);
    }
    const onRemarkChange=(e)=>{
        setRemark(e.target.value);
    }
    

    return(
        <div>
            <div className="d-flex justify-content-center"><h1>UNESI NOVI ARANZMAN!</h1></div>
            <form className="needs-validation " novalidate>
            <div className="row ">
                <div className="col-md-4 offset-md-4">
                    <label htmlFor="naziv" className="form-label">Naziv aranzmana:</label>
                    <input 
                        type={"text"}
                        className="form-control"  
                        placeholder="Unesite anziv aranzmana" 
                        name="name"
                        value={name} 
                        onChange={(e)=>onNameChange(e)} 
                        required></input>
                </div>
            </div>
            <div className="row">
            <div className="col-md-4 offset-md-4">
                <label htmlFor="cena" className="form-label">Cena u evrima:</label>
                <input 
                    type="number" 
                    className="form-control"  
                    placeholder="Unesite cenu aranzmana"  
                    name="price"
                    value={price}
                    onChange={(e)=>onPriceChange(e)} 
                    required></input>
            </div>
            </div>
            <div className="row">
            <div className="col-md-4 offset-md-4 my-2">
                <label htmlFor="transportation" className="form-label">Tip prevoza:</label>
                <br></br>
                <select value={transportation} name="transportation" onChange={(e)=>onTransChange(e)} required>
                    <option selected>Avion</option>
                    <option value="Autobus">Autobus</option>
                    <option value="Krstarenje">Krstarenje</option>
                    <option value="Samostalni prevoz">Samostalni prevoz</option>
                    <option value="Voz">Voz</option>
                </select>
            </div>
            </div>
            <div className="row">
            <div className="col-md-4 offset-md-4">
                <label htmlFor="remark" className="form-label">Komentar:</label>
                <br></br>
                <textarea value={remark} name="remark" onChange={e=>setRemark(e.target.value)} rows={5} cols={10}  required/>
            </div>
            </div>
            <div className="row">
            <div className="col-md-4 offset-md-4">
                <div className="form-check">
                <input className="form-check-input" type="checkbox" value="" id="invalidCheck" required></input>
                <label className="form-check-label" for="invalidCheck">
                    Agree to terms and conditions
                </label>
                </div>
            </div>
            </div>
            <div className="row">
            <div className="col-md-4 offset-md-4">
                <button className="btn btn-primary" type="submit">Submit form</button>
            </div>
            </div>
            </form>
            
        </div>
    )
}