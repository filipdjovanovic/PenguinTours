import React from 'react';
import { Navigate, Outlet, Route } from 'react-router-dom';

const ProtectedRoutesS = () => {
    const isAuth = localStorage.getItem("token") && localStorage.getItem('role')==="STAFF";
    return isAuth ? <Outlet /> : <Navigate to="/" />;
  };

export default ProtectedRoutesS;