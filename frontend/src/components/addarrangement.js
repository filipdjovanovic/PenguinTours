import React, { useState } from "react";
import Accinput from "./accomodationinput";

export default function Addarrangement(){
    const [arrangement,setArrangement]=useState({
        name:"",
        price:0,
        transportation:"",
        status:"D",
        remark:"",
        accomodations:[],
        programs:[]
    });

    const [date,setDate]=useState({
        startdate:"",
        enddate:""
    });

    const updateStartdate=(e)=>{
        setDate(previousData=>{
            return{...previousData,startdate:(e.target.value)}
        })
    }

    const updateEnddate=(e)=>{
        setDate(previousData=>{
            return{...previousData,enddate:(e.target.value)}
        })
    }

    const updateName=(e)=>{
        setArrangement(previousData=>{
            return{...previousData,name:(e.target.value)}
        })
    }
    const updatPrice=(e)=>{
        setArrangement(previousData=>{
            return{...previousData,price:(e.target.value)}
        })
    }
    const updateTransportation=(e)=>{
        setArrangement(previousData=>{
            return{...previousData,transportation:(e.target.value)}
        })
    }
    const updateStatus=(e)=>{
        setArrangement(previousData=>{
            return{...previousData,status:(e.target.value)}
        })
    }
    const updateRemark=(e)=>{
        setArrangement(previousData=>{
            return{...previousData,remark:(e.target.value)}
        })
    }
    const handleUpdateAccomodations=(newArray)=>{
        setArrangement((previousData) => (
             { ...previousData, accomodations:newArray}
        ));
    }

    const removeFromArray = (item) => {
        setArrangement((prevState) => ({
          ...prevState,
          accomodations: prevState.accomodations.filter((i) => i !== item),
        }));
      };
   
    function updatePrograms(data) {
        setArrangement(previousData => {
            return { ...previousData, programs: (oldArray => [...oldArray, data]) };
        });
    }
    return(
        <div className="conteiner">
            <form className="row ">
                <div className="col-md-5">
                    <label className="form-label my-1" htmlFor="name">Naziv aranzmana:</label>
                    <input className="form-control" 
                        type="text" 
                        id="name" 
                        name="name"
                        value={arrangement.name}
                        onChange={updateName}
                        style={{borderRadius:'20px'}}></input>
                    <label htmlFor="price" className="form-label my-1">Cena u evrima:</label>
                    <input 
                        type="number" 
                        className="form-control"  
                        placeholder="Unesite cenu aranzmana"  
                        name="price"
                        value={arrangement.price}
                        onChange={updatPrice} 
                        style={{borderRadius:'20px',width:'120px'}}
                        required></input>
                    <label htmlFor="transportation" className="form-label my-1">Tip prevoza:</label>
                    <br></br>
                    <select className="text-wrap" value={arrangement.transportation} name="transportation" onChange={updateTransportation} style={{borderRadius:'20px',height:"30px"}} required>
                        <option value="Avion">Avion</option>
                        <option value="Autobus">Autobus</option>
                        <option value="Krstarenje">Krstarenje</option>
                        <option value="Samostalni prevoz">Samostalni prevoz</option>
                        <option value="Voz">Voz</option>
                        <option selected value="">...</option>
                    </select>
                    <div className="row justify-content-center">
                        <label className="form-label my-1" >Datum:</label>
                        <div className="col-md-6">
                            <label htmlFor="startDate">Od:</label>
                            <input id="startDate" class="form-control" type="date" name='startdate' value={date.startdate}  onChange={updateStartdate} required/>
                        </div>
                        <div className="col-md-6">
                            <label htmlFor="endDate">Do:</label>
                            <input id="endDate" class="form-control" type="date" name='enddate' value={date.enddate} onChange={updateEnddate} required/>
                        </div>
                    </div>
                </div>
                <div className="col-md-5">
                    <label htmlFor="remark" className="form-label my-1">Komentar:</label>
                    <br></br>
                    <textarea value={arrangement.remark} name="remark" onChange={updateRemark} rows={5} cols={10} style={{borderRadius:'10px',height:"180px",width:"400px"}} required/>
                </div>
                <div className="row">
                    <Accinput sendAcc={handleUpdateAccomodations} accarr={arrangement} removeFromArray={removeFromArray}/>
                </div>
            </form>
        </div>
    );
}