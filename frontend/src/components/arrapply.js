import axios from "axios";
import React, { useState } from "react";
import { useParams } from "react-router-dom";

export default function Arrapply(){
    const [application,setApplication]=useState({
        numberOfAdults:0,
        numberOfKids:0,
        paymentMethod:"",
        comment:"",
        person:{
            name:"",
            lastName:"",
            email:"",
            contact:"",
            address:""
        }
    });
    const {id}= useParams();
    const [error, setError] = useState("");
    const [success,setSuccess]=useState("")
    const [errorEmpty,setErrorEmpty]=useState("")


    const sendApplication=()=>{
        handleClick();
        cancelSearch();
    };

    const checkSearch=()=>{
        let isEmpty = ((application.numberOfAdults!== 0 || application.numberOfKids!==0) && application.paymentMethod!== "" && 
        application.comment!=="" && application.person.name !== "" && application.person.lastName !== "" 
        && application.person.email !== "" && application.person.contact !== "" && application.person.address !== "");
        return isEmpty;
    }

    const handleClick = () => {
        if (!Object.keys(application).length) {
            console.error('State cannot be empty');
            return;
          }
        
        
        
        if (checkSearch()){
            try{
                axios.post('http://localhost:8080/reservation/add?arrangement_id='+id, application)
                  .then(response => {
                    setSuccess(response.data);
                    setErrorEmpty("")
                  })
                }catch(error)  {
                    setError(error);
                  }
        } else{
            setErrorEmpty("error");
            setSuccess("");
        }
        
      };

    const cancelSearch=()=>{
        setApplication({
            numberOfAdults:0,
            numberOfKids:0,
            paymentMethod:"",
            comment:"",
            person:{
                name:"",
                lastName:"",
                email:"",
                contact:"",
                address:""
        }
        });
    };


    const updateAdults=(e)=>{
        setApplication(previousData=>{
            return{...previousData,numberOfAdults:Number(e.target.value)}
        })
    }
    const updateKids=(e)=>{
        setApplication(previousData=>{
            return{...previousData,numberOfKids:Number(e.target.value)}
        })
    }
    const updatePayment=(e)=>{
        setApplication(previousData=>{
            return{...previousData,paymentMethod:(e.target.value)}
        })
    }
    const updateComent=(e)=>{
        setApplication(previousData=>{
            return{...previousData,comment:(e.target.value)}
        })
    }

    const updateName=(e)=>{
        setApplication(previousData=>({...previousData,person:{...previousData.person,name:(e.target.value)}}));
    }
    const updateLastname=(e)=>{
        setApplication(previousData=>({...previousData,person:{...previousData.person,lastName:(e.target.value)}}));
    }
    const updateEmail=(e)=>{
        setApplication(previousData=>({...previousData,person:{...previousData.person,email:(e.target.value)}}));
    }
    const updateContact=(e)=>{
        setApplication(previousData=>({...previousData,person:{...previousData.person,contact:(e.target.value)}}));
    }
    const updateAddress=(e)=>{
        setApplication(previousData=>({...previousData,person:{...previousData.person,address:(e.target.value)}}));
    }
    return(
        <>
        <div className="container" style={{display:"flex",flexDirection:"column"}}>
            <div className="row justify-content-center mt-5 ">
                <div className="col-md-4 text-center">
                    <h2 className="text-break text-wrap">Prijava</h2>
                </div>
            </div>
            <div className="row my-3 " style={{borderTop:"solid",borderColor:"navy"}}>
                <div className="col-md-4 mx-5 mt-3">
                    <div className="row">
                        <label className="form-label my-1" htmlFor="name">Ime:</label>
                        <input className="form-control" 
                            type="text" 
                            id="name" 
                            name="name"
                            value={application.person.name}
                            onChange={updateName}
                            style={{borderRadius:'20px'}}></input>
                    </div>
                    <div className="row">
                        <label className="form-label my-1" htmlFor="lastName">Prezime:</label>
                        <input className="form-control" 
                            type="text" 
                            id="lastName" 
                            name="lastName"
                            value={application.person.lastName}
                            onChange={updateLastname}
                            style={{borderRadius:'20px'}}></input>
                    </div>
                    <div className="row">
                        <label className="form-label my-1" htmlFor="email">E-mail:</label>
                        <input className="form-control" 
                            type="text" 
                            id="email" 
                            name="email"
                            value={application.person.email}
                            onChange={updateEmail}
                            style={{borderRadius:'20px'}}></input>
                    </div>
                    <div className="row">
                        <label className="form-label my-1" htmlFor="contact">Kontakt telefon:</label>
                        <input className="form-control" 
                            type="text" 
                            id="contact" 
                            name="contact"
                            value={application.person.contact}
                            onChange={updateContact}
                            style={{borderRadius:'20px'}}></input>
                    </div>
                    <div className="row">
                        <label className="form-label my-1" htmlFor="address">Adresa:</label>
                        <input className="form-control" 
                            type="text" 
                            id="address" 
                            name="address"
                            value={application.person.address}
                            onChange={updateAddress}
                            style={{borderRadius:'20px'}}></input>
                    </div>
                    <div className="row">
                        <label className="form-label my-1" htmlFor="paymentMethod">Način placanja:</label>
                        <input className="form-control" 
                            type="text" 
                            id="paymentMethod" 
                            name="paymentMethod"
                            value={application.paymentMethod}
                            onChange={updatePayment}
                            style={{borderRadius:'20px'}}></input>
                    </div>
                    <div className="row">
                        <div className="col-md-6">
                            <label htmlFor="numberOfAdults" className="form-label my-1">Broj odraslih:</label>
                            <input 
                                type="number" 
                                className="form-control"  
                                placeholder="..."  
                                name="numberOfAdults"
                                value={application.numberOfAdults}
                                onChange={updateAdults} 
                                style={{borderRadius:'20px',width:'120px'}}
                                required></input>
                        </div>
                        <div className="col-md-6">
                            <label htmlFor="numberOfKids" className="form-label my-1">Broj dece:</label>
                            <input 
                                type="number" 
                                className="form-control"  
                                placeholder="..."  
                                name="numberOfKids"
                                value={application.numberOfKids}
                                onChange={updateKids} 
                                style={{borderRadius:'20px',width:'120px'}}
                                required></input>
                        </div>
                    </div>
                </div>
                <div className="col-md-6 mx-5 mt-3">
                    <div className="row">
                        <label htmlFor="comment" className="form-label my-1">Komentar:</label>
                        <br></br>
                        <textarea value={application.comment} name="comment" onChange={updateComent} rows={5} cols={10} style={{borderRadius:'10px',height:"180px",width:"400px"}} required/>
                    </div>
                </div>
            </div>
            <div className="row justify-content-center mt-3 mb-5">
                <div className="col-md-3">
                    <button className="btn btn-primary" type="button" onClick={sendApplication} style={{width:"100%"}}>Prijavi se!</button>
                </div>
            </div>
            <div className="row my-2">
                <div className="col-md-12">
                    <p className="text-center text-break">{errorEmpty && <span className="error" style={{color:"red"}}>Molimo Vas popunite sva polja</span>}</p>
                </div>
            </div>
            <div className="row my-2">
                <div className="col-md-12">
                    <p className="text-center text-break">{success && <span  style={{color:"green"}}>Rezervacija je poslata</span>}</p>
                </div>
            </div>
            <div className="row my-2">
                <div className="col-md-12">
                    <p className="text-center text-break">{error && <span className="error" style={{color:"red"}}>Pogrešno korisničko ime ili lozinka</span>}</p>
                </div>
            </div>
        </div>
        </>
    );
}