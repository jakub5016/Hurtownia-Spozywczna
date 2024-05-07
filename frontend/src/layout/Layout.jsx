import Nav from './Nav.jsx';
import React, { useEffect, useState } from 'react';

const Layout = ({ children }) => {
    const [logged, setLogged] = useState(false)
    const [userType, setUserType] = useState(null)
    useEffect(()=>{
      // DEBUG
      localStorage.setItem("logged", "yes")
      localStorage.setItem("userType", "client")
      // DEbug
      localStorage.getItem("logged") == "null" ? setLogged(false) : setLogged(true)
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