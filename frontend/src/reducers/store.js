import { configureStore, getDefaultMiddleware } from "@reduxjs/toolkit";
import userReducer from "./userReducer";
import postReducer from "./postReducer";
import { persistReducer } from "redux-persist";
import storage from "redux-persist/lib/storage";
import { combineReducers } from "redux";

const persistConfig = {
    key: "root",
    storage,
};

const rootReducer = combineReducers({
    user: userReducer,
    post: postReducer
})

const rootPersistedReducer = persistReducer(persistConfig, rootReducer);

export default configureStore({
    reducer: {
        root: rootPersistedReducer,
    },
    middleware: getDefaultMiddleware({
        serializableCheck: false,
    }),
})
