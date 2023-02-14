import React from "react";
import "react-responsive-carousel/lib/styles/carousel.min.css"; 
import { Carousel } from 'react-responsive-carousel';
import '../css/mystyle.css'

export default function Accomodationview(props){
    return(
        <>
        <div className="row my-1" >
            <div className="row my-2 py-2 justify-content-center" style={{backgroundColor:'whitesmoke'}}>
                <div className="col-md-12  text-center ">
                    <b>{props.view.name}</b>
                </div>
            </div>
            <div className="row my-2 py-2 justify-content-center" style={{borderLeftStyle:'solid',borderRightStyle:'solid',borderColor:'navy'}}>
                <div className="col-md-4" >
                    <div className="row p-3">
                        <div className="col-md-12">
                            <strong>Kategorija:</strong> {props.view.category}
                        </div>
                    </div>
                    <div className="row p-3">
                        <div className="col-md-12">
                            <strong>Tip smeštaja u sobi:</strong> {props.view.type}
                        </div>
                    </div>
                    <div className="row p-3">
                        <div className="col-md-12">
                            <strong>Kategorija smeštaja:</strong>  {(props.view.category)}
                        </div>
                    </div>
                    <div className="row p-3">
                        <div className="col-md-12">
                            <strong>TV:</strong> {(props.view.tv)?<span>DA!</span>:<span>NE!</span>}
                        </div>
                    </div>
                    <div className="row p-3">
                        <div className="col-md-12">
                            <strong>Sef:</strong> {(props.view.safe)?<span>DA!</span>:<span>NE!</span>}
                        </div>
                    </div>
                    <div className="row p-3">
                        <div className="col-md-12">
                            <strong>Frižider:</strong> {(props.view.fridge)?<span>DA!</span>:<span>NE!</span>}
                        </div>
                    </div>
                    <div className="row p-3">
                        <div className="col-md-12">
                            <strong>Klima:</strong> {(props.view.ac)?<span>DA!</span>:<span>NE!</span>}
                        </div>
                    </div>
                    <div className="row p-3">
                        <div className="col-md-12">
                            <strong>Internet:</strong> {(props.view.internet)?<span>DA!</span>:<span>NE!</span>}
                        </div>
                    </div>
                </div>
                <div className="col-md-6">
                    <Carousel >
                        <div>
                            <img src={((props.view.picture1===null)?"":"data:image/jpeg;base64,"+props.view.picture1)} alt='' style={{width:'100%', height:'100%',objectFit:'cover'}}/>
                        </div>
                        <div>
                            <img src={((props.view.picture2===null)?"":"data:image/jpeg;base64,"+props.view.picture2)} alt='' />
                        </div>
                        <div>
                            <img src={((props.view.picture3===null)?"":"data:image/jpeg;base64,"+props.view.picture3)} alt=''/>
                        </div>
                        <div>
                            <img src={((props.view.picture4===null)?"":"data:image/jpeg;base64,"+props.view.picture4)} alt=''/>
                        </div>
                        <div>
                            <img src={((props.view.picture5===null)?"":"data:image/jpeg;base64,"+props.view.picture5)} alt=''/>
                        </div>
                        <div>
                            <img src={((props.view.picture6===null)?"":"data:image/jpeg;base64,"+props.view.picture6)} alt=''/>
                        </div>
                    </Carousel>
                </div>
            </div>
        </div>
        </>

    );
}