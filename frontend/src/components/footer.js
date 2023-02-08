import React from "react";

export default function Footer(){
    return(
        <div className="text-center text-black sticky-bottom" style={{backgroundColor:'gray'}}>
        <div className="container py-2">
          <div className="row">
            <div className="col-md-3">
              <h6 className="text-uppercase fw-bold mb-4">Contacts:</h6>
            </div>
            <div className="col-md-3">
              <p><i className="fa fa-home text-dark"></i> KRAGUJEVAC, KG 34000, SRB</p>
            </div>
            <div className="col-md-3">
              <p><i className="fa fa-envelope text-dark"></i> danilostevanovic53@gmail.com</p>
            </div>
            <div className="col-md-3">
              <p><i className="fa fa-phone text-dark"></i> + 381 64 996 88 52</p>
            </div>
          </div>
        </div>
        <div className="container p-0">
          <section className="m-0 p-0">
            <a
              className="btn btn-link btn-lg text-dark m-1"
              href="#!"
              role="button"
              data-mdb-ripple-color="dark"
              ><i className="fa fa-facebook-f"></i>
              </a>
            <a
              className="btn btn-link btn-lg text-dark m-1"
              href="#!"
              role="button"
              data-mdb-ripple-color="dark"
              ><i className="fa fa-twitter"></i>
              </a>
            <a
              className="btn btn-link btn-lg text-dark m-1"
              href="#!"
              role="button"
              data-mdb-ripple-color="dark"
              ><i className="fa fa-google"></i>
              </a>
            <a
              className="btn btn-link btn-lg text-dark m-1"
              href="#!"
              role="button"
              data-mdb-ripple-color="dark"
              ><i className="fa fa-instagram"></i>
              </a>
            <a
              className="btn btn-link btn-lg text-dark m-1"
              href="#!"
              role="button"
              data-mdb-ripple-color="dark"
              ><i className="fa fa-linkedin"></i>
              </a>
            <a
              className="btn btn-link btn-lg text-dark m-1"
              href="#!"
              role="button"
              data-mdb-ripple-color="dark"
              ><i className="fa fa-github"></i>
            </a>
          </section>
        </div>
        <hr className="py-0 my-0"></hr>
        <div className="text-center p-1">
          © 2020 Copyright PenguinTours - SVA PRAVA ZADRZANA
        </div>
      </div> 
    );
}