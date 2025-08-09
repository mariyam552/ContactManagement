import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Login from './login/Login';  
import ForgotPassword from './login/forgotpassword';
import Register from './login/register';
import ResetPassword from './login/resetpassword';
import Contacts from './Contact Managment screen/ContactList';
import ContactDetails from './Contact Managment screen/ContactDetails'; // ✅ New Import
import UserProfile from './User Profile/UserProfile';
import Layout from './layout/Layout'; 
import AdminDashboard from './Admin/AdminDashboard';

function App() {
  return (
    <Router>
      <Routes>

        {/* 🔒 Routes WITHOUT Header */}
        <Route path="/" element={<Login />} />
        <Route path="/login" element={<Login />} />
        <Route path="/forgot" element={<ForgotPassword />} />
        <Route path="/register" element={<Register />} />
        <Route path="/reset-password" element={<ResetPassword />} />
        <Route path="/admin-dashboard" element={<AdminDashboard />} />

        {/* 📌 Routes WITH Header */}
        <Route element={<Layout />}>
          <Route path="/contacts" element={<Contacts />} />
          <Route path="/contacts/:id" element={<ContactDetails />} /> {/* ✅ NEW ROUTE */}
          <Route path="/profile" element={<UserProfile />} />
          
        </Route>

      </Routes>
    </Router>
  );
}

export default App;
