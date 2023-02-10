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
import Updatearr from './pages/updatearr';

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

function App() {
  return (
    <Router>
      <Routes>
        <Route  path='/' element={<Userlayout />}>
          <Route index element={<Homepage />} />
          <Route path='aboutus' element={<Aboutuspage />} />
          <Route path='contacts' element={<Contactspage />} />
          <Route path='search' element={<Searchpage />} />
          <Route path='login' element={<Loginpage />} />
          <Route path='arrangamentview/:id' element={<Arrview />} />
        </Route>
        <Route path='/admin' element={<Adminlayout />}>
          <Route index element={<Adminpage />} />
          <Route path='aboutus' element={<Aboutuspage />} />
          <Route path='contacts' element={<Contactspage />} />
          <Route path='search' element={<Searchpage />} />
          <Route path='login' element={<Loginpage />} />
          <Route path='search/arrangamentview/:id' element={<Arrview />} />
          <Route path='arrangamentupdate/:id' element={<Updatearr />} />
        </Route>
      </Routes>
    </Router>
  );
}

export default App;
