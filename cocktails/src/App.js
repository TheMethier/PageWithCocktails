import logo from './logo.svg';
import './App.css';
import React from 'react';

import Button from '@mui/material/Button';
import Stack from '@mui/material/Stack';
import Navbar from './components/NavBar';
import CocktailCard from './components/CocktailCard';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import CocktailDescription from './components/CocktailDescription';
function App() {
  const [tag,setTag]=React.useState("");
  const [search,setSearch]=React.useState("");
  function SearchToApp(x)
  {
  setSearch(x);
  };
  const passTagToApp=(data)=>{
    setTag(data);
  } 
  return (
    <div>
    <Navbar passTagToApp={passTagToApp} passSearchToApp={SearchToApp}/>
    <BrowserRouter>
    <Routes>
      <Route exact path="/" element={<CocktailCard tag={tag} search={search} />}/>
      <Route exact path="desc" element={<CocktailDescription/>}/>
    </Routes>
    </BrowserRouter>
    </div>

    );
    }


export default App;
