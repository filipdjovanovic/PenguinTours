import React, { useState } from 'react'
import Cardview from '../components/cards';

export default function Homepage(){

    const [data,setData]=useState({
        name:"",
        city:"",
        country:"",
        continent:"Evropa",
        transportation:"Autobus",
        startdate:"",
        enddate:"",
    })

    const updateName=(e)=>{
        setData(previousData=>{
            return{...previousData,name:(e.target.value)}
        })
    }
    const updateCity=(e)=>{
        setData(previousData=>{
            return{...previousData,city:(e.target.value)}
        })
    }
    const updateCountry=(e)=>{
        setData(previousData=>{
            return{...previousData,country:(e.target.value)}
        })
    }
    const updateContinent=(e)=>{
        setData(previousData=>{
            return{...previousData,continent:(e.target.value)}
        })
    }
    const updateStartdate=(e)=>{
        setData(previousData=>{
            return{...previousData,startdate:(e.target.value)}
        })
    }
    const updateEnddate=(e)=>{
        setData(previousData=>{
            return{...previousData,enddate:(e.target.value)}
        })
    }
    const updateTransportation=(e)=>{
        setData(previousData=>{
            return{...previousData,transportation:(e.target.value)}
        })
    }

    


    return(
        <>
        <div className='py-5' style={{backgroundColor: 'floralwhite'}}>
            <div className='container' >
                <div className='row' >
                    <div className='col-md-6 offset-md-3 my-5'>
                        <h1 className='text-center mt-4 ' style={{color:'navy'}}>Doživite svet, jedno po jedno putovanje!</h1>
                    </div>
                </div>
                <div className='row-flex shadow ' style={{border:'solid',borderColor:'navy',backgroundColor:'whitesmoke',borderRadius:'20px'}}>
                    <div className='row justify-content-center'>
                        <div className='col-md-4 offset-ms-4'>
                            <h3 className='text-center'>Pronadji putovanje</h3>
                        </div>
                    </div>
                    <form className='row-flex mb-3 mx-5'>
                        <div className='row justify-content-around text-center'>
                            <div className='col-md-4 form-group '>
                            <label className="form-label" htmlFor="name">Naziv aranzmana:</label>
                            <input className="form-control" 
                                type="text" 
                                id="name" 
                                name="name"
                                value={data.name}
                                onChange={updateName}
                                style={{borderRadius:'20px'}}
                                ></input>
                            </div>
                            <div className='col-md-4 form-group '>
                            <label className="form-label" htmlFor="location">Grad:</label>
                            <input className="form-control" 
                                type="text" 
                                id="location" 
                                name="location"
                                value={data.city}
                                onChange={updateCity}
                                style={{borderRadius:'20px'}}
                                ></input>
                            </div>
                            <div className='col-md-4 form-group '>
                            <label className="form-label" htmlFor="country">Drzava:</label>
                            <input className="form-control" 
                                type="text" 
                                id="country" 
                                name="country"
                                value={data.country}
                                onChange={updateCountry}
                                style={{borderRadius:'20px'}}
                                ></input>
                            </div>
                        </div>
                        <div className='row justify-content-around text-center'>
                        <div className='col-md-4 form-group'>
                            <label htmlFor="continent" className="form-label">Kontinent:</label>
                            <br></br>
                            <select value={data.continent} name="continent" onChange={updateContinent}>
                                <option selected value="Evropa">Evropa</option>
                                <option value="Azija">Azija</option>
                                <option value="Afrika">Afrika</option>
                                <option value="Severna Amerika">Severna Amerika</option>
                                <option value="Juzna Amerika">Juzna Amerika</option>
                                <option value="Australija i Okeanija">Australija i Okeanija</option>
                            </select>
                            </div>
                            <div className='col-md-4 form-group'>
                            <label htmlFor="transportation" className="form-label">Tip prevoza:</label>
                            <br></br>
                            <select value={data.transportation} name="transportation" onChange={updateTransportation}>
                                <option selected value="Autobus">Autobus</option>
                                <option value="Avion">Avion</option>
                                <option value="Krstarenje">Krstarenje</option>
                                <option value="Samostalni prevoz">Samostalni prevoz</option>
                                <option value="Voz">Voz</option>
                            </select>
                            </div>
                        </div>
                        <div className='row text-center'>
                            <label><h5>Termin:</h5></label>
                        </div>
                        <div className='row justify-content-center text-center'>
                            <div className='col-md-4 form-group '>
                            <label htmlFor="startDate">Od:</label>
                            <input id="startDate" class="form-control" type="date" name='startdate' value={data.startdate}  onChange={updateStartdate}/>
                            </div>
                            <div className='col-md-4 form-group'>
                            <label htmlFor="endDate">Do:</label>
                            <input id="endDate" class="form-control" type="date" name='enddate' value={data.enddate} onChange={updateEnddate}/>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div className='container my-5'>
            <Cardview />
        </div>
        </>
    );
}