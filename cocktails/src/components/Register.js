import React, { useState } from 'react';
import { Card, TextField, Button,  CardContent } from '@mui/material';
import Grid from '@mui/material/Grid';
import { confirmAlert } from 'react-confirm-alert';
export default function Register()
{
    const [errors, setErrors]=useState([]);
    const defaultRegister={
         username: "",
         password: "",
         email: "",
         phoneNumber: "",
      }
    const [register, setRegister] =useState(defaultRegister);
    const handleChange=(name, value)=>{
        setRegister({...register,
        [name]:value});
    };
    const handleRegisterClick= () => {
        fetch("http://localhost:8080/api/v1/auth/register", {
            method: 'POST',
            mode:  'cors' ,
            headers: {
                'Content-Type': 'application/json',
                'Accept':'*',
                
            },
            body: JSON.stringify(register)
        })
        .then(response => {
            if(!response.ok)
                {
                    console.log(register)
                    response.text().then(text => {
                        console.log(text)
                        let p=JSON.parse(text);
                        setErrors(p.errors);
                    });
                }
                else{
                    return response.json();
                }
        })
        .catch(error => {
            console.log(error)
        })
        .then(data => {
            if(data)
            {
            confirmAlert({
                title: 'Sukces',
                message: 'Zarejestrowano!',
                buttons: [
                    {
                        label: 'OK',
                        onClick: () => window.location.replace("/login")
                    }
                ]
            });
        }            
        });
    }
    return(

<Card
             sx={{
                marginTop: "23rem",
                borderRadius: '16px',  
                position: 'absolute',
                left: '50%',
                transform: 'translate(-50%, -50%)',
                width: "23rem",
                height: "25rem",
                bgcolor: 'white',
                boxShadow: 24,
                p: 4, }}>
                    <CardContent>
      <Grid container spacing={1}>
      <Grid item xs={10}>
      <TextField
                    id="outlined-username-input-standard-size-normal"
                    label="Username"
                    type='username'
                    autoComplete='current-username'
                    size="normal"
                    sx={{
                        width: '21rem',
                        height: '4rem',
                        marginBottom: errors?.find((x)=>x.name=="username")?.message,
                        "0.5rem": null
                    }}
                    onChange={(x)=>handleChange("username",x.target.value)}
                    error={!!errors?.find((x)=>x.name=="username")?.message}       
                    helperText={errors?.find((x)=>x.name=="username")? errors?.find((x)=>x.name=="username")?.message: null}

                />
        </Grid>
        <Grid item xs={10}>
        <TextField
                    id="outlined-password-input-standard-size-normal"
                    label="Password"
                    type="password"
                    autoComplete="current-password"
                    size="normal"
                    sx={{
                        width: '21rem',
                        height: '4rem',
                        marginBottom: errors?.find((x)=>x.name=="password")?.message?
                        "0.5rem": null
    
                    }}
                    onChange={(x)=>handleChange("password",x.target.value)}
                    error={!!errors?.find((x)=>x.name=="password")?.message}       
                    helperText={errors?.find((x)=>x.name=="password")? errors?.find((x)=>x.name=="password")?.message: null}

                />
        </Grid>
        <Grid item xs={10}>
        <TextField
                    id="outlined-email-input-standard-size-normal"
                    label="Email"
                    type='email'
                    autoComplete='current-email'
                    size="normal"
                    sx={{
                        width: '21rem',
                        height: '4rem',
                        marginBottom: errors?.find((x)=>x.name=="email")?.message ?
                        "0.5rem": null
                    }}
                    onChange={(x)=>handleChange("email",x.target.value)}
                    error={!! errors?.find((x)=>x.name=="email")?.message}       
                    helperText={errors?.find((x)=>x.name=="email")? errors?.find((x)=>x.name=="email")?.message: null}
                />  
        </Grid>
        <Grid item xs={10}>
        <TextField
                    id="outlined-phonenumber-input-standard-size-normal"
                    label="PhoneNumber"
                    type='tel'
                    autoComplete='current-phonenumber'
                    size="normal"
                    sx={{
                        width: '21rem',
                        height: '4rem',
                        marginBottom: errors?.find((x)=>x.name=="phoneNumber")?.message ?
                        "0.5rem": null
                    }}
                    onChange={(x)=>handleChange("phoneNumber",x.target.value)}
                    error={!!errors?.find((x)=>x.name=="phoneNumber")?.message}       
                    helperText={errors?.find((x)=>x.name=="phoneNumber")? errors?.find((x)=>x.name=="phoneNumber")?.message: null}
                />  
        </Grid>

        
        <Grid item xs={10}>
        <Button variant="contained"
                    size="large"
                    sx={{
                        width: '21rem',
                        height: '4rem',
                        fontFamily: 'sans-serif',
                        fontWeight: "border"
                    }}
                    onClick={()=>{handleRegisterClick();}}
                    >
                    Zarejestruj się
                </Button>
        </Grid>
      </Grid>
      </CardContent>
      </Card>
);
}