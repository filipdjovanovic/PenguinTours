import axios from "axios";
import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";


export default function Updatearr(){

    const [arrangament,setArrangament]=useState([])
    const {id}= useParams();

    useEffect(() => {
        const fetchData = async () => {
          const result = await axios.get("http://localhost:8080/arrangements/get?id=" + id);
          setArrangament(result.data);
        };
    
        fetchData();
    }, [id]);

    return(
        <h1>{arrangament.name}</h1>
    );
}