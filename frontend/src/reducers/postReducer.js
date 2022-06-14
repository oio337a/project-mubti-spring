import { createSlice } from "@reduxjs/toolkit";

const initialStateValue = {category: "", title: "", content: "", views: 0, votes: 0};

const postSilce = createSlice({
    name: "post",
    initialState: { value: initialStateValue},
    reducers: {
        savePost: (state, action) => {
            state.value = action.payload
        },
        readPost: (state, action) => {
            state.value = action.payload
        }
    },
})

export const { savePost, readPost } = postSilce.actions;

export default postSilce.reducer;