import React, { useState } from "react";

export default function Accinput(props){
    const [accomodation,setAccomodation]=useState({
        name:"",
        category:"",
        type:"",
        tv:true,
        safe:true,
        fridge:true,
        ac:true,
        internet:true,
        location:{
            city:"",
            country:"",
            continent:""
        }
    });
    const [selectedFiles, setSelectedFile] = useState({ });

    const handleUpdate = () => {
        const updatedArray = [...props.accarr.accomodations, accomodation];
        props.sendAcc(updatedArray);
        setAccomodation({
            name:"",
            category:"",
            type:"",
            tv:true,
            safe:true,
            fridge:true,
            ac:true,
            internet:true,
            location:{
                city:"",
                country:"",
                continent:""
            }})
      };

    const handleRemove = (item) => {
        props.removeFromArray(item);
    };

    const updateFFile=(e)=>{
        setSelectedFile(previousData=>{
            return{...previousData,selectedFile1:(e.target.files[0])}
        })
    }
    const updateSFile=(e)=>{
        setSelectedFile(previousData=>{
            return{...previousData,selectedFile2:(e.target.files[0])}
        })
    }
    const updateTFile=(e)=>{
        setSelectedFile(previousData=>{
            return{...previousData,selectedFile3:(e.target.files[0])}
        })
    }
    const updateFoFile=(e)=>{
        setSelectedFile(previousData=>{
            return{...previousData,selectedFile4:(e.target.files[0])}
        })
    }
    const updateFiFile=(e)=>{
        setSelectedFile(previousData=>{
            return{...previousData,selectedFile5:(e.target.files[0])}
        })
    }
    const updateSiFile=(e)=>{
        setSelectedFile(previousData=>{
            return{...previousData,selectedFile6:(e.target.files[0])}
        })
    }

    const updateName=(e)=>{
        setAccomodation(previousData=>{
            return{...previousData,name:(e.target.value)}
        })
    }
    const updateCategory=(e)=>{
        setAccomodation(previousData=>{
            return{...previousData,category:(e.target.value)}
        })
    }
    const updateType=(e)=>{
        setAccomodation(previousData=>{
            return{...previousData,type:(e.target.value)}
        })
    }
    const updateTv=(e)=>{
        setAccomodation(previousData=>{
            return{...previousData,tv:((e.target.value)==="true"?true:false)}
        })
    }
    const updateSafe=(e)=>{
        setAccomodation(previousData=>{
            return{...previousData,safe:((e.target.value)==="true"?true:false)}
        })
    }
    const updateFrifge=(e)=>{
        setAccomodation(previousData=>{
            return{...previousData,fridge:((e.target.value)==="true"?true:false)}
        })
    }
    const updateAc=(e)=>{
        setAccomodation(previousData=>{
            return{...previousData,ac:((e.target.value)==="true"?true:false)}
        })
    }
    const updateInternet=(e)=>{
        setAccomodation(previousData=>{
            return{...previousData,internet:((e.target.value)==="true"?true:false)}
        })
    }
    const updateCity=(e)=>{
        setAccomodation(previousData=>({...previousData,location:{...previousData.location,city:(e.target.value)}}));
    }
    const updateCountry=(e)=>{
        setAccomodation(previousData=>({...previousData,location:{...previousData.location,country:(e.target.value)}}));
    }
    const updateContinent=(e)=>{
        setAccomodation(previousData=>({...previousData,location:{...previousData.location,continent:(e.target.value)}}));
    }
    return(
        <>
        <div className="row mt-5 mb-2 p-2" style={{borderTop:"solid"}}>
            <div className="row justify-content-center">
                <div className="col-md-5 text-center">
                    <h4>Unesi smestaje</h4>
                </div>
            </div>
            <div className="col-md-5">
                <label className="form-label my-1" htmlFor="name">Naziv smestajnog objekta:</label>
                <input className="form-control" 
                    type="text" 
                    id="name" 
                    name="name"
                    value={accomodation.name}
                    onChange={updateName}
                    style={{borderRadius:'20px'}}></input>
                <label className="form-label my-1" htmlFor="category">Kategorija smestaja:</label>
                <input className="form-control" 
                    type="text" 
                    id="category" 
                    name="category"
                    value={accomodation.category}
                    onChange={updateCategory}
                    style={{borderRadius:'20px'}}></input>
                <label htmlFor="type" className="form-label my-1">Tip smestaja:</label>
                <br></br>
                <select className="text-wrap text-center" value={accomodation.type} id="type" name="type" onChange={updateType} style={{borderRadius:'20px',height:"30px"}} required>
                    <option value="Hotelski">Hotelski</option>
                    <option value="Bungalov">Bungalov</option>
                    <option defaultValue={""} value="">...</option>
                </select>
                <div className="row">
                    <div className="col-md-4">
                        <label htmlFor="internet" className="form-label my-1">Internet:</label>
                        <br></br>
                        <select className="text-wrap text-center" value={accomodation.internet} id="internet" name="internet" onChange={updateInternet} style={{borderRadius:'20px',height:"30px",width:"75px"}} required>
                            <option defaultValue={true} value={true}>Da</option>
                            <option value={false}>Ne</option>
                        </select>
                    </div>
                    <div className="col-md-4">
                        <label htmlFor="tv" className="form-label my-1">TV:</label>
                        <br></br>
                        <select className="text-wrap text-center" value={accomodation.tv} id="tv" name="tv" onChange={updateTv} style={{borderRadius:'20px',height:"30px",width:"75px"}} required>
                            <option defaultValue={true}value={true}>Da</option>
                            <option value={false}>Ne</option>
                        </select>
                    </div>
                    <div className="col-md-4">
                        <label htmlFor="ac" className="form-label my-1">Klima:</label>
                        <br></br>
                        <select className="text-wrap text-center" value={accomodation.ac} id="ac" name="ac" onChange={updateAc} style={{borderRadius:'20px',height:"30px",width:"75px"}} required>
                            <option defaultValue={true} value={true}>Da</option>
                            <option value={false}>Ne</option>
                        </select>
                    </div>
                    <div className="row justify-content-around">
                        <div className="col-md-4">
                            <label htmlFor="fridge" className="form-label my-1">Frizider:</label>
                            <br></br>
                            <select className="text-wrap text-center" value={accomodation.fridge} id="fridge" name="fridge" onChange={updateFrifge} style={{borderRadius:'20px',height:"30px",width:"75px"}} required>
                                <option defaultValue={true} value={true}>Da</option>
                                <option value={false}>Ne</option>
                            </select>
                        </div>
                        <div className="col-md-4">
                        <label htmlFor="safe" className="form-label my-1">Sef:</label>
                        <br></br>
                        <select className="text-wrap text-center" value={accomodation.safe} id="safe" name="safe" onChange={updateSafe} style={{borderRadius:'20px',height:"30px",width:"75px"}} required>
                            <option defaultValue={true}value={true}>Da</option>
                            <option value={false}>Ne</option>
                        </select>
                    </div>
                    </div>
                </div>
                <div className="row">
                    <div className="col-md-12">
                        <label className="form-label my-1" htmlFor="city">Grad:</label>
                        <input className="form-control" 
                            type="text" 
                            id="city" 
                            name="city"
                            value={accomodation.location.city}
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
                            value={accomodation.location.country}
                            onChange={updateCountry}
                            style={{borderRadius:'20px'}}></input>
                    </div>
                </div>
                <div className="row">
                    <div className="col-md-12">
                        <label htmlFor="continent" className="form-label">Kontinent:</label>
                        <br></br>
                        <select value={accomodation.location.continent} className="text-wrap text-center" name="continent" onChange={updateContinent} style={{borderRadius:'20px',height:"30px",width:"200px"}}>
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
            </div>
            <div className="col-md-5 text-center myu-1">
                <label className="form-label">Unesite slike:</label>
                <div className="row justify-content-center p-0 m-2">
                    <input type="file"
                        id="slika1"
                        value={selectedFiles.selectedFiles1}
                        accept=".png,.jpg,.jpeg"
                        onChange={updateFFile}/>
                </div>
                <div className="row justify-content-center p-0 m-2">
                    <input type="file"
                        id="slika2"
                        value={selectedFiles.selectedFiles2}
                        accept=".png,.jpg,.jpeg"
                        onChange={updateSFile}/>
                </div>
                <div className="row justify-content-center p-0 m-2">
                    <input type="file"
                        id="slika3"
                        value={selectedFiles.selectedFiles3}
                        accept=".png,.jpg,.jpeg"
                        onChange={updateTFile}/>
                </div>
                <div className="row justify-content-center p-0 m-2">
                    <input type="file"
                        id="slika4"
                        value={selectedFiles.selectedFiles4}
                        accept=".png,.jpg,.jpeg"
                        onChange={updateFoFile}/>
                </div>
                <div className="row justify-content-center p-0 m-2">
                    <input type="file"
                        id="slika5"
                        value={selectedFiles.selectedFiles5}
                        accept=".png,.jpg,.jpeg"
                        onChange={updateFiFile}/>
                </div>
                <div className="row justify-content-center p-0 m-2">
                    <input type="file"
                        id="slika6"
                        value={selectedFiles.selectedFiles6}
                        accept=".png,.jpg,.jpeg"
                        onChange={updateSiFile}/>
                </div>
            </div>
        </div>
        <div className="row justify-cnontent-center my-3">
            <div className="col-md-12">
                <button className="btn btn-primary" type="button" onClick={handleUpdate}  >Unesi smestaj</button>
            </div>
        </div>
        <div className="row">
            {props.accarr.accomodations && props.accarr.accomodations.map((item,)=>(
                <div key={item.name}>
                {item.name} <button onClick={() => handleRemove(item)}>Ukloni</button>
                </div>
            ))}
        </div>
        </>
        
    );
}