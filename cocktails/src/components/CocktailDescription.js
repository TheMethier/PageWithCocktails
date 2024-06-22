import CardMedia from '@mui/material/CardMedia';
import Typography from '@mui/material/Typography';
import { useEffect, useState } from 'react';
import { CardHeader, Grid, Card,CardContent, Accordion,AccordionSummary,AccordionDetails  } from '@mui/material';
import { useLocation } from 'react-router-dom';
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';

export default function CocktailDescription()
{
    const [loading, setLoading]=useState(true);
    const local = useLocation();
    const [cocktail,setCocktail]=useState([]);
    const [ingredients,setIngredients]=useState([]);
    let p =Number(local.state)
    useEffect(()=>{
        if(loading){
        setLoading(true);
       fetch(`http://localhost:8080/api/v1/cocktails/${p}`)
        .then((resp)=>resp.json())
        .then((data)=>{
            setCocktail(data);
        })
        .finally(()=>{
            setLoading(false);
        });
        console.log(cocktail.prep)
    }
    },[loading]);
    useEffect(()=>{
        fetch(`http://localhost:8080/api/v1/indi/${p}`)
        .then((resp)=>resp.json())
        .then((data)=>{
            setIngredients(data);
        });

    },[loading]);
    return(<div>
        <Grid container spacing={1} sx={{alignContent:"center",paddingLeft:40,paddingTop:2}}>
             <Grid container item spacing={1}>
        <Card sx={{backgroundColor:"black", width:1000, height:200,marginRight:50}}>
            <CardHeader sx={{color:"white", fontSize:'56', fontFamily:'sans-serif'}} title={cocktail.name} titleTypographyProps={{variant:'h2' }}>
            </CardHeader>
               
        </Card>
        
             <Card key={-1} className='card'  sx={{width:600,
                height:600, backgroundColor:"black", marginTop:2,marginLeft:8,  }}>
            <CardMedia
            sx={{ height: 600,  width:600  }}
                title="green iguana"
                image={cocktail.imageUrl}
                >
            </CardMedia>
                </Card>
                </Grid>                    
                <Grid container item rowSpacing={1} sx={{ paddingRight:50, paddingLeft:20, marginTop:5}}>
                    <Accordion sx={{backgroundColor:'black',color:'white'}} defaultExpanded="true">
        <AccordionSummary
          expandIcon={<ExpandMoreIcon />}
          aria-controls="panel1a-content"
          id="panel1a-header"
        >
          <Typography titleTypographyProps={{variant:'h1'}}>Opis </Typography>
        </AccordionSummary>
        <AccordionDetails>
          <Typography sx={{fontFamily:'sans-serif', fontSize:'20'}}>
            {cocktail.description}
          </Typography>
        </AccordionDetails>
      </Accordion>
      <Accordion sx={{backgroundColor:'black',color:'white'}} defaultExpanded="true">
        <AccordionSummary
          expandIcon={<ExpandMoreIcon />}
          aria-controls="panel2a-content"
          id="panel2a-header"
        >
          <Typography>Składniki</Typography>
        </AccordionSummary>
        <AccordionDetails>
          <Typography>
                    <ul>
                {ingredients.map(y=>(
                        <li>
                            
                                {y.quantity>0?y.quantity:null} {y.unit} {y.name}
                            
                        </li>
                ))}
                  </ul>
          </Typography>
        </AccordionDetails>
      </Accordion>
      <Grid columnSpacing={4}sx={{marginTop:2, marginLeft:3}}>
      <Accordion sx={{backgroundColor:'black',color:'white', width:500}} defaultExpanded="true">
        <AccordionSummary
          expandIcon={<ExpandMoreIcon />}
          aria-controls="panel3a-content"
          id="panel3a-header"
        >
          <Typography>Sposób przygotowania</Typography>
        </AccordionSummary>
        <AccordionDetails>
          <Typography>
                  {cocktail.prep}
          </Typography>
        </AccordionDetails>
      </Accordion>
                    </Grid>
                    
            </Grid>      

            </Grid>
        </div>
    );

};
