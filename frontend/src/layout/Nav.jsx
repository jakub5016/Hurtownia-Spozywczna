import Paper from "@mui/material/Paper"
import AccountCircleIcon from '@mui/icons-material/AccountCircle';
import LoginIcon from '@mui/icons-material/Login';
import { Button, Grid, Icon, IconButton } from "@mui/material";
import AdminNav from "../../adminView/AdminNav";


function Nav(props){

    return(        
        <Paper sx={{position:"sticky", padding:"20px", width:"90vw", display:"flex"}}>
            {localStorage.getItem("userType") == "admin" ?
            <AdminNav selectedSite={props.selectedSite} setSelectedSite={props.setSelectedSite}/> :  <div className="container"></div>}
            {/* User icon */}
            <div style={{textAlign:"right"}}> 
                {props.logged ? 
                    <IconButton>
                        <AccountCircleIcon sx={{fontSize:"40px"}}/>
                    </IconButton>
                    : 
                    <IconButton>
                        <LoginIcon sx={{fontSize:"40px"}}/>
                    </IconButton>}

            </div>
        </Paper>
    )
}

export default Nav