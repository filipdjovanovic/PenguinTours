import axios from "axios";
import React, { useEffect, useState } from "react";

export default function Accinput(props){
    const [accomodation,setAccomodation]=useState({
        name:"",
        category:5,
        type:"",
        tv:true,
        safe:true,
        fridge:true,
        ac:true,
        internet:true,
        picture1:null,
        picture2:null,
        picture3:null,
        picture4:null,
        picture5:null,
        picture6:null
    });
    /*
    const [bytesmore,setBytes]=useState({
        name:"Ivaneeee",
        myFile: "",
    });
    
    const [image,setImage]=useState("");
    const handleFileUpload = async (e) => {
        const file = e.target.files[0];
        const base64 = await convertToBase64(file);
        setBytes(previousData=>{
            return{...previousData,myFile:base64}
        })
    };*/

    const handleFile1Upload = async (e) => {
        const file = e.target.files[0];
        const base64 = await convertToBase64(file);
        updatePiture1(base64);
    };

    const handleFile2Upload = async (e) => {
        const file = e.target.files[0];
        const base64 = await convertToBase64(file);
        updatePiture2(base64)
    };

    const handleFile3Upload = async (e) => {
        const file = e.target.files[0];
        const base64 = await convertToBase64(file);
        updatePiture3(base64)
    };

    const handleFile4Upload = async (e) => {
        const file = e.target.files[0];
        const base64 = await convertToBase64(file);
        updatePiture4(base64)
    };

    const handleFile5Upload = async (e) => {
        const file = e.target.files[0];
        const base64 = await convertToBase64(file);
        updatePiture5(base64)
    };

    const handleFile6Upload = async (e) => {
        const file = e.target.files[0];
        const base64 = await convertToBase64(file);
        updatePiture6(base64)
    };

    const handleUpdate = () => {
        const updatedArray = [...props.accarr.accomodations, accomodation];
        props.sendAcc(updatedArray);
        setAccomodation({
            name:"",
            category:5,
            type:"",
            tv:true,
            safe:true,
            fridge:true,
            ac:true,
            internet:true,
            picture1:null,
            picture2:null,
            picture3:null,
            picture4:null,
            picture5:null,
            picture6:null
      });
    }

    const handleRemove = (item) => {
        props.removeFromArray(item);
    };
/*
    const getImage=()=>{
        const fetchData = async () => {
        const result= await axios.get("http://localhost:8080/arrangements/image/get");
        setImage("data:image/jpeg;base64,"+result.data.myFile);
        console.log(result.data);
        }
        fetchData()
    };

    useEffect(()=>{
        getImage()
    },[]);

    const url = "http://localhost:8080/arrangements/image/upload";
    const createImage = (newImage) => axios.post(url, newImage);

    const createPost = async (post) => {
        try {
          await createImage(post);
        } catch (error) {
          console.log(error.message);
        }
      };

    const handleSend = (e) => {
        e.preventDefault();
        createPost(bytesmore);
    };*/

    const convertToBase64 = (file) => {
        return new Promise((resolve, reject) => {
          const fileReader = new FileReader();
          fileReader.readAsDataURL(file);
          fileReader.onload = () => {
            resolve(fileReader.result);
          };
          fileReader.onerror = (error) => {
            reject(error);
          };
        });
    };

    const updateName=(e)=>{
        setAccomodation(previousData=>{
            return{...previousData,name:(e.target.value)}
        });
    }

    const updatePiture1=(item)=>{
        setAccomodation(previousData=>{
            return{...previousData,picture1:item}
        });
    }

    const updatePiture2=(item)=>{
        setAccomodation(previousData=>{
            return{...previousData,picture2:item}
        });
    }

    const updatePiture3=(item)=>{
        setAccomodation(previousData=>{
            return{...previousData,picture3:item}
        });
    }

    const updatePiture4=(item)=>{
        setAccomodation(previousData=>{
            return{...previousData,picture4:item}
        });
    }

    const updatePiture5=(item)=>{
        setAccomodation(previousData=>{
            return{...previousData,picture5:item}
        });
    }

    const updatePiture6=(item)=>{
        setAccomodation(previousData=>{
            return{...previousData,picture6:item}
        });
    }

    const updateCategory=(e)=>{
        setAccomodation(previousData=>{
            return{...previousData,category:Number(e.target.value)}
        });
    }
    const updateType=(e)=>{
        setAccomodation(previousData=>{
            return{...previousData,type:(e.target.value)}
        });
    }
    const updateTv=(e)=>{
        setAccomodation(previousData=>{
            return{...previousData,tv:((e.target.value)==="true"?true:false)}
        });
    }
    const updateSafe=(e)=>{
        setAccomodation(previousData=>{
            return{...previousData,safe:((e.target.value)==="true"?true:false)}
        });
    }
    const updateFrifge=(e)=>{
        setAccomodation(previousData=>{
            return{...previousData,fridge:((e.target.value)==="true"?true:false)}
        });
    }
    const updateAc=(e)=>{
        setAccomodation(previousData=>{
            return{...previousData,ac:((e.target.value)==="true"?true:false)}
        });
    }
    const updateInternet=(e)=>{
        setAccomodation(previousData=>{
            return{...previousData,internet:((e.target.value)==="true"?true:false)}
        });
    }
    return(
        <>
        <div className="row mt-5 mb-2 p-2" style={{borderTop:"solid"}}>
            <div className="row justify-content-center">
                <div className="col-md-5 text-center">
                    <h4>Unesi smestaje</h4>
                </div>
            </div>
            <div className="col-md-5">
                <label className="form-label my-1" htmlFor="name">Naziv smestajnog objekta:</label>
                <input className="form-control" 
                    type="text" 
                    id="name" 
                    name="name"
                    value={accomodation.name}
                    onChange={updateName}
                    style={{borderRadius:'20px'}}></input>
                <label className="form-label my-1" htmlFor="category">Kategorija smestaja:</label>
                <br></br>
                <select className="text-wrap text-center" value={accomodation.category} id="category" name="category" onChange={updateCategory} style={{borderRadius:'20px',height:"30px",width:"50px"}} required>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option defaultValue={"5"} value="5">5</option>
                </select>
                <br></br>
                <label htmlFor="type" className="form-label my-1">Tip smestaja u sobi:</label>
                <br></br>
                <select className="text-wrap text-center" value={accomodation.type} id="type" name="type" onChange={updateType} style={{borderRadius:'20px',height:"30px"}} required>
                    <option value="1/1">1/1</option>
                    <option value="1/2">1/2</option>
                    <option value="1/3">1/3</option>
                    <option value="1/2+1">1/2+1</option>
                    <option value="1/3+1">1/3+1</option>
                    <option defaultValue={"1/4"} value="1/4">1/4</option>
                </select>
                <div className="row">
                    <div className="col-md-4">
                        <label htmlFor="internet" className="form-label my-1">Internet:</label>
                        <br></br>
                        <select className="text-wrap text-center" value={accomodation.internet} id="internet" name="internet" onChange={updateInternet} style={{borderRadius:'20px',height:"30px",width:"75px"}} required>
                            <option defaultValue={true} value={true}>Da</option>
                            <option value={false}>Ne</option>
                        </select>
                    </div>
                    <div className="col-md-4">
                        <label htmlFor="tv" className="form-label my-1">TV:</label>
                        <br></br>
                        <select className="text-wrap text-center" value={accomodation.tv} id="tv" name="tv" onChange={updateTv} style={{borderRadius:'20px',height:"30px",width:"75px"}} required>
                            <option defaultValue={true}value={true}>Da</option>
                            <option value={false}>Ne</option>
                        </select>
                    </div>
                    <div className="col-md-4">
                        <label htmlFor="ac" className="form-label my-1">Klima:</label>
                        <br></br>
                        <select className="text-wrap text-center" value={accomodation.ac} id="ac" name="ac" onChange={updateAc} style={{borderRadius:'20px',height:"30px",width:"75px"}} required>
                            <option defaultValue={true} value={true}>Da</option>
                            <option value={false}>Ne</option>
                        </select>
                    </div>
                    <div className="row justify-content-around">
                        <div className="col-md-4">
                            <label htmlFor="fridge" className="form-label my-1">Frizider:</label>
                            <br></br>
                            <select className="text-wrap text-center" value={accomodation.fridge} id="fridge" name="fridge" onChange={updateFrifge} style={{borderRadius:'20px',height:"30px",width:"75px"}} required>
                                <option defaultValue={true} value={true}>Da</option>
                                <option value={false}>Ne</option>
                            </select>
                        </div>
                        <div className="col-md-4">
                        <label htmlFor="safe" className="form-label my-1">Sef:</label>
                        <br></br>
                        <select className="text-wrap text-center" value={accomodation.safe} id="safe" name="safe" onChange={updateSafe} style={{borderRadius:'20px',height:"30px",width:"75px"}} required>
                            <option defaultValue={true}value={true}>Da</option>
                            <option value={false}>Ne</option>
                        </select>
                    </div>
                    </div>
                </div>
            </div>
            <div className="col-md-5 text-center myu-1">
                <label className="form-label">Unesite slike:</label>
                <div className="row justify-content-center mx-5 px-4">
                    <div className="row justify-content-center p-0 m-2">
                        <input type="file"
                            id="picture1"
                            accept=".png,.jpg,.jpeg"
                            onChange={(e) => handleFile1Upload(e)}
                        />
                    </div>
                    <div className="row justify-content-center p-0 m-2">
                        <input type="file"
                            id="picture2"
                            accept=".png,.jpg,.jpeg"
                            onChange={(e) => handleFile2Upload(e)}
                        />
                    </div>
                    <div className="row justify-content-center p-0 m-2">
                        <input type="file"
                            id="picture3"
                            accept=".png,.jpg,.jpeg"
                            onChange={(e) => handleFile3Upload(e)}
                        />
                    </div>
                    <div className="row justify-content-center p-0 m-2">
                        <input type="file"
                            id="picure4"
                            accept=".png,.jpg,.jpeg"
                            onChange={(e) => handleFile4Upload(e)}
                        />
                    </div>
                    <div className="row justify-content-center p-0 m-2">
                        <input type="file"
                            id="picture5"
                            accept=".png,.jpg,.jpeg"
                            onChange={(e) => handleFile5Upload(e)}
                        />
                    </div>
                    <div className="row justify-content-center p-0 m-2">
                        <input type="file"
                            id="picture6"
                            accept=".png,.jpg,.jpeg"
                            onChange={(e) => handleFile6Upload(e)}
                        />
                    </div>
                   {/* <div className="row justify-content-center p-0 m-2">
                        <button type="button" onClick={handleSend}>Salji</button>
                    </div>*
                    <div className="row justify-content-center p-0 m-2">
                        <button type="button" onClick={getImage}>Salji</button>
                    </div>*
                    <img src={image}></img>*/}
                </div>
            </div>
        </div>
        <div className="row justify-cnontent-center my-3">
            <div className="col-md-12">
                <button className="btn btn-primary" type="button" onClick={handleUpdate}  >Unesi smestaj</button>
            </div>
        </div>
        <div className="row">
            {props.accarr.accomodations && props.accarr.accomodations.map((item,)=>(
                <div className="row" key={item.name}>
                    <div className="col-md-6">
                        {item.name} 
                    </div>
                    <div className="col-md-6">
                        <button onClick={() => handleRemove(item)}>Ukloni</button>
                    </div>
                </div>
            ))}
           
        </div>
        </>
        
    );
}

