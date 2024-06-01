import { Button } from '@mui/material';
import login from '../auth/login.js';
import Nav from './Nav.jsx';
import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';

const Layout = ({ children, logged, setLogged, userType, selectedSite, setSelectedSite }) => {
    return (
      <div>
          <Nav 
            selectedSite={selectedSite} 
            setSelectedSite={setSelectedSite} 
            logged={logged} 
            setLogged={setLogged} 
            userType={userType}/>
        {children}
      </div>
    );
  };
export default Layout