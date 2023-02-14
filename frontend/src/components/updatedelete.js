import React, { useState } from "react";
import axios from "axios";
import Cardupdate from "./cardupdate";

export default function Updatedelete(){
    const [search,setSearch]=useState("");
    const [arr,setArr] = useState([]);
    const [page, setPage] = useState(0);
    const [size, setSize] = useState(50);
    const [totalPages,setTotalPages]=useState(0);
    const [loading, setLoading] = useState(true);

    const doOnRender=(data)=>{
        setArr(data.content);
        setTotalPages(data.totalPages);
    };

    const removeFromArray = (id) => {
        setArr(arr.filter((item) => item.id !== id));
    };

    const getArr=()=>{
        var x = `http://localhost:8080/arrangements/all?page=${page}&size=${size}`;
        axios.get(x)
            .then((response) => response.content)
            .then((data) => {
                doOnRender(data.content);
                setLoading(false);
        });
    };

    const getSearch=()=>{
        var x = `http://localhost:8080/arrangements/get?`+
            (search?"name="+search+"&":"")+
            `page=${page}&size=${size}`;
        axios.get(x)
            .then((response) => response.data)
            .then((data) => {
                doOnRender(data);
                setLoading(false);
        });
    };

    const onSearch=()=>{
        getSearch()
    };

    React.useEffect(() => {
            getSearch()
    },[page]);

    const firstPage=()=>{
        setPage(0);
        setLoading(true);
    };

    const lastPage=()=>{
        setPage(totalPages-1) ;
        setLoading(true);
    };

    const prevPage=()=>{
        setPage(page-1);
        setLoading(true);
    };

    const nextPage=()=>{
        setPage(page+1);
        setLoading(true);
    };

    return(
        <div className="row">
            <form className="row justify-content-center">
                <div className="col-md-4">
                    <div className="row justify-content-center text-center">
                        <label htmlFor="search" className="form-label text-">Pretraži po nazivu:</label>
                        <input className="form-control" 
                            type="text" 
                            id="search" 
                            name="search"
                            value={search}
                            onChange={(e)=>setSearch(e.target.value)}
                            style={{borderRadius:'20px'}}
                            ></input>
                            <button className='btn btn-primary my-2' type='button' onClick={onSearch} style={{width:"50%"}}>Pretraži</button>
                    </div>
                </div>
            </form>
            <div className="container my-2" style={{border: 'solid',borderColor:'navy',borderRadius:'20px'}}>
                <div className='row align-items-center justify-content-around my-2'>
                    <div className='col-md-3 text-center'>
                        Strana {page+1} od {totalPages}
                    </div>
                    <div className='col-md-6 d-flex justify-content-center'>
                        <button className='btn btn-outline-info' type='button' disabled={page === 0 ? true : false} onClick={firstPage}>Prva </button>
                        <button className='btn btn-outline-info' type='button' disabled={page === 0 ? true : false} onClick={prevPage}>Prethodna</button>
                        <button className='btn btn-outline-info' type='button' disabled={true} >{page+1}</button>
                        <button className='btn btn-outline-info' type='button' disabled={page === totalPages-1 ? true : false} onClick={nextPage}>Sledeća</button>
                        <button className='btn btn-outline-info' type='button' disabled={page === totalPages-1 ? true : false} onClick={lastPage}>Poslednja</button>
                    </div>
                    <div className='col-md-3'>
                        <label htmlFor="size" className="form-label mx-2">Po strani:</label>
                        <select value={size} name="size" onChange={(e)=>setSize(e.target.value)}>
                            <option defaultValue="50"  value="50">50</option>
                            <option value="25">25</option>
                            <option value="100">100</option>
                            <option value="200">200</option>
                        </select>
                    </div>
                </div>
                <div className="row row-cols-1 row-cols-md-3 g-4 my-2 justify-content-center">
                {loading ? (
                    <div>
                        <div className="d-flex justify-content-center m-5 p-5">
                            <div className="spinner-border" role="status">
                                <span className="visually-hidden">Loading...</span>
                            </div>
                        </div>
                    </div>
                    ) : 
                    (arr && arr.map((item,index) => (
                        <Cardupdate name={item.name}  startDate={item.startDate} endDate={item.endDate}  id={item.id} key={index} arr={item} updateDelete={removeFromArray}/>
                        )))
                }
                </div>
            </div>
        </div>
    );
};