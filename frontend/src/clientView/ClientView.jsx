import OrderTable from "../adminView/orderDatabase/OrderTable"
import ProductTable from "../adminView/productDatabase/PoductTable"

function ClientView(props){

    return(
        props.logged && props.userType=="CLIENT" ?
        <div className="container">
        {props.selectedSite == 1 ? <OrderTable userType={props.userType}/> : <ProductTable userType={props.userType}/>}
        </div> : null
    )
}

export default ClientView