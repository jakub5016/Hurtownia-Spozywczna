import { Button, InputAdornment, Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, TextField } from "@mui/material";
import { styled } from '@mui/material/styles';
import { useEffect, useState } from "react";

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

function handleChange(index, value, max, ammoutArray, setAmmoutArray) {
  let valueToSet = parseInt(value, 10);
  if (isNaN(valueToSet) || valueToSet < 1) {
    valueToSet = 0;
  }

  if (valueToSet <= max) {
    const newArray = [...ammoutArray];
    newArray[index] = valueToSet;
    setAmmoutArray(newArray);
  } else {
    alert("Podana wartość jest niepoprawna, zmień ją, inaczej nie będziesz mógł kontynuować zamówienia");
  }
}


function ProductToOrder(props) {
  const [ammoutArray, setAmmoutArray] = useState(new Array(props.products.length).fill(1));

  useEffect(() => {
    if (props.products.length > ammoutArray.length) {
      setAmmoutArray([...ammoutArray, ...new Array(props.products.length - ammoutArray.length).fill(1)]);
    }
  }, [props.products, ammoutArray]);

  const totalSum = props.products.reduce((acc, product, index) => {
    return acc + (product.price.price * ammoutArray[index]);
  }, 0);


  return (
    <TableContainer component={Paper} sx={{ margin: "1vw", padding: "1vw", width: "80vw", background: "#1976d2", color: "white" }}>
      <h1> Nowe zamówienie </h1>
      <Table>
        <TableHead>
          <TableRow>
            <TableCell align="left" sx={{ color: "white", fontWeight: "bold" }}>
              Nazwa
            </TableCell>
            <TableCell align="right" sx={{ color: "white", fontWeight: "bold" }}>
              Zamówiona Ilość
            </TableCell>
            <TableCell align="right" sx={{ color: "white", fontWeight: "bold" }}>
              Kategoria
            </TableCell>
            <TableCell align="right" sx={{ color: "white", fontWeight: "bold" }}>
              Cena za sztuke
            </TableCell>
            <TableCell align="right" sx={{ color: "white", fontWeight: "bold" }}>
              Razem
            </TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {props.products.map((product, index) => {
            return (
              <TableRow key={product.id}>
                <TableCell sx={{ color: "white" }} align="left">{product.name}</TableCell>
                <TableCell sx={{ color: "white" }} align="right">
                  <WhiteBackgroundTextField
                    helperText=""
                    defaultValue={1}
                    inputProps={{ type: 'number', max: product.availableQuantity, min: 1 }}
                    size="small"
                    onChange={(e) => { handleChange(index, e.target.value, product.availableQuantity, ammoutArray, setAmmoutArray) }}
                    InputProps={{
                      endAdornment: (
                        <InputAdornment position="end">
                          /{product.availableQuantity}
                        </InputAdornment>
                      ),
                    }}
                  />
                </TableCell>
                <TableCell sx={{ color: "white" }} align="right">{product.category}</TableCell>
                <TableCell sx={{ color: "white" }} align="right">
                  {product.price.price + " zł"}
                </TableCell>
                <TableCell sx={{ color: "white" }} align="right">{product.price.price * ammoutArray[index] + " zł"}</TableCell>
                <TableCell align="right">
                  <Button sx={{ borderRadius: "80vw", fontSize: "1.1vw" }} variant="contained" color="inherit" onClick={() => {
                    props.setAddedProducts(props.products.filter((p) => p.id !== product.id));
                    const newAmmoutArr = [...ammoutArray];
                    newAmmoutArr.splice(index, 1);
                    setAmmoutArray(newAmmoutArr);
                  }}>-</Button>
                </TableCell>
              </TableRow>
            );
          })}
          <TableRow>
            <TableCell sx={{ color: "white", fontWeight: "bold" }} align="left">Podsumowanie</TableCell>
            <TableCell />
            <TableCell />
            <TableCell />
            <TableCell sx={{ color: "white", fontWeight: "bold" }} align="right">{totalSum +" zł"}</TableCell>
          </TableRow>
        </TableBody>
      </Table>
      <div style={{ marginTop: "20px" }}>
        <Button sx={{ backgroundColor: "white", "&:hover": { backgroundColor: "white !important" } }} variant="outlined">
          Dodaj nowe zamówienie
        </Button>
      </div>
    </TableContainer>
  )
}

export default ProductToOrder;
