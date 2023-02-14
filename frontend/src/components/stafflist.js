import axios from "axios";
import React from "react";

export default function Stafflist(props){

    const doAll=(id)=>{
        deleteUser();
        props.updateDelete(id);
        props.loadingRes(false);
    };

    const deleteUser=()=>{
        axios.delete(`http://localhost:8080/users/delete?id=${props.id}`)  
        .then(res => {  
            console.log(res);  
            console.log(res.data);  
        })
    };

    return(
        <div className="row m-2 p-1 align-items-center" style={{borderTop:"solid",borderBottom:"solid",borderColor:"ThreeDShadow"}} >
                <div className="col-md-3 ">
                    <p className="text-center text-break my-2">Korisničko ime: {props.username}</p>
                </div>
                <div className="col-md-9">
                    <div className="row justify-content-end">
                        <div className="col-md-3">
                            <button className="btn btn-danger" onClick={()=>doAll(props.id)}>Izbriši</button>
                        </div>
                    </div>
                </div>
        </div>
    );
}