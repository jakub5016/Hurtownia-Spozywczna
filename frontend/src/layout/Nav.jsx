import Paper from "@mui/material/Paper"
import AccountCircleIcon from '@mui/icons-material/AccountCircle';
import LoginIcon from '@mui/icons-material/Login';
import { Button, Grid, Icon, IconButton } from "@mui/material";
import AdminNav from "../adminView/AdminNav";
import StorekeeperNav from "../storekeeperView/StorekeeperNav";
import ExitToAppIcon from '@mui/icons-material/ExitToApp';
import ClientNav from "../clientView/ClientNav";
import { useNavigate } from "react-router-dom";
import logout from "../auth/logout";

function Nav(props){
    const navigate = useNavigate()
    return(        
        <Paper sx={{position:"sticky", padding:"20px", width:"90vw", display:"flex"}}>
            {props.logged ?
            props.userType == "ADMIN" ?
            <AdminNav selectedSite={props.selectedSite} setSelectedSite={props.setSelectedSite}/> :  
            props.userType == "EMPLOYEE"  ? 
            <StorekeeperNav selectedSite={props.selectedSite} setSelectedSite={props.setSelectedSite}/>: 
            props.userType == "CLIENT"  ? 
            <ClientNav selectedSite={props.selectedSite} setSelectedSite={props.setSelectedSite}/>
            :  <div className="container"></div> : <div className="container"></div>
            }
           
            {/* User icon */}
            <div style={{textAlign:"right"}}> 
                {props.logged ? 
                    <div style={{display:"flex"}}>
                    <IconButton>
                        <AccountCircleIcon sx={{fontSize:"40px"}}/>
                    </IconButton>
                    <IconButton onClick={()=>{logout(navigate)}}>
                        <ExitToAppIcon sx={{fontSize:"40px"}}/>
                    </IconButton>
                    </div>
                    : 
                    <IconButton onClick={()=>{navigate("/login")}}>
                        <LoginIcon sx={{fontSize:"40px"}}/>
                    </IconButton>}
            </div>
        </Paper>
    )
}

export default Nav