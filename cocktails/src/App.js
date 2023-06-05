import logo from './logo.svg';
import './App.css';
import Button from '@mui/material/Button';
import Stack from '@mui/material/Stack';
import Navbar from './components/NavBar';
import CocktailCard from './components/CocktailCard';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import CocktailDescription from './components/CocktailDescription';
function App() {
  return (
    
    <BrowserRouter>
    <Routes>
      <Route exact path="/" element={<CocktailCard/>}/>
      <Route exact path="desc" element={<CocktailDescription/>}/>
    </Routes>
    </BrowserRouter>
    
    );
    }


export default App;
