import React, { useState } from "react";

export default function Programlocation(props){
    const [location,setLocation]=useState({
        city:"",
        country:"",
        continent:"",
        picture:null
    });

    const convertToBase64 = (file) => {
        return new Promise((resolve, reject) => {
          const fileReader = new FileReader();
          fileReader.readAsDataURL(file);
          fileReader.onload = () => {
            resolve(fileReader.result);
          };
          fileReader.onerror = (error) => {
            reject(error);
          };
        });
    };

    const handleFileUpload = async (e) => {
        const file = e.target.files[0];
        const base64 = await convertToBase64(file);
        updatePicture(base64);
    };

    const handleRemove = (item) => {
        props.removeFromArray(item);
    };

    const updateCity=(e)=>{
        setLocation(previousData=>({...previousData,city:(e.target.value)}));
    };
    const updateCountry=(e)=>{
        setLocation(previousData=>({...previousData,country:(e.target.value)}));
    };
    const updateContinent=(e)=>{
        setLocation(previousData=>({...previousData,continent:(e.target.value)}));
    };
    const updatePicture=(item)=>{
        setLocation(previousData=>({...previousData,picture:item}));
    };
    const handleUpdate = () => {
        const updatedArray = [...props.program.locations, location];
        props.sendLocation(updatedArray);
        setLocation({
            city:"",
            country:"",
            continent:"",
            picture:null
      })};
    return(
        <>
        <div className="row">
            <div className="col-md-12">
                <label className="form-label my-1" htmlFor="city">Grad:</label>
                <input className="form-control" 
                    type="text" 
                    id="city" 
                    name="city"
                    value={location.city}
                    onChange={updateCity}
                    style={{borderRadius:'20px'}}></input>
            </div>
        </div>
        <div className="row">
            <div className="col-md-12">
                <label className="form-label my-1" htmlFor="country">Drzava:</label>
                <input className="form-control" 
                    type="text" 
                    id="country" 
                    name="country"
                    value={location.country}
                    onChange={updateCountry}
                    style={{borderRadius:'20px'}}></input>
            </div>
        </div>
        <div className="row">
            <div className="col-md-12">
                <label htmlFor="continent" className="form-label">Kontinent:</label>
                <br></br>
                <select value={location.continent} className="text-wrap text-center" name="continent" onChange={updateContinent} style={{borderRadius:'20px',height:"30px",width:"200px"}}>
                    <option value="Evropa">Evropa</option>
                    <option value="Azija">Azija</option>
                    <option value="Afrika">Afrika</option>
                    <option value="Severna Amerika">Severna Amerika</option>
                    <option value="Juzna Amerika">Juzna Amerika</option>
                    <option value="Australija i Okeanija">Australija i Okeanija</option>
                    <option defaultValue={""} value="">...</option>
                </select>
            </div>
        </div>
        <div className="row my-1">
            <div className="col-md-12">
                <label htmlFor="picture" className="form-label">Slika Lokacije:</label>
                <br></br>
                <input type="file"
                    id="picture"
                    accept=".png,.jpg,.jpeg"
                    onChange={(e) => handleFileUpload(e)}
                />
            </div>
        </div>
        <div className="row justify-cnontent-center my-3">
            <div className="col-md-12">
                <button className="btn btn-primary" type="button" onClick={handleUpdate}  >Unesi lokaciju</button>
            </div>
        </div>
        <div className="row">
            {props.program.locations && props.program.locations.map((item,index)=>(
                <div key={index}>
                {item.city} <button onClick={() => handleRemove(item)}>Ukloni</button>
                </div>
            ))}
        </div>
        </>
    );
}