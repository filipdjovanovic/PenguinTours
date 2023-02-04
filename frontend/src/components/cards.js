import React, { useEffect, useState } from "react";
import {Link} from 'react-router-dom'

export default function Cardview(){
    const [data,setData]=useState([])

    useEffect(()=>{
        fetch("http://localhost:8080/arrangements/hot")
        .then(res=>res.json())
        .then((result)=>{
            setData(result);
        })
    },[])
    return(
        <div className="row my-3 mx-3">
            <div className="col-md-4">
                <Link className="card" style={{width: '18rem',textDecoration: 'none' }} to='/arrangamentview'>
                    <img src="..." className="card-img-top" alt="Slika"></img>
                    <div className="card-body">
                        <h5 className="card-title" style={{ color: 'black' }}>Card title</h5>
                        <p className="card-text my-0" style={{ color: 'black' }}>Some quick example </p>
                        <p className="card-text my-0" style={{ color: 'black' }}>Some quick example </p>
                        <p className="card-text my-0" style={{ color: 'black' }}>Some quick example </p>
                        <p className="card-text my-0" style={{ color: 'black' }}>Some quick example </p>
                    </div>
                </Link>
            </div>
            <div className="col-md-4">
                <Link className="card" style={{width: '18rem', textDecoration: 'none' }} to='/arrangamentview'>
                    <img src="..." className="card-img-top" alt="Slika"></img>
                    <div className="card-body">
                        <h5 className="card-title" style={{ color: 'black' }}>Card title</h5>
                        <p className="card-text my-0" style={{ color: 'black' }}>Some quick example </p>
                        <p className="card-text my-0" style={{ color: 'black' }}>Some quick example </p>
                        <p className="card-text my-0" style={{ color: 'black' }}>Some quick example </p>
                        <p className="card-text my-0" style={{ color: 'black' }}>Some quick example </p>
                    </div>
                </Link>
            </div>
            <div className="col-md-4">
                <Link className="card" style={{width: '18rem',textDecoration: 'none' }} to='/arrangamentview'>
                    <img src="..." className="card-img-top" alt="Slika"></img>
                    <div className="card-body">
                        <h5 className="card-title" style={{ color: 'black' }}>Card title</h5>
                        <p className="card-text  my-0" style={{ color: 'black' }}>Some quick example </p>
                        <p className="card-text my-0" style={{ color: 'black' }}>Some quick example </p>
                        <p className="card-text my-0" style={{ color: 'black' }}>Some quick example </p>
                        <p className="card-text my-0" style={{ color: 'black' }}>Some quick example </p>
                    </div>
                </Link>
            </div>
        </div>
    );
}