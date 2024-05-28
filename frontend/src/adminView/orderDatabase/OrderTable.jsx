import {
  Paper,
  Button,
  Dialog,
  Table,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  TableBody,
  useThemeProps,
  Pagination,
} from "@mui/material";
import { useEffect, useState } from "react";
import getClientOrders from "./getClientOrders.js"
import getAllOrders from "./getAllOrdes.js";


const orderStatusColors = {
    REJECTED: '#F5A9A9',
    FINALIZED: '#A9F5A9',
    ACCEPTED: '#A9F5A9',
    IN_PROGRESS: '#A9D0F5',
    CREATED: '#F5F5A9',
    IN_DELIVERY: '#A9F5E1',
    CANCELLED: '#F5A9A9'
};


function OrderTable(props) {
    const [orders, setOrders] = useState({content:[{client:{name:"", address:""}, orderedProducts:[]}]})
    const [open, setOpen] = useState(false)
    const [selectedOrder, setSelectedOrder] = useState(0)
    const [currentPage, setCurrentPage] = useState(0)

    useEffect(()=>{
        if (props.userType == "CLIENT"){
            getClientOrders(setOrders)
        }
        else{
            getAllOrders(props.type, setOrders, currentPage)
        }
    }, [props.type])
    
    console.log(orders.content.orderedProducts)

  return (
    <div style={{display:"flex", flexDirection:"column"}}>
      <TableContainer component={Paper} sx={{ width: "80vw" }}>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell sx={{ fontWeight: "bold", textAlign:"left"}}>ID</TableCell>
              <TableCell sx={{ fontWeight: "bold", textAlign:"right" }}>Data Zamówienia</TableCell>
              <TableCell sx={{ fontWeight: "bold", textAlign:"right" }}>
                Data dostarczenia zamówiena
              </TableCell>
              <TableCell sx={{ fontWeight: "bold", textAlign:"right" }}>
                Status zamówiena
              </TableCell>
              <TableCell sx={{ fontWeight: "bold", textAlign:"right" }}>Cena Razem</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
                {
                    orders.content.map((element, index) => {
                        return(
                            <TableRow sx={{ backgroundColor: orderStatusColors[element.status] }} key={index}>
                                <TableCell sx={{textAlign:"left"}}>{element.id}</TableCell>
                                <TableCell sx={{textAlign:"right"}}>{element.orderDate}</TableCell>
                                <TableCell sx={{textAlign:"right"}}>{element.deliveryDate == null? "---" : element.deliveryDate}</TableCell>
                                <TableCell sx={{textAlign:"right"}}>{element.status}</TableCell>
                                <TableCell sx={{textAlign:"right"}}>{element.totalPrice + " zł"}</TableCell>
                                <TableCell sx={{textAlign:"center"}}>
                                        <Button onClick={()=>{setOpen(true); setSelectedOrder(index)}} variant="contained">Szczegóły</Button>
                                </TableCell>
                            </TableRow>
                        )
                    })
                }
          </TableBody>
        </Table>
      </TableContainer>
      {props.userType != "CLIENT" ? <Pagination
        sx={{ margin: "0 auto", marginTop:"30px" }}
        color="primary"
        count={orders.totalPages}
        onChange={(event, page)=>{setCurrentPage(page-1)}}
      />:null}
      <Dialog open={open} onClose={()=>{setOpen(false)}} sx={{padding:"1vw"}}>
            {
                props.userType != "CLIENT"?
                <div >
                    <h1>
                        Dane klienta
                    </h1>

                    <h3>
                        Imię i nazwisko:  <a style={{fontWeight:"normal", textAlign:"left", paddingLeft:"30px"}}>
                        {orders.content[selectedOrder].client.name}
                        </a>
                    </h3>
                    <h3>
                        Adres:  <a style={{fontWeight:"normal", textAlign:"left", paddingLeft:"30px"}}>
                        {orders.content[selectedOrder].client.address}
                        </a>
                    </h3>
                </div>
                : null 
            }

            <h1>Zamówione produkty</h1>
            <Table>
                <TableHead>
                    <TableRow>
                        <TableCell sx={{ fontWeight: "bold", textAlign:"left"}}>
                            Nazwa
                        </TableCell>
                        <TableCell sx={{ fontWeight: "bold", textAlign:"left"}}>
                            Ilość
                        </TableCell>
                        <TableCell sx={{ fontWeight: "bold", textAlign:"left"}}>
                            Cena za sztukę
                        </TableCell>
                        <TableCell sx={{ fontWeight: "bold", textAlign:"left"}}>
                            Cena ostateczna
                        </TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {   
                        orders.content.length > 0  && 
                        orders.content[selectedOrder].orderedProducts.map((element, index)=>{
                            return(
                                <TableRow key={index}>
                                    <TableCell sx={{textAlign:"left"}}>
                                        {element.product.name}
                                    </TableCell>
                                    <TableCell sx={{textAlign:"right"}}>
                                        {element.quantity}
                                    </TableCell>
                                    <TableCell sx={{textAlign:"right"}}>
                                        {element.price/element.quantity + " zł"}
                                    </TableCell>
                                    <TableCell sx={{textAlign:"right"}}>
                                        {element.price + " zł"}
                                    </TableCell>
                                </TableRow>
                            )
                        })
                    }
                    <TableRow >
                        <TableCell sx={{textAlign:"left",fontWeight: "bold", color:"green"}}>
                            Razem
                        </TableCell>
                        <TableCell>
                        </TableCell>
                        <TableCell>
                        </TableCell>
                        <TableCell sx={{textAlign:"right", color:"green"}}>
                            {orders.content.length != 0 && orders.content[selectedOrder].totalPrice + " zł"}
                        </TableCell>
                    </TableRow>
                </TableBody>
            </Table>
      </Dialog>
    </div>
  );
}

export default OrderTable;
