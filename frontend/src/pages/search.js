import React from 'react';
import { useState,useEffect } from 'react';
import axios from 'axios';
import Cardlist from '../components/cardlist';

export default function Searchpage(){
    const [search,setSearch]=useState({
        name:"",
        city:"",
        country:"",
        continent:"",
        transportation:"",
        startdate:"",
        enddate:"",
    });
    const [arr,setArr] = useState([]);
    const [page, setPage] = useState(0);
    const [size, setSize] = useState(50);
    const [totalPages,setTotalPages]=useState(0);
    const [loading, setLoading] = useState(true);


    const updateName=(e)=>{
        setSearch(previousData=>{
            return{...previousData,name:(e.target.value)}
        })
    };

    const updateCity=(e)=>{
        setSearch(previousData=>{
            return{...previousData,city:(e.target.value)}
        })
    };

    const updateCountry=(e)=>{
        setSearch(previousData=>{
            return{...previousData,country:(e.target.value)}
        })
    };

    const updateContinent=(e)=>{
        setSearch(previousData=>{
            return{...previousData,continent:(e.target.value)}
        })
    };

    const updateStartdate=(e)=>{
        setSearch(previousData=>{
            return{...previousData,startdate:(e.target.value)}
        })
    };

    const updateEnddate=(e)=>{
        setSearch(previousData=>{
            return{...previousData,enddate:(e.target.value)}
        })
    };

    const updateTransportation=(e)=>{
        setSearch(previousData=>{
            return{...previousData,transportation:(e.target.value)}
        })
    };

    const doOnRender=(data)=>{
        setArr(data.content);
        setTotalPages(data.totalPages);
    };

    const getArr=()=>{
        var x = `http://localhost:8080/arrangements/all?page=${page}&size=${size}`;
        axios.get(x)
            .then((response) => response.data)
            .then((data) => {
                doOnRender(data);
                setLoading(false);
        });
    };

    const getSearch=()=>{
        var x = `http://localhost:8080/arrangements/get?`+
            (search.name?"name="+search.name+"&":"") +
            (search.city?"city="+search.city+"&":"") +
            (search.country?"country="+search.country+"&":"") +
            (search.continent?"continent="+search.continent+"&":"") +
            (search.transportation?"transportation="+search.transportation+"&":"") +
            (search.startdate?"startDate="+search.startdate+"&":"") +
            (search.enddate?"endDate="+search.enddate+"&":"") +
            `page=${page}&size=${size}`;
        axios.get(x)
            .then((response) => response.data)
            .then((data) => {
                doOnRender(data);
                console.log(data);
                setLoading(false);
        });
    };

    const onSearch=()=>{
        getSearch()
    };

    React.useEffect(() => {
            getSearch()
    }, [page,size])

    const firstPage=()=>{
        setPage(0);
        setLoading(true);
    };

    const lastPage=()=>{
        setPage(totalPages-1)
        setLoading(true);
    };

    const prevPage=()=>{
        setPage(page-1)
        setLoading(true);
    };
    
    const nextPage=()=>{
        setPage(page+1)
        setLoading(true);
    };
    
    const cancelSearch = () => {
        setSearch({ name:"",
                    city:"",
                    country:"",
                    continent:"",
                    transportation:"",
                    startdate:"",
                    enddate:"",
                });
        getArr();
    };

    return(
        <>
        <div className='py-5' style={{backgroundColor: 'floralwhite'}}>
            <div className='container' >
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
                                    value={search.name}
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
                                    value={search.city}
                                    onChange={updateCity}
                                    style={{borderRadius:'20px'}}
                                    ></input>
                            </div>
                            <div className='col-md-4 form-group '>
                                <label className="form-label" htmlFor="country">Država:</label>
                                <input className="form-control" 
                                    type="text" 
                                    id="country" 
                                    name="country"
                                    value={search.country}
                                    onChange={updateCountry}
                                    style={{borderRadius:'20px'}}
                                    ></input>
                            </div>
                        </div>
                        <div className='row justify-content-around text-center'>
                            <div className='col-md-4 form-group'>
                                <label htmlFor="continent" className="form-label">Kontinent:</label>
                                <br></br>
                                <select value={search.continent} name="continent" onChange={updateContinent}>
                                    <option value="Evropa">Evropa</option>
                                    <option value="Azija">Azija</option>
                                    <option value="Afrika">Afrika</option>
                                    <option value="Severna Amerika">Severna Amerika</option>
                                    <option value="Juzna Amerika">Južna Amerika</option>
                                    <option value="Australija i Okeanija">Australija i Okeanija</option>
                                    <option defaultValue={""} value="">...</option>
                                </select>
                            </div>
                            <div className='col-md-4 form-group'>
                                <label htmlFor="transportation" className="form-label">Tip prevoza:</label>
                                <br></br>
                                <select value={search.transportation} name="transportation" onChange={updateTransportation}>
                                    <option value="Autobus">Autobus</option>
                                    <option value="Avion">Avion</option>
                                    <option value="Brod">Brod</option>
                                    <option value="Samostalni prevoz">Samostalni prevoz</option>
                                    <option value="Voz">Voz</option>
                                    <option defaultValue={""}  value="">...</option>
                                </select>
                            </div>
                        </div>
                        <div className='row text-center'>
                            <label><h5>Termin:</h5></label>
                        </div>
                        <div className='row justify-content-center text-center'>
                            <div className='col-md-4 form-group '>
                                <label htmlFor="startDate">Od:</label>
                                <input id="startDate" className="form-control" type="date" name='startdate' value={search.startdate}  onChange={updateStartdate}/>
                            </div>
                            <div className='col-md-4 form-group'>
                                <label htmlFor="endDate">Do:</label>
                                <input id="endDate" className="form-control" type="date" name='enddate' value={search.enddate} onChange={updateEnddate}/>
                            </div>
                        </div>
                        <div className='row justify-content-around m-3 p-3'>
                            <div className='col-md-1'>
                                <button className='btn btn-outline-primary' type='button' onClick={onSearch}>Pretraži</button>
                            </div>
                            <div className='col-md-1'>
                                <button className='btn btn-outline-danger' type='button' onClick={cancelSearch}>Poništi</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div className="container my-2" style={{border: "solid",borderColor:"navy",borderRadius:"20px"}}>
            <div className="row align-items-center justify-content-around my-2">
                <div className='col-md-3 text-center'>
                    Strana {page+1} od {totalPages}
                </div>
                <div className="col-md-6 d-flex justify-content-center">
                    <button className='btn btn-outline-info' type='button' disabled={page === 0 ? true : false} onClick={firstPage}>Prva </button>
                    <button className='btn btn-outline-info' type='button' disabled={page === 0 ? true : false} onClick={prevPage}>Prethodna</button>
                    <button className='btn btn-outline-info' type='button' disabled={true} >{page+1}</button>
                    <button className='btn btn-outline-info' type='button' disabled={page === totalPages-1 ? true : false} onClick={nextPage}>Sledeća</button>
                    <button className='btn btn-outline-info' type='button' disabled={page === totalPages-1 ? true : false} onClick={lastPage}>Poslednja</button>
                </div>
                <div className='col-md-3'>
                    <label htmlFor="size" className="form-label mx-2">Po strani:</label>
                    <select value={size} name="size" onChange={(e)=>setSize(e.target.value)}>
                        <option value="25">25</option>
                        <option defaultValue="50"  value="50">50</option>
                        <option value="100">100</option>
                        <option value="200">200</option>
                        
                    </select>
                </div>
            </div>
            <div className='row'>
                {loading ? (
                    <div>
                        <div className="d-flex justify-content-center m-5 p-5">
                            <div className="spinner-border" role="status">
                                <span className="visually-hidden">Loading...</span>
                            </div>
                        </div>
                    </div>
                    ) : (
                    <div>
                        <div className="row row-cols-1 row-cols-md-3 g-4 my-2 justify-content-center">
                            {arr && arr.map((item,index) => (
                                <Cardlist name={item.name}  startDate={item.startDate} endDate={item.endDate} city={item.city} country={item.country} price={item.price} transport={item.transportation} id={item.id} key={index} picture={item.bigPicture}/>
                                ))}
                        </div>
                    </div>)
                }
            </div>
        </div>
        </>
    );
}