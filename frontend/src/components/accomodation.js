import React from "react";

export default function Accomodation(props){
    return(
        <form novalidate>
            <div className="row ">
                <div className="col-md-12">
                    <label htmlFor="name" className="form-label">Naziv smestaja:</label>
                    <input 
                        type={"text"}
                        className="form-control"  
                        placeholder="Unesite naziv smestaja" 
                        name="name"
                        value={props.name} 
                        onChange={(e)=>props.setAccName(e.target.value)} 
                        required></input>
                </div>
            </div>
        </form>
    );
}