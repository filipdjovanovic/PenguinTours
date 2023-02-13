import axios from "axios";
import React, { useEffect, useState } from "react";
import { setAuthToken } from "../routes.js/setauth";
import Staffview from "./staffview";


export default function Staffwork(){

    const [username,setUsername]=useState("");
    const [password,setPassword]=useState("");
    const [error, setError] = useState("");
    const [error2, setError2] = useState("");
    const [users,setUsers]=useState([]);


    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
        const response=await axios.post('http://localhost:8080/auth/register', { username, password });
        } catch (error) {
        setError(error.response.data);
        }
    };

    const removeFromArray = (name) => {
        setUsers(users.filter((item) => item.username !== name));
    };

    const getUsers= async (e)=>{
        e.preventDefault();
        try{
            const response=await axios.get("mock");
            setUsers(response.data);
        }catch (error){
            setError2(error.response.data)
        }

    }

    React.useEffect(() => {
            getUsers()
    }, []);

    const clearAll=()=>{
        setUsername("");
        setPassword("");
    };

    /*{users && users.map((user,index)=>(
            <Staffview key={index} name={user.username} password={user.password} updateDelete={removeFromArray}/>
            ))}*/


    

    return(
        <>
        <div className="row justify-content-center">
            <form className="col-md-5" onSubmit={handleSubmit}>
                <div className="row justify-content-center">
                    <h5 className="text-center text break">Unesi novog zaposlenog</h5>
                </div>
                <div className="row justify-content-center text-center">
                <label className="form-label my-1" htmlFor="username">Korisnicko ime:</label>
                    <input className="form-control" 
                        type="text" 
                        id="username" 
                        name="username"
                        value={username}
                        onChange={(e)=>setUsername(e.target.value)}
                        style={{borderRadius:'20px'}}></input>
                </div>
                <div className="row justify-content-center text-center">
                <label className="form-label my-1 " htmlFor="password">Sifra:</label>
                    <input className="form-control" 
                        type="password" 
                        id="password" 
                        name="password"
                        value={password}
                        onChange={(e)=>setPassword(e.target.value)}
                        style={{borderRadius:'20px'}}></input>
                </div>
                <div className="row justify-content-center my-3">
                    <button className="btn btn-primary" style={{width:"130px"}} type="subbmit"> Unesi </button>
                </div>
                <div className="row my-2">
                    <div className="col-md-12">
                        <p className="text-center text-break">{error && <span className="error" style={{color:"red"}}>Nije moguce uneti zaposlenog<br></br>Korisnicko ime vec postoji</span>}</p>
                    </div>
                </div>
            </form>  
        </div>
        <div className="row justify-content-center" style={{border: 'solid',borderColor:'navy',borderRadius:'20px'}}>
            <Staffview /*key={index}*/ name={users.username} password={users.password} updateDelete={removeFromArray}/>
        </div>
        </>
    );
}