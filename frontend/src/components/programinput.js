import React, { useState } from "react";
import Programlocation from "./programlocation";

export default function Programinput(props){

    const [program,setProgram]=useState({
        description:"",
        date:"",
        locations:[]
    })

    const handleUpdate = () => {
        const updatedArray = [...props.progarr.programs, program];
        props.sendProgram(updatedArray);
        setProgram({
            description:"",
            date:"",
            locations:[]
            })
      };

    const handleRemove = (item) => {
        props.removeFromArray(item);
    };

    const updateDescription=(e)=>{
        setProgram(previousData=>{
            return{...previousData,description:(e.target.value)}
        })
    }
    const updateDate=(e)=>{
        setProgram(previousData=>{
            return{...previousData,date:(e.target.value)}
        })
    }
    const handleUpdatePrograms=(newArray)=>{
        setProgram((previousData) => (
             { ...previousData, locations:newArray}
        ));
    }
    const removeFromArray = (item) => {
        setProgram((prevState) => ({
          ...prevState,
          locations: prevState.locations.filter((i) => i !== item),
        }));
    };
    return(
        <div className="row mt-5 mb-2 p-2" style={{borderTop:"solid"}}>
            <div className="row justify-content-center">
                <div className="col-md-5 text-center">
                    <h4>Unesi programe</h4>
                </div>
            </div>
            <div className="row">
                <div className="col-md-5">
                    <label htmlFor="date">Datum programa:</label>
                    <input id="date" className="form-control" type="date" name='date' value={program.date} onChange={updateDate}/>
                    <Programlocation sendLocation={handleUpdatePrograms} program={program} removeFromArray={removeFromArray}/>
                    <div className="row justify-cnontent-center my-3">
                    <div className="col-md-12">
                        <button className="btn btn-primary" type="button" onClick={handleUpdate}>Unesi program</button>
                    </div>
                </div>
                <div className="row">
                    {props.progarr.programs && props.progarr.programs.map((item,index)=>(
                        <div className="row"  key={index}>
                            <div className="col-md-3 p-0" style={{borderLeft:"solid",borderColor:"navy"}}>
                                <p className="text-center my-3">{item.date} </p>
                            </div>
                            <div className="col-md-3 p-0" style={{borderRight:"solid",borderColor:"navy"}}>
                                <button className="btn btn-primary my-2" onClick={() => handleRemove(item)}>Ukloni</button>
                            </div>
                        </div>
                    ))}
                </div>
                </div>
                <div className="col-md-5">
                    <label htmlFor="description" className="form-label my-1">Opis:</label>
                    <br></br>
                    <textarea value={program.description} name="description" onChange={updateDescription} rows={5} cols={10} style={{borderRadius:'10px',height:"180px",width:"400px"}} required/>
                </div>
            </div>
        </div>
    );
}
