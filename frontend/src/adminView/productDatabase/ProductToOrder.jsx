import { Button, InputAdornment, Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, TextField } from "@mui/material";
import { styled } from '@mui/material/styles';

const WhiteBackgroundTextField = styled(TextField)({
  '& .MuiInputBase-root': {
    backgroundColor: 'white',
  },
  '& input[type=number]': {
    '-moz-appearance': 'textfield',
    '-webkit-appearance': 'none',
    'appearance': 'textfield',
    'margin': 0,
  },
  '& input[type=number]::-webkit-outer-spin-button, & input[type=number]::-webkit-inner-spin-button': {
    '-webkit-appearance': 'none',
    'margin': 0,
  },
});


function ProductToOrder(props){
    return(
        <TableContainer component={Paper} sx={{margin:"1vw", padding: "1vw", width: "80vw", background:"#1976d2", color:"white"}}>
        <h1> Dodane Produkty </h1>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell align="left" sx={{color:"white", fontWeight: "bold" }}>
                Nazwa
              </TableCell>
              <TableCell align="right" sx={{color:"white", fontWeight: "bold" }}>
                Zamówiona Ilość
              </TableCell>
              <TableCell align="right" sx={{color:"white", fontWeight: "bold" }}>
                Kategoria
              </TableCell>
              <TableCell align="right" sx={{color:"white", fontWeight: "bold" }}>
                Cena
              </TableCell>
              <TableCell align="right" sx={{color:"white", fontWeight: "bold" }}>
                Cena
              </TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {props.products.map((product) => {
              return (
                <TableRow key={product.id}>
                  <TableCell sx={{color:"white"}} align="left">{product.name}</TableCell>
                  <TableCell sx={{color:"white"}} align="right">
                    <WhiteBackgroundTextField 
                          helperText=""
                          inputProps={{ type: 'number', max: product.availableQuantity, min: 1 }}
                          size="small"
                          InputProps={{
                            endAdornment: (
                              <InputAdornment position="end">
                                /{product.availableQuantity}
                              </InputAdornment>
                            ),
                          }}
                      />
                  </TableCell>
                  <TableCell sx={{color:"white"}} align="right">{product.category}</TableCell>
                  <TableCell sx={{color:"white"}} align="right">
                    {product.price.lowestFrom30Days}
                  </TableCell>
                  <TableCell sx={{color:"white"}} align="right">{product.price.price}</TableCell>
                  <TableCell align="right">
                          <Button sx={{borderRadius:"80vw", fontSize:"1.1vw"}} variant="contained" color="inherit" onClick={()=>{
                                props.setAddedProducts(props.products.filter((p) =>{return p.id != product.id}));
                          }}>-</Button>
                      </TableCell>
                </TableRow>
              );
            })}
          </TableBody>
        </Table>
      </TableContainer>
    )

}

export default ProductToOrder