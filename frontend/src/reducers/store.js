import { configureStore, getDefaultMiddleware } from "@reduxjs/toolkit";
import userReducer from "./userReducer";
import postReducer from "./postReducer";
import { persistReducer } from "redux-persist";
import storage from "redux-persist/lib/storage";

const persistConfig = {
    key: "root",
    storage,
};

const userPersistedReducer = persistReducer(persistConfig, userReducer);
const postPersistedReducer = persistReducer(persistConfig, postReducer);

export default configureStore({
    reducer: {
        user: userPersistedReducer,
        post: postPersistedReducer,
    },
    middleware: getDefaultMiddleware({
        serializableCheck: false,
    }),
})
