import { Button } from '@mui/material';
import login from '../auth/login.js';
import Nav from './Nav.jsx';
import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';

const Layout = ({ children }) => {
    return (
      <div>
          <Nav selectedSite={children.props.selectedSite} setSelectedSite={children.props.setSelectedSite} logged={children.props.logged} setLogged={children.props.setLogged}/>
        {children}
      </div>
    );
  };
export default Layout