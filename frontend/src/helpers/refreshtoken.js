import axios from "axios";
import React from "react";
import { setAuthToken } from "../routes.js/setauth";

export const refreshToken = async (token ) => {
    try {
      const response = await axios.post("http://localhost:8080/auth/refresh",{}, { headers: { Authorization: `Bearer ${token}` } });
      const newToken = response.data.accessToken;
      console.log(newToken);
      localStorage.setItem("token", newToken);
      setAuthToken(newToken);
    } catch (error) {
      console.error(error);
    }
  };