import Card from '@mui/material/Card';
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
import Stack from '@mui/material/Stack';
import CardMedia from '@mui/material/CardMedia';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import { AddToDriveSharp, CenterFocusStrong, Description, Height, Route, } from '@mui/icons-material';
import { useEffect, useState } from 'react';
import { Grid,Modal,Box, CardHeader, TextField } from '@mui/material';
import Alert from '@mui/material/Alert';
import ArrowRightIcon from '@mui/icons-material/ArrowRight';

import { Link, useHref } from "react-router-dom";
import { useNavigate } from 'react-router-dom';
import CocktailDescription from './CocktailDescription';
import SendIcon from '@mui/icons-material/Send';

export default function CocktailCard()
{
    const [loading, setLoading]=useState(true);
    const [cocktails,setCocktails]=useState([]);
    const [name,setName]=useState([]);
    const [desc,setDesc]=useState([]);
    const [url,setUrl]=useState([]);
    const [prep,SetPrep]=useState([]);
    const [Ingredients, setIngredients]=useState([]);
    const [open, setOpen] = useState(false);
    const [open1, setOpen1] = useState(false);
    const [dname,setDname]=useState([]);
    const [quanity, setQuantity]= useState(12);
    const [unit, setUnit]=useState([]);
    const handleOpen = () => setOpen(true);
    const handleClose = () => setOpen(false);
    const handleClose1 = () => setOpen1(false);

    useEffect(()=>{
        if(loading){
        setLoading(true);
       fetch("http://localhost:8080/api/v1/cocktails/",{method:'GET'})
        .then((resp)=>resp.json())
        .then((data)=>{
            console.log(data);
            setCocktails(data);
        })
        .finally(()=>{
            setLoading(false);
        });

    }
    },[]);
    useEffect(()=>{
        fetch(`http://localhost:8080/api/v1/indi/${cocktails.length}`)
        .then((resp)=>resp.json())
        .then((data)=>{
            console.log(data);
            setIngredients(data);
        });

    },[]);

let navigate=useNavigate();
    function GoToDesc(props)
    {
        let path='desc';
        console.log(props);
        navigate(path,{
        state: props
        });
    }
    
  const handleNameChange=(event)=>
   {  
        const name=event.target.name;
        const value=event.target.value;
        setName(value);
   }
   const handleDescChange=(event)=>
   {  
        const value=event.target.value;
        setDesc(value);
   }
   const handleUrlChange=(event)=>
   {  
        const value=event.target.value;
        setUrl(value);
   }
   const handleDnChange=(event)=>{
    const value=event.target.value;
    setDname(value);
   }
   const handleQChange=(event)=>{
    const value=event.target.value;
    setQuantity(Number(value));
   }
   const handleUChange=(event)=>{
    const value=event.target.value;
    setUnit(value);
   }
   const handlePrepChange=(event)=>{
    const value=event.target.value;
    SetPrep(value);
   }
   const Submit=()=>
   {
    
    const text={name: name, description: desc, image_url: url,prep: prep}

    const requestOptions = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(text)
        ,type: "no-cors"
    };
     fetch("http://localhost:8080/api/v1/cocktails/",requestOptions);
     handleClose();
     setOpen1(true);

}
const SubmitC=()=>{
    console.log(typeof(quanity));
    const text={quantity: quanity, name: String(dname), unit: String(unit)}
    console.log(JSON.stringify(text))
    const requestOptions = {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(text),
      type: "no-cors"
  };
  fetch(`http://localhost:8080/api/v1/indi/${cocktails.length}`,requestOptions);
  alert(`Dodano składnik: ${text.quantity} ${text.unit} ${text.name}  do drinka !`)
}
const SubmitFinal=()=>{
  setOpen1(false);

}
   
const style = {
    position: 'absolute',
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)',
    width: 300,
    height:630,
    bgcolor: 'white',
    border: '2px solid #000',
    boxShadow: 24,
    p: 4,
  };
  const style1 = {
    position: 'absolute',
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)',
    width: 400,
    height:200,
    bgcolor: 'white',
    border: '2px solid #000',
    boxShadow: 24,
    p: 4,
  };
    return(
        
        <div>
<Modal
  open={open}
  onClose={handleClose}
  aria-labelledby="modal-modal-title"
  aria-describedby="modal-modal-description"
>
  <Card sx={style}>
    <CardHeader title="Dodaj Drina" sx={{color:"black", fontSize:'50', fontFamily:'sans-serif'}} titleTypographyProps={{variant:'h3' }}>
    </CardHeader>
    <CardMedia sx={{backgroundColor:"white"}}>
        <form onSubmit={Submit}>
    <TextField sx={{bgcolor:"white", color:"black",width:300}}
          id="outlined-multiline-flexible"
          label="Nazwa"
          multiline          
          maxRows={6}
          name='name'
          value={name}
          onChange={handleNameChange}
        />
            <TextField sx={{bgcolor:"white", color:"black",width:300, marginTop:2}}
          id="outlined-multiline-flexible"
          label="URL"
          multiline
          type='text'
          inputMode='text'
          maxRows={6}
          value={url}
          name='image_url'
          onChange={handleUrlChange}

        />
     <TextField sx={{bgcolor:"white", color:"black", width:300, marginTop:2}}
          id="outlined-multiline-flexible"
          label="Opis"
          multiline
          name='description'
          value={desc}
          rows={5}
          maxRows={6}
          onChange={handleDescChange}

        />
        <TextField sx={{bgcolor:"white", color:"black", width:300, marginTop:2}}
          id="outlined-multiline-flexible"
          label="Przygotowanie"
          multiline
          value={prep}
          name='prep'
          rows={5}
          maxRows={6}
          onChange={handlePrepChange}

        />
    <Button input type='submit' variant="contained" inputMode='submit'  sx={{width:100, marginTop:7, marginLeft:10}} onClick={(event)=>Submit()} >
  Dalej<ArrowRightIcon>

  </ArrowRightIcon>
</Button>

</form>
    </CardMedia>
  </Card>
</Modal>
<Modal
  open={open1}
  onClose={handleClose1}
  aria-labelledby="modal-modal-title"
  aria-describedby="modal-modal-description"
>
  <Card sx={style1}>
    <CardHeader title="Dodaj Składniki" sx={{color:"black", fontSize:'50', fontFamily:'sans-serif'}} titleTypographyProps={{variant:'h3' }}>
    </CardHeader>
    <CardMedia sx={{backgroundColor:"white"}}>

        <form onSubmit={Submit}>

          <TextField sx={{bgcolor:"white", color:"black",width:100, marginTop:4,marginRight:1}}
          id="outlined-multiline-flexible"
          label="Ilość"
          multiline
          type="number"
          value={quanity}
          maxRows={6}
          onChange={handleQChange}
        />
         <TextField sx={{bgcolor:"white", color:"black",width:100, marginRight:1,marginTop:4}}
          id="outlined-multiline-flexible"
          label="Jedonstka"
          multiline
          value={unit}
          onChange={handleUChange}
          maxRows={6}
        />
         <TextField sx={{bgcolor:"white", color:"black",width:100, marginRight:1,marginTop:4}}
          id="outlined-multiline-flexible"
          label="Nazwa"
          value={dname}
          onChange={handleDnChange}
          multiline
          maxRows={6}
        />
    <Button input typ='submit' variant="contained" inputMode='submit'  sx={{width:55,height:55,marginTop:4}} onClick={(event)=>SubmitC()} >
  +
</Button>

</form>
<Button onClick={SubmitFinal}>Zapisz drinka</Button>
    </CardMedia>
  </Card>
</Modal>
<Grid container spacing={1} sx={{alignContent:"center",paddingLeft:10,paddingTop:2}}>
  <Grid container item spacing={1}>
  <Card key={-1} className='card'  sx={{width:250,
    height:400, backgroundColor:"black", marginTop:2,marginLeft:2,  }} onClick={handleOpen} >
<CardMedia
sx={{ height: 250 }}
        title="green iguana"
        image='https://www.brit.co/media-library/vegas-bomb-shot.jpg?id=32039807&width=760&quality=80'
        >
</CardMedia>
<CardContent>
    
    <h1 style={{color: 'white',fontSize:25}}>
        Dodaj Drina

    </h1>
    
        <h3 style={{color:'white',fontSize:15}}>

        </h3>
    </CardContent>
<CardActions>
</CardActions>
    </Card>
        
        {cocktails.map((x)=>(
        
<div>
    <Card key={x.id} className='card'  sx={{width:250,
    height:450, backgroundColor:"black", marginTop:2,marginLeft:2,  }} onClick={(event)=>GoToDesc(x.id)} >
<CardMedia
sx={{ height:300  }}
        title="green iguana"
        image={x.imageUrl}     
          >
            {
                
            }
</CardMedia>
<CardContent>
    
    <h1 style={{color: 'white',fontSize:25}}>
        {x.name}

    </h1>
    
        <h3 style={{color:'white',fontSize:25}}>
        </h3>
    </CardContent>
<CardActions>
</CardActions>
    </Card>

     </div>        

))};
  </Grid>
  </Grid>     
</div>
    );
};