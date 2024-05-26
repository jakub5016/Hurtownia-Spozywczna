import React, { useEffect, useState } from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import Layout from './layout/Layout.jsx';
import { BrowserRouter as Router, Route, Routes, useNavigate } from 'react-router-dom';
import LoginPage from './login/LoginPage.jsx';
import AdminView from './adminView/AdminView.jsx';
import StorekeeperView from './storekeeperView/StorekeeperView.jsx';
import ClientView from './clientView/ClientView.jsx';
import BasicView from './BasicView.jsx';
import Register from './register/Register.jsx';
import PrivacyPolitics from './privacy/PrivacyPolitics.jsx';
import authourize from './auth/authorize.js'


function Main() {
  // Step 2: Initialize state
  const [selectedSite, setSelectedSite] = useState(1);
  const [logged, setLogged] = useState(false)
  const [userType, setUserType] = useState("")
  useEffect(()=>{
    authourize(setUserType, setLogged)
  }, [])

  return (
    <React.StrictMode>
      <Router>
        <Routes>
          <Route
            path="/"
            element={ // Use the Layout component as the wrapper for the App component
              <Layout logged={logged} setLogged={setLogged} userType={userType} selectedSite={selectedSite} setSelectedSite={setSelectedSite}>
                <BasicView/>
              </Layout>
            }
          />
          <Route
            path="/login"
            element={ // Use the Layout component as the wrapper for the App component
              <Layout logged={logged} setLogged={setLogged} userType={userType} selectedSite={selectedSite} setSelectedSite={setSelectedSite}>
                <LoginPage logged={logged} setLogged={setLogged}/>
              </Layout>
            }
          />
          <Route
            path="/register"
            element={ // Use the Layout component as the wrapper for the App component
              <Layout logged={logged} setLogged={setLogged} userType={userType} selectedSite={selectedSite} setSelectedSite={setSelectedSite}>
                <Register/>
              </Layout>
            }
          />
          <Route
            path="/privacy"
            element={ // Use the Layout component as the wrapper for the App component
              <Layout logged={logged} setLogged={setLogged} userType={userType} selectedSite={selectedSite} setSelectedSite={setSelectedSite}>
                <PrivacyPolitics/>
              </Layout>
            }
          />
          <Route
            path="/admin"
            element={ // Use the Layout component as the wrapper for the App component
              // Step 3: Pass state and setter as props
              <Layout logged={logged} setLogged={setLogged} userType={userType} selectedSite={selectedSite} setSelectedSite={setSelectedSite}>
                <AdminView logged={logged} userType={userType} selectedSite={selectedSite} setSelectedSite={setSelectedSite}/>
              </Layout>
            }
          />

          <Route
            path="/storekeeper"
            element={ // Use the Layout component as the wrapper for the App component
              // Step 3: Pass state and setter as props
              <Layout logged={logged} setLogged={setLogged} userType={userType} selectedSite={selectedSite} setSelectedSite={setSelectedSite}>
                <StorekeeperView logged={logged} userType={userType} selectedSite={selectedSite} setSelectedSite={setSelectedSite}/>
              </Layout>
            }
          />

          <Route
            path="/client"
            element={ // Use the Layout component as the wrapper for the App component
              // Step 3: Pass state and setter as props
              <Layout logged={logged} setLogged={setLogged} userType={userType} selectedSite={selectedSite} setSelectedSite={setSelectedSite}>
                <ClientView logged={logged} userType={userType} selectedSite={selectedSite} setSelectedSite={setSelectedSite}/>
              </Layout>
            }
          />
        </Routes>
      </Router>
    </React.StrictMode>
  );
}

// Render the Main component
ReactDOM.createRoot(document.getElementById('root')).render(<Main />);
