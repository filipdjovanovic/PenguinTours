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
  return (
    <>
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
    </Router> 
    </>
  );
}

export default App;
