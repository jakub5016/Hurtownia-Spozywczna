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
} from "@mui/material";
import { useEffect, useState } from "react";
import getClientOrders from "./getClientOrders.js"

function OrderTable(props) {
    const [orders, setOrders] = useState([{orderedProducts:[]}])
    const [open, setOpen] = useState(false)
    const [selectedOrder, setSelectedOrder] = useState(0)

    useEffect(()=>{
        getClientOrders(setOrders)
    }, [])
    

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
                    orders.map((element, index) => {
                        return(
                            <TableRow>
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

      <Dialog open={open} onClose={()=>{setOpen(false)}} sx={{padding:"1vw"}}>
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
                        orders[selectedOrder].orderedProducts.map((element, index)=>{
                            console.log(orders[selectedOrder])
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
                            {orders[selectedOrder].totalPrice + " zł"}
                        </TableCell>
                    </TableRow>
                </TableBody>
            </Table>
      </Dialog>
    </div>
  );
}

export default OrderTable;
