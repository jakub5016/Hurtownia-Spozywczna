import {
  Button,
  Dialog,
  Pagination,
  Paper,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
} from "@mui/material";
import { useEffect, useState } from "react";
import getProducts from "./getProducts";
import addProduct from "./addProduct";

function ProductTable(props) {
  const [products, setProducts] = useState({ content: [] });
  const [open, setOpen] = useState(false)
  const [currentPage, setCurrentPage] = useState(0)

//   const [productDTO, setPoroductDTO] = useState({price: 0, name:"string", category:"category", quantity:"0"})
  const [newProductPrice, setNewProductPrice] = useState(0)
  const [newProductName, setNewProductName] = useState("")
  const [newProductCategory, setNewProductCategory] = useState("")
  const [newProductQuantity, setNewProductQuantity] = useState(0)


  useEffect(() => {
    let isMounted = true;
    getProducts(currentPage).then((data) => {
      if (isMounted) {
        setProducts(data);
      }
    });
    return () => {
      isMounted = false;
    };
  }, [currentPage]);

  console.log(newProductName)

  return (
    <div style={{ display: "flex", flexDirection: "column" }}>
      <TableContainer component={Paper} sx={{margin:"1vw", padding: "1vw", width: "80vw" }}>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell sx={{ fontWeight: "bold" }}>ID</TableCell>
              <TableCell align="right" sx={{ fontWeight: "bold" }}>
                Nazwa
              </TableCell>
              <TableCell align="right" sx={{ fontWeight: "bold" }}>
                Ilość
              </TableCell>
              <TableCell align="right" sx={{ fontWeight: "bold" }}>
                Kategoria
              </TableCell>
              <TableCell align="right" sx={{ fontWeight: "bold" }}>
                Cena z ostatnich 30 dni
              </TableCell>
              <TableCell align="right" sx={{ fontWeight: "bold" }}>
                Cena
              </TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {products.content.map((product) => {
              return (
                <TableRow key={product.id}>
                  <TableCell>{product.id}</TableCell>
                  <TableCell align="right">{product.name}</TableCell>
                  <TableCell align="right">
                    {product.availableQuantity}
                  </TableCell>
                  <TableCell align="right">{product.category}</TableCell>
                  <TableCell align="right">
                    {product.price.lowestFrom30Days}
                  </TableCell>
                  <TableCell align="right">{product.price.price}</TableCell>
                </TableRow>
              );
            })}
          </TableBody>
        </Table>
      </TableContainer>

      {props.userType == "ADMIN" ? (
        <Button
          sx={{
            background: "white",
          }}
          variant="outlined"
          onClick={()=>{setOpen(true)}}
        >
          Dodaj nowy produkt do bazy danych
        </Button>
      ) : null}

      <Pagination
        sx={{ margin: "0 auto" }}
        color="primary"
        count={products.totalPages}
        onChange={(event, page)=>{setCurrentPage(page-1)}}
      />

      <Dialog open={open} onClose={()=>{setOpen(false)}}>
        <Paper style={{padding:"1vw"}}>
            <form style={{display:"flex", flexDirection:"column", margin:"1vw", textAlign:"justify"}}>
                <label style={{padding:"1vw", textAlign:"justify", justifyItems:"center"}}>
                    <a>Nazwa produktu  </a>
                    <input type="text" onChange={(e)=>{setNewProductName(e.target.value)}}></input>
                </label>
                <label style={{padding:"1vw"}}>
                    <a>Kategoria </a>
                    <input type="text" onChange={(e)=>{setNewProductCategory(e.target.value)}}></input>
                </label>
                <label style={{padding:"1vw"}}>
                    <a>Ilość </a>
                    <input type="number" onChange={(e)=>{setNewProductQuantity(e.target.value)}}></input>
                </label>
                <label style={{padding:"1vw"}}>
                    <a>Cena </a>
                    <input type="number" onChange={(e)=>{setNewProductPrice(e.target.value)}}></input>
                </label>
            </form>

            <Button variant="outlined" onClick={()=>{addProduct({
                "name": newProductName,
                "category": newProductCategory,
                "quantity": newProductQuantity,
                "price": newProductPrice,
            })}}> Dodaj </Button>
        </Paper>
      </Dialog>
    </div>
  );
}
export default ProductTable;
