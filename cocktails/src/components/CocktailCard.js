import Card from '@mui/material/Card';
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Button from '@mui/material/Button';
import { useEffect, useState } from 'react';
import { Grid,Modal,Box, CardHeader, TextField } from '@mui/material';
import ArrowRightIcon from '@mui/icons-material/ArrowRight';
import { useNavigate } from 'react-router-dom';
import { confirmAlert } from 'react-confirm-alert';

export default function CocktailCard({tag, search})
{   
  const defaultCocktail={
        name: "",
        description: "",
        imageUrl: "",
        prep: "",
        tag: ""
    }
    const defaultIngredient={
      name: null,
      quanity: 0,
      unit: null
  }
    const [errors, setErrors]=useState([]);
    const [loading, setLoading]=useState(true);
    const [cocktails,setCocktails]=useState([]);
    const [name,setName]=useState([]);
    const [desc,setDesc]=useState([]);
    const [url,setUrl]=useState([]);
    const [cocktail, setCocktail] = useState(defaultCocktail);
    const [prep,SetPrep]=useState([]);
    const [ingredient, setIngredient]=useState([]);
    const [open, setOpen] = useState(false);
    const [open1, setOpen1] = useState(false);
    const [dname,setDname]=useState([]);
    const [quanity, setQuantity]= useState(12);
    const [unit, setUnit]=useState([]);
    const handleOpen = () => setOpen(true);
    const handleClose = () => setOpen(false);
    const handleClose1 = () => setOpen1(false);
    const handleChangeCockTail=(name, value)=>{
      setCocktail({...cocktail,
      [name]:value});
  };
  const handleChangeIndi=(name, value)=>{
    setIngredient({...ingredient,
    [name]:value});
};
    useEffect(()=>{
      fetch(`http://localhost:8080/api/v1/cocktails/cocktail/${tag}`,{method:'GET'})
        .then((resp)=>resp.json())
        .then((data)=>{
            setCocktails(data);
        })
        .finally(()=>{
            setLoading(false);
        });
    },[tag]);

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
    const requestOptions = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(cocktail)
    };
     fetch("http://localhost:8080/api/v1/cocktails/",requestOptions) .then(response => {
      if(!response.ok)
          {
              console.log(response)
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
      console.log("susces");
      setErrors([]);
      handleClose();
      setOpen1(true);
}            
});


}
const SubmitC=()=>{

    console.log(JSON.stringify(ingredient))
    const requestOptions = {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(ingredient),
      type: "no-cors"
  };
  fetch(`http://localhost:8080/api/v1/indi/${cocktails.length}`,requestOptions).then(response => {
    if(!response.ok)
        {
            console.log(response)
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
    console.log("susces");
    setErrors([]);
    setLoading(false);
    alert(`Dodano składnik: ${ingredient.quantity} ${ingredient.unit} ${ingredient.name}  do drinka !`);
    setIngredient(defaultIngredient)
    setLoading(true);
}            
});

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
    height:700,
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
    <TextField sx={{bgcolor:"white",
     color:"black",
     width:300,
     marginBottom:   errors?.find((x)=>x.name==="username") ?
     "1rem": null,
    }}
          id="outlined-multiline-flexible"
          label="Nazwa"
          multiline          
          maxRows={6}
          name='name'
          error={!!errors?.find((x)=>x.name=="name")}       
          helperText={errors?.find((x)=>x.name=="name")? errors?.find((x)=>x.name=="name")?.message: null}
          onChange={(x)=>handleChangeCockTail("name",x.target.value)}
        />
            <TextField sx={{bgcolor:"white", color:"black",width:300, marginTop:2,     marginBottom: errors?.find((x)=>x.name=="name")?.message}}
          id="outlined-multiline-flexible"
          label="URL"
          multiline
          type='text'
          inputMode='text'
          maxRows={6}
          name='image_url'
          onChange={(x)=>handleChangeCockTail("imageUrl",x.target.value)}
          error={!!errors?.find((x)=>x.name=="imageUrl")?.message}       
          helperText={errors?.find((x)=>x.name=="imageUrl")? errors?.find((x)=>x.name=="imageUrl")?.message: null}
        />
     <TextField sx={{bgcolor:"white", color:"black", width:300, marginTop:2,     marginBottom: errors?.find((x)=>x.name=="name")?.message
}}
          id="outlined-multiline-flexible"
          label="Opis"
          multiline
          name='description'
          rows={5}
          maxRows={6}
          onChange={(x)=>handleChangeCockTail("description",x.target.value)}
          error={!!errors?.find((x)=>x.name=="description")?.message}       
          helperText={errors?.find((x)=>x.name=="description")? errors?.find((x)=>x.name=="description")?.message: null}
        />
        <TextField sx={{bgcolor:"white", color:"black", width:300, marginTop:2,   
          marginBottom: errors?.find((x)=>x.name=="name")?.message}}
          id="outlined-multiline-flexible"
          label="Przygotowanie"
          multiline
          name='prep'
          rows={5}
          maxRows={6}
          onChange={(x)=>handleChangeCockTail("prep",x.target.value)}
          error={!!errors?.find((x)=>x.name=="prep")?.message}       
          helperText={errors?.find((x)=>x.name=="prep")? errors?.find((x)=>x.name=="prep")?.message: null}

        />
    <Button  variant="contained" inputMode='submit'  sx={{width:100, marginTop:7, marginLeft:10}} onClick={(event)=>Submit()} >
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

          <TextField sx={{bgcolor:"white", color:"black",width:100, marginTop:4,marginRight:1,
          marginBottom: errors?.find((x)=>x.name=="quantity")?.message?  "0.5rem": null}}
          id="outlined-multiline-flexible"
          label="Ilość"
          type="number"
          maxRows={6}
          onChange={(x)=>handleChangeIndi("quantity",x.target.value)}
          error={!!errors?.find((x)=>x.name=="quantity")?.message}       
          helperText={errors?.find((x)=>x.name=="quantity")? errors?.find((x)=>x.name=="quantity")?.message: null}
        />
         <TextField sx={{bgcolor:"white", color:"black",width:100, marginRight:1,marginTop:4,marginBottom: errors?.find((x)=>x.name=="unit")?.message?  "0.5rem": null}}
          id="outlined-multiline-flexible"
          label="Jedonstka"
          onChange={(x)=>handleChangeIndi("unit",x.target.value)}
          maxRows={6}
          error={!!errors?.find((x)=>x.name=="unit")?.message}       
          helperText={errors?.find((x)=>x.name=="unit")? errors?.find((x)=>x.name=="unit")?.message: null}
        />
         <TextField sx={{bgcolor:"white", color:"black",width:100, marginRight:1,marginTop:4,marginBottom: errors?.find((x)=>x.name=="name")?.message ?"0.5rem": null}}
          id="outlined-multiline-flexible"
          label="Nazwa"
          onChange={(x)=>handleChangeIndi("name",x.target.value)}
          maxRows={6}
          error={!!errors?.find((x)=>x.name=="name")?.message}       
          helperText={errors?.find((x)=>x.name=="name")? errors?.find((x)=>x.name=="name")?.message: null}
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
        
        {cocktails.filter(x=>{return search==""?x:String(x.name).toLowerCase().includes(String(search).toLowerCase());}).map((x)=>(
        
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