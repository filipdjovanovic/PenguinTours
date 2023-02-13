import React from "react";

export default function Staffview(props){
    return(
        <div className="row m-2 p-1 align-items-center" style={{borderTop:"solid",borderBottom:"solid",borderColor:"ThreeDShadow"}}>
            <div className="col-md-3 ">
                <p className="text-center text-break my-2">Korisnicko ime:{props.name}</p>
            </div>
            <div className="col-md-3 ">
                <p className="text-center text-break my-2">Sifra:{props.password}</p>
            </div>
            <div className="col-md-6">
                <div className="row justify-content-end">
                    <div className="col-md-3">
                        <button className="btn btn-danger" onClick={props.updateDelete(props.name)}>Izbrisi</button>
                    </div>
                </div>
            </div>
        </div>
    );
}