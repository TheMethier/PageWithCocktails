import * as React from 'react';
import Button from '@mui/material/Button';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';
import Slide from '@mui/material/Slide';
import { IconButton } from '@mui/material';
import TuneIcon from '@mui/icons-material/Tune';
import { useTheme } from '@mui/material/styles';
import Box from '@mui/material/Box';
import OutlinedInput from '@mui/material/OutlinedInput';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';
import Chip from '@mui/material/Chip';
import FormatAlignLeftIcon from '@mui/icons-material/FormatAlignLeft';
import FormatAlignCenterIcon from '@mui/icons-material/FormatAlignCenter';
import FormatAlignRightIcon from '@mui/icons-material/FormatAlignRight';
import FormatAlignJustifyIcon from '@mui/icons-material/FormatAlignJustify';
import ToggleButton from '@mui/material/ToggleButton';
import ToggleButtonGroup from '@mui/material/ToggleButtonGroup';
import LocalBarIcon from '@mui/icons-material/LocalBar';

const ITEM_HEIGHT = 48;
const ITEM_PADDING_TOP = 8;
const MenuProps = {
  PaperProps: {
    style: {
      maxHeight: ITEM_HEIGHT * 4.5 + ITEM_PADDING_TOP,
      width: 250,
    },
  },
};

const alko = [
  '',
  'Whisky',
  'Wódka',
  'Gin',
  'Rum',
  'Tequila'
];

function getStyles(name, personName, theme) {
  return {
    fontWeight:
      personName.indexOf(name) === -1
        ? theme.typography.fontWeightRegular
        : theme.typography.fontWeightMedium,
  };
}
const Transition = React.forwardRef(function Transition(props, ref) {
  return <Slide direction="up" ref={ref} {...props} />;
});

export default function Filter({passTagToNav}, ) {
  const [open, setOpen] = React.useState(false);
  const [size, setSize]=React.useState("");
  const theme = useTheme();
  const [baseLiq, setBaseLiq] = React.useState("");
    const [tag,setTag]=React.useState("");

   const handleSize = (event, newSize) => {
    
    if(newSize==null)
    {
      newSize="";
    }
    setSize(newSize);    
  };
 

  const handleChange = (event) => {
    const {
      target: { value },
    } = event;

    console.log(value) ;
    let p="";
      if(value=='Whisky')
    {
        p+= 'h';
    }
     if(value=='Tequila')
    {
        p+="t";
    }
  
     if(value=='Wódka')
    {
        p+="w"
    }
   
     if(value=='Gin')
    {
        p+="g"
    }

    if(value=='Rum')
    {
        p+='r'
    }
    setTag(p);
    setBaseLiq(value);
    };
  const handleClickOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    passTagToNav(tag+size);
    setOpen(false);
  };

  return (
    <div>
        <IconButton aria-label="delete" onClick={handleClickOpen} sx={{width:'2rem', height:'2rem', color:'white', marginLeft:'2%' }}>
  <TuneIcon />
</IconButton>

      <Dialog
        open={open}
        TransitionComponent={Transition}
        keepMounted
        onClose={handleClose}
        aria-describedby="alert-dialog-slide-description"
      >
        <DialogTitle >{"Filtruj według"}</DialogTitle>
        <DialogContent>
          <DialogContentText id="alert-dialog-slide-description">
     <div>
      <Box sx={{ minWidth: 300, marginTop: "5%" }}>
      <FormControl fullWidth>
        <InputLabel id="demo-simple-select-label">Alkohol</InputLabel>
        <Select
          labelId="demo-simple-select-label"
          id="demo-simple-select"
          value={baseLiq}
          label="Age"
          onChange={handleChange}
         
          MenuProps={MenuProps}>
        
          {
          alko.map((name) => (
            <MenuItem
              key={name}
              value={name}
              style={getStyles(name, baseLiq, theme)}
            >
              {name}
            </MenuItem>
            ))}
        </Select>
      </FormControl>
    </Box>

    </div>
    <ToggleButtonGroup
      value={size}
      exclusive
      onChange={handleSize}
      aria-label="text alignment"
      sx={{marginTop:'5%',}}
    >
      <ToggleButton value="S" aria-label="left aligned">
        <FormatAlignLeftIcon />
      </ToggleButton>
      <ToggleButton value="M" aria-label="centered">
        <LocalBarIcon />
      </ToggleButton>
      <ToggleButton value="L" aria-label="right aligned">
        <FormatAlignRightIcon />
      </ToggleButton>
    </ToggleButtonGroup>

          </DialogContentText>
        </DialogContent>
        <DialogActions>
          <Button onClick={handleClose}>OK</Button>
        </DialogActions>
      </Dialog>
    </div>
  );
}