import axios from "axios";
import React, { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";
import Accinput from "./accomodationinput";
import Programinput from "./programinput";



export default function Updatearr(props){

    const [arrangement,setArrangement]=useState([])
    const {id}= useParams();

    

    useEffect(() => {
        const fetchData = async () => {
          const result = await axios.get("http://localhost:8080/arrangements/get?id=" + id);
          setArrangement(result.data);
        };
    
        fetchData();
    }, [id]);

    const handleClick = () => {
        if (!Object.keys(arrangement).length) {
            console.error('State cannot be empty');
            return;
          }

        function myFunction(item) {
            item.date = new Date((item.date)).toLocaleDateString("en-GB", {
                day: "2-digit",
                month: "2-digit",
                year: "numeric"
              });
          }

        arrangement.programs.forEach(myFunction);
        
        axios.put("http://localhost:8080/arrangements/update?id="+id,arrangement)
          .then(response => {
            console.log(response.data);
          })
          .catch(error => {
            console.error(error);
          });
      };

    const jumpClick=()=>{
        handleClick();
        refreshPage()
    }

    const refreshPage=()=>{
        window.location.reload(false);
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
        <div className="container">
            <div className="row justify-content-center my-3">
                <div className="col-md-6 offset-md-3">
                    <h3 className="text-break">Azuriraj aranzman</h3>
                </div>
            </div>
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
                    <div className="col-md-4 mx-5 px-5 align-items-center">
                        <Link to={""} >
                            <button className="btn btn-primary" type="button" onClick={jumpClick}  >Azuriraj aranzman</button>
                        </Link>
                    </div>
                </div>
            </form>
        </div>
    );
}