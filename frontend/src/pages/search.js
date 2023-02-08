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
    })
    const [arrangaments,setArrangaments]=useState([]);
    const [currentPage,setCurrentPage]=useState(1);
    const [arrPerPage,setArrPerPage]=useState(1);
    const [sortDir,setSortDir]=useState("asc");
    const [totalPages,setTotalPages]=useState(0);
    const [totalElements,setTotalElements]=useState(0);


    const updateName=(e)=>{
        setSearch(previousData=>{
            return{...previousData,name:(e.target.value)}
        })
    }
    const updateCity=(e)=>{
        setSearch(previousData=>{
            return{...previousData,city:(e.target.value)}
        })
    }
    const updateCountry=(e)=>{
        setSearch(previousData=>{
            return{...previousData,country:(e.target.value)}
        })
    }
    const updateContinent=(e)=>{
        setSearch(previousData=>{
            return{...previousData,continent:(e.target.value)}
        })
    }
    const updateStartdate=(e)=>{
        setSearch(previousData=>{
            return{...previousData,startdate:(e.target.value)}
        })
    }
    const updateEnddate=(e)=>{
        setSearch(previousData=>{
            return{...previousData,enddate:(e.target.value)}
        })
    }
    const updateTransportation=(e)=>{
        setSearch(previousData=>{
            return{...previousData,transportation:(e.target.value)}
        })
    }

    const doOnRender=(dat)=>{
        setArrangaments(dat.content);
        setTotalPages(dat.totalPages);
        setTotalElements(dat.totalElements);
        setCurrentPage(dat.number + 1);
    };

    const findAllArr=()=>{
        axios.get("http://localhost:8080/arrangements/all?page="+(currentPage-1).toString()+"&size="+arrPerPage.toString())
            .then((response) => response.data)
            .then((data) => (
                doOnRender(data)
            ))
            /*.catch((error) => {
                console.log(error);
                /*localStorage.removeItem("jwtToken");*/
                /*this.props.history.push("/");
            });*/
    }

    const sortData = () => {
        setTimeout(() => {
          sortDir === "asc"
            ? setSortDir("desc")
            : setSortDir("asc");
          findAllArr(currentPage);
        }, 500);
    };

    useEffect(()=>{
        if(checkSearch){
            searchData()
        }else{
            findAllArr();
        }
        findAllArr();
    },[])

    const checkSearch=()=>{
        let check=(search.name || search.city || search.country || search.continent || search.transportation || search.startdate || search.enddate);
        if (check) {
            return true;
        } else {
            return false;
        }
    }
    
    const firstPage = () => {
        if (currentPage > firstPage) {
            if (checkSearch) {
                setCurrentPage(1)
                searchData();
            } else {
                setCurrentPage(1);
                findAllArr();
            }
        }
    };
    
    const prevPage = () => {
        if (currentPage > 1) {
            if (checkSearch) {
                setCurrentPage(currentPage-1);
                searchData();
            } else {
                setCurrentPage(currentPage - 1);
                findAllArr();
            }
        }
    };

    const lastPage = () => {
    let condition = totalPages;
    if (currentPage < condition) {
        if (checkSearch) {
            setCurrentPage(condition);
            searchData();
        } else {
            setCurrentPage(condition);
            findAllArr();
        }
    }
    };
    
    const nextPage = () => {
    if (currentPage < totalPages) {
        if (checkSearch) {
            setCurrentPage(currentPage + 1);
            searchData();
        } else {
            setCurrentPage(currentPage + 1);
            findAllArr(currentPage + 1);
        }
    }
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
        findAllArr();
    };
    
    const searchData = () => {
        axios.get("http://localhost:8080/arrangement/get" +
                (search.name?"?name="+search.name+"&":"") +
                (search.city?"?city="+search.city+"&":"") +
                (search.country?"?country="+search.country+"&":"") +
                (search.continent?"?continent="+search.continent+"&":"") +
                (search.transportation?"?transportation="+search.transportation+"&":"") +
                (search.startdate?"?startDate="+search.startdate+"&":"") +
                (search.enddate?"?endDate="+search.enddate:"") +
                "?page=" +
                (currentPage-1).toString() +
                "&size=" +
                arrPerPage.toString()
            )
            .then((response) => response.data)
            .then((data) => (
                doOnRender(data)
            ));
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
                            <label className="form-label" htmlFor="country">Drzava:</label>
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
                                <option value="Juzna Amerika">Juzna Amerika</option>
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
                                <option value="Krstarenje">Krstarenje</option>
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
                                <button className='btn btn-outline-primary' type='button' onClick={searchData}>Pretrazi</button>
                            </div>
                            <div className='col-md-1'>
                                <button className='btn btn-outline-danger' type='button' onClick={cancelSearch}>Pretrazi</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div className="container my-2" style={{border: 'solid',borderColor:'navy',borderRadius:'20px'}}>
            <div className="row row-cols-1 row-cols-md-3 g-4 my-2 justify-content-center">
                {arrangaments && arrangaments.map((item,index) => (
                    <Cardlist name={item.name}  startDate={item.startDate} endDate={item.endDate} price={item.price} transport={item.transportation} id={item.id} key={index}/>
                ))}
            </div>
                <div className='row align-items-center justify-content-around my-2'>
                    <div className='col-md-5'>
                    Strana {currentPage} od {totalPages}
                    </div>
                    <div className='col-md-5 '>
                        <button className='btn btn-outline-info' type='button' disabled={currentPage === 1 ? true : false} onClick={firstPage}>Prva </button>
                        <button className='btn btn-outline-info' type='button' disabled={currentPage === 1 ? true : false} onClick={prevPage}>Predhodna</button>
                        <button className='btn btn-outline-info' type='button' disabled={true} >{currentPage}</button>
                        <button className='btn btn-outline-info' type='button' disabled={currentPage === totalPages ? true : false} onClick={nextPage}>Sledeca</button>
                        <button className='btn btn-outline-info' type='button' disabled={currentPage === totalPages ? true : false} onClick={lastPage}>Poslednja</button>
                    </div>
                </div>
        </div>
        </>
    );
}