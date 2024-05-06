import Nav from './Nav.jsx';
import React, { useEffect, useState } from 'react';

const Layout = ({ children }) => {
    const [logged, setLogged] = useState(false)
  
    useEffect(()=>{
      localStorage.getItem("logged") == null ? setLogged(false) : setLogged(true)
    },[])
  
  
    return (
      <div>
          <Nav logged={logged} setLogged={setLogged}/>
        {children}
      </div>
    );
  };
export default Layout