import React from "react";
import "react-responsive-carousel/lib/styles/carousel.min.css"; // requires a loader
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
                            <strong>Tip smestaja:</strong> {props.view.type}
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
                            <strong>Frizider:</strong> {(props.view.fridge)?<span>DA!</span>:<span>NE!</span>}
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
                    <div className="row p-3">
                        <div className="col-md-12">
                            <strong>Lokacija:</strong> {props.view.location.city},{props.view.location.country}
                        </div>
                    </div>
                </div>
                <div className="col-md-6">
                    <Carousel >
                        <div>
                            <img src={require('../slike/pozadina1.jpg')} alt='' style={{width:'100%', height:'100%',objectFit:'cover'}}/>
                        </div>
                        <div>
                            <img src={require('../slike/ikonica.png')} alt='' />
                        </div>
                        <div>
                            <img src={require('../slike/favicon.png')} alt=''/>
                        </div>
                    </Carousel>
                </div>
            </div>
            
            {/*
            <div className="row justify-content-center py-3">
                <div className="col-md-4 ">
                    <div className="row justify-content-center mx-1" style={{backgroundColor:'white'}}>
                        Kategorija:
                    </div>
                    <div className="row justify-content-center">
                        {props.view.category}
                    </div>
                </div>
                <div className="col-md-4 ">
                    <div className="row justify-content-center mx-1" style={{backgroundColor:'white'}}>
                        Tip smestaja:
                    </div>
                    <div className="row justify-content-center">
                        {props.view.type}
                    </div>
                </div>
            </div>
            <div className="row justify-content-center py-3">
                <div className="col-md-2 ">
                    <div className="row justify-content-center mx-1" style={{backgroundColor:'white'}}>
                        TV:
                    </div>
                    <div className="row justify-content-center text-center">
                        {(props.view.tv)?<div>DA!</div>:<div>NE!</div>}
                    </div>
                </div>
                <div className="col-md-2 ">
                    <div className="row justify-content-center mx-1" style={{backgroundColor:'white'}}>
                        Sef:
                    </div>
                    <div className="row justify-content-center text-center">
                        {(props.view.safe)?<div>DA!</div>:<div>NE!</div>}
                    </div>
                </div>
                <div className="col-md-2 ">
                    <div className="row justify-content-center mx-1" style={{backgroundColor:'white'}}>
                        Frizider:
                    </div>
                    <div className="row justify-content-center text-center">
                        {(props.view.fridge)?<div>DA!</div>:<div>NE!</div>}
                    </div>
                </div>
                <div className="col-md-2 ">
                    <div className="row justify-content-center mx-1" style={{backgroundColor:'white'}}>
                        Klima:
                    </div>
                    <div className="row justify-content-center text-center">
                        {(props.view.ac)?<div>DA!</div>:<div>NE!</div>}
                    </div>
                </div>
                <div className="col-md-2 ">
                    <div className="row justify-content-center mx-1" style={{backgroundColor:'white'}}>
                        Internet:
                    </div>
                    <div className="row justify-content-center text-center">
                        {(props.view.internet)?<div>DA!</div>:<div>NE!</div>}
                    </div>
                </div>
                <div className="row justify-content-center py-3">
                    <div className="col-md-4 ">
                    <div className="row justify-content-center mx-1" style={{backgroundColor:'white'}}>
                        Lokacija:
                    </div>
                    <div className="row justify-content-center">
                        {props.view.location.city},{props.view.location.country}
                    </div>
                </div>
                </div>
            </div>*/}
        </div>
        </>

    );
}