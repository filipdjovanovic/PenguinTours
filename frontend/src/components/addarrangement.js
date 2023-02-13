import React, { useState } from "react";
import Accinput from "./accomodationinput";
import Programinput from "./programinput";
import axios from "axios";

export default function Addarrangement(){
    const [arrangement,setArrangement]=useState({
        name:"",
        price:0,
        transportation:"",
        status:"Dostupno",
        remark:"",
        accomodations:[],
        programs:[]
    });

    const handleClick = () => {
        if (!Object.keys(arrangement).length) {
            console.error('State cannot be empty');
            return;
          }
        
        arrangement.programs.forEach(myFunction);

        function myFunction(item) {
            item.date = new Date((item.date)).toLocaleDateString("en-GB", {
                day: "2-digit",
                month: "2-digit",
                year: "numeric"
              });
          }
        
        axios.post('http://localhost:8080/arrangements/add', arrangement)
          .then(response => {
            console.log(response.data);
          })
          .catch(error => {
            console.error(error);
          });
      };

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
            return{...previousData,price:(Number(e.target.value))   }
        })
    }
    
    const updateTransportation=(e)=>{
        setArrangement(previousData=>{
            return{...previousData,transportation:(e.target.value)}
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
    const handleUpdatePrograms=(newArray)=>{
        setArrangement((previousData) => (
             { ...previousData, programs:newArray}
        ));
    }

    const removeFromArrayProg = (item) => {
        setArrangement((prevState) => ({
          ...prevState,
          programs: prevState.programs.filter((i) => i !== item),
        }));
    };
   
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
                    <select className="text-wrap text-center" value={arrangement.transportation} name="transportation" onChange={updateTransportation} style={{borderRadius:'20px',height:"30px"}} required>
                        <option value="Avion">Avion</option>
                        <option value="Autobus">Autobus</option>
                        <option value="Brod">Brod</option>
                        <option value="Samostalni prevoz">Samostalni prevoz</option>
                        <option value="Voz">Voz</option>
                        <option defaultValue="" value="">...</option>
                    </select>
                    
                </div>
                <div className="col-md-5">
                    <label htmlFor="remark" className="form-label my-1">Komentar:</label>
                    <br></br>
                    <textarea value={arrangement.remark} name="remark" onChange={updateRemark} rows={5} cols={10} style={{borderRadius:'10px',height:"180px",width:"400px"}} required/>
                </div>
                <div className="row">
                    <Accinput sendAcc={handleUpdateAccomodations} accarr={arrangement} removeFromArray={removeFromArray}/>
                </div>
                <div className="row">
                    <Programinput sendProgram={handleUpdatePrograms} progarr={arrangement} removeFromArray={removeFromArrayProg}/>
                </div>
                <div className="row justify-content-center my-3">
                    <div className="col-md-4">
                        <button className="btn btn-primary" type="submit" onClick={handleClick}  >Dodaj aranzman</button>
                    </div>
                </div>
            </form>
        </div>
    );
}