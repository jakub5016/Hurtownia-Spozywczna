import {
  Button,
  Dialog,
  InputLabel,
  MenuItem,
  Pagination,
  Paper,
  Select,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  TextField,
} from "@mui/material";
import { useEffect, useState } from "react";
import getProducts from "./getProducts";
import addProduct from "./addProduct";
import ProductToOrder from "./ProductToOrder";
import EditProductDialog from "./EditProductDialog";
import PriceHistoryDialog from "../priceDatabase/PriceHistoryDialog";
import searchProducts from "./searchProducts";
import getProductsCategory from "./getProductsCategory";


function UserProductView(product, setAddedProducts, addedProducts){
  return(
  <TableRow key={product.id}>
    <TableCell align="left">{product.name}</TableCell>
    <TableCell align="right">
      {product.availableQuantity}
    </TableCell>
    <TableCell align="right">{formatujTekst(product.category)}</TableCell>
    <TableCell align="right">
      {product.price.lowestFrom30Days +" zł"}
    </TableCell>
    <TableCell align="right">{product.price.price +" zł"}</TableCell>
    <TableCell align="right">
          <Button sx={{borderRadius:"80vw"}} variant="contained" onClick={()=>{
              setAddedProducts(addedProducts.concat(product))
          }}>+</Button>
      </TableCell>
  </TableRow>
  )
}

function AdminProductView(product, setOpenEdit, setOpenPriceHistory, setPriceHistoryProductID ,setNewProductQuantity, setNewProductPrice, setEditProductID){
  return(
  <TableRow key={product.id} sx={{background: product.archived?"gray":"white"}}>
    <TableCell align="left">{product.name}</TableCell>
    <TableCell align="right">
      {product.availableQuantity}
    </TableCell>
    <TableCell align="right">{formatujTekst(product.category)}</TableCell>
    <TableCell align="right">
      {product.price.lowestFrom30Days +" zł"}
    </TableCell>
    <TableCell align="right">{product.price.price +" zł"}</TableCell>
    <TableCell align="right">
          <Button sx={{borderRadius:"80vw", fontSize:"12px"}} variant="contained" onClick={()=>{
            setOpenPriceHistory(true)
            setPriceHistoryProductID(product.id)
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

function formatujTekst(tekst) {
  return tekst.toLowerCase().replace(/_/g, ' ').replace(/(?:^|\s)\S/g, function(a) { return a.toUpperCase(); });
}


function ProductTable(props) {
  const [products, setProducts] = useState({ content: [] });
  
  const [open, setOpen] = useState(false)
  const [openEdit, setOpenEdit] = useState(false)
  const [openPriceHistory, setOpenPriceHistory] = useState(false)

  const [currentPage, setCurrentPage] = useState(0)
  const [fetchCategory, setFetchCategory] = useState("")

  const [newProductPrice, setNewProductPrice] = useState(0)
  const [newProductName, setNewProductName] = useState("")
  const [newProductCategory, setNewProductCategory] = useState("")
  const [newProductQuantity, setNewProductQuantity] = useState(0)
  const [addedProducts, setAddedProducts] = useState([])
  
  const [editProductID, setEditProductID] = useState(null)
  const [editProductPrice, setEditProductPrice] = useState(0)
  const [editProductQuantity, setEditProductQuantity] = useState(0)
  
  const [priceHistoryProductID, setPriceHistoryProductID] = useState(1)

  useEffect(()=>{
    if (fetchCategory != ""){
      let isMounted = true;
      getProductsCategory(fetchCategory, currentPage).then((data) => {
        if (isMounted) {
          setProducts(data);
        }
      });
      return () => {
        isMounted = false;
      };
    } 
    else{
      let isMounted = true;
      getProducts(currentPage).then((data) => {
        if (isMounted) {
          setProducts(data);
        }
      });
      return () => {
        isMounted = false;
      };
    }

  }, [fetchCategory, currentPage])

  return (
    <div style={{ display: "flex", flexDirection: "column" }}>
      <EditProductDialog open={openEdit} setOpen={setOpenEdit} id={editProductID} price={editProductPrice} quantity={editProductQuantity}/>
      <PriceHistoryDialog open={openPriceHistory} setOpen={setOpenPriceHistory} id={priceHistoryProductID}/>
      
      {addedProducts.length!= 0 && <ProductToOrder products={addedProducts} setAddedProducts={setAddedProducts}/>}
      
      <TextField 
        label="Wyszukaj produkt" 
        variant="filled" 
        onChange={(e)=>{
          let isMounted = true;
          console.log(e.target.value)
          searchProducts(e.target.value, currentPage).then((data) => {
              setProducts(data);
              console.log(data.content)
          })
        }}  
      />
      
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
              <select label="Kategoria" onChange={(e)=>{setFetchCategory(e.target.value)}}>
                    <option value="">Kategoria</option>
                        <optgroup label="Artykuły spożywcze">
                            <option value="WARZYWA_I_OWOCE">Warzywa i owoce</option>
                            <option value="PIECZYWO">Pieczywo</option>
                            <option value="NABIAL">Nabiał</option>
                            <option value="MIESO_I_WEDLINY">Mięso i wędliny</option>
                            <option value="RYBY_I_OWOCE_MORZA">Ryby i owoce morza</option>
                            <option value="MROZONKI">Mrożonki</option>
                            <option value="KONSERWY">Konserwy</option>
                            <option value="PRODUKTY_SYPKIE">Produkty sypkie</option>
                            <option value="PRZYPRAWY_I_ZIOLA">Przyprawy i zioła</option>
                            <option value="SLODYCZE_I_PRZEKASKI">Słodycze i przekąski</option>
                            <option value="NAPOJE_BEZALKOHOLOWE">Napoje bezalkoholowe</option>
                            <option value="NAPOJE_ALKOHOLOWE">Napoje alkoholowe</option>
                            <option value="OLEJE_I_TLUSZCZE">Oleje i tłuszcze</option>
                            <option value="PRODUKTY_DIETETYCZNE_I_EKOLOGICZNE">Produkty dietetyczne i ekologiczne</option>
                            <option value="PRODUKTY_BEZGLUTENOWE">Produkty bezglutenowe</option>
                            <option value="KAWA_HERBATA_I_KAKAO">Kawa, herbata i kakao</option>
                            <option value="SOSY_I_DRESSINGI">Sosy i dressingi</option>
                            <option value="PRZETWORY_OWOCOWE_I_WARZYWNE">Przetwory owocowe i warzywne</option>
                            <option value="PIECZYWO_CHRUPKIE_I_SUCHARKI">Pieczywo chrupkie i sucharki</option>
                            <option value="ZUPY_INSTANT_I_DANIA_GOTOWE">Zupy instant i dania gotowe</option>
                            <option value="PLATKI_SNIADANIOWE_I_MUSLI">Płatki śniadaniowe i musli</option>
                            <option value="PRODUKTY_DLA_DZIECI">Produkty dla dzieci</option>
                            <option value="PRZEKASKI_NA_WYNOS">Przekąski na wynos</option>
                            <option value="ARTYKULY_CUKIERNICZE">Artykuły cukiernicze</option>
                            <option value="ARTYKULY_SNIADANIOWE">Artykuły śniadaniowe</option>
                        </optgroup>
                        <optgroup label="Artykuły budowlane">
                            <option value="SRUBY">Śruby</option>
                            <option value="NAKRETKI">Nakrętki</option>
                            <option value="PODKLADKI">Podkładki</option>
                            <option value="KRZYZAKI_DO_GLAZURY">Krzyżaki do glazury</option>
                            <option value="WKRETY">Wkręty</option>
                            <option value="KOLKI_ROZPOROWE">Kołki rozporowe</option>
                            <option value="GWOZDZIE">Gwoździe</option>
                            <option value="NITY">Nity</option>
                            <option value="KLEJE_I_SILIKONY">Kleje i silikony</option>
                            <option value="TASMY_BUDOWLANE">Tasmy budowlane</option>
                            <option value="NARZEDZIA_RECZNE">Narzędzia ręczne</option>
                            <option value="NARZEDZIA_ELEKTRYCZNE">Narzędzia elektryczne</option>
                            <option value="DRABINY_I_RUSZTOWANIA">Drabiny i rusztowania</option>
                            <option value="MATERIALY_IZOLACYJNE">Materiały izolacyjne</option>
                            <option value="USZCZELKI_I_PROFILE">Uszczelki i profile</option>
                            <option value="FARBY_I_LAKIERY">Farby i lakiery</option>
                            <option value="WALKI_I_PEDZLE_MALARSKIE">Wałki i pędzle malarskie</option>
                            <option value="OSWIETLENIE">Oświetlenie</option>
                            <option value="AKCESORIA_ELEKTRYCZNE">Akcesoria elektryczne</option>
                            <option value="PRZEDLUZACZE_I_LISTWY_ZASILAJACE">Przedłużacze i listwy zasilające</option>
                            <option value="RURY_I_ZLACZKI_HYDRAULICZNE">Rury i złączki hydrauliczne</option>
                            <option value="KRANY_I_BATERIE_LAZIENKOWE">Kran i baterie łazienkowe</option>
                            <option value="WENTYLATORY_I_KLIMATYZATORY">Wentylatory i klimatyzatory</option>
                            <option value="PANELE_PODLOGOWE_I_LISTWY_PRZYPODLOGOWE">Panele podłogowe i listwy przypodłogowe</option>
                            <option value="MATERIALY_WYKONCZENIOWE">Materiały wykończeniowe</option>
                        </optgroup>
                    </select>              </TableCell>
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

                  return (AdminProductView(product, setOpenEdit, setOpenPriceHistory , setPriceHistoryProductID ,setEditProductQuantity, setEditProductPrice, setEditProductID));
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
                    <select onChange={(e)=>{setNewProductCategory(e.target.value)}}>
                    <option value="">Wybierz kategorię</option>
                        <optgroup label="Artykuły spożywcze">
                            <option value="WARZYWA_I_OWOCE">Warzywa i owoce</option>
                            <option value="PIECZYWO">Pieczywo</option>
                            <option value="NABIAL">Nabiał</option>
                            <option value="MIESO_I_WEDLINY">Mięso i wędliny</option>
                            <option value="RYBY_I_OWOCE_MORZA">Ryby i owoce morza</option>
                            <option value="MROZONKI">Mrożonki</option>
                            <option value="KONSERWY">Konserwy</option>
                            <option value="PRODUKTY_SYPKIE">Produkty sypkie</option>
                            <option value="PRZYPRAWY_I_ZIOLA">Przyprawy i zioła</option>
                            <option value="SLODYCZE_I_PRZEKASKI">Słodycze i przekąski</option>
                            <option value="NAPOJE_BEZALKOHOLOWE">Napoje bezalkoholowe</option>
                            <option value="NAPOJE_ALKOHOLOWE">Napoje alkoholowe</option>
                            <option value="OLEJE_I_TLUSZCZE">Oleje i tłuszcze</option>
                            <option value="PRODUKTY_DIETETYCZNE_I_EKOLOGICZNE">Produkty dietetyczne i ekologiczne</option>
                            <option value="PRODUKTY_BEZGLUTENOWE">Produkty bezglutenowe</option>
                            <option value="KAWA_HERBATA_I_KAKAO">Kawa, herbata i kakao</option>
                            <option value="SOSY_I_DRESSINGI">Sosy i dressingi</option>
                            <option value="PRZETWORY_OWOCOWE_I_WARZYWNE">Przetwory owocowe i warzywne</option>
                            <option value="PIECZYWO_CHRUPKIE_I_SUCHARKI">Pieczywo chrupkie i sucharki</option>
                            <option value="ZUPY_INSTANT_I_DANIA_GOTOWE">Zupy instant i dania gotowe</option>
                            <option value="PLATKI_SNIADANIOWE_I_MUSLI">Płatki śniadaniowe i musli</option>
                            <option value="PRODUKTY_DLA_DZIECI">Produkty dla dzieci</option>
                            <option value="PRZEKASKI_NA_WYNOS">Przekąski na wynos</option>
                            <option value="ARTYKULY_CUKIERNICZE">Artykuły cukiernicze</option>
                            <option value="ARTYKULY_SNIADANIOWE">Artykuły śniadaniowe</option>
                        </optgroup>
                        <optgroup label="Artykuły budowlane">
                            <option value="SRUBY">Śruby</option>
                            <option value="NAKRETKI">Nakrętki</option>
                            <option value="PODKLADKI">Podkładki</option>
                            <option value="KRZYZAKI_DO_GLAZURY">Krzyżaki do glazury</option>
                            <option value="WKRETY">Wkręty</option>
                            <option value="KOLKI_ROZPOROWE">Kołki rozporowe</option>
                            <option value="GWOZDZIE">Gwoździe</option>
                            <option value="NITY">Nity</option>
                            <option value="KLEJE_I_SILIKONY">Kleje i silikony</option>
                            <option value="TASMY_BUDOWLANE">Tasmy budowlane</option>
                            <option value="NARZEDZIA_RECZNE">Narzędzia ręczne</option>
                            <option value="NARZEDZIA_ELEKTRYCZNE">Narzędzia elektryczne</option>
                            <option value="DRABINY_I_RUSZTOWANIA">Drabiny i rusztowania</option>
                            <option value="MATERIALY_IZOLACYJNE">Materiały izolacyjne</option>
                            <option value="USZCZELKI_I_PROFILE">Uszczelki i profile</option>
                            <option value="FARBY_I_LAKIERY">Farby i lakiery</option>
                            <option value="WALKI_I_PEDZLE_MALARSKIE">Wałki i pędzle malarskie</option>
                            <option value="OSWIETLENIE">Oświetlenie</option>
                            <option value="AKCESORIA_ELEKTRYCZNE">Akcesoria elektryczne</option>
                            <option value="PRZEDLUZACZE_I_LISTWY_ZASILAJACE">Przedłużacze i listwy zasilające</option>
                            <option value="RURY_I_ZLACZKI_HYDRAULICZNE">Rury i złączki hydrauliczne</option>
                            <option value="KRANY_I_BATERIE_LAZIENKOWE">Kran i baterie łazienkowe</option>
                            <option value="WENTYLATORY_I_KLIMATYZATORY">Wentylatory i klimatyzatory</option>
                            <option value="PANELE_PODLOGOWE_I_LISTWY_PRZYPODLOGOWE">Panele podłogowe i listwy przypodłogowe</option>
                            <option value="MATERIALY_WYKONCZENIOWE">Materiały wykończeniowe</option>
                        </optgroup>
                    </select>
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
