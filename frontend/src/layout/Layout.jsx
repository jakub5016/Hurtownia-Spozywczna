import Nav from './Nav.jsx';
import React, { useEffect, useState } from 'react';

const Layout = ({ children }) => {
    const [logged, setLogged] = useState(false)
    const [userType, setUserType] = useState(null)
    useEffect(()=>{
      localStorage.getItem("logged") == null ? setLogged(false) : setLogged(true)
      localStorage.setItem("userType", "admin")
    },[])
  
  
    return (
      <div>
          <Nav selectedSite={children.props.selectedSite} setSelectedSite={children.props.setSelectedSite} logged={logged} setLogged={setLogged}/>
        {children}
      </div>
    );
  };
export default Layout