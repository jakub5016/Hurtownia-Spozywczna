import {
  Paper,
  Table,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
} from "@mui/material";
import ProductTable from "./productDatabase/PoductTable";
import { useState } from "react";
import ClientTable from "./clientDatabse/ClientTable";
import OrderTable from "./orderDatabase/OrderTable";

function AdminView(props) {
  return props.logged && props.userType == "ADMIN" ? (
    <div className="container">
      {props.selectedSite == 1 ? (
        <ProductTable userType="ADMIN" />
      ) : props.selectedSite == 2 ? (
        <ClientTable userType="ADMIN" />
      ) : (
        <OrderTable type="all" userType="ADMIN" />
      )}
    </div>
  ) : null;
}

export default AdminView;
