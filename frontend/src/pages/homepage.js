import React, { useEffect, useState } from 'react'
import Cards from '../components/cards';
import { Link } from 'react-router-dom';
import axios from 'axios';

export default function Homepage(){
    const [arrangaments,setArrangaments]=useState([]);
    const [loading, setLoading] = useState(true);
    const [populate,setPopulate]=useState("false");


    const initAll=(data)=>{
        let some=data;
        setPopulate(data);
    }

    
    useEffect(() => {
          const interval = setInterval(() => {
            axios.get('http://localhost:8080/arrangements/flag')
            .then((response)=>response.data)
            .then((data)=>(
                initAll(data.name)))
            console.log("pozvan");
          }, 6000);
          return () => clearInterval(interval);
    }, []);

    useEffect(()=>{
        findHotArr();
    },[]);

    const findHotArr=()=>{
        axios.get("http://localhost:8080/arrangements/hot")
            .then((res)=>res.data)
            .then((data)=>{
                setArrangaments(data);
                setLoading(false);
        });
    };

    

    return(
        <>
       
            <div className='py-5' style={{backgroundColor: 'floralwhite'}}>
                <div className='container' >
                    <div className='row' >
                        <div className='col-md-6 offset-md-3 my-5'>
                            <h1 className='text-center mt-4 ' style={{color:'navy'}}>Doživite svet, jedno po jedno putovanje!</h1>
                        </div>
                    </div>
                    <div className='row py-5 my-0 justify-content-center'>
                        <Link className="btn btn-primary" type="button" to="/search" style={{width:"200px"}}>Pretraži</Link>
                    </div>
                </div>
            </div>
            <div className='container my-5'>
            {loading?<div>
                        <div className="d-flex justify-content-center m-5 p-5">
                            <div className="spinner-border" role="status">
                                <span className="visually-hidden">Loading...</span>
                            </div>
                        </div>
                    </div>:<Cards content={arrangaments}/>}
            </div>
            
       
      
        </>
    );
}