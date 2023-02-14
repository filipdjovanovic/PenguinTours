import axios from "axios";
import React, { useEffect, useState } from "react";
import Stafflist from "./stafflist";


export default function Staffwork(){
    const [all,setAll]=useState([]);
    const [username,setUsername]=useState("");
    const [password,setPassword]=useState("");
    const [error, setError] = useState("");
    const [response, setResponse] = useState("");
    const [loading, setLoading] = useState(true);

    let users=[];
    
    const doOnRender=(data)=>{
        users=data;
        setAll(users);
        setLoading(false);
        console.log(all);
    }

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
        const response=await axios.post('http://localhost:8080/auth/register', { username, password });
        setResponse("accepted");
        setError("");
        getUsers();
        } catch (error) {
        setError(error.response.data);
        setResponse("");
        }
    };

    const removeFromArray = (id) => {
        setAll(all.filter((item) => item.id !== id));
        setLoading(false);
    };



    const getUsers=async()=>{
        await axios.get('http://localhost:8080/users/all')
        .then((response) => response.data)
        .then((data) => {
            doOnRender(data);
            console.log(data);
            console.log(users);
        }
        )};
 

    React.useEffect(() => {
        getUsers();
    },[]);

    const clearAll=()=>{
        setUsername("");
        setPassword("");
    };

    return(
        <>
        <div className="row justify-content-center">
            <form className="col-md-5" onSubmit={handleSubmit}>
                <div className="row justify-content-center">
                    <h5 className="text-center text break">Unesi novog zaposlenog</h5>
                </div>
                <div className="row justify-content-center text-center">
                <label className="form-label my-1" htmlFor="username">Korisničko ime:</label>
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
                        <p className="text-center text-break">{error && <span className="error" style={{color:"red"}}>Nije moguće uneti zaposlenog<br></br>Korisničko ime već postoji</span>}</p>
                    </div>
                </div>
                <div className="row my-2">
                    <div className="col-md-12">
                        <p className="text-center text-break">{response && <span  style={{color:"green"}}>Korisnik je unet!</span>}</p>
                    </div>
                </div>
            </form>  
        </div>
        <div className="row justify-content-center" style={{border: 'solid',borderColor:'navy',borderRadius:'20px'}}>
        {loading ? (
                    <div>
                        <div className="d-flex justify-content-center m-5 p-5">
                            <div className="spinner-border" role="status">
                                <span className="visually-hidden">Loading...</span>
                            </div>
                        </div>
                    </div>
                    ) : <div>{all && all.map((item) => (
                                <Stafflist username={item.username} key={item.id} id={item.id} updateDelete={removeFromArray} loadingRes={setLoading}/>
                                ))}</div>}
        </div>
        </>
    );
}