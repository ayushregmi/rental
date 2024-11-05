import { configureStore } from "@reduxjs/toolkit";
import { createLogger } from "redux-logger";
import { userReducer } from "./user/UserSlice";

const logger = createLogger();

const store = configureStore({
  reducer: {
    user: userReducer,
  },
  middleware: (generalDefaultMiddleware) =>
    generalDefaultMiddleware().concat(logger),
});

export default store;
