import login from '../auth/login.js';
import Nav from './Nav.jsx';
import React, { useEffect, useState } from 'react';

const Layout = ({ children }) => {
    const [logged, setLogged] = useState(false)
    const [userType, setUserType] = useState(null)
    useEffect(()=>{
      login("admin", "admin", setLogged)
    },[])
  
    console.log(localStorage.getItem("logged"))
    return (
      <div>
          <Nav selectedSite={children.props.selectedSite} setSelectedSite={children.props.setSelectedSite} logged={logged}/>
        {children}
      </div>
    );
  };
export default Layout