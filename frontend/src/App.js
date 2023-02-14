import './App.css';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css'
import Navheader from './components/header';
import {BrowserRouter as Router, Outlet, Route, Routes} from 'react-router-dom'
import Homepage from './pages/homepage';
import Contactspage from './pages/contacts';
import Searchpage from './pages/search';
import Aboutuspage from './pages/about';
import Loginpage from './pages/login';
import Navheaderad from './components/headerad';
import Adminpage from './pages/adminpage';
import Footer from './components/footer';
import Arrview from './pages/arrview';
import Updatearr from './components/updatearr';
import Arrapply from './components/arrapply';
import ProtectedRoutes from './routes.js/protected';
import ProtectedRoutesS from './routes.js/protectedstaff';
import Staffpage from './pages/staffpage';
import { useEffect, useState } from 'react';
import axios from 'axios';


function Userlayout(){
  return(
    <>
    <Navheader />
    <Outlet />
    <Footer />
    </>
  );
}
function Adminlayout(){
  return(
    <>
    <Navheaderad />
    <Outlet />
    <Footer />
    </>
  );
}

function Stafflayout(){
  return(
    <>
    <Navheaderad />
    <Outlet />
    <Footer />
    </>
  );
}

function App() {
  const [populate,setPopulate]=useState("");


    const initAll=(data)=>{
        let some=data;
        setPopulate(data);
    }

    
    useEffect(() => {
          const interval = setInterval(() => {
            axios.get('http://localhost:8080/arrangements/flag')
            .then((response)=>response.data)
            .then((data)=>{
              if (data.name=="true") {
                clearInterval(interval);
                initAll(data.name);
              } else {
                initAll(data.name);
              }
            });
          }, 600);
          return () => clearInterval(interval);
    }, []);
  return (
    <>
    
      {populate==="false"?<div className='row justify-content-center align-items-center text-center my-5 py-5'>
                            <div className='row text-center'>
                              <h1>Inicijalizacija je u toku</h1>
                            </div>
                            <div className="row d-flex justify-content-center m-5 p-5">
                                  <div className="spinner-border" role="status">
                                      <span className="visually-hidden">Loading...</span>
                                  </div>
                            </div>
                          </div>:
    
    <Router>
      <Routes>
        <Route  path='/' element={<Userlayout />}>
          <Route index element={<Homepage />} />
          <Route path='aboutus' element={<Aboutuspage />} />
          <Route path='contacts' element={<Contactspage />} />
          <Route path='search' element={<Searchpage />} />
          <Route path='login' element={<Loginpage />} />
          <Route path='search/arrangamentview/:id' element={<Arrview />} />
          <Route path='arrangamentview/:id' element={<Arrview />} />
          <Route path='arrangamentview/:id/arrangament/apply/:id' element={<Arrapply />} />
          <Route path='search/arrangamentview/:id/arrangament/apply/:id' element={<Arrapply />} />
        </Route>
        <Route  element={<ProtectedRoutes />}>
          <Route path='/admin' element={<Adminlayout />}>
            <Route index element={<Adminpage />} />
            <Route path='aboutus' element={<Aboutuspage />} />
            <Route path='contacts' element={<Contactspage />} />
            <Route path='search' element={<Searchpage />} />
            <Route path='login' element={<Loginpage />} />
            <Route path='search/arrangamentview/:id' element={<Arrview />} />
            <Route path='arrangamentupdate/:id' element={<Updatearr />} />
            <Route path='search/arrangamentview/:id/arrangament/apply/:id' element={<Arrapply />} />
          </Route>  
        </Route>
        <Route   element={<ProtectedRoutesS />}>
          <Route path='/staff' element={<Stafflayout />}>
            <Route index element={<Staffpage />} />
            <Route path='aboutus' element={<Aboutuspage />} />
            <Route path='contacts' element={<Contactspage />} />
            <Route path='search' element={<Searchpage />} />
            <Route path='login' element={<Loginpage />} />
            <Route path='search/arrangamentview/:id' element={<Arrview />} />
            <Route path='arrangamentupdate/:id' element={<Updatearr />} />
            <Route path='search/arrangamentview/:id/arrangament/apply/:id' element={<Arrapply />} />
          </Route>
        </Route>
      </Routes>
    </Router> }
    </>
  );
}

export default App;
