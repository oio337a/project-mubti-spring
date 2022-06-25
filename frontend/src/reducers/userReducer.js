import { createSlice } from '@reduxjs/toolkit';

const initialStateValue = {accessToken: "", expiryTime: "", role: "", id: ""};

const userSlice = createSlice({
    name: "user",
    initialState: { value: initialStateValue},
    reducers:{
        login: (state, action) => {
            console.log("ASD",state, action)
                state.userReducer.value = action.payload
        },
        logout: (state) => {
            console.log("SDSDSD",state)
            state.value = initialStateValue;
        }
    },
})

export const { login, logout } = userSlice.actions;

export default userSlice.reducer;