import React, { useState } from 'react';
import {  useNavigate } from 'react-router-dom';
import { Card, TextField, Paper, Button,  Stack, CardContent } from '@mui/material';
import { confirmAlert } from 'react-confirm-alert';
import {useForm} from 'react-hook-form'
import { Password } from '@mui/icons-material';
import Cookies from 'js-cookie';

export default function Login({set})
{

    const navigate = useNavigate();
    const defaultLogin={
        username: "",
        password: ""
      };
      const possibleErrors = new Set([""]);
    const [errors, setErrors]=useState([]);
    const [login, setLogin] =useState(defaultLogin);
    const handleLoginChange=(name, value)=>{
        setLogin({...login,
        [name]:value});
    };
    const handleRegisterClick= () => {
        navigate("/register");
    }
    const handleLoginClick = () => {
        fetch(`http://localhost:8080/api/v1/auth/authenticate`, {
            method: 'POST',
            mode:  'cors' ,
            headers: {
                'Content-Type': 'application/json',
                'Accept':'*'
            },
            credentials: 'include',
            body: JSON.stringify(login)
        })
        .then(response => {
            if(!response.ok)
                {
                    if(response.status==403){
                        var notValidErrors = [{name : "username", message: "username or password not valid"}, {name : "password", message: "username or password not valid"}]
                        setErrors(notValidErrors);

                    }
                      else{
                    response.text().then(text => {
                        console.log(text)
                        let p=JSON.parse(text);
                        setErrors(p.errors);
                    
                    });
                }
                   
                }
                else{
                    console.log(Cookies.get("token"))
                    return response.json();

                }
        })

        .then(data => {
            if(data!=null)
            {
                set(true)
                localStorage.setItem("userId",data.userId)
                var data1 = {"isLoggedIn": true,
                    "userId": data.userId
                }
                localStorage.setItem("isLoggedIn",JSON.stringify(data1));
                confirmAlert({
                    title: 'Sukces',
                    message: 'Zalogowano!',
                    buttons: [
                        {
                            label: 'OK',
                            onClick: () => navigate("/")
                        }
                    ]
                });
            }
                        
        })        .catch(error => {
        });
    };
    return(
        <div>
            <Card
             sx={{
                marginTop: "13rem",
                borderRadius: '16px',  
                position: 'absolute',
                left: '50%',
                transform: 'translate(-50%, -50%)',
                width: "23rem",
                height: "16rem",
                boxShadow: 24,
                p: 4, }}>
                    <CardContent>
            <Stack  
                spacing={1}   
                alignItems="center"   
                justifyContent="center"
                sx={{}}>
            <div>

                <TextField
                    id="outlined-username-input-standard-size-normal"
                    label="Username"
                    type='username'
                    autoComplete='current-username'
                    size="normal"
                    sx={{
                        width: '20rem',
                        height: '4rem',
                        //bgcolor: #262626
                        marginBottom: errors.find((x)=>x.name==="username") ?
                        "1rem": null,
                    }}
                    error={!!errors.find((x)=>x.name==="username")?.message}       
                    helperText={errors.find((x)=>x.name==="username")? errors.find((x)=>x.name=="username")?.message: null}
                    onChange={(x)=>handleLoginChange("username",x.target.value)}
                    
                />
                  </div>
                  <div>
                <TextField
                    id="outlined-password-input-standard-size-normal"
                    label="Password"
                    type="password"
                    autoComplete="current-password"
                    size="normal"
                    sx={{
                        width: '20rem',
                        height: '4rem',
                        marginBottom: errors.find((x)=>x.name==="password")?.message?
                        "1rem": null,    
                    }}
                    onChange={(x)=>handleLoginChange("password",x.target.value)}
                    error={!!errors.find((x)=>x.name==="password")?.message}
                    helperText={errors.find((x)=>x.name==="password")? errors.find((x)=>x.name==="password")?.message: null}
                />
                </div>
                <div>
                <Button variant="contained"
                    size="large"
                    sx={{
                        width: '20rem',
                        height: '4rem',
                        fontFamily: 'sans-serif',
                        fontWeight: "border"
                    }}
                    onClick={x=>{handleLoginClick()}}
                    >
                    Zaloguj się
                </Button>
                </div>
            </Stack>
            </CardContent>
            </Card>
            <Paper sx={{ marginTop: "40rem",
                borderRadius: '16px',  
                position: 'absolute',
                left: '50%',
                transform: 'translate(-50%, -50%)',
                width: "25rem",
                height: "8rem",
                boxShadow: 24,
                p: 2, }}>
                    <Stack  
                spacing={1}   
                alignItems="center"   
                justifyContent="center"
                sx={{}}>

            <div>
                    <h2>Pierwszy raz w Drinki? </h2>
                </div>
                <div>
                <Button variant="contained"
                color='success'
                    size="large"
                    sx={{
                        width: '12rem',
                        height: '3rem',
                        fontFamily: 'sans-serif',
                        fontWeight: "border"
                    }}
                    onClick={()=>{handleRegisterClick();}}
                    >
                    Zarejestruj się
                </Button>
                </div>
                </Stack>

            </Paper>
        </div>
    );
}