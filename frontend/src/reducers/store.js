import React from 'react';
import { configureStore, getDefaultMiddleware } from "@reduxjs/toolkit";
import userReducer from "./userReducer";
import postReducer from "./postReducer";
import { persistReducer } from "redux-persist";
import storage from "redux-persist/lib/storage";

const persistConfig = {
    key: "root",
    storage,
};

const persistedReducer = persistReducer(persistConfig, userReducer);

export default configureStore({
    reducer: {
        user: persistedReducer,
        post: postReducer,
    },
    middleware: getDefaultMiddleware({
        serializableCheck: false,
    }),
})
