import React from 'react';
import { Navigate, Outlet } from 'react-router-dom';

const ProtectedRoutes = () => {
    const isAuth = localStorage.getItem("token") && localStorage.getItem('role')==="ADMIN";
    return isAuth ? <Outlet /> : <Navigate to="/" />;
  };

export default ProtectedRoutes;