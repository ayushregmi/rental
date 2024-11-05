import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  authToken: "",
  refreshToken: "",
  user: {
    name: "Userone",
    profileUrl: "default_user_icon.png",
  },
  loggedIn: true,
};

const userSlice = createSlice({
  name: "user",
  initialState,
  reducers: {
    login: (state, action) => {
      const { user, authToken, refreshToken } = action.payload;

      return {
        ...state,
        loggedIn: true,
        user,
        authToken: `Bearer ${authToken}`,
        refreshToken,
      };
    },

    logout: (state, action) => {
      return {
        ...state,
        user: {},
        loggedIn: false,
        authToken: "",
        refreshToken: "",
      };
    },
  },
});

export const userReducer = userSlice.reducer;
export const { login, logout } = userSlice.actions;
