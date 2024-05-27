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
import ProductToOrder from "./ProductToOrder";
import EditProductDialog from "./EditProductDialog";
import PriceHistoryDialog from "../priceDatabase/PriceHistoryDialog";


function UserProductView(product, setAddedProducts, addedProducts){
  return(
  <TableRow key={product.id}>
    <TableCell align="left">{product.name}</TableCell>
    <TableCell align="right">
      {product.availableQuantity}
    </TableCell>
    <TableCell align="right">{product.category}</TableCell>
    <TableCell align="right">
      {product.price.lowestFrom30Days}
    </TableCell>
    <TableCell align="right">{product.price.price}</TableCell>
    <TableCell align="right">
          <Button sx={{borderRadius:"80vw"}} variant="contained" onClick={()=>{
              setAddedProducts(addedProducts.concat(product))
          }}>+</Button>
      </TableCell>
  </TableRow>
  )
}


function AdminProductView(product, setOpenEdit, setOpenPriceHistory ,setNewProductQuantity, setNewProductPrice, setEditProductID){
  return(
  <TableRow key={product.id} sx={{background: product.archived?"gray":"white"}}>
    <TableCell align="left">{product.name}</TableCell>
    <TableCell align="right">
      {product.availableQuantity}
    </TableCell>
    <TableCell align="right">{product.category}</TableCell>
    <TableCell align="right">
      {product.price.lowestFrom30Days}
    </TableCell>
    <TableCell align="right">{product.price.price}</TableCell>
    <TableCell align="right">
          <Button sx={{borderRadius:"80vw", fontSize:"12px"}} variant="contained" onClick={()=>{
            setOpenPriceHistory(true)
          }}>Historia Cen</Button>
      </TableCell>
    {product.archived ? null : <TableCell align="right">
          <Button sx={{borderRadius:"80vw", fontSize:"12px"}} variant="contained" onClick={()=>{
              setNewProductQuantity(product.availableQuantity)
              setNewProductPrice(product.price.price)
              setEditProductID(product.id)
              setOpenEdit(true)
          }}>Edytuj</Button>
      </TableCell>}


  </TableRow>
  )
}


function ProductTable(props) {
  const [products, setProducts] = useState({ content: [] });
  
  const [open, setOpen] = useState(false)
  const [openEdit, setOpenEdit] = useState(false)
  const [openPriceHistory, setOpenPriceHistory] = useState(false)

  const [currentPage, setCurrentPage] = useState(0)
  
  const [newProductPrice, setNewProductPrice] = useState(0)
  const [newProductName, setNewProductName] = useState("")
  const [newProductCategory, setNewProductCategory] = useState("")
  const [newProductQuantity, setNewProductQuantity] = useState(0)
  const [addedProducts, setAddedProducts] = useState([])
  
  const [editProductID, setEditProductID] = useState(null)
  const [editProductPrice, setEditProductPrice] = useState(0)
  const [editProductQuantity, setEditProductQuantity] = useState(0)

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


  return (
    <div style={{ display: "flex", flexDirection: "column" }}>
      <EditProductDialog open={openEdit} setOpen={setOpenEdit} id={editProductID} price={editProductPrice} quantity={editProductQuantity}/>
      <PriceHistoryDialog open={openPriceHistory} setOpen={setOpenPriceHistory} />
      
      {addedProducts.length!= 0 && <ProductToOrder products={addedProducts} setAddedProducts={setAddedProducts}/>}

      <TableContainer component={Paper} sx={{margin:"1vw", padding: "1vw", width: "80vw" }}>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell align="left" sx={{ fontWeight: "bold" }}>
                Nazwa
              </TableCell>
              <TableCell align="right" sx={{ fontWeight: "bold" }}>
                Ilość dostępna w magazynie
              </TableCell>
              <TableCell align="right" sx={{ fontWeight: "bold" }}>
                Kategoria
              </TableCell>
              <TableCell align="right" sx={{ fontWeight: "bold" }}>
                Najniższa cena z ostatnich 30 dni
              </TableCell>
              <TableCell align="right" sx={{ fontWeight: "bold" }}>
                Cena
              </TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {products.content.map((product) => {
              if (!addedProducts.some(p => p.id === product.id)){
                if (props.userType == "CLIENT"){
                  return (UserProductView(product, setAddedProducts, addedProducts));
                }
                else{

                  return (AdminProductView(product, setOpenEdit, setOpenPriceHistory ,setEditProductQuantity, setEditProductPrice, setEditProductID));
                }
              }
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
