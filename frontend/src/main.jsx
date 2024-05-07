import React from 'react'
import ReactDOM from 'react-dom/client'
import './index.css'
import Layout from './layout/Layout.jsx'
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import LoginPage from '../login/LoginPage.jsx'
import AdminView from '../adminView/AdminView.jsx'

ReactDOM.createRoot(document.getElementById('root')).render(
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
            <Layout>
                <AdminView />
            </Layout>
          }
        />
      </Routes>
    </Router>
  </React.StrictMode>,
)
