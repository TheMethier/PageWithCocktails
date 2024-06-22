import logo from './logo.svg';
import './App.css';
import React from 'react';
import Login from './components/Login';
import Button from '@mui/material/Button';
import Stack from '@mui/material/Stack';
import Register from './components/Register';
import Navbar from './components/NavBar';
import CocktailCard from './components/CocktailCard';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import CocktailDescription from './components/CocktailDescription';
import { useLocalStorage } from "@uidotdev/usehooks";

function App() {
  const [tag,setTag]=React.useState("");
  const [isLoggedIn, setIsLoggedIn]= React.useState(false);
  const [search,setSearch]=React.useState("");

  function SearchToApp(x)
  {
    setSearch(x);
  };
  const passTagToApp=(data)=>{
    setTag(data);
  } 
  const setIsLoggedIn1=(x)=>
  {
    setIsLoggedIn(x)
  }

  return (
    <div>
    <Navbar passTagToApp={passTagToApp} passSearchToApp={SearchToApp} />
    <BrowserRouter>
    <Routes>
      <Route exact path="/" element={<CocktailCard tag={tag} search={search} />}/>
      <Route exact path="desc" element={<CocktailDescription/>}/>
      <Route exact path='login' element = {<Login set ={setIsLoggedIn1}/>}/>
      <Route exact path='register' element= {<Register/>}/>
    </Routes>
    </BrowserRouter>
    </div>

    );
    }


export default App;
