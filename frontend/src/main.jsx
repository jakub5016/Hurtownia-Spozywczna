import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App.jsx'
import './index.css'
import Layout from './layout/Layout.jsx'
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <Router>
      <Routes>
        <Route
          path="/"
          element={ // Use the Layout component as the wrapper for the App component
            <Layout>
              <App />
            </Layout>
          }
        />
         <Route
          path="/main"
          element={ // Use the Layout component as the wrapper for the App component
              <App />
          }
        />
      </Routes>
    </Router>
  </React.StrictMode>,
)
