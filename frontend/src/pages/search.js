import React from 'react';
import { useState } from 'react';
import PaginatedItems from '../components/searchlist';

export default function Searchpage(){
    const [name,setName]=useState('');
    const [transportation,setTransportation]=useState('');
    const [location,setLocation]=useState('');
    const [country,setCountry]=useState('');
    const [continent,setContinent]=useState('');
    const [startdate,setStartdate]=useState(new Date());
    const [enddate,setEnddate]=useState(new Date());

    return(
        <>
        <div className='container my-3' style={{border:'solid',borderColor:'navy',backgroundColor:'whitesmoke',borderRadius:'20px'}}>
            <div className='row justify-content-center'>
                <div className='col-md-4 offset-ms-4'>
                    <h3 className='text-center'>Pronadji putovanje</h3>
                </div>
            </div>
            <form className='row-flex mb-3 mx-5'>
                <div className='row justify-content-around text-center'>
                    <div className='col-md-4 form-group '>
                    <label className='form-label' htmlFor='name'>Naziv aranzmana:</label>
                    <input className='form-control' 
                        type='text' 
                        id='name' 
                        name='name'
                        value={name}
                        onChange={(e)=>setName(e.target.value)}
                        style={{borderRadius:'20px'}}
                        ></input>
                    </div>
                    <div className='col-md-4 form-group '>
                    <label className='form-label' htmlFor='location'>Grad:</label>
                    <input className='form-control' 
                        type='text' 
                        id='location' 
                        name='location'
                        value={location}
                        onChange={(e)=>setLocation(e.target.value)}
                        style={{borderRadius:'20px'}}
                        ></input>
                    </div>
                    <div className='col-md-4 form-group '>
                    <label className='form-label' htmlFor='country'>Drzava:</label>
                    <input className='form-control' 
                        type='text' 
                        id='country' 
                        name='country'
                        value={country}
                        onChange={(e)=>setCountry(e.target.value)}
                        style={{borderRadius:'20px'}}
                        ></input>
                    </div>
                </div>
                <div className='row justify-content-around text-center'>
                <div className='col-md-4 form-group'>
                    <label htmlFor='continent' className='form-label'>Kontinent:</label>
                    <br></br>
                    <select value={continent} name='continent' onChange={(e)=>setContinent(e.target.value)}>
                        <option selected value='Evropa'>Evropa</option>
                        <option value='Azija'>Azija</option>
                        <option value='Afrika'>Afrika</option>
                        <option value='Severna Amerika'>Severna Amerika</option>
                        <option value='Juzna Amerika'>Juzna Amerika</option>
                        <option value='Australija i Okeanija'>Australija i Okeanija</option>
                    </select>
                    </div>
                    <div className='col-md-4 form-group'>
                    <label htmlFor='transportation' className='form-label'>Tip prevoza:</label>
                    <br></br>
                    <select value={transportation} name='transportation' onChange={(e)=>setTransportation(e.target.value)}>
                        <option selected value='Autobus'>Autobus</option>
                        <option value='Avion'>Avion</option>
                        <option value='Krstarenje'>Krstarenje</option>
                        <option value='Samostalni prevoz'>Samostalni prevoz</option>
                        <option value='Voz'>Voz</option>
                    </select>
                    </div>
                </div>
                <div className='row text-center'>
                    <label><h5>Termin:</h5></label>
                </div>
                <div className='row justify-content-center text-center'>
                    <div className='col-md-4 form-group '>
                    <label htmlFor='startDate'>Od:</label>
                    <input id='startDate' class='form-control' type='date' name='startdate' value={startdate}  onChange={(e)=>setStartdate(e.target.value)}/>
                    </div>
                    <div className='col-md-4 form-group'>
                    <label htmlFor='endDate'>Do:</label>
                    <input id='endDate' class='form-control' type='date' name='enddate' value={enddate} onChange={(e)=>setEnddate(e.target.value)}/>
                    </div>
                </div>
            </form>
        </div>
        <PaginatedItems itemsPerPage={50} />
        </>
    );
}