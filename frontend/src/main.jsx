import React, { useState } from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import Layout from './layout/Layout.jsx';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import LoginPage from './login/LoginPage.jsx';
import AdminView from './adminView/AdminView.jsx';
import StorekeeperView from './storekeeperView/StorekeeperView.jsx';
import ClientView from './clientView/ClientView.jsx';

function Main() {
  // Step 2: Initialize state
  const [selectedSite, setSelectedSite] = useState(1);

  return (
    <React.StrictMode>
      <Router>
        <Routes>
          <Route
            path="/"
            element={ // Use the Layout component as the wrapper for the App component
              <Layout>
                <LoginPage/>
              </Layout>
            }
          />
          <Route
            path="/admin"
            element={ // Use the Layout component as the wrapper for the App component
              // Step 3: Pass state and setter as props
              <Layout selectedSite={selectedSite} setSelectedSite={setSelectedSite}>
                <AdminView selectedSite={selectedSite} setSelectedSite={setSelectedSite}/>
              </Layout>
            }
          />

          <Route
            path="/storekeeper"
            element={ // Use the Layout component as the wrapper for the App component
              // Step 3: Pass state and setter as props
              <Layout selectedSite={selectedSite} setSelectedSite={setSelectedSite}>
                <StorekeeperView selectedSite={selectedSite} setSelectedSite={setSelectedSite}/>
              </Layout>
            }
          />

          <Route
            path="/client"
            element={ // Use the Layout component as the wrapper for the App component
              // Step 3: Pass state and setter as props
              <Layout selectedSite={selectedSite} setSelectedSite={setSelectedSite}>
                <ClientView selectedSite={selectedSite} setSelectedSite={setSelectedSite}/>
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
