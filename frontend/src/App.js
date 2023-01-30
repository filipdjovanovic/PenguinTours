import './App.css';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css'
import Navheader from './components/header';
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom'
import Homepage from './pages/homepage';
import About from './pages/about';
import Contacts from './pages/contacts';
import Addarrangament from './components/addarr';

function App() {
  return (
    <Router>
      <Navheader/>

      <Routes>
        <Route exact path='/' element={<Homepage />} />
        <Route exact path='/about' element={<About />} />
        <Route exact path='/contacts' element={<Contacts />} />
        <Route exact path='/arrangement/add' element={<Addarrangament />} />
      </Routes>
    </Router>
  );
}

export default App;
