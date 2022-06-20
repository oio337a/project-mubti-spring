import { createSlice } from "@reduxjs/toolkit";

const initialStateValue = {category: "", title: "", content: ""};

const postSilce = createSlice({
    name: "post",
    initialState: { value: initialStateValue},
    reducers: {
        savePost: (state, action) => {
            state.value = action.payload
        }
    },
})

export const { savePost, readPost } = postSilce.actions;

export default postSilce.reducer;